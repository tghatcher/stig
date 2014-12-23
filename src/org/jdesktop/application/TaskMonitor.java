/*
 * Decompiled with CFR 0_92.
 */
package org.jdesktop.application;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.jdesktop.application.AbstractBean;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.Task;
import org.jdesktop.application.TaskService;
import org.jdesktop.swingworker.SwingWorker;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class TaskMonitor extends AbstractBean {
	private final PropertyChangeListener applicationPCL;
	private final PropertyChangeListener taskServicePCL;
	private final PropertyChangeListener taskPCL;
	private final LinkedList<Task> taskQueue;
	private boolean autoUpdateForegroundTask = true;
	private Task foregroundTask = null;

	public TaskMonitor(ApplicationContext applicationContext) {
		this.applicationPCL = new ApplicationPCL();
		this.taskServicePCL = new TaskServicePCL();
		this.taskPCL = new TaskPCL();
		this.taskQueue = new LinkedList();
		applicationContext.addPropertyChangeListener(this.applicationPCL);
		for (TaskService taskService : applicationContext.getTaskServices()) {
			taskService.addPropertyChangeListener(this.taskServicePCL);
		}
	}

	public void setForegroundTask(Task task) {
		Task task2;
		Task task3 = this.foregroundTask;
		if (task3 != null) {
			task3.removePropertyChangeListener(this.taskPCL);
		}
		if ((task2 = (this.foregroundTask = task)) != null) {
			task2.addPropertyChangeListener(this.taskPCL);
		}
		this.firePropertyChange("foregroundTask", task3, task2);
	}

	public Task getForegroundTask() {
		return this.foregroundTask;
	}

	public boolean getAutoUpdateForegroundTask() {
		return this.autoUpdateForegroundTask;
	}

	public void setAutoUpdateForegroundTask(boolean bl) {
		boolean bl2 = this.autoUpdateForegroundTask;
		this.autoUpdateForegroundTask = bl;
		this.firePropertyChange("autoUpdateForegroundTask", bl2,
				this.autoUpdateForegroundTask);
	}

	private List<Task> copyTaskQueue() {
		LinkedList<Task> linkedList = this.taskQueue;
		synchronized (linkedList) {
			if (this.taskQueue.isEmpty()) {
				return Collections.emptyList();
			}
			return new ArrayList<Task>(this.taskQueue);
		}
	}

	public List<Task> getTasks() {
		return this.copyTaskQueue();
	}

	private void updateTasks(List<Task> list, List<Task> list2) {
		boolean bl = false;
		List<Task> list3 = this.copyTaskQueue();
		for (Task object22 : list) {
			if (list2.contains(object22) || !this.taskQueue.remove(object22))
				continue;
			bl = true;
		}
		for (Task task : list2) {
			if (this.taskQueue.contains(task))
				continue;
			this.taskQueue.addLast(task);
			bl = true;
		}
		Iterator<Task> iterator = this.taskQueue.iterator();
		while (iterator.hasNext()) {
			Task task2 = iterator.next();
			if (!task2.isDone())
				continue;
			iterator.remove();
			bl = true;
		}
		if (bl) {
			List<Task> list4 = this.copyTaskQueue();
			this.firePropertyChange("tasks", list3, list4);
		}
		if (this.autoUpdateForegroundTask && this.getForegroundTask() == null) {
			this.setForegroundTask(this.taskQueue.isEmpty() ? null
					: this.taskQueue.getLast());
		}
	}

	private class ApplicationPCL implements PropertyChangeListener {
		private ApplicationPCL() {
		}

		public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
			String string = propertyChangeEvent.getPropertyName();
			if ("taskServices".equals(string)) {
				List list = (List) propertyChangeEvent.getOldValue();
				List list2 = (List) propertyChangeEvent.getNewValue();
				for (TaskService taskService2 : list) {
					taskService2
							.removePropertyChangeListener(TaskMonitor.this.taskServicePCL);
				}
				for (TaskService taskService2 : list2) {
					taskService2
							.addPropertyChangeListener(TaskMonitor.this.taskServicePCL);
				}
			}
		}
	}

	private class TaskPCL implements PropertyChangeListener {
		private TaskPCL() {
		}

		private void fireStateChange(Task task, String string) {
			TaskMonitor.this.firePropertyChange(new PropertyChangeEvent(task,
					string, false, true));
		}

		public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
			String string = propertyChangeEvent.getPropertyName();
			Task task = (Task) propertyChangeEvent.getSource();
			Object object = propertyChangeEvent.getNewValue();
			if (task != null && task == TaskMonitor.this.getForegroundTask()) {
				TaskMonitor.this.firePropertyChange(propertyChangeEvent);
				if ("state".equals(string)) {
					SwingWorker.StateValue stateValue = (SwingWorker.StateValue) propertyChangeEvent
							.getNewValue();
					switch (stateValue) {
					case PENDING: {
						this.fireStateChange(task, "pending");
						break;
					}
					case STARTED: {
						this.fireStateChange(task, "started");
						break;
					}
					case DONE: {
						this.fireStateChange(task, "done");
						TaskMonitor.this.setForegroundTask(null);
					}
					}
				}
			}
		}
	}

	private class TaskServicePCL implements PropertyChangeListener {
		private TaskServicePCL() {
		}

		public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
			String string = propertyChangeEvent.getPropertyName();
			if ("tasks".equals(string)) {
				List list = (List) propertyChangeEvent.getOldValue();
				List list2 = (List) propertyChangeEvent.getNewValue();
				TaskMonitor.this.updateTasks(list, list2);
			}
		}
	}

}
