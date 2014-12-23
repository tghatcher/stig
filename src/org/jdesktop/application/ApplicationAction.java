/*
 * Decompiled with CFR 0_92.
 */
package org.jdesktop.application;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.KeyStroke;
import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationActionMap;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.DefaultInputBlocker;
import org.jdesktop.application.MnemonicText;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.Task;
import org.jdesktop.application.TaskService;

public class ApplicationAction extends AbstractAction {
	private static final Logger logger = Logger
			.getLogger(ApplicationAction.class.getName());
	private final ApplicationActionMap appAM;
	private final ResourceMap resourceMap;
	private final String actionName;
	private final Method actionMethod;
	private final String enabledProperty;
	private final Method isEnabledMethod;
	private final Method setEnabledMethod;
	private final String selectedProperty;
	private final Method isSelectedMethod;
	private final Method setSelectedMethod;
	private final Task.BlockingScope block;
	private Action proxy = null;
	private Object proxySource = null;
	private PropertyChangeListener proxyPCL = null;
	private static final String SELECTED_KEY = "SwingSelectedKey";
	private static final String DISPLAYED_MNEMONIC_INDEX_KEY = "SwingDisplayedMnemonicIndexKey";
	private static final String LARGE_ICON_KEY = "SwingLargeIconKey";

	public ApplicationAction(ApplicationActionMap applicationActionMap,
			ResourceMap resourceMap, String string, Method method,
			String string2, String string3, Task.BlockingScope blockingScope) {
		if (applicationActionMap == null) {
			throw new IllegalArgumentException("null appAM");
		}
		if (string == null) {
			throw new IllegalArgumentException("null baseName");
		}
		this.appAM = applicationActionMap;
		this.resourceMap = resourceMap;
		this.actionName = string;
		this.actionMethod = method;
		this.enabledProperty = string2;
		this.selectedProperty = string3;
		this.block = blockingScope;
		if (string2 != null) {
			this.setEnabledMethod = this.propertySetMethod(string2,
					Boolean.TYPE);
			this.isEnabledMethod = this.propertyGetMethod(string2);
			if (this.isEnabledMethod == null) {
				throw this.newNoSuchPropertyException(string2);
			}
		} else {
			this.isEnabledMethod = null;
			this.setEnabledMethod = null;
		}
		if (string3 != null) {
			this.setSelectedMethod = this.propertySetMethod(string3,
					Boolean.TYPE);
			this.isSelectedMethod = this.propertyGetMethod(string3);
			if (this.isSelectedMethod == null) {
				throw this.newNoSuchPropertyException(string3);
			}
			super.putValue("SwingSelectedKey", Boolean.FALSE);
		} else {
			this.isSelectedMethod = null;
			this.setSelectedMethod = null;
		}
		if (resourceMap != null) {
			this.initActionProperties(resourceMap, string);
		}
	}

	ApplicationAction(ApplicationActionMap applicationActionMap,
			ResourceMap resourceMap, String string) {
		this(applicationActionMap, resourceMap, string, null, null, null,
				Task.BlockingScope.NONE);
	}

	private IllegalArgumentException newNoSuchPropertyException(String string) {
		String string2 = this.appAM.getActionsClass().getName();
		String string3 = String.format("no property named %s in %s", string,
				string2);
		return new IllegalArgumentException(string3);
	}

	String getEnabledProperty() {
		return this.enabledProperty;
	}

	String getSelectedProperty() {
		return this.selectedProperty;
	}

	public Action getProxy() {
		return this.proxy;
	}

	public void setProxy(Action action) {
		Action action2 = this.proxy;
		this.proxy = action;
		if (action2 != null) {
			action2.removePropertyChangeListener(this.proxyPCL);
			this.proxyPCL = null;
		}
		if (this.proxy != null) {
			this.updateProxyProperties();
			this.proxyPCL = new ProxyPCL();
			action.addPropertyChangeListener(this.proxyPCL);
		} else if (action2 != null) {
			this.setEnabled(false);
			this.setSelected(false);
		}
		this.firePropertyChange("proxy", action2, this.proxy);
	}

	public Object getProxySource() {
		return this.proxySource;
	}

	public void setProxySource(Object object) {
		Object object2 = this.proxySource;
		this.proxySource = object;
		this.firePropertyChange("proxySource", object2, this.proxySource);
	}

	private void maybePutDescriptionValue(String string, Action action) {
		Object object = action.getValue(string);
		if (object instanceof String) {
			this.putValue(string, (String) object);
		}
	}

	private void updateProxyProperties() {
		Action action = this.getProxy();
		if (action != null) {
			this.setEnabled(action.isEnabled());
			Object object = action.getValue("SwingSelectedKey");
			this.setSelected(object instanceof Boolean
					&& (Boolean) object != false);
			this.maybePutDescriptionValue("ShortDescription", action);
			this.maybePutDescriptionValue("LongDescription", action);
		}
	}

	private void initActionProperties(ResourceMap resourceMap, String string) {
		KeyStroke keyStroke;
		Integer n;
		Icon icon;
		Integer n2;
		Icon icon2;
		Icon icon3;
		boolean bl = false;
		Object var4_4 = null;
		String string2 = resourceMap.getString(string + ".Action.text",
				new Object[0]);
		if (string2 != null) {
			MnemonicText.configure(this, string2);
			bl = true;
		}
		if ((n = resourceMap.getKeyCode(string + ".Action.mnemonic")) != null) {
			this.putValue("MnemonicKey", n);
		}
		if ((n2 = resourceMap.getInteger(string
				+ ".Action.displayedMnemonicIndex")) != null) {
			this.putValue("SwingDisplayedMnemonicIndexKey", n2);
		}
		if ((keyStroke = resourceMap.getKeyStroke(string
				+ ".Action.accelerator")) != null) {
			this.putValue("AcceleratorKey", keyStroke);
		}
		if ((icon3 = resourceMap.getIcon(string + ".Action.icon")) != null) {
			this.putValue("SmallIcon", icon3);
			this.putValue("SwingLargeIconKey", icon3);
			bl = true;
		}
		if ((icon = resourceMap.getIcon(string + ".Action.smallIcon")) != null) {
			this.putValue("SmallIcon", icon);
			bl = true;
		}
		if ((icon2 = resourceMap.getIcon(string + ".Action.largeIcon")) != null) {
			this.putValue("SwingLargeIconKey", icon2);
			bl = true;
		}
		this.putValue("ShortDescription", resourceMap.getString(string
				+ ".Action.shortDescription", new Object[0]));
		this.putValue("LongDescription", resourceMap.getString(string
				+ ".Action.longDescription", new Object[0]));
		this.putValue("ActionCommandKey", resourceMap.getString(string
				+ ".Action.command", new Object[0]));
		if (!bl) {
			this.putValue("Name", this.actionName);
		}
	}

	private String propertyMethodName(String string, String string2) {
		return string + string2.substring(0, 1).toUpperCase()
				+ string2.substring(1);
	}

	private Method propertyGetMethod(String string) {
		String[] arrstring = new String[] {
				this.propertyMethodName("is", string),
				this.propertyMethodName("get", string) };
		Class class_ = this.appAM.getActionsClass();
		for (String string2 : arrstring) {
			try {
				return class_.getMethod(string2, new Class[0]);
			} catch (NoSuchMethodException var8_8) {
				continue;
			}
		}
		return null;
	}

	private Method propertySetMethod(String string, Class class_) {
		Class class_2 = this.appAM.getActionsClass();
		try {
			return class_2.getMethod(this.propertyMethodName("set", string),
					class_);
		} catch (NoSuchMethodException var4_4) {
			return null;
		}
	}

	public String getName() {
		return this.actionName;
	}

	public ResourceMap getResourceMap() {
		return this.resourceMap;
	}

	protected Object getActionArgument(Class class_, String string,
			ActionEvent actionEvent) {
		Object object = null;
		if (class_ == ActionEvent.class) {
			object = actionEvent;
		} else if (class_ == Action.class) {
			object = this;
		} else if (class_ == ActionMap.class) {
			object = this.appAM;
		} else if (class_ == ResourceMap.class) {
			object = this.resourceMap;
		} else if (class_ == ApplicationContext.class) {
			object = this.appAM.getContext();
		} else if (class_ == Application.class) {
			object = this.appAM.getContext().getApplication();
		} else {
			IllegalArgumentException illegalArgumentException = new IllegalArgumentException(
					"unrecognized @Action method parameter");
			this.actionFailed(actionEvent, illegalArgumentException);
		}
		return object;
	}

	private Task.InputBlocker createInputBlocker(Task task,
			ActionEvent actionEvent) {
		Object object = actionEvent.getSource();
		if (this.block == Task.BlockingScope.ACTION) {
			object = this;
		}
		return new DefaultInputBlocker(task, this.block, object, this);
	}

	private void noProxyActionPerformed(ActionEvent actionEvent) {
		Object object;
		Object object2;
		Object object3 = null;
		Annotation[][] arrannotation = this.actionMethod
				.getParameterAnnotations();
		Class<?>[] arrclass = this.actionMethod.getParameterTypes();
		Object[] arrobject = new Object[arrclass.length];
		for (int i = 0; i < arrclass.length; ++i) {
			object = null;
			for (Annotation annotation : arrannotation[i]) {
				if (!(annotation instanceof Action.Parameter))
					continue;
				object = ((Action.Parameter) annotation).value();
				break;
			}
			arrobject[i] = this.getActionArgument(arrclass[i], (String) object,
					actionEvent);
		}
		try {
			object2 = this.appAM.getActionsObject();
			object3 = this.actionMethod.invoke(object2, arrobject);
		} catch (Exception var6_8) {
			this.actionFailed(actionEvent, var6_8);
		}
		if (object3 instanceof Task) {
			object2 = (Task) object3;
			if (object2.getInputBlocker() == null) {
				object2.setInputBlocker(this.createInputBlocker((Task) object2,
						actionEvent));
			}
			object = this.appAM.getContext();
			object.getTaskService().execute((Task) object2);
		}
	}

	public void actionPerformed(ActionEvent actionEvent) {
		Action action = this.getProxy();
		if (action != null) {
			actionEvent.setSource(this.getProxySource());
			action.actionPerformed(actionEvent);
		} else if (this.actionMethod != null) {
			this.noProxyActionPerformed(actionEvent);
		}
	}

	public boolean isEnabled() {
		if (this.getProxy() != null || this.isEnabledMethod == null) {
			return super.isEnabled();
		}
		try {
			Object object = this.isEnabledMethod.invoke(
					this.appAM.getActionsObject(), new Object[0]);
			return (Boolean) object;
		} catch (Exception var1_2) {
			throw this.newInvokeError(this.isEnabledMethod, var1_2,
					new Object[0]);
		}
	}

	public void setEnabled(boolean bl) {
		if (this.getProxy() != null || this.setEnabledMethod == null) {
			super.setEnabled(bl);
		} else {
			try {
				this.setEnabledMethod.invoke(this.appAM.getActionsObject(), bl);
			} catch (Exception var2_2) {
				throw this.newInvokeError(this.setEnabledMethod, var2_2, bl);
			}
		}
	}

	public boolean isSelected() {
		if (this.getProxy() != null || this.isSelectedMethod == null) {
			Object object = this.getValue("SwingSelectedKey");
			return object instanceof Boolean ? (Boolean) object : false;
		}
		try {
			Object object = this.isSelectedMethod.invoke(
					this.appAM.getActionsObject(), new Object[0]);
			return (Boolean) object;
		} catch (Exception var1_3) {
			throw this.newInvokeError(this.isSelectedMethod, var1_3,
					new Object[0]);
		}
	}

	public void setSelected(boolean bl) {
		if (this.getProxy() != null || this.setSelectedMethod == null) {
			super.putValue("SwingSelectedKey", bl);
		} else {
			try {
				super.putValue("SwingSelectedKey", bl);
				if (bl != this.isSelected()) {
					this.setSelectedMethod.invoke(
							this.appAM.getActionsObject(), bl);
				}
			} catch (Exception var2_2) {
				throw this.newInvokeError(this.setSelectedMethod, var2_2, bl);
			}
		}
	}

	public void putValue(String string, Object object) {
		if ("SwingSelectedKey".equals(string) && object instanceof Boolean) {
			this.setSelected((Boolean) object);
		} else {
			super.putValue(string, object);
		}
	}

	private/* varargs */Error newInvokeError(Method method,
			Exception exception, Object... arrobject) {
		String string = arrobject.length == 0 ? "" : arrobject[0].toString();
		for (int i = 1; i < arrobject.length; ++i) {
			string = string + ", " + arrobject[i];
		}
		String string2 = this.appAM.getActionsObject().getClass().getName();
		String string3 = String.format("%s.%s(%s) failed", string2, method,
				string);
		return new Error(string3, exception);
	}

	void forwardPropertyChangeEvent(PropertyChangeEvent propertyChangeEvent,
			String string) {
		if ("selected".equals(string)
				&& propertyChangeEvent.getNewValue() instanceof Boolean) {
			this.putValue("SwingSelectedKey",
					(Boolean) propertyChangeEvent.getNewValue());
		}
		this.firePropertyChange(string, propertyChangeEvent.getOldValue(),
				propertyChangeEvent.getNewValue());
	}

	private void actionFailed(ActionEvent actionEvent, Exception exception) {
		throw new Error(exception);
	}

	public String toString() {
		Object object;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(this.getClass().getName());
		stringBuilder.append(" ");
		boolean bl = this.isEnabled();
		if (!bl) {
			stringBuilder.append("(");
		}
		stringBuilder.append(this.getName());
		Object object2 = this.getValue("SwingSelectedKey");
		if (object2 instanceof Boolean && ((Boolean) object2).booleanValue()) {
			stringBuilder.append("+");
		}
		if (!bl) {
			stringBuilder.append(")");
		}
		if ((object = this.getValue("Name")) instanceof String) {
			stringBuilder.append(" \"");
			stringBuilder.append((String) object);
			stringBuilder.append("\"");
		}
		this.proxy = this.getProxy();
		if (this.proxy != null) {
			stringBuilder.append(" Proxy for: ");
			stringBuilder.append(this.proxy.toString());
		}
		return stringBuilder.toString();
	}

	private class ProxyPCL implements PropertyChangeListener {
		private ProxyPCL() {
		}

		public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
			String string = propertyChangeEvent.getPropertyName();
			if (string == null || "enabled".equals(string)
					|| "selected".equals(string)
					|| "ShortDescription".equals(string)
					|| "LongDescription".equals(string)) {
				ApplicationAction.this.updateProxyProperties();
			}
		}
	}

}
