/*
 * Decompiled with CFR 0_92.
 */
package org.jdesktop.application;

import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.View;

public class FrameView extends View {
	private static final Logger logger = Logger.getLogger(FrameView.class
			.getName());
	private JFrame frame = null;

	public FrameView(Application application) {
		super(application);
	}

	public JFrame getFrame() {
		if (this.frame == null) {
			String string = this.getContext().getResourceMap()
					.getString("Application.title", new Object[0]);
			this.frame = new JFrame(string);
			this.frame.setName("mainFrame");
		}
		return this.frame;
	}

	public void setFrame(JFrame jFrame) {
		if (jFrame == null) {
			throw new IllegalArgumentException("null JFrame");
		}
		if (this.frame != null) {
			throw new IllegalStateException("frame already set");
		}
		this.frame = jFrame;
		this.firePropertyChange("frame", null, this.frame);
	}

	public JRootPane getRootPane() {
		return this.getFrame().getRootPane();
	}
}
