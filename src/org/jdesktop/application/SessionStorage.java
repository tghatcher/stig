/*
 * Decompiled with CFR 0_92.
 */
package org.jdesktop.application;

import java.applet.Applet;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Window;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.plaf.SplitPaneUI;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.LocalStorage;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SessionStorage {
	private static Logger logger = Logger.getLogger(SessionStorage.class
			.getName());
	private final Map<Class, Property> propertyMap;
	private final ApplicationContext context;

	protected SessionStorage(ApplicationContext applicationContext) {
		if (applicationContext == null) {
			throw new IllegalArgumentException("null context");
		}
		this.context = applicationContext;
		this.propertyMap = new HashMap<Class, Property>();
		this.propertyMap.put(Window.class, new WindowProperty());
		this.propertyMap.put(JTabbedPane.class, new TabbedPaneProperty());
		this.propertyMap.put(JSplitPane.class, new SplitPaneProperty());
		this.propertyMap.put(JTable.class, new TableProperty());
	}

	protected final ApplicationContext getContext() {
		return this.context;
	}

	private void checkSaveRestoreArgs(Component component, String string) {
		if (component == null) {
			throw new IllegalArgumentException("null root");
		}
		if (string == null) {
			throw new IllegalArgumentException("null fileName");
		}
	}

	private String getComponentName(Component component) {
		return component.getName();
	}

	private String getComponentPathname(Component component) {
		String string = this.getComponentName(component);
		if (string == null) {
			return null;
		}
		StringBuilder stringBuilder = new StringBuilder(string);
		while (!(component.getParent() == null || component instanceof Window || component instanceof Applet)) {
			string = this.getComponentName(component = component.getParent());
			if (string == null) {
				int n = component.getParent().getComponentZOrder(component);
				if (n >= 0) {
					Class class_ = component.getClass();
					string = class_.getSimpleName();
					if (string.length() == 0) {
						string = "Anonymous"
								+ class_.getSuperclass().getSimpleName();
					}
					string = string + n;
				} else {
					logger.warning("Couldn't compute pathname for " + component);
					return null;
				}
			}
			stringBuilder.append("/").append(string);
		}
		return stringBuilder.toString();
	}

	private void saveTree(List<Component> list, Map<String, Object> map) {
		ArrayList<Component> arrayList = new ArrayList<Component>();
		for (Component component : list) {
			String string;
			Component[] arrcomponent;
			Object object;
			if (component != null
					&& (arrcomponent = this.getProperty(component)) != null
					&& (string = this.getComponentPathname(component)) != null
					&& (object = arrcomponent.getSessionState(component)) != null) {
				map.put(string, object);
			}
			if (!(component instanceof Container)
					|| (arrcomponent = ((Container) component).getComponents()) == null
					|| arrcomponent.length <= 0)
				continue;
			Collections.addAll(arrayList, arrcomponent);
		}
		if (arrayList.size() > 0) {
			this.saveTree(arrayList, map);
		}
	}

	public void save(Component component, String string) throws IOException {
		this.checkSaveRestoreArgs(component, string);
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		this.saveTree(Collections.singletonList(component), hashMap);
		LocalStorage localStorage = this.getContext().getLocalStorage();
		localStorage.save(hashMap, string);
	}

	private void restoreTree(List<Component> list, Map<String, Object> map) {
		ArrayList<Component> arrayList = new ArrayList<Component>();
		for (Component component : list) {
			String string;
			Component[] arrcomponent;
			if (component != null
					&& (arrcomponent = this.getProperty(component)) != null
					&& (string = this.getComponentPathname(component)) != null) {
				Object object = map.get(string);
				if (object != null) {
					arrcomponent.setSessionState(component, object);
				} else {
					logger.warning("No saved state for " + component);
				}
			}
			if (!(component instanceof Container)
					|| (arrcomponent = ((Container) component).getComponents()) == null
					|| arrcomponent.length <= 0)
				continue;
			Collections.addAll(arrayList, arrcomponent);
		}
		if (arrayList.size() > 0) {
			this.restoreTree(arrayList, map);
		}
	}

	public void restore(Component component, String string) throws IOException {
		this.checkSaveRestoreArgs(component, string);
		LocalStorage localStorage = this.getContext().getLocalStorage();
		Map map = (Map) localStorage.load(string);
		if (map != null) {
			this.restoreTree(Collections.singletonList(component), map);
		}
	}

	private void checkClassArg(Class class_) {
		if (class_ == null) {
			throw new IllegalArgumentException("null class");
		}
	}

	public Property getProperty(Class class_) {
		this.checkClassArg(class_);
		for (; class_ != null; class_ = class_.getSuperclass()) {
			Property property = this.propertyMap.get(class_);
			if (property == null)
				continue;
			return property;
		}
		return null;
	}

	public void putProperty(Class class_, Property property) {
		this.checkClassArg(class_);
		this.propertyMap.put(class_, property);
	}

	public final Property getProperty(Component component) {
		if (component == null) {
			throw new IllegalArgumentException("null component");
		}
		if (component instanceof Property) {
			return (Property) component;
		}
		Property property = null;
		if (component instanceof JComponent) {
			Object object = ((JComponent) component)
					.getClientProperty(Property.class);
			property = object instanceof Property ? (Property) object : null;
		}
		return property != null ? property : this.getProperty(component
				.getClass());
	}

	public static interface Property {
		public Object getSessionState(Component var1);

		public void setSessionState(Component var1, Object var2);
	}

	public static class SplitPaneProperty implements Property {
		private void checkComponent(Component component) {
			if (component == null) {
				throw new IllegalArgumentException("null component");
			}
			if (!(component instanceof JSplitPane)) {
				throw new IllegalArgumentException("invalid component");
			}
		}

		public Object getSessionState(Component component) {
			this.checkComponent(component);
			JSplitPane jSplitPane = (JSplitPane) component;
			return new SplitPaneState(jSplitPane.getUI().getDividerLocation(
					jSplitPane), jSplitPane.getOrientation());
		}

		public void setSessionState(Component component, Object object) {
			this.checkComponent(component);
			if (!(object == null || object instanceof SplitPaneState)) {
				throw new IllegalArgumentException("invalid state");
			}
			JSplitPane jSplitPane = (JSplitPane) component;
			SplitPaneState splitPaneState = (SplitPaneState) object;
			if (jSplitPane.getOrientation() == splitPaneState.getOrientation()) {
				jSplitPane.setDividerLocation(splitPaneState
						.getDividerLocation());
			}
		}
	}

	public static class SplitPaneState {
		private int dividerLocation = -1;
		private int orientation = 1;

		private void checkOrientation(int n) {
			if (n != 1 && n != 0) {
				throw new IllegalArgumentException("invalid orientation");
			}
		}

		public SplitPaneState() {
		}

		public SplitPaneState(int n, int n2) {
			this.checkOrientation(n2);
			if (n < -1) {
				throw new IllegalArgumentException("invalid dividerLocation");
			}
			this.dividerLocation = n;
			this.orientation = n2;
		}

		public int getDividerLocation() {
			return this.dividerLocation;
		}

		public void setDividerLocation(int n) {
			if (n < -1) {
				throw new IllegalArgumentException("invalid dividerLocation");
			}
			this.dividerLocation = n;
		}

		public int getOrientation() {
			return this.orientation;
		}

		public void setOrientation(int n) {
			this.checkOrientation(n);
			this.orientation = n;
		}
	}

	public static class TabbedPaneProperty implements Property {
		private void checkComponent(Component component) {
			if (component == null) {
				throw new IllegalArgumentException("null component");
			}
			if (!(component instanceof JTabbedPane)) {
				throw new IllegalArgumentException("invalid component");
			}
		}

		public Object getSessionState(Component component) {
			this.checkComponent(component);
			JTabbedPane jTabbedPane = (JTabbedPane) component;
			return new TabbedPaneState(jTabbedPane.getSelectedIndex(),
					jTabbedPane.getTabCount());
		}

		public void setSessionState(Component component, Object object) {
			this.checkComponent(component);
			if (!(object == null || object instanceof TabbedPaneState)) {
				throw new IllegalArgumentException("invalid state");
			}
			JTabbedPane jTabbedPane = (JTabbedPane) component;
			TabbedPaneState tabbedPaneState = (TabbedPaneState) object;
			if (jTabbedPane.getTabCount() == tabbedPaneState.getTabCount()) {
				jTabbedPane
						.setSelectedIndex(tabbedPaneState.getSelectedIndex());
			}
		}
	}

	public static class TabbedPaneState {
		private int selectedIndex;
		private int tabCount;

		public TabbedPaneState() {
			this.selectedIndex = -1;
			this.tabCount = 0;
		}

		public TabbedPaneState(int n, int n2) {
			if (n2 < 0) {
				throw new IllegalArgumentException("invalid tabCount");
			}
			if (n < -1 || n > n2) {
				throw new IllegalArgumentException("invalid selectedIndex");
			}
			this.selectedIndex = n;
			this.tabCount = n2;
		}

		public int getSelectedIndex() {
			return this.selectedIndex;
		}

		public void setSelectedIndex(int n) {
			if (n < -1) {
				throw new IllegalArgumentException("invalid selectedIndex");
			}
			this.selectedIndex = n;
		}

		public int getTabCount() {
			return this.tabCount;
		}

		public void setTabCount(int n) {
			if (n < 0) {
				throw new IllegalArgumentException("invalid tabCount");
			}
			this.tabCount = n;
		}
	}

	public static class TableProperty implements Property {
		private void checkComponent(Component component) {
			if (component == null) {
				throw new IllegalArgumentException("null component");
			}
			if (!(component instanceof JTable)) {
				throw new IllegalArgumentException("invalid component");
			}
		}

		public Object getSessionState(Component component) {
			this.checkComponent(component);
			JTable jTable = (JTable) component;
			int[] arrn = new int[jTable.getColumnCount()];
			boolean bl = false;
			for (int i = 0; i < arrn.length; ++i) {
				TableColumn tableColumn = jTable.getColumnModel().getColumn(i);
				int n = arrn[i] = tableColumn.getResizable() ? tableColumn
						.getWidth() : -1;
				if (!tableColumn.getResizable())
					continue;
				bl = true;
			}
			return bl ? new TableState(arrn) : null;
		}

		public void setSessionState(Component component, Object object) {
			this.checkComponent(component);
			if (!(object instanceof TableState)) {
				throw new IllegalArgumentException("invalid state");
			}
			JTable jTable = (JTable) component;
			int[] arrn = ((TableState) object).getColumnWidths();
			if (jTable.getColumnCount() == arrn.length) {
				for (int i = 0; i < arrn.length; ++i) {
					TableColumn tableColumn;
					if (arrn[i] == -1
							|| !(tableColumn = jTable.getColumnModel()
									.getColumn(i)).getResizable())
						continue;
					tableColumn.setPreferredWidth(arrn[i]);
				}
			}
		}
	}

	public static class TableState {
		private int[] columnWidths = new int[0];

		private int[] copyColumnWidths(int[] arrn) {
			if (arrn == null) {
				throw new IllegalArgumentException("invalid columnWidths");
			}
			int[] arrn2 = new int[arrn.length];
			System.arraycopy(arrn, 0, arrn2, 0, arrn.length);
			return arrn2;
		}

		public TableState() {
		}

		public TableState(int[] arrn) {
			this.columnWidths = this.copyColumnWidths(arrn);
		}

		public int[] getColumnWidths() {
			return this.copyColumnWidths(this.columnWidths);
		}

		public void setColumnWidths(int[] arrn) {
			this.columnWidths = this.copyColumnWidths(arrn);
		}
	}

	public static class WindowProperty implements Property {
		private void checkComponent(Component component) {
			if (component == null) {
				throw new IllegalArgumentException("null component");
			}
			if (!(component instanceof Window)) {
				throw new IllegalArgumentException("invalid component");
			}
		}

		private int getScreenCount() {
			return GraphicsEnvironment.getLocalGraphicsEnvironment()
					.getScreenDevices().length;
		}

		public Object getSessionState(Component component) {
			GraphicsConfiguration graphicsConfiguration;
			this.checkComponent(component);
			int n = 0;
			if (component instanceof Frame) {
				n = ((Frame) component).getExtendedState();
			}
			Rectangle rectangle = (graphicsConfiguration = component
					.getGraphicsConfiguration()) == null ? null
					: graphicsConfiguration.getBounds();
			Rectangle rectangle2 = component.getBounds();
			if (component instanceof JFrame && 0 != (n & 6)) {
				String string = "WindowState.normalBounds";
				Object object = ((JFrame) component).getRootPane()
						.getClientProperty(string);
				if (object instanceof Rectangle) {
					rectangle2 = (Rectangle) object;
				}
			}
			return new WindowState(rectangle2, rectangle,
					this.getScreenCount(), n);
		}

		public void setSessionState(Component component, Object object) {
			this.checkComponent(component);
			if (!(object == null || object instanceof WindowState)) {
				throw new IllegalArgumentException("invalid state");
			}
			Window window = (Window) component;
			if (!(window.isLocationByPlatform() || object == null)) {
				WindowState windowState = (WindowState) object;
				Rectangle rectangle = windowState
						.getGraphicsConfigurationBounds();
				int n = windowState.getScreenCount();
				GraphicsConfiguration graphicsConfiguration = component
						.getGraphicsConfiguration();
				Rectangle rectangle2 = graphicsConfiguration == null ? null
						: graphicsConfiguration.getBounds();
				int n2 = this.getScreenCount();
				if (rectangle != null && rectangle.equals(rectangle2)
						&& n == n2) {
					boolean bl = true;
					if (window instanceof Frame) {
						bl = ((Frame) window).isResizable();
					} else if (window instanceof Dialog) {
						bl = ((Dialog) window).isResizable();
					}
					if (bl) {
						window.setBounds(windowState.getBounds());
					}
				}
				if (window instanceof Frame) {
					((Frame) window).setExtendedState(windowState
							.getFrameState());
				}
			}
		}
	}

	public static class WindowState {
		private final Rectangle bounds;
		private Rectangle gcBounds = null;
		private int screenCount;
		private int frameState = 0;

		public WindowState() {
			this.bounds = new Rectangle();
		}

		public WindowState(Rectangle rectangle, Rectangle rectangle2, int n,
				int n2) {
			if (rectangle == null) {
				throw new IllegalArgumentException("null bounds");
			}
			if (n < 1) {
				throw new IllegalArgumentException("invalid screenCount");
			}
			this.bounds = rectangle;
			this.gcBounds = rectangle2;
			this.screenCount = n;
			this.frameState = n2;
		}

		public Rectangle getBounds() {
			return new Rectangle(this.bounds);
		}

		public void setBounds(Rectangle rectangle) {
			this.bounds.setBounds(rectangle);
		}

		public int getScreenCount() {
			return this.screenCount;
		}

		public void setScreenCount(int n) {
			this.screenCount = n;
		}

		public int getFrameState() {
			return this.frameState;
		}

		public void setFrameState(int n) {
			this.frameState = n;
		}

		public Rectangle getGraphicsConfigurationBounds() {
			return this.gcBounds == null ? null : new Rectangle(this.gcBounds);
		}

		public void setGraphicsConfigurationBounds(Rectangle rectangle) {
			this.gcBounds = rectangle == null ? null : new Rectangle(rectangle);
		}
	}

}
