/*
 * Decompiled with CFR 0_92.
 */
package org.jdesktop.application;

import java.awt.AWTEvent;
import java.awt.ActiveEvent;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.PaintEvent;
import java.beans.Beans;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.jdesktop.application.AbstractBean;
import org.jdesktop.application.Action;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.ProxyActions;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.Task;
import org.jdesktop.application.View;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@ProxyActions(value = { "cut", "copy", "paste", "delete" })
public abstract class Application extends AbstractBean {
	private static final Logger logger = Logger.getLogger(Application.class
			.getName());
	private static Application application = null;
	private final List<ExitListener> exitListeners = new CopyOnWriteArrayList<ExitListener>();
	private final ApplicationContext context = new ApplicationContext();

	protected Application() {
	}

	public static synchronized <T extends Application> void launch(
			final Class<T> class_, final String[] arrstring) {
		Runnable runnable = new Runnable() {

			public void run() {
				try {
					Application.application = Application.create(class_);
					application.initialize(arrstring);
					application.startup();
					application.waitForReady();
				} catch (Exception var1_1) {
					String string = String.format(
							"Application %s failed to launch", class_);
					logger.log(Level.SEVERE, string, var1_1);
					throw new Error(string, var1_1);
				}
			}
		};
		SwingUtilities.invokeLater(runnable);
	}

	static <T extends Application> T create(Class<T> class_) throws Exception {
		Constructor<T> constructor;
		if (!Beans.isDesignTime()) {
			try {
				System.setProperty("java.net.useSystemProxies", "true");
			} catch (SecurityException var1_1) {
				// empty catch block
			}
		}
		if (!(constructor = class_.getDeclaredConstructor(new Class[0]))
				.isAccessible()) {
			try {
				constructor.setAccessible(true);
			} catch (SecurityException var2_3) {
				// empty catch block
			}
		}
		Application application = (Application) constructor
				.newInstance(new Object[0]);
		ApplicationContext applicationContext = application.getContext();
		applicationContext.setApplicationClass(class_);
		applicationContext.setApplication(application);
		ResourceMap resourceMap = applicationContext.getResourceMap();
		resourceMap.putResource("platform", Application.platform());
		if (!Beans.isDesignTime()) {
			String string = "Application.lookAndFeel";
			String string2 = resourceMap.getString(string, new Object[0]);
			String string3 = string2 == null ? "system" : string2;
			try {
				if (string3.equalsIgnoreCase("system")) {
					String string4 = UIManager.getSystemLookAndFeelClassName();
					UIManager.setLookAndFeel(string4);
				} else if (!string3.equalsIgnoreCase("default")) {
					UIManager.setLookAndFeel(string3);
				}
			} catch (Exception var8_11) {
				String string5 = "Couldn't set LookandFeel " + string + " = \""
						+ string2 + "\"";
				logger.log(Level.WARNING, string5, var8_11);
			}
		}
		return (T) application;
	}

	private static String platform() {
		String string = "default";
		try {
			String string2 = System.getProperty("os.name");
			if (string2 != null && string2.toLowerCase().startsWith("mac os x")) {
				string = "osx";
			}
		} catch (SecurityException var1_2) {
			// empty catch block
		}
		return string;
	}

	void waitForReady() {
		new DoWaitForEmptyEventQ().execute();
	}

	protected void initialize(String[] arrstring) {
	}

	protected abstract void startup();

	protected void ready() {
	}

	protected void shutdown() {
	}

	private void waitForEmptyEventQ() {
		boolean bl = false;
		JPanel jPanel = new JPanel();
		EventQueue eventQueue = Toolkit.getDefaultToolkit()
				.getSystemEventQueue();
		while (!bl) {
			NotifyingEvent notifyingEvent = new NotifyingEvent(jPanel);
			eventQueue.postEvent(notifyingEvent);
			NotifyingEvent notifyingEvent2 = notifyingEvent;
			synchronized (notifyingEvent2) {
				while (!notifyingEvent.isDispatched()) {
					try {
						notifyingEvent.wait();
					} catch (InterruptedException var6_6) {
					}
				}
				bl = notifyingEvent.isEventQEmpty();
				continue;
			}
		}
	}

	public final void exit() {
		this.exit(null);
	}

	public void exit(EventObject eventObject) {
		for (ExitListener exitListener2 : this.exitListeners) {
			if (exitListener2.canExit(eventObject))
				continue;
			return;
		}
		try {
			for (ExitListener exitListener2 : this.exitListeners) {
				try {
					exitListener2.willExit(eventObject);
				} catch (Exception var4_5) {
					logger.log(Level.WARNING, "ExitListener.willExit() failed",
							var4_5);
				}
			}
			this.shutdown();
		} catch (Exception var2_3) {
			logger.log(Level.WARNING,
					"unexpected error in Application.shutdown()", var2_3);
		} finally {
			this.end();
		}
	}

	protected void end() {
		Runtime.getRuntime().exit(0);
	}

	public void addExitListener(ExitListener exitListener) {
		this.exitListeners.add(exitListener);
	}

	public void removeExitListener(ExitListener exitListener) {
		this.exitListeners.remove(exitListener);
	}

	public ExitListener[] getExitListeners() {
		int n = this.exitListeners.size();
		return this.exitListeners.toArray(new ExitListener[n]);
	}

	@Action
	public void quit(ActionEvent actionEvent) {
		this.exit(actionEvent);
	}

	public final ApplicationContext getContext() {
		return this.context;
	}

	public static synchronized <T extends Application> T getInstance(
			Class<T> class_) {
		if (application == null) {
			try {
				application = Application.create(class_);
			} catch (Exception var1_1) {
				String string = String.format("Couldn't construct %s", class_);
				throw new Error(string, var1_1);
			}
		}
		return (T) ((Application) class_.cast(application));
	}

	public static synchronized Application getInstance() {
		if (application == null) {
			application = new NoApplication();
		}
		return application;
	}

	public void show(View view) {
		Window window = (Window) view.getRootPane().getParent();
		if (window != null) {
			window.pack();
			window.setVisible(true);
		}
	}

	public void hide(View view) {
		view.getRootPane().getParent().setVisible(false);
	}

	/*
	 * This class specifies class file version 49.0 but uses Java 6 signatures.
	 * Assumed Java 6.
	 */
	private class DoWaitForEmptyEventQ extends Task<Void, Void> {
		DoWaitForEmptyEventQ() {
			super(Application.this);
		}

		@Override
		protected Void doInBackground() {
			Application.this.waitForEmptyEventQ();
			return null;
		}

		@Override
		protected void finished() {
			Application.this.ready();
		}
	}

	public static interface ExitListener extends EventListener {
		public boolean canExit(EventObject var1);

		public void willExit(EventObject var1);
	}

	private static class NoApplication extends Application {
		protected NoApplication() {
			ApplicationContext applicationContext = this.getContext();
			applicationContext.setApplicationClass(this.getClass());
			applicationContext.setApplication(this);
			ResourceMap resourceMap = applicationContext.getResourceMap();
			resourceMap.putResource("platform", Application.platform());
		}

		protected void startup() {
		}
	}

	private static class NotifyingEvent extends PaintEvent implements
			ActiveEvent {
		private boolean dispatched = false;
		private boolean qEmpty = false;

		NotifyingEvent(Component component) {
			super(component, 801, null);
		}

		synchronized boolean isDispatched() {
			return this.dispatched;
		}

		synchronized boolean isEventQEmpty() {
			return this.qEmpty;
		}

		public void dispatch() {
			EventQueue eventQueue = Toolkit.getDefaultToolkit()
					.getSystemEventQueue();
			NotifyingEvent notifyingEvent = this;
			synchronized (notifyingEvent) {
				this.qEmpty = eventQueue.peekEvent() == null;
				this.dispatched = true;
				this.notifyAll();
			}
		}
	}

}
