/*
 * Decompiled with CFR 0_92.
 */
package org.jdesktop.application;

import java.util.List;
import org.jdesktop.application.TaskEvent;

public interface TaskListener<T, V> {
	public void doInBackground(TaskEvent<Void> var1);

	public void process(TaskEvent<List<V>> var1);

	public void succeeded(TaskEvent<T> var1);

	public void failed(TaskEvent<Throwable> var1);

	public void cancelled(TaskEvent<Void> var1);

	public void interrupted(TaskEvent<InterruptedException> var1);

	public void finished(TaskEvent<Void> var1);

	/*
	 * This class specifies class file version 49.0 but uses Java 6 signatures.
	 * Assumed Java 6.
	 */
	public static class Adapter<T, V> implements TaskListener<T, V> {
		@Override
		public void doInBackground(TaskEvent<Void> taskEvent) {
		}

		@Override
		public void process(TaskEvent<List<V>> taskEvent) {
		}

		@Override
		public void succeeded(TaskEvent<T> taskEvent) {
		}

		@Override
		public void failed(TaskEvent<Throwable> taskEvent) {
		}

		@Override
		public void cancelled(TaskEvent<Void> taskEvent) {
		}

		@Override
		public void interrupted(TaskEvent<InterruptedException> taskEvent) {
		}

		@Override
		public void finished(TaskEvent<Void> taskEvent) {
		}
	}

}
