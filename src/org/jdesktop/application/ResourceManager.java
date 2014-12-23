/*
 * Decompiled with CFR 0_92.
 */
package org.jdesktop.application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import org.jdesktop.application.AbstractBean;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.ResourceMap;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ResourceManager extends AbstractBean {
	private static final Logger logger = Logger.getLogger(ResourceManager.class
			.getName());
	private final Map<String, ResourceMap> resourceMaps;
	private final ApplicationContext context;
	private List<String> applicationBundleNames = null;
	private ResourceMap appResourceMap = null;

	protected ResourceManager(ApplicationContext applicationContext) {
		if (applicationContext == null) {
			throw new IllegalArgumentException("null context");
		}
		this.context = applicationContext;
		this.resourceMaps = new ConcurrentHashMap<String, ResourceMap>();
	}

	protected final ApplicationContext getContext() {
		return this.context;
	}

	private List<String> allBundleNames(Class class_, Class class_2) {
		ArrayList<String> arrayList = new ArrayList<String>();
		Class class_3 = class_2.getSuperclass();
		for (Class class_4 = class_; class_4 != class_3; class_4 = class_4
				.getSuperclass()) {
			arrayList.addAll(this.getClassBundleNames(class_4));
		}
		return Collections.unmodifiableList(arrayList);
	}

	private String bundlePackageName(String string) {
		int n = string.lastIndexOf(".");
		return n == -1 ? "" : string.substring(0, n);
	}

	private ResourceMap createResourceMapChain(ClassLoader classLoader,
			ResourceMap resourceMap, ListIterator<String> listIterator) {
		Object object;
		if (!listIterator.hasNext()) {
			return resourceMap;
		}
		String string = listIterator.next();
		String string2 = this.bundlePackageName(string);
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add(string);
		while (listIterator.hasNext()) {
			object = listIterator.next();
			if (string2.equals(this.bundlePackageName((String) object))) {
				arrayList.add((String) object);
				continue;
			}
			listIterator.previous();
			break;
		}
		object = this.createResourceMapChain(classLoader, resourceMap,
				listIterator);
		return this.createResourceMap(classLoader, (ResourceMap) object,
				arrayList);
	}

	private ResourceMap getApplicationResourceMap() {
		if (this.appResourceMap == null) {
			List<String> list = this.getApplicationBundleNames();
			reference var2_2 = this.getContext().getApplicationClass();
			if (var2_2 == null) {
				logger.warning("getApplicationResourceMap(): no Application class");
				var2_2 = Application.class;
			}
			ClassLoader classLoader = var2_2.getClassLoader();
			this.appResourceMap = this.createResourceMapChain(classLoader,
					null, list.listIterator());
		}
		return this.appResourceMap;
	}

	private ResourceMap getClassResourceMap(Class class_, Class class_2) {
		String string = class_.getName() + class_2.getName();
		ResourceMap resourceMap = this.resourceMaps.get(string);
		if (resourceMap == null) {
			List<String> list = this.allBundleNames(class_, class_2);
			ClassLoader classLoader = class_.getClassLoader();
			ResourceMap resourceMap2 = this.getResourceMap();
			resourceMap = this.createResourceMapChain(classLoader,
					resourceMap2, list.listIterator());
			this.resourceMaps.put(string, resourceMap);
		}
		return resourceMap;
	}

	public ResourceMap getResourceMap(Class class_, Class class_2) {
		if (class_ == null) {
			throw new IllegalArgumentException("null startClass");
		}
		if (class_2 == null) {
			throw new IllegalArgumentException("null stopClass");
		}
		if (!class_2.isAssignableFrom(class_)) {
			throw new IllegalArgumentException(
					"startClass is not a subclass, or the same as, stopClass");
		}
		return this.getClassResourceMap(class_, class_2);
	}

	public final ResourceMap getResourceMap(Class class_) {
		if (class_ == null) {
			throw new IllegalArgumentException("null class");
		}
		return this.getResourceMap(class_, class_);
	}

	public ResourceMap getResourceMap() {
		return this.getApplicationResourceMap();
	}

	public List<String> getApplicationBundleNames() {
		if (this.applicationBundleNames == null) {
			Class class_ = this.getContext().getApplicationClass();
			if (class_ == null) {
				return this
						.allBundleNames(Application.class, Application.class);
			}
			this.applicationBundleNames = this.allBundleNames(class_,
					Application.class);
		}
		return this.applicationBundleNames;
	}

	public void setApplicationBundleNames(List<String> list) {
		if (list != null) {
			for (String string : list) {
				if (string != null && list.size() != 0)
					continue;
				throw new IllegalArgumentException("invalid bundle name \""
						+ string + "\"");
			}
		}
		Object object = this.applicationBundleNames;
		this.applicationBundleNames = list != null ? Collections
				.unmodifiableList(new ArrayList<String>(list)) : null;
		this.resourceMaps.clear();
		this.firePropertyChange("applicationBundleNames", object,
				this.applicationBundleNames);
	}

	private String classBundleBaseName(Class class_) {
		String string = class_.getName();
		StringBuffer stringBuffer = new StringBuffer();
		int n = string.lastIndexOf(46);
		if (n > 0) {
			stringBuffer.append(string.substring(0, n));
			stringBuffer.append(".resources.");
			stringBuffer.append(class_.getSimpleName());
		} else {
			stringBuffer.append("resources.");
			stringBuffer.append(class_.getSimpleName());
		}
		return stringBuffer.toString();
	}

	protected List<String> getClassBundleNames(Class class_) {
		String string = this.classBundleBaseName(class_);
		return Collections.singletonList(string);
	}

	protected ResourceMap createResourceMap(ClassLoader classLoader,
			ResourceMap resourceMap, List<String> list) {
		return new ResourceMap(resourceMap, classLoader, list);
	}

	public String getPlatform() {
		return this.getResourceMap().getString("platform", new Object[0]);
	}

	public void setPlatform(String string) {
		if (string == null) {
			throw new IllegalArgumentException("null platform");
		}
		this.getResourceMap().putResource("platform", string);
	}
}
