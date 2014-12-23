/*
 * Decompiled with CFR 0_92.
 */
package org.jdesktop.application;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;
import javax.swing.JComponent;
import org.jdesktop.application.AbstractBean;
import org.jdesktop.application.ActionManager;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationActionMap;
import org.jdesktop.application.LocalStorage;
import org.jdesktop.application.ResourceManager;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SessionStorage;
import org.jdesktop.application.TaskMonitor;
import org.jdesktop.application.TaskService;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ApplicationContext extends AbstractBean {
	private static final Logger logger = Logger
			.getLogger(ApplicationContext.class.getName());
	private final List<TaskService> taskServices;
	private final List<TaskService> taskServicesReadOnly;
	private ResourceManager resourceManager;
	private ActionManager actionManager;
	private LocalStorage localStorage;
	private SessionStorage sessionStorage;
	private Application application = null;
	private Class applicationClass = null;
	private JComponent focusOwner = null;
	private Clipboard clipboard = null;
	private Throwable uncaughtException = null;
	private TaskMonitor taskMonitor = null;

	protected ApplicationContext() {
		this.resourceManager = new ResourceManager(this);
		this.actionManager = new ActionManager(this);
		this.localStorage = new LocalStorage(this);
		this.sessionStorage = new SessionStorage(this);
		this.taskServices = new CopyOnWriteArrayList<TaskService>();
		this.taskServices.add(new TaskService("default"));
		this.taskServicesReadOnly = Collections
				.unmodifiableList(this.taskServices);
	}

	public final synchronized Class getApplicationClass() {
		return this.applicationClass;
	}

	public final synchronized void setApplicationClass(Class class_) {
		if (this.application != null) {
			throw new IllegalStateException("application has been launched");
		}
		this.applicationClass = class_;
	}

	public final synchronized Application getApplication() {
		return this.application;
	}

	synchronized void setApplication(Application application) {
		if (this.application != null) {
			throw new IllegalStateException(
					"application has already been launched");
		}
		this.application = application;
	}

	public final ResourceManager getResourceManager() {
		return this.resourceManager;
	}

	protected void setResourceManager(ResourceManager resourceManager) {
		if (resourceManager == null) {
			throw new IllegalArgumentException("null resourceManager");
		}
		ResourceManager resourceManager2 = this.resourceManager;
		this.resourceManager = resourceManager;
		this.firePropertyChange("resourceManager", resourceManager2,
				this.resourceManager);
	}

	public final ResourceMap getResourceMap(Class class_) {
		return this.getResourceManager().getResourceMap(class_, class_);
	}

	public final ResourceMap getResourceMap(Class class_, Class class_2) {
		return this.getResourceManager().getResourceMap(class_, class_2);
	}

	public final ResourceMap getResourceMap() {
		return this.getResourceManager().getResourceMap();
	}

	public final ActionManager getActionManager() {
		return this.actionManager;
	}

	protected void setActionManager(ActionManager actionManager) {
		if (actionManager == null) {
			throw new IllegalArgumentException("null actionManager");
		}
		ActionManager actionManager2 = this.actionManager;
		this.actionManager = actionManager;
		this.firePropertyChange("actionManager", actionManager2,
				this.actionManager);
	}

	public final ApplicationActionMap getActionMap() {
		return this.getActionManager().getActionMap();
	}

	public final ApplicationActionMap getActionMap(Class class_, Object object) {
		return this.getActionManager().getActionMap(class_, object);
	}

	public final ApplicationActionMap getActionMap(Object object) {
		if (object == null) {
			throw new IllegalArgumentException("null actionsObject");
		}
		return this.getActionManager().getActionMap(object.getClass(), object);
	}

	public final LocalStorage getLocalStorage() {
		return this.localStorage;
	}

	protected void setLocalStorage(LocalStorage localStorage) {
		if (localStorage == null) {
			throw new IllegalArgumentException("null localStorage");
		}
		LocalStorage localStorage2 = this.localStorage;
		this.localStorage = localStorage;
		this.firePropertyChange("localStorage", localStorage2,
				this.localStorage);
	}

	public final SessionStorage getSessionStorage() {
		return this.sessionStorage;
	}

	protected void setSessionStorage(SessionStorage sessionStorage) {
		if (sessionStorage == null) {
			throw new IllegalArgumentException("null sessionStorage");
		}
		SessionStorage sessionStorage2 = this.sessionStorage;
		this.sessionStorage = sessionStorage;
		this.firePropertyChange("sessionStorage", sessionStorage2,
				this.sessionStorage);
	}

	public Clipboard getClipboard() {
		if (this.clipboard == null) {
			try {
				this.clipboard = Toolkit.getDefaultToolkit()
						.getSystemClipboard();
			} catch (SecurityException var1_1) {
				this.clipboard = new Clipboard("sandbox");
			}
		}
		return this.clipboard;
	}

	public JComponent getFocusOwner() {
		return this.focusOwner;
	}

	void setFocusOwner(JComponent jComponent) {
		JComponent jComponent2 = this.focusOwner;
		this.focusOwner = jComponent;
		this.firePropertyChange("focusOwner", jComponent2, this.focusOwner);
	}

	private List<TaskService> copyTaskServices() {
		return new ArrayList<TaskService>(this.taskServices);
	}

	public void addTaskService(TaskService taskService) {
		if (taskService == null) {
			throw new IllegalArgumentException("null taskService");
		}
		List<TaskService> list = null;
		List<TaskService> list2 = null;
		boolean bl = false;
		List<TaskService> list3 = this.taskServices;
		synchronized (list3) {
			if (!this.taskServices.contains(taskService)) {
				list = this.copyTaskServices();
				this.taskServices.add(taskService);
				list2 = this.copyTaskServices();
				bl = true;
			}
		}
		if (bl) {
			this.firePropertyChange("taskServices", list, list2);
		}
	}

	public void removeTaskService(TaskService taskService) {
		if (taskService == null) {
			throw new IllegalArgumentException("null taskService");
		}
		List<TaskService> list = null;
		List<TaskService> list2 = null;
		boolean bl = false;
		List<TaskService> list3 = this.taskServices;
		synchronized (list3) {
			if (this.taskServices.contains(taskService)) {
				list = this.copyTaskServices();
				this.taskServices.remove(taskService);
				list2 = this.copyTaskServices();
				bl = true;
			}
		}
		if (bl) {
			this.firePropertyChange("taskServices", list, list2);
		}
	}

	public TaskService getTaskService(String string) {
		if (string == null) {
			throw new IllegalArgumentException("null name");
		}
		for (TaskService taskService : this.taskServices) {
			if (!string.equals(taskService.getName()))
				continue;
			return taskService;
		}
		return null;
	}

	public final TaskService getTaskService() {
		return this.getTaskService("default");
	}

	public List<TaskService> getTaskServices() {
		return this.taskServicesReadOnly;
	}

	public final TaskMonitor getTaskMonitor() {
		if (this.taskMonitor == null) {
			this.taskMonitor = new TaskMonitor(this);
		}
		return this.taskMonitor;
	}
}
