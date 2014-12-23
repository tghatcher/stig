/*
 * Decompiled with CFR 0_92.
 */
package org.jdesktop.swingworker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import org.jdesktop.swingworker.AccumulativeRunnable;
import org.jdesktop.swingworker.SwingPropertyChangeSupport;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class SwingWorker<T, V> implements Future<T>, Runnable {
	private static final int MAX_WORKER_THREADS = 10;
	private volatile int progress;
	private volatile StateValue state;
	private final FutureTask<T> future;
	private final SwingPropertyChangeSupport propertyChangeSupport;
	private AccumulativeRunnable<V> doProcess;
	private AccumulativeRunnable<Integer> doNotifyProgressChange;
	private static final AccumulativeRunnable<Runnable> doSubmit = new DoSubmitAccumulativeRunnable();
	private static ExecutorService executorService = null;

	public SwingWorker() {
		Callable callable = new Callable<T>() {

			@Override
			public T call() throws Exception {
				SwingWorker.this.setState(StateValue.STARTED);
				return SwingWorker.this.doInBackground();
			}
		};
		this.future = new FutureTask<T>(callable) {

			@Override
			protected void done() {
				SwingWorker.this.doneEDT();
				SwingWorker.this.setState(StateValue.DONE);
			}
		};
		this.state = StateValue.PENDING;
		this.propertyChangeSupport = new SwingPropertyChangeSupport(this, true);
		this.doProcess = null;
		this.doNotifyProgressChange = null;
	}

	protected abstract T doInBackground() throws Exception;

	@Override
	public final void run() {
		this.future.run();
	}

	protected final/* varargs */void publish(V... arrV) {
		SwingWorker swingWorker = this;
		synchronized (swingWorker) {
			if (this.doProcess == null) {
				this.doProcess = new AccumulativeRunnable<V>() {

					@Override
					public void run(List<V> list) {
						SwingWorker.this.process(list);
					}

					@Override
					protected void submit() {
						doSubmit.add(this);
					}
				};
			}
		}
		this.doProcess.add(arrV);
	}

	protected void process(List<V> list) {
	}

	protected void done() {
	}

	protected final void setProgress(int n) {
		if (n < 0 || n > 100) {
			throw new IllegalArgumentException(
					"the value should be from 0 to 100");
		}
		if (this.progress == n) {
			return;
		}
		int n2 = this.progress;
		this.progress = n;
		if (!this.getPropertyChangeSupport().hasListeners("progress")) {
			return;
		}
		SwingWorker swingWorker = this;
		synchronized (swingWorker) {
			if (this.doNotifyProgressChange == null) {
				this.doNotifyProgressChange = new AccumulativeRunnable<Integer>() {

					@Override
					public void run(List<Integer> list) {
						SwingWorker.this.firePropertyChange("progress",
								list.get(0), list.get(list.size() - 1));
					}

					@Override
					protected void submit() {
						doSubmit.add(this);
					}
				};
			}
		}
		this.doNotifyProgressChange.add(n2, n);
	}

	public final int getProgress() {
		return this.progress;
	}

	public final void execute() {
		SwingWorker.getWorkersExecutorService().execute(this);
	}

	@Override
	public final boolean cancel(boolean bl) {
		return this.future.cancel(bl);
	}

	@Override
	public final boolean isCancelled() {
		return this.future.isCancelled();
	}

	@Override
	public final boolean isDone() {
		return this.future.isDone();
	}

	@Override
	public final T get() throws InterruptedException, ExecutionException {
		return this.future.get();
	}

	@Override
	public final T get(long l, TimeUnit timeUnit) throws InterruptedException,
			ExecutionException, TimeoutException {
		return this.future.get(l, timeUnit);
	}

	public final void addPropertyChangeListener(
			PropertyChangeListener propertyChangeListener) {
		this.getPropertyChangeSupport().addPropertyChangeListener(
				propertyChangeListener);
	}

	public final void removePropertyChangeListener(
			PropertyChangeListener propertyChangeListener) {
		this.getPropertyChangeSupport().removePropertyChangeListener(
				propertyChangeListener);
	}

	public final void firePropertyChange(String string, Object object,
			Object object2) {
		this.getPropertyChangeSupport().firePropertyChange(string, object,
				object2);
	}

	public final PropertyChangeSupport getPropertyChangeSupport() {
		return this.propertyChangeSupport;
	}

	public final StateValue getState() {
		if (this.isDone()) {
			return StateValue.DONE;
		}
		return this.state;
	}

	private void setState(StateValue stateValue) {
		StateValue stateValue2 = this.state;
		this.state = stateValue;
		this.firePropertyChange("state", (Object) stateValue2,
				(Object) stateValue);
	}

	private void doneEDT() {
		Runnable runnable = new Runnable() {

			public void run() {
				SwingWorker.this.done();
			}
		};
		if (SwingUtilities.isEventDispatchThread()) {
			runnable.run();
		} else {
			SwingUtilities.invokeLater(runnable);
		}
	}

	private static synchronized ExecutorService getWorkersExecutorService() {
		if (executorService == null) {
			ThreadFactory threadFactory = new ThreadFactory() {
				final ThreadFactory defaultFactory = Executors
						.defaultThreadFactory();

				public Thread newThread(Runnable runnable) {
					Thread thread = this.defaultFactory.newThread(runnable);
					thread.setName("SwingWorker-" + thread.getName());
					return thread;
				}
			};
			executorService = new ThreadPoolExecutor(0, 10, 1,
					TimeUnit.SECONDS, new LinkedBlockingQueue<E>(),
					threadFactory) {
				private final ReentrantLock pauseLock = new ReentrantLock();
				private final Condition unpaused = this.pauseLock
						.newCondition();
				private boolean isPaused = false;
				private final ReentrantLock executeLock = new ReentrantLock();

				public void execute(Runnable runnable) {
					this.executeLock.lock();
					try {
						this.pauseLock.lock();
						try {
							this.isPaused = true;
						} finally {
							this.pauseLock.unlock();
						}
						this.setCorePoolSize(10);
						super.execute(runnable);
						this.setCorePoolSize(0);
						this.pauseLock.lock();
						try {
							this.isPaused = false;
							this.unpaused.signalAll();
						} finally {
							this.pauseLock.unlock();
						}
					} finally {
						this.executeLock.unlock();
					}
				}

				protected void afterExecute(Runnable runnable,
						Throwable throwable) {
					super.afterExecute(runnable, throwable);
					this.pauseLock.lock();
					try {
						while (this.isPaused) {
							this.unpaused.await();
						}
					} catch (InterruptedException var3_3) {
					} finally {
						this.pauseLock.unlock();
					}
				}
			};
		}
		return executorService;
	}

	/*
	 * This class specifies class file version 49.0 but uses Java 6 signatures.
	 * Assumed Java 6.
	 */
	private static class DoSubmitAccumulativeRunnable extends
			AccumulativeRunnable<Runnable> implements ActionListener {
		private static final int DELAY = 33;

		private DoSubmitAccumulativeRunnable() {
		}

		@Override
		protected void run(List<Runnable> list) {
			for (Runnable runnable : list) {
				runnable.run();
			}
		}

		@Override
		protected void submit() {
			Timer timer = new Timer(33, this);
			timer.setRepeats(false);
			timer.start();
		}

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			this.run();
		}
	}

	public static enum StateValue {
		PENDING, STARTED, DONE;

		private StateValue() {
		}
	}

}
