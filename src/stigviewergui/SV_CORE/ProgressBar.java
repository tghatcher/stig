/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.SV_CORE;

import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.LayoutManager;
import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.ResourceMap;
import stigviewergui.STIGViewerGUIApp;
import stigviewergui.SV_CORE.Util;

public class ProgressBar extends JDialog {
	private JProgressBar bar;
	private JLabel lbl_Message;

	public ProgressBar(Frame parent, boolean modal, String sTitle) {
		super(parent, modal);
		Util.SetWindowHeader(sTitle, this);
		this.initComponents();
		this.bar.setMinimum(0);
		this.bar.setMaximum(100);
	}

	private void initComponents() {
		this.bar = new JProgressBar();
		this.lbl_Message = new JLabel();
		this.setDefaultCloseOperation(2);
		this.setName("Form");
		this.bar.setName("bar");
		this.lbl_Message.setHorizontalAlignment(0);
		ResourceMap resourceMap = ((STIGViewerGUIApp) Application
				.getInstance(STIGViewerGUIApp.class)).getContext()
				.getResourceMap(ProgressBar.class);
		this.lbl_Message.setText(resourceMap.getString("lbl_Message.text",
				new Object[0]));
		this.lbl_Message.setName("lbl_Message");
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addComponent(this.lbl_Message,
														-1, 209, 32767)
												.addComponent(this.bar, -1,
														209, 32767))
								.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(this.lbl_Message)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.bar, -2, -1, -2)
								.addContainerGap(-1, 32767)));
		this.pack();
	}

	public void SetBar(int i) {
		this.bar.setValue(i % 101);
		this.update(this.getGraphics());
	}

	public void SetMessage(String s) {
		this.lbl_Message.setText(s);
		this.update(this.getGraphics());
	}
}
