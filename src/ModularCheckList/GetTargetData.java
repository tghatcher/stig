/*
 * Decompiled with CFR 0_92.
 */
package ModularCheckList;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.UUID;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.ResourceMap;
import stigviewergui.STIGViewerGUIApp;
import stigviewergui.SV_CORE.GTD_PostData;
import stigviewergui.SV_CORE.Util;

public class GetTargetData extends JFrame {
	String sHostName;
	String sMAC;
	String sIP;
	GTD_PostData myPoster;
	InetAddress myIP;
	NetworkInterface NI;
	DefaultComboBoxModel Host;
	DefaultComboBoxModel IP;
	DefaultComboBoxModel MAC;
	private JComboBox IPBox;
	private JComboBox InterfaceBox;
	private JComboBox MACBox;
	private JLabel OperatingSystem;
	private JButton btn_Cancel;
	private JButton btn_Done;
	private JButton btn_GUID;
	private JButton jButton1;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel lbl_GUID;

	public GetTargetData() {
		Util.SetWindowHeader("Target Data", this);
		this.initComponents();
	}

	public GetTargetData(GTD_PostData PDin) {
		block2: {
			this.myPoster = PDin;
			this.initComponents();
			try {
				this.myIP = InetAddress.getLocalHost();
				this.NI = NetworkInterface.getByInetAddress(this.myIP);
			} catch (Exception ex) {
				if (!Util.bAllowPrintln)
					break block2;
				ex.printStackTrace();
			}
		}
		this.Host = new DefaultComboBoxModel();
		this.IP = new DefaultComboBoxModel();
		this.MAC = new DefaultComboBoxModel();
		Util.SetWindowHeader("Target Data", this);
	}

	private void initComponents() {
		this.jButton1 = new JButton();
		this.InterfaceBox = new JComboBox();
		this.IPBox = new JComboBox();
		this.MACBox = new JComboBox();
		this.btn_Done = new JButton();
		this.btn_Cancel = new JButton();
		this.jLabel1 = new JLabel();
		this.OperatingSystem = new JLabel();
		this.jLabel2 = new JLabel();
		this.lbl_GUID = new JLabel();
		this.btn_GUID = new JButton();
		this.setDefaultCloseOperation(2);
		this.setName("Form");
		this.setResizable(false);
		ResourceMap resourceMap = ((STIGViewerGUIApp) Application
				.getInstance(STIGViewerGUIApp.class)).getContext()
				.getResourceMap(GetTargetData.class);
		this.jButton1.setText(resourceMap.getString("jButton1.text",
				new Object[0]));
		this.jButton1.setName("jButton1");
		this.jButton1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				GetTargetData.this.jButton1ActionPerformed(evt);
			}
		});
		this.InterfaceBox.setName("InterfaceBox");
		this.IPBox.setName("IPBox");
		this.IPBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				GetTargetData.this.IPBoxActionPerformed(evt);
			}
		});
		this.MACBox.setName("MACBox");
		this.btn_Done.setText(resourceMap.getString("btn_Done.text",
				new Object[0]));
		this.btn_Done.setName("btn_Done");
		this.btn_Done.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				GetTargetData.this.btn_DoneActionPerformed(evt);
			}
		});
		this.btn_Cancel.setText(resourceMap.getString("btn_Cancel.text",
				new Object[0]));
		this.btn_Cancel.setName("btn_Cancel");
		this.btn_Cancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				GetTargetData.this.btn_CancelActionPerformed(evt);
			}
		});
		this.jLabel1.setText(resourceMap.getString("jLabel1.text",
				new Object[0]));
		this.jLabel1.setName("jLabel1");
		this.OperatingSystem.setText(resourceMap.getString(
				"OperatingSystem.text", new Object[0]));
		this.OperatingSystem.setName("OperatingSystem");
		this.jLabel2.setText(resourceMap.getString("jLabel2.text",
				new Object[0]));
		this.jLabel2.setName("jLabel2");
		this.lbl_GUID.setText(resourceMap.getString("lbl_GUID.text",
				new Object[0]));
		this.lbl_GUID.setName("lbl_GUID");
		this.btn_GUID.setText(resourceMap.getString("btn_GUID.text",
				new Object[0]));
		this.btn_GUID.setName("btn_GUID");
		this.btn_GUID.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				GetTargetData.this.btn_GUIDActionPerformed(evt);
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
												.addComponent(this.btn_GUID,
														-1, 267, 32767)
												.addComponent(
														this.InterfaceBox, 0,
														267, 32767)
												.addComponent(this.IPBox, 0,
														267, 32767)
												.addComponent(this.MACBox, 0,
														267, 32767)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		this.btn_Done)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED,
																		145,
																		32767)
																.addComponent(
																		this.btn_Cancel))
												.addComponent(this.jButton1,
														-1, 267, 32767)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		this.jLabel1)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		this.OperatingSystem,
																		-1,
																		243,
																		32767))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		this.jLabel2)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		this.lbl_GUID,
																		-1,
																		232,
																		32767)))
								.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(this.jButton1)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.InterfaceBox, -2, -1, -2)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.IPBox, -2, -1, -2)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.MACBox, -2, -1, -2)
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
												.addComponent(this.jLabel1)
												.addComponent(
														this.OperatingSystem,
														-2, 14, -2))
								.addGap(45, 45, 45)
								.addComponent(this.btn_GUID)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
												.addComponent(this.jLabel2)
												.addComponent(this.lbl_GUID,
														-2, 14, -2))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED,
										160, 32767)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
												.addComponent(this.btn_Done)
												.addComponent(this.btn_Cancel))
								.addContainerGap()));
		this.pack();
	}

	private void jButton1ActionPerformed(ActionEvent evt) {
		block5: {
			try {
				InetAddress[] Ads;
				this.Host.addElement(this.myIP.getHostName());
				this.InterfaceBox.setModel(this.Host);
				for (InetAddress ian : Ads = InetAddress.getAllByName(this.myIP
						.getHostName())) {
					this.IP.addElement(ian.getHostAddress());
				}
				this.IPBox.setModel(this.IP);
				this.NI = NetworkInterface.getByInetAddress(Ads[0]);
				if (this.NI.getHardwareAddress().length > 0) {
					this.MAC.addElement(this.ba2str(this.NI
							.getHardwareAddress()));
					this.MACBox.setModel(this.MAC);
				}
			} catch (Exception e) {
				if (!Util.bAllowPrintln)
					break block5;
				e.printStackTrace();
			}
		}
		this.OperatingSystem.setText(System.getProperty("os.name"));
	}

	private String getGUID() {
		String sGUID = UUID.randomUUID().toString();
		if (sGUID != null) {
			return sGUID;
		}
		return "GUID not found!";
	}

	private void btn_DoneActionPerformed(ActionEvent evt) {
		this.sHostName = (String) this.InterfaceBox.getSelectedItem();
		this.sIP = (String) this.IPBox.getSelectedItem();
		this.sMAC = (String) this.MACBox.getSelectedItem();
		if (this.myPoster != null) {
			this.myPoster.PostData(this.getData());
		}
		this.dispose();
	}

	private void IPBoxActionPerformed(ActionEvent evt) {
		block2: {
			int i = this.IPBox.getSelectedIndex();
			try {
				InetAddress[] Ads = InetAddress.getAllByName(this.myIP
						.getHostName());
				this.NI = NetworkInterface.getByInetAddress(Ads[i]);
				this.MAC = new DefaultComboBoxModel();
				this.MAC.addElement(this.ba2str(this.NI.getHardwareAddress()));
				this.MACBox.setModel(this.MAC);
			} catch (Exception ex) {
				if (!Util.bAllowPrintln)
					break block2;
				ex.printStackTrace();
			}
		}
	}

	private void btn_CancelActionPerformed(ActionEvent evt) {
		this.dispose();
	}

	private void btn_GUIDActionPerformed(ActionEvent evt) {
		this.lbl_GUID.setText("Scanning for GUID...");
		this.lbl_GUID.updateUI();
		this.repaint();
		this.update(this.getGraphics());
		this.lbl_GUID.setText(this.getGUID());
	}

	public String[] getData() {
		String[] sOut = new String[] { this.sHostName, this.sIP, this.sMAC };
		return sOut;
	}

	public String ba2str(byte[] ba) {
		String sOut = "";
		for (int i = 0; i < ba.length; ++i) {
			int b = ba[i];
			int x = b < 0 ? b + 256 : b;
			sOut = sOut + Integer.toHexString(x) + ":";
		}
		sOut = sOut.substring(0, sOut.length() - 1);
		return sOut;
	}

}
