/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.SV_CORE;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.JFrame;
import stigviewergui.SV_CORE.ProgressBar;

public class ProgressUpdater implements Runnable {
	ProgressBar myBar;

	public ProgressUpdater(JFrame Parent, String sTitle) {
		this.myBar = new ProgressBar(Parent, false, sTitle);
		if (Parent.getX() > 0 && Parent.getY() > 0) {
			this.myBar
					.setLocation(
							Parent.getX()
									+ (Parent.getWidth() - this.myBar
											.getWidth()) / 2,
							Parent.getY()
									+ (Parent.getHeight() - this.myBar
											.getHeight()) / 2);
		} else {
			Toolkit tk = Toolkit.getDefaultToolkit();
			Dimension dim = tk.getScreenSize();
			this.myBar
					.setLocation((int) (dim.getWidth() - (double) this.myBar
							.getWidth()) / 2,
							(int) (dim.getHeight() - (double) this.myBar
									.getHeight()) / 2);
		}
	}

	public void SetValue(int i) {
		this.myBar.SetBar(i);
	}

	public void SetMessage(String s) {
		this.myBar.SetMessage(s);
	}

	public void close() {
		this.myBar.setVisible(false);
		this.myBar.dispose();
	}

	public void run() {
		this.myBar.setVisible(true);
	}
}
