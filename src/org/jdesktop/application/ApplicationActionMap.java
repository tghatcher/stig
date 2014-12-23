/*
 * Decompiled with CFR 0_92.
 */
package org.jdesktop.application;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.swing.Action;
import javax.swing.ActionMap;
import org.jdesktop.application.Action;
import org.jdesktop.application.ApplicationAction;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.ProxyActions;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.Task;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ApplicationActionMap extends ActionMap {
	private final ApplicationContext context;
	private final ResourceMap resourceMap;
	private final Class actionsClass;
	private final Object actionsObject;
	private final List<ApplicationAction> proxyActions;

	public ApplicationActionMap(ApplicationContext applicationContext,
			Class class_, Object object, ResourceMap resourceMap) {
		if (applicationContext == null) {
			throw new IllegalArgumentException("null context");
		}
		if (class_ == null) {
			throw new IllegalArgumentException("null actionsClass");
		}
		if (object == null) {
			throw new IllegalArgumentException("null actionsObject");
		}
		if (!class_.isInstance(object)) {
			throw new IllegalArgumentException(
					"actionsObject not an instanceof actionsClass");
		}
		this.context = applicationContext;
		this.actionsClass = class_;
		this.actionsObject = object;
		this.resourceMap = resourceMap;
		this.proxyActions = new ArrayList<ApplicationAction>();
		this.addAnnotationActions(resourceMap);
		this.maybeAddActionsPCL();
	}

	public final ApplicationContext getContext() {
		return this.context;
	}

	public final Class getActionsClass() {
		return this.actionsClass;
	}

	public final Object getActionsObject() {
		return this.actionsObject;
	}

	public List<ApplicationAction> getProxyActions() {
		ArrayList<ApplicationAction> arrayList = new ArrayList<ApplicationAction>(
				this.proxyActions);
		for (ActionMap actionMap = this.getParent(); actionMap != null; actionMap = actionMap
				.getParent()) {
			if (!(actionMap instanceof ApplicationActionMap))
				continue;
			arrayList.addAll(((ApplicationActionMap) actionMap).proxyActions);
		}
		return Collections.unmodifiableList(arrayList);
	}

	private String aString(String string, String string2) {
		return string.length() == 0 ? string2 : string;
	}

	private void putAction(String string, ApplicationAction applicationAction) {
		if (this.get(string) != null) {
			// empty if block
		}
		this.put(string, applicationAction);
	}

	private void addAnnotationActions(ResourceMap resourceMap) {
		Object object;
		Class class_ = this.getActionsClass();
		for (Method method : class_.getDeclaredMethods()) {
			org.jdesktop.application.Action object2 = (org.jdesktop.application.Action) method
					.getAnnotation(org.jdesktop.application.Action.class);
			if (object2 == null)
				continue;
			object = method.getName();
			String string = this.aString(object2.enabledProperty(), null);
			String string2 = this.aString(object2.selectedProperty(), null);
			String string3 = this.aString(object2.name(), (String) object);
			Task.BlockingScope blockingScope = object2.block();
			ApplicationAction applicationAction = new ApplicationAction(this,
					resourceMap, string3, method, string, string2,
					blockingScope);
			this.putAction(string3, applicationAction);
		}
		Object object3 = (ProxyActions) class_
				.getAnnotation(ProxyActions.class);
		if (object3 != null) {
			for (String string : object3.value()) {
				object = new ApplicationAction(this, resourceMap, string);
				object.setEnabled(false);
				this.putAction(string, (ApplicationAction) object);
				this.proxyActions.add((ApplicationAction) object);
			}
		}
	}

	private void maybeAddActionsPCL() {
		boolean bl = false;
		Object[] arrobject = this.keys();
		if (arrobject != null) {
			for (Object object : arrobject) {
				ApplicationAction applicationAction;
				Action action = this.get(object);
				if (!(action instanceof ApplicationAction)
						|| (applicationAction = (ApplicationAction) action)
								.getEnabledProperty() == null
						&& applicationAction.getSelectedProperty() == null)
					continue;
				bl = true;
				break;
			}
			if (bl) {
				try {
					Object object2 = this.getActionsClass();
					Method method = object2.getMethod(
							"addPropertyChangeListener",
							PropertyChangeListener.class);
					method.invoke(this.getActionsObject(), new ActionsPCL());
				} catch (Exception var3_4) {
					String string = "addPropertyChangeListener undefined "
							+ this.actionsClass;
					throw new Error(string, var3_4);
				}
			}
		}
	}

	private class ActionsPCL implements PropertyChangeListener {
		private ActionsPCL() {
		}

		public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
			String string = propertyChangeEvent.getPropertyName();
			Object[] arrobject = ApplicationActionMap.this.keys();
			if (arrobject != null) {
				for (Object object : arrobject) {
					Action action = ApplicationActionMap.this.get(object);
					if (!(action instanceof ApplicationAction))
						continue;
					ApplicationAction applicationAction = (ApplicationAction) action;
					if (string.equals(applicationAction.getEnabledProperty())) {
						applicationAction.forwardPropertyChangeEvent(
								propertyChangeEvent, "enabled");
						continue;
					}
					if (!string.equals(applicationAction.getSelectedProperty()))
						continue;
					applicationAction.forwardPropertyChangeEvent(
							propertyChangeEvent, "selected");
				}
			}
		}
	}

}
