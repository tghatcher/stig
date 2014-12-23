/*
 * Decompiled with CFR 0_92.
 */
package ModularCheckList;

import File_Interfaces.CCIReader;
import ModularCheckList.ModCL_Checklist_State;
import ModularCheckList.Sev_Ovr_Interface;
import ModularCheckList.Sev_Ovr_Jst;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.LayoutStyle;
import javax.swing.border.Border;
import javax.swing.text.Caret;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.ResourceMap;
import stigviewergui.STIGSearch;
import stigviewergui.STIGViewerConfig;
import stigviewergui.STIGViewerGUIApp;
import stigviewergui.SV_CORE.SV_Checklist;
import stigviewergui.SV_CORE.Util;
import stigviewergui.SV_CORE.Vuln;
import stigviewergui.SV_CORE.VulnStringGen;

public class ModCL_Card_Checklist extends JPanel implements Sev_Ovr_Interface {
	ModCL_Checklist_State StateEvent;
	SV_Checklist myChecklist;
	STIGViewerConfig myConfig;
	private ClipboardOwner CBO;
	int iSelectedRef;
	Vuln SelectedVul;
	CCIReader myCCI;
	VulnStringGen VulnDisplay;
	private JPopupMenu CommentsPasteMenu;
	private JPopupMenu CopyMenu;
	private JPopupMenu DetailsPasteMenu;
	private JMenuItem H_pop_copy;
	private JPopupMenu HeaderCopyMenu;
	private JComboBox Sev_Override;
	private JComboBox jComboBox1;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JPanel jPanel3;
	private JPanel jPanel4;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane3;
	private JScrollPane jScrollPane4;
	private JSplitPane jSplitPane1;
	private JSplitPane jSplitPane2;
	private JSplitPane jSplitPane3;
	private JMenuItem pop_Copy;
	private JMenuItem pop_Copy_com;
	private JMenuItem pop_Copy_det;
	private JMenuItem pop_Paste_com;
	private JMenuItem pop_Paste_det;
	private JEditorPane txt_Body;
	private JEditorPane txt_Comments;
	private JEditorPane txt_Header;
	private JEditorPane txt_Notes;

	public ModCL_Card_Checklist(SV_Checklist Checklist,
			STIGViewerConfig Config, ModCL_Checklist_State StateHandler,
			CCIReader CCI, STIGSearch Searcher) {
		this.myCCI = CCI;
		this.initComponents();
		this.StateEvent = StateHandler;
		this.myChecklist = Checklist;
		this.myConfig = Config;
		this.iSelectedRef = -1;
		this.VulnDisplay = new VulnStringGen(Searcher, this.myConfig);
		this.CBO = new ClipboardOwner() {

			public void lostOwnership(Clipboard clipboard, Transferable contents) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		};
	}

	public void Post_Override(String s) {
		if (!s.equals("")) {
			this.myChecklist.setSevOverride(this.iSelectedRef,
					(String) this.Sev_Override.getSelectedItem());
			this.myChecklist.setSevJustification(this.iSelectedRef, s);
		} else {
			this.Sev_Override.setSelectedIndex(0);
		}
		this.StateEvent.UpdateVulnColor(this.iSelectedRef);
	}

	private void initComponents() {
		this.HeaderCopyMenu = new JPopupMenu();
		this.H_pop_copy = new JMenuItem();
		this.CopyMenu = new JPopupMenu();
		this.pop_Copy = new JMenuItem();
		this.DetailsPasteMenu = new JPopupMenu();
		this.pop_Copy_det = new JMenuItem();
		this.pop_Paste_det = new JMenuItem();
		this.CommentsPasteMenu = new JPopupMenu();
		this.pop_Copy_com = new JMenuItem();
		this.pop_Paste_com = new JMenuItem();
		this.jSplitPane3 = new JSplitPane();
		this.jPanel4 = new JPanel();
		this.jComboBox1 = new JComboBox();
		this.Sev_Override = new JComboBox();
		this.jSplitPane1 = new JSplitPane();
		this.jScrollPane1 = new JScrollPane();
		this.txt_Header = new JEditorPane();
		this.jScrollPane2 = new JScrollPane();
		this.txt_Body = new JEditorPane();
		this.jLabel2 = new JLabel();
		this.jPanel1 = new JPanel();
		this.jSplitPane2 = new JSplitPane();
		this.jPanel2 = new JPanel();
		this.jLabel1 = new JLabel();
		this.jScrollPane3 = new JScrollPane();
		this.txt_Notes = new JEditorPane();
		this.jPanel3 = new JPanel();
		this.jScrollPane4 = new JScrollPane();
		this.txt_Comments = new JEditorPane();
		this.jLabel3 = new JLabel();
		this.HeaderCopyMenu.setName("HeaderCopyMenu");
		ResourceMap resourceMap = ((STIGViewerGUIApp) Application
				.getInstance(STIGViewerGUIApp.class)).getContext()
				.getResourceMap(ModCL_Card_Checklist.class);
		this.H_pop_copy.setText(resourceMap.getString("H_pop_copy.text",
				new Object[0]));
		this.H_pop_copy.setName("H_pop_copy");
		this.H_pop_copy.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL_Card_Checklist.this.H_pop_copyActionPerformed(evt);
			}
		});
		this.HeaderCopyMenu.add(this.H_pop_copy);
		this.CopyMenu.setName("CopyMenu");
		this.pop_Copy.setText(resourceMap.getString("pop_Copy.text",
				new Object[0]));
		this.pop_Copy.setName("pop_Copy");
		this.pop_Copy.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL_Card_Checklist.this.pop_CopyActionPerformed(evt);
			}
		});
		this.CopyMenu.add(this.pop_Copy);
		this.DetailsPasteMenu.setName("DetailsPasteMenu");
		this.pop_Copy_det.setText(resourceMap.getString("pop_Copy_det.text",
				new Object[0]));
		this.pop_Copy_det.setName("pop_Copy_det");
		this.pop_Copy_det.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL_Card_Checklist.this.pop_Copy_detActionPerformed(evt);
			}
		});
		this.DetailsPasteMenu.add(this.pop_Copy_det);
		this.pop_Paste_det.setText(resourceMap.getString("pop_Paste_det.text",
				new Object[0]));
		this.pop_Paste_det.setName("pop_Paste_det");
		this.pop_Paste_det.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL_Card_Checklist.this.pop_Paste_detActionPerformed(evt);
			}
		});
		this.DetailsPasteMenu.add(this.pop_Paste_det);
		this.CommentsPasteMenu.setName("CommentsPasteMenu");
		this.pop_Copy_com.setText(resourceMap.getString("pop_Copy_com.text",
				new Object[0]));
		this.pop_Copy_com.setName("pop_Copy_com");
		this.pop_Copy_com.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL_Card_Checklist.this.pop_Copy_comActionPerformed(evt);
			}
		});
		this.CommentsPasteMenu.add(this.pop_Copy_com);
		this.pop_Paste_com.setText(resourceMap.getString("pop_Paste_com.text",
				new Object[0]));
		this.pop_Paste_com.setName("pop_Paste_com");
		this.pop_Paste_com.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL_Card_Checklist.this.pop_Paste_comActionPerformed(evt);
			}
		});
		this.CommentsPasteMenu.add(this.pop_Paste_com);
		this.setName("Form");
		this.jSplitPane3.setOrientation(0);
		this.jSplitPane3.setResizeWeight(0.7);
		this.jSplitPane3.setName("jSplitPane3");
		this.jPanel4.setName("jPanel4");
		this.jComboBox1.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Not Reviewed", "Open", "Not a Finding", "Not Applicable" }));
		this.jComboBox1.setName("jComboBox1");
		this.jComboBox1.addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent evt) {
				ModCL_Card_Checklist.this.jComboBox1MouseReleased(evt);
			}
		});
		this.jComboBox1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL_Card_Checklist.this.jComboBox1ActionPerformed(evt);
			}
		});
		this.Sev_Override.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Severity Override", "CAT I", "CAT II",
						"CAT III" }));
		this.Sev_Override.setName("Sev_Override");
		this.Sev_Override.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL_Card_Checklist.this.Sev_OverrideActionPerformed(evt);
			}
		});
		this.jSplitPane1.setDividerLocation(80);
		this.jSplitPane1.setOrientation(0);
		this.jSplitPane1.setName("jSplitPane1");
		this.jScrollPane1.setName("jScrollPane1");
		this.txt_Header.setContentType("text/rtf");
		this.txt_Header.setEditable(false);
		this.txt_Header.setComponentPopupMenu(this.HeaderCopyMenu);
		this.txt_Header.setName("txt_Header");
		this.jScrollPane1.setViewportView(this.txt_Header);
		this.jSplitPane1.setLeftComponent(this.jScrollPane1);
		this.jScrollPane2.setName("jScrollPane2");
		this.txt_Body.setContentType("text/rtf");
		this.txt_Body.setEditable(false);
		this.txt_Body.setComponentPopupMenu(this.CopyMenu);
		this.txt_Body.setName("txt_Body");
		this.jScrollPane2.setViewportView(this.txt_Body);
		this.jSplitPane1.setRightComponent(this.jScrollPane2);
		this.jLabel2.setText(resourceMap.getString("jLabel2.text",
				new Object[0]));
		this.jLabel2.setName("jLabel2");
		GroupLayout jPanel4Layout = new GroupLayout(this.jPanel4);
		this.jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel4Layout
								.createSequentialGroup()
								.addGap(4, 4, 4)
								.addComponent(this.jLabel2)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.jComboBox1, -2, 116, -2)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED,
										1470, 32767)
								.addComponent(this.Sev_Override, -2, -1, -2))
				.addComponent(this.jSplitPane1, -1, 1739, 32767));
		jPanel4Layout
				.setVerticalGroup(jPanel4Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel4Layout
										.createSequentialGroup()
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																this.jLabel2)
														.addComponent(
																this.jComboBox1,
																-2, -1, -2)
														.addComponent(
																this.Sev_Override,
																-2, -1, -2))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(this.jSplitPane1, -1,
												308, 32767)));
		this.jSplitPane3.setLeftComponent(this.jPanel4);
		this.jPanel1.setName("jPanel1");
		this.jSplitPane2.setBorder(BorderFactory.createEtchedBorder());
		this.jSplitPane2.setOrientation(0);
		this.jSplitPane2.setResizeWeight(0.5);
		this.jSplitPane2.setName("jSplitPane2");
		this.jPanel2.setName("jPanel2");
		this.jLabel1.setText(resourceMap.getString("jLabel1.text",
				new Object[0]));
		this.jLabel1.setName("jLabel1");
		this.jScrollPane3.setName("jScrollPane3");
		this.txt_Notes.setComponentPopupMenu(this.DetailsPasteMenu);
		this.txt_Notes.setName("txt_Notes");
		this.txt_Notes.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				ModCL_Card_Checklist.this.txt_NotesFocusLost(evt);
			}
		});
		this.txt_Notes.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent evt) {
				ModCL_Card_Checklist.this.txt_NotesKeyPressed(evt);
			}
		});
		this.jScrollPane3.setViewportView(this.txt_Notes);
		GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
		this.jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel2Layout.createSequentialGroup()
								.addComponent(this.jLabel1)
								.addContainerGap(1662, 32767))
				.addComponent(this.jScrollPane3));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addComponent(this.jLabel1)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(this.jScrollPane3, -1,
												68, 32767)));
		this.jSplitPane2.setLeftComponent(this.jPanel2);
		this.jPanel3.setName("jPanel3");
		this.jScrollPane4.setName("jScrollPane4");
		this.txt_Comments.setComponentPopupMenu(this.CommentsPasteMenu);
		this.txt_Comments.setName("txt_Comments");
		this.txt_Comments.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				ModCL_Card_Checklist.this.txt_CommentsFocusLost(evt);
			}
		});
		this.txt_Comments.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent evt) {
				ModCL_Card_Checklist.this.txt_CommentsKeyPressed(evt);
			}
		});
		this.jScrollPane4.setViewportView(this.txt_Comments);
		this.jLabel3.setText(resourceMap.getString("jLabel3.text",
				new Object[0]));
		this.jLabel3.setName("jLabel3");
		GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
		this.jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout
				.setHorizontalGroup(jPanel3Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel3Layout.createSequentialGroup()
										.addComponent(this.jLabel3)
										.addContainerGap())
						.addComponent(this.jScrollPane4,
								GroupLayout.Alignment.TRAILING));
		jPanel3Layout
				.setVerticalGroup(jPanel3Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addComponent(this.jLabel3)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(this.jScrollPane4, -1,
												101, 32767)));
		this.jSplitPane2.setRightComponent(this.jPanel3);
		GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
		this.jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(this.jSplitPane2,
				GroupLayout.Alignment.TRAILING));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(this.jSplitPane2,
				GroupLayout.Alignment.TRAILING, -1, 218, 32767));
		this.jSplitPane3.setRightComponent(this.jPanel1);
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(this.jSplitPane3,
				GroupLayout.Alignment.TRAILING, -1, 1741, 32767));
		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(this.jSplitPane3,
				GroupLayout.Alignment.TRAILING, -1, 559, 32767));
	}

	private void jComboBox1ActionPerformed(ActionEvent evt) {
		this.StateEvent.Checklist_State_Set((String) this.jComboBox1
				.getSelectedItem());
	}

	private void txt_NotesFocusLost(FocusEvent evt) {
		if (this.SelectedVul != null) {
			this.SelectedVul.setCHK_Notes(this.txt_Notes.getText());
		}
	}

	private void txt_CommentsFocusLost(FocusEvent evt) {
		if (this.SelectedVul != null) {
			this.SelectedVul.setCheckComment(this.txt_Comments.getText());
		}
	}

	private void Sev_OverrideActionPerformed(ActionEvent evt) {
		if (!(((String) this.Sev_Override.getSelectedItem())
				.equals("Severity Override") || ((String) this.Sev_Override
				.getSelectedItem()).equals(this.myChecklist
				.getSevOverride(this.iSelectedRef)))) {
			Sev_Ovr_Jst myJust = new Sev_Ovr_Jst(this,
					this.myChecklist.getSevJustification(this.iSelectedRef));
			myJust.SetSevDisp((String) this.Sev_Override.getSelectedItem());
			myJust.setLocation(this.getX() + 50, this.getY() + 50);
			myJust.setVisible(true);
		} else if (((String) this.Sev_Override.getSelectedItem())
				.equals("Severity Override")) {
			this.myChecklist.setSevOverride(this.iSelectedRef, "");
			this.myChecklist.setSevJustification(this.iSelectedRef, "");
			this.Post_Override("");
		}
	}

	private void txt_NotesKeyPressed(KeyEvent evt) {
		if (!this.StateEvent.GetNeedToSave()) {
			this.StateEvent.UpdateNeedToSave();
		}
	}

	private void txt_CommentsKeyPressed(KeyEvent evt) {
		if (!this.StateEvent.GetNeedToSave()) {
			this.StateEvent.UpdateNeedToSave();
		}
	}

	private void H_pop_copyActionPerformed(ActionEvent evt) {
		this.ProcessCopy(this.txt_Header);
	}

	private void pop_CopyActionPerformed(ActionEvent evt) {
		this.ProcessCopy(this.txt_Body);
	}

	private void jComboBox1MouseReleased(MouseEvent evt) {
	}

	private void pop_Paste_detActionPerformed(ActionEvent evt) {
		this.ProcessPaste(this.txt_Notes);
	}

	private void pop_Paste_comActionPerformed(ActionEvent evt) {
		this.ProcessPaste(this.txt_Comments);
	}

	private void pop_Copy_detActionPerformed(ActionEvent evt) {
		this.ProcessCopy(this.txt_Notes);
	}

	private void pop_Copy_comActionPerformed(ActionEvent evt) {
		this.ProcessCopy(this.txt_Comments);
	}

	private void ProcessCopy(JEditorPane myPane) {
		String s = myPane.getSelectedText();
		this.CopyToClipboard(s);
	}

	private void ProcessPaste(JEditorPane myPane) {
		String s = this.GetStringFromClip();
		String sText = myPane.getText();
		int iCaret = myPane.getCaretPosition();
		sText = sText.equals("") ? s : sText.substring(0, iCaret) + s
				+ sText.substring(iCaret);
		myPane.setText(sText);
	}

	private String GetStringFromClip() {
		String sRet;
		block3: {
			boolean bHasTransferableText;
			sRet = "";
			Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
			Transferable content = clip.getContents(null);
			boolean bl = bHasTransferableText = content != null
					&& content.isDataFlavorSupported(DataFlavor.stringFlavor);
			if (bHasTransferableText) {
				try {
					sRet = (String) content
							.getTransferData(DataFlavor.stringFlavor);
				} catch (Exception e) {
					if (!Util.bAllowPrintln)
						break block3;
					e.printStackTrace();
				}
			}
		}
		return sRet;
	}

	public void DisplayVuln(int iVuln) {
		if (iVuln >= 0) {
			if (Util.bHasAlphNum(this.txt_Notes.getText())) {
				this.SelectedVul.setCHK_Notes(this.txt_Notes.getText());
			}
			if (Util.bHasAlphNum(this.txt_Comments.getText())) {
				this.SelectedVul.setCheckComment(this.txt_Comments.getText());
			}
			this.iSelectedRef = iVuln;
			this.SelectedVul = this.myChecklist.getVuln(this.iSelectedRef);
			this.txt_Header.setBackground(this.myConfig.GetBackgroundColor());
			this.txt_Header.setForeground(this.myConfig.GetTextColor());
			this.txt_Body.setBackground(this.myConfig.GetBackgroundColor());
			this.txt_Body.setForeground(this.myConfig.GetTextColor());
			Vuln myVuln = this.myChecklist.getVuln(iVuln);
			this.txt_Notes.setText(this.myChecklist.getNote(iVuln));
			this.txt_Notes.getCaret().setDot(0);
			this.txt_Comments.setText(this.myChecklist.getComments(iVuln));
			this.txt_Comments.getCaret().setDot(0);
			this.jComboBox1.setSelectedIndex(this.myChecklist.getState(iVuln)
					.ordinal());
			if (this.myChecklist.getSevOverride(iVuln).equals("")) {
				if (this.Sev_Override.getSelectedIndex() != 0) {
					this.Sev_Override.setSelectedIndex(0);
				}
			} else {
				this.Sev_Override.setSelectedItem(this.myChecklist
						.getSevOverride(iVuln));
			}
			this.txt_Header.setText(this.VulnDisplay.GetRTFHeader(myVuln));
			this.txt_Body.setText(this.VulnDisplay.GetRTFBody(myVuln));
			Caret c2 = this.txt_Header.getCaret();
			c2.moveDot(0);
			this.txt_Header.setCaret(c2);
			Caret c = this.txt_Body.getCaret();
			c.moveDot(0);
			this.txt_Body.setCaret(c);
		}
	}

	private void CopyToClipboard(String s) {
		StringSelection stringSel = new StringSelection(s);
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		clip.setContents(stringSel, this.CBO);
	}

	private void PasteFromClipboard(JEditorPane textbox) {
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		clip.getContents(textbox);
	}

}
