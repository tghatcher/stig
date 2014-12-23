/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.SV_CORE;

import java.awt.Frame;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.ResourceMap;
import stigviewergui.MultipleVMSTargetSelector;
import stigviewergui.STIGViewerGUIApp;
import stigviewergui.STIGViewerGUIView;
import stigviewergui.SV_CORE.Pair;
import stigviewergui.SV_CORE.Vuln;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Util {
	public static boolean bAllowPrintln = false;

	public static boolean bHasAlphNum(String s) {
		if (s != null) {
			for (int i = 0; i < s.length(); ++i) {
				if (!Character.isLetterOrDigit(s.charAt(i)))
					continue;
				return true;
			}
		}
		return false;
	}

	public static void SetWindowHeader(String sTitle, JFrame frame) {
		block3: {
			try {
				ResourceMap resMap = ((STIGViewerGUIApp) Application
						.getInstance(STIGViewerGUIApp.class)).getContext()
						.getResourceMap(STIGViewerGUIView.class);
				ImageIcon ii = resMap.getImageIcon("topIcon.imageIcon");
				frame.setIconImage(ii.getImage());
				if (!sTitle.equals("")) {
					sTitle = " : " + sTitle;
				}
				frame.setTitle(resMap.getString("Application.title",
						new Object[0]) + sTitle);
			} catch (Exception e) {
				if (!bAllowPrintln)
					break block3;
				e.printStackTrace();
			}
		}
	}

	public static void SetWindowHeader(String sTitle, JDialog frame) {
		block2: {
			try {
				ResourceMap resMap = ((STIGViewerGUIApp) Application
						.getInstance(STIGViewerGUIApp.class)).getContext()
						.getResourceMap(STIGViewerGUIView.class);
				ImageIcon ii = resMap.getImageIcon("topIcon.imageIcon");
				frame.setIconImage(ii.getImage());
				frame.setTitle(sTitle);
			} catch (Exception e) {
				if (!bAllowPrintln)
					break block2;
				e.printStackTrace();
			}
		}
	}

	public static void SetWindowTitle(String sTitle, JFrame frame) {
		block3: {
			try {
				ResourceMap resMap = ((STIGViewerGUIApp) Application
						.getInstance(STIGViewerGUIApp.class)).getContext()
						.getResourceMap(STIGViewerGUIView.class);
				if (!sTitle.equals("")) {
					sTitle = " : " + sTitle;
				}
				frame.setTitle(resMap.getString("Application.title",
						new Object[0]) + sTitle);
			} catch (Exception e) {
				if (!bAllowPrintln)
					break block3;
				e.printStackTrace();
			}
		}
	}

	public static String GetAppName() {
		try {
			ResourceMap resMap = ((STIGViewerGUIApp) Application
					.getInstance(STIGViewerGUIApp.class)).getContext()
					.getResourceMap(STIGViewerGUIView.class);
			return resMap.getString("Application.title", new Object[0]);
		} catch (Exception e) {
			if (bAllowPrintln) {
				e.printStackTrace();
			}
			return "";
		}
	}

	public static String GetAppVersion() {
		try {
			ResourceMap resMap = ((STIGViewerGUIApp) Application
					.getInstance(STIGViewerGUIApp.class)).getContext()
					.getResourceMap(STIGViewerGUIView.class);
			return resMap.getString("Application.version", new Object[0]);
		} catch (Exception e) {
			if (bAllowPrintln) {
				e.printStackTrace();
			}
			return "";
		}
	}

	public static void SetupAssetKeys(JFrame parent, ArrayList<Vuln> vList,
			boolean bEnableOverwrite) {
		ArrayList<Pair<String, String>> STIGs = new ArrayList<Pair<String, String>>();
		for (Vuln v : vList) {
			Pair<String, String> s = new Pair<String, String>(
					v.getAttr(Vuln.VulnAttr.STIGRef),
					v.getAttr(Vuln.VulnAttr.TargetKey));
			if (STIGs.contains(s) || !s.getSecond().equals("")
					&& !bEnableOverwrite)
				continue;
			STIGs.add(s);
		}
		for (int i = 0; i < STIGs.size(); ++i) {
			for (int j = 0; j < STIGs.size(); ++j) {
				if (i == j
						|| !STIGs.get(i).getFirst()
								.equals(STIGs.get(j).getFirst()))
					continue;
				STIGs.remove(STIGs.get(j));
				STIGs.get(i).setSecond("");
			}
		}
		if (STIGs.size() > 0) {
			MultipleVMSTargetSelector GetTargets = new MultipleVMSTargetSelector(
					parent, true, STIGs);
			GetTargets
					.setLocation(
							parent.getX()
									+ (parent.getWidth() - GetTargets
											.getWidth()) / 2,
							parent.getY()
									+ (parent.getHeight() - GetTargets
											.getHeight()) / 2);
			GetTargets.setVisible(true);
			ArrayList<Pair<String, String>> UpdatedSTIGs = GetTargets
					.GetListing();
			boolean bOverWrite = false;
			boolean bDoNotOverWrite = false;
			for (Vuln v2 : vList) {
				if (!(v2.getAttr(Vuln.VulnAttr.TargetKey).equals("")
						|| bOverWrite || bDoNotOverWrite)) {
					int iAns = JOptionPane.showConfirmDialog(parent,
							"Asset Key Already Set. Overwrite all keys?",
							"Asset Key Conflict", 0);
					switch (iAns) {
					case 0: {
						bOverWrite = true;
						break;
					}
					case 1: {
						bDoNotOverWrite = true;
						break;
					}
					}
				}
				if (!v2.getAttr(Vuln.VulnAttr.TargetKey).equals("")
						&& !bOverWrite)
					continue;
				for (Pair<String, String> Entry : UpdatedSTIGs) {
					if (!Entry.getFirst().equals(
							v2.getAttr(Vuln.VulnAttr.STIGRef)))
						continue;
					v2.setAttr(Vuln.VulnAttr.TargetKey, Entry.getSecond());
				}
			}
		}
	}
}
