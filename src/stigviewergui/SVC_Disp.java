/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui;

import java.util.ArrayList;
import stigviewergui.SV_CORE.Vuln;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SVC_Disp {
	private ArrayList<VulnBool> VBList = new ArrayList();

	SVC_Disp() {
		this.VBList.add(new VulnBool(Vuln.VulnAttr.Check_Content,
				"Check Content", true));
		this.VBList.add(new VulnBool(Vuln.VulnAttr.Documentable,
				"Documentable", true));
		this.VBList.add(new VulnBool(Vuln.VulnAttr.Vuln_Discuss, "Discussion",
				true));
		this.VBList.add(new VulnBool(Vuln.VulnAttr.False_Negatives,
				"False Negatives", true));
		this.VBList.add(new VulnBool(Vuln.VulnAttr.False_Positives,
				"False Positives", true));
		this.VBList.add(new VulnBool(Vuln.VulnAttr.Fix_Text, "Fix Text", true));
		this.VBList.add(new VulnBool(Vuln.VulnAttr.IA_Controls, "IA Controls",
				true));
		this.VBList.add(new VulnBool(Vuln.VulnAttr.Mitigation_Control,
				"Mitigation_Control", true));
		this.VBList.add(new VulnBool(Vuln.VulnAttr.Mitigations, "Mitigations",
				true));
		this.VBList.add(new VulnBool(Vuln.VulnAttr.Potential_Impact,
				"Potential Impact", true));
		this.VBList.add(new VulnBool(Vuln.VulnAttr.Responsibility,
				"Responsibility", true));
		this.VBList.add(new VulnBool(Vuln.VulnAttr.Security_Override_Guidance,
				"Severity Override Guidance", true));
		this.VBList.add(new VulnBool(Vuln.VulnAttr.Third_Party_Tools,
				"Third Party Tools", true));
	}

	public ArrayList<VulnBool> GetVBList() {
		return this.VBList;
	}

	public void SetValue(Vuln.VulnAttr V, Boolean b) {
		for (int i = 0; i < this.VBList.size(); ++i) {
			if (this.VBList.get((int) i).VA != V)
				continue;
			this.VBList.get((int) i).bVal = b;
		}
	}

	public class VulnBool {
		public String sName;
		public boolean bVal;
		public Vuln.VulnAttr VA;

		public VulnBool(Vuln.VulnAttr Attr, String s, boolean b) {
			this.sName = s;
			this.bVal = b;
			this.VA = Attr;
		}
	}

}
