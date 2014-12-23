/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui;

import File_Interfaces.VMS_Keys;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.ResourceMap;
import stigviewergui.MVMSTargetSel_TableModel;
import stigviewergui.STIGViewerGUIApp;
import stigviewergui.STIGViewerGUIView;
import stigviewergui.SV_CORE.GTD_PostData;
import stigviewergui.SV_CORE.GetElementKey;
import stigviewergui.SV_CORE.Pair;
import stigviewergui.SV_CORE.Util;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class MultipleVMSTargetSelector extends JDialog implements GTD_PostData {
	private VMS_Keys myVMS_Keys;
	private MultipleVMSTargetSelector MySelf;
	private static String VMS_KEY_FILE = "";
	private ArrayList<Pair<String, String>> RefList;
	private Frame myParent;
	private JButton jButton1;
	private JEditorPane jEditorPane1;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JTable jTable1;

	public MultipleVMSTargetSelector(Frame parent, boolean modal,
			ArrayList<Pair<String, String>> StigList) {
		block6: {
			super(parent, modal);
			this.MySelf = this;
			this.myParent = parent;
			ResourceMap resMap = ((STIGViewerGUIApp) Application
					.getInstance(STIGViewerGUIApp.class)).getContext()
					.getResourceMap(STIGViewerGUIView.class);
			VMS_KEY_FILE = resMap.getString("Application.Element_File",
					new Object[0]);
			this.myVMS_Keys = new VMS_Keys();
			InputStream fIn = this.myVMS_Keys.getClass().getClassLoader()
					.getResourceAsStream(VMS_KEY_FILE);
			try {
				this.myVMS_Keys.open(fIn);
			} catch (Exception ex) {
				if (!Util.bAllowPrintln)
					break block6;
				ex.printStackTrace();
			}
		}
		this.initComponents();
		Util.SetWindowHeader("Asset Posture Selector", this);
		MVMSTargetSel_TableModel myTableModel = new MVMSTargetSel_TableModel();
		for (Pair<String, String> p : StigList) {
			String[] s;
			block7: {
				s = new String[] { p.getFirst(), "", p.getSecond() };
				if (!s[2].equals("")) {
					try {
						s[1] = this.myVMS_Keys.GetKeyName(s[2]);
					} catch (Exception e) {
						if (!Util.bAllowPrintln)
							break block7;
						e.printStackTrace();
					}
				}
			}
			myTableModel.addRow(s);
		}
		this.jTable1.setModel(myTableModel);
		this.SetCustomMouseDoubleClickHandler();
	}

	@Override
	public void PostData(String[] sIn) {
	}

	@Override
	public void PostKey(String sAsset) {
		String sKey = this.myVMS_Keys.GetKeyVal(sAsset);
		((MVMSTargetSel_TableModel) this.jTable1.getModel()).setValueAt(sAsset,
				1, this.jTable1.getSelectedRow());
		((MVMSTargetSel_TableModel) this.jTable1.getModel()).setValueAt(sKey,
				2, this.jTable1.getSelectedRow());
		this.jTable1.update(this.jTable1.getGraphics());
	}

	private void SetCustomMouseDoubleClickHandler() {
		this.jTable1.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					GetElementKey KeyGetter = new GetElementKey(
							MultipleVMSTargetSelector.this.myParent, true,
							MultipleVMSTargetSelector.this.MySelf,
							MultipleVMSTargetSelector.this.myVMS_Keys);
					KeyGetter.setLocation(
							MultipleVMSTargetSelector.this.MySelf.getX() + 50,
							MultipleVMSTargetSelector.this.MySelf.getY() + 50);
					KeyGetter.setVisible(true);
				}
			}
		});
	}

	public ArrayList<Pair<String, String>> GetListing() {
		ArrayList<Pair<String, String>> Ret = new ArrayList<Pair<String, String>>();
		for (int i = 0; i < this.jTable1.getRowCount(); ++i) {
			Pair<Object, Object> Pss = new Pair<Object, Object>(
					this.jTable1.getValueAt(i, 0),
					this.jTable1.getValueAt(i, 2));
			Ret.add(Pss);
		}
		return Ret;
	}

	private void initComponents() {
		this.jScrollPane1 = new JScrollPane();
		this.jTable1 = new JTable();
		this.jScrollPane2 = new JScrollPane();
		this.jEditorPane1 = new JEditorPane();
		this.jButton1 = new JButton();
		this.setDefaultCloseOperation(2);
		this.setName("Form");
		this.jScrollPane1.setName("jScrollPane1");
		this.jTable1.setModel(new DefaultTableModel(new Object[][] {
				{ null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null } },
				new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		this.jTable1.setName("jTable1");
		this.jScrollPane1.setViewportView(this.jTable1);
		this.jScrollPane2.setName("jScrollPane2");
		this.jEditorPane1.setContentType("text/html");
		this.jEditorPane1.setEditable(false);
		ResourceMap resourceMap = ((STIGViewerGUIApp) Application
				.getInstance(STIGViewerGUIApp.class)).getContext()
				.getResourceMap(MultipleVMSTargetSelector.class);
		this.jEditorPane1.setText(resourceMap.getString("jEditorPane1.text",
				new Object[0]));
		this.jEditorPane1.setName("jEditorPane1");
		this.jScrollPane2.setViewportView(this.jEditorPane1);
		this.jButton1.setText(resourceMap.getString("jButton1.text",
				new Object[0]));
		this.jButton1.setName("jButton1");
		this.jButton1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				MultipleVMSTargetSelector.this.jButton1ActionPerformed(evt);
			}
		});
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(this.jButton1, GroupLayout.Alignment.TRAILING,
						-1, 357, 32767)
				.addComponent(this.jScrollPane2,
						GroupLayout.Alignment.TRAILING, -1, 357, 32767)
				.addComponent(this.jScrollPane1, -1, 357, 32767));
		layout.setVerticalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addComponent(this.jScrollPane2, -1, 108, 32767)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.jScrollPane1, -2, 270, -2)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.jButton1)));
		this.pack();
	}

	private void jButton1ActionPerformed(ActionEvent evt) {
		this.dispose();
	}

}
