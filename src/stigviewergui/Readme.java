/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.Caret;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.ResourceMap;
import stigviewergui.STIGViewerGUIApp;
import stigviewergui.SV_CORE.Util;

public class Readme extends JFrame {
	private JButton jButton1;
	private JEditorPane jEditorPane1;
	private JPanel jPanel1;
	private JScrollPane jScrollPane1;

	public Readme(String sTitle) {
		this.initComponents();
		Util.SetWindowHeader(sTitle, this);
	}

	public void SetText(String s) {
		this.jEditorPane1.setText(s);
		this.jEditorPane1.getCaret().setDot(0);
	}

	private void initComponents() {
		this.jScrollPane1 = new JScrollPane();
		this.jEditorPane1 = new JEditorPane();
		this.jPanel1 = new JPanel();
		this.jButton1 = new JButton();
		this.setDefaultCloseOperation(2);
		this.setName("Form");
		this.jScrollPane1.setName("jScrollPane1");
		this.jEditorPane1.setEditable(false);
		ResourceMap resourceMap = ((STIGViewerGUIApp) Application
				.getInstance(STIGViewerGUIApp.class)).getContext()
				.getResourceMap(Readme.class);
		this.jEditorPane1.setFont(resourceMap.getFont("jEditorPane1.font"));
		this.jEditorPane1.setName("jEditorPane1");
		this.jEditorPane1.setPreferredSize(new Dimension(106, 400));
		this.jScrollPane1.setViewportView(this.jEditorPane1);
		this.getContentPane().add((Component) this.jScrollPane1, "Center");
		this.jPanel1.setName("jPanel1");
		this.jButton1.setText(resourceMap.getString("jButton1.text",
				new Object[0]));
		this.jButton1.setName("jButton1");
		this.jButton1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				Readme.this.jButton1ActionPerformed(evt);
			}
		});
		GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
		this.jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				GroupLayout.Alignment.TRAILING,
				jPanel1Layout.createSequentialGroup()
						.addContainerGap(574, 32767)
						.addComponent(this.jButton1)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(this.jButton1));
		this.getContentPane().add((Component) this.jPanel1, "Last");
		this.pack();
	}

	private void jButton1ActionPerformed(ActionEvent evt) {
		this.dispose();
	}

}
