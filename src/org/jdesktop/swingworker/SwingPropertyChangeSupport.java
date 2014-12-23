/*
 * Decompiled with CFR 0_92.
 */
package org.jdesktop.swingworker;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import javax.swing.SwingUtilities;

public final class SwingPropertyChangeSupport extends PropertyChangeSupport {
	static final long serialVersionUID = 7162625831330845068L;
	private final boolean notifyOnEDT;

	public SwingPropertyChangeSupport(Object object) {
		this(object, false);
	}

	public SwingPropertyChangeSupport(Object object, boolean bl) {
		super(object);
		this.notifyOnEDT = bl;
	}

	public void firePropertyChange(final PropertyChangeEvent propertyChangeEvent) {
		if (propertyChangeEvent == null) {
			throw new NullPointerException();
		}
		if (!this.isNotifyOnEDT() || SwingUtilities.isEventDispatchThread()) {
			super.firePropertyChange(propertyChangeEvent);
		} else {
			SwingUtilities.invokeLater(new Runnable() {

				public void run() {
					SwingPropertyChangeSupport.this
							.firePropertyChange(propertyChangeEvent);
				}
			});
		}
	}

	public final boolean isNotifyOnEDT() {
		return this.notifyOnEDT;
	}

}
