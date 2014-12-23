/*
 * Decompiled with CFR 0_92.
 * 
 * Could not load the following classes:
 *  javax.jnlp.BasicService
 *  javax.jnlp.FileContents
 *  javax.jnlp.PersistenceService
 *  javax.jnlp.ServiceManager
 *  javax.jnlp.UnavailableServiceException
 */
package org.jdesktop.application;

import java.awt.Rectangle;
import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.ExceptionListener;
import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jnlp.BasicService;
import javax.jnlp.FileContents;
import javax.jnlp.PersistenceService;
import javax.jnlp.ServiceManager;
import javax.jnlp.UnavailableServiceException;
import org.jdesktop.application.AbstractBean;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.ResourceMap;

public class LocalStorage extends AbstractBean {
	private static Logger logger = Logger.getLogger(LocalStorage.class
			.getName());
	private final ApplicationContext context;
	private long storageLimit = -1;
	private LocalIO localIO = null;
	private final File unspecifiedFile;
	private File directory;
	private static boolean persistenceDelegatesInitialized = false;

	protected LocalStorage(ApplicationContext applicationContext) {
		this.directory = this.unspecifiedFile = new File("unspecified");
		if (applicationContext == null) {
			throw new IllegalArgumentException("null context");
		}
		this.context = applicationContext;
	}

	protected final ApplicationContext getContext() {
		return this.context;
	}

	private void checkFileName(String string) {
		if (string == null) {
			throw new IllegalArgumentException("null fileName");
		}
	}

	public InputStream openInputFile(String string) throws IOException {
		this.checkFileName(string);
		return this.getLocalIO().openInputFile(string);
	}

	public OutputStream openOutputFile(String string) throws IOException {
		this.checkFileName(string);
		return this.getLocalIO().openOutputFile(string);
	}

	public boolean deleteFile(String string) throws IOException {
		this.checkFileName(string);
		return this.getLocalIO().deleteFile(string);
	}

	public void save(Object object, String string) throws IOException {
		AbortExceptionListener abortExceptionListener = new AbortExceptionListener();
		XMLEncoder xMLEncoder = null;
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			xMLEncoder = new XMLEncoder(byteArrayOutputStream);
			if (!persistenceDelegatesInitialized) {
				xMLEncoder.setPersistenceDelegate(Rectangle.class,
						new RectanglePD());
				persistenceDelegatesInitialized = true;
			}
			xMLEncoder.setExceptionListener(abortExceptionListener);
			xMLEncoder.writeObject(object);
		} finally {
			if (xMLEncoder != null) {
				xMLEncoder.close();
			}
		}
		if (abortExceptionListener.exception != null) {
			throw new LSException("save failed \"" + string + "\"",
					abortExceptionListener.exception);
		}
		OutputStream outputStream = null;
		try {
			outputStream = this.openOutputFile(string);
			outputStream.write(byteArrayOutputStream.toByteArray());
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	public Object load(String string) throws IOException {
		InputStream inputStream = null;
		try {
			inputStream = this.openInputFile(string);
		} catch (IOException var3_3) {
			return null;
		}
		AbortExceptionListener abortExceptionListener = new AbortExceptionListener();
		XMLDecoder xMLDecoder = null;
		try {
			xMLDecoder = new XMLDecoder(inputStream);
			xMLDecoder.setExceptionListener(abortExceptionListener);
			Object object = xMLDecoder.readObject();
			if (abortExceptionListener.exception != null) {
				throw new LSException("load failed \"" + string + "\"",
						abortExceptionListener.exception);
			}
			Object object2 = object;
			return object2;
		} finally {
			if (xMLDecoder != null) {
				xMLDecoder.close();
			}
		}
	}

	private void closeStream(Closeable closeable, String string)
			throws IOException {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException var3_3) {
				throw new LSException("close failed \"" + string + "\"", var3_3);
			}
		}
	}

	public long getStorageLimit() {
		return this.storageLimit;
	}

	public void setStorageLimit(long l) {
		if (l < -1) {
			throw new IllegalArgumentException("invalid storageLimit");
		}
		long l2 = this.storageLimit;
		this.storageLimit = l;
		this.firePropertyChange("storageLimit", l2, this.storageLimit);
	}

	private String getId(String string, String string2) {
		ResourceMap resourceMap = this.getContext().getResourceMap();
		String string3 = resourceMap.getString(string, new Object[0]);
		if (string3 == null) {
			logger.log(Level.WARNING, "unspecified resource " + string
					+ " using " + string2);
			string3 = string2;
		} else if (string3.trim().length() == 0) {
			logger.log(Level.WARNING, "empty resource " + string + " using "
					+ string2);
			string3 = string2;
		}
		return string3;
	}

	private String getApplicationId() {
		return this.getId("Application.id", this.getContext()
				.getApplicationClass().getSimpleName());
	}

	private String getVendorId() {
		return this.getId("Application.vendorId", "UnknownApplicationVendor");
	}

	private OSId getOSId() {
		PrivilegedAction<String> privilegedAction = new PrivilegedAction<String>() {

			@Override
			public String run() {
				return System.getProperty("os.name");
			}
		};
		OSId oSId = OSId.UNIX;
		String string = (String) AccessController
				.doPrivileged(privilegedAction);
		if (string != null) {
			if (string.toLowerCase().startsWith("mac os x")) {
				oSId = OSId.OSX;
			} else if (string.contains((CharSequence) "Windows")) {
				oSId = OSId.WINDOWS;
			}
		}
		return oSId;
	}

	public File getDirectory() {
		if (this.directory == this.unspecifiedFile) {
			this.directory = null;
			String string = null;
			try {
				string = System.getProperty("user.home");
			} catch (SecurityException var2_2) {
				// empty catch block
			}
			if (string != null) {
				String string2 = this.getApplicationId();
				OSId oSId = this.getOSId();
				if (oSId == OSId.WINDOWS) {
					String string3;
					File file = null;
					try {
						string3 = System.getenv("APPDATA");
						if (string3 != null && string3.length() > 0) {
							file = new File(string3);
						}
					} catch (SecurityException var5_9) {
						// empty catch block
					}
					string3 = this.getVendorId();
					if (file != null && file.isDirectory()) {
						String string4 = string3 + "\\" + string2 + "\\";
						this.directory = new File(file, string4);
					} else {
						String string5 = "Application Data\\" + string3 + "\\"
								+ string2 + "\\";
						this.directory = new File(string, string5);
					}
				} else if (oSId == OSId.OSX) {
					String string6 = "Library/Application Support/" + string2
							+ "/";
					this.directory = new File(string, string6);
				} else {
					String string7 = "." + string2 + "/";
					this.directory = new File(string, string7);
				}
			}
		}
		return this.directory;
	}

	public void setDirectory(File file) {
		File file2 = this.directory;
		this.directory = file;
		this.firePropertyChange("directory", file2, this.directory);
	}

	private synchronized LocalIO getLocalIO() {
		if (this.localIO == null) {
			this.localIO = this.getPersistenceServiceIO();
			if (this.localIO == null) {
				this.localIO = new LocalFileIO();
			}
		}
		return this.localIO;
	}

	private LocalIO getPersistenceServiceIO() {
		try {
			Class class_ = Class.forName("javax.jnlp.ServiceManager");
			Method method = class_.getMethod("getServiceNames", new Class[0]);
			String[] arrstring = (String[]) method.invoke(null, new Object[0]);
			boolean bl = false;
			boolean bl2 = false;
			for (String string : arrstring) {
				if (string.equals("javax.jnlp.BasicService")) {
					bl2 = true;
					continue;
				}
				if (!string.equals("javax.jnlp.PersistenceService"))
					continue;
				bl = true;
			}
			if (bl2 && bl) {
				return new PersistenceServiceIO();
			}
		} catch (Exception var1_2) {
			// empty catch block
		}
		return null;
	}

	private static class AbortExceptionListener implements ExceptionListener {
		public Exception exception = null;

		private AbortExceptionListener() {
		}

		public void exceptionThrown(Exception exception) {
			if (this.exception == null) {
				this.exception = exception;
			}
		}
	}

	private static class LSException extends IOException {
		public LSException(String string, Throwable throwable) {
			super(string);
			this.initCause(throwable);
		}

		public LSException(String string) {
			super(string);
		}
	}

	private class LocalFileIO extends LocalIO {
		private LocalFileIO() {
			super();
		}

		public InputStream openInputFile(String string) throws IOException {
			File file = new File(LocalStorage.this.getDirectory(), string);
			try {
				return new BufferedInputStream(new FileInputStream(file));
			} catch (IOException var3_3) {
				throw new LSException("couldn't open input file \"" + string
						+ "\"", var3_3);
			}
		}

		public OutputStream openOutputFile(String string) throws IOException {
			File file = LocalStorage.this.getDirectory();
			if (!(file.isDirectory() || file.mkdirs())) {
				throw new LSException("couldn't create directory " + file);
			}
			File file2 = new File(file, string);
			try {
				return new BufferedOutputStream(new FileOutputStream(file2));
			} catch (IOException var4_4) {
				throw new LSException("couldn't open output file \"" + string
						+ "\"", var4_4);
			}
		}

		public boolean deleteFile(String string) throws IOException {
			File file = new File(LocalStorage.this.getDirectory(), string);
			return file.delete();
		}
	}

	private abstract class LocalIO {
		private LocalIO() {
		}

		public abstract InputStream openInputFile(String var1)
				throws IOException;

		public abstract OutputStream openOutputFile(String var1)
				throws IOException;

		public abstract boolean deleteFile(String var1) throws IOException;
	}

	private static enum OSId {
		WINDOWS, OSX, UNIX;

		private OSId() {
		}
	}

	private class PersistenceServiceIO extends LocalIO {
		private BasicService bs;
		private PersistenceService ps;

		private String initFailedMessage(String string) {
			return this.getClass().getName() + " initialization failed: "
					+ string;
		}

		PersistenceServiceIO() {
			try {
				this.bs = (BasicService) ServiceManager
						.lookup((String) "javax.jnlp.BasicService");
				this.ps = (PersistenceService) ServiceManager
						.lookup((String) "javax.jnlp.PersistenceService");
			} catch (UnavailableServiceException var2_2) {
				logger.log(Level.SEVERE,
						this.initFailedMessage("ServiceManager.lookup"),
						(Throwable) var2_2);
				this.bs = null;
				this.ps = null;
			}
		}

		private void checkBasics(String string) throws IOException {
			if (this.bs == null || this.ps == null) {
				throw new IOException(this.initFailedMessage(string));
			}
		}

		private URL fileNameToURL(String string) throws IOException {
			try {
				return new URL(this.bs.getCodeBase(), string);
			} catch (MalformedURLException var2_2) {
				throw new LSException("invalid filename \"" + string + "\"",
						var2_2);
			}
		}

		public InputStream openInputFile(String string) throws IOException {
			this.checkBasics("openInputFile");
			URL uRL = this.fileNameToURL(string);
			try {
				return new BufferedInputStream(this.ps.get(uRL)
						.getInputStream());
			} catch (Exception var3_3) {
				throw new LSException(
						"openInputFile \"" + string + "\" failed", var3_3);
			}
		}

		public OutputStream openOutputFile(String string) throws IOException {
			this.checkBasics("openOutputFile");
			URL uRL = this.fileNameToURL(string);
			try {
				FileContents fileContents;
				block5: {
					fileContents = null;
					try {
						fileContents = this.ps.get(uRL);
					} catch (FileNotFoundException var4_5) {
						long l = 131072;
						long l2 = this.ps.create(uRL, l);
						if (l2 < l)
							break block5;
						fileContents = this.ps.get(uRL);
					}
				}
				if (fileContents != null && fileContents.canWrite()) {
					return new BufferedOutputStream(
							fileContents.getOutputStream(true));
				}
				throw new IOException("unable to create FileContents object");
			} catch (Exception var3_4) {
				throw new LSException("openOutputFile \"" + string
						+ "\" failed", var3_4);
			}
		}

		public boolean deleteFile(String string) throws IOException {
			this.checkBasics("deleteFile");
			URL uRL = this.fileNameToURL(string);
			try {
				this.ps.delete(uRL);
				return true;
			} catch (Exception var3_3) {
				throw new LSException(
						"openInputFile \"" + string + "\" failed", var3_3);
			}
		}
	}

	private static class RectanglePD extends DefaultPersistenceDelegate {
		public RectanglePD() {
			super(new String[] { "x", "y", "width", "height" });
		}

		protected Expression instantiate(Object object, Encoder encoder) {
			Rectangle rectangle = (Rectangle) object;
			Object[] arrobject = new Object[] { rectangle.x, rectangle.y,
					rectangle.width, rectangle.height };
			return new Expression(object, object.getClass(), "new", arrobject);
		}
	}

}
