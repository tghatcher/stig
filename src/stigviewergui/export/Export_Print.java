/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.export;

import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.text.Caret;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.ResourceMap;
import stigviewergui.STIGViewerGUIApp;
import stigviewergui.SV_CORE.Util;

public class Export_Print extends JFrame {
	private String sText;
	private JFrame myMainFrame;
	private STIGViewerGUIApp myApplication;
	private JButton btn_Print;
	private JEditorPane jEditorPane1;
	private JPanel jPanel1;
	private JScrollPane jScrollPane1;

	public Export_Print(STIGViewerGUIApp myApp) {
		this.initComponents();
		this.myApplication = myApp;
	}

	public Export_Print(STIGViewerGUIApp myApp, String TextToPrint) {
		this.initComponents();
		this.myApplication = myApp;
		this.myApplication.show(this);
		this.jEditorPane1.setText(TextToPrint);
		Caret c2 = this.jEditorPane1.getCaret();
		c2.moveDot(0);
		this.jEditorPane1.setCaret(c2);
		this.myMainFrame = myApp.getMainFrame();
		Util.SetWindowHeader("Print", this);
		this.setLocationRelativeTo(this.myMainFrame);
		this.setVisible(true);
		this.setDefaultCloseOperation(2);
	}

	public Export_Print(String TextToPrint) {
		this.initComponents();
		Util.SetWindowHeader("Print", this);
		this.jEditorPane1.setText(TextToPrint);
		Caret c2 = this.jEditorPane1.getCaret();
		c2.moveDot(0);
		this.jEditorPane1.setCaret(c2);
		this.setDefaultCloseOperation(2);
	}

	public void PrintData(String sTextToPrint) {
		this.sText = sTextToPrint;
		Export_Print me = new Export_Print(this.myApplication, this.sText);
	}

	private void initComponents() {
		this.jScrollPane1 = new JScrollPane();
		this.jEditorPane1 = new JEditorPane();
		this.jPanel1 = new JPanel();
		this.btn_Print = new JButton();
		this.setDefaultCloseOperation(3);
		this.setAlwaysOnTop(true);
		this.setName("Form");
		this.jScrollPane1.setName("jScrollPane1");
		this.jEditorPane1.setBorder(BorderFactory.createEtchedBorder());
		this.jEditorPane1.setContentType("text/html");
		this.jEditorPane1.setName("jEditorPane1");
		this.jScrollPane1.setViewportView(this.jEditorPane1);
		this.getContentPane().add((Component) this.jScrollPane1, "Center");
		this.jPanel1.setName("jPanel1");
		ResourceMap resourceMap = ((STIGViewerGUIApp) Application
				.getInstance(STIGViewerGUIApp.class)).getContext()
				.getResourceMap(Export_Print.class);
		this.btn_Print.setText(resourceMap.getString("btn_Print.text",
				new Object[0]));
		this.btn_Print.setName("btn_Print");
		this.btn_Print.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				Export_Print.this.btn_PrintActionPerformed(evt);
			}
		});
		GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
		this.jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				GroupLayout.Alignment.TRAILING,
				jPanel1Layout.createSequentialGroup()
						.addContainerGap(602, 32767)
						.addComponent(this.btn_Print)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(this.btn_Print));
		this.getContentPane().add((Component) this.jPanel1, "Last");
		this.pack();
	}

	private void btn_PrintActionPerformed(ActionEvent evt) {
		block2: {
			try {
				this.jEditorPane1.print();
			} catch (PrinterException ex) {
				if (!Util.bAllowPrintln)
					break block2;
				ex.printStackTrace();
			}
		}
		this.dispose();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
			}
		});
	}

	private class PrintSpooler implements Runnable {
		Export_Print myFrame;

		PrintSpooler(Export_Print PrintSuper) {
			this.myFrame = PrintSuper;
		}

		public void main(String ARGZ) {
		}

		public void run() {
			block2: {
				try {
					Export_Print.this.jEditorPane1.print();
				} catch (PrinterException ex) {
					if (!Util.bAllowPrintln)
						break block2;
					ex.printStackTrace();
				}
			}
			this.myFrame.dispose();
		}
	}

}
