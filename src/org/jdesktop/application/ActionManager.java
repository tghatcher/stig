/*
 * Decompiled with CFR 0_92.
 */
package org.jdesktop.application;

import java.awt.KeyboardFocusManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JComponent;
import org.jdesktop.application.AbstractBean;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationAction;
import org.jdesktop.application.ApplicationActionMap;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.TextActions;

public class ActionManager extends AbstractBean {
	private static final Logger logger = Logger.getLogger(ActionManager.class
			.getName());
	private final ApplicationContext context;
	private final WeakHashMap<Object, WeakReference<ApplicationActionMap>> actionMaps;
	private ApplicationActionMap globalActionMap = null;

	protected ActionManager(ApplicationContext applicationContext) {
		if (applicationContext == null) {
			throw new IllegalArgumentException("null context");
		}
		this.context = applicationContext;
		this.actionMaps = new WeakHashMap();
	}

	protected final ApplicationContext getContext() {
		return this.context;
	}

	private ApplicationActionMap createActionMapChain(Class class_,
			Class class_2, Object object, ResourceMap resourceMap) {
		ArrayList<Class> arrayList = new ArrayList<Class>();
		Object object2 = class_;
		do {
			arrayList.add((Class) object2);
			if (object2.equals(class_2))
				break;
			object2 = object2.getSuperclass();
		} while (true);
		Collections.reverse(arrayList);
		object2 = this.getContext();
		ApplicationActionMap applicationActionMap = null;
		for (Class class_3 : arrayList) {
			ApplicationActionMap applicationActionMap2 = new ApplicationActionMap(
					(ApplicationContext) object2, class_3, object, resourceMap);
			applicationActionMap2.setParent(applicationActionMap);
			applicationActionMap = applicationActionMap2;
		}
		return applicationActionMap;
	}

	public ApplicationActionMap getActionMap() {
		if (this.globalActionMap == null) {
			ApplicationContext applicationContext = this.getContext();
			Application application = applicationContext.getApplication();
			Class class_ = applicationContext.getApplicationClass();
			ResourceMap resourceMap = applicationContext.getResourceMap();
			this.globalActionMap = this.createActionMapChain(class_,
					Application.class, application, resourceMap);
			this.initProxyActionSupport();
		}
		return this.globalActionMap;
	}

	private void initProxyActionSupport() {
		KeyboardFocusManager keyboardFocusManager = KeyboardFocusManager
				.getCurrentKeyboardFocusManager();
		keyboardFocusManager.addPropertyChangeListener(new KeyboardFocusPCL());
	}

	public ApplicationActionMap getActionMap(Class class_, Object object) {
		if (class_ == null) {
			throw new IllegalArgumentException("null actionsClass");
		}
		if (object == null) {
			throw new IllegalArgumentException("null actionsObject");
		}
		if (!class_.isAssignableFrom(object.getClass())) {
			throw new IllegalArgumentException(
					"actionsObject not instanceof actionsClass");
		}
		WeakHashMap<Object, WeakReference<ApplicationActionMap>> weakHashMap = this.actionMaps;
		synchronized (weakHashMap) {
			ApplicationActionMap applicationActionMap;
			WeakReference<ApplicationActionMap> weakReference = this.actionMaps
					.get(object);
			ApplicationActionMap applicationActionMap2 = applicationActionMap = weakReference != null ? weakReference
					.get() : null;
			if (applicationActionMap == null
					|| applicationActionMap.getActionsClass() != class_) {
				ApplicationContext applicationContext = this.getContext();
				Class class_2 = object.getClass();
				ResourceMap resourceMap = applicationContext.getResourceMap(
						class_2, class_);
				ActionMap actionMap = applicationActionMap = this
						.createActionMapChain(class_2, class_, object,
								resourceMap);
				while (actionMap.getParent() != null) {
					actionMap = actionMap.getParent();
				}
				actionMap.setParent(this.getActionMap());
				this.actionMaps.put(object,
						new WeakReference<ApplicationActionMap>(
								applicationActionMap));
			}
			return applicationActionMap;
		}
	}

	private void updateAllProxyActions(JComponent jComponent,
			JComponent jComponent2) {
		ActionMap actionMap;
		if (jComponent2 != null
				&& (actionMap = jComponent2.getActionMap()) != null) {
			this.updateProxyActions(this.getActionMap(), actionMap, jComponent2);
			for (WeakReference<ApplicationActionMap> weakReference : this.actionMaps
					.values()) {
				ApplicationActionMap applicationActionMap = weakReference.get();
				if (applicationActionMap == null)
					continue;
				this.updateProxyActions(applicationActionMap, actionMap,
						jComponent2);
			}
		}
	}

	private void updateProxyActions(ApplicationActionMap applicationActionMap,
			ActionMap actionMap, JComponent jComponent) {
		for (ApplicationAction applicationAction : applicationActionMap
				.getProxyActions()) {
			String string = applicationAction.getName();
			Action action = actionMap.get(string);
			if (action != null) {
				applicationAction.setProxy(action);
				applicationAction.setProxySource(jComponent);
				continue;
			}
			applicationAction.setProxy(null);
			applicationAction.setProxySource(null);
		}
	}

	private final class KeyboardFocusPCL implements PropertyChangeListener {
		private final TextActions textActions;

		KeyboardFocusPCL() {
			this.textActions = new TextActions(ActionManager.this.getContext());
		}

		public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
			if (propertyChangeEvent.getPropertyName() == "permanentFocusOwner") {
				JComponent jComponent = ActionManager.this.getContext()
						.getFocusOwner();
				Object object = propertyChangeEvent.getNewValue();
				JComponent jComponent2 = object instanceof JComponent ? (JComponent) object
						: null;
				this.textActions.updateFocusOwner(jComponent, jComponent2);
				ActionManager.this.getContext().setFocusOwner(jComponent2);
				ActionManager.this.updateAllProxyActions(jComponent,
						jComponent2);
			}
		}
	}

}
