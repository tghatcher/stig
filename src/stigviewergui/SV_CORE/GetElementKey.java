/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.SV_CORE;

import File_Interfaces.VMS_Keys;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListModel;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.ResourceMap;
import stigviewergui.STIGViewerGUIApp;
import stigviewergui.SV_CORE.GTD_PostData;
import stigviewergui.SV_CORE.Util;

public class GetElementKey extends JDialog {
	GTD_PostData myPoster;
	VMS_Keys myKeys;
	DefaultListModel dlm;
	ArrayList<VMS_Keys.VMS_KeyStore> KS;
	GetElementKey Me;
	private JTextField SearchField;
	private JButton btn_Cancel;
	private JButton btn_Select;
	private JLabel jLabel1;
	private JList jList1;
	private JScrollPane jScrollPane1;

	public GetElementKey(Frame parent, boolean modal) {
		super(parent, modal);
		this.Me = this;
		this.initComponents();
		Util.SetWindowHeader("Element Keys", this);
	}

	public GetElementKey(Frame parent, boolean modal, GTD_PostData Poster,
			VMS_Keys Keys) {
		super(parent, modal);
		this.Me = this;
		this.myPoster = Poster;
		this.myKeys = Keys;
		this.dlm = new DefaultListModel();
		this.initComponents();
		this.SetCustomMouseDoubleClickHandler();
		this.KS = this.myKeys.LiveSearch(this.SearchField.getText());
		for (int i = 0; i < this.KS.size(); ++i) {
			this.dlm.add(this.dlm.getSize(), this.KS.get((int) i).sDesc);
		}
		this.jList1.setModel(this.dlm);
		Util.SetWindowHeader("Element Keys", this);
	}

	private void SetCustomMouseDoubleClickHandler() {
		this.jList1.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JList target = (JList) e.getSource();
					int row = target.getSelectedIndex();
					GetElementKey.this.Me.myPoster
							.PostKey((String) GetElementKey.this.Me.jList1
									.getSelectedValue());
					GetElementKey.this.Me.dispose();
				}
			}
		});
	}

	private void initComponents() {
		this.SearchField = new JTextField();
		this.jScrollPane1 = new JScrollPane();
		this.jList1 = new JList();
		this.btn_Select = new JButton();
		this.btn_Cancel = new JButton();
		this.jLabel1 = new JLabel();
		this.setDefaultCloseOperation(2);
		this.setName("Form");
		this.setResizable(false);
		ResourceMap resourceMap = ((STIGViewerGUIApp) Application
				.getInstance(STIGViewerGUIApp.class)).getContext()
				.getResourceMap(GetElementKey.class);
		this.SearchField.setText(resourceMap.getString("SearchField.text",
				new Object[0]));
		this.SearchField.setName("SearchField");
		this.SearchField.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent evt) {
				GetElementKey.this.SearchFieldKeyReleased(evt);
			}
		});
		this.jScrollPane1.setName("jScrollPane1");
		this.jList1.setSelectionMode(0);
		this.jList1.setName("jList1");
		this.jScrollPane1.setViewportView(this.jList1);
		this.btn_Select.setText(resourceMap.getString("btn_Select.text",
				new Object[0]));
		this.btn_Select.setName("btn_Select");
		this.btn_Select.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				GetElementKey.this.btn_SelectActionPerformed(evt);
			}
		});
		this.btn_Cancel.setText(resourceMap.getString("btn_Cancel.text",
				new Object[0]));
		this.btn_Cancel.setName("btn_Cancel");
		this.btn_Cancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				GetElementKey.this.btn_CancelActionPerformed(evt);
			}
		});
		this.jLabel1.setText(resourceMap.getString("jLabel1.text",
				new Object[0]));
		this.jLabel1.setName("jLabel1");
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addComponent(
														this.jScrollPane1, -1,
														229, 32767)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		this.btn_Select)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED,
																		103,
																		32767)
																.addComponent(
																		this.btn_Cancel))
												.addComponent(this.SearchField,
														-1, 229, 32767)
												.addComponent(this.jLabel1, -1,
														229, 32767))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(this.jLabel1)
								.addGap(9, 9, 9)
								.addComponent(this.SearchField, -2, -1, -2)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.jScrollPane1, -1, 398, 32767)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
												.addComponent(this.btn_Select)
												.addComponent(this.btn_Cancel))
								.addContainerGap()));
		this.pack();
	}

	private void SearchFieldKeyReleased(KeyEvent evt) {
		this.KS.clear();
		this.KS = this.myKeys.LiveSearch(this.SearchField.getText());
		this.dlm.clear();
		for (int i = 0; i < this.KS.size(); ++i) {
			this.dlm.add(this.dlm.getSize(), this.KS.get((int) i).sDesc);
		}
	}

	private void btn_SelectActionPerformed(ActionEvent evt) {
		this.myPoster.PostKey((String) this.jList1.getSelectedValue());
		this.dispose();
	}

	private void btn_CancelActionPerformed(ActionEvent evt) {
		this.dispose();
	}

}
