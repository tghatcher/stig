/*
 * Decompiled with CFR 0_92.
 */
package ModularCheckList;

import ModularCheckList.Sev_Ovr_Interface;
import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.border.Border;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.ResourceMap;
import stigviewergui.STIGViewerGUIApp;
import stigviewergui.SV_CORE.Util;

public class Sev_Ovr_Jst extends JFrame {
	boolean bConfirmed;
	Sev_Ovr_Interface myPoster;
	private JButton btn_Cancel;
	private JButton btn_Confirm;
	private JLabel jLabel1;
	private JLabel jLabel3;
	private JPanel jPanel1;
	private JScrollPane jScrollPane1;
	private JLabel lbl_Sev;
	private JEditorPane txt_Justification;

	public Sev_Ovr_Jst() {
		this.initComponents();
		Util.SetWindowHeader("Severity Override", this);
		this.bConfirmed = false;
	}

	public Sev_Ovr_Jst(Sev_Ovr_Interface Poster, String sInit_Text) {
		this.myPoster = Poster;
		Util.SetWindowHeader("Severity Override", this);
		this.initComponents();
		this.txt_Justification.setText(sInit_Text);
		this.bConfirmed = false;
	}

	public void SetSevDisp(String s) {
		this.lbl_Sev.setText(s);
	}

	private void initComponents() {
		this.jLabel1 = new JLabel();
		this.lbl_Sev = new JLabel();
		this.jPanel1 = new JPanel();
		this.jLabel3 = new JLabel();
		this.jScrollPane1 = new JScrollPane();
		this.txt_Justification = new JEditorPane();
		this.btn_Confirm = new JButton();
		this.btn_Cancel = new JButton();
		this.setDefaultCloseOperation(2);
		this.setName("Form");
		ResourceMap resourceMap = ((STIGViewerGUIApp) Application
				.getInstance(STIGViewerGUIApp.class)).getContext()
				.getResourceMap(Sev_Ovr_Jst.class);
		this.jLabel1.setText(resourceMap.getString("jLabel1.text",
				new Object[0]));
		this.jLabel1.setName("jLabel1");
		this.lbl_Sev.setText(resourceMap.getString("lbl_Sev.text",
				new Object[0]));
		this.lbl_Sev.setName("lbl_Sev");
		this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
		this.jPanel1.setName("jPanel1");
		this.jLabel3.setText(resourceMap.getString("jLabel3.text",
				new Object[0]));
		this.jLabel3.setName("jLabel3");
		this.jScrollPane1.setName("jScrollPane1");
		this.txt_Justification.setName("txt_Justification");
		this.jScrollPane1.setViewportView(this.txt_Justification);
		GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
		this.jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																this.jLabel3)
														.addGroup(
																GroupLayout.Alignment.TRAILING,
																jPanel1Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				this.jScrollPane1,
																				-1,
																				321,
																				32767)))
										.addContainerGap()));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addComponent(this.jLabel3)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(this.jScrollPane1, -1,
												191, 32767).addContainerGap()));
		this.btn_Confirm.setText(resourceMap.getString("btn_Confirm.text",
				new Object[0]));
		this.btn_Confirm.setName("btn_Confirm");
		this.btn_Confirm.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				Sev_Ovr_Jst.this.btn_ConfirmActionPerformed(evt);
			}
		});
		this.btn_Cancel.setText(resourceMap.getString("btn_Cancel.text",
				new Object[0]));
		this.btn_Cancel.setName("btn_Cancel");
		this.btn_Cancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				Sev_Ovr_Jst.this.btn_CancelActionPerformed(evt);
			}
		});
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		this.jLabel1)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		this.lbl_Sev))
												.addGroup(
														layout.createSequentialGroup()
																.addContainerGap()
																.addComponent(
																		this.jPanel1,
																		-2, -1,
																		32767))
												.addGroup(
														layout.createSequentialGroup()
																.addContainerGap()
																.addComponent(
																		this.btn_Confirm)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED,
																		211,
																		32767)
																.addComponent(
																		this.btn_Cancel)))
								.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
												.addComponent(this.jLabel1)
												.addComponent(this.lbl_Sev))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.jPanel1, -2, -1, 32767)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
												.addComponent(this.btn_Confirm)
												.addComponent(this.btn_Cancel))
								.addGap(11, 11, 11)));
		this.pack();
	}

	private void btn_ConfirmActionPerformed(ActionEvent evt) {
		if (!this.txt_Justification.getText().equals("")) {
			this.myPoster.Post_Override(this.txt_Justification.getText());
			this.dispose();
		} else if (Util.bAllowPrintln) {
			System.out.println("CANNOT HAVE NOTHING!!!");
		}
	}

	private void btn_CancelActionPerformed(ActionEvent evt) {
		this.myPoster.Post_Override("");
		this.dispose();
	}

}
