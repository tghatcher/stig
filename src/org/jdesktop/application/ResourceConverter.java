/*
 * Decompiled with CFR 0_92.
 */
package org.jdesktop.application;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.jdesktop.application.ResourceMap;

public abstract class ResourceConverter {
	protected final Class type;
	private static ResourceConverter[] resourceConvertersArray = new ResourceConverter[] {
			new BooleanResourceConverter("true", "on", "yes"),
			new IntegerResourceConverter(),
			new MessageFormatResourceConverter(), new FloatResourceConverter(),
			new DoubleResourceConverter(), new LongResourceConverter(),
			new ShortResourceConverter(), new ByteResourceConverter(),
			new URLResourceConverter(), new URIResourceConverter() };
	private static List<ResourceConverter> resourceConverters = new ArrayList<ResourceConverter>(
			Arrays.asList(resourceConvertersArray));

	public abstract Object parseString(String var1, ResourceMap var2)
			throws ResourceConverterException;

	public String toString(Object object) {
		return object == null ? "null" : object.toString();
	}

	protected ResourceConverter(Class class_) {
		if (class_ == null) {
			throw new IllegalArgumentException("null type");
		}
		this.type = class_;
	}

	private ResourceConverter() {
		this.type = null;
	}

	public boolean supportsType(Class class_) {
		return this.type.equals(class_);
	}

	public static void register(ResourceConverter resourceConverter) {
		if (resourceConverter == null) {
			throw new IllegalArgumentException("null resourceConverter");
		}
		resourceConverters.add(resourceConverter);
	}

	public static ResourceConverter forType(Class class_) {
		if (class_ == null) {
			throw new IllegalArgumentException("null type");
		}
		for (ResourceConverter resourceConverter : resourceConverters) {
			if (!resourceConverter.supportsType(class_))
				continue;
			return resourceConverter;
		}
		return null;
	}

	private static class BooleanResourceConverter extends ResourceConverter {
		private final String[] trueStrings;

		/* varargs */BooleanResourceConverter(String... arrstring) {
			super(Boolean.class);
			this.trueStrings = arrstring;
		}

		public Object parseString(String string, ResourceMap resourceMap) {
			string = string.trim();
			for (String string2 : this.trueStrings) {
				if (!string.equalsIgnoreCase(string2))
					continue;
				return Boolean.TRUE;
			}
			return Boolean.FALSE;
		}

		public boolean supportsType(Class class_) {
			return class_.equals(Boolean.class) || class_.equals(Boolean.TYPE);
		}
	}

	private static class ByteResourceConverter extends INumberResourceConverter {
		ByteResourceConverter() {
			super(Byte.class, Byte.TYPE);
		}

		protected Number parseString(String string, int n)
				throws NumberFormatException {
			return Byte.valueOf(n == -1 ? Byte.decode(string).byteValue()
					: Byte.parseByte(string, n));
		}
	}

	private static class DoubleResourceConverter extends
			NumberResourceConverter {
		DoubleResourceConverter() {
			super(Double.class, Double.TYPE);
		}

		protected Number parseString(String string)
				throws NumberFormatException {
			return Double.parseDouble(string);
		}
	}

	private static class FloatResourceConverter extends NumberResourceConverter {
		FloatResourceConverter() {
			super(Float.class, Float.TYPE);
		}

		protected Number parseString(String string)
				throws NumberFormatException {
			return Float.valueOf(Float.parseFloat(string));
		}
	}

	private static abstract class INumberResourceConverter extends
			ResourceConverter {
		private final Class primitiveType;

		INumberResourceConverter(Class class_, Class class_2) {
			super(class_);
			this.primitiveType = class_2;
		}

		protected abstract Number parseString(String var1, int var2)
				throws NumberFormatException;

		public Object parseString(String string, ResourceMap resourceMap)
				throws ResourceConverterException {
			try {
				String[] arrstring = string.split("&");
				int n = arrstring.length == 2 ? Integer.parseInt(arrstring[1])
						: -1;
				return this.parseString(arrstring[0], n);
			} catch (NumberFormatException var3_4) {
				throw new ResourceConverterException("invalid "
						+ this.type.getSimpleName(), string, var3_4);
			}
		}

		public boolean supportsType(Class class_) {
			return class_.equals(this.type)
					|| class_.equals(this.primitiveType);
		}
	}

	private static class IntegerResourceConverter extends
			INumberResourceConverter {
		IntegerResourceConverter() {
			super(Integer.class, Integer.TYPE);
		}

		protected Number parseString(String string, int n)
				throws NumberFormatException {
			return n == -1 ? Integer.decode(string) : Integer.parseInt(string,
					n);
		}
	}

	private static class LongResourceConverter extends INumberResourceConverter {
		LongResourceConverter() {
			super(Long.class, Long.TYPE);
		}

		protected Number parseString(String string, int n)
				throws NumberFormatException {
			return n == -1 ? Long.decode(string) : Long.parseLong(string, n);
		}
	}

	private static class MessageFormatResourceConverter extends
			ResourceConverter {
		MessageFormatResourceConverter() {
			super(MessageFormat.class);
		}

		public Object parseString(String string, ResourceMap resourceMap) {
			return new MessageFormat(string);
		}
	}

	private static abstract class NumberResourceConverter extends
			ResourceConverter {
		private final Class primitiveType;

		NumberResourceConverter(Class class_, Class class_2) {
			super(class_);
			this.primitiveType = class_2;
		}

		protected abstract Number parseString(String var1)
				throws NumberFormatException;

		public Object parseString(String string, ResourceMap resourceMap)
				throws ResourceConverterException {
			try {
				return this.parseString(string);
			} catch (NumberFormatException var3_3) {
				throw new ResourceConverterException("invalid "
						+ this.type.getSimpleName(), string, var3_3);
			}
		}

		public boolean supportsType(Class class_) {
			return class_.equals(this.type)
					|| class_.equals(this.primitiveType);
		}
	}

	public static class ResourceConverterException extends Exception {
		private final String badString;

		private String maybeShorten(String string) {
			int n = string.length();
			return n < 128 ? string : string.substring(0, 128) + "...["
					+ (n - 128) + " more characters]";
		}

		public ResourceConverterException(String string, String string2,
				Throwable throwable) {
			super(string, throwable);
			this.badString = this.maybeShorten(string2);
		}

		public ResourceConverterException(String string, String string2) {
			super(string);
			this.badString = this.maybeShorten(string2);
		}

		public String toString() {
			StringBuffer stringBuffer = new StringBuffer(super.toString());
			stringBuffer.append(" string: \"");
			stringBuffer.append(this.badString);
			stringBuffer.append("\"");
			return stringBuffer.toString();
		}
	}

	private static class ShortResourceConverter extends
			INumberResourceConverter {
		ShortResourceConverter() {
			super(Short.class, Short.TYPE);
		}

		protected Number parseString(String string, int n)
				throws NumberFormatException {
			return n == -1 ? Short.decode(string) : Short.parseShort(string, n);
		}
	}

	private static class URIResourceConverter extends ResourceConverter {
		URIResourceConverter() {
			super(URI.class);
		}

		public Object parseString(String string, ResourceMap resourceMap)
				throws ResourceConverterException {
			try {
				return new URI(string);
			} catch (URISyntaxException var3_3) {
				throw new ResourceConverterException("invalid URI", string,
						var3_3);
			}
		}
	}

	private static class URLResourceConverter extends ResourceConverter {
		URLResourceConverter() {
			super(URL.class);
		}

		public Object parseString(String string, ResourceMap resourceMap)
				throws ResourceConverterException {
			try {
				return new URL(string);
			} catch (MalformedURLException var3_3) {
				throw new ResourceConverterException("invalid URL", string,
						var3_3);
			}
		}
	}

}
