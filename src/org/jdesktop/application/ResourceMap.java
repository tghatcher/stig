/*
 * Decompiled with CFR 0_92.
 */
package org.jdesktop.application;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import org.jdesktop.application.MnemonicText;
import org.jdesktop.application.Resource;
import org.jdesktop.application.ResourceConverter;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ResourceMap {
	private static Logger logger;
	private static final Object nullResource;
	private final ClassLoader classLoader;
	private final ResourceMap parent;
	private final List<String> bundleNames;
	private final String resourcesDir;
	private Map<String, Object> bundlesMapP = null;
	private Locale locale = Locale.getDefault();
	private Set<String> bundlesMapKeysP = null;
	private boolean bundlesLoaded = false;

	public ResourceMap(ResourceMap resourceMap, ClassLoader classLoader,
			List<String> list) {
		if (classLoader == null) {
			throw new IllegalArgumentException("null ClassLoader");
		}
		if (list == null || list.size() == 0) {
			throw new IllegalArgumentException("no bundle specified");
		}
		for (String object : list) {
			if (object != null && object.length() != 0)
				continue;
			throw new IllegalArgumentException("invalid bundleName: \""
					+ object + "\"");
		}
		Object object2 = this.bundlePackageName(list.get(0));
		for (String string : list) {
			if (object2.equals(this.bundlePackageName(string)))
				continue;
			throw new IllegalArgumentException("bundles not colocated: \""
					+ string + "\" != \"" + (String) object2 + "\"");
		}
		this.parent = resourceMap;
		this.classLoader = classLoader;
		this.bundleNames = Collections.unmodifiableList(new ArrayList<String>(
				list));
		this.resourcesDir = object2.replace((CharSequence) ".",
				(CharSequence) "/") + "/";
	}

	private String bundlePackageName(String string) {
		int n = string.lastIndexOf(".");
		return n == -1 ? "" : string.substring(0, n);
	}

	public/* varargs */ResourceMap(ResourceMap resourceMap,
			ClassLoader classLoader, String... arrstring) {
		this(resourceMap, classLoader, Arrays.asList(arrstring));
	}

	public ResourceMap getParent() {
		return this.parent;
	}

	public List<String> getBundleNames() {
		return this.bundleNames;
	}

	public ClassLoader getClassLoader() {
		return this.classLoader;
	}

	public String getResourcesDir() {
		return this.resourcesDir;
	}

	private synchronized Map<String, Object> getBundlesMap() {
		Locale locale = Locale.getDefault();
		if (this.locale != locale) {
			this.bundlesLoaded = false;
			this.locale = locale;
		}
		if (!this.bundlesLoaded) {
			ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<String, Object>();
			for (int i = this.bundleNames.size() - 1; i >= 0; --i) {
				try {
					String string = this.bundleNames.get(i);
					ResourceBundle resourceBundle = ResourceBundle.getBundle(
							string, this.locale, this.classLoader);
					Enumeration<String> enumeration = resourceBundle.getKeys();
					while (enumeration.hasMoreElements()) {
						String string2 = enumeration.nextElement();
						concurrentHashMap.put(string2,
								resourceBundle.getObject(string2));
					}
					continue;
				} catch (MissingResourceException var4_5) {
					// empty catch block
				}
			}
			this.bundlesMapP = concurrentHashMap;
			this.bundlesLoaded = true;
		}
		return this.bundlesMapP;
	}

	private void checkNullKey(String string) {
		if (string == null) {
			throw new IllegalArgumentException("null key");
		}
	}

	private synchronized Set<String> getBundlesMapKeys() {
		if (this.bundlesMapKeysP == null) {
			HashSet<String> hashSet = new HashSet<String>(
					this.getResourceKeySet());
			ResourceMap resourceMap = this.getParent();
			if (resourceMap != null) {
				hashSet.addAll(resourceMap.keySet());
			}
			this.bundlesMapKeysP = Collections.unmodifiableSet(hashSet);
		}
		return this.bundlesMapKeysP;
	}

	public Set<String> keySet() {
		return this.getBundlesMapKeys();
	}

	public boolean containsKey(String string) {
		this.checkNullKey(string);
		if (this.containsResourceKey(string)) {
			return true;
		}
		ResourceMap resourceMap = this.getParent();
		return resourceMap != null ? resourceMap.containsKey(string) : false;
	}

	protected Set<String> getResourceKeySet() {
		Map<String, Object> map = this.getBundlesMap();
		if (map == null) {
			return Collections.emptySet();
		}
		return map.keySet();
	}

	protected boolean containsResourceKey(String string) {
		this.checkNullKey(string);
		Map<String, Object> map = this.getBundlesMap();
		return map != null && map.containsKey(string);
	}

	protected Object getResource(String string) {
		this.checkNullKey(string);
		Map<String, Object> map = this.getBundlesMap();
		Object object = map != null ? map.get(string) : null;
		return object == nullResource ? null : object;
	}

	protected void putResource(String string, Object object) {
		this.checkNullKey(string);
		Map<String, Object> map = this.getBundlesMap();
		if (map != null) {
			map.put(string, object == null ? nullResource : object);
		}
	}

	/*
	 * Enabled aggressive block sorting Enabled unnecessary exception pruning
	 */
	public Object getObject(String string, Class var2_2) {
		ResourceMap resourceMap;
		this.checkNullKey(string);
		if (var2_2 == null) {
			throw new IllegalArgumentException("null type");
		}
		if (var2_2.isPrimitive()) {
			if (var2_2 == Boolean.TYPE) {
				var2_2 = Boolean.class;
			} else if (var2_2 == Character.TYPE) {
				var2_2 = Character.class;
			} else if (var2_2 == Byte.TYPE) {
				var2_2 = Byte.class;
			} else if (var2_2 == Short.TYPE) {
				var2_2 = Short.class;
			} else if (var2_2 == Integer.TYPE) {
				var2_2 = Integer.class;
			} else if (var2_2 == Long.TYPE) {
				var2_2 = Long.class;
			} else if (var2_2 == Float.TYPE) {
				var2_2 = Float.class;
			} else if (var2_2 == Double.TYPE) {
				var2_2 = Double.class;
			}
		}
		Object object = null;
		for (resourceMap = this; resourceMap != null; resourceMap = resourceMap
				.getParent()) {
			if (!resourceMap.containsResourceKey(string))
				continue;
			object = resourceMap.getResource(string);
			break;
		}
		if (object instanceof String
				&& ((String) object).contains((CharSequence) "${")) {
			object = this.evaluateStringExpression((String) object);
			resourceMap.putResource(string, object);
		}
		if (object == null)
			return object;
		Class class_ = object.getClass();
		if (var2_2.isAssignableFrom(class_))
			return object;
		if (!(object instanceof String)) {
			String string2 = "named resource has wrong type";
			throw new LookupException(string2, string, var2_2);
		}
		ResourceConverter resourceConverter = ResourceConverter.forType(var2_2);
		if (resourceConverter == null) {
			String string3 = "no StringConverter for required type";
			throw new LookupException(string3, string, var2_2);
		}
		String string4 = (String) object;
		try {
			object = resourceConverter.parseString(string4, resourceMap);
			resourceMap.putResource(string, object);
			return object;
		} catch (ResourceConverter.ResourceConverterException var8_10) {
			String string5 = "string conversion failed";
			LookupException lookupException = new LookupException(string5,
					string, var2_2);
			lookupException.initCause(var8_10);
			throw lookupException;
		}
	}

	private String evaluateStringExpression(String string) {
		if (string.trim().equals("${null}")) {
			return null;
		}
		StringBuffer stringBuffer = new StringBuffer();
		int n = 0;
		int n2 = 0;
		while ((n2 = string.indexOf("${", n)) != -1) {
			if (n2 == 0 || n2 > 0 && string.charAt(n2 - 1) != '\\') {
				String string2;
				int n3 = string.indexOf("}", n2);
				if (n3 != -1 && n3 > n2 + 2) {
					string2 = string.substring(n2 + 2, n3);
					String string3 = this.getString(string2, new Object[0]);
					stringBuffer.append(string.substring(n, n2));
					if (string3 == null) {
						String string4 = String.format(
								"no value for \"%s\" in \"%s\"", string2,
								string);
						throw new LookupException(string4, string2,
								String.class);
					}
					stringBuffer.append(string3);
					n = n3 + 1;
					continue;
				}
				string2 = String.format("no closing brace in \"%s\"", string);
				throw new LookupException(string2, "<not found>", String.class);
			}
			stringBuffer.append(string.substring(n, n2 - 1));
			stringBuffer.append("${");
			n = n2 + 2;
		}
		stringBuffer.append(string.substring(n));
		return stringBuffer.toString();
	}

	public/* varargs */String getString(String string, Object... arrobject) {
		if (arrobject.length == 0) {
			return (String) this.getObject(string, String.class);
		}
		String string2 = (String) this.getObject(string, String.class);
		return string2 == null ? null : String.format(string2, arrobject);
	}

	public final Boolean getBoolean(String string) {
		return (Boolean) this.getObject(string, Boolean.class);
	}

	public final Integer getInteger(String string) {
		return (Integer) this.getObject(string, Integer.class);
	}

	public final Long getLong(String string) {
		return (Long) this.getObject(string, Long.class);
	}

	public final Short getShort(String string) {
		return (Short) this.getObject(string, Short.class);
	}

	public final Byte getByte(String string) {
		return (Byte) this.getObject(string, Byte.class);
	}

	public final Float getFloat(String string) {
		return (Float) this.getObject(string, Float.class);
	}

	public final Double getDouble(String string) {
		return (Double) this.getObject(string, Double.class);
	}

	public final Icon getIcon(String string) {
		return (Icon) this.getObject(string, Icon.class);
	}

	public final ImageIcon getImageIcon(String string) {
		return (ImageIcon) this.getObject(string, ImageIcon.class);
	}

	public final Font getFont(String string) {
		return (Font) this.getObject(string, Font.class);
	}

	public final Color getColor(String string) {
		return (Color) this.getObject(string, Color.class);
	}

	public final KeyStroke getKeyStroke(String string) {
		return (KeyStroke) this.getObject(string, KeyStroke.class);
	}

	public Integer getKeyCode(String string) {
		KeyStroke keyStroke = this.getKeyStroke(string);
		return keyStroke != null ? new Integer(keyStroke.getKeyCode()) : null;
	}

	private void injectComponentProperty(Component component,
			PropertyDescriptor propertyDescriptor, String string) {
		Method method = propertyDescriptor.getWriteMethod();
		Class class_ = propertyDescriptor.getPropertyType();
		if (method != null && class_ != null && this.containsKey(string)) {
			Object object = this.getObject(string, class_);
			String string2 = propertyDescriptor.getName();
			try {
				if ("text".equals(string2)
						&& component instanceof AbstractButton) {
					MnemonicText.configure(component, (String) object);
				}
				if ("text".equals(string2) && component instanceof JLabel) {
					MnemonicText.configure(component, (String) object);
				}
				method.invoke(component, object);
			} catch (Exception var8_12) {
				String string3 = propertyDescriptor.getName();
				String string4 = "property setter failed";
				PropertyInjectionException propertyInjectionException = new PropertyInjectionException(
						string4, string, component, string3);
				propertyInjectionException.initCause(var8_12);
				throw propertyInjectionException;
			}
		} else {
			if (class_ != null) {
				String string5 = propertyDescriptor.getName();
				String string6 = "no value specified for resource";
				throw new PropertyInjectionException(string6, string,
						component, string5);
			}
			if (method == null) {
				String string7 = propertyDescriptor.getName();
				String string8 = "can't set read-only property";
				throw new PropertyInjectionException(string8, string,
						component, string7);
			}
		}
	}

	private void injectComponentProperties(Component component) {
		String string = component.getName();
		if (string != null) {
			boolean bl = false;
			for (String arrpropertyDescriptor2 : this.keySet()) {
				int string2 = arrpropertyDescriptor2.lastIndexOf(".");
				if (string2 == -1
						|| !string.equals(arrpropertyDescriptor2.substring(0,
								string2)))
					continue;
				bl = true;
				break;
			}
			if (!bl) {
				return;
			}
			Object object = null;
			try {
				object = Introspector.getBeanInfo(component.getClass());
			} catch (IntrospectionException var5_6) {
				String string2 = "introspection failed";
				PropertyInjectionException string3 = new PropertyInjectionException(
						string2, null, component, null);
				string3.initCause(var5_6);
				throw string3;
			}
			PropertyDescriptor[] arrpropertyDescriptor = object
					.getPropertyDescriptors();
			if (arrpropertyDescriptor != null
					&& arrpropertyDescriptor.length > 0) {
				for (String string3 : this.keySet()) {
					String string4;
					int n = string3.lastIndexOf(".");
					String string5 = n == -1 ? null : string3.substring(0, n);
					if (!string.equals(string5))
						continue;
					if (n + 1 == string3.length()) {
						string4 = "component resource lacks property name suffix";
						logger.warning(string4);
						break;
					}
					string4 = string3.substring(n + 1);
					boolean bl2 = false;
					for (PropertyDescriptor propertyDescriptor : arrpropertyDescriptor) {
						if (!propertyDescriptor.getName().equals(string4))
							continue;
						this.injectComponentProperty(component,
								propertyDescriptor, string3);
						bl2 = true;
						break;
					}
					if (bl2)
						continue;
					Object object2 = String
							.format("[resource %s] component named %s doesn't have a property named %s",
									string3, string, string4);
					logger.warning((String) object2);
				}
			}
		}
	}

	public void injectComponent(Component component) {
		if (component == null) {
			throw new IllegalArgumentException("null target");
		}
		this.injectComponentProperties(component);
	}

	/*
	 * Enabled force condition propagation Lifted jumps to return sites
	 */
	public void injectComponents(Component component) {
		this.injectComponent(component);
		if (component instanceof JMenu) {
			JMenu jMenu = (JMenu) component;
			for (Component component2 : jMenu.getMenuComponents()) {
				this.injectComponents(component2);
			}
			return;
		}
		if (!(component instanceof Container))
			return;
		Container container = (Container) component;
		for (Component component3 : container.getComponents()) {
			this.injectComponents(component3);
		}
	}

	private void injectField(Field field, Object object, String string) {
		Class class_ = field.getType();
		if (class_.isArray()) {
			class_ = class_.getComponentType();
			Pattern pattern = Pattern.compile(string + "\\[([\\d]+)\\]");
			ArrayList arrayList = new ArrayList();
			for (String string2 : this.keySet()) {
				Matcher matcher = pattern.matcher((CharSequence) string2);
				if (!matcher.matches())
					continue;
				Object object2 = this.getObject(string2, class_);
				if (!field.isAccessible()) {
					field.setAccessible(true);
				}
				try {
					int n = Integer.parseInt(matcher.group(1));
					Array.set(field.get(object), n, object2);
					continue;
				} catch (Exception var11_16) {
					String string3 = "unable to set array element";
					InjectFieldException injectFieldException = new InjectFieldException(
							string3, field, object, string);
					injectFieldException.initCause(var11_16);
					throw injectFieldException;
				}
			}
		} else {
			Object object3 = this.getObject(string, class_);
			if (object3 != null) {
				if (!field.isAccessible()) {
					field.setAccessible(true);
				}
				try {
					field.set(object, object3);
				} catch (Exception var6_8) {
					String string4 = "unable to set field's value";
					InjectFieldException injectFieldException = new InjectFieldException(
							string4, field, object, string);
					injectFieldException.initCause(var6_8);
					throw injectFieldException;
				}
			}
		}
	}

	public void injectFields(Object object) {
		if (object == null) {
			throw new IllegalArgumentException("null target");
		}
		Class class_ = object.getClass();
		if (class_.isArray()) {
			throw new IllegalArgumentException("array target");
		}
		String string = class_.getSimpleName() + ".";
		for (Field field : class_.getDeclaredFields()) {
			Resource resource = (Resource) field.getAnnotation(Resource.class);
			if (resource == null)
				continue;
			String string2 = resource.key();
			String string3 = string2.length() > 0 ? string2 : string
					+ field.getName();
			this.injectField(field, object, string3);
		}
	}

	private static String resourcePath(String string, ResourceMap resourceMap) {
		String string2 = string;
		string2 = string == null ? null : (string.startsWith("/") ? (string
				.length() > 1 ? string.substring(1) : null) : resourceMap
				.getResourcesDir() + string);
		return string2;
	}

	private static ImageIcon loadImageIcon(String string,
			ResourceMap resourceMap)
			throws ResourceConverter.ResourceConverterException {
		String string2 = ResourceMap.resourcePath(string, resourceMap);
		if (string2 == null) {
			String string3 = String.format("invalid image/icon path \"%s\"",
					string);
			throw new ResourceConverter.ResourceConverterException(string3,
					string);
		}
		URL uRL = resourceMap.getClassLoader().getResource(string2);
		if (uRL != null) {
			return new ImageIcon(uRL);
		}
		String string4 = String.format("couldn't find Icon resource \"%s\"",
				string);
		throw new ResourceConverter.ResourceConverterException(string4, string);
	}

	private static List<Double> parseDoubles(String string, int n,
			String string2) throws ResourceConverter.ResourceConverterException {
		String[] arrstring = string.split(",", n + 1);
		if (arrstring.length != n) {
			throw new ResourceConverter.ResourceConverterException(string2,
					string);
		}
		ArrayList<Double> arrayList = new ArrayList<Double>(n);
		for (String string3 : arrstring) {
			try {
				arrayList.add(Double.valueOf(string3));
				continue;
			} catch (NumberFormatException var9_9) {
				throw new ResourceConverter.ResourceConverterException(string2,
						string, var9_9);
			}
		}
		return arrayList;
	}

	static {
		ResourceConverter[] arrresourceConverter;
		logger = Logger.getLogger(ResourceMap.class.getName());
		nullResource = new String("null resource");
		for (ResourceConverter resourceConverter : arrresourceConverter = new ResourceConverter[] {
				new ColorStringConverter(), new IconStringConverter(),
				new ImageStringConverter(), new FontStringConverter(),
				new KeyStrokeStringConverter(), new DimensionStringConverter(),
				new PointStringConverter(), new RectangleStringConverter(),
				new InsetsStringConverter(), new EmptyBorderStringConverter() }) {
			ResourceConverter.register(resourceConverter);
		}
	}

	private static class ColorStringConverter extends ResourceConverter {
		ColorStringConverter() {
			super(Color.class);
		}

		private void error(String string, String string2, Exception exception)
				throws ResourceConverter.ResourceConverterException {
			throw new ResourceConverter.ResourceConverterException(string,
					string2, exception);
		}

		private void error(String string, String string2)
				throws ResourceConverter.ResourceConverterException {
			this.error(string, string2, null);
		}

		/*
		 * Enabled force condition propagation Lifted jumps to return sites
		 */
		public Object parseString(String string, ResourceMap resourceMap)
				throws ResourceConverter.ResourceConverterException {
			Color color = null;
			if (string.startsWith("#")) {
				switch (string.length()) {
				case 7: {
					return Color.decode(string);
				}
				case 9: {
					int n = Integer.decode(string.substring(0, 3));
					int n2 = Integer.decode("#" + string.substring(3));
					return new Color(n << 24 | n2, true);
				}
				}
				throw new ResourceConverter.ResourceConverterException(
						"invalid #RRGGBB or #AARRGGBB color string", string);
			}
			String[] arrstring = string.split(",");
			if (arrstring.length < 3)
				throw new ResourceConverter.ResourceConverterException(
						"invalid R, G, B[, A] color string", string);
			if (arrstring.length > 4) {
				throw new ResourceConverter.ResourceConverterException(
						"invalid R, G, B[, A] color string", string);
			}
			try {
				if (arrstring.length == 4) {
					int n = Integer.parseInt(arrstring[0].trim());
					int n3 = Integer.parseInt(arrstring[1].trim());
					int n4 = Integer.parseInt(arrstring[2].trim());
					int n5 = Integer.parseInt(arrstring[3].trim());
					return new Color(n, n3, n4, n5);
				}
				int n = Integer.parseInt(arrstring[0].trim());
				int n6 = Integer.parseInt(arrstring[1].trim());
				int n7 = Integer.parseInt(arrstring[2].trim());
				return new Color(n, n6, n7);
			} catch (NumberFormatException var5_9) {
				throw new ResourceConverter.ResourceConverterException(
						"invalid R, G, B[, A] color string", string, var5_9);
			}
		}
	}

	private static class DimensionStringConverter extends ResourceConverter {
		DimensionStringConverter() {
			super(Dimension.class);
		}

		public Object parseString(String string, ResourceMap resourceMap)
				throws ResourceConverter.ResourceConverterException {
			List list = ResourceMap.parseDoubles(string, 2,
					"invalid x,y Dimension string");
			Dimension dimension = new Dimension();
			dimension.setSize((Double) list.get(0), (Double) list.get(1));
			return dimension;
		}
	}

	private static class EmptyBorderStringConverter extends ResourceConverter {
		EmptyBorderStringConverter() {
			super(EmptyBorder.class);
		}

		public Object parseString(String string, ResourceMap resourceMap)
				throws ResourceConverter.ResourceConverterException {
			List list = ResourceMap.parseDoubles(string, 4,
					"invalid top,left,bottom,right EmptyBorder string");
			return new EmptyBorder(((Double) list.get(0)).intValue(),
					((Double) list.get(1)).intValue(),
					((Double) list.get(2)).intValue(),
					((Double) list.get(3)).intValue());
		}
	}

	private static class FontStringConverter extends ResourceConverter {
		FontStringConverter() {
			super(Font.class);
		}

		public Object parseString(String string, ResourceMap resourceMap)
				throws ResourceConverter.ResourceConverterException {
			return Font.decode(string);
		}
	}

	private static class IconStringConverter extends ResourceConverter {
		IconStringConverter() {
			super(Icon.class);
		}

		public Object parseString(String string, ResourceMap resourceMap)
				throws ResourceConverter.ResourceConverterException {
			return ResourceMap.loadImageIcon(string, resourceMap);
		}

		public boolean supportsType(Class class_) {
			return class_.equals(Icon.class) || class_.equals(ImageIcon.class);
		}
	}

	private static class ImageStringConverter extends ResourceConverter {
		ImageStringConverter() {
			super(Image.class);
		}

		public Object parseString(String string, ResourceMap resourceMap)
				throws ResourceConverter.ResourceConverterException {
			return ResourceMap.loadImageIcon(string, resourceMap).getImage();
		}
	}

	public static class InjectFieldException extends RuntimeException {
		private final Field field;
		private final Object target;
		private final String key;

		public InjectFieldException(String string, Field field, Object object,
				String string2) {
			super(String.format("%s: resource %s, field %s, target %s", string,
					string2, field, object));
			this.field = field;
			this.target = object;
			this.key = string2;
		}

		public Field getField() {
			return this.field;
		}

		public Object getTarget() {
			return this.target;
		}

		public String getKey() {
			return this.key;
		}
	}

	private static class InsetsStringConverter extends ResourceConverter {
		InsetsStringConverter() {
			super(Insets.class);
		}

		public Object parseString(String string, ResourceMap resourceMap)
				throws ResourceConverter.ResourceConverterException {
			List list = ResourceMap.parseDoubles(string, 4,
					"invalid top,left,bottom,right Insets string");
			return new Insets(((Double) list.get(0)).intValue(),
					((Double) list.get(1)).intValue(),
					((Double) list.get(2)).intValue(),
					((Double) list.get(3)).intValue());
		}
	}

	private static class KeyStrokeStringConverter extends ResourceConverter {
		KeyStrokeStringConverter() {
			super(KeyStroke.class);
		}

		public Object parseString(String string, ResourceMap resourceMap) {
			if (string.contains((CharSequence) "shortcut")) {
				int n = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
				string = string.replaceAll("shortcut", n == 4 ? "meta"
						: "control");
			}
			return KeyStroke.getKeyStroke(string);
		}
	}

	public static class LookupException extends RuntimeException {
		private final Class type;
		private final String key;

		public LookupException(String string, String string2, Class class_) {
			super(String.format("%s: resource %s, type %s", string, string2,
					class_));
			this.key = string2;
			this.type = class_;
		}

		public Class getType() {
			return this.type;
		}

		public String getKey() {
			return this.key;
		}
	}

	private static class PointStringConverter extends ResourceConverter {
		PointStringConverter() {
			super(Point.class);
		}

		public Object parseString(String string, ResourceMap resourceMap)
				throws ResourceConverter.ResourceConverterException {
			List list = ResourceMap.parseDoubles(string, 2,
					"invalid x,y Point string");
			Point point = new Point();
			point.setLocation((Double) list.get(0), (Double) list.get(1));
			return point;
		}
	}

	public static class PropertyInjectionException extends RuntimeException {
		private final String key;
		private final Component component;
		private final String propertyName;

		public PropertyInjectionException(String string, String string2,
				Component component, String string3) {
			super(String.format("%s: resource %s, property %s, component %s",
					string, string2, string3, component));
			this.key = string2;
			this.component = component;
			this.propertyName = string3;
		}

		public String getKey() {
			return this.key;
		}

		public Component getComponent() {
			return this.component;
		}

		public String getPropertyName() {
			return this.propertyName;
		}
	}

	private static class RectangleStringConverter extends ResourceConverter {
		RectangleStringConverter() {
			super(Rectangle.class);
		}

		public Object parseString(String string, ResourceMap resourceMap)
				throws ResourceConverter.ResourceConverterException {
			List list = ResourceMap.parseDoubles(string, 4,
					"invalid x,y,width,height Rectangle string");
			Rectangle rectangle = new Rectangle();
			rectangle.setFrame((Double) list.get(0), (Double) list.get(1),
					(Double) list.get(2), (Double) list.get(3));
			return rectangle;
		}
	}

}
