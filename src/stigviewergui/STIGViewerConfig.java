/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.ListModel;
import javax.swing.border.Border;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.ResourceMap;
import stigviewergui.Config_CallBack;
import stigviewergui.STIGViewerGUIApp;
import stigviewergui.STIGViewerGUIView;
import stigviewergui.SVC_Disp;
import stigviewergui.SV_CORE.Util;
import stigviewergui.SV_CORE.Vuln;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class STIGViewerConfig extends JFrame implements ChangeListener {
	private int iFontSize;
	private int storedFontSize;
	private String sFont;
	private String storedFont;
	private DefaultListModel ShowList;
	private DefaultListModel HideList;
	private STIGViewerGUIView myApp;
	private JFrame myMainFrame;
	private Color TextColor;
	private Color TempTextColor;
	private Color BackColor;
	private Color TempBackColor;
	private boolean bCCI_Data;
	private boolean bCCI_Desc;
	private boolean bCCI_Map;
	private ArrayList<SVC_Disp.VulnBool> myVList;
	private DefaultComboBoxModel FontList;
	private Config_CallBack myCallback;
	private String sSlash = "\\";
	private JColorChooser Background_ColorChooser;
	private JCheckBox CCI_Data;
	private JCheckBox CCI_Desc;
	private JCheckBox CCI_Map;
	private JCheckBox ColorBlindMode;
	private JComboBox FontBox;
	private JButton StatDown;
	private JButton StatUp;
	private JList StatusSortList;
	private JColorChooser Text_ColorChooser;
	private JButton btn_Cancel;
	private JButton btn_OK;
	private JButton btn_add;
	private JButton btn_remove;
	private JComboBox jComboBox2;
	private JEditorPane jEditorPane1;
	private JLabel jLabel1;
	private JLabel jLabel10;
	private JLabel jLabel11;
	private JLabel jLabel12;
	private JLabel jLabel13;
	private JLabel jLabel14;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JLabel jLabel8;
	private JLabel jLabel9;
	private JPanel jPanel1;
	private JPanel jPanel10;
	private JPanel jPanel11;
	private JPanel jPanel2;
	private JPanel jPanel3;
	private JPanel jPanel4;
	private JPanel jPanel5;
	private JPanel jPanel6;
	private JPanel jPanel7;
	private JPanel jPanel8;
	private JPanel jPanel9;
	private JRadioButton jRB_UNIX;
	private JRadioButton jRB_Win;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane3;
	private JScrollPane jScrollPane4;
	private JTabbedPane jTabbedPane1;
	private JTabbedPane jTabbedPane2;
	private JList lst_Hide;
	private JList lst_Shown;
	private JTextField txt_NotAppChar;
	private JTextField txt_NotFindingChar;
	private JTextField txt_NotRevChar;
	private JTextField txt_OpenChar;

	public STIGViewerConfig() {
		this.SVC_Setup();
	}

	public STIGViewerConfig(STIGViewerGUIView App) {
		this.myApp = App;
		this.SVC_Setup();
	}

	private void SVC_Setup() {
		Util.SetWindowHeader("Settings", this);
		this.initComponents();
		this.Text_ColorChooser.getSelectionModel().addChangeListener(this);
		this.Background_ColorChooser.getSelectionModel()
				.addChangeListener(this);
		SVC_Disp SVCDTemp = new SVC_Disp();
		this.myVList = SVCDTemp.GetVBList();
		this.iFontSize = 28;
		this.storedFontSize = 28;
		this.myMainFrame = this.myApp.getFrame();
		this.TempTextColor = this.TextColor = Color.black;
		this.TempBackColor = this.BackColor = Color.white;
		this.bCCI_Data = true;
		this.bCCI_Desc = false;
		this.bCCI_Map = true;
		this.ShowList = new DefaultListModel();
		this.HideList = new DefaultListModel();
		this.lst_Shown.setModel(this.ShowList);
		this.lst_Hide.setModel(this.HideList);
		this.setDefaultCloseOperation(1);
		this.FontList = new DefaultComboBoxModel();
		this.FontBox.setModel(this.FontList);
		this.PopulateFontBox();
		this.SetDefaultFont();
		this.jComboBox2.setSelectedItem(Integer.toString(this.iFontSize / 2));
		this.UpdateSampleText();
		this.UpdateLists();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		this.TempTextColor = this.Text_ColorChooser.getColor();
		this.TempBackColor = this.Background_ColorChooser.getColor();
		this.UpdateSampleText();
	}

	private void SetDefaultFont() {
		if (this.FontList.getIndexOf("Courier New") != -1) {
			this.FontBox.setSelectedIndex(this.FontList
					.getIndexOf("Courier New"));
			this.sFont = "Courier New";
			this.storedFont = "Courier New";
		}
	}

	private void PopulateFontBox() {
		Font[] fonts;
		GraphicsEnvironment e = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		for (Font f : fonts = e.getAllFonts()) {
			this.FontList.addElement(f.getFontName());
		}
	}

	public void SetFont(String s) {
		this.sFont = s;
		this.storedFont = s;
	}

	public void SetFontSize(int i) {
		this.iFontSize = i;
		this.storedFontSize = i;
	}

	public void SetFontColor(Color c) {
		this.TextColor = c;
		this.TempTextColor = c;
		this.Text_ColorChooser.setColor(this.TextColor);
	}

	public void SetBackColor(Color c) {
		this.BackColor = c;
		this.TempBackColor = c;
		this.Background_ColorChooser.setColor(this.BackColor);
	}

	public boolean bUseColorBlindMode() {
		return this.ColorBlindMode.isSelected();
	}

	public void bSetColorBlindMode(boolean b) {
		this.ColorBlindMode.setSelected(b);
	}

	public void SetDisplayLists(ArrayList<String> aShowStrings,
			ArrayList<String> aHideStrings) {
		this.ShowList.removeAllElements();
		this.HideList.removeAllElements();
		for (String s2 : aShowStrings) {
			this.ShowList.add(this.ShowList.size(), s2);
		}
		for (String s2 : aHideStrings) {
			this.HideList.add(this.HideList.size(), s2);
		}
	}

	public void SetShowList(ArrayList<String> aShowStrings) {
		this.ShowList.removeAllElements();
		for (String s : aShowStrings) {
			this.ShowList.add(this.ShowList.size(), s);
		}
		for (int i = 0; i < this.myVList.size(); ++i) {
			this.myVList.get((int) i).bVal = this.ShowList
					.contains(this.myVList.get((int) i).sName);
		}
	}

	public void SetHideList(ArrayList<String> aHideStrings) {
		this.HideList.removeAllElements();
		for (String s : aHideStrings) {
			this.HideList.add(this.HideList.size(), s);
		}
	}

	public void SetCCI(boolean bCCI, boolean bCCIDesc, boolean bCCIMap) {
		this.bCCI_Data = bCCI;
		this.bCCI_Desc = bCCIDesc;
		this.bCCI_Map = bCCIMap;
	}

	private void UpdateLists() {
		this.ShowList.removeAllElements();
		this.HideList.removeAllElements();
		for (int i = 0; i < this.myVList.size(); ++i) {
			if (this.myVList.get((int) i).bVal) {
				this.ShowList.add(this.ShowList.size(),
						this.myVList.get((int) i).sName);
				continue;
			}
			this.HideList.add(this.HideList.size(),
					this.myVList.get((int) i).sName);
		}
	}

	public ArrayList<String> GetShowList() {
		ArrayList<String> alsRet = new ArrayList<String>();
		for (Object o : this.ShowList.toArray()) {
			alsRet.add((String) o);
		}
		return alsRet;
	}

	public ArrayList<String> GetHideList() {
		ArrayList<String> alsRet = new ArrayList<String>();
		for (Object o : this.HideList.toArray()) {
			alsRet.add((String) o);
		}
		return alsRet;
	}

	public int getFontSize() {
		return this.storedFontSize;
	}

	public String sGetFont() {
		return this.storedFont;
	}

	public void Show(Config_CallBack ReturnObject) {
		this.myCallback = ReturnObject;
		this.UpdateSampleText();
		this.pack();
		this.setVisible(true);
	}

	private void UpdateSampleText() {
		this.jEditorPane1.setBackground(this.TempBackColor);
		String s = "";
		s = s + "{\\rtf1\\ansi\\f0\\pard {\\fonttbl {\\f0 " + this.sFont
				+ ";}}";
		s = s + "\\fs" + Integer.toString(this.iFontSize) + " ";
		s = s + "{\\colortbl;\\red" + this.TempTextColor.getRed() + "\\green"
				+ this.TempTextColor.getGreen() + "\\blue"
				+ this.TempTextColor.getBlue() + ";}\\cf1";
		s = s + "{\\b Sample: }" + "This is sample text!";
		s = s + " \\par}";
		this.jEditorPane1.setText(s);
	}

	public Color GetTextColor() {
		return this.TextColor;
	}

	public Color GetBackgroundColor() {
		return this.BackColor;
	}

	public void ShowOpts() {
	}

	public String[] GetStatusSortList() {
		String[] sRet = new String[this.StatusSortList.getModel().getSize()];
		for (int i = 0; i < sRet.length; ++i) {
			sRet[i] = (String) this.StatusSortList.getModel().getElementAt(i);
		}
		return sRet;
	}

	public void SetStatusSortList(String[] saList) {
		String[] Vals = new String[] { "Not Reviewed", "Open", "Not a Finding",
				"Not Applicable" };
		String[] saSet = new String[] { "", "", "", "" };
		Boolean bBadInput = false;
		if (saList.length == 4) {
			for (int i = 0; i < saList.length; ++i) {
				for (int j = 0; j < Vals.length; ++j) {
					if (!saList[i].equals(Vals[j]))
						continue;
					saSet[i] = Vals[j];
				}
				if (!saSet[i].equals(""))
					continue;
				bBadInput = true;
			}
		} else {
			bBadInput = true;
		}
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		if (!bBadInput.booleanValue()) {
			for (String s : saSet) {
				dlm.addElement(s);
			}
		} else {
			if (Util.bAllowPrintln) {
				System.out.println("BAD INPUT to CL Sort!");
			}
			for (String s : Vals) {
				dlm.addElement(s);
			}
		}
		this.StatusSortList.setModel(dlm);
	}

	public char Get_SetOpenKey() {
		return this.txt_OpenChar.getText().charAt(0);
	}

	public char Get_SetNotAFindingKey() {
		return this.txt_NotFindingChar.getText().charAt(0);
	}

	public char Get_SetNotReviewedKey() {
		return this.txt_NotRevChar.getText().charAt(0);
	}

	public char Get_SetNotApplicableKey() {
		return this.txt_NotAppChar.getText().charAt(0);
	}

	private void initComponents() {
		this.jTabbedPane1 = new JTabbedPane();
		this.jPanel1 = new JPanel();
		this.jPanel2 = new JPanel();
		this.jLabel3 = new JLabel();
		this.jScrollPane3 = new JScrollPane();
		this.lst_Hide = new JList();
		this.jLabel5 = new JLabel();
		this.jLabel6 = new JLabel();
		this.btn_add = new JButton();
		this.btn_remove = new JButton();
		this.jScrollPane2 = new JScrollPane();
		this.lst_Shown = new JList();
		this.jPanel4 = new JPanel();
		this.CCI_Data = new JCheckBox();
		this.CCI_Map = new JCheckBox();
		this.CCI_Desc = new JCheckBox();
		this.jPanel11 = new JPanel();
		this.jLabel14 = new JLabel();
		this.jRB_Win = new JRadioButton();
		this.jRB_UNIX = new JRadioButton();
		this.jPanel3 = new JPanel();
		this.jPanel5 = new JPanel();
		this.jComboBox2 = new JComboBox();
		this.jLabel4 = new JLabel();
		this.FontBox = new JComboBox();
		this.jScrollPane1 = new JScrollPane();
		this.jEditorPane1 = new JEditorPane();
		this.jLabel1 = new JLabel();
		this.jLabel2 = new JLabel();
		this.jTabbedPane2 = new JTabbedPane();
		this.Text_ColorChooser = new JColorChooser();
		this.Background_ColorChooser = new JColorChooser();
		this.jPanel7 = new JPanel();
		this.jPanel8 = new JPanel();
		this.StatDown = new JButton();
		this.jScrollPane4 = new JScrollPane();
		this.StatusSortList = new JList();
		this.StatUp = new JButton();
		this.jLabel7 = new JLabel();
		this.jPanel9 = new JPanel();
		this.txt_NotRevChar = new JTextField();
		this.jLabel12 = new JLabel();
		this.jLabel8 = new JLabel();
		this.jLabel11 = new JLabel();
		this.txt_OpenChar = new JTextField();
		this.jLabel13 = new JLabel();
		this.jLabel9 = new JLabel();
		this.txt_NotFindingChar = new JTextField();
		this.txt_NotAppChar = new JTextField();
		this.jLabel10 = new JLabel();
		this.jPanel10 = new JPanel();
		this.ColorBlindMode = new JCheckBox();
		this.jPanel6 = new JPanel();
		this.btn_OK = new JButton();
		this.btn_Cancel = new JButton();
		this.setDefaultCloseOperation(3);
		this.setAlwaysOnTop(true);
		this.setCursor(new Cursor(0));
		this.setName("Form");
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent evt) {
				STIGViewerConfig.this.formWindowClosing(evt);
			}
		});
		this.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {
				STIGViewerConfig.this.formPropertyChange(evt);
			}
		});
		this.jTabbedPane1.setName("jTabbedPane1");
		this.jPanel1.setName("jPanel1");
		this.jPanel2.setBorder(BorderFactory.createEtchedBorder());
		this.jPanel2.setName("jPanel2");
		this.jLabel3.setHorizontalAlignment(0);
		ResourceMap resourceMap = ((STIGViewerGUIApp) Application
				.getInstance(STIGViewerGUIApp.class)).getContext()
				.getResourceMap(STIGViewerConfig.class);
		this.jLabel3.setText(resourceMap.getString("jLabel3.text",
				new Object[0]));
		this.jLabel3.setHorizontalTextPosition(0);
		this.jLabel3.setName("jLabel3");
		this.jScrollPane3.setName("jScrollPane3");
		this.lst_Hide.setName("lst_Hide");
		this.jScrollPane3.setViewportView(this.lst_Hide);
		this.jLabel5.setHorizontalAlignment(0);
		this.jLabel5.setText(resourceMap.getString("jLabel5.text",
				new Object[0]));
		this.jLabel5.setName("jLabel5");
		this.jLabel6.setHorizontalAlignment(0);
		this.jLabel6.setText(resourceMap.getString("jLabel6.text",
				new Object[0]));
		this.jLabel6.setName("jLabel6");
		this.btn_add.setText(resourceMap.getString("btn_add.text",
				new Object[0]));
		this.btn_add.setName("btn_add");
		this.btn_add.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerConfig.this.btn_addActionPerformed(evt);
			}
		});
		this.btn_remove.setText(resourceMap.getString("btn_remove.text",
				new Object[0]));
		this.btn_remove.setName("btn_remove");
		this.btn_remove.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerConfig.this.btn_removeActionPerformed(evt);
			}
		});
		this.jScrollPane2.setName("jScrollPane2");
		this.lst_Shown.setName("lst_Shown");
		this.jScrollPane2.setViewportView(this.lst_Shown);
		GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
		this.jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout
				.setHorizontalGroup(jPanel2Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addGap(294,
																				294,
																				294)
																		.addComponent(
																				this.jLabel5,
																				-1,
																				104,
																				32767))
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				this.jScrollPane3,
																				-1,
																				140,
																				32767)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel2Layout
																						.createParallelGroup(
																								GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								this.btn_add,
																								-1,
																								-1,
																								32767)
																						.addComponent(
																								this.btn_remove,
																								-1,
																								-1,
																								32767))
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				this.jScrollPane2,
																				-1,
																				143,
																				32767))
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addGap(46,
																				46,
																				46)
																		.addComponent(
																				this.jLabel6))
														.addComponent(
																this.jLabel3,
																-1, 398, 32767))
										.addContainerGap()));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addComponent(this.jLabel3)
										.addGap(9, 9, 9)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																this.jLabel5)
														.addComponent(
																this.jLabel6))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																GroupLayout.Alignment.TRAILING)
														.addComponent(
																this.jScrollPane3,
																GroupLayout.Alignment.LEADING,
																-1, 323, 32767)
														.addGroup(
																GroupLayout.Alignment.LEADING,
																jPanel2Layout
																		.createSequentialGroup()
																		.addGap(108,
																				108,
																				108)
																		.addComponent(
																				this.btn_add)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				this.btn_remove))
														.addComponent(
																this.jScrollPane2,
																-1, 323, 32767))
										.addContainerGap()));
		this.jPanel4.setBorder(BorderFactory.createEtchedBorder());
		this.jPanel4.setName("jPanel4");
		this.CCI_Data.setSelected(true);
		this.CCI_Data.setText(resourceMap.getString("CCI_Data.text",
				new Object[0]));
		this.CCI_Data.setName("CCI_Data");
		this.CCI_Data.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerConfig.this.CCI_DataActionPerformed(evt);
			}
		});
		this.CCI_Map.setSelected(true);
		this.CCI_Map.setText(resourceMap.getString("CCI_Map.text",
				new Object[0]));
		this.CCI_Map.setName("CCI_Map");
		this.CCI_Desc.setSelected(true);
		this.CCI_Desc.setText(resourceMap.getString("CCI_Desc.text",
				new Object[0]));
		this.CCI_Desc.setName("CCI_Desc");
		this.CCI_Desc.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerConfig.this.CCI_DescActionPerformed(evt);
			}
		});
		GroupLayout jPanel4Layout = new GroupLayout(this.jPanel4);
		this.jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout
				.setHorizontalGroup(jPanel4Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel4Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																this.CCI_Data)
														.addGroup(
																jPanel4Layout
																		.createSequentialGroup()
																		.addGap(21,
																				21,
																				21)
																		.addGroup(
																				jPanel4Layout
																						.createParallelGroup(
																								GroupLayout.Alignment.LEADING)
																						.addComponent(
																								this.CCI_Map)
																						.addComponent(
																								this.CCI_Desc))))
										.addContainerGap(76, 32767)));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel4Layout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(this.CCI_Data)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.CCI_Desc)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.CCI_Map).addContainerGap()));
		this.jPanel11.setBorder(BorderFactory.createEtchedBorder());
		this.jPanel11.setName("jPanel11");
		this.jLabel14.setText(resourceMap.getString("jLabel14.text",
				new Object[0]));
		this.jLabel14.setName("jLabel14");
		this.jRB_Win.setSelected(true);
		this.jRB_Win.setText(resourceMap.getString("jRB_Win.text",
				new Object[0]));
		this.jRB_Win.setName("jRB_Win");
		this.jRB_Win.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerConfig.this.jRB_WinActionPerformed(evt);
			}
		});
		this.jRB_UNIX.setText(resourceMap.getString("jRB_UNIX.text",
				new Object[0]));
		this.jRB_UNIX.setName("jRB_UNIX");
		this.jRB_UNIX.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerConfig.this.jRB_UNIXActionPerformed(evt);
			}
		});
		GroupLayout jPanel11Layout = new GroupLayout(this.jPanel11);
		this.jPanel11.setLayout(jPanel11Layout);
		jPanel11Layout.setHorizontalGroup(jPanel11Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				jPanel11Layout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								jPanel11Layout
										.createParallelGroup(
												GroupLayout.Alignment.LEADING)
										.addComponent(this.jRB_UNIX)
										.addComponent(this.jLabel14)
										.addComponent(this.jRB_Win))
						.addContainerGap(157, 32767)));
		jPanel11Layout.setVerticalGroup(jPanel11Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel11Layout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(this.jLabel14)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.jRB_Win)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.jRB_UNIX)
								.addContainerGap(16, 32767)));
		GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
		this.jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(this.jPanel2, -2, -1, -2)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																this.jPanel11,
																-1, -1, 32767)
														.addComponent(
																this.jPanel4,
																-1, -1, 32767))
										.addContainerGap()));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addComponent(
																				this.jPanel4,
																				-2,
																				-1,
																				-2)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				this.jPanel11,
																				-2,
																				-1,
																				-2))
														.addComponent(
																this.jPanel2,
																-1, -1, 32767))
										.addContainerGap()));
		this.jTabbedPane1
				.addTab(resourceMap.getString(
						"jPanel1.TabConstraints.tabTitle", new Object[0]),
						this.jPanel1);
		this.jPanel3.setName("jPanel3");
		this.jPanel5.setName("jPanel5");
		this.jComboBox2.setModel(new DefaultComboBoxModel<String>(new String[] {
				"8", "10", "12", "14", "16", "18", "20", "24" }));
		this.jComboBox2.setSelectedIndex(2);
		this.jComboBox2.setName("jComboBox2");
		this.jComboBox2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerConfig.this.jComboBox2ActionPerformed(evt);
			}
		});
		this.jLabel4.setText(resourceMap.getString("jLabel4.text",
				new Object[0]));
		this.jLabel4.setName("jLabel4");
		this.FontBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "(font list)" }));
		this.FontBox.setName("FontBox");
		this.FontBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerConfig.this.FontBoxActionPerformed(evt);
			}
		});
		this.jScrollPane1.setName("jScrollPane1");
		this.jEditorPane1.setContentType("text/rtf");
		this.jEditorPane1.setEditable(false);
		this.jEditorPane1.setName("jEditorPane1");
		this.jScrollPane1.setViewportView(this.jEditorPane1);
		this.jLabel1.setText(resourceMap.getString("jLabel1.text",
				new Object[0]));
		this.jLabel1.setName("jLabel1");
		this.jLabel2.setText(resourceMap.getString("jLabel2.text",
				new Object[0]));
		this.jLabel2.setName("jLabel2");
		GroupLayout jPanel5Layout = new GroupLayout(this.jPanel5);
		this.jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout
				.setHorizontalGroup(jPanel5Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel5Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel5Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING,
																false)
														.addGroup(
																jPanel5Layout
																		.createSequentialGroup()
																		.addComponent(
																				this.jLabel2)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				this.jComboBox2,
																				-2,
																				-1,
																				-2))
														.addGroup(
																jPanel5Layout
																		.createSequentialGroup()
																		.addComponent(
																				this.jLabel1)
																		.addGap(18,
																				18,
																				18)
																		.addComponent(
																				this.FontBox,
																				-2,
																				-1,
																				-2))
														.addComponent(
																this.jLabel4)
														.addComponent(
																this.jScrollPane1,
																-2, 206, -2))
										.addContainerGap()));
		jPanel5Layout
				.setVerticalGroup(jPanel5Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel5Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel5Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																this.jLabel1)
														.addComponent(
																this.FontBox,
																-2, -1, -2))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel5Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																this.jLabel2)
														.addComponent(
																this.jComboBox2,
																-2, -1, -2))
										.addGap(18, 18, 18)
										.addComponent(this.jLabel4)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(this.jScrollPane1, -2,
												71, -2).addContainerGap()));
		this.jTabbedPane2.setName("jTabbedPane2");
		this.jTabbedPane2.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent evt) {
				STIGViewerConfig.this.jTabbedPane2MouseClicked(evt);
			}
		});
		this.Text_ColorChooser.setName("Text_ColorChooser");
		this.jTabbedPane2.addTab(resourceMap.getString(
				"Text_ColorChooser.TabConstraints.tabTitle", new Object[0]),
				this.Text_ColorChooser);
		this.Background_ColorChooser.setName("Background_ColorChooser");
		this.jTabbedPane2.addTab(resourceMap.getString(
				"Background_ColorChooser.TabConstraints.tabTitle",
				new Object[0]), this.Background_ColorChooser);
		GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
		this.jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout
				.setHorizontalGroup(jPanel3Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addComponent(this.jPanel5, -2, -1, -2)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(this.jTabbedPane2, -1,
												442, 32767).addContainerGap()));
		jPanel3Layout
				.setVerticalGroup(jPanel3Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addComponent(this.jPanel5, -2, 205,
												32767).addGap(198, 198, 198))
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addComponent(this.jTabbedPane2, -1,
												392, 32767).addContainerGap()));
		this.jTabbedPane1
				.addTab(resourceMap.getString(
						"jPanel3.TabConstraints.tabTitle", new Object[0]),
						this.jPanel3);
		this.jPanel7.setName("jPanel7");
		this.jPanel8.setBorder(BorderFactory.createEtchedBorder());
		this.jPanel8.setName("jPanel8");
		this.StatDown.setText(resourceMap.getString("StatDown.text",
				new Object[0]));
		this.StatDown.setName("StatDown");
		this.StatDown.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerConfig.this.StatDownActionPerformed(evt);
			}
		});
		this.jScrollPane4.setName("jScrollPane4");
		this.StatusSortList.setModel(new AbstractListModel() {
			String[] strings;

			public int getSize() {
				return this.strings.length;
			}

			public Object getElementAt(int i) {
				return this.strings[i];
			}
		});
		this.StatusSortList.setName("StatusSortList");
		this.jScrollPane4.setViewportView(this.StatusSortList);
		this.StatUp
				.setText(resourceMap.getString("StatUp.text", new Object[0]));
		this.StatUp.setName("StatUp");
		this.StatUp.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerConfig.this.StatUpActionPerformed(evt);
			}
		});
		this.jLabel7.setText(resourceMap.getString("jLabel7.text",
				new Object[0]));
		this.jLabel7.setName("jLabel7");
		GroupLayout jPanel8Layout = new GroupLayout(this.jPanel8);
		this.jPanel8.setLayout(jPanel8Layout);
		jPanel8Layout.setHorizontalGroup(jPanel8Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				jPanel8Layout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								jPanel8Layout
										.createParallelGroup(
												GroupLayout.Alignment.TRAILING)
										.addComponent(this.StatDown,
												GroupLayout.Alignment.LEADING,
												-1, 93, 32767)
										.addComponent(this.jScrollPane4,
												GroupLayout.Alignment.LEADING,
												-1, 93, 32767)
										.addComponent(this.jLabel7,
												GroupLayout.Alignment.LEADING,
												-1, 93, 32767)
										.addComponent(this.StatUp,
												GroupLayout.Alignment.LEADING,
												-1, 93, 32767))
						.addContainerGap()));
		jPanel8Layout
				.setVerticalGroup(jPanel8Layout.createParallelGroup(
						GroupLayout.Alignment.LEADING).addGroup(
						jPanel8Layout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(this.jLabel7)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.StatUp)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.jScrollPane4, -2, 78, -2)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.StatDown).addContainerGap()));
		this.jPanel9.setBorder(BorderFactory.createEtchedBorder());
		this.jPanel9.setName("jPanel9");
		this.txt_NotRevChar.setText(resourceMap.getString(
				"txt_NotRevChar.text", new Object[0]));
		this.txt_NotRevChar.setName("txt_NotRevChar");
		this.jLabel12.setText(resourceMap.getString("jLabel12.text",
				new Object[0]));
		this.jLabel12.setName("jLabel12");
		this.jLabel8.setText(resourceMap.getString("jLabel8.text",
				new Object[0]));
		this.jLabel8.setName("jLabel8");
		this.jLabel11.setText(resourceMap.getString("jLabel11.text",
				new Object[0]));
		this.jLabel11.setName("jLabel11");
		this.txt_OpenChar.setText(resourceMap.getString("txt_OpenChar.text",
				new Object[0]));
		this.txt_OpenChar.setName("txt_OpenChar");
		this.jLabel13.setText(resourceMap.getString("jLabel13.text",
				new Object[0]));
		this.jLabel13.setName("jLabel13");
		this.jLabel9.setText(resourceMap.getString("jLabel9.text",
				new Object[0]));
		this.jLabel9.setName("jLabel9");
		this.txt_NotFindingChar.setText(resourceMap.getString(
				"txt_NotFindingChar.text", new Object[0]));
		this.txt_NotFindingChar.setName("txt_NotFindingChar");
		this.txt_NotAppChar.setText(resourceMap.getString(
				"txt_NotAppChar.text", new Object[0]));
		this.txt_NotAppChar.setName("txt_NotAppChar");
		this.jLabel10.setText(resourceMap.getString("jLabel10.text",
				new Object[0]));
		this.jLabel10.setName("jLabel10");
		GroupLayout jPanel9Layout = new GroupLayout(this.jPanel9);
		this.jPanel9.setLayout(jPanel9Layout);
		jPanel9Layout
				.setHorizontalGroup(jPanel9Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel9Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel9Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																this.jLabel8)
														.addComponent(
																this.jLabel13)
														.addGroup(
																jPanel9Layout
																		.createParallelGroup(
																				GroupLayout.Alignment.TRAILING)
																		.addGroup(
																				jPanel9Layout
																						.createSequentialGroup()
																						.addComponent(
																								this.jLabel10)
																						.addPreferredGap(
																								LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(
																								this.txt_NotFindingChar,
																								-2,
																								21,
																								-2))
																		.addGroup(
																				jPanel9Layout
																						.createSequentialGroup()
																						.addComponent(
																								this.jLabel9)
																						.addPreferredGap(
																								LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(
																								this.txt_OpenChar,
																								-2,
																								21,
																								-2))
																		.addGroup(
																				jPanel9Layout
																						.createSequentialGroup()
																						.addComponent(
																								this.jLabel11)
																						.addPreferredGap(
																								LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(
																								this.txt_NotAppChar,
																								-2,
																								21,
																								-2))
																		.addGroup(
																				jPanel9Layout
																						.createSequentialGroup()
																						.addComponent(
																								this.jLabel12)
																						.addPreferredGap(
																								LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(
																								this.txt_NotRevChar,
																								-2,
																								21,
																								-2))))
										.addContainerGap()));
		jPanel9Layout
				.setVerticalGroup(jPanel9Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel9Layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(this.jLabel8)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(this.jLabel13)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel9Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																this.jLabel9)
														.addComponent(
																this.txt_OpenChar,
																-2, -1, -2))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel9Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																this.jLabel10)
														.addComponent(
																this.txt_NotFindingChar,
																-2, -1, -2))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel9Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																this.jLabel11)
														.addComponent(
																this.txt_NotAppChar,
																-2, -1, -2))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel9Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																this.jLabel12)
														.addComponent(
																this.txt_NotRevChar,
																-2, -1, -2))
										.addContainerGap(29, 32767)));
		this.jPanel10.setBorder(BorderFactory.createEtchedBorder());
		this.jPanel10.setName("jPanel10");
		this.ColorBlindMode.setText(resourceMap.getString(
				"ColorBlindMode.text", new Object[0]));
		this.ColorBlindMode.setName("ColorBlindMode");
		GroupLayout jPanel10Layout = new GroupLayout(this.jPanel10);
		this.jPanel10.setLayout(jPanel10Layout);
		jPanel10Layout.setHorizontalGroup(jPanel10Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				jPanel10Layout.createSequentialGroup().addContainerGap()
						.addComponent(this.ColorBlindMode, -1, 315, 32767)
						.addContainerGap()));
		jPanel10Layout.setVerticalGroup(jPanel10Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING)
				.addComponent(this.ColorBlindMode));
		GroupLayout jPanel7Layout = new GroupLayout(this.jPanel7);
		this.jPanel7.setLayout(jPanel7Layout);
		jPanel7Layout
				.setHorizontalGroup(jPanel7Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								GroupLayout.Alignment.TRAILING,
								jPanel7Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																GroupLayout.Alignment.TRAILING)
														.addComponent(
																this.jPanel10,
																GroupLayout.Alignment.LEADING,
																-1, -1, 32767)
														.addGroup(
																jPanel7Layout
																		.createSequentialGroup()
																		.addComponent(
																				this.jPanel8,
																				-2,
																				-1,
																				32767)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				this.jPanel9,
																				-2,
																				-1,
																				-2)))
										.addGap(347, 347, 347)));
		jPanel7Layout
				.setVerticalGroup(jPanel7Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								GroupLayout.Alignment.TRAILING,
								jPanel7Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																GroupLayout.Alignment.TRAILING)
														.addComponent(
																this.jPanel9,
																GroupLayout.Alignment.LEADING,
																-1, -1, 32767)
														.addComponent(
																this.jPanel8,
																GroupLayout.Alignment.LEADING,
																-2, -1, 32767))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(this.jPanel10, -2, -1, -2)
										.addGap(177, 177, 177)));
		this.jTabbedPane1
				.addTab(resourceMap.getString(
						"jPanel7.TabConstraints.tabTitle", new Object[0]),
						this.jPanel7);
		this.getContentPane().add((Component) this.jTabbedPane1, "Center");
		this.jPanel6.setName("jPanel6");
		this.btn_OK
				.setText(resourceMap.getString("btn_OK.text", new Object[0]));
		this.btn_OK.setName("btn_OK");
		this.btn_OK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerConfig.this.btn_OKActionPerformed(evt);
			}
		});
		this.btn_Cancel.setText(resourceMap.getString("btn_Cancel.text",
				new Object[0]));
		this.btn_Cancel.setName("btn_Cancel");
		this.btn_Cancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerConfig.this.btn_CancelActionPerformed(evt);
			}
		});
		GroupLayout jPanel6Layout = new GroupLayout(this.jPanel6);
		this.jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				jPanel6Layout
						.createSequentialGroup()
						.addComponent(this.btn_OK)
						.addPreferredGap(
								LayoutStyle.ComponentPlacement.RELATED, 579,
								32767).addComponent(this.btn_Cancel)));
		jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				jPanel6Layout
						.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.btn_OK)
						.addComponent(this.btn_Cancel)));
		this.getContentPane().add((Component) this.jPanel6, "South");
		this.pack();
	}

	private void formWindowClosing(WindowEvent evt) {
		this.myCallback.ReturnPostUpdate();
	}

	private void formPropertyChange(PropertyChangeEvent evt) {
	}

	private void CCI_DescActionPerformed(ActionEvent evt) {
	}

	private void CCI_DataActionPerformed(ActionEvent evt) {
		if (this.CCI_Data.isSelected()) {
			this.CCI_Desc.setEnabled(true);
			this.CCI_Map.setEnabled(true);
		} else {
			this.CCI_Desc.setEnabled(false);
			this.CCI_Map.setEnabled(false);
			this.CCI_Desc.setSelected(false);
			this.CCI_Map.setSelected(false);
		}
	}

	private void btn_CancelActionPerformed(ActionEvent evt) {
		this.SetConfigDefaultDisplay();
		this.setVisible(false);
		this.formWindowClosing(null);
	}

	private void btn_OKActionPerformed(ActionEvent evt) {
		this.storedFontSize = this.iFontSize;
		this.storedFont = this.sFont;
		this.TextColor = this.TempTextColor;
		this.BackColor = this.TempBackColor;
		this.bCCI_Data = this.CCI_Data.isSelected();
		this.bCCI_Desc = this.CCI_Desc.isSelected();
		this.bCCI_Map = this.CCI_Map.isSelected();
		this.setVisible(false);
		this.formWindowClosing(null);
	}

	private void btn_removeActionPerformed(ActionEvent evt) {
		int[] Sel = this.lst_Shown.getSelectedIndices();
		if (Sel.length > 0 && Sel != null) {
			for (int i = 0; i < Sel.length; ++i) {
				for (int j = 0; j < this.myVList.size(); ++j) {
					if (!this.myVList.get((int) j).sName.equals(this.ShowList
							.get(Sel[i])))
						continue;
					this.myVList.get((int) j).bVal = false;
				}
			}
		}
		this.UpdateLists();
	}

	private void btn_addActionPerformed(ActionEvent evt) {
		int[] Sel = this.lst_Hide.getSelectedIndices();
		if (Sel.length > 0 && Sel != null) {
			for (int i = 0; i < Sel.length; ++i) {
				for (int j = 0; j < this.myVList.size(); ++j) {
					if (!this.myVList.get((int) j).sName.equals(this.HideList
							.get(Sel[i])))
						continue;
					this.myVList.get((int) j).bVal = true;
				}
			}
		}
		this.UpdateLists();
	}

	private void jComboBox2ActionPerformed(ActionEvent evt) {
		this.iFontSize = (this.jComboBox2.getSelectedIndex() * 2 + 8) * 2;
		this.UpdateSampleText();
	}

	private void FontBoxActionPerformed(ActionEvent evt) {
		this.sFont = (String) this.FontBox.getSelectedItem();
		this.UpdateSampleText();
	}

	private void jTabbedPane2MouseClicked(MouseEvent evt) {
	}

	private void StatUpActionPerformed(ActionEvent evt) {
		int x = this.StatusSortList.getSelectedIndex();
		if (x > 0) {
			String[] List = new String[this.StatusSortList.getModel().getSize()];
			for (int i = 0; i < List.length; ++i) {
				List[i] = (String) this.StatusSortList.getModel().getElementAt(
						i);
			}
			String st = List[x - 1];
			List[x - 1] = List[x];
			List[x] = st;
			DefaultListModel<String> lm = new DefaultListModel<String>();
			for (String s : List) {
				lm.addElement(s);
			}
			this.StatusSortList.setModel(lm);
			this.StatusSortList.setSelectedIndex(x - 1);
		}
	}

	private void StatDownActionPerformed(ActionEvent evt) {
		String[] List;
		int x = this.StatusSortList.getSelectedIndex();
		if (x != -1
				&& x < (List = new String[this.StatusSortList.getModel()
						.getSize()]).length - 1) {
			for (int i = 0; i < List.length; ++i) {
				List[i] = (String) this.StatusSortList.getModel().getElementAt(
						i);
			}
			String st = List[x + 1];
			List[x + 1] = List[x];
			List[x] = st;
			DefaultListModel<String> lm = new DefaultListModel<String>();
			for (String s : List) {
				lm.addElement(s);
			}
			this.StatusSortList.setModel(lm);
			this.StatusSortList.setSelectedIndex(x + 1);
		}
	}

	private void jRB_WinActionPerformed(ActionEvent evt) {
		this.SetSlashWindows();
	}

	private void jRB_UNIXActionPerformed(ActionEvent evt) {
		this.SetSlashUNIX();
	}

	public void SetSlashWindows() {
		this.sSlash = "\\";
		this.jRB_Win.setSelected(true);
		this.jRB_UNIX.setSelected(false);
	}

	public void SetSlashUNIX() {
		this.sSlash = "/";
		this.jRB_Win.setSelected(false);
		this.jRB_UNIX.setSelected(true);
	}

	public String GetSlash() {
		return this.sSlash;
	}

	public void SetConfigDefaultDisplay() {
		this.iFontSize = this.storedFontSize;
		this.sFont = this.storedFont;
		this.TempTextColor = this.TextColor;
		this.TempBackColor = this.BackColor;
		this.CCI_Data.setSelected(this.bCCI_Data);
		if (this.CCI_Data.isSelected()) {
			this.CCI_Desc.setEnabled(true);
			this.CCI_Map.setEnabled(true);
			this.CCI_Desc.setSelected(this.bCCI_Desc);
			this.CCI_Map.setSelected(this.bCCI_Map);
		} else {
			this.CCI_Desc.setEnabled(false);
			this.CCI_Map.setEnabled(false);
			this.CCI_Desc.setSelected(false);
			this.CCI_Map.setSelected(false);
		}
		this.UpdateSampleText();
	}

	public boolean CheckIfShow(Vuln.VulnAttr V) {
		for (int i = 0; i < this.myVList.size(); ++i) {
			if (this.myVList.get((int) i).VA != V)
				continue;
			return this.myVList.get((int) i).bVal;
		}
		return true;
	}

	public boolean CheckIfShow_CCI() {
		return this.bCCI_Data;
	}

	public boolean CheckIfShow_CCIDesc() {
		return this.bCCI_Desc;
	}

	public boolean CheckIfShow_CCIMap() {
		return this.bCCI_Map;
	}

}
