/*
 * Decompiled with CFR 0_92.
 */
package org.jdesktop.application;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.SwingUtilities;

public class AbstractBean {
	private final PropertyChangeSupport pcs;

	public AbstractBean() {
		this.pcs = new EDTPropertyChangeSupport(this);
	}

	public void addPropertyChangeListener(
			PropertyChangeListener propertyChangeListener) {
		this.pcs.addPropertyChangeListener(propertyChangeListener);
	}

	public void removePropertyChangeListener(
			PropertyChangeListener propertyChangeListener) {
		this.pcs.removePropertyChangeListener(propertyChangeListener);
	}

	public void addPropertyChangeListener(String string,
			PropertyChangeListener propertyChangeListener) {
		this.pcs.addPropertyChangeListener(string, propertyChangeListener);
	}

	public synchronized void removePropertyChangeListener(String string,
			PropertyChangeListener propertyChangeListener) {
		this.pcs.removePropertyChangeListener(string, propertyChangeListener);
	}

	public PropertyChangeListener[] getPropertyChangeListeners() {
		return this.pcs.getPropertyChangeListeners();
	}

	protected void firePropertyChange(String string, Object object,
			Object object2) {
		if (object != null && object2 != null && object.equals(object2)) {
			return;
		}
		this.pcs.firePropertyChange(string, object, object2);
	}

	protected void firePropertyChange(PropertyChangeEvent propertyChangeEvent) {
		this.pcs.firePropertyChange(propertyChangeEvent);
	}

	private static class EDTPropertyChangeSupport extends PropertyChangeSupport {
		EDTPropertyChangeSupport(Object object) {
			super(object);
		}

		public void firePropertyChange(
				final PropertyChangeEvent propertyChangeEvent) {
			if (SwingUtilities.isEventDispatchThread()) {
				super.firePropertyChange(propertyChangeEvent);
			} else {
				Runnable runnable = new Runnable() {

					public void run() {
						EDTPropertyChangeSupport.this
								.firePropertyChange(propertyChangeEvent);
					}
				};
				SwingUtilities.invokeLater(runnable);
			}
		}

	}

}
