/*
 * Decompiled with CFR 0_92.
 */
package org.jdesktop.application;

import java.util.EventObject;
import org.jdesktop.application.Task;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class TaskEvent<T> extends EventObject {
	private final T value;

	public final T getValue() {
		return this.value;
	}

	public TaskEvent(Task task, T t) {
		super(task);
		this.value = t;
	}
}
