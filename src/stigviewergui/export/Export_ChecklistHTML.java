/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.export;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import stigviewergui.SV_CORE.SV_Checklist;
import stigviewergui.SV_CORE.Vuln;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Export_ChecklistHTML {
	ArrayList<Vuln> myList;

	public Export_ChecklistHTML(ArrayList<Vuln> VulnList) {
		this.myList = VulnList;
	}

	public String GetHTMLString(Vuln.VulnAttr Listing, String Title) {
		String sRet = "";
		sRet = "<html>";
		sRet = sRet + Title + " Checklist<br>";
		sRet = sRet + "Classification:____________________________________<br>";
		sRet = sRet + "Asset Information: <br><br><br><br><br>";
		sRet = sRet
				+ "<table align = \"center\" width = \"600\" border = \"1\">";
		sRet = sRet + "<tr>";
		sRet = sRet + "<th width = \"100\"> Check </th>";
		sRet = sRet + "<th width = \"100\"> Status </th>";
		sRet = sRet + "<th> Details </th>";
		sRet = sRet + "</tr>";
		for (Vuln v : this.myList) {
			sRet = sRet + "<tr>";
			sRet = sRet + "<th>" + v.getAttr(Listing) + "</th>";
			if (v.getCheckState().equals(
					(Object) SV_Checklist.CheckState.Not_Reviewed)) {
				sRet = sRet + "<th> O/NF/NA </th>";
			} else if (v.getCheckState().equals(
					(Object) SV_Checklist.CheckState.Open)) {
				sRet = sRet + "<th> Open </th>";
			} else if (v.getCheckState().equals(
					(Object) SV_Checklist.CheckState.NotAFinding)) {
				sRet = sRet + "<th> NF </th>";
			} else if (v.getCheckState().equals(
					(Object) SV_Checklist.CheckState.Not_Applicable)) {
				sRet = sRet + "<th> NA </th>";
			}
			sRet = sRet + "<th>&nbsp;</th>";
			sRet = sRet + "</tr>";
		}
		sRet = sRet + "</table>";
		sRet = sRet + "Classification:____________________________________<br>";
		sRet = sRet + "</html>";
		return sRet;
	}

	public void ExportChecklist(Vuln.VulnAttr Listing, String Title, File f)
			throws FileNotFoundException, UnsupportedEncodingException,
			IOException {
		FileOutputStream FOS = new FileOutputStream(f);
		BufferedOutputStream BOS = new BufferedOutputStream(FOS);
		String s = this.GetHTMLString(Listing, Title);
		BOS.write(s.getBytes("UTF-8"));
		BOS.close();
	}
}
