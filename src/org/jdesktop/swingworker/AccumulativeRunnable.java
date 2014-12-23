/*
 * Decompiled with CFR 0_92.
 */
package org.jdesktop.swingworker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.SwingUtilities;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
abstract class AccumulativeRunnable<T> implements Runnable {
	private List<T> arguments = null;

	AccumulativeRunnable() {
	}

	protected abstract void run(List<T> var1);

	@Override
	public final void run() {
		this.run(this.flush());
	}

	public final synchronized/* varargs */void add(T... arrT) {
		boolean bl = true;
		if (this.arguments == null) {
			bl = false;
			this.arguments = new ArrayList<T>();
		}
		Collections.addAll(this.arguments, arrT);
		if (!bl) {
			this.submit();
		}
	}

	protected void submit() {
		SwingUtilities.invokeLater(this);
	}

	private final synchronized List<T> flush() {
		List<T> list = this.arguments;
		this.arguments = null;
		return list;
	}
}
