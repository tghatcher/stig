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
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.swing.SwingUtilities;
import org.jdesktop.application.AbstractBean;
import org.jdesktop.application.Task;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class TaskService extends AbstractBean {
	private final String name;
	private final ExecutorService executorService;
	private final List<Task> tasks;
	private final PropertyChangeListener taskPCL;

	public TaskService(String string, ExecutorService executorService) {
		if (string == null) {
			throw new IllegalArgumentException("null name");
		}
		if (executorService == null) {
			throw new IllegalArgumentException("null executorService");
		}
		this.name = string;
		this.executorService = executorService;
		this.tasks = new ArrayList<Task>();
		this.taskPCL = new TaskPCL();
	}

	public TaskService(String string) {
		this(string, new ThreadPoolExecutor(3, 10, 1, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>()));
	}

	public final String getName() {
		return this.name;
	}

	private List<Task> copyTasksList() {
		List<Task> list = this.tasks;
		synchronized (list) {
			if (this.tasks.isEmpty()) {
				return Collections.emptyList();
			}
			return new ArrayList<Task>(this.tasks);
		}
	}

	private void maybeBlockTask(Task task) {
		final Task.InputBlocker inputBlocker = task.getInputBlocker();
		if (inputBlocker == null) {
			return;
		}
		if (inputBlocker.getScope() != Task.BlockingScope.NONE) {
			if (SwingUtilities.isEventDispatchThread()) {
				inputBlocker.block();
			} else {
				Runnable runnable = new Runnable() {

					public void run() {
						inputBlocker.block();
					}
				};
				SwingUtilities.invokeLater(runnable);
			}
		}
	}

	public void execute(Task task) {
		List<Task> list;
		List<Task> list2;
		if (task == null) {
			throw new IllegalArgumentException("null task");
		}
		if (!(task.isPending() && task.getTaskService() == null)) {
			throw new IllegalArgumentException("task has already been executed");
		}
		task.setTaskService(this);
		List<Task> list3 = this.tasks;
		synchronized (list3) {
			list = this.copyTasksList();
			this.tasks.add(task);
			list2 = this.copyTasksList();
			task.addPropertyChangeListener(this.taskPCL);
		}
		this.firePropertyChange("tasks", list, list2);
		this.maybeBlockTask(task);
		this.executorService.execute(task);
	}

	public List<Task> getTasks() {
		return this.copyTasksList();
	}

	public final void shutdown() {
		this.executorService.shutdown();
	}

	public final List<Runnable> shutdownNow() {
		return this.executorService.shutdownNow();
	}

	public final boolean isShutdown() {
		return this.executorService.isShutdown();
	}

	public final boolean isTerminated() {
		return this.executorService.isTerminated();
	}

	public final boolean awaitTermination(long l, TimeUnit timeUnit)
			throws InterruptedException {
		return this.executorService.awaitTermination(l, timeUnit);
	}

	private class TaskPCL implements PropertyChangeListener {
		private TaskPCL() {
		}

		public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
			Task task;
			String string = propertyChangeEvent.getPropertyName();
			if ("done".equals(string)
					&& (task = (Task) propertyChangeEvent.getSource()).isDone()) {
				List list;
				List list2;
				Object object = TaskService.this.tasks;
				synchronized (object) {
					list2 = TaskService.this.copyTasksList();
					TaskService.this.tasks.remove(task);
					task.removePropertyChangeListener(TaskService.this.taskPCL);
					list = TaskService.this.copyTasksList();
				}
				TaskService.this.firePropertyChange("tasks", list2, list);
				object = task.getInputBlocker();
				if (object != null) {
					object.unblock();
				}
			}
		}
	}

}
