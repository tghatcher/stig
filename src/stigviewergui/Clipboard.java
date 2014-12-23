/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.ResourceMap;
import stigviewergui.STIGViewerGUIApp;
import stigviewergui.SV_CORE.Util;
import stigviewergui.SV_CORE.Vuln;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Clipboard extends JFrame {
	private ArrayList<Vuln> vClip;
	private DefaultListModel vList;
	private JButton jButton1;
	private JButton jButton2;
	private JList jList1;
	private JScrollPane jScrollPane1;

	public Clipboard() {
		Util.SetWindowHeader("Clipboard", this);
		this.vClip = new ArrayList();
		this.initComponents();
		this.vList = new DefaultListModel();
		this.jList1.setModel(this.vList);
	}

	public void AddVul(Vuln v) {
		if (!this.vClip.contains(v)) {
			this.vClip.add(v);
			this.Redraw();
		}
	}

	public void RemoveVul(Vuln v) {
		this.vClip.remove(v);
		this.Redraw();
	}

	public void RemoveVul(int i) {
		this.vClip.remove(i);
		this.Redraw();
	}

	public void Redraw() {
		this.vList.clear();
		for (int i = 0; i < this.vClip.size(); ++i) {
			this.vList.add(
					i,
					this.vClip.get(i).getAttr(Vuln.VulnAttr.Vuln_Num)
							+ " - "
							+ this.vClip.get(i).getAttr(
									Vuln.VulnAttr.Group_Title));
		}
	}

	public ArrayList<Vuln> GetList() {
		return this.vClip;
	}

	private void initComponents() {
		this.jButton1 = new JButton();
		this.jButton2 = new JButton();
		this.jScrollPane1 = new JScrollPane();
		this.jList1 = new JList();
		this.setAlwaysOnTop(true);
		this.setName("Form");
		ResourceMap resourceMap = ((STIGViewerGUIApp) Application
				.getInstance(STIGViewerGUIApp.class)).getContext()
				.getResourceMap(Clipboard.class);
		this.jButton1.setText(resourceMap.getString("jButton1.text",
				new Object[0]));
		this.jButton1.setName("jButton1");
		this.getContentPane().add((Component) this.jButton1, "First");
		this.jButton2.setText(resourceMap.getString("jButton2.text",
				new Object[0]));
		this.jButton2.setName("jButton2");
		this.getContentPane().add((Component) this.jButton2, "Last");
		this.jScrollPane1.setName("jScrollPane1");
		this.jList1.setName("jList1");
		this.jScrollPane1.setViewportView(this.jList1);
		this.getContentPane().add((Component) this.jScrollPane1, "Center");
		this.pack();
	}
}
