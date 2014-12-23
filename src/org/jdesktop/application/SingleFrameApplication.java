/*
 * Decompiled with CFR 0_92.
 */
package org.jdesktop.application;

import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.JWindow;
import javax.swing.RootPaneContainer;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SessionStorage;
import org.jdesktop.application.View;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class SingleFrameApplication extends Application {
	private static final Logger logger = Logger
			.getLogger(SingleFrameApplication.class.getName());
	private ResourceMap appResources = null;
	private FrameView mainView = null;

	public final JFrame getMainFrame() {
		return this.getMainView().getFrame();
	}

	protected final void setMainFrame(JFrame jFrame) {
		this.getMainView().setFrame(jFrame);
	}

	private String sessionFilename(Window window) {
		if (window == null) {
			return null;
		}
		String string = window.getName();
		return string == null ? null : string + ".session.xml";
	}

	protected void configureWindow(Window window) {
		this.getContext().getResourceMap().injectComponents(window);
	}

	private void initRootPaneContainer(RootPaneContainer rootPaneContainer) {
		Object object;
		JFrame jFrame;
		String string;
		JRootPane jRootPane = rootPaneContainer.getRootPane();
		if (jRootPane
				.getClientProperty(string = "SingleFrameApplication.initRootPaneContainer") != null) {
			return;
		}
		jRootPane.putClientProperty(string, Boolean.TRUE);
		Container container = jRootPane.getParent();
		if (container instanceof Window) {
			this.configureWindow((Window) container);
		}
		if (rootPaneContainer == (jFrame = this.getMainFrame())) {
			jFrame.addWindowListener(new MainFrameListener());
			jFrame.setDefaultCloseOperation(0);
		} else if (container instanceof Window) {
			object = (Window) container;
			object.addHierarchyListener(new SecondaryWindowListener());
		}
		if (container instanceof JFrame) {
			container.addComponentListener(new FrameBoundsListener());
		}
		if (container instanceof Window) {
			object = (Window) container;
			if (!(container.isValid() && container.getWidth() != 0 && container
					.getHeight() != 0)) {
				object.pack();
			}
			if (!(object.isLocationByPlatform() || container.getX() != 0 || container
					.getY() != 0)) {
				Window window = object.getOwner();
				if (window == null) {
					window = object != jFrame ? jFrame : null;
				}
				object.setLocationRelativeTo(window);
			}
		}
		if (container instanceof Window
				&& (object = this.sessionFilename((Window) container)) != null) {
			try {
				this.getContext().getSessionStorage()
						.restore(container, (String) object);
			} catch (Exception var7_8) {
				String string2 = String.format(
						"couldn't restore sesssion [%s]", object);
				logger.log(Level.WARNING, string2, var7_8);
			}
		}
	}

	protected void show(JComponent jComponent) {
		if (jComponent == null) {
			throw new IllegalArgumentException("null JComponent");
		}
		JFrame jFrame = this.getMainFrame();
		jFrame.getContentPane().add((Component) jComponent, "Center");
		this.initRootPaneContainer(jFrame);
		jFrame.setVisible(true);
	}

	public void show(JDialog jDialog) {
		if (jDialog == null) {
			throw new IllegalArgumentException("null JDialog");
		}
		this.initRootPaneContainer(jDialog);
		jDialog.setVisible(true);
	}

	public void show(JFrame jFrame) {
		if (jFrame == null) {
			throw new IllegalArgumentException("null JFrame");
		}
		this.initRootPaneContainer(jFrame);
		jFrame.setVisible(true);
	}

	private void saveSession(Window window) {
		String string = this.sessionFilename(window);
		if (string != null) {
			try {
				this.getContext().getSessionStorage().save(window, string);
			} catch (IOException var3_3) {
				logger.log(Level.WARNING, "couldn't save sesssion", var3_3);
			}
		}
	}

	private boolean isVisibleWindow(Window window) {
		return window.isVisible()
				&& (window instanceof JFrame || window instanceof JDialog || window instanceof JWindow);
	}

	/*
	 * Enabled force condition propagation Lifted jumps to return sites
	 */
	private List<Window> getVisibleSecondaryWindows() {
		ArrayList<Window> arrayList = new ArrayList<Window>();
		Method method = null;
		try {
			method = Window.class.getMethod("getWindows", new Class[0]);
		} catch (Exception var3_3) {
			// empty catch block
		}
		if (method != null) {
			Window[] arrwindow = null;
			try {
				arrwindow = (Window[]) method.invoke(null, new Object[0]);
			} catch (Exception var4_6) {
				throw new Error("HCTB - can't get top level windows list",
						var4_6);
			}
			if (arrwindow == null)
				return arrayList;
			for (Window window : arrwindow) {
				if (!this.isVisibleWindow(window))
					continue;
				arrayList.add(window);
			}
			return arrayList;
		}
		Frame[] arrframe = Frame.getFrames();
		if (arrframe == null)
			return arrayList;
		for (Frame frame : arrframe) {
			if (!this.isVisibleWindow(frame))
				continue;
			arrayList.add(frame);
		}
		return arrayList;
	}

	@Override
	protected void shutdown() {
		this.saveSession(this.getMainFrame());
		for (Window window : this.getVisibleSecondaryWindows()) {
			this.saveSession(window);
		}
	}

	public FrameView getMainView() {
		if (this.mainView == null) {
			this.mainView = new FrameView(this);
		}
		return this.mainView;
	}

	@Override
	public void show(View view) {
		if (this.mainView == null && view instanceof FrameView) {
			this.mainView = (FrameView) view;
		}
		RootPaneContainer rootPaneContainer = (RootPaneContainer) view
				.getRootPane().getParent();
		this.initRootPaneContainer(rootPaneContainer);
		((Window) rootPaneContainer).setVisible(true);
	}

	private static class FrameBoundsListener implements ComponentListener {
		private FrameBoundsListener() {
		}

		private void maybeSaveFrameSize(ComponentEvent componentEvent) {
			JFrame jFrame;
			if (componentEvent.getComponent() instanceof JFrame
					&& ((jFrame = (JFrame) componentEvent.getComponent())
							.getExtendedState() & 6) == 0) {
				String string = "WindowState.normalBounds";
				jFrame.getRootPane().putClientProperty(string,
						jFrame.getBounds());
			}
		}

		public void componentResized(ComponentEvent componentEvent) {
			this.maybeSaveFrameSize(componentEvent);
		}

		public void componentMoved(ComponentEvent componentEvent) {
		}

		public void componentHidden(ComponentEvent componentEvent) {
		}

		public void componentShown(ComponentEvent componentEvent) {
		}
	}

	private class MainFrameListener extends WindowAdapter {
		private MainFrameListener() {
		}

		public void windowClosing(WindowEvent windowEvent) {
			SingleFrameApplication.this.exit(windowEvent);
		}
	}

	private class SecondaryWindowListener implements HierarchyListener {
		private SecondaryWindowListener() {
		}

		public void hierarchyChanged(HierarchyEvent hierarchyEvent) {
			Window window;
			if ((hierarchyEvent.getChangeFlags() & 4) != 0
					&& hierarchyEvent.getSource() instanceof Window
					&& !(window = (Window) hierarchyEvent.getSource())
							.isShowing()) {
				SingleFrameApplication.this.saveSession(window);
			}
		}
	}

}
