/*
 * Decompiled with CFR 0_92.
 */
package ModularCheckList;

import ColorListBox.CLB_EventHandler;
import ColorListBox.CLB_Model;
import ColorListBox.ColorListBox;
import FileFilters.FileFilterFactory;
import File_Interfaces.ARF_ASR_SI;
import File_Interfaces.CCIReader;
import File_Interfaces.CSV_Export_Selector;
import File_Interfaces.RETINA_VMS_SI;
import File_Interfaces.SaveFile_SI;
import File_Interfaces.VMS_Keys;
import File_Interfaces.VMS_SI;
import File_Interfaces.XCCDF_RES;
import ModularCheckList.GetTargetData;
import ModularCheckList.ModCL_Card_Checklist;
import ModularCheckList.ModCL_Checklist_State;
import ModularCheckList.SimpleVulnList;
import ModularCheckList.SimpleVulnList_I;
import RETINA_VMS.ASSET;
import RETINA_VMS.ASSETID;
import RETINA_VMS.FINDING;
import RETINA_VMS.FINDINGDETAILS;
import RETINA_VMS.FINDINGID;
import RETINA_VMS.IMPORTFILE;
import RETINA_VMS.SCAN;
import RETINA_VMS.TARGET;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.filechooser.FileFilter;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.ResourceMap;
import stigviewergui.Config_CallBack;
import stigviewergui.Filter;
import stigviewergui.STIGSearch;
import stigviewergui.STIGViewerConfig;
import stigviewergui.STIGViewerGUIApp;
import stigviewergui.STIGViewerGUIView;
import stigviewergui.SV_CORE.GTD_PostData;
import stigviewergui.SV_CORE.SV_Checklist;
import stigviewergui.SV_CORE.Util;
import stigviewergui.SV_CORE.Vuln;
import stigviewergui.export.Export_ChecklistHTML;
import stigviewergui.export.Export_Print;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ModCL extends JFrame implements CLB_EventHandler,
		ModCL_Checklist_State, GTD_PostData, SimpleVulnList_I, Config_CallBack {
	private SV_Checklist myChecklist;
	private STIGViewerConfig myConfig;
	private ArrayList<Color> clText;
	private ArrayList<Color> clBack;
	private ArrayList<String> ColorMap;
	private ModCL_Mode myMode;
	private JPanel CheckListCard;
	private STIGViewerGUIView myParent;
	private File fMySaveFile;
	private boolean bChangedSinceSave;
	private String sTitle;
	CCIReader myCCI;
	VMS_Keys myVMS_Keys;
	private static String VMS_KEY_FILE = "";
	private String sMetricPreviousValue = "OVERVIEW";
	private boolean bEnableCL_Selecetion_EH = true;
	private JTabbedPane AssetIDTabs;
	private JMenuItem ChangeAssetPostures;
	private ColorListBox DisplayList;
	private JMenu Export;
	private JMenuItem Export_ARFASR;
	private JMenuItem Export_CSV;
	private JMenuItem Export_VMS;
	private JMenu File;
	private JMenuItem GenerateChecklist_SF;
	private JMenu Import;
	private JMenuItem Import_RetVMS_Res;
	private JMenuItem Import_XCCDF_Res;
	private JTextField NC_TargetName;
	private JLabel Num_NA;
	private JLabel Num_NA1;
	private JLabel Num_NA2;
	private JLabel Num_NA3;
	private JLabel Num_NR;
	private JLabel Num_NR1;
	private JLabel Num_NR2;
	private JLabel Num_NR3;
	private JLabel Num_NotAFinding;
	private JLabel Num_NotAFinding1;
	private JLabel Num_NotAFinding2;
	private JLabel Num_NotAFinding3;
	private JLabel Num_Open;
	private JLabel Num_Open1;
	private JLabel Num_Open2;
	private JLabel Num_Open3;
	private JLabel Num_Total;
	private JLabel Num_Total1;
	private JLabel Num_Total2;
	private JLabel Num_Total3;
	private JMenuItem OpenChecklist;
	private JMenu Options;
	private JMenuItem Print_Checklist;
	private JMenuItem RefreshDisplay;
	private JMenuItem SaveChecklist;
	private JMenuItem SaveChecklistAs;
	private JMenuItem SetNotAFinding;
	private JMenuItem SetNotApplicable;
	private JMenuItem SetNotReviewed;
	private JMenuItem SetOpen;
	private JCheckBoxMenuItem SetStatusSort;
	private JMenuItem Settings;
	private JPanel SwapPanel;
	private JTextField TargetHostName;
	private JTextField TargetIP;
	private JTextField TargetMAC;
	private JComboBox TargetRole;
	private JPopupMenu chklst_Status;
	private JButton jButton1;
	private JComboBox jComboBox1;
	private JLabel jLabel1;
	private JLabel jLabel10;
	private JLabel jLabel11;
	private JLabel jLabel12;
	private JLabel jLabel13;
	private JLabel jLabel14;
	private JLabel jLabel15;
	private JLabel jLabel16;
	private JLabel jLabel17;
	private JLabel jLabel18;
	private JLabel jLabel19;
	private JLabel jLabel2;
	private JLabel jLabel20;
	private JLabel jLabel21;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JLabel jLabel8;
	private JLabel jLabel9;
	private JMenuBar jMenuBar1;
	private JMenuItem jMenuItem1;
	private JPanel jPanel1;
	private JPanel jPanel10;
	private JPanel jPanel12;
	private JPanel jPanel2;
	private JPanel jPanel3;
	private JPanel jPanel4;
	private JPanel jPanel5;
	private JPanel jPanel6;
	private JPanel jPanel7;
	private JPanel jPanel8;
	private JPanel jPanel9;
	private JSeparator jSeparator1;
	private JSeparator jSeparator2;
	private JSeparator jSeparator3;
	private JSeparator jSeparator4;
	private JPopupMenu.Separator jSeparator5;
	private JSplitPane jSplitPane1;
	private JTabbedPane jTabbedPane1;
	private JTabbedPane jTabbedPane2;
	private JTextField jTextField1;
	private JTextField jTextField2;
	private JTextField jTextField3;
	private JTextField jTextField4;
	private JTextField jTextField5;
	private JTextField jTextField6;
	private JTextField jTextField7;
	private JTextField jTextField8;
	private JTextField jTextField9;

	public ModCL(ModCL_Mode mode, STIGViewerConfig conf,
			SV_Checklist checklist, CCIReader CCI, STIGViewerGUIView parent) {
		this.SetupModCL(mode, conf, checklist, CCI, parent);
	}

	public ModCL(ModCL_Mode mode, STIGViewerConfig conf,
			SV_Checklist checklist, CCIReader CCI, STIGViewerGUIView parent,
			SaveFile_SI.Asset AssetIN, File SaveFile) {
		this.SetupModCL(mode, conf, checklist, CCI, parent);
		this.fMySaveFile = SaveFile;
		if (AssetIN != null) {
			this.AssetIDTabs.setSelectedIndex(this.AssetIDTabs
					.indexOfTab(AssetIN.AssetType));
			if (AssetIN.AssetType.equals("Computing")) {
				this.TargetHostName.setText(AssetIN.HostName);
				this.TargetIP.setText(AssetIN.HostIP);
				this.TargetMAC.setText(AssetIN.HostMAC);
			} else if (AssetIN.AssetType.equals("Non-Computing")) {
				this.NC_TargetName.setText(AssetIN.HostName);
			}
			this.TargetRole.getModel().setSelectedItem(AssetIN.Role);
		}
		this.SetIsSaved(true);
	}

	private void SetupModCL(ModCL_Mode mode, STIGViewerConfig conf,
			SV_Checklist checklist, CCIReader CCI, STIGViewerGUIView parent) {
		CardLayout cl;
		block5: {
			this.myCCI = CCI;
			this.myMode = mode;
			this.myChecklist = checklist;
			this.myConfig = conf;
			this.myParent = parent;
			this.fMySaveFile = null;
			ResourceMap resMap = ((STIGViewerGUIApp) Application
					.getInstance(STIGViewerGUIApp.class)).getContext()
					.getResourceMap(STIGViewerGUIView.class);
			VMS_KEY_FILE = resMap.getString("Application.Element_File",
					new Object[0]);
			this.initComponents();
			cl = new CardLayout();
			this.SwapPanel.setLayout(cl);
			this.clText = new ArrayList();
			this.clBack = new ArrayList();
			this.ColorMap = new ArrayList();
			this.myVMS_Keys = new VMS_Keys();
			InputStream fIn = this.myVMS_Keys.getClass().getClassLoader()
					.getResourceAsStream(VMS_KEY_FILE);
			try {
				this.myVMS_Keys.open(fIn);
			} catch (Exception ex) {
				if (!Util.bAllowPrintln)
					break block5;
				ex.printStackTrace();
			}
		}
		this.DisplayList.SetPopupMenu(this.chklst_Status);
		this.sTitle = " " + this.myChecklist.GetSTIGTitle() + " Checklist";
		Util.SetWindowHeader(this.sTitle, this);
		this.SetIsSaved(false);
		if (this.myMode == ModCL_Mode.Checklist) {
			this.CheckListCard = new ModCL_Card_Checklist(this.myChecklist,
					this.myConfig, this, this.myCCI, parent.GetSearcher());
			this.SwapPanel.add((Component) this.CheckListCard, "Checklist");
			cl.show(this.SwapPanel, "Checklist");
			this.ColorMap.add("Not_Reviewed");
			this.clText.add(Color.BLACK);
			this.clBack.add(Color.WHITE);
			this.ColorMap.add("OpenI");
			this.clText.add(Color.WHITE);
			this.clBack.add(Color.RED);
			this.ColorMap.add("OpenII");
			this.clText.add(Color.BLACK);
			this.clBack.add(Color.ORANGE);
			this.ColorMap.add("OpenIII");
			this.clText.add(Color.BLACK);
			this.clBack.add(Color.YELLOW);
			this.ColorMap.add("NotAFinding");
			this.clText.add(Color.BLACK);
			this.clBack.add(Color.GREEN);
			this.ColorMap.add("NotApplicable");
			this.clText.add(Color.BLACK);
			this.clBack.add(Color.GRAY);
			this.RedrawDisplayList();
			this.DisplayList.setSelectedIndex(0);
			this.DisplayList.requestFocus();
		} else if (this.myMode == ModCL_Mode.OCIL && Util.bAllowPrintln) {
			System.out.println("Not yet handled");
		}
	}

	@Override
	public boolean GetNeedToSave() {
		return this.bChangedSinceSave;
	}

	@Override
	public void UpdateNeedToSave() {
		this.SetIsSaved(false);
	}

	@Override
	public void UpdateVulnColor(int iSelected) {
		Vuln v = this.myChecklist.getVuln(iSelected);
		this.DisplayList.SetColorOf(iSelected, this.clBack.get(this.ColorMap
				.indexOf(this.Checklist_GetColor(v))), this.clText
				.get(this.ColorMap.indexOf(this.Checklist_GetColor(v))));
		this.DisplayList.update(this.DisplayList.getGraphics());
		this.SetIsSaved(false);
	}

	private String GetDisplayListEntry(int i) {
		String sSortText = "";
		String sStatus = " ";
		if (this.myChecklist.getSortType() == STIGSearch.SortType.STIGID) {
			sSortText = this.myChecklist.getVuln(i).getAttr(
					Vuln.VulnAttr.Rule_Ver);
		} else if (this.myChecklist.getSortType() == STIGSearch.SortType.VULID) {
			sSortText = this.myChecklist.getVuln(i).getAttr(
					Vuln.VulnAttr.Vuln_Num);
		} else if (this.myChecklist.getSortType() == STIGSearch.SortType.RULEID) {
			sSortText = this.myChecklist.getVuln(i).getAttr(
					Vuln.VulnAttr.Rule_ID);
		} else if (this.myChecklist.getSortType() == STIGSearch.SortType.CCIID) {
			sSortText = this.myChecklist.getVuln(i).GetCCIVal(0);
		}
		if (this.myConfig.bUseColorBlindMode()) {
			if (this.myChecklist.getVuln(i).getCheckState()
					.equals((Object) SV_Checklist.CheckState.NotAFinding)) {
				sStatus = "NF- ";
			} else if (this.myChecklist.getVuln(i).getCheckState()
					.equals((Object) SV_Checklist.CheckState.Not_Applicable)) {
				sStatus = "NA- ";
			} else if (this.myChecklist.getVuln(i).getCheckState()
					.equals((Object) SV_Checklist.CheckState.Not_Reviewed)) {
				sStatus = "NR- ";
			} else if (this.myChecklist.getVuln(i).getCheckState()
					.equals((Object) SV_Checklist.CheckState.Open)) {
				sStatus = "O- ";
			}
		} else {
			sStatus = " ";
		}
		return sSortText
				+ " -"
				+ sStatus
				+ this.myChecklist.getVuln(i)
						.getAttr(Vuln.VulnAttr.Group_Title);
	}

	private void RedrawDisplayList() {
		this.DisplayList.clearList();
		Vector<CLB_Model> DL_Mod = new Vector<CLB_Model>();
		for (int i = 0; i < this.myChecklist.getVulnListSize(); ++i) {
			String sColor = this
					.Checklist_GetColor(this.myChecklist.getVuln(i));
			CLB_Model tMod = new CLB_Model(this.GetDisplayListEntry(i),
					this.clBack.get(this.ColorMap.indexOf(sColor)),
					this.clText.get(this.ColorMap.indexOf(sColor)));
			DL_Mod.add(tMod);
		}
		this.DisplayList.SetModelArray(DL_Mod);
		this.UpdateMetrics();
		this.DisplayList.repaint();
	}

	public void UpdateMetrics() {
		int iOpen = 0;
		int iCIOpen = 0;
		int iCIIOpen = 0;
		int iCIIIOpen = 0;
		int iNotFinding = 0;
		int iCINotFinding = 0;
		int iCIINotFinding = 0;
		int iCIIINotFinding = 0;
		int iNR = 0;
		int iCINR = 0;
		int iCIINR = 0;
		int iCIIINR = 0;
		int iNA = 0;
		int iCINA = 0;
		int iCIINA = 0;
		int iCIIINA = 0;
		for (int i = 0; i < this.myChecklist.getVulnListSize(); ++i) {
			if (this.myChecklist.getState(i) == SV_Checklist.CheckState.Open) {
				++iOpen;
				if (this.myChecklist.getVuln(i).getAttr(Vuln.VulnAttr.Severity)
						.toLowerCase().equals("high")) {
					++iCIOpen;
					continue;
				}
				if (this.myChecklist.getVuln(i).getAttr(Vuln.VulnAttr.Severity)
						.toLowerCase().equals("medium")) {
					++iCIIOpen;
					continue;
				}
				if (!this.myChecklist.getVuln(i)
						.getAttr(Vuln.VulnAttr.Severity).toLowerCase()
						.equals("low"))
					continue;
				++iCIIIOpen;
				continue;
			}
			if (this.myChecklist.getState(i) == SV_Checklist.CheckState.NotAFinding) {
				++iNotFinding;
				if (this.myChecklist.getVuln(i).getAttr(Vuln.VulnAttr.Severity)
						.toLowerCase().equals("high")) {
					++iCINotFinding;
					continue;
				}
				if (this.myChecklist.getVuln(i).getAttr(Vuln.VulnAttr.Severity)
						.toLowerCase().equals("medium")) {
					++iCIINotFinding;
					continue;
				}
				if (!this.myChecklist.getVuln(i)
						.getAttr(Vuln.VulnAttr.Severity).toLowerCase()
						.equals("low"))
					continue;
				++iCIIINotFinding;
				continue;
			}
			if (this.myChecklist.getState(i) == SV_Checklist.CheckState.Not_Applicable) {
				++iNA;
				if (this.myChecklist.getVuln(i).getAttr(Vuln.VulnAttr.Severity)
						.toLowerCase().equals("high")) {
					++iCINA;
					continue;
				}
				if (this.myChecklist.getVuln(i).getAttr(Vuln.VulnAttr.Severity)
						.toLowerCase().equals("medium")) {
					++iCIINA;
					continue;
				}
				if (!this.myChecklist.getVuln(i)
						.getAttr(Vuln.VulnAttr.Severity).toLowerCase()
						.equals("low"))
					continue;
				++iCIIINA;
				continue;
			}
			if (this.myChecklist.getState(i) != SV_Checklist.CheckState.Not_Reviewed)
				continue;
			++iNR;
			if (this.myChecklist.getVuln(i).getAttr(Vuln.VulnAttr.Severity)
					.toLowerCase().equals("high")) {
				++iCINR;
				continue;
			}
			if (this.myChecklist.getVuln(i).getAttr(Vuln.VulnAttr.Severity)
					.toLowerCase().equals("medium")) {
				++iCIINR;
				continue;
			}
			if (!this.myChecklist.getVuln(i).getAttr(Vuln.VulnAttr.Severity)
					.toLowerCase().equals("low"))
				continue;
			++iCIIINR;
		}
		this.Num_Open.setText(Integer.toString(iOpen));
		this.Num_Open1.setText(Integer.toString(iCIOpen));
		this.Num_Open2.setText(Integer.toString(iCIIOpen));
		this.Num_Open3.setText(Integer.toString(iCIIIOpen));
		this.Num_NotAFinding.setText(Integer.toString(iNotFinding));
		this.Num_NotAFinding1.setText(Integer.toString(iCINotFinding));
		this.Num_NotAFinding2.setText(Integer.toString(iCIINotFinding));
		this.Num_NotAFinding3.setText(Integer.toString(iCIIINotFinding));
		this.Num_NA.setText(Integer.toString(iNA));
		this.Num_NA1.setText(Integer.toString(iCINA));
		this.Num_NA2.setText(Integer.toString(iCIINA));
		this.Num_NA3.setText(Integer.toString(iCIIINA));
		this.Num_NR.setText(Integer.toString(iNR));
		this.Num_NR1.setText(Integer.toString(iCINR));
		this.Num_NR2.setText(Integer.toString(iCIINR));
		this.Num_NR3.setText(Integer.toString(iCIIINR));
		this.Num_Total.setText(Integer.toString(this.myChecklist
				.getVulnListSize()));
		this.Num_Total1.setText(Integer.toString(iCIOpen + iCINotFinding
				+ iCINA + iCINR));
		this.Num_Total2.setText(Integer.toString(iCIIOpen + iCIINotFinding
				+ iCIINA + iCIINR));
		this.Num_Total3.setText(Integer.toString(iCIIIOpen + iCIIINotFinding
				+ iCIIINA + iCIIINR));
	}

	public final void SetIsSaved(boolean b) {
		boolean bl = this.bChangedSinceSave = !b;
		if (this.bChangedSinceSave) {
			Util.SetWindowTitle(this.sTitle + "*", this);
		} else {
			Util.SetWindowTitle(this.sTitle, this);
		}
	}

	public void setSortList(ArrayList<String> SortList) {
		DefaultComboBoxModel<String> dcbm = new DefaultComboBoxModel<String>();
		for (int i = 0; i < SortList.size(); ++i) {
			dcbm.addElement(SortList.get(i));
		}
		this.jComboBox1.setModel(dcbm);
		this.jComboBox1ActionPerformed(null);
	}

	@Override
	public void CL_ListSelectionEvent(ListSelectionEvent evt) {
		if (this.myMode == ModCL_Mode.Checklist && this.bEnableCL_Selecetion_EH) {
			((ModCL_Card_Checklist) this.CheckListCard)
					.DisplayVuln(this.DisplayList.getSelectedIndex());
			this.DisplayList.repaint();
		}
	}

	public String Checklist_GetColor(Vuln v) {
		SV_Checklist.CheckState s = v.getCheckState();
		if (s == SV_Checklist.CheckState.NotAFinding) {
			return "NotAFinding";
		}
		if (s == SV_Checklist.CheckState.Not_Applicable) {
			return "NotApplicable";
		}
		if (s == SV_Checklist.CheckState.Open) {
			if (v.getCheckSevOverride().equals("")) {
				if (v.getAttr(Vuln.VulnAttr.Severity).equals("high")) {
					return "OpenI";
				}
				if (v.getAttr(Vuln.VulnAttr.Severity).equals("medium")) {
					return "OpenII";
				}
				if (v.getAttr(Vuln.VulnAttr.Severity).equals("low")) {
					return "OpenIII";
				}
				return "OpenIII";
			}
			if (v.getCheckSevOverride().equals("CAT I")) {
				return "OpenI";
			}
			if (v.getCheckSevOverride().equals("CAT II")) {
				return "OpenII";
			}
			if (v.getCheckSevOverride().equals("CAT III")) {
				return "OpenIII";
			}
		} else if (s == SV_Checklist.CheckState.Not_Reviewed) {
			return "Not_Reviewed";
		}
		return "Not_Reviewed";
	}

	@Override
	public void Checklist_State_Set(String s) {
		if (s.equals("Not Reviewed")) {
			this.myChecklist.setState(this.DisplayList.getSelectedIndex(),
					SV_Checklist.CheckState.Not_Reviewed);
			this.DisplayList.SetColors(
					this.clBack.get(this.ColorMap.indexOf("Not_Reviewed")),
					this.clText.get(this.ColorMap.indexOf("Not_Reviewed")));
		} else if (s.equals("Open")) {
			this.myChecklist.setState(this.DisplayList.getSelectedIndex(),
					SV_Checklist.CheckState.Open);
			String severity = this.myChecklist
					.getVuln(this.DisplayList.getSelectedIndex())
					.getCheckSevOverride().equals("") ? this.myChecklist
					.getVuln(this.DisplayList.getSelectedIndex()).getAttr(
							Vuln.VulnAttr.Severity) : this.myChecklist
					.getSevOverride(this.DisplayList.getSelectedIndex());
			if (severity.equals("high") || severity.equals("CAT I")) {
				this.DisplayList.SetColors(
						this.clBack.get(this.ColorMap.indexOf("OpenI")),
						this.clText.get(this.ColorMap.indexOf("OpenI")));
			} else if (severity.equals("medium") || severity.equals("CAT II")) {
				this.DisplayList.SetColors(
						this.clBack.get(this.ColorMap.indexOf("OpenII")),
						this.clText.get(this.ColorMap.indexOf("OpenII")));
			} else if (severity.equals("low") || severity.equals("CAT III")) {
				this.DisplayList.SetColors(
						this.clBack.get(this.ColorMap.indexOf("OpenIII")),
						this.clText.get(this.ColorMap.indexOf("OpenIII")));
			} else {
				this.DisplayList.SetColors(
						this.clBack.get(this.ColorMap.indexOf("OpenIII")),
						this.clText.get(this.ColorMap.indexOf("OpenIII")));
			}
		} else if (s.equals("Not a Finding")) {
			this.myChecklist.setState(this.DisplayList.getSelectedIndex(),
					SV_Checklist.CheckState.NotAFinding);
			this.DisplayList.SetColors(
					this.clBack.get(this.ColorMap.indexOf("NotAFinding")),
					this.clText.get(this.ColorMap.indexOf("NotAFinding")));
		} else if (s.equals("Not Applicable")) {
			this.myChecklist.setState(this.DisplayList.getSelectedIndex(),
					SV_Checklist.CheckState.Not_Applicable);
			this.DisplayList.SetColors(
					this.clBack.get(this.ColorMap.indexOf("NotApplicable")),
					this.clText.get(this.ColorMap.indexOf("NotApplicable")));
		}
		this.CL_ListSelectionEvent(null);
		this.UpdateMetrics();
	}

	private void initComponents() {
		this.chklst_Status = new JPopupMenu();
		this.SetOpen = new JMenuItem();
		this.SetNotAFinding = new JMenuItem();
		this.SetNotApplicable = new JMenuItem();
		this.SetNotReviewed = new JMenuItem();
		this.jSeparator5 = new JPopupMenu.Separator();
		this.SetStatusSort = new JCheckBoxMenuItem();
		this.jPanel10 = new JPanel();
		this.jSplitPane1 = new JSplitPane();
		this.jPanel1 = new JPanel();
		this.jPanel2 = new JPanel();
		this.DisplayList = new ColorListBox(this);
		this.jComboBox1 = new JComboBox();
		this.jTabbedPane1 = new JTabbedPane();
		this.jPanel5 = new JPanel();
		this.jLabel2 = new JLabel();
		this.Num_Open = new JLabel();
		this.jLabel4 = new JLabel();
		this.Num_NotAFinding = new JLabel();
		this.jLabel6 = new JLabel();
		this.Num_NA = new JLabel();
		this.jLabel8 = new JLabel();
		this.Num_NR = new JLabel();
		this.jLabel10 = new JLabel();
		this.Num_Total = new JLabel();
		this.jSeparator1 = new JSeparator();
		this.jPanel6 = new JPanel();
		this.jLabel3 = new JLabel();
		this.Num_Open1 = new JLabel();
		this.jLabel5 = new JLabel();
		this.Num_NotAFinding1 = new JLabel();
		this.jLabel7 = new JLabel();
		this.Num_NA1 = new JLabel();
		this.jLabel9 = new JLabel();
		this.Num_NR1 = new JLabel();
		this.jLabel11 = new JLabel();
		this.Num_Total1 = new JLabel();
		this.jSeparator2 = new JSeparator();
		this.jPanel7 = new JPanel();
		this.jLabel12 = new JLabel();
		this.Num_Open2 = new JLabel();
		this.jLabel13 = new JLabel();
		this.Num_NotAFinding2 = new JLabel();
		this.jLabel14 = new JLabel();
		this.Num_NA2 = new JLabel();
		this.jLabel15 = new JLabel();
		this.Num_NR2 = new JLabel();
		this.jLabel16 = new JLabel();
		this.Num_Total2 = new JLabel();
		this.jSeparator3 = new JSeparator();
		this.jPanel8 = new JPanel();
		this.jLabel17 = new JLabel();
		this.Num_Open3 = new JLabel();
		this.jLabel18 = new JLabel();
		this.Num_NotAFinding3 = new JLabel();
		this.jLabel19 = new JLabel();
		this.Num_NA3 = new JLabel();
		this.jLabel20 = new JLabel();
		this.Num_NR3 = new JLabel();
		this.jLabel21 = new JLabel();
		this.Num_Total3 = new JLabel();
		this.jSeparator4 = new JSeparator();
		this.jTabbedPane2 = new JTabbedPane();
		this.jPanel4 = new JPanel();
		this.AssetIDTabs = new JTabbedPane();
		this.jPanel3 = new JPanel();
		this.jLabel1 = new JLabel();
		this.TargetIP = new JTextField();
		this.TargetMAC = new JTextField();
		this.TargetHostName = new JTextField();
		this.jButton1 = new JButton();
		this.TargetRole = new JComboBox();
		this.jPanel9 = new JPanel();
		this.NC_TargetName = new JTextField();
		this.jPanel12 = new JPanel();
		this.jTextField1 = new JTextField();
		this.jTextField2 = new JTextField();
		this.jTextField3 = new JTextField();
		this.jTextField4 = new JTextField();
		this.jTextField5 = new JTextField();
		this.jTextField6 = new JTextField();
		this.jTextField7 = new JTextField();
		this.jTextField8 = new JTextField();
		this.jTextField9 = new JTextField();
		this.SwapPanel = new JPanel();
		this.jMenuBar1 = new JMenuBar();
		this.File = new JMenu();
		this.jMenuItem1 = new JMenuItem();
		this.SaveChecklist = new JMenuItem();
		this.SaveChecklistAs = new JMenuItem();
		this.OpenChecklist = new JMenuItem();
		this.Print_Checklist = new JMenuItem();
		this.Import = new JMenu();
		this.Import_XCCDF_Res = new JMenuItem();
		this.Import_RetVMS_Res = new JMenuItem();
		this.Export = new JMenu();
		this.Export_VMS = new JMenuItem();
		this.Export_ARFASR = new JMenuItem();
		this.GenerateChecklist_SF = new JMenuItem();
		this.Export_CSV = new JMenuItem();
		this.Options = new JMenu();
		this.Settings = new JMenuItem();
		this.RefreshDisplay = new JMenuItem();
		this.ChangeAssetPostures = new JMenuItem();
		this.chklst_Status.setName("chklst_Status");
		ResourceMap resourceMap = ((STIGViewerGUIApp) Application
				.getInstance(STIGViewerGUIApp.class)).getContext()
				.getResourceMap(ModCL.class);
		this.SetOpen.setText(resourceMap.getString("SetOpen.text",
				new Object[0]));
		this.SetOpen.setName("SetOpen");
		this.SetOpen.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL.this.SetOpenActionPerformed(evt);
			}
		});
		this.chklst_Status.add(this.SetOpen);
		this.SetNotAFinding.setText(resourceMap.getString(
				"SetNotAFinding.text", new Object[0]));
		this.SetNotAFinding.setName("SetNotAFinding");
		this.SetNotAFinding.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL.this.SetNotAFindingActionPerformed(evt);
			}
		});
		this.chklst_Status.add(this.SetNotAFinding);
		this.SetNotApplicable.setText(resourceMap.getString(
				"SetNotApplicable.text", new Object[0]));
		this.SetNotApplicable.setName("SetNotApplicable");
		this.SetNotApplicable.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL.this.SetNotApplicableActionPerformed(evt);
			}
		});
		this.chklst_Status.add(this.SetNotApplicable);
		this.SetNotReviewed.setText(resourceMap.getString(
				"SetNotReviewed.text", new Object[0]));
		this.SetNotReviewed.setName("SetNotReviewed");
		this.SetNotReviewed.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL.this.SetNotReviewedActionPerformed(evt);
			}
		});
		this.chklst_Status.add(this.SetNotReviewed);
		this.jSeparator5.setName("jSeparator5");
		this.chklst_Status.add(this.jSeparator5);
		this.SetStatusSort.setText(resourceMap.getString("SetStatusSort.text",
				new Object[0]));
		this.SetStatusSort.setName("SetStatusSort");
		this.SetStatusSort.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL.this.SetStatusSortActionPerformed(evt);
			}
		});
		this.chklst_Status.add(this.SetStatusSort);
		this.jPanel10.setBorder(BorderFactory.createLineBorder(new Color(0, 0,
				0)));
		this.jPanel10.setName("jPanel10");
		GroupLayout jPanel10Layout = new GroupLayout(this.jPanel10);
		this.jPanel10.setLayout(jPanel10Layout);
		jPanel10Layout.setHorizontalGroup(jPanel10Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGap(0, 228, 32767));
		jPanel10Layout.setVerticalGroup(jPanel10Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGap(0, 23, 32767));
		this.setDefaultCloseOperation(2);
		this.setName("Form");
		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent evt) {
				ModCL.this.formWindowClosing(evt);
			}
		});
		this.jSplitPane1.setName("jSplitPane1");
		this.jPanel1.setName("jPanel1");
		this.jPanel2.setBorder(BorderFactory.createEtchedBorder());
		this.jPanel2.setName("jPanel2");
		this.jPanel2.setLayout(new BorderLayout());
		this.DisplayList.setComponentPopupMenu(this.chklst_Status);
		this.DisplayList.setName("DisplayList");
		this.DisplayList.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent evt) {
				ModCL.this.DisplayListFocusLost(evt);
			}
		});
		this.jPanel2.add((Component) this.DisplayList, "Center");
		this.jComboBox1.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Item 1", "Item 2", "Item 3", "Item 4" }));
		this.jComboBox1.setName("jComboBox1");
		this.jComboBox1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL.this.jComboBox1ActionPerformed(evt);
			}
		});
		this.jPanel2.add((Component) this.jComboBox1, "First");
		this.jTabbedPane1.setName("jTabbedPane1");
		this.jTabbedPane1.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent evt) {
				ModCL.this.jTabbedPane1MouseClicked(evt);
			}

			public void mousePressed(MouseEvent evt) {
				ModCL.this.jTabbedPane1MousePressed(evt);
			}
		});
		this.jPanel5.setBorder(BorderFactory.createEtchedBorder());
		this.jPanel5.setName("jPanel5");
		this.jLabel2.setText(resourceMap.getString("jLabel2.text",
				new Object[0]));
		this.jLabel2.setName("jLabel2");
		this.Num_Open.setText(resourceMap.getString("Num_Open.text",
				new Object[0]));
		this.Num_Open.setName("Num_Open");
		this.jLabel4.setText(resourceMap.getString("jLabel4.text",
				new Object[0]));
		this.jLabel4.setName("jLabel4");
		this.Num_NotAFinding.setText(resourceMap.getString(
				"Num_NotAFinding.text", new Object[0]));
		this.Num_NotAFinding.setName("Num_NotAFinding");
		this.jLabel6.setText(resourceMap.getString("jLabel6.text",
				new Object[0]));
		this.jLabel6.setName("jLabel6");
		this.Num_NA
				.setText(resourceMap.getString("Num_NA.text", new Object[0]));
		this.Num_NA.setName("Num_NA");
		this.jLabel8.setText(resourceMap.getString("jLabel8.text",
				new Object[0]));
		this.jLabel8.setName("jLabel8");
		this.Num_NR
				.setText(resourceMap.getString("Num_NR.text", new Object[0]));
		this.Num_NR.setName("Num_NR");
		this.jLabel10.setText(resourceMap.getString("jLabel10.text",
				new Object[0]));
		this.jLabel10.setName("jLabel10");
		this.Num_Total.setText(resourceMap.getString("Num_Total.text",
				new Object[0]));
		this.Num_Total.setName("Num_Total");
		this.jSeparator1.setName("jSeparator1");
		GroupLayout jPanel5Layout = new GroupLayout(this.jPanel5);
		this.jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout
				.setHorizontalGroup(jPanel5Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel5Layout
										.createSequentialGroup()
										.addGroup(
												jPanel5Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel5Layout
																		.createSequentialGroup()
																		.addGap(10,
																				10,
																				10)
																		.addGroup(
																				jPanel5Layout
																						.createParallelGroup(
																								GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel5Layout
																										.createSequentialGroup()
																										.addComponent(
																												this.jLabel2,
																												-2,
																												33,
																												-2)
																										.addPreferredGap(
																												LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												this.Num_Open,
																												-2,
																												20,
																												-2))
																						.addGroup(
																								jPanel5Layout
																										.createSequentialGroup()
																										.addGap(10,
																												10,
																												10)
																										.addComponent(
																												this.jLabel6)
																										.addGap(6,
																												6,
																												6)
																										.addComponent(
																												this.Num_NA)))
																		.addGap(18,
																				18,
																				18)
																		.addGroup(
																				jPanel5Layout
																						.createParallelGroup(
																								GroupLayout.Alignment.LEADING)
																						.addComponent(
																								this.jLabel8)
																						.addComponent(
																								this.jLabel4)))
														.addGroup(
																jPanel5Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				this.jLabel10)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				this.Num_Total)))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel5Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																this.Num_NotAFinding)
														.addComponent(
																this.Num_NR))
										.addContainerGap())
						.addComponent(this.jSeparator1,
								GroupLayout.Alignment.TRAILING, -1, 192, 32767));
		jPanel5Layout
				.setVerticalGroup(jPanel5Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel5Layout
										.createSequentialGroup()
										.addGroup(
												jPanel5Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																this.jLabel2)
														.addComponent(
																this.Num_Open,
																-2, 14, -2)
														.addComponent(
																this.jLabel4)
														.addComponent(
																this.Num_NotAFinding))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel5Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																this.jLabel6)
														.addComponent(
																this.Num_NA)
														.addComponent(
																this.jLabel8,
																-2, 14, -2)
														.addComponent(
																this.Num_NR))
										.addGap(8, 8, 8)
										.addComponent(this.jSeparator1, -2, -1,
												-2)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED,
												-1, 32767)
										.addGroup(
												jPanel5Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																this.jLabel10)
														.addComponent(
																this.Num_Total))));
		this.jTabbedPane1
				.addTab(resourceMap.getString(
						"jPanel5.TabConstraints.tabTitle", new Object[0]),
						this.jPanel5);
		this.jPanel6.setBorder(BorderFactory.createEtchedBorder());
		this.jPanel6.setName("jPanel6");
		this.jLabel3.setText(resourceMap.getString("jLabel3.text",
				new Object[0]));
		this.jLabel3.setName("jLabel3");
		this.Num_Open1.setText(resourceMap.getString("Num_Open1.text",
				new Object[0]));
		this.Num_Open1.setName("Num_Open1");
		this.jLabel5.setText(resourceMap.getString("jLabel5.text",
				new Object[0]));
		this.jLabel5.setName("jLabel5");
		this.Num_NotAFinding1.setText(resourceMap.getString(
				"Num_NotAFinding1.text", new Object[0]));
		this.Num_NotAFinding1.setName("Num_NotAFinding1");
		this.jLabel7.setText(resourceMap.getString("jLabel7.text",
				new Object[0]));
		this.jLabel7.setName("jLabel7");
		this.Num_NA1.setText(resourceMap.getString("Num_NA1.text",
				new Object[0]));
		this.Num_NA1.setName("Num_NA1");
		this.jLabel9.setText(resourceMap.getString("jLabel9.text",
				new Object[0]));
		this.jLabel9.setName("jLabel9");
		this.Num_NR1.setText(resourceMap.getString("Num_NR1.text",
				new Object[0]));
		this.Num_NR1.setName("Num_NR1");
		this.jLabel11.setText(resourceMap.getString("jLabel11.text",
				new Object[0]));
		this.jLabel11.setName("jLabel11");
		this.Num_Total1.setText(resourceMap.getString("Num_Total1.text",
				new Object[0]));
		this.Num_Total1.setName("Num_Total1");
		this.jSeparator2.setName("jSeparator2");
		GroupLayout jPanel6Layout = new GroupLayout(this.jPanel6);
		this.jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout
				.setHorizontalGroup(jPanel6Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel6Layout
										.createSequentialGroup()
										.addGroup(
												jPanel6Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel6Layout
																		.createSequentialGroup()
																		.addGap(10,
																				10,
																				10)
																		.addGroup(
																				jPanel6Layout
																						.createParallelGroup(
																								GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel6Layout
																										.createSequentialGroup()
																										.addComponent(
																												this.jLabel3,
																												-2,
																												33,
																												-2)
																										.addPreferredGap(
																												LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												this.Num_Open1,
																												-2,
																												20,
																												-2))
																						.addGroup(
																								jPanel6Layout
																										.createSequentialGroup()
																										.addGap(10,
																												10,
																												10)
																										.addComponent(
																												this.jLabel7)
																										.addGap(6,
																												6,
																												6)
																										.addComponent(
																												this.Num_NA1)))
																		.addGap(18,
																				18,
																				18)
																		.addGroup(
																				jPanel6Layout
																						.createParallelGroup(
																								GroupLayout.Alignment.LEADING)
																						.addComponent(
																								this.jLabel9)
																						.addComponent(
																								this.jLabel5)))
														.addGroup(
																jPanel6Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				this.jLabel11)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				this.Num_Total1)))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel6Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																this.Num_NotAFinding1)
														.addComponent(
																this.Num_NR1))
										.addContainerGap())
						.addComponent(this.jSeparator2,
								GroupLayout.Alignment.TRAILING, -1, 192, 32767));
		jPanel6Layout
				.setVerticalGroup(jPanel6Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel6Layout
										.createSequentialGroup()
										.addGroup(
												jPanel6Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																this.jLabel3)
														.addComponent(
																this.Num_Open1,
																-2, 14, -2)
														.addComponent(
																this.jLabel5)
														.addComponent(
																this.Num_NotAFinding1))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel6Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																this.jLabel7)
														.addComponent(
																this.Num_NA1)
														.addComponent(
																this.jLabel9,
																-2, 14, -2)
														.addComponent(
																this.Num_NR1))
										.addGap(8, 8, 8)
										.addComponent(this.jSeparator2, -2, -1,
												-2)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED,
												-1, 32767)
										.addGroup(
												jPanel6Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																this.jLabel11)
														.addComponent(
																this.Num_Total1))));
		this.jTabbedPane1
				.addTab(resourceMap.getString(
						"jPanel6.TabConstraints.tabTitle", new Object[0]),
						this.jPanel6);
		this.jPanel7.setBorder(BorderFactory.createEtchedBorder());
		this.jPanel7.setName("jPanel7");
		this.jLabel12.setText(resourceMap.getString("jLabel12.text",
				new Object[0]));
		this.jLabel12.setName("jLabel12");
		this.Num_Open2.setText(resourceMap.getString("Num_Open2.text",
				new Object[0]));
		this.Num_Open2.setName("Num_Open2");
		this.jLabel13.setText(resourceMap.getString("jLabel13.text",
				new Object[0]));
		this.jLabel13.setName("jLabel13");
		this.Num_NotAFinding2.setText(resourceMap.getString(
				"Num_NotAFinding2.text", new Object[0]));
		this.Num_NotAFinding2.setName("Num_NotAFinding2");
		this.jLabel14.setText(resourceMap.getString("jLabel14.text",
				new Object[0]));
		this.jLabel14.setName("jLabel14");
		this.Num_NA2.setText(resourceMap.getString("Num_NA2.text",
				new Object[0]));
		this.Num_NA2.setName("Num_NA2");
		this.jLabel15.setText(resourceMap.getString("jLabel15.text",
				new Object[0]));
		this.jLabel15.setName("jLabel15");
		this.Num_NR2.setText(resourceMap.getString("Num_NR2.text",
				new Object[0]));
		this.Num_NR2.setName("Num_NR2");
		this.jLabel16.setText(resourceMap.getString("jLabel16.text",
				new Object[0]));
		this.jLabel16.setName("jLabel16");
		this.Num_Total2.setText(resourceMap.getString("Num_Total2.text",
				new Object[0]));
		this.Num_Total2.setName("Num_Total2");
		this.jSeparator3.setName("jSeparator3");
		GroupLayout jPanel7Layout = new GroupLayout(this.jPanel7);
		this.jPanel7.setLayout(jPanel7Layout);
		jPanel7Layout
				.setHorizontalGroup(jPanel7Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel7Layout
										.createSequentialGroup()
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel7Layout
																		.createSequentialGroup()
																		.addGap(10,
																				10,
																				10)
																		.addGroup(
																				jPanel7Layout
																						.createParallelGroup(
																								GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel7Layout
																										.createSequentialGroup()
																										.addComponent(
																												this.jLabel12,
																												-2,
																												33,
																												-2)
																										.addPreferredGap(
																												LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												this.Num_Open2,
																												-2,
																												20,
																												-2))
																						.addGroup(
																								jPanel7Layout
																										.createSequentialGroup()
																										.addGap(10,
																												10,
																												10)
																										.addComponent(
																												this.jLabel14)
																										.addGap(6,
																												6,
																												6)
																										.addComponent(
																												this.Num_NA2)))
																		.addGap(18,
																				18,
																				18)
																		.addGroup(
																				jPanel7Layout
																						.createParallelGroup(
																								GroupLayout.Alignment.LEADING)
																						.addComponent(
																								this.jLabel15)
																						.addComponent(
																								this.jLabel13)))
														.addGroup(
																jPanel7Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				this.jLabel16)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				this.Num_Total2)))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																this.Num_NotAFinding2)
														.addComponent(
																this.Num_NR2))
										.addContainerGap())
						.addComponent(this.jSeparator3,
								GroupLayout.Alignment.TRAILING, -1, 192, 32767));
		jPanel7Layout
				.setVerticalGroup(jPanel7Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel7Layout
										.createSequentialGroup()
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																this.jLabel12)
														.addComponent(
																this.Num_Open2,
																-2, 14, -2)
														.addComponent(
																this.jLabel13)
														.addComponent(
																this.Num_NotAFinding2))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																this.jLabel14)
														.addComponent(
																this.Num_NA2)
														.addComponent(
																this.jLabel15,
																-2, 14, -2)
														.addComponent(
																this.Num_NR2))
										.addGap(8, 8, 8)
										.addComponent(this.jSeparator3, -2, -1,
												-2)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED,
												-1, 32767)
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																this.jLabel16)
														.addComponent(
																this.Num_Total2))));
		this.jTabbedPane1
				.addTab(resourceMap.getString(
						"jPanel7.TabConstraints.tabTitle", new Object[0]),
						this.jPanel7);
		this.jPanel8.setBorder(BorderFactory.createEtchedBorder());
		this.jPanel8.setName("jPanel8");
		this.jLabel17.setText(resourceMap.getString("jLabel17.text",
				new Object[0]));
		this.jLabel17.setName("jLabel17");
		this.Num_Open3.setText(resourceMap.getString("Num_Open3.text",
				new Object[0]));
		this.Num_Open3.setName("Num_Open3");
		this.jLabel18.setText(resourceMap.getString("jLabel18.text",
				new Object[0]));
		this.jLabel18.setName("jLabel18");
		this.Num_NotAFinding3.setText(resourceMap.getString(
				"Num_NotAFinding3.text", new Object[0]));
		this.Num_NotAFinding3.setName("Num_NotAFinding3");
		this.jLabel19.setText(resourceMap.getString("jLabel19.text",
				new Object[0]));
		this.jLabel19.setName("jLabel19");
		this.Num_NA3.setText(resourceMap.getString("Num_NA3.text",
				new Object[0]));
		this.Num_NA3.setName("Num_NA3");
		this.jLabel20.setText(resourceMap.getString("jLabel20.text",
				new Object[0]));
		this.jLabel20.setName("jLabel20");
		this.Num_NR3.setText(resourceMap.getString("Num_NR3.text",
				new Object[0]));
		this.Num_NR3.setName("Num_NR3");
		this.jLabel21.setText(resourceMap.getString("jLabel21.text",
				new Object[0]));
		this.jLabel21.setName("jLabel21");
		this.Num_Total3.setText(resourceMap.getString("Num_Total3.text",
				new Object[0]));
		this.Num_Total3.setName("Num_Total3");
		this.jSeparator4.setName("jSeparator4");
		GroupLayout jPanel8Layout = new GroupLayout(this.jPanel8);
		this.jPanel8.setLayout(jPanel8Layout);
		jPanel8Layout
				.setHorizontalGroup(jPanel8Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel8Layout
										.createSequentialGroup()
										.addGroup(
												jPanel8Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel8Layout
																		.createSequentialGroup()
																		.addGap(10,
																				10,
																				10)
																		.addGroup(
																				jPanel8Layout
																						.createParallelGroup(
																								GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel8Layout
																										.createSequentialGroup()
																										.addComponent(
																												this.jLabel17,
																												-2,
																												33,
																												-2)
																										.addPreferredGap(
																												LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												this.Num_Open3,
																												-2,
																												20,
																												-2))
																						.addGroup(
																								jPanel8Layout
																										.createSequentialGroup()
																										.addGap(10,
																												10,
																												10)
																										.addComponent(
																												this.jLabel19)
																										.addGap(6,
																												6,
																												6)
																										.addComponent(
																												this.Num_NA3)))
																		.addGap(18,
																				18,
																				18)
																		.addGroup(
																				jPanel8Layout
																						.createParallelGroup(
																								GroupLayout.Alignment.LEADING)
																						.addComponent(
																								this.jLabel20)
																						.addComponent(
																								this.jLabel18)))
														.addGroup(
																jPanel8Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				this.jLabel21)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				this.Num_Total3)))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel8Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																this.Num_NotAFinding3)
														.addComponent(
																this.Num_NR3))
										.addContainerGap())
						.addComponent(this.jSeparator4,
								GroupLayout.Alignment.TRAILING, -1, 192, 32767));
		jPanel8Layout
				.setVerticalGroup(jPanel8Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel8Layout
										.createSequentialGroup()
										.addGroup(
												jPanel8Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																this.jLabel17)
														.addComponent(
																this.Num_Open3,
																-2, 14, -2)
														.addComponent(
																this.jLabel18)
														.addComponent(
																this.Num_NotAFinding3))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel8Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																this.jLabel19)
														.addComponent(
																this.Num_NA3)
														.addComponent(
																this.jLabel20,
																-2, 14, -2)
														.addComponent(
																this.Num_NR3))
										.addGap(8, 8, 8)
										.addComponent(this.jSeparator4, -2, -1,
												-2)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED,
												-1, 32767)
										.addGroup(
												jPanel8Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																this.jLabel21)
														.addComponent(
																this.Num_Total3))));
		this.jTabbedPane1
				.addTab(resourceMap.getString(
						"jPanel8.TabConstraints.tabTitle", new Object[0]),
						this.jPanel8);
		this.jTabbedPane2.setToolTipText(resourceMap.getString(
				"jTabbedPane2.toolTipText", new Object[0]));
		this.jTabbedPane2.setEnabled(false);
		this.jTabbedPane2.setName("jTabbedPane2");
		this.jPanel4.setBorder(BorderFactory.createEtchedBorder(0));
		this.jPanel4.setName("jPanel4");
		this.AssetIDTabs.setName("AssetIDTabs");
		this.jPanel3.setBorder(BorderFactory.createEtchedBorder());
		this.jPanel3.setName("jPanel3");
		this.jLabel1.setText(resourceMap.getString("jLabel1.text",
				new Object[0]));
		this.jLabel1.setName("jLabel1");
		this.TargetIP.setText(resourceMap.getString("TargetIP.text",
				new Object[0]));
		this.TargetIP.setName("TargetIP");
		this.TargetIP.addFocusListener(new FocusAdapter() {

			public void focusGained(FocusEvent evt) {
				ModCL.this.TargetIPFocusGained(evt);
			}

			public void focusLost(FocusEvent evt) {
				ModCL.this.TargetIPFocusLost(evt);
			}
		});
		this.TargetMAC.setText(resourceMap.getString("TargetMAC.text",
				new Object[0]));
		this.TargetMAC.setName("TargetMAC");
		this.TargetMAC.addFocusListener(new FocusAdapter() {

			public void focusGained(FocusEvent evt) {
				ModCL.this.TargetMACFocusGained(evt);
			}

			public void focusLost(FocusEvent evt) {
				ModCL.this.TargetMACFocusLost(evt);
			}
		});
		this.TargetHostName.setText(resourceMap.getString(
				"TargetHostName.text", new Object[0]));
		this.TargetHostName.setName("TargetHostName");
		this.TargetHostName.addFocusListener(new FocusAdapter() {

			public void focusGained(FocusEvent evt) {
				ModCL.this.TargetHostNameFocusGained(evt);
			}

			public void focusLost(FocusEvent evt) {
				ModCL.this.TargetHostNameFocusLost(evt);
			}
		});
		this.jButton1.setText(resourceMap.getString("jButton1.text",
				new Object[0]));
		this.jButton1.setName("jButton1");
		this.jButton1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL.this.jButton1ActionPerformed(evt);
			}
		});
		this.TargetRole.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Role", "Workstation", "Member Server", "Domain Controller" }));
		this.TargetRole.setName("TargetRole");
		this.TargetRole.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL.this.TargetRoleActionPerformed(evt);
			}
		});
		GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
		this.jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout
				.setHorizontalGroup(jPanel3Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																this.jLabel1,
																GroupLayout.Alignment.TRAILING,
																-1, 173, 32767)
														.addGroup(
																GroupLayout.Alignment.TRAILING,
																jPanel3Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel3Layout
																						.createParallelGroup(
																								GroupLayout.Alignment.TRAILING)
																						.addComponent(
																								this.TargetMAC,
																								GroupLayout.Alignment.LEADING,
																								-1,
																								163,
																								32767)
																						.addComponent(
																								this.TargetIP,
																								GroupLayout.Alignment.LEADING,
																								-1,
																								163,
																								32767)
																						.addComponent(
																								this.TargetHostName,
																								GroupLayout.Alignment.LEADING,
																								-1,
																								163,
																								32767))
																		.addContainerGap())
														.addGroup(
																jPanel3Layout
																		.createSequentialGroup()
																		.addComponent(
																				this.TargetRole,
																				0,
																				163,
																				32767)
																		.addContainerGap())
														.addGroup(
																jPanel3Layout
																		.createSequentialGroup()
																		.addComponent(
																				this.jButton1,
																				-1,
																				163,
																				32767)
																		.addContainerGap()))));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel3Layout
								.createSequentialGroup()
								.addComponent(this.jLabel1)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.TargetHostName, -2, -1, -2)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.TargetIP, -2, 20, -2)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.TargetMAC, -2, -1, -2)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.TargetRole, -2, -1, -2)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.jButton1)
								.addContainerGap(-1, 32767)));
		this.AssetIDTabs
				.addTab(resourceMap.getString(
						"jPanel3.TabConstraints.tabTitle", new Object[0]),
						this.jPanel3);
		this.jPanel9.setName("jPanel9");
		this.NC_TargetName.setText(resourceMap.getString("NC_TargetName.text",
				new Object[0]));
		this.NC_TargetName.setName("NC_TargetName");
		this.NC_TargetName.addFocusListener(new FocusAdapter() {

			public void focusGained(FocusEvent evt) {
				ModCL.this.NC_TargetNameFocusGained(evt);
			}

			public void focusLost(FocusEvent evt) {
				ModCL.this.NC_TargetNameFocusLost(evt);
			}
		});
		GroupLayout jPanel9Layout = new GroupLayout(this.jPanel9);
		this.jPanel9.setLayout(jPanel9Layout);
		jPanel9Layout.setHorizontalGroup(jPanel9Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				jPanel9Layout.createSequentialGroup().addContainerGap()
						.addComponent(this.NC_TargetName, -1, 167, 32767)
						.addContainerGap()));
		jPanel9Layout.setVerticalGroup(jPanel9Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				jPanel9Layout.createSequentialGroup().addContainerGap()
						.addComponent(this.NC_TargetName, -2, -1, -2)
						.addContainerGap(129, 32767)));
		this.AssetIDTabs
				.addTab(resourceMap.getString(
						"jPanel9.TabConstraints.tabTitle", new Object[0]),
						this.jPanel9);
		GroupLayout jPanel4Layout = new GroupLayout(this.jPanel4);
		this.jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(this.AssetIDTabs,
				-1, 192, 32767));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				jPanel4Layout.createSequentialGroup()
						.addComponent(this.AssetIDTabs, -2, 188, -2)
						.addGap(81, 81, 81)));
		this.jTabbedPane2
				.addTab(resourceMap.getString(
						"jPanel4.TabConstraints.tabTitle", new Object[0]),
						this.jPanel4);
		this.jPanel12.setName("jPanel12");
		this.jTextField1.setText(resourceMap.getString("jTextField1.text",
				new Object[0]));
		this.jTextField1.setName("jTextField1");
		this.jTextField2.setText(resourceMap.getString("jTextField2.text",
				new Object[0]));
		this.jTextField2.setName("jTextField2");
		this.jTextField3.setText(resourceMap.getString("jTextField3.text",
				new Object[0]));
		this.jTextField3.setName("jTextField3");
		this.jTextField4.setText(resourceMap.getString("jTextField4.text",
				new Object[0]));
		this.jTextField4.setName("jTextField4");
		this.jTextField5.setText(resourceMap.getString("jTextField5.text",
				new Object[0]));
		this.jTextField5.setName("jTextField5");
		this.jTextField6.setText(resourceMap.getString("jTextField6.text",
				new Object[0]));
		this.jTextField6.setName("jTextField6");
		this.jTextField7.setText(resourceMap.getString("jTextField7.text",
				new Object[0]));
		this.jTextField7.setName("jTextField7");
		this.jTextField8.setText(resourceMap.getString("jTextField8.text",
				new Object[0]));
		this.jTextField8.setName("jTextField8");
		this.jTextField9.setText(resourceMap.getString("jTextField9.text",
				new Object[0]));
		this.jTextField9.setName("jTextField9");
		GroupLayout jPanel12Layout = new GroupLayout(this.jPanel12);
		this.jPanel12.setLayout(jPanel12Layout);
		jPanel12Layout
				.setHorizontalGroup(jPanel12Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel12Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel12Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																this.jTextField2,
																-1, 176, 32767)
														.addComponent(
																this.jTextField1,
																-1, 176, 32767)
														.addComponent(
																this.jTextField3,
																-1, 176, 32767)
														.addComponent(
																this.jTextField4,
																-1, 176, 32767)
														.addComponent(
																this.jTextField5,
																-1, 176, 32767)
														.addComponent(
																this.jTextField6,
																-1, 176, 32767)
														.addComponent(
																this.jTextField7,
																-1, 176, 32767)
														.addComponent(
																this.jTextField8,
																-1, 176, 32767)
														.addComponent(
																this.jTextField9,
																-1, 176, 32767))
										.addContainerGap()));
		jPanel12Layout.setVerticalGroup(jPanel12Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel12Layout
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(this.jTextField1, -2, -1, -2)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.jTextField2, -2, -1, -2)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.jTextField3, -2, -1, -2)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.jTextField4, -2, -1, -2)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.jTextField5, -2, -1, -2)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.jTextField6, -2, -1, -2)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.jTextField7, -2, -1, -2)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.jTextField8, -2, -1, -2)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.jTextField9, -2, -1, -2)
								.addContainerGap(-1, 32767)));
		this.jTabbedPane2.addTab(resourceMap.getString(
				"jPanel12.TabConstraints.tabTitle", new Object[0]),
				this.jPanel12);
		GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
		this.jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(this.jTabbedPane2,
						GroupLayout.Alignment.TRAILING, -1, 201, 32767)
				.addComponent(this.jTabbedPane1, -1, 201, 32767)
				.addComponent(this.jPanel2, GroupLayout.Alignment.TRAILING, -1,
						201, 32767));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING)
				.addGroup(
						GroupLayout.Alignment.TRAILING,
						jPanel1Layout
								.createSequentialGroup()
								.addComponent(this.jPanel2, -1, 299, 32767)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.jTabbedPane1, -2, 96, -2)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.jTabbedPane2, -2, 220, -2)));
		this.jSplitPane1.setLeftComponent(this.jPanel1);
		this.SwapPanel.setName("SwapPanel");
		GroupLayout SwapPanelLayout = new GroupLayout(this.SwapPanel);
		this.SwapPanel.setLayout(SwapPanelLayout);
		SwapPanelLayout.setHorizontalGroup(SwapPanelLayout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGap(0, 541, 32767));
		SwapPanelLayout.setVerticalGroup(SwapPanelLayout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGap(0, 627, 32767));
		this.jSplitPane1.setRightComponent(this.SwapPanel);
		this.jMenuBar1.setName("jMenuBar1");
		this.File.setText(resourceMap.getString("File.text", new Object[0]));
		this.File.setName("File");
		this.jMenuItem1.setText(resourceMap.getString("jMenuItem1.text",
				new Object[0]));
		this.jMenuItem1.setName("jMenuItem1");
		this.jMenuItem1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL.this.jMenuItem1ActionPerformed(evt);
			}
		});
		this.File.add(this.jMenuItem1);
		this.SaveChecklist.setAccelerator(KeyStroke.getKeyStroke(83, 2));
		this.SaveChecklist.setText(resourceMap.getString("SaveChecklist.text",
				new Object[0]));
		this.SaveChecklist.setName("SaveChecklist");
		this.SaveChecklist.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL.this.SaveChecklistActionPerformed(evt);
			}
		});
		this.File.add(this.SaveChecklist);
		this.SaveChecklistAs.setText(resourceMap.getString(
				"SaveChecklistAs.text", new Object[0]));
		this.SaveChecklistAs.setName("SaveChecklistAs");
		this.SaveChecklistAs.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL.this.SaveChecklistAsActionPerformed(evt);
			}
		});
		this.File.add(this.SaveChecklistAs);
		this.OpenChecklist.setText(resourceMap.getString("OpenChecklist.text",
				new Object[0]));
		this.OpenChecklist.setName("OpenChecklist");
		this.OpenChecklist.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL.this.OpenChecklistActionPerformed(evt);
			}
		});
		this.File.add(this.OpenChecklist);
		this.Print_Checklist.setText(resourceMap.getString(
				"Print_Checklist.text", new Object[0]));
		this.Print_Checklist.setName("Print_Checklist");
		this.Print_Checklist.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL.this.Print_ChecklistActionPerformed(evt);
			}
		});
		this.File.add(this.Print_Checklist);
		this.jMenuBar1.add(this.File);
		this.Import
				.setText(resourceMap.getString("Import.text", new Object[0]));
		this.Import.setName("Import");
		this.Import_XCCDF_Res.setText(resourceMap.getString(
				"Import_XCCDF_Res.text", new Object[0]));
		this.Import_XCCDF_Res.setName("Import_XCCDF_Res");
		this.Import_XCCDF_Res.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL.this.Import_XCCDF_ResActionPerformed(evt);
			}
		});
		this.Import.add(this.Import_XCCDF_Res);
		this.Import_RetVMS_Res.setText(resourceMap.getString(
				"Import_RetVMS_Res.text", new Object[0]));
		this.Import_RetVMS_Res.setName("Import_RetVMS_Res");
		this.Import_RetVMS_Res.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL.this.Import_RetVMS_ResActionPerformed(evt);
			}
		});
		this.Import.add(this.Import_RetVMS_Res);
		this.jMenuBar1.add(this.Import);
		this.Export
				.setText(resourceMap.getString("Export.text", new Object[0]));
		this.Export_VMS.setText(resourceMap.getString("Export_VMS.text",
				new Object[0]));
		this.Export_VMS.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL.this.Export_VMSActionPerformed(evt);
			}
		});
		this.Export.add(this.Export_VMS);
		this.Export_ARFASR.setText(resourceMap.getString("Export_ARFASR.text",
				new Object[0]));
		this.Export_ARFASR.setEnabled(false);
		this.Export_ARFASR.setName("Export_ARFASR");
		this.Export_ARFASR.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL.this.Export_ARFASRActionPerformed(evt);
			}
		});
		this.Export.add(this.Export_ARFASR);
		this.GenerateChecklist_SF.setText(resourceMap.getString(
				"GenerateChecklist_SF.text", new Object[0]));
		this.GenerateChecklist_SF.setName("GenerateChecklist_SF");
		this.GenerateChecklist_SF.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL.this.GenerateChecklist_SFActionPerformed(evt);
			}
		});
		this.Export.add(this.GenerateChecklist_SF);
		this.Export_CSV.setText(resourceMap.getString("Export_CSV.text",
				new Object[0]));
		this.Export_CSV.setName("Export_CSV");
		this.Export_CSV.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL.this.Export_CSVActionPerformed(evt);
			}
		});
		this.Export.add(this.Export_CSV);
		this.jMenuBar1.add(this.Export);
		this.Options.setText(resourceMap.getString("Options.text",
				new Object[0]));
		this.Options.setName("Options");
		this.Settings.setText(resourceMap.getString("Settings.text",
				new Object[0]));
		this.Settings.setName("Settings");
		this.Settings.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL.this.SettingsActionPerformed(evt);
			}
		});
		this.Options.add(this.Settings);
		this.RefreshDisplay.setAccelerator(KeyStroke.getKeyStroke(116, 0));
		this.RefreshDisplay.setText(resourceMap.getString(
				"RefreshDisplay.text", new Object[0]));
		this.RefreshDisplay.setName("RefreshDisplay");
		this.RefreshDisplay.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL.this.RefreshDisplayActionPerformed(evt);
			}
		});
		this.Options.add(this.RefreshDisplay);
		this.ChangeAssetPostures.setText(resourceMap.getString(
				"ChangeAssetPostures.text", new Object[0]));
		this.ChangeAssetPostures.setName("ChangeAssetPostures");
		this.ChangeAssetPostures.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				ModCL.this.ChangeAssetPosturesActionPerformed(evt);
			}
		});
		this.Options.add(this.ChangeAssetPostures);
		this.jMenuBar1.add(this.Options);
		this.setJMenuBar(this.jMenuBar1);
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(this.jSplitPane1,
				-1, 749, 32767));
		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(this.jSplitPane1,
				-1, 629, 32767));
		this.pack();
	}

	private void jButton1ActionPerformed(ActionEvent evt) {
		GetTargetData gtd = new GetTargetData(this);
		gtd.setLocation(this.getX() + 50, this.getY() + 50);
		gtd.setVisible(true);
	}

	@Override
	public void PostData(String[] sIn) {
		if (sIn.length == 3) {
			this.TargetHostName.setText(sIn[0]);
			this.TargetIP.setText(sIn[1]);
			this.TargetMAC.setText(sIn[2]);
			this.SetIsSaved(false);
		}
	}

	@Override
	public void PostKey(String sKey) {
	}

	@Override
	public ArrayList<Vuln> SVL_Update() {
		ArrayList<Vuln> vaBadList = new ArrayList<Vuln>();
		for (int i = 0; i < this.myChecklist.getVulnListSize(); ++i) {
			Vuln v = this.myChecklist.getVuln(i);
			if (v.getCheckState() != SV_Checklist.CheckState.Open
					|| !v.getCHK_Notes().equals(""))
				continue;
			vaBadList.add(v);
		}
		return vaBadList;
	}

	@Override
	public void SVL_Select(Vuln v) {
		int iRet = this.myChecklist.getIndexOf(v);
		if (iRet >= 0) {
			this.DisplayList.setSelectedIndex(iRet);
			this.DisplayList.SetCarot(iRet);
		}
	}

	private boolean bReadyForExport() {
		boolean bRet = true;
		boolean bMissingFindingDetails = false;
		boolean bMissingAssetPostures = false;
		ArrayList<Vuln> vaBadList = new ArrayList<Vuln>();
		ArrayList<String> saBL = new ArrayList<String>();
		for (int i = 0; i < this.myChecklist.getRawListSize(); ++i) {
			Vuln v = this.myChecklist.getRL_Vuln(i);
			if (v.getCheckState() == SV_Checklist.CheckState.Open
					&& v.getCHK_Notes().equals("")) {
				vaBadList.add(v);
				saBL.add(v.getAttr(Vuln.VulnAttr.Vuln_Num));
				bRet = false;
				bMissingFindingDetails = true;
			}
			if (!v.getAttr(Vuln.VulnAttr.TargetKey).equals(""))
				continue;
			bRet = false;
			bMissingAssetPostures = true;
		}
		if (!bRet) {
			if (bMissingFindingDetails) {
				SimpleVulnList vList = new SimpleVulnList(
						vaBadList,
						null,
						this.myChecklist.getSortType(),
						"<html>All Open Findings must have Finding Details.  Please fill in Details for the following Checks:</html>",
						this, true);
				vList.setLocation(this.getX() + 100, this.getY() + 100);
			}
			if (bMissingAssetPostures) {
				Util.SetupAssetKeys(this, this.myChecklist.getRawList(), false);
			}
		}
		return bRet;
	}

	private void Export_VMSActionPerformed(ActionEvent evt) {
		if (this.bReadyForExport()) {
			JFileChooser fSel = new JFileChooser();
			fSel.setApproveButtonText("Export");
			FileFilterFactory xmlFilter = new FileFilterFactory("XML Files",
					"xml");
			fSel.setFileFilter(xmlFilter);
			int iRet = fSel.showSaveDialog(this);
			if (iRet == 0) {
				File fOutput = fSel.getSelectedFile();
				if (fOutput.getName().contains((CharSequence) ".")) {
					if (!fOutput.getName().contains((CharSequence) ".xml")) {
						JOptionPane.showMessageDialog(this,
								"Incorrect Extension! Export Aborted.");
					} else {
						this.ExportVMS(fOutput);
					}
				} else {
					String sFile = fOutput.getPath() + ".xml";
					fOutput = new File(sFile);
					this.ExportVMS(fOutput);
				}
			}
		}
	}

	private void jComboBox1ActionPerformed(ActionEvent evt) {
		String sSort = (String) this.jComboBox1.getSelectedItem();
		this.myChecklist.Sort(sSort);
		this.RedrawDisplayList();
		this.DisplayList.setSelectedIndex(0);
	}

	private void jMenuItem1ActionPerformed(ActionEvent evt) {
		this.myParent.getFrame().setVisible(true);
		this.dispose();
	}

	private void TargetHostNameFocusLost(FocusEvent evt) {
		if (this.TargetHostName.getText().equals("")) {
			this.TargetHostName.setText("HOST NAME");
		}
	}

	private void Import_XCCDF_ResActionPerformed(ActionEvent evt) {
		JFileChooser fSel = new JFileChooser();
		FileFilterFactory xmlFilter = new FileFilterFactory("XML Files", "xml");
		fSel.setFileFilter(xmlFilter);
		fSel.setApproveButtonText("Import");
		int iRet = fSel.showOpenDialog(this);
		if (iRet == 0) {
			File fIn = fSel.getSelectedFile();
			XCCDF_RES xccdfRes = new XCCDF_RES(fIn);
			this.TargetHostName.setText(xccdfRes.getsPrimary_Host_Name());
			if (xccdfRes.getsIP_Address().size() == 1) {
				this.TargetIP.setText(xccdfRes.getsIP_Address().get(0));
			}
			if (xccdfRes.getsMAC_Address().size() == 1) {
				this.TargetMAC.setText(xccdfRes.getsMAC_Address().get(0));
			}
			this.ProcessResults(xccdfRes.GetResults());
		}
		this.RedrawDisplayList();
	}

	private void SetStatesFromList(int[] ia, SV_Checklist.CheckState state) {
		for (int i = 0; i < ia.length; ++i) {
			this.myChecklist.setState(ia[i], state);
		}
		this.SetIsSaved(false);
		this.RedrawDisplayList();
		this.bEnableCL_Selecetion_EH = false;
		this.DisplayList.setSelectedIndecies(ia);
		this.bEnableCL_Selecetion_EH = true;
	}

	private void SetOpenActionPerformed(ActionEvent evt) {
		this.SetStatesFromList(this.DisplayList.getSelectedIndecies(),
				SV_Checklist.CheckState.Open);
	}

	private void SetNotAFindingActionPerformed(ActionEvent evt) {
		this.SetStatesFromList(this.DisplayList.getSelectedIndecies(),
				SV_Checklist.CheckState.NotAFinding);
	}

	private void SetNotApplicableActionPerformed(ActionEvent evt) {
		this.SetStatesFromList(this.DisplayList.getSelectedIndecies(),
				SV_Checklist.CheckState.Not_Applicable);
	}

	private void SetNotReviewedActionPerformed(ActionEvent evt) {
		this.SetStatesFromList(this.DisplayList.getSelectedIndecies(),
				SV_Checklist.CheckState.Not_Reviewed);
	}

	private void Print_ChecklistActionPerformed(ActionEvent evt) {
		Export_ChecklistHTML EC = new Export_ChecklistHTML(
				this.myChecklist.getRawList());
		Vuln.VulnAttr type = this.myChecklist.getSortType().equals(
				(Object) STIGSearch.SortType.RULEID) ? Vuln.VulnAttr.Rule_ID
				: (this.myChecklist.getSortType().equals(
						(Object) STIGSearch.SortType.STIGID) ? Vuln.VulnAttr.Rule_Ver
						: Vuln.VulnAttr.Vuln_Num);
		Export_Print Printer = new Export_Print(EC.GetHTMLString(type,
				this.myChecklist.GetSTIGTitle()));
		Printer.setLocation(this.getX() + 50, this.getY() + 50);
		Printer.setVisible(true);
	}

	private void GenerateChecklist_SFActionPerformed(ActionEvent evt) {
		JFileChooser fSel = new JFileChooser();
		fSel.setApproveButtonText("Export");
		FileFilterFactory htmlfilter = new FileFilterFactory("HTML Files",
				"html");
		fSel.setFileFilter(htmlfilter);
		int iRet = fSel.showSaveDialog(this);
		if (iRet == 0) {
			File fOutput = fSel.getSelectedFile();
			if (fOutput.getName().contains((CharSequence) ".")) {
				if (!fOutput.getName().contains((CharSequence) ".html")) {
					JOptionPane.showMessageDialog(this,
							"Incorrect Extension! Export Aborted.");
				} else {
					this.ExportHTMLSFCL(fOutput);
				}
			} else {
				String sFile = fOutput.getPath() + ".html";
				fOutput = new File(sFile);
				this.ExportHTMLSFCL(fOutput);
			}
		}
	}

	private void SaveChecklist(File f) {
		SaveFile_SI SF = new SaveFile_SI(f);
		SaveFile_SI.Asset OAsset = new SaveFile_SI.Asset();
		OAsset.AssetType = this.AssetIDTabs.getTitleAt(this.AssetIDTabs
				.getSelectedIndex());
		if (OAsset.AssetType.equals("Computing")) {
			OAsset.HostIP = this.TargetIP.getText();
			OAsset.HostMAC = this.TargetMAC.getText();
			OAsset.HostName = this.TargetHostName.getText();
		} else if (OAsset.AssetType.equals("Non-Computing")) {
			OAsset.HostName = this.NC_TargetName.getText();
		}
		OAsset.Role = this.TargetRole.getSelectedItem().toString();
		try {
			SF.WriteCheckList(OAsset, this.myChecklist.getRawList(),
					this.myChecklist.GetSTIGTitle());
			this.SetIsSaved(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,
					"Error writing checklist save file.");
		}
	}

	private void SaveChecklistActionPerformed(ActionEvent evt) {
		if (this.fMySaveFile != null) {
			this.SaveChecklist(this.fMySaveFile);
		} else {
			this.SaveChecklistAsActionPerformed(null);
		}
	}

	private void SaveChecklistAsActionPerformed(ActionEvent evt) {
		JFileChooser fSel = new JFileChooser();
		fSel.setApproveButtonText("Save");
		FileFilterFactory htmlfilter = new FileFilterFactory("Checklist Files",
				"ckl");
		fSel.setFileFilter(htmlfilter);
		int iRet = fSel.showSaveDialog(this);
		if (iRet == 0) {
			File fOutput = fSel.getSelectedFile();
			if (fOutput.getName().contains((CharSequence) ".")) {
				if (!fOutput.getName().contains((CharSequence) ".ckl")) {
					JOptionPane.showMessageDialog(this,
							"Incorrect Extension! Export Aborted.");
				} else {
					this.fMySaveFile = fOutput;
					this.SaveChecklist(fOutput);
				}
			} else {
				String sFile = fOutput.getPath() + ".ckl";
				this.fMySaveFile = fOutput = new File(sFile);
				this.SaveChecklist(fOutput);
			}
		}
	}

	private void LoadChecklist(File f) {
		SaveFile_SI CLSave = new SaveFile_SI(f);
		CLSave.LoadFromSave();
		boolean bHasCCI = false;
		if (CLSave != null) {
			ArrayList<Vuln> avList = CLSave.GetVulnList();
			for (Vuln v : avList) {
				for (String s : v.GetCCIVals()) {
					CCIReader.CCI_Store tmp = this.myCCI.getCCIbyName(s);
					if (tmp == null)
						continue;
					v.addCCI(tmp);
					if (bHasCCI)
						continue;
					bHasCCI = true;
				}
			}
			SV_Checklist chk = new SV_Checklist(avList, CLSave.GetSTIGName(),
					false, this.myConfig);
			ModCL modCL = new ModCL(ModCL_Mode.Checklist, this.myConfig, chk,
					this.myCCI, this.myParent, CLSave.GetAsset(), f);
			ArrayList<String> s = new ArrayList<String>();
			s.add("STIG ID");
			s.add("VUL ID");
			s.add("RULE ID");
			if (bHasCCI) {
				s.add("CCI ID");
			}
			modCL.setSortList(s);
			modCL.setLocation(this.getX() + 50, this.getY() + 50);
			modCL.setVisible(true);
		} else if (Util.bAllowPrintln) {
			System.out.println("Checklist Load Error : CLSave is null!");
		}
	}

	private void OpenChecklistActionPerformed(ActionEvent evt) {
		JFileChooser fSel = new JFileChooser();
		FileFilterFactory xmlFilter = new FileFilterFactory("CheckList Files",
				"ckl");
		fSel.setFileFilter(xmlFilter);
		fSel.setApproveButtonText("Open");
		int iRet = fSel.showOpenDialog(this);
		if (iRet == 0) {
			File fIn = fSel.getSelectedFile();
			this.LoadChecklist(fIn);
		}
	}

	private void TargetHostNameFocusGained(FocusEvent evt) {
		if (this.TargetHostName.getText().equals("HOST NAME")) {
			this.TargetHostName.setText("");
		}
	}

	private void TargetIPFocusGained(FocusEvent evt) {
		if (this.TargetIP.getText().equals("IP")) {
			this.TargetIP.setText("");
		}
	}

	private void TargetIPFocusLost(FocusEvent evt) {
		if (this.TargetIP.getText().equals("")) {
			this.TargetIP.setText("IP");
		}
	}

	private void TargetMACFocusGained(FocusEvent evt) {
		if (this.TargetMAC.getText().equals("MAC")) {
			this.TargetMAC.setText("");
		}
	}

	private void TargetMACFocusLost(FocusEvent evt) {
		if (this.TargetMAC.getText().equals("")) {
			this.TargetMAC.setText("MAC");
		}
	}

	private void formWindowClosing(WindowEvent evt) {
		int iRet;
		if (this.bChangedSinceSave
				&& (iRet = JOptionPane
						.showConfirmDialog(
								this,
								"This Checklist has not been saved. Save Before Closing?",
								"Save Checklist", 0)) == 0) {
			this.SaveChecklistActionPerformed(null);
		}
	}

	private void NC_TargetNameFocusGained(FocusEvent evt) {
		if (this.NC_TargetName.getText().equals("DISPLAY NAME")) {
			this.NC_TargetName.setText("");
		}
	}

	private void NC_TargetNameFocusLost(FocusEvent evt) {
		if (this.NC_TargetName.getText().equals("")) {
			this.NC_TargetName.setText("DISPLAY NAME");
		}
	}

	private void jTabbedPane1MouseClicked(MouseEvent evt) {
	}

	private void jTabbedPane1MousePressed(MouseEvent evt) {
		String s = this.jTabbedPane1.getTitleAt(this.jTabbedPane1
				.getSelectedIndex());
		if (!this.sMetricPreviousValue.equals(s)) {
			if (s.equals("Overview")) {
				this.myChecklist.LoadVulnList(this.myChecklist.getRawList(),
						false);
			} else {
				Filter f = new Filter();
				STIGSearch searcher = new STIGSearch(this.myCCI);
				if (s.equals("CAT I")) {
					f.SetFilterType(Filter.FilterType.CATI);
				} else if (s.equals("CAT II")) {
					f.SetFilterType(Filter.FilterType.CATII);
				} else if (s.equals("CAT III")) {
					f.SetFilterType(Filter.FilterType.CATIII);
				}
				if (f.GetFilterType() != null) {
					this.myChecklist.LoadVulnList(searcher.FilterList(
							this.myChecklist.getRawList(), f), false);
				}
			}
			this.sMetricPreviousValue = s;
			String sSort = (String) this.jComboBox1.getSelectedItem();
			this.myChecklist.Sort(sSort);
			this.RedrawDisplayList();
			this.DisplayList.setSelectedIndex(0);
		}
	}

	private void ExportARFASR(File fARF, File fASR) {
		try {
			ARF_ASR_SI writer = new ARF_ASR_SI(fARF, fASR);
			String sUUID = UUID.randomUUID().toString();
			writer.AddReportObject(this.TargetHostName.getText(), sUUID,
					this.TargetMAC.getText(), this.TargetIP.getText(), "", "");
			writer.AddResults(this.myChecklist.getRawList(),
					this.myChecklist.GetSTIGTitle(), "V");
			writer.WriteARF();
			writer.WriteASR();
		} catch (Exception e) {
			if (Util.bAllowPrintln) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(this, "Error writing ARF/ASR files!");
		}
	}

	private void Export_ARFASRActionPerformed(ActionEvent evt) {
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(1);
		fc.setToolTipText("Choose a directory to which to output your ARF/ASR files.");
		int iResponse = fc.showDialog(this, "Save");
		if (iResponse == 0) {
			String sPath = fc.getSelectedFile().getAbsolutePath();
			String sRootFileName = this.myChecklist.GetSTIGTitle();
			File fARF = new File(sPath + sRootFileName + "_ARF.xml");
			File fASR = new File(sPath + sRootFileName + "_ASR.xml");
			this.ExportARFASR(fARF, fASR);
		}
	}

	private void DisplayListFocusLost(FocusEvent evt) {
		if (this.DisplayList.getSelectedIndex() == -1) {
			this.DisplayList.setSelectedIndex(0);
		}
	}

	private void TargetRoleActionPerformed(ActionEvent evt) {
		this.SetIsSaved(false);
	}

	private void SetStatusSortActionPerformed(ActionEvent evt) {
		if (this.SetStatusSort.isSelected()) {
			this.myChecklist.setSortByStatus(true);
		} else {
			this.myChecklist.setSortByStatus(false);
		}
		this.jComboBox1ActionPerformed(null);
	}

	private void SettingsActionPerformed(ActionEvent evt) {
		this.myConfig.SetConfigDefaultDisplay();
		this.myConfig.setLocation(this.getX() + 50, this.getY() + 50);
		this.myConfig.Show(this);
		this.setEnabled(false);
	}

	private void Export_CSVActionPerformed(ActionEvent evt) {
		CSV_Export_Selector csv = new CSV_Export_Selector(this.myConfig, true);
		csv.SetVulnList(this.myChecklist.getRawList());
		csv.setLocation(this.getX() + 100, this.getY() + 100);
		csv.setVisible(true);
	}

	private void RefreshDisplayActionPerformed(ActionEvent evt) {
		String sSort = (String) this.jComboBox1.getSelectedItem();
		this.myChecklist.Sort(sSort);
		this.RedrawDisplayList();
	}

	private void ProcessRetVMSResults(IMPORTFILE RetVMSRes) {
		int iUpdated = 0;
		List<Object> Results = RetVMSRes.getSCANAndASSET();
		System.out.println("Starting Ret VMS Processing...");
		for (Object o : Results) {
			if (o.getClass() == SCAN.class)
				continue;
			ASSET test = (ASSET) o;
			List<ASSETID> IDs = test.getASSETID();
			boolean bMatch = false;
			for (ASSETID ID : IDs) {
				if (!ID.getTYPE().equals("IP ADDRESS"))
					continue;
				if (Util.bAllowPrintln) {
					System.out.println("Compair: " + ID.getValue() + " : "
							+ this.TargetIP.getText());
				}
				if (!ID.getValue().equals(this.TargetIP.getText()))
					continue;
				bMatch = true;
			}
			if (!bMatch)
				continue;
			TARGET myTarget = test.getTARGET();
			String tKey = myTarget.getTARGETKEY();
			List<FINDING> FINDINGs = myTarget.getFINDING();
			for (FINDING f : FINDINGs) {
				boolean bFindingMatch = false;
				int iMax = this.myChecklist.getVulnListSize();
				for (int iCounter = 0; !(bFindingMatch || iCounter >= iMax); ++iCounter) {
					Vuln v = this.myChecklist.getVulnList().get(iCounter);
					if (!v.getAttr(Vuln.VulnAttr.TargetKey).equals(tKey))
						continue;
					List<FINDINGID> fIDs = f.getFINDINGID();
					for (FINDINGID fID : fIDs) {
						if (!fID.getTYPE().equals("IAVA")
								|| !fID.getValue().equals(
										v.getAttr(Vuln.VulnAttr.Rule_Ver))
								|| !f.getCHECKSUCCESS().equals("True"))
							continue;
						String status = f.getFINDINGSTATUS();
						if (status.equals("O")) {
							v.setCheckState(SV_Checklist.CheckState.Open);
							v.setCHK_Notes(f.getFINDINGDETAILS().getValue());
							++iUpdated;
							continue;
						}
						if (status.equals("NF")) {
							v.setCheckState(SV_Checklist.CheckState.NotAFinding);
							++iUpdated;
							continue;
						}
						if (status.equals("NA")) {
							v.setCheckState(SV_Checklist.CheckState.Not_Applicable);
							++iUpdated;
							continue;
						}
						if (!status.equals("NR"))
							continue;
					}
				}
			}
		}
		if (iUpdated > 0) {
			if (Util.bAllowPrintln) {
				System.out.println("Updated " + iUpdated + " results!");
			}
		} else if (Util.bAllowPrintln) {
			System.out.println("No results updated.  Check IP.");
		}
	}

	private void Import_RetVMS_ResActionPerformed(ActionEvent evt) {
		JFileChooser fSel = new JFileChooser();
		FileFilterFactory xmlFilter = new FileFilterFactory("XML Files", "xml");
		fSel.setFileFilter(xmlFilter);
		fSel.setApproveButtonText("Import");
		int iRet = fSel.showOpenDialog(this);
		if (iRet == 0) {
			File fIn = fSel.getSelectedFile();
			RETINA_VMS_SI RetinaResults = new RETINA_VMS_SI(fIn);
			this.ProcessRetVMSResults(RetinaResults.GetResults());
		}
		this.RedrawDisplayList();
	}

	private void ChangeAssetPosturesActionPerformed(ActionEvent evt) {
		Util.SetupAssetKeys(this, this.myChecklist.getRawList(), true);
	}

	@Override
	public void ReturnPostUpdate() {
		this.RedrawDisplayList();
		((ModCL_Card_Checklist) this.CheckListCard)
				.DisplayVuln(this.DisplayList.getSelectedIndex());
		this.setEnabled(true);
		this.myParent.ReturnPostUpdate();
		this.requestFocus();
	}

	private void ExportHTMLSFCL(File fOutput) {
		block2: {
			Export_ChecklistHTML EC = new Export_ChecklistHTML(
					this.myChecklist.getRawList());
			Vuln.VulnAttr type = this.myChecklist.getSortType().equals(
					(Object) STIGSearch.SortType.RULEID) ? Vuln.VulnAttr.Rule_ID
					: (this.myChecklist.getSortType().equals(
							(Object) STIGSearch.SortType.STIGID) ? Vuln.VulnAttr.Rule_Ver
							: Vuln.VulnAttr.Vuln_Num);
			try {
				EC.ExportChecklist(type, this.myChecklist.GetSTIGTitle(),
						fOutput);
			} catch (Exception ex) {
				if (!Util.bAllowPrintln)
					break block2;
				ex.printStackTrace();
			}
		}
	}

	private void ProcessResults(ArrayList<XCCDF_RES.xccdfResItem> List) {
		int iTotal = List.size();
		int iMatched = 0;
		int iExceptions = 0;
		ArrayList<Vuln> ErrorList = new ArrayList<Vuln>();
		ArrayList<String> ErrorValueList = new ArrayList<String>();
		if (this.myMode == ModCL_Mode.Checklist) {
			for (int i = 0; i < List.size(); ++i) {
				boolean bMatched = false;
				for (int k = 0; !(bMatched || k >= this.myChecklist
						.getVulnListSize()); ++k) {
					if (List.get((int) i).RuleID.toLowerCase().startsWith("i")) {
						String sID = "";
						String sTemp = List.get((int) i).RuleID;
						int iOffset = -1;
						for (int iCount = 0; iOffset == -1
								&& iCount < sTemp.length(); ++iCount) {
							if (!Character.isDigit(sTemp.charAt(iCount)))
								continue;
							iOffset = iCount - 1;
						}
						sID = sTemp.substring(iOffset + 1, iOffset + 5) + "-"
								+ sTemp.substring(iOffset + 5, iOffset + 6)
								+ "-"
								+ sTemp.substring(iOffset + 6, iOffset + 10);
						if (!sID.equals(this.myChecklist.getVuln(k).getAttr(
								Vuln.VulnAttr.Group_Title)))
							continue;
						++iMatched;
						bMatched = true;
						if (List.get((int) i).Result.toLowerCase().equals(
								"pass")) {
							this.myChecklist.setState(k,
									SV_Checklist.CheckState.NotAFinding);
							continue;
						}
						if (List.get((int) i).Result.toLowerCase().equals(
								"fail")) {
							this.myChecklist.setState(k,
									SV_Checklist.CheckState.Open);
							this.myChecklist.setNote(k,
									List.get((int) i).OVALRef);
							continue;
						}
						++iExceptions;
						ErrorList.add(this.myChecklist.getVuln(k));
						ErrorValueList.add(List.get((int) i).Result);
						continue;
					}
					if (!List.get((int) i).RuleID.equals(this.myChecklist
							.getVuln(k).getAttr(Vuln.VulnAttr.Rule_ID)))
						continue;
					++iMatched;
					bMatched = true;
					if (List.get((int) i).Result.toLowerCase().equals("pass")) {
						this.myChecklist.setState(k,
								SV_Checklist.CheckState.NotAFinding);
						continue;
					}
					if (List.get((int) i).Result.toLowerCase().equals("fail")) {
						this.myChecklist.setState(k,
								SV_Checklist.CheckState.Open);
						this.myChecklist.setNote(k, List.get((int) i).OVALRef);
						continue;
					}
					++iExceptions;
					ErrorList.add(this.myChecklist.getVuln(k));
					ErrorValueList.add(List.get((int) i).Result);
				}
				if (bMatched)
					continue;
				Vuln v = new Vuln();
				v.setAttr(Vuln.VulnAttr.Rule_ID, List.get((int) i).RuleID);
				v.setAttr(Vuln.VulnAttr.Vuln_Num, List.get((int) i).RuleID);
				v.setAttr(Vuln.VulnAttr.Rule_Ver, List.get((int) i).RuleID);
				v.addCCIVal(List.get((int) i).RuleID);
				ErrorList.add(v);
				ErrorValueList.add("Could Not Match");
			}
		}
		if (iExceptions > 0) {
			String s = "";
			s = s + "<html>";
			s = s + "Matched: " + (iMatched - iExceptions) + " of " + iTotal
					+ ".  ";
			s = s + "The following " + iExceptions
					+ "values were not pass/fail.";
			s = s + "</html>";
			SimpleVulnList DispErrors = new SimpleVulnList(ErrorList,
					ErrorValueList, this.myChecklist.getSortType(), s, this,
					false);
			DispErrors.setLocation(this.getX() + 50, this.getY() + 50);
		} else if (ErrorList.size() > 0) {
			String s = "";
			s = s + "<html>";
			s = s + "Matched: " + (iMatched - iExceptions) + " of " + iTotal
					+ ".  ";
			s = s + "The following values could not be matched.";
			s = s + "</html>";
			SimpleVulnList DispErrors = new SimpleVulnList(ErrorList,
					ErrorValueList, this.myChecklist.getSortType(), s, this,
					false);
			DispErrors.setLocation(this.getX() + 50, this.getY() + 50);
		} else {
			JOptionPane.showMessageDialog(this, "Matched " + iMatched
					+ " out of " + iTotal + " results.");
		}
		this.SetIsSaved(false);
	}

	private void ExportVMS(File f) {
		VMS_SI VMSSI = new VMS_SI(f);
		if (this.AssetIDTabs.getTitleAt(this.AssetIDTabs.getSelectedIndex())
				.equals("Computing")) {
			VMSSI.AddASSETIDs(this.TargetHostName.getText(),
					this.TargetMAC.getText(), this.TargetIP.getText());
			VMSSI.SetAssetType("1");
			if (this.TargetRole.getSelectedItem().toString()
					.equals("Workstation")) {
				VMSSI.SetIsWorkstation();
			} else if (this.TargetRole.getSelectedItem().toString()
					.equals("Member Server")) {
				VMSSI.AddElement("221");
				VMSSI.AddElement("222");
			} else if (this.TargetRole.getSelectedItem().toString()
					.equals("Domain Controller")) {
				VMSSI.AddElement("221");
				VMSSI.AddElement("223");
			}
		} else {
			VMSSI.SetAssetType("2");
			VMSSI.AddASSETID("ASSET NAME", this.NC_TargetName.getText());
		}
		ResourceMap resMap = ((STIGViewerGUIApp) Application
				.getInstance(STIGViewerGUIApp.class)).getContext()
				.getResourceMap(STIGViewerGUIView.class);
		String sToolName = "GD";
		String sToolVersion = resMap.getString("Application.name",
				new Object[0])
				+ " : "
				+ resMap.getString("Application.version", new Object[0]);
		ArrayList<Vuln> av = this.myChecklist.getRawList();
		for (Vuln v : av) {
			VMSSI.AddElement(v.getAttr(Vuln.VulnAttr.TargetKey));
			VMSSI.AddTarget(v.getAttr(Vuln.VulnAttr.TargetKey));
			String sOverride = "";
			if (v.getCheckSevOverride().equals("CAT I")) {
				sOverride = "1";
			} else if (v.getCheckSevOverride().equals("CAT II")) {
				sOverride = "2";
			} else if (v.getCheckSevOverride().equals("CAT III")) {
				sOverride = "3";
			}
			VMSSI.AddFINDING(v.getAttr(Vuln.VulnAttr.TargetKey), "VK",
					this.sFormVKforVMS(v.getAttr(Vuln.VulnAttr.Vuln_Num)),
					this.sFormVStateforVMS(v.getCheckState()),
					v.getCHK_Notes(), sOverride, v.getCheckSevJust(), "",
					v.getCheckComment(), sToolName, sToolVersion);
		}
		VMSSI.Print();
	}

	private String sFormVKforVMS(String sVIn) {
		try {
			sVIn = sVIn.substring(2);
			String sZeros = "";
			for (int i = 0; i < 7 - sVIn.length(); ++i) {
				sZeros = sZeros + "0";
			}
			return "V" + sZeros + sVIn;
		} catch (Exception e) {
			if (Util.bAllowPrintln) {
				e.printStackTrace();
			}
			return sVIn;
		}
	}

	private String sFormVStateforVMS(SV_Checklist.CheckState state) {
		if (state == SV_Checklist.CheckState.NotAFinding) {
			return "NF";
		}
		if (state == SV_Checklist.CheckState.Not_Applicable) {
			return "NA";
		}
		if (state == SV_Checklist.CheckState.Open) {
			return "O";
		}
		if (state == SV_Checklist.CheckState.Not_Reviewed) {
			return "NR";
		}
		return "ERROR";
	}

	@Override
	public void CL_KeyReleased(KeyEvent evt) {
		if (this.myMode == ModCL_Mode.Checklist) {
			int i = evt.getKeyCode();
			char c = evt.getKeyChar();
			String s = KeyEvent.getKeyText(i);
			if (evt.isControlDown()) {
				if (s.toLowerCase().charAt(0) == this.myConfig.Get_SetOpenKey()) {
					this.SetStatesFromList(
							this.DisplayList.getSelectedIndecies(),
							SV_Checklist.CheckState.Open);
				} else if (s.toLowerCase().charAt(0) == this.myConfig
						.Get_SetNotAFindingKey()) {
					this.SetStatesFromList(
							this.DisplayList.getSelectedIndecies(),
							SV_Checklist.CheckState.NotAFinding);
				} else if (s.toLowerCase().charAt(0) == this.myConfig
						.Get_SetNotReviewedKey()) {
					this.SetStatesFromList(
							this.DisplayList.getSelectedIndecies(),
							SV_Checklist.CheckState.Not_Reviewed);
				} else if (s.toLowerCase().charAt(0) == this.myConfig
						.Get_SetNotApplicableKey()) {
					this.SetStatesFromList(
							this.DisplayList.getSelectedIndecies(),
							SV_Checklist.CheckState.Not_Applicable);
				}
			}
		}
	}

	public static enum ModCL_Mode {
		Checklist, OCIL;

		private ModCL_Mode() {
		}
	}

}
