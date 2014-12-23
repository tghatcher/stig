/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.LayoutManager;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.LayoutStyle;
import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationActionMap;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.ResourceMap;
import stigviewergui.STIGViewerGUIApp;
import stigviewergui.STIGViewerGUIView;

public class STIGViewerGUIAboutBox extends JDialog {
	private JButton closeButton;
	private JLabel jLabel1;

	public STIGViewerGUIAboutBox(Frame parent) {
		super(parent);
		this.initComponents();
		ResourceMap resMap = ((STIGViewerGUIApp) Application
				.getInstance(STIGViewerGUIApp.class)).getContext()
				.getResourceMap(STIGViewerGUIView.class);
		ImageIcon ii = resMap.getImageIcon("topIcon.imageIcon");
		this.setIconImage(ii.getImage());
		this.getRootPane().setDefaultButton(this.closeButton);
	}

	@org.jdesktop.application.Action
	public void closeAboutBox() {
		this.dispose();
	}

	private void initComponents() {
		this.closeButton = new JButton();
		JLabel appTitleLabel = new JLabel();
		JLabel versionLabel = new JLabel();
		JLabel appVersionLabel = new JLabel();
		this.jLabel1 = new JLabel();
		this.setDefaultCloseOperation(2);
		ResourceMap resourceMap = ((STIGViewerGUIApp) Application
				.getInstance(STIGViewerGUIApp.class)).getContext()
				.getResourceMap(STIGViewerGUIAboutBox.class);
		this.setTitle(resourceMap.getString("title", new Object[0]));
		this.setModal(true);
		this.setName("aboutBox");
		this.setResizable(false);
		ApplicationActionMap actionMap = ((STIGViewerGUIApp) Application
				.getInstance(STIGViewerGUIApp.class)).getContext()
				.getActionMap(STIGViewerGUIAboutBox.class, this);
		this.closeButton.setAction(actionMap.get("closeAboutBox"));
		this.closeButton.setName("closeButton");
		appTitleLabel.setFont(appTitleLabel.getFont().deriveFont(
				appTitleLabel.getFont().getStyle() | 1,
				appTitleLabel.getFont().getSize() + 4));
		appTitleLabel.setText(resourceMap.getString("Application.title",
				new Object[0]));
		appTitleLabel.setName("appTitleLabel");
		versionLabel.setFont(versionLabel.getFont().deriveFont(
				versionLabel.getFont().getStyle() | 1));
		versionLabel.setText(resourceMap.getString("versionLabel.text",
				new Object[0]));
		versionLabel.setName("versionLabel");
		appVersionLabel.setText(resourceMap.getString("Application.version",
				new Object[0]));
		appVersionLabel.setName("appVersionLabel");
		this.jLabel1.setIcon(resourceMap.getIcon("jLabel1.icon"));
		this.jLabel1.setText(resourceMap.getString("jLabel1.text",
				new Object[0]));
		this.jLabel1.setName("jLabel1");
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addComponent(this.jLabel1, -1, 450, 32767)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.TRAILING)
												.addGroup(
														layout.createParallelGroup(
																GroupLayout.Alignment.LEADING,
																false)
																.addComponent(
																		appTitleLabel)
																.addGroup(
																		layout.createSequentialGroup()
																				.addComponent(
																						versionLabel)
																				.addPreferredGap(
																						LayoutStyle.ComponentPlacement.RELATED)
																				.addComponent(
																						appVersionLabel,
																						-1,
																						-1,
																						32767)))
												.addComponent(this.closeButton))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(appTitleLabel, -2, 19, -2)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
												.addComponent(versionLabel)
												.addComponent(appVersionLabel,
														-2, 14, -2))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED,
										93, 32767)
								.addComponent(this.closeButton)
								.addGap(43, 43, 43))
				.addComponent(this.jLabel1, -1, -1, 32767));
		this.pack();
	}
}
