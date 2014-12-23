/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui;

import File_Interfaces.CCIReader;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import org.xml.sax.ContentHandler;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import stigviewergui.Filter;
import stigviewergui.SV_CORE.Pair;
import stigviewergui.SV_CORE.STIG;
import stigviewergui.SV_CORE.Util;
import stigviewergui.SV_CORE.Vuln;
import stigviewergui.SV_CORE.XMLHandler;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class STIGSearch {
	private ArrayList<STIG> mySTIGs = new ArrayList();
	private ArrayList<String> STIGNames = new ArrayList();
	private ArrayList<Vuln> RootList = null;
	private ArrayList<Vuln> ProfileList = null;
	private ArrayList<Vuln> DisplayList = null;
	private ArrayList<Filter> FilterList = new ArrayList();
	private boolean bSearchAllSTIGs;
	private String mysProfile = "";
	int iCurrentSTIG = -1;
	private CCIReader myCCI;
	private boolean bShowOnlyUniqueRules;

	public STIGSearch(CCIReader CCIin) {
		this.myCCI = CCIin;
		this.bSearchAllSTIGs = false;
		this.bShowOnlyUniqueRules = false;
	}

	public ArrayList<Vuln> STIGSort(SortType st) {
		Vuln v = new Vuln();
		if (this.DisplayList != null) {
			switch (st) {
			case STIGID: {
				Collections.sort(this.DisplayList, v.getVulnSTIGID());
				break;
			}
			case RULEID: {
				Collections.sort(this.DisplayList, v.getVulnRuleID());
				break;
			}
			case VULID: {
				Collections.sort(this.DisplayList, v.getVulnIDs());
				break;
			}
			case CCIID: {
				Collections.sort(this.DisplayList, v.getVulnCCI_ID());
				break;
			}
			default: {
				return null;
			}
			}
			return this.DisplayList;
		}
		return null;
	}

	public boolean bCurrentRefHasCCI() {
		boolean bRet = false;
		if (this.bSearchAllSTIGs) {
			for (STIG s : this.mySTIGs) {
				if (!s.getHasCCI())
					continue;
				bRet = true;
			}
		} else if (this.mySTIGs.get(this.iCurrentSTIG).getHasCCI()) {
			bRet = true;
		}
		return bRet;
	}

	public STIG getCurrentSTIG() {
		if (this.iCurrentSTIG >= 0) {
			return this.mySTIGs.get(this.iCurrentSTIG);
		}
		return null;
	}

	public ArrayList<STIG> getSTIGList() {
		return this.mySTIGs;
	}

	public ArrayList<String> getCurrentSTIGProfileList() {
		return this.mySTIGs.get(this.iCurrentSTIG).getsProfileList();
	}

	public void setCurrentSTIG(int i) {
		try {
			if (this.mySTIGs.get(i) != null) {
				this.iCurrentSTIG = i;
			}
		} catch (Exception e) {
			return;
		}
	}

	public void setShowOnlyUniqueRules(boolean b) {
		this.bShowOnlyUniqueRules = b;
	}

	public boolean getShowOnlyUniqueRules() {
		return this.bShowOnlyUniqueRules;
	}

	public ArrayList<Vuln> FilterList(ArrayList<Vuln> vList, Filter f) {
		ArrayList<Vuln> vuls = new ArrayList<Vuln>();
		for (Vuln v : vList) {
			if (!this.FilterCheck(v, f))
				continue;
			vuls.add(v);
		}
		return vuls;
	}

	public ArrayList<Vuln> SearchAllSTIGs() {
		this.bSearchAllSTIGs = true;
		this.RootList = new ArrayList();
		this.DisplayList = new ArrayList();
		for (int j = 0; j < this.mySTIGs.size(); ++j) {
			for (int i = 0; i < this.mySTIGs.get(j).getVulnList().size(); ++i) {
				this.RootList.add(this.mySTIGs.get(j).getVulnList().get(i));
				this.DisplayList.add(this.mySTIGs.get(j).getVulnList().get(i));
			}
		}
		return this.Search(this.DisplayList);
	}

	public Boolean bIsSearchingAllSTIGs() {
		return this.bSearchAllSTIGs;
	}

	public ArrayList<Vuln> GetRootList() {
		return this.RootList;
	}

	public ArrayList<Vuln> GetProfileList() {
		return this.ProfileList;
	}

	public ArrayList<Vuln> GetDisplayList() {
		return this.DisplayList;
	}

	public ArrayList<Vuln> ClearSearch() {
		for (int i = 0; i < this.FilterList.size(); ++i) {
			this.FilterList.remove(i);
		}
		return this.SetProfile(this.mysProfile);
	}

	public ArrayList<Vuln> SetProfile(String Pro) {
		this.mysProfile = Pro;
		if (this.RootList != null) {
			if (!this.mysProfile.equals("")) {
				this.ProfileList = new ArrayList();
				ArrayList<Vuln> vTemp = this.mySTIGs.get(this.iCurrentSTIG)
						.getsProfileVulnListStr(this.mysProfile);
				for (int i = 0; i < vTemp.size(); ++i) {
					this.ProfileList.add(vTemp.get(i));
				}
				this.LoadDisplayList(this.ProfileList);
			} else {
				this.LoadDisplayList(this.RootList);
			}
			return this.DisplayList;
		}
		return null;
	}

	public ArrayList<Vuln> AddFilter(Filter f) {
		this.FilterList.add(f);
		return this.Search(this.DisplayList, f);
	}

	public ArrayList<Vuln> RemoveFilter(int i) {
		this.FilterList.remove(i);
		if (!(this.mysProfile.equals("") || this.bSearchAllSTIGs)) {
			return this.Search(this.ProfileList);
		}
		return this.Search(this.RootList);
	}

	public ArrayList<Vuln> RefreshSearch() {
		return this.Search(this.DisplayList);
	}

	public ArrayList<Vuln> RawSearch() {
		return this.Search(this.DisplayList);
	}

	private ArrayList<Vuln> Search(ArrayList<Vuln> Source) {
		ArrayList<Vuln> avTemp = new ArrayList<Vuln>();
		for (int i = 0; i < Source.size(); ++i) {
			Vuln v = Source.get(i);
			if (!this.FilterCheck(v, this.FilterList))
				continue;
			if (this.bShowOnlyUniqueRules) {
				boolean bTest = true;
				for (Vuln vn : avTemp) {
					if (!vn.getAttr(Vuln.VulnAttr.Rule_ID).equals(
							v.getAttr(Vuln.VulnAttr.Rule_ID)))
						continue;
					bTest = false;
				}
				if (!bTest)
					continue;
				avTemp.add(v);
				continue;
			}
			avTemp.add(v);
		}
		this.LoadDisplayList(avTemp);
		return this.DisplayList;
	}

	private ArrayList<Vuln> Search(ArrayList<Vuln> Source, Filter myFilter) {
		ArrayList<Vuln> avTemp = new ArrayList<Vuln>();
		for (int i = 0; i < Source.size(); ++i) {
			if (!this.FilterCheck(Source.get(i), myFilter))
				continue;
			avTemp.add(Source.get(i));
		}
		this.LoadDisplayList(avTemp);
		return this.DisplayList;
	}

	private boolean FilterCheck(Vuln v, Filter f) {
		boolean bReturn = false;
		switch (f.GetFilterType()) {
		case Keyword: {
			if (v.getAttr(Vuln.VulnAttr.Check_Content).toLowerCase()
					.contains((CharSequence) f.GetFilterText().toLowerCase())
					|| v.getAttr(Vuln.VulnAttr.Fix_Text)
							.toLowerCase()
							.contains(
									(CharSequence) f.GetFilterText()
											.toLowerCase())
					|| v.getAttr(Vuln.VulnAttr.Group_Title)
							.toLowerCase()
							.contains(
									(CharSequence) f.GetFilterText()
											.toLowerCase())
					|| v.getAttr(Vuln.VulnAttr.Rule_Title)
							.toLowerCase()
							.contains(
									(CharSequence) f.GetFilterText()
											.toLowerCase())
					|| v.getAttr(Vuln.VulnAttr.Vuln_Discuss)
							.toLowerCase()
							.contains(
									(CharSequence) f.GetFilterText()
											.toLowerCase())
					|| v.getAttr(Vuln.VulnAttr.Rule_Ver)
							.toLowerCase()
							.contains(
									(CharSequence) f.GetFilterText()
											.toLowerCase())
					|| v.getAttr(Vuln.VulnAttr.Vuln_Num)
							.toLowerCase()
							.contains(
									(CharSequence) f.GetFilterText()
											.toLowerCase())
					|| v.getAttr(Vuln.VulnAttr.Rule_ID)
							.toLowerCase()
							.contains(
									(CharSequence) f.GetFilterText()
											.toLowerCase())) {
				bReturn = true;
			}
			if (!this.CheckCCI(v, f))
				break;
			bReturn = true;
			break;
		}
		case Title: {
			if (!v.getAttr(Vuln.VulnAttr.Group_Title).toLowerCase()
					.contains((CharSequence) f.GetFilterText().toLowerCase())
					&& !v.getAttr(Vuln.VulnAttr.Rule_Title)
							.toLowerCase()
							.contains(
									(CharSequence) f.GetFilterText()
											.toLowerCase()))
				break;
			bReturn = true;
			break;
		}
		case STIGID: {
			if (!v.getAttr(Vuln.VulnAttr.Rule_Ver).toLowerCase()
					.contains((CharSequence) f.GetFilterText().toLowerCase()))
				break;
			bReturn = true;
			break;
		}
		case VULID: {
			if (!v.getAttr(Vuln.VulnAttr.Vuln_Num).toLowerCase()
					.contains((CharSequence) f.GetFilterText().toLowerCase()))
				break;
			bReturn = true;
			break;
		}
		case RULEID: {
			if (!v.getAttr(Vuln.VulnAttr.Rule_ID).toLowerCase()
					.contains((CharSequence) f.GetFilterText().toLowerCase()))
				break;
			bReturn = true;
			break;
		}
		case CATI: {
			if (!v.getAttr(Vuln.VulnAttr.Severity).equals("high"))
				break;
			bReturn = true;
			break;
		}
		case CATII: {
			if (!v.getAttr(Vuln.VulnAttr.Severity).equals("medium"))
				break;
			bReturn = true;
			break;
		}
		case CATIII: {
			if (!v.getAttr(Vuln.VulnAttr.Severity).equals("low"))
				break;
			bReturn = true;
			break;
		}
		case IACONTROLS: {
			if (!v.getAttr(Vuln.VulnAttr.IA_Controls).toLowerCase()
					.contains((CharSequence) f.GetFilterText().toLowerCase()))
				break;
			bReturn = true;
			break;
		}
		case CCIREF: {
			if (!this.CheckCCI(v, f))
				break;
			bReturn = true;
			break;
		}
		}
		if (f.bGetIsExcludeFilter()) {
			bReturn = !bReturn;
		}
		return bReturn;
	}

	private boolean CheckCCI(Vuln v, Filter f) {
		boolean bReturn = false;
		if (v.GetCCIVals().size() > 0) {
			for (String sCCI : v.GetCCIVals()) {
				if (!sCCI.toLowerCase().contains(
						(CharSequence) f.GetFilterText().toLowerCase()))
					continue;
				bReturn = true;
			}
		}
		if (v.GetCCIs().size() > 0) {
			for (int i = 0; i < v.GetCCIs().size(); ++i) {
				if (v.GetCCI(i) != null && v.GetCCI((int) i).sRefs.size() > 0) {
					if (v.GetCCI((int) i).sDefinition.toLowerCase().contains(
							(CharSequence) f.GetFilterText().toLowerCase())) {
						bReturn = true;
					}
					int j = 0;
					while (j < v.GetCCI((int) i).sRefs.size()) {
						if (v.GetCCI((int) i).sRefs
								.get(j)
								.getFirst()
								.toLowerCase()
								.contains(
										(CharSequence) f.GetFilterText()
												.toLowerCase())
								|| v.GetCCI((int) i).sRefs
										.get(j)
										.getSecond()
										.toLowerCase()
										.contains(
												(CharSequence) f
														.GetFilterText()
														.toLowerCase())) {
							bReturn = true;
						}
						++i;
					}
					continue;
				}
				if (!Util.bAllowPrintln)
					continue;
				System.out.println("CCI Ref is Null!");
			}
		}
		return bReturn;
	}

	private boolean FilterCheck(Vuln v, ArrayList<Filter> f) {
		for (int i = 0; i < f.size(); ++i) {
			if (this.FilterCheck(v, f.get(i)))
				continue;
			return false;
		}
		return true;
	}

	private void LoadDisplayList(ArrayList<Vuln> avIn) {
		if (avIn != null) {
			this.DisplayList = new ArrayList();
			for (int i = 0; i < avIn.size(); ++i) {
				this.DisplayList.add(i, avIn.get(i));
			}
		}
	}

	public ArrayList<Vuln> SetCurrentStig(String s) {
		if (this.STIGNames.contains(s)) {
			int x;
			this.bSearchAllSTIGs = false;
			this.iCurrentSTIG = x = this.STIGNames.indexOf(s);
			this.RootList = new ArrayList();
			for (int i = 0; i < this.mySTIGs.get(this.iCurrentSTIG)
					.getVulnList().size(); ++i) {
				this.RootList.add(this.mySTIGs.get(this.iCurrentSTIG)
						.getVulnList().get(i));
			}
			if (!this.mysProfile.equals("")) {
				this.ProfileList = new ArrayList();
				ArrayList<Vuln> vTemp = this.mySTIGs.get(this.iCurrentSTIG)
						.getsProfileVulnListStr(this.mysProfile);
				for (int i2 = 0; i2 < vTemp.size(); ++i2) {
					this.ProfileList.add(vTemp.get(i2));
				}
				this.LoadDisplayList(this.ProfileList);
			} else {
				this.LoadDisplayList(this.RootList);
			}
			return this.DisplayList;
		}
		return null;
	}

	public void parseSTIGXML(String Filename, STIG currSTIG) {
		block2: {
			try {
				currSTIG.setSTIG_FileName(Filename);
				XMLReader parser = XMLReaderFactory.createXMLReader();
				XMLHandler handler = new XMLHandler(parser, currSTIG,
						this.myCCI);
				parser.setContentHandler(handler);
				parser.parse(Filename);
			} catch (Exception e) {
				if (!Util.bAllowPrintln)
					break block2;
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Vuln> LoadSTIG(File f, String sSTIGIdent) {
		STIG s = new STIG();
		this.parseSTIGXML(f.getPath(), s);
		String sClass = "";
		sClass = f.getName().contains((CharSequence) "FOUO") ? "FOUO" : (f
				.getName().indexOf("U_") == 0 ? "Unclass" : "FOUO");
		s.setClass(sClass);
		this.mySTIGs.add(s);
		this.STIGNames.add(sSTIGIdent);
		this.iCurrentSTIG = this.mySTIGs.indexOf(s);
		return this.SetCurrentStig(sSTIGIdent);
	}

	public String RemoveCurrentSTIG() {
		if (this.mySTIGs.size() > 0) {
			this.mySTIGs.remove(this.iCurrentSTIG);
			this.DisplayList = null;
			this.ProfileList = null;
			this.RootList = null;
			this.STIGNames.remove(this.iCurrentSTIG);
			if (this.STIGNames.size() > 0) {
				this.SetCurrentStig(this.STIGNames.get(0));
				return this.STIGNames.get(0);
			}
			this.iCurrentSTIG = -1;
			return null;
		}
		return null;
	}

	public int GetNumberOfSTIGs() {
		return this.mySTIGs.size();
	}

	public static enum SortType {
		STIGID, RULEID, VULID, CCIID;

		private SortType() {
		}
	}

}
