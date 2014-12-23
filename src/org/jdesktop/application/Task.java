/*
 * Decompiled with CFR 0_92.
 */
package org.jdesktop.application;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import org.jdesktop.application.AbstractBean;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationAction;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.TaskEvent;
import org.jdesktop.application.TaskListener;
import org.jdesktop.application.TaskService;
import org.jdesktop.swingworker.SwingWorker;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class Task<T, V> extends SwingWorker<T, V> {
	private static final Logger logger = Logger.getLogger(Task.class.getName());
	private final Application application;
	private String resourcePrefix;
	private ResourceMap resourceMap;
	private List<TaskListener<T, V>> taskListeners;
	private InputBlocker inputBlocker;
	private String name = null;
	private String title = null;
	private String description = null;
	private long messageTime = -1;
	private String message = null;
	private long startTime = -1;
	private long doneTime = -1;
	private boolean userCanCancel = true;
	private boolean progressPropertyIsValid = false;
	private TaskService taskService = null;

	private void initTask(ResourceMap resourceMap, String string) {
		this.resourceMap = resourceMap;
		this.resourcePrefix = string == null || string.length() == 0 ? ""
				: (string.endsWith(".") ? string : string + ".");
		if (resourceMap != null) {
			this.title = resourceMap.getString(this.resourceName("title"),
					new Object[0]);
			this.description = resourceMap.getString(
					this.resourceName("description"), new Object[0]);
			this.message = resourceMap.getString(this.resourceName("message"),
					new Object[0]);
			if (this.message != null) {
				this.messageTime = System.currentTimeMillis();
			}
		}
		this.addPropertyChangeListener(new StatePCL());
		this.taskListeners = new CopyOnWriteArrayList<TaskListener<T, V>>();
	}

	private ResourceMap defaultResourceMap(Application application) {
		return application.getContext().getResourceMap(this.getClass(),
				Task.class);
	}

	@Deprecated
	public Task(Application application, ResourceMap resourceMap, String string) {
		this.application = application;
		this.initTask(resourceMap, string);
	}

	@Deprecated
	public Task(Application application, String string) {
		this.application = application;
		this.initTask(this.defaultResourceMap(application), string);
	}

	public Task(Application application) {
		this.application = application;
		this.initTask(this.defaultResourceMap(application), "");
	}

	public final Application getApplication() {
		return this.application;
	}

	public final ApplicationContext getContext() {
		return this.getApplication().getContext();
	}

	public synchronized TaskService getTaskService() {
		return this.taskService;
	}

	synchronized void setTaskService(TaskService taskService) {
		TaskService taskService2;
		TaskService taskService3;
		Task task = this;
		synchronized (task) {
			taskService2 = this.taskService;
			taskService3 = this.taskService = taskService;
		}
		this.firePropertyChange("taskService", taskService2, taskService3);
	}

	protected final String resourceName(String string) {
		return this.resourcePrefix + string;
	}

	public final ResourceMap getResourceMap() {
		return this.resourceMap;
	}

	public synchronized String getTitle() {
		return this.title;
	}

	protected void setTitle(String string) {
		String string2;
		String string3;
		Task task = this;
		synchronized (task) {
			string2 = this.title;
			string3 = this.title = string;
		}
		this.firePropertyChange("title", string2, string3);
	}

	public synchronized String getDescription() {
		return this.description;
	}

	protected void setDescription(String string) {
		String string2;
		String string3;
		Task task = this;
		synchronized (task) {
			string2 = this.description;
			string3 = this.description = string;
		}
		this.firePropertyChange("description", string2, string3);
	}

	public long getExecutionDuration(TimeUnit timeUnit) {
		long l;
		long l2;
		Task task = this;
		synchronized (task) {
			l2 = this.startTime;
			l = this.doneTime;
		}
		long l3 = l2 == -1 ? 0 : (l == -1 ? System.currentTimeMillis() - l2 : l
				- l2);
		return timeUnit.convert(Math.max(0, l3), TimeUnit.MILLISECONDS);
	}

	public String getMessage() {
		return this.message;
	}

	protected void setMessage(String string) {
		String string2;
		String string3;
		Task task = this;
		synchronized (task) {
			string2 = this.message;
			string3 = this.message = string;
			this.messageTime = System.currentTimeMillis();
		}
		this.firePropertyChange("message", string2, string3);
	}

	protected final/* varargs */void message(String string,
			Object... arrobject) {
		ResourceMap resourceMap = this.getResourceMap();
		if (resourceMap != null) {
			this.setMessage(resourceMap.getString(this.resourceName(string),
					arrobject));
		} else {
			this.setMessage(string);
		}
	}

	public long getMessageDuration(TimeUnit timeUnit) {
		long l;
		Task task = this;
		synchronized (task) {
			l = this.messageTime;
		}
		long l2 = l == -1 ? 0 : Math.max(0, System.currentTimeMillis() - l);
		return timeUnit.convert(l2, TimeUnit.MILLISECONDS);
	}

	public synchronized boolean getUserCanCancel() {
		return this.userCanCancel;
	}

	protected void setUserCanCancel(boolean bl) {
		boolean bl2;
		boolean bl3;
		Task task = this;
		synchronized (task) {
			bl3 = this.userCanCancel;
			bl2 = this.userCanCancel = bl;
		}
		this.firePropertyChange("userCanCancel", bl3, bl2);
	}

	public synchronized boolean isProgressPropertyValid() {
		return this.progressPropertyIsValid;
	}

	protected final void setProgress(int n, int n2, int n3) {
		if (n2 >= n3) {
			throw new IllegalArgumentException("invalid range: min >= max");
		}
		if (n < n2 || n > n3) {
			throw new IllegalArgumentException("invalid value");
		}
		float f = (float) (n - n2) / (float) (n3 - n2);
		this.setProgress(Math.round(f * 100.0f));
	}

	protected final void setProgress(float f) {
		if ((double) f < 0.0 || (double) f > 1.0) {
			throw new IllegalArgumentException("invalid percentage");
		}
		this.setProgress(Math.round(f * 100.0f));
	}

	protected final void setProgress(float f, float f2, float f3) {
		if (f2 >= f3) {
			throw new IllegalArgumentException("invalid range: min >= max");
		}
		if (f < f2 || f > f3) {
			throw new IllegalArgumentException("invalid value");
		}
		float f4 = (f - f2) / (f3 - f2);
		this.setProgress(Math.round(f4 * 100.0f));
	}

	public final boolean isPending() {
		return this.getState() == SwingWorker.StateValue.PENDING;
	}

	public final boolean isStarted() {
		return this.getState() == SwingWorker.StateValue.STARTED;
	}

	@Override
	protected void process(List<V> list) {
		this.fireProcessListeners(list);
	}

	@Override
	protected final void done() {
		block12: {
			try {
				if (this.isCancelled()) {
					this.cancelled();
					break block12;
				}
				try {
					this.succeeded(this.get());
				} catch (InterruptedException var1_1) {
					this.interrupted(var1_1);
				} catch (ExecutionException var1_2) {
					this.failed(var1_2.getCause());
				}
			} finally {
				try {
					this.finished();
				} finally {
					this.setTaskService(null);
				}
			}
		}
	}

	protected void cancelled() {
	}

	protected void succeeded(T t) {
	}

	protected void interrupted(InterruptedException interruptedException) {
	}

	protected void failed(Throwable throwable) {
		String string = String.format("%s failed: %s", this, throwable);
		logger.log(Level.SEVERE, string, throwable);
	}

	protected void finished() {
	}

	public void addTaskListener(TaskListener<T, V> taskListener) {
		if (taskListener == null) {
			throw new IllegalArgumentException("null listener");
		}
		this.taskListeners.add(taskListener);
	}

	public void removeTaskListener(TaskListener<T, V> taskListener) {
		if (taskListener == null) {
			throw new IllegalArgumentException("null listener");
		}
		this.taskListeners.remove(taskListener);
	}

	public TaskListener<T, V>[] getTaskListeners() {
		return this.taskListeners.toArray(new TaskListener[this.taskListeners
				.size()]);
	}

	private void fireProcessListeners(List<V> list) {
		TaskEvent<List<V>> taskEvent = new TaskEvent<List<V>>(this, list);
		for (TaskListener<T, V> taskListener : this.taskListeners) {
			taskListener.process(taskEvent);
		}
	}

	private void fireDoInBackgroundListeners() {
		TaskEvent<Object> taskEvent = new TaskEvent<Object>(this, null);
		for (TaskListener<T, V> taskListener : this.taskListeners) {
			taskListener.doInBackground(taskEvent);
		}
	}

	private void fireSucceededListeners(T t) {
		TaskEvent<T> taskEvent = new TaskEvent<T>(this, t);
		for (TaskListener<T, V> taskListener : this.taskListeners) {
			taskListener.succeeded(taskEvent);
		}
	}

	private void fireCancelledListeners() {
		TaskEvent<Object> taskEvent = new TaskEvent<Object>(this, null);
		for (TaskListener<T, V> taskListener : this.taskListeners) {
			taskListener.cancelled(taskEvent);
		}
	}

	private void fireInterruptedListeners(
			InterruptedException interruptedException) {
		TaskEvent<InterruptedException> taskEvent = new TaskEvent<InterruptedException>(
				this, interruptedException);
		for (TaskListener<T, V> taskListener : this.taskListeners) {
			taskListener.interrupted(taskEvent);
		}
	}

	private void fireFailedListeners(Throwable throwable) {
		TaskEvent<Throwable> taskEvent = new TaskEvent<Throwable>(this,
				throwable);
		for (TaskListener<T, V> taskListener : this.taskListeners) {
			taskListener.failed(taskEvent);
		}
	}

	private void fireFinishedListeners() {
		TaskEvent<Object> taskEvent = new TaskEvent<Object>(this, null);
		for (TaskListener<T, V> taskListener : this.taskListeners) {
			taskListener.finished(taskEvent);
		}
	}

	private void fireCompletionListeners() {
		block7: {
			try {
				if (this.isCancelled()) {
					this.fireCancelledListeners();
					break block7;
				}
				try {
					this.fireSucceededListeners(this.get());
				} catch (InterruptedException var1_1) {
					this.fireInterruptedListeners(var1_1);
				} catch (ExecutionException var1_2) {
					this.fireFailedListeners(var1_2.getCause());
				}
			} finally {
				this.fireFinishedListeners();
			}
		}
	}

	public final InputBlocker getInputBlocker() {
		return this.inputBlocker;
	}

	public final void setInputBlocker(InputBlocker inputBlocker) {
		InputBlocker inputBlocker2;
		InputBlocker inputBlocker3;
		if (this.getTaskService() != null) {
			throw new IllegalStateException("task already being executed");
		}
		Task task = this;
		synchronized (task) {
			inputBlocker2 = this.inputBlocker;
			inputBlocker3 = this.inputBlocker = inputBlocker;
		}
		this.firePropertyChange("inputBlocker", inputBlocker2, inputBlocker3);
	}

	public static enum BlockingScope {
		NONE, ACTION, COMPONENT, WINDOW, APPLICATION;

		private BlockingScope() {
		}
	}

	public static abstract class InputBlocker extends AbstractBean {
		private final Task task;
		private final BlockingScope scope;
		private final Object target;
		private final ApplicationAction action;

		public InputBlocker(Task task, BlockingScope blockingScope,
				Object object, ApplicationAction applicationAction) {
			if (task == null) {
				throw new IllegalArgumentException("null task");
			}
			if (task.getTaskService() != null) {
				throw new IllegalStateException("task already being executed");
			}
			switch (blockingScope) {
			case ACTION: {
				if (object instanceof Action)
					break;
				throw new IllegalArgumentException("target not an Action");
			}
			case COMPONENT:
			case WINDOW: {
				if (object instanceof Component)
					break;
				throw new IllegalArgumentException("target not a Component");
			}
			}
			this.task = task;
			this.scope = blockingScope;
			this.target = object;
			this.action = applicationAction;
		}

		public InputBlocker(Task task, BlockingScope blockingScope,
				Object object) {
			this(
					task,
					blockingScope,
					object,
					object instanceof ApplicationAction ? (ApplicationAction) object
							: null);
		}

		public final Task getTask() {
			return this.task;
		}

		public final BlockingScope getScope() {
			return this.scope;
		}

		public final Object getTarget() {
			return this.target;
		}

		public final ApplicationAction getAction() {
			return this.action;
		}

		protected abstract void block();

		protected abstract void unblock();
	}

	private class StatePCL implements PropertyChangeListener {
		private StatePCL() {
		}

		public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
			String string = propertyChangeEvent.getPropertyName();
			if ("state".equals(string)) {
				SwingWorker.StateValue stateValue = (SwingWorker.StateValue) propertyChangeEvent
						.getNewValue();
				Task task = (Task) propertyChangeEvent.getSource();
				switch (stateValue) {
				case STARTED: {
					this.taskStarted(task);
					break;
				}
				case DONE: {
					this.taskDone(task);
				}
				}
			} else if ("progress".equals(string)) {
				Task task = Task.this;
				synchronized (task) {
					Task.this.progressPropertyIsValid = true;
				}
			}
		}

		private void taskStarted(Task task) {
			Task task2 = Task.this;
			synchronized (task2) {
				Task.this.startTime = System.currentTimeMillis();
			}
			Task.this.firePropertyChange("started", false, true);
			Task.this.fireDoInBackgroundListeners();
		}

		private void taskDone(Task task) {
			Task task2 = Task.this;
			synchronized (task2) {
				Task.this.doneTime = System.currentTimeMillis();
			}
			try {
				task.removePropertyChangeListener(this);
				Task.this.firePropertyChange("done", false, true);
				Task.this.fireCompletionListeners();
			} finally {
				Task.this.firePropertyChange("completed", false, true);
			}
		}
	}

}
