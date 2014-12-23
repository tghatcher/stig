/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.SV_CORE;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import stigviewergui.STIGSearch;
import stigviewergui.STIGViewerConfig;
import stigviewergui.SV_CORE.STIG;
import stigviewergui.SV_CORE.Util;
import stigviewergui.SV_CORE.Vuln;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SV_Checklist {
	STIGSearch.SortType mySort;
	private ArrayList<Vuln> myVulnList;
	private ArrayList<Vuln> RawList;
	private String STIG_Title;
	private boolean bSortByStatus;
	private STIGViewerConfig myConfig;

	public SV_Checklist(STIG inputSTIG, STIGViewerConfig Conf) {
		this.STIG_Title = inputSTIG.getSTIG_Title();
		this.myConfig = Conf;
		this.LoadRawList(inputSTIG.getVulnList(), true);
		this.LoadVulnList(this.RawList, false);
	}

	public SV_Checklist(ArrayList<Vuln> VulnList, STIG inputSTIG,
			boolean bCleanCheckData, STIGViewerConfig Conf) {
		this.STIG_Title = inputSTIG.getSTIG_Title();
		this.myConfig = Conf;
		this.LoadRawList(VulnList, bCleanCheckData);
		this.LoadVulnList(this.RawList, false);
	}

	public SV_Checklist(ArrayList<Vuln> VulnList, String sSTIGTitle,
			boolean bCleanCheckData, STIGViewerConfig Conf) {
		this.STIG_Title = sSTIGTitle;
		this.myConfig = Conf;
		this.LoadRawList(VulnList, bCleanCheckData);
		this.LoadVulnList(this.RawList, false);
	}

	/*
	 * Enabled force condition propagation Lifted jumps to return sites
	 */
	public final void LoadVulnList(ArrayList<Vuln> List, boolean bCleanCheckData) {
		this.myVulnList = new ArrayList();
		if (bCleanCheckData) {
			for (int i = 0; i < List.size(); ++i) {
				this.myVulnList.add(List.get(i).Clean());
			}
			return;
		}
		for (int i = 0; i < List.size(); ++i) {
			this.myVulnList.add(List.get(i));
		}
	}

	/*
	 * Enabled force condition propagation Lifted jumps to return sites
	 */
	public final void LoadRawList(ArrayList<Vuln> List, boolean bCleanCheckData) {
		this.RawList = new ArrayList();
		if (bCleanCheckData) {
			for (int i = 0; i < List.size(); ++i) {
				this.RawList.add(List.get(i).Clean());
			}
			return;
		}
		for (int i = 0; i < List.size(); ++i) {
			this.RawList.add(List.get(i));
		}
	}

	public ArrayList<Vuln> getRawList() {
		return this.RawList;
	}

	public String GetSTIGTitle() {
		return this.STIG_Title;
	}

	public int getVulnListSize() {
		return this.myVulnList.size();
	}

	public int getRawListSize() {
		return this.RawList.size();
	}

	public Vuln getVuln(int i) {
		return this.myVulnList.get(i);
	}

	public Vuln getRL_Vuln(int i) {
		return this.RawList.get(i);
	}

	public boolean isSortByStatus() {
		return this.bSortByStatus;
	}

	public void setSortByStatus(boolean b) {
		this.bSortByStatus = b;
	}

	public ArrayList<Vuln> getVulnList() {
		return this.myVulnList;
	}

	public int getIndexOf(Vuln v) {
		for (int i = 0; i < this.myVulnList.size(); ++i) {
			if (!this.myVulnList.get(i).equals(v))
				continue;
			return i;
		}
		return -1;
	}

	public String getNote(int i) {
		return this.myVulnList.get(i).getCHK_Notes();
	}

	public CheckState getState(int i) {
		return this.myVulnList.get(i).getCheckState();
	}

	public String getSevOverride(int i) {
		return this.myVulnList.get(i).getCheckSevOverride();
	}

	public String getSevJustification(int i) {
		return this.myVulnList.get(i).getCheckSevJust();
	}

	public String getComments(int i) {
		return this.myVulnList.get(i).getCheckComment();
	}

	public void setState(int i, CheckState inState) {
		this.myVulnList.get(i).setCheckState(inState);
	}

	public void setNote(int i, String s) {
		this.myVulnList.get(i).setCHK_Notes(s);
	}

	public void setSevOverride(int i, String s) {
		this.myVulnList.get(i).setCheckSevOverride(s);
	}

	public void setSevJustification(int i, String s) {
		this.myVulnList.get(i).setCheckSevJust(s);
	}

	public void setComments(int i, String s) {
		this.myVulnList.get(i).setCheckComment(s);
	}

	public STIGSearch.SortType getSortType() {
		return this.mySort;
	}

	public void Sort(String sSort) {
		Vuln v = new Vuln();
		if (sSort.equals("STIG ID")) {
			this.mySort = STIGSearch.SortType.STIGID;
			Collections.sort(this.myVulnList, v.getVulnSTIGID());
		} else if (sSort.equals("VUL ID")) {
			this.mySort = STIGSearch.SortType.VULID;
			Collections.sort(this.myVulnList, v.getVulnIDs());
		} else if (sSort.equals("RULE ID")) {
			this.mySort = STIGSearch.SortType.RULEID;
			Collections.sort(this.myVulnList, v.getVulnRuleID());
		} else if (sSort.equals("CCI ID")) {
			this.mySort = STIGSearch.SortType.CCIID;
			Collections.sort(this.myVulnList, v.getVulnCCI_ID());
		}
		if (this.bSortByStatus) {
			ArrayList<Vuln> Finding = new ArrayList<Vuln>();
			ArrayList<Vuln> NotAFinding = new ArrayList<Vuln>();
			ArrayList<Vuln> NotApplicable = new ArrayList<Vuln>();
			ArrayList<Vuln> NotReviewed = new ArrayList<Vuln>();
			for (Vuln vul : this.myVulnList) {
				switch (vul.getCheckState()) {
				case Not_Reviewed: {
					NotReviewed.add(vul);
					break;
				}
				case Open: {
					Finding.add(vul);
					break;
				}
				case NotAFinding: {
					NotAFinding.add(vul);
					break;
				}
				case Not_Applicable: {
					NotApplicable.add(vul);
				}
				}
			}
			String[] sList = this.myConfig.GetStatusSortList();
			int iCheckCount = this.myVulnList.size();
			this.myVulnList.clear();
			for (String s : sList) {
				if (s.equals("Not Reviewed")) {
					for (Vuln vul2 : NotReviewed) {
						this.myVulnList.add(vul2);
					}
					continue;
				}
				if (s.equals("Open")) {
					for (Vuln vul2 : Finding) {
						this.myVulnList.add(vul2);
					}
					continue;
				}
				if (s.equals("Not a Finding")) {
					for (Vuln vul2 : NotAFinding) {
						this.myVulnList.add(vul2);
					}
					continue;
				}
				if (!s.equals("Not Applicable"))
					continue;
				for (Vuln vul2 : NotApplicable) {
					this.myVulnList.add(vul2);
				}
			}
			if (this.myVulnList.size() != iCheckCount && Util.bAllowPrintln) {
				System.out.println("Sort Error: Count Miss-Match!");
			}
		}
	}

	public static enum CheckState {
		Not_Reviewed, Open, NotAFinding, Not_Applicable;

		private CheckState() {
		}
	}

}
