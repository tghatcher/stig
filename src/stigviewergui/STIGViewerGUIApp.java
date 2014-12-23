/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui;

import java.awt.Window;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.View;
import stigviewergui.STIGViewerGUIView;

public class STIGViewerGUIApp extends SingleFrameApplication {
	protected void startup() {
		this.show(new STIGViewerGUIView(this));
	}

	protected void configureWindow(Window root) {
	}

	public static STIGViewerGUIApp getApplication() {
		return (STIGViewerGUIApp) Application
				.getInstance(STIGViewerGUIApp.class);
	}

	public static void main(String[] args) {
		STIGViewerGUIApp.launch(STIGViewerGUIApp.class, args);
	}
}
