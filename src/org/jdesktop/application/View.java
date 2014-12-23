/*
 * Decompiled with CFR 0_92.
 */
package org.jdesktop.application;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JToolBar;
import org.jdesktop.application.AbstractBean;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.ResourceMap;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class View extends AbstractBean {
	private static final Logger logger = Logger.getLogger(View.class.getName());
	private final Application application;
	private ResourceMap resourceMap = null;
	private JRootPane rootPane = null;
	private JComponent component = null;
	private JMenuBar menuBar = null;
	private List<JToolBar> toolBars = Collections.emptyList();
	private JComponent toolBarsPanel = null;
	private JComponent statusBar = null;

	public View(Application application) {
		if (application == null) {
			throw new IllegalArgumentException("null application");
		}
		this.application = application;
	}

	public final Application getApplication() {
		return this.application;
	}

	public final ApplicationContext getContext() {
		return this.getApplication().getContext();
	}

	public ResourceMap getResourceMap() {
		if (this.resourceMap == null) {
			this.resourceMap = this.getContext().getResourceMap(
					this.getClass(), View.class);
		}
		return this.resourceMap;
	}

	public JRootPane getRootPane() {
		if (this.rootPane == null) {
			this.rootPane = new JRootPane();
			this.rootPane.setOpaque(true);
		}
		return this.rootPane;
	}

	private void replaceContentPaneChild(JComponent jComponent,
			JComponent jComponent2, String string) {
		Container container = this.getRootPane().getContentPane();
		if (jComponent != null) {
			container.remove(jComponent);
		}
		if (jComponent2 != null) {
			container.add((Component) jComponent2, string);
		}
	}

	public JComponent getComponent() {
		return this.component;
	}

	public void setComponent(JComponent jComponent) {
		JComponent jComponent2 = this.component;
		this.component = jComponent;
		this.replaceContentPaneChild(jComponent2, this.component, "Center");
		this.firePropertyChange("component", jComponent2, this.component);
	}

	public JMenuBar getMenuBar() {
		return this.menuBar;
	}

	public void setMenuBar(JMenuBar jMenuBar) {
		JMenuBar jMenuBar2 = this.getMenuBar();
		this.menuBar = jMenuBar;
		this.getRootPane().setJMenuBar(jMenuBar);
		this.firePropertyChange("menuBar", jMenuBar2, jMenuBar);
	}

	public List<JToolBar> getToolBars() {
		return this.toolBars;
	}

	public void setToolBars(List<JToolBar> list) {
		if (list == null) {
			throw new IllegalArgumentException("null toolbars");
		}
		List<JToolBar> list2 = this.getToolBars();
		this.toolBars = Collections.unmodifiableList(new ArrayList<JToolBar>(
				list));
		JComponent jComponent = this.toolBarsPanel;
		JComponent jComponent2 = null;
		if (this.toolBars.size() == 1) {
			jComponent2 = list.get(0);
		} else if (this.toolBars.size() > 1) {
			jComponent2 = new JPanel();
			for (JToolBar jToolBar : this.toolBars) {
				jComponent2.add(jToolBar);
			}
		}
		this.replaceContentPaneChild(jComponent, jComponent2, "North");
		this.firePropertyChange("toolBars", list2, this.toolBars);
	}

	public final JToolBar getToolBar() {
		List<JToolBar> list = this.getToolBars();
		return list.size() == 0 ? null : list.get(0);
	}

	public final void setToolBar(JToolBar jToolBar) {
		JToolBar jToolBar2 = this.getToolBar();
		List list = Collections.emptyList();
		if (jToolBar != null) {
			list = Collections.singletonList(jToolBar);
		}
		this.setToolBars(list);
		this.firePropertyChange("toolBar", jToolBar2, jToolBar);
	}

	public JComponent getStatusBar() {
		return this.statusBar;
	}

	public void setStatusBar(JComponent jComponent) {
		JComponent jComponent2 = this.statusBar;
		this.statusBar = jComponent;
		this.replaceContentPaneChild(jComponent2, this.statusBar, "South");
		this.firePropertyChange("statusBar", jComponent2, this.statusBar);
	}
}
