/*
 * Decompiled with CFR 0_92.
 */
package ModularCheckList;

import ModularCheckList.SimpleVulnList_I;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.ListModel;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.ResourceMap;
import stigviewergui.STIGSearch;
import stigviewergui.STIGViewerGUIApp;
import stigviewergui.SV_CORE.Util;
import stigviewergui.SV_CORE.Vuln;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SimpleVulnList extends JFrame {
	ArrayList<Vuln> myVList;
	String sMyMessage;
	SimpleVulnList_I myInterface;
	STIGSearch.SortType mySortType;
	private JButton CloseButton;
	private JEditorPane MessageArea;
	private JMenuItem PopUp_Select;
	private JButton UpdateButton;
	private JList VList;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JPopupMenu jPopup_Select;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;

	public SimpleVulnList(ArrayList<Vuln> vList, ArrayList<String> DataList,
			STIGSearch.SortType st, String sMessageText,
			SimpleVulnList_I Interface, boolean bShowUpdateButton) {
		this.myVList = vList;
		this.sMyMessage = sMessageText;
		this.myInterface = Interface;
		this.mySortType = st;
		this.initComponents();
		this.UpdateButton.setVisible(bShowUpdateButton);
		Util.SetWindowHeader("Vuln List", this);
		this.setAlwaysOnTop(true);
		this.MessageArea.setText(this.sMyMessage);
		DefaultListModel<String> mod = new DefaultListModel<String>();
		this.VList.setModel(mod);
		for (Vuln v : vList) {
			String sEntry = "";
			if (st.equals((Object) STIGSearch.SortType.VULID)) {
				sEntry = sEntry + v.getAttr(Vuln.VulnAttr.Vuln_Num);
			} else if (st.equals((Object) STIGSearch.SortType.RULEID)) {
				sEntry = sEntry + v.getAttr(Vuln.VulnAttr.Rule_ID);
			} else if (st.equals((Object) STIGSearch.SortType.STIGID)) {
				sEntry = sEntry + v.getAttr(Vuln.VulnAttr.Rule_Ver);
			} else if (st.equals((Object) STIGSearch.SortType.CCIID)) {
				sEntry = v.GetCCIVal(0).equals("") ? sEntry + "[No CCI Ref]"
						: sEntry + v.GetCCIVal(0);
			}
			if (DataList != null) {
				sEntry = sEntry + " : " + DataList.get(vList.indexOf(v));
			}
			mod.addElement(sEntry);
		}
		this.setVisible(true);
	}

	private void initComponents() {
		this.jPopup_Select = new JPopupMenu();
		this.PopUp_Select = new JMenuItem();
		this.jPanel1 = new JPanel();
		this.jScrollPane2 = new JScrollPane();
		this.VList = new JList();
		this.jScrollPane1 = new JScrollPane();
		this.MessageArea = new JEditorPane();
		this.jPanel2 = new JPanel();
		this.CloseButton = new JButton();
		this.UpdateButton = new JButton();
		this.jPopup_Select.setComponentPopupMenu(this.jPopup_Select);
		this.jPopup_Select.setCursor(new Cursor(0));
		this.jPopup_Select.setName("jPopup_Select");
		ResourceMap resourceMap = ((STIGViewerGUIApp) Application
				.getInstance(STIGViewerGUIApp.class)).getContext()
				.getResourceMap(SimpleVulnList.class);
		this.PopUp_Select.setText(resourceMap.getString("PopUp_Select.text",
				new Object[0]));
		this.PopUp_Select.setName("PopUp_Select");
		this.PopUp_Select.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				SimpleVulnList.this.PopUp_SelectActionPerformed(evt);
			}
		});
		this.jPopup_Select.add(this.PopUp_Select);
		this.setDefaultCloseOperation(2);
		this.setName("Form");
		this.jPanel1.setName("jPanel1");
		this.jScrollPane2.setName("jScrollPane2");
		this.VList.setSelectionMode(0);
		this.VList.setComponentPopupMenu(this.jPopup_Select);
		this.VList.setName("VList");
		this.jScrollPane2.setViewportView(this.VList);
		this.jScrollPane1.setName("jScrollPane1");
		this.MessageArea.setContentType("text/html");
		this.MessageArea.setName("MessageArea");
		this.jScrollPane1.setViewportView(this.MessageArea);
		GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
		this.jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(this.jScrollPane2,
						GroupLayout.Alignment.TRAILING, -1, 189, 32767)
				.addComponent(this.jScrollPane1, -1, 189, 32767));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addComponent(this.jScrollPane1, -2,
												96, -2)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(this.jScrollPane2, -1,
												348, 32767)));
		this.getContentPane().add((Component) this.jPanel1, "Center");
		this.jPanel2.setName("jPanel2");
		this.jPanel2.setLayout(new BorderLayout());
		this.CloseButton.setText(resourceMap.getString("CloseButton.text",
				new Object[0]));
		this.CloseButton.setName("CloseButton");
		this.CloseButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				SimpleVulnList.this.CloseButtonActionPerformed(evt);
			}
		});
		this.jPanel2.add((Component) this.CloseButton, "East");
		this.UpdateButton.setText(resourceMap.getString("UpdateButton.text",
				new Object[0]));
		this.UpdateButton.setName("UpdateButton");
		this.UpdateButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				SimpleVulnList.this.UpdateButtonActionPerformed(evt);
			}
		});
		this.jPanel2.add((Component) this.UpdateButton, "West");
		this.getContentPane().add((Component) this.jPanel2, "South");
		this.pack();
	}

	private void CloseButtonActionPerformed(ActionEvent evt) {
		this.dispose();
	}

	private void UpdateButtonActionPerformed(ActionEvent evt) {
		DefaultListModel<String> mod = new DefaultListModel<String>();
		this.VList.setModel(mod);
		ArrayList<Vuln> va = this.myInterface.SVL_Update();
		for (Vuln v : va) {
			mod.addElement(v.getAttr(Vuln.VulnAttr.Vuln_Num));
		}
	}

	private void PopUp_SelectActionPerformed(ActionEvent evt) {
		this.myInterface.SVL_Select(this.myVList.get(this.VList
				.getSelectedIndex()));
	}

}
