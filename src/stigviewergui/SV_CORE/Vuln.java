/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.SV_CORE;

import File_Interfaces.CCIReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import stigviewergui.SV_CORE.SV_Checklist;
import stigviewergui.SV_CORE.Util;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Vuln {
	private ArrayList<String> attr;
	private VulnSTIGID VulnSTIGID;
	private VulnRuleID VulnRuleID;
	private VulnIDs VulnIDs;
	private VulnCCI VulnCCI_ID;
	ArrayList<CCIReader.CCI_Store> myCCI;
	private ArrayList<String> CCI_Nums;
	private String sCHK_Notes;
	private SV_Checklist.CheckState CHK_State;
	private String sCHK_SEV_OVER;
	private String sCHK_SEV_Just;
	private String sCHK_Comment;

	public Vuln() {
		this.VulnSTIGID = new VulnSTIGID();
		this.VulnRuleID = new VulnRuleID();
		this.VulnIDs = new VulnIDs();
		this.VulnCCI_ID = new VulnCCI();
		int x = VulnAttr.values().length;
		this.attr = new ArrayList();
		this.CCI_Nums = new ArrayList();
		this.myCCI = new ArrayList();
		for (int i = 0; i < x - 1; ++i) {
			this.attr.add("");
		}
		this.sCHK_Notes = "";
		this.CHK_State = SV_Checklist.CheckState.Not_Reviewed;
		this.sCHK_SEV_OVER = "";
		this.sCHK_SEV_Just = "";
		this.sCHK_Comment = "";
	}

	public Vuln Clean() {
		Vuln v = new Vuln();
		v.setAttrs(this.attr);
		v.setCCI(this.myCCI);
		v.setCCIs(this.CCI_Nums);
		return v;
	}

	public String getAttr(VulnAttr index) {
		String sTemp = this.attr.get(index.ordinal());
		if (sTemp != null) {
			return sTemp;
		}
		return "";
	}

	public ArrayList<String> getAttrs() {
		return this.attr;
	}

	public VulnSTIGID getVulnSTIGID() {
		return new VulnSTIGID();
	}

	public VulnRuleID getVulnRuleID() {
		return this.VulnRuleID;
	}

	public VulnIDs getVulnIDs() {
		return this.VulnIDs;
	}

	public VulnCCI getVulnCCI_ID() {
		return new VulnCCI();
	}

	public String getCHK_Notes() {
		return this.sCHK_Notes;
	}

	public SV_Checklist.CheckState getCheckState() {
		return this.CHK_State;
	}

	public String getCheckSevOverride() {
		return this.sCHK_SEV_OVER;
	}

	public String getCheckSevJust() {
		return this.sCHK_SEV_Just;
	}

	public String getCheckComment() {
		return this.sCHK_Comment;
	}

	public CCIReader.CCI_Store GetCCI(int i) {
		if (i >= 0 && i < this.myCCI.size()) {
			return this.myCCI.get(i);
		}
		return new CCIReader.CCI_Store();
	}

	public CCIReader.CCI_Store GetCCIbyName(String s) {
		block3: {
			try {
				for (CCIReader.CCI_Store cci : this.myCCI) {
					if (cci == null || !cci.sID.equals(s))
						continue;
					return cci;
				}
			} catch (Exception e) {
				if (!Util.bAllowPrintln)
					break block3;
				e.printStackTrace();
			}
		}
		return null;
	}

	public ArrayList<CCIReader.CCI_Store> GetCCIs() {
		return this.myCCI;
	}

	public String GetCCIVal(int i) {
		if (this.CCI_Nums.size() > 0) {
			return this.CCI_Nums.get(i);
		}
		return "";
	}

	public ArrayList<String> GetCCIVals() {
		return this.CCI_Nums;
	}

	public void setAttr(VulnAttr index, String str) {
		this.attr.set(index.ordinal(), str);
	}

	public void setAttrs(ArrayList<String> attributes) {
		this.attr = attributes;
	}

	public void setVulnSTIGID(VulnSTIGID vulnSTIGID) {
		this.VulnSTIGID = vulnSTIGID;
	}

	public void setVulnRuleID(VulnRuleID vulnRuleID) {
		this.VulnRuleID = vulnRuleID;
	}

	public void setVulnIDs(VulnIDs vulnIDs) {
		this.VulnIDs = vulnIDs;
	}

	public void setCHK_Notes(String sNotes) {
		this.sCHK_Notes = sNotes;
	}

	public void setCheckState(SV_Checklist.CheckState State) {
		this.CHK_State = State;
	}

	public void setCheckSevOverride(String sSevOver) {
		this.sCHK_SEV_OVER = sSevOver;
	}

	public void setCheckSevJust(String sSevJust) {
		this.sCHK_SEV_Just = sSevJust;
	}

	public void setCheckComment(String sComment) {
		this.sCHK_Comment = sComment;
	}

	public void addCCI(CCIReader.CCI_Store cci) {
		this.myCCI.add(cci);
	}

	public void setCCI(ArrayList<CCIReader.CCI_Store> CCIs) {
		this.myCCI = CCIs;
	}

	public void clearCCIs() {
		this.myCCI.clear();
	}

	public void addCCIVal(String s) {
		this.CCI_Nums.add(s);
	}

	public void setCCIs(ArrayList<String> CCI_Names) {
		this.CCI_Nums = CCI_Names;
	}

	public void clearCCIVals() {
		this.CCI_Nums.clear();
	}

	/*
	 * This class specifies class file version 49.0 but uses Java 6 signatures.
	 * Assumed Java 6.
	 */
	public class VulnCCI implements Comparator<Vuln> {
		/*
		 * Enabled force condition propagation Lifted jumps to return sites
		 */
		@Override
		public int compare(Vuln o1, Vuln o2) {
			try {
				if (o1.GetCCIVals().size() > 0 && o2.GetCCIVals().size() > 0) {
					if (o1.GetCCIVal(0).length() > o2.GetCCIVal(0).length()) {
						return 1;
					}
					if (o1.GetCCIVal(0).length() < o2.GetCCIVal(0).length()) {
						return -1;
					}
					if (o1.GetCCIVal(0).length() <= 4
							|| o2.GetCCIVal(0).length() <= 4)
						return 0;
					if (new Integer(o1.GetCCIVal(0).substring(4))
							.compareTo(new Integer(o2.GetCCIVal(0).substring(4))) > 0) {
						return 1;
					}
					if (new Integer(o1.GetCCIVal(0).substring(4))
							.compareTo(new Integer(o2.GetCCIVal(0).substring(4))) >= 0)
						return 0;
					return -1;
				}
				if (o1.GetCCIVals().size() > 0 && o2.GetCCIVals().size() < 1) {
					return -1;
				}
				if (o1.GetCCIVals().size() >= 1 || o2.GetCCIVals().size() <= 0)
					return 0;
				return 1;
			} catch (Exception e) {
				if (!Util.bAllowPrintln)
					return 0;
				System.err.println("CCI Sort Error.");
			}
			return 0;
		}
	}

	/*
	 * This class specifies class file version 49.0 but uses Java 6 signatures.
	 * Assumed Java 6.
	 */
	public class VulnIDs implements Comparator<Vuln> {
		@Override
		public int compare(Vuln o1, Vuln o2) {
			if (o1.getAttr(VulnAttr.Vuln_Num).length() > o2.getAttr(
					VulnAttr.Vuln_Num).length()) {
				return 1;
			}
			if (o1.getAttr(VulnAttr.Vuln_Num).length() < o2.getAttr(
					VulnAttr.Vuln_Num).length()) {
				return -1;
			}
			if (o1.getAttr(VulnAttr.Vuln_Num).compareTo(
					o2.getAttr(VulnAttr.Vuln_Num)) > 0) {
				return 1;
			}
			if (o1.getAttr(VulnAttr.Vuln_Num).compareTo(
					o2.getAttr(VulnAttr.Vuln_Num)) < 0) {
				return -1;
			}
			return 0;
		}
	}

	/*
	 * This class specifies class file version 49.0 but uses Java 6 signatures.
	 * Assumed Java 6.
	 */
	public class VulnRuleID implements Comparator<Vuln> {
		@Override
		public int compare(Vuln o1, Vuln o2) {
			return o1.getAttr(VulnAttr.Rule_ID).compareTo(
					o2.getAttr(VulnAttr.Rule_ID));
		}
	}

	/*
	 * This class specifies class file version 49.0 but uses Java 6 signatures.
	 * Assumed Java 6.
	 */
	public class VulnSTIGID implements Comparator<Vuln> {
		@Override
		public int compare(Vuln o1, Vuln o2) {
			if (o1.getAttr(VulnAttr.Rule_Ver).length() > o2.getAttr(
					VulnAttr.Rule_Ver).length()) {
				return 1;
			}
			if (o1.getAttr(VulnAttr.Rule_Ver).length() < o2.getAttr(
					VulnAttr.Rule_Ver).length()) {
				return -1;
			}
			return o1.getAttr(VulnAttr.Rule_Ver).compareTo(
					o2.getAttr(VulnAttr.Rule_Ver));
		}
	}

	public static enum VulnAttr {
		Vuln_Num("Vuln ID"), Severity("Severity"), Group_Title("Group Title"), Rule_ID(
				"Rule ID"), Rule_Ver("STIG ID"), Rule_Title("Rule Title"), Vuln_Discuss(
				"Discussion"), IA_Controls("IA Controls"), Check_Content(
				"Check Content"), Fix_Text("Fix Text"), False_Positives(
				"False Positives"), False_Negatives("False Negatives"), Documentable(
				"Documentable"), Mitigations("Mitigations"), Potential_Impact(
				"Potential Impact"), Third_Party_Tools("Third Party Tools"), Mitigation_Control(
				"Mitigation Control"), Responsibility("Responsibility"), Security_Override_Guidance(
				"Severity Override Guidance"), Check_Content_Ref(
				"Check Content Reference"), Class("Classification"), STIGRef(
				"STIG"), TargetKey("VMS Asset Posture"), NULL("null");

		String text;

		private VulnAttr(String s) {
			this.text = s;
		}

		public String toString() {
			return this.text;
		}
	}

}
