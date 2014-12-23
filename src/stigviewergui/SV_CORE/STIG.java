/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.SV_CORE;

import java.util.ArrayList;
import stigviewergui.SV_CORE.Pair;
import stigviewergui.SV_CORE.Vuln;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class STIG {
	private ArrayList<Pair<String, ArrayList<String>>> sProfiles = new ArrayList();
	private ArrayList<Vuln> Vuln_List = new ArrayList();
	private static String sLastProfile = "";
	private String STIG_Title = "";
	private String STIG_Description = "";
	private String STIG_Release_Info = "";
	private String STIG_Version = "";
	private String STIG_source = "";
	private String STIG_notice = "";
	private String STIG_Classification = "";
	private String STIG_FileName = "";
	private boolean bHasCCI = false;

	public void setClass(String Classification) {
		for (int i = 0; i < this.Vuln_List.size(); ++i) {
			this.Vuln_List.get(i).setAttr(Vuln.VulnAttr.Class, Classification);
		}
		this.STIG_Classification = Classification.equals("FOUO") ? "UNCLASSIFIED//FOR OFFICIAL USE ONLY"
				: "UNCLASSIFIED";
	}

	public void addProfile(String Profile) {
		this.sProfiles.add(new Pair(Profile, new ArrayList()));
		sLastProfile = Profile;
	}

	public void addVuln(String Profile, String Vuln) {
		for (int i = 0; i < this.sProfiles.size(); ++i) {
			if (!Profile.equals(this.sProfiles.get(i).getFirst()))
				continue;
			this.sProfiles.get(i).getSecond().add(Vuln.trim());
		}
	}

	public void addVulnObj(Vuln vuln) {
		this.Vuln_List.add(vuln);
	}

	public void setSTIG_Title(String sTIG_Title) {
		this.STIG_Title = sTIG_Title;
	}

	public void setSTIG_Description(String sTIG_Description) {
		this.STIG_Description = sTIG_Description;
	}

	public void setSTIG_Release_Info(String sTIG_Release_Info) {
		this.STIG_Release_Info = sTIG_Release_Info;
	}

	public void setSTIG_Version(String sTIG_Version) {
		this.STIG_Version = sTIG_Version;
	}

	public void setSTIG_notice(String sTIG_notice) {
		this.STIG_notice = sTIG_notice;
	}

	public void setSTIG_source(String sTIG_source) {
		this.STIG_source = sTIG_source;
	}

	public void setSTIG_Classification(String STIG_Class) {
		this.STIG_Classification = STIG_Class;
	}

	public void setSTIG_FileName(String sFileName) {
		this.STIG_FileName = sFileName;
	}

	public void setHasCCI(boolean hasCCI) {
		this.bHasCCI = hasCCI;
	}

	public int getVulnCount() {
		return this.Vuln_List.size();
	}

	public int getProfileCount() {
		return this.sProfiles.size();
	}

	public ArrayList<String> getProfileVulnListByIndex(Integer index) {
		return this.sProfiles.get(index).getSecond();
	}

	public ArrayList<Vuln> getVulnList() {
		return this.Vuln_List;
	}

	public Vuln getVulnByIndex(int index) {
		return this.Vuln_List.get(index);
	}

	public String getsLastProfile() {
		return sLastProfile;
	}

	public String getSTIG_Title() {
		return this.STIG_Title;
	}

	public String getSTIG_Version() {
		return this.STIG_Version;
	}

	public String getSTIG_Description() {
		return this.STIG_Description;
	}

	public String getSTIG_Release_Info() {
		return this.STIG_Release_Info;
	}

	public String getSTIG_source() {
		return this.STIG_source;
	}

	public String getSTIG_notice() {
		return this.STIG_notice;
	}

	public String getSTIG_Classification() {
		return this.STIG_Classification;
	}

	public String getSTIG_FileName() {
		return this.STIG_FileName;
	}

	public String getSTIG_FileName_NoExt() {
		if (this.STIG_FileName != null) {
			int i = this.STIG_FileName.length() - 1;
			boolean bExtFound = false;
			while (!(bExtFound || i <= 0)) {
				if (this.STIG_FileName.charAt(i) == '.') {
					bExtFound = true;
					continue;
				}
				--i;
			}
			if (bExtFound) {
				return this.STIG_FileName.substring(0, i);
			}
		}
		return null;
	}

	public boolean getHasCCI() {
		return this.bHasCCI;
	}

	public ArrayList<String> getsProfileVulnList(String Profile) {
		for (int i = 0; i < this.sProfiles.size(); ++i) {
			if (!Profile.equals(this.sProfiles.get(i).getFirst()))
				continue;
			return this.sProfiles.get(i).getSecond();
		}
		return null;
	}

	public ArrayList<Vuln> getsProfileVulnListStr(String pro) {
		ArrayList<String> Profile = this.getsProfileVulnList(pro);
		ArrayList<Vuln> tVuln_List = new ArrayList<Vuln>();
		if (Profile != null) {
			for (int k = 0; k < Profile.size(); ++k) {
				for (int i = 0; i < this.Vuln_List.size(); ++i) {
					if (!this.getVulnByIndex(i).getAttr(Vuln.VulnAttr.Vuln_Num)
							.matches(Profile.get(k)))
						continue;
					tVuln_List.add(this.getVulnByIndex(i));
				}
			}
			return tVuln_List;
		}
		return null;
	}

	public ArrayList<String> getsProfileList() {
		ArrayList<String> sTmp = new ArrayList<String>();
		for (int i = 0; i < this.sProfiles.size(); ++i) {
			sTmp.add(this.sProfiles.get(i).getFirst());
		}
		return sTmp;
	}

	public String toString() {
		return "STIG [Profiles=" + this.sProfiles + ", Vuln_List="
				+ this.Vuln_List + ", STIG_Title=" + this.STIG_Title
				+ ", STIG_Description=" + this.STIG_Description
				+ ", STIG_Release_Info=" + this.STIG_Release_Info
				+ ", STIG_Version=" + this.STIG_Version + "]";
	}
}
