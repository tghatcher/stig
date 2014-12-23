/*
 * Decompiled with CFR 0_92.
 */
package File_Interfaces;

import CheckListBox.CheckListBox;
import FileFilters.FileFilterFactory;
import File_Interfaces.CCIReader;
import File_Interfaces.CSVWriter;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.ResourceMap;
import stigviewergui.STIGViewerConfig;
import stigviewergui.STIGViewerGUIApp;
import stigviewergui.SV_CORE.Pair;
import stigviewergui.SV_CORE.SV_Checklist;
import stigviewergui.SV_CORE.Util;
import stigviewergui.SV_CORE.Vuln;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class CSV_Export_Selector extends JFrame {
	STIGViewerConfig myConfig;
	ArrayList<Vuln> vList;
	CSVWriter myWrite;
	File fWrite;
	private JButton btn_Export;
	private JButton btn_FileChoose;
	private CheckListBox checkListBox1;
	private JCheckBox ckb_CCI_800_53;
	private JCheckBox ckb_CCI_Desc;
	private JCheckBox ckb_CCI_REF;
	private JCheckBox ckb_Comments;
	private JCheckBox ckb_Finding_Details;
	private JCheckBox ckb_Sev_Over_Just;
	private JCheckBox ckb_Severity_Override;
	private JCheckBox ckb_Status;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel5;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JPanel jPanel3;
	private JLabel lbl_File;

	public CSV_Export_Selector(STIGViewerConfig conf, boolean bIsCheckList) {
		this.myConfig = conf;
		this.initComponents();
		this.SetupSelectionBox();
		this.fWrite = null;
		if (bIsCheckList) {
			this.ckb_Status.setSelected(true);
			this.ckb_Comments.setSelected(true);
			this.ckb_Finding_Details.setSelected(true);
			this.ckb_Sev_Over_Just.setSelected(true);
			this.ckb_Severity_Override.setSelected(true);
		}
	}

	private void SetupSelectionBox() {
		Vuln.VulnAttr[] vals;
		for (Vuln.VulnAttr va : vals = Vuln.VulnAttr.values()) {
			if (va.name().equals("NULL"))
				continue;
			this.checkListBox1.add(va.name(), this.myConfig.CheckIfShow(va));
		}
		this.ckb_CCI_REF.setSelected(this.myConfig.CheckIfShow_CCI());
		this.ckb_CCI_800_53.setSelected(this.myConfig.CheckIfShow_CCIMap());
		this.ckb_CCI_Desc.setSelected(this.myConfig.CheckIfShow_CCIDesc());
	}

	public void SetVulnList(ArrayList<Vuln> List) {
		this.vList = List;
	}

	private void initComponents() {
		this.jLabel5 = new JLabel();
		this.jPanel1 = new JPanel();
		this.jLabel2 = new JLabel();
		this.ckb_Status = new JCheckBox();
		this.ckb_Finding_Details = new JCheckBox();
		this.ckb_Comments = new JCheckBox();
		this.ckb_Severity_Override = new JCheckBox();
		this.ckb_Sev_Over_Just = new JCheckBox();
		this.jPanel2 = new JPanel();
		this.jLabel1 = new JLabel();
		this.checkListBox1 = new CheckListBox();
		this.jPanel3 = new JPanel();
		this.jLabel3 = new JLabel();
		this.ckb_CCI_REF = new JCheckBox();
		this.ckb_CCI_Desc = new JCheckBox();
		this.ckb_CCI_800_53 = new JCheckBox();
		this.btn_Export = new JButton();
		this.lbl_File = new JLabel();
		this.btn_FileChoose = new JButton();
		ResourceMap resourceMap = ((STIGViewerGUIApp) Application
				.getInstance(STIGViewerGUIApp.class)).getContext()
				.getResourceMap(CSV_Export_Selector.class);
		this.jLabel5.setText(resourceMap.getString("jLabel5.text",
				new Object[0]));
		this.jLabel5.setName("jLabel5");
		this.setDefaultCloseOperation(2);
		this.setName("Form");
		this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
		this.jPanel1.setName("jPanel1");
		this.jLabel2.setText(resourceMap.getString("jLabel2.text",
				new Object[0]));
		this.jLabel2.setName("jLabel2");
		this.ckb_Status.setText(resourceMap.getString("ckb_Status.text",
				new Object[0]));
		this.ckb_Status.setName("ckb_Status");
		this.ckb_Finding_Details.setText(resourceMap.getString(
				"ckb_Finding_Details.text", new Object[0]));
		this.ckb_Finding_Details.setName("ckb_Finding_Details");
		this.ckb_Comments.setText(resourceMap.getString("ckb_Comments.text",
				new Object[0]));
		this.ckb_Comments.setName("ckb_Comments");
		this.ckb_Severity_Override.setText(resourceMap.getString(
				"ckb_Severity_Override.text", new Object[0]));
		this.ckb_Severity_Override.setName("ckb_Severity_Override");
		this.ckb_Sev_Over_Just.setText(resourceMap.getString(
				"ckb_Sev_Over_Just.text", new Object[0]));
		this.ckb_Sev_Over_Just.setName("ckb_Sev_Over_Just");
		GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
		this.jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								jPanel1Layout
										.createParallelGroup(
												GroupLayout.Alignment.LEADING)
										.addComponent(this.ckb_Sev_Over_Just)
										.addComponent(
												this.ckb_Severity_Override)
										.addComponent(this.ckb_Comments)
										.addComponent(this.ckb_Finding_Details)
										.addComponent(this.ckb_Status)
										.addComponent(this.jLabel2))
						.addContainerGap(27, 32767)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel1Layout
								.createSequentialGroup()
								.addComponent(this.jLabel2)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.ckb_Status)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.ckb_Finding_Details)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.ckb_Comments)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.ckb_Severity_Override)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.ckb_Sev_Over_Just)
								.addContainerGap(94, 32767)));
		this.jPanel2.setBorder(BorderFactory.createEtchedBorder());
		this.jPanel2.setName("jPanel2");
		this.jLabel1.setText(resourceMap.getString("jLabel1.text",
				new Object[0]));
		this.jLabel1.setName("jLabel1");
		this.checkListBox1.setName("checkListBox1");
		GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
		this.jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				jPanel2Layout
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								jPanel2Layout
										.createParallelGroup(
												GroupLayout.Alignment.LEADING)
										.addComponent(this.jLabel1)
										.addComponent(this.checkListBox1,
												GroupLayout.Alignment.TRAILING,
												-1, 207, 32767))
						.addContainerGap()));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel2Layout
								.createSequentialGroup()
								.addComponent(this.jLabel1)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.checkListBox1, -2, 197, -2)
								.addContainerGap(8, 32767)));
		this.jPanel3.setBorder(BorderFactory.createEtchedBorder());
		this.jPanel3.setName("jPanel3");
		this.jLabel3.setText(resourceMap.getString("jLabel3.text",
				new Object[0]));
		this.jLabel3.setName("jLabel3");
		this.ckb_CCI_REF.setText(resourceMap.getString("ckb_CCI_REF.text",
				new Object[0]));
		this.ckb_CCI_REF.setName("ckb_CCI_REF");
		this.ckb_CCI_Desc.setText(resourceMap.getString("ckb_CCI_Desc.text",
				new Object[0]));
		this.ckb_CCI_Desc.setName("ckb_CCI_Desc");
		this.ckb_CCI_800_53.setText(resourceMap.getString(
				"ckb_CCI_800_53.text", new Object[0]));
		this.ckb_CCI_800_53.setName("ckb_CCI_800_53");
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
														.addGroup(
																jPanel3Layout
																		.createSequentialGroup()
																		.addGap(10,
																				10,
																				10)
																		.addGroup(
																				jPanel3Layout
																						.createParallelGroup(
																								GroupLayout.Alignment.LEADING)
																						.addComponent(
																								this.ckb_CCI_REF)
																						.addComponent(
																								this.ckb_CCI_Desc)
																						.addComponent(
																								this.ckb_CCI_800_53)))
														.addComponent(
																this.jLabel3))
										.addContainerGap(34, 32767)));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel3Layout
								.createSequentialGroup()
								.addComponent(this.jLabel3)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.ckb_CCI_REF)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.ckb_CCI_Desc)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.ckb_CCI_800_53)
								.addContainerGap(140, 32767)));
		this.btn_Export.setText(resourceMap.getString("btn_Export.text",
				new Object[0]));
		this.btn_Export.setName("btn_Export");
		this.btn_Export.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				CSV_Export_Selector.this.btn_ExportActionPerformed(evt);
			}
		});
		this.lbl_File.setText(resourceMap.getString("lbl_File.text",
				new Object[0]));
		this.lbl_File.setName("lbl_File");
		this.btn_FileChoose.setText(resourceMap.getString(
				"btn_FileChoose.text", new Object[0]));
		this.btn_FileChoose.setName("btn_FileChoose");
		this.btn_FileChoose.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				CSV_Export_Selector.this.btn_FileChooseActionPerformed(evt);
			}
		});
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
														this.btn_Export,
														GroupLayout.Alignment.TRAILING)
												.addGroup(
														GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addComponent(
																		this.jPanel2,
																		-2, -1,
																		-2)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		this.jPanel1,
																		-2, -1,
																		-2)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		this.jPanel3,
																		-1, -1,
																		32767)
																.addContainerGap())
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		this.btn_FileChoose,
																		-2, 61,
																		-2)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		this.lbl_File,
																		-1,
																		595,
																		32767)
																.addContainerGap()))));
		layout.setVerticalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addComponent(this.lbl_File,
														-1, 23, 32767)
												.addComponent(
														this.btn_FileChoose,
														GroupLayout.Alignment.TRAILING,
														-1, -1, 32767))
								.addGap(8, 8, 8)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING,
												false)
												.addComponent(this.jPanel3, -1,
														-1, 32767)
												.addComponent(this.jPanel2, 0,
														229, 32767)
												.addComponent(this.jPanel1, -2,
														-1, -2))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.btn_Export)));
		this.pack();
	}

	private String CleanStr(String sIn) {
		String sOut = sIn;
		sOut = sOut.replace((CharSequence) "\"", (CharSequence) "'");
		sOut = sOut.replace((CharSequence) "\n", (CharSequence) "");
		sOut = sOut.replace((CharSequence) "\r", (CharSequence) "");
		return sOut;
	}

	private void PerformExport() {
		block26: {
			ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
			ArrayList<String> titleLine = new ArrayList<String>();
			Vuln.VulnAttr[] vas = Vuln.VulnAttr.values();
			for (int i = 0; i < this.checkListBox1.getItemCount(); ++i) {
				if (!this.checkListBox1.GetIsChecked(i))
					continue;
				titleLine.add(vas[i].toString());
			}
			if (this.ckb_Status.isSelected()) {
				titleLine.add("Status");
			}
			if (this.ckb_Comments.isSelected()) {
				titleLine.add("Comments");
			}
			if (this.ckb_Finding_Details.isSelected()) {
				titleLine.add("Finding Details");
			}
			if (this.ckb_Severity_Override.isSelected()) {
				titleLine.add("Severity Override");
			}
			if (this.ckb_Sev_Over_Just.isSelected()) {
				titleLine.add("Severity Override Justification");
			}
			if (this.ckb_CCI_REF.isSelected()
					|| this.ckb_CCI_800_53.isSelected()
					|| this.ckb_CCI_Desc.isSelected()) {
				titleLine.add("CCI Data");
			}
			output.add(titleLine);
			for (Vuln v : this.vList) {
				try {
					ArrayList<String> als;
					ArrayList<String> entry = new ArrayList<String>();
					for (int i2 = 0; i2 < this.checkListBox1.getItemCount(); ++i2) {
						if (!this.checkListBox1.GetIsChecked(i2))
							continue;
						entry.add(this.CleanStr(v.getAttr(vas[i2])));
					}
					if (this.ckb_Status.isSelected()) {
						entry.add(this.CleanStr(v.getCheckState().name()));
					}
					if (this.ckb_Comments.isSelected()) {
						entry.add(this.CleanStr(v.getCheckComment()));
					}
					if (this.ckb_Finding_Details.isSelected()) {
						entry.add(this.CleanStr(v.getCHK_Notes()));
					}
					if (this.ckb_Severity_Override.isSelected()) {
						entry.add(this.CleanStr(v.getCheckSevOverride()));
					}
					if (this.ckb_Sev_Over_Just.isSelected()) {
						entry.add(this.CleanStr(v.getCheckSevJust()));
					}
					if ((als = v.GetCCIVals()).size() > 0) {
						CCIReader.CCI_Store cci;
						String sTemp = "";
						if (this.ckb_CCI_REF.isSelected()) {
							for (String s : als) {
								sTemp = sTemp + s + " ";
							}
						}
						if (this.ckb_CCI_800_53.isSelected()) {
							for (String s : als) {
								cci = v.GetCCIbyName(s);
								for (int k = 0; k < cci.sRefs.size(); ++k) {
									sTemp = sTemp + cci.sRefs.get(k).getFirst()
											+ " :: "
											+ cci.sRefs.get(k).getSecond()
											+ " ";
								}
							}
						}
						if (this.ckb_CCI_Desc.isSelected()) {
							for (String s : als) {
								cci = v.GetCCIbyName(s);
								sTemp = sTemp + cci.sDefinition;
							}
						}
						entry.add(sTemp);
					}
					output.add(entry);
				} catch (Exception e) {
					if (!Util.bAllowPrintln)
						continue;
					e.printStackTrace();
				}
			}
			CSVWriter writer = new CSVWriter(this, output, this.fWrite);
			try {
				writer.Write();
			} catch (Exception e) {
				if (!Util.bAllowPrintln)
					break block26;
				e.printStackTrace();
			}
		}
	}

	private void btn_ExportActionPerformed(ActionEvent evt) {
		if (this.fWrite != null) {
			this.PerformExport();
		} else {
			JFileChooser jfc = new JFileChooser();
			FileFilterFactory csvFiles = new FileFilterFactory("CSV Files",
					"csv");
			jfc.setApproveButtonText("Select");
			jfc.setFileFilter(csvFiles);
			int iResult = jfc.showSaveDialog(this);
			if (iResult == 0) {
				if (jfc.getSelectedFile().getPath().endsWith(".csv")) {
					this.fWrite = jfc.getSelectedFile();
					this.lbl_File.setText(this.fWrite.getPath());
				} else {
					String sPath = jfc.getSelectedFile().getPath() + ".csv";
					this.fWrite = new File(sPath);
					this.lbl_File.setText(sPath);
				}
				this.PerformExport();
			}
		}
	}

	private void btn_FileChooseActionPerformed(ActionEvent evt) {
		JFileChooser jfc = new JFileChooser();
		FileFilterFactory csvFiles = new FileFilterFactory("CSV Files", "csv");
		jfc.setApproveButtonText("Select");
		jfc.setFileFilter(csvFiles);
		int iResult = jfc.showSaveDialog(this);
		if (iResult == 0) {
			if (jfc.getSelectedFile().getPath().endsWith(".csv")) {
				this.fWrite = jfc.getSelectedFile();
				this.lbl_File.setText(this.fWrite.getPath());
			} else {
				String sPath = jfc.getSelectedFile().getPath() + ".csv";
				this.fWrite = new File(sPath);
				this.lbl_File.setText(sPath);
			}
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
			}
		});
	}

}
