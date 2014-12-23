/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.SV_CORE;

import File_Interfaces.CCIReader;
import java.awt.Color;
import java.util.ArrayList;
import stigviewergui.STIGSearch;
import stigviewergui.STIGViewerConfig;
import stigviewergui.SV_CORE.Pair;
import stigviewergui.SV_CORE.STIG;
import stigviewergui.SV_CORE.Util;
import stigviewergui.SV_CORE.Vuln;

public class VulnStringGen {
	STIGSearch Searcher;
	STIGViewerConfig myConfig;

	public VulnStringGen(STIGSearch mySearcher, STIGViewerConfig Config) {
		this.Searcher = mySearcher;
		this.myConfig = Config;
	}

	public String GetRTFHeader(Vuln myVuln) {
		if (myVuln != null) {
			return this.GetRTFHeader(myVuln, false);
		}
		return "";
	}

	public String GetRTFBody(Vuln myVuln) {
		if (myVuln != null) {
			return this.GetRTFBody(myVuln, false);
		}
		return "";
	}

	public String GetRTFHeader(Vuln myVuln, boolean bUseLine) {
		String sHeader = "";
		String sNL = bUseLine ? "\\line " : "\\par ";
		sHeader = "{\\rtf1\\ansi\\f0\\pard {\\fonttbl {\\f0 "
				+ this.myConfig.sGetFont() + ";}}";
		sHeader = sHeader + "\\fs"
				+ Integer.toString(this.myConfig.getFontSize()) + " ";
		sHeader = sHeader + "{\\colortbl;\\red"
				+ this.myConfig.GetTextColor().getRed() + "\\green"
				+ this.myConfig.GetTextColor().getGreen() + "\\blue"
				+ this.myConfig.GetTextColor().getBlue() + ";}\\cf1";
		if (this.Searcher.bIsSearchingAllSTIGs().booleanValue()) {
			sHeader = sHeader + myVuln.getAttr(Vuln.VulnAttr.STIGRef) + sNL;
		}
		sHeader = sHeader + "{\\b Rule Title: } "
				+ myVuln.getAttr(Vuln.VulnAttr.Rule_Title) + sNL
				+ "{\\b STIG ID: }" + myVuln.getAttr(Vuln.VulnAttr.Rule_Ver)
				+ "{\\b   Rule ID: }" + myVuln.getAttr(Vuln.VulnAttr.Rule_ID)
				+ "{\\b   Vuln ID: }" + myVuln.getAttr(Vuln.VulnAttr.Vuln_Num)
				+ sNL;
		sHeader = sHeader + "{\\b Severity: }";
		sHeader = myVuln.getAttr(Vuln.VulnAttr.Severity).equals("high") ? sHeader
				+ "CAT I"
				: (myVuln.getAttr(Vuln.VulnAttr.Severity).equals("medium") ? sHeader
						+ "CAT II"
						: (myVuln.getAttr(Vuln.VulnAttr.Severity).equals("low") ? sHeader
								+ "CAT III"
								: sHeader + "Unknown"));
		sHeader = sHeader + "{\\b  Class: }"
				+ myVuln.getAttr(Vuln.VulnAttr.Class) + sNL;
		if (Util.bHasAlphNum(myVuln.getAttr(Vuln.VulnAttr.Check_Content_Ref))
				&& myVuln.getAttr(Vuln.VulnAttr.Check_Content_Ref).contains(
						(CharSequence) "oval:")) {
			sHeader = sHeader + "{\\b SCAP:}"
					+ myVuln.getAttr(Vuln.VulnAttr.Check_Content_Ref);
		}
		sHeader = sHeader + sNL + "}";
		return sHeader;
	}

	public String GetRTFBody(Vuln myVuln, boolean bUseLine) {
		ArrayList<CCIReader.CCI_Store> CCIs;
		String sNL = bUseLine ? "\\line " : "\\par ";
		String sBody = "{\\rtf1\\ansi\\f0\\pard {\\fonttbl {\\f0 "
				+ this.myConfig.sGetFont() + ";}}";
		sBody = sBody + "\\fs" + Integer.toString(this.myConfig.getFontSize())
				+ " ";
		sBody = sBody + "{\\colortbl;\\red"
				+ this.myConfig.GetTextColor().getRed() + "\\green"
				+ this.myConfig.GetTextColor().getGreen() + "\\blue"
				+ this.myConfig.GetTextColor().getBlue() + ";}\\cf1";
		if (Util.bHasAlphNum(myVuln.getAttr(Vuln.VulnAttr.Vuln_Discuss))
				&& this.myConfig.CheckIfShow(Vuln.VulnAttr.Vuln_Discuss)) {
			sBody = sBody
					+ this.Body("Discussion",
							myVuln.getAttr(Vuln.VulnAttr.Vuln_Discuss),
							bUseLine);
		}
		if (this.myConfig.CheckIfShow(Vuln.VulnAttr.Documentable)) {
			String sTemp = myVuln.getAttr(Vuln.VulnAttr.Documentable).equals(
					"true") ? "Yes" : "No";
			sBody = sBody + this.Body("Documentable", sTemp, bUseLine, true);
		}
		if (Util.bHasAlphNum(myVuln.getAttr(Vuln.VulnAttr.False_Negatives))
				&& this.myConfig.CheckIfShow(Vuln.VulnAttr.False_Negatives)) {
			sBody = sBody
					+ this.Body("False Negatives",
							myVuln.getAttr(Vuln.VulnAttr.False_Negatives),
							bUseLine);
		}
		if (Util.bHasAlphNum(myVuln.getAttr(Vuln.VulnAttr.False_Positives))
				&& this.myConfig.CheckIfShow(Vuln.VulnAttr.False_Positives)) {
			sBody = sBody
					+ this.Body("False Positives",
							myVuln.getAttr(Vuln.VulnAttr.False_Positives),
							bUseLine);
		}
		if (Util.bHasAlphNum(myVuln.getAttr(Vuln.VulnAttr.Mitigation_Control))
				&& this.myConfig.CheckIfShow(Vuln.VulnAttr.Mitigation_Control)) {
			sBody = sBody
					+ this.Body("Mitigation Control",
							myVuln.getAttr(Vuln.VulnAttr.Mitigation_Control),
							bUseLine);
		}
		if (Util.bHasAlphNum(myVuln.getAttr(Vuln.VulnAttr.Mitigations))
				&& this.myConfig.CheckIfShow(Vuln.VulnAttr.Mitigations)) {
			sBody = sBody
					+ this.Body("Mitigations",
							myVuln.getAttr(Vuln.VulnAttr.Mitigations), bUseLine);
		}
		if (Util.bHasAlphNum(myVuln.getAttr(Vuln.VulnAttr.Potential_Impact))
				&& this.myConfig.CheckIfShow(Vuln.VulnAttr.Potential_Impact)) {
			sBody = sBody
					+ this.Body("Potential Impacts",
							myVuln.getAttr(Vuln.VulnAttr.Potential_Impact),
							bUseLine);
		}
		if (Util.bHasAlphNum(myVuln.getAttr(Vuln.VulnAttr.Responsibility))
				&& this.myConfig.CheckIfShow(Vuln.VulnAttr.Responsibility)) {
			sBody = sBody
					+ this.Body("Responsibility",
							myVuln.getAttr(Vuln.VulnAttr.Responsibility),
							bUseLine);
		}
		if (Util.bHasAlphNum(myVuln
				.getAttr(Vuln.VulnAttr.Security_Override_Guidance))
				&& this.myConfig
						.CheckIfShow(Vuln.VulnAttr.Security_Override_Guidance)) {
			sBody = sBody
					+ this.Body("Severity Override Guidance", myVuln
							.getAttr(Vuln.VulnAttr.Security_Override_Guidance),
							bUseLine);
		}
		if (Util.bHasAlphNum(myVuln.getAttr(Vuln.VulnAttr.Third_Party_Tools))
				&& this.myConfig.CheckIfShow(Vuln.VulnAttr.Third_Party_Tools)) {
			sBody = sBody
					+ this.Body("Third Party Tools",
							myVuln.getAttr(Vuln.VulnAttr.Third_Party_Tools),
							bUseLine);
		}
		if (Util.bHasAlphNum(myVuln.getAttr(Vuln.VulnAttr.Check_Content))
				&& this.myConfig.CheckIfShow(Vuln.VulnAttr.Check_Content)) {
			sBody = sBody
					+ this.Body("Check Content",
							myVuln.getAttr(Vuln.VulnAttr.Check_Content),
							bUseLine);
		}
		if (Util.bHasAlphNum(myVuln.getAttr(Vuln.VulnAttr.Fix_Text))
				&& this.myConfig.CheckIfShow(Vuln.VulnAttr.Fix_Text)) {
			sBody = sBody
					+ this.Body("Fix Text",
							myVuln.getAttr(Vuln.VulnAttr.Fix_Text), bUseLine);
		}
		if (Util.bHasAlphNum(myVuln.getAttr(Vuln.VulnAttr.IA_Controls))
				&& this.myConfig.CheckIfShow(Vuln.VulnAttr.IA_Controls)) {
			sBody = sBody + "{\\b IA Controls:}";
			sBody = sBody + myVuln.getAttr(Vuln.VulnAttr.IA_Controls) + sNL;
		}
		if (myVuln.myCCI != null
				&& this.myConfig.CheckIfShow_CCI()
				&& (CCIs = myVuln.GetCCIs()).size() == myVuln.GetCCIVals()
						.size() && myVuln.GetCCIVals().size() > 0) {
			for (String sCCI : myVuln.GetCCIVals()) {
				sBody = sBody + "{\\b CCI: }";
				sBody = sBody + sCCI + sNL;
				CCIReader.CCI_Store cci = myVuln.GetCCIbyName(sCCI);
				if (cci != null) {
					if (this.myConfig.CheckIfShow_CCIDesc()) {
						sBody = sBody + cci.sDefinition + sNL;
					}
					if (this.myConfig.CheckIfShow_CCIMap()) {
						for (int k = 0; k < cci.sRefs.size(); ++k) {
							sBody = sBody + cci.sRefs.get(k).getFirst()
									+ " :: " + cci.sRefs.get(k).getSecond()
									+ sNL;
						}
					}
				}
				sBody = sBody + sNL;
			}
		}
		sBody = !bUseLine ? sBody + " \\par}" : sBody + "}";
		return sBody;
	}

	public String GetHTMLHeader(int iTableWidth) {
		String sRet = "";
		sRet = sRet + "<table align = \"center\" width = \"" + iTableWidth
				+ "\" border = \"1\">";
		STIG sOut = this.Searcher.getCurrentSTIG();
		sRet = sRet + "<h2><br>" + sOut.getSTIG_Title() + "<br>";
		sRet = sRet + "Version: " + sOut.getSTIG_Version() + "<br>";
		sRet = sRet + sOut.getSTIG_Release_Info() + "<br>";
		sRet = sRet + sOut.getSTIG_Description() + "</h2><br>";
		sRet = sRet + "</table> <br><br>";
		return sRet;
	}

	public String GetHTMLBody(int iTableWidth) {
		String asRet = "";
		ArrayList<Vuln> vOut = this.Searcher.GetDisplayList();
		for (int i = 0; i < vOut.size(); ++i) {
			ArrayList<CCIReader.CCI_Store> CCIs;
			String sTemp = "";
			sTemp = sTemp + "<table align = \"center\" width = \""
					+ iTableWidth + "\" border = \"1\">";
			sTemp = sTemp + " <tr><td>";
			sTemp = sTemp
					+ this.sHeadHTML("Group Title",
							vOut.get(i).getAttr(Vuln.VulnAttr.Group_Title),
							true) + " <br>";
			if (vOut.get(i).getAttr(Vuln.VulnAttr.Severity).equals("high")) {
				sTemp = sTemp + this.sHeadHTML("Severity", "CAT I", true)
						+ " <br>";
			} else if (vOut.get(i).getAttr(Vuln.VulnAttr.Severity)
					.equals("medium")) {
				sTemp = sTemp + this.sHeadHTML("Severity", "CAT II", true)
						+ " <br>";
			} else if (vOut.get(i).getAttr(Vuln.VulnAttr.Severity)
					.equals("low")) {
				sTemp = sTemp + this.sHeadHTML("Severity", "CAT III", true)
						+ " <br>";
			}
			sTemp = sTemp
					+ this.sHeadHTML("Group ID (Vulid)",
							vOut.get(i).getAttr(Vuln.VulnAttr.Vuln_Num), true)
					+ this.sHeadHTML(" Rule ID",
							vOut.get(i).getAttr(Vuln.VulnAttr.Rule_ID), true)
					+ this.sHeadHTML(" Rule Version (STIG-ID)", vOut.get(i)
							.getAttr(Vuln.VulnAttr.Rule_Ver), true) + " <br>";
			sTemp = sTemp
					+ this.sHeadHTML("Rule Title",
							vOut.get(i).getAttr(Vuln.VulnAttr.Rule_Title), true);
			if (vOut.get(i).getAttr(Vuln.VulnAttr.Check_Content_Ref) != null
					&& vOut.get(i).getAttr(Vuln.VulnAttr.Check_Content_Ref)
							.length() > 0) {
				sTemp = sTemp
						+ this.sHeadHTML(
								" SCAP",
								vOut.get(i).getAttr(
										Vuln.VulnAttr.Check_Content_Ref), true);
			}
			sTemp = sTemp + " </td></tr>";
			sTemp = sTemp + " <tr><td>";
			if (Util.bHasAlphNum(vOut.get(i)
					.getAttr(Vuln.VulnAttr.Vuln_Discuss))
					&& this.myConfig.CheckIfShow(Vuln.VulnAttr.Vuln_Discuss)) {
				sTemp = sTemp
						+ this.sBodyHTML("Vulnerability Discussion", vOut
								.get(i).getAttr(Vuln.VulnAttr.Vuln_Discuss),
								true);
			}
			if (Util.bHasAlphNum(vOut.get(i).getAttr(
					Vuln.VulnAttr.False_Positives))
					&& this.myConfig.CheckIfShow(Vuln.VulnAttr.False_Positives)) {
				sTemp = sTemp
						+ this.sBodyHTML("False Positives", vOut.get(i)
								.getAttr(Vuln.VulnAttr.False_Positives), true);
			}
			if (Util.bHasAlphNum(vOut.get(i).getAttr(
					Vuln.VulnAttr.False_Negatives))
					&& this.myConfig.CheckIfShow(Vuln.VulnAttr.False_Negatives)) {
				sTemp = sTemp
						+ this.sBodyHTML("False Negatives", vOut.get(i)
								.getAttr(Vuln.VulnAttr.False_Negatives), true);
			}
			if (this.myConfig.CheckIfShow(Vuln.VulnAttr.Documentable)) {
				sTemp = vOut.get(i).getAttr(Vuln.VulnAttr.Documentable)
						.equals("true") ? sTemp
						+ this.sBodyHTML("Documentable", "Yes", true) : sTemp
						+ this.sBodyHTML("Documentable", "No", true);
			}
			if (Util.bHasAlphNum(vOut.get(i).getAttr(
					Vuln.VulnAttr.Responsibility))
					&& this.myConfig.CheckIfShow(Vuln.VulnAttr.Responsibility)) {
				sTemp = sTemp
						+ this.sBodyHTML(
								"Responsibility",
								vOut.get(i).getAttr(
										Vuln.VulnAttr.Responsibility), true);
			}
			if (Util.bHasAlphNum(vOut.get(i).getAttr(Vuln.VulnAttr.IA_Controls))
					&& this.myConfig.CheckIfShow(Vuln.VulnAttr.IA_Controls)) {
				sTemp = sTemp + "<b>IA Controls: </b>";
				sTemp = sTemp + vOut.get(i).getAttr(Vuln.VulnAttr.IA_Controls);
				sTemp = sTemp + " <br><br>";
			}
			if (Util.bHasAlphNum(vOut.get(i).getAttr(
					Vuln.VulnAttr.Check_Content))
					&& this.myConfig.CheckIfShow(Vuln.VulnAttr.Check_Content)) {
				sTemp = sTemp
						+ this.sBodyHTML("Check Content",
								vOut.get(i)
										.getAttr(Vuln.VulnAttr.Check_Content),
								true);
			}
			if (Util.bHasAlphNum(vOut.get(i).getAttr(Vuln.VulnAttr.Fix_Text))
					&& this.myConfig.CheckIfShow(Vuln.VulnAttr.Fix_Text)) {
				sTemp = sTemp
						+ this.sBodyHTML("Fix Text",
								vOut.get(i).getAttr(Vuln.VulnAttr.Fix_Text),
								true);
			}
			if (Util.bHasAlphNum(vOut.get(i).getAttr(
					Vuln.VulnAttr.Mitigation_Control))
					&& this.myConfig
							.CheckIfShow(Vuln.VulnAttr.Mitigation_Control)) {
				sTemp = sTemp
						+ this.sBodyHTML("Mitigation Control", vOut.get(i)
								.getAttr(Vuln.VulnAttr.Mitigation_Control),
								true);
			}
			if (Util.bHasAlphNum(vOut.get(i).getAttr(Vuln.VulnAttr.Mitigations))
					&& this.myConfig.CheckIfShow(Vuln.VulnAttr.Mitigations)) {
				sTemp = sTemp
						+ this.sBodyHTML("Mitigations",
								vOut.get(i).getAttr(Vuln.VulnAttr.Mitigations),
								true);
			}
			if (Util.bHasAlphNum(vOut.get(i).getAttr(
					Vuln.VulnAttr.Potential_Impact))
					&& this.myConfig
							.CheckIfShow(Vuln.VulnAttr.Potential_Impact)) {
				sTemp = sTemp
						+ this.sBodyHTML("Potential Impacts", vOut.get(i)
								.getAttr(Vuln.VulnAttr.Potential_Impact), true);
			}
			if (Util.bHasAlphNum(vOut.get(i).getAttr(
					Vuln.VulnAttr.Security_Override_Guidance))
					&& this.myConfig
							.CheckIfShow(Vuln.VulnAttr.Security_Override_Guidance)) {
				sTemp = sTemp
						+ this.sBodyHTML(
								"Severity Override Guidance",
								vOut.get(i)
										.getAttr(
												Vuln.VulnAttr.Security_Override_Guidance),
								true);
			}
			if (Util.bHasAlphNum(vOut.get(i).getAttr(
					Vuln.VulnAttr.Third_Party_Tools))
					&& this.myConfig
							.CheckIfShow(Vuln.VulnAttr.Third_Party_Tools)) {
				sTemp = sTemp
						+ this.sBodyHTML("Third Party Tools", vOut.get(i)
								.getAttr(Vuln.VulnAttr.Third_Party_Tools), true);
			}
			if (vOut.get((int) i).myCCI != null
					&& this.myConfig.CheckIfShow_CCI()
					&& (CCIs = vOut.get(i).GetCCIs()).size() == vOut.get(i)
							.GetCCIVals().size()
					&& vOut.get(i).GetCCIVals().size() > 0) {
				for (String sCCI : vOut.get(i).GetCCIVals()) {
					sTemp = sTemp + "<b> CCI: </b>";
					sTemp = sTemp + sCCI + "<br>";
					CCIReader.CCI_Store cci = vOut.get(i).GetCCIbyName(sCCI);
					if (cci != null) {
						if (this.myConfig.CheckIfShow_CCIDesc()) {
							sTemp = sTemp + cci.sDefinition + "<br>";
						}
						if (this.myConfig.CheckIfShow_CCIMap()) {
							for (int k = 0; k < cci.sRefs.size(); ++k) {
								sTemp = sTemp + cci.sRefs.get(k).getFirst()
										+ " :: " + cci.sRefs.get(k).getSecond()
										+ "<br>";
							}
						}
					}
					sTemp = sTemp + "<br>";
				}
			}
			sTemp = sTemp + "</td></tr><br>";
			sTemp = sTemp + "</table><br>";
			asRet = asRet + sTemp;
		}
		return asRet;
	}

	private String sBRInsert(String s) {
		String sRet = "";
		int iLast = 0;
		if (s.length() > 1) {
			for (int i = 1; i < s.length(); ++i) {
				if (s.charAt(i) == '\n' && iLast <= i) {
					sRet = sRet + s.substring(iLast, i) + " <br>";
					iLast = i;
				}
				if (s.charAt(i) <= '\u2000' || iLast > i)
					continue;
				sRet = sRet + s.substring(iLast, i) + "&#x"
						+ Integer.toHexString(s.charAt(i)) + ";";
				iLast = i + 1;
			}
			if (iLast <= s.length()) {
				sRet = sRet + s.substring(iLast, s.length());
			}
		}
		sRet = sRet + " <br><br>";
		return sRet;
	}

	private String sHeadHTML(String sCaption, String sIn, boolean bHasCaption) {
		String sTemp = "";
		if (bHasCaption) {
			sTemp = sTemp + "<b>" + sCaption + ": </b>";
		}
		sTemp = sTemp + sIn;
		return sTemp;
	}

	private String sBodyHTML(String sCaption, String sIn, boolean bHasCaption) {
		String sTemp = "";
		if (bHasCaption) {
			sTemp = sTemp + "<b>" + sCaption + ": </b><br>";
		}
		sTemp = sTemp + this.sBRInsert(sIn);
		return sTemp;
	}

	private String Body(String sTitle, String sData, boolean bUseLine) {
		return this.Body(sTitle, sData, bUseLine, false);
	}

	private String Body(String sTitle, String sData, boolean bUseLine,
			boolean bNoReturnAfterTitle) {
		String sRet = "";
		if (sTitle != null) {
			sRet = "{\\b " + sTitle + ":} ";
			if (!bNoReturnAfterTitle) {
				sRet = bUseLine ? sRet + "\\line " : sRet + "\\par ";
			}
		}
		if (bUseLine) {
			sRet = sRet + this.RTFnlX_loop_line(sData);
			sRet = sRet + "\\line \\line ";
		} else {
			sRet = sRet + this.RTFnlX_loop_par(sData);
			sRet = sRet + "\\par \\par ";
		}
		return sRet;
	}

	private String RTFnlX_loop_par(String s) {
		String sRet = "";
		int iLast = 0;
		if (s.length() > 1) {
			for (int i = 1; i < s.length(); ++i) {
				if (s.charAt(i) != '\n' && s.charAt(i) != '\\'
						&& s.charAt(i) != '{' && s.charAt(i) != '}')
					continue;
				if (iLast <= i) {
					sRet = s.charAt(i) == '\n' ? sRet + s.substring(iLast, i)
							+ "\\par " : (s.charAt(i) == '\\' ? sRet
							+ s.substring(iLast, i) + "\\" : sRet
							+ s.substring(iLast, i) + "\\");
				}
				iLast = i;
			}
			if (iLast <= s.length()) {
				sRet = sRet + s.substring(iLast, s.length());
			}
		}
		return sRet;
	}

	private String RTFnlX_loop_line(String s) {
		String sRet = "";
		int iLast = 0;
		if (s.length() >= 1) {
			for (int i = 1; i < s.length(); ++i) {
				if (s.charAt(i) == '\n' || s.charAt(i) == '\\'
						|| s.charAt(i) == '{' || s.charAt(i) == '}') {
					if (iLast <= i) {
						sRet = s.charAt(i) == '\n' ? sRet
								+ s.substring(iLast, i) + "\\line " : (s
								.charAt(i) == '\\' ? sRet
								+ s.substring(iLast, i) + "\\" : sRet
								+ s.substring(iLast, i) + "\\");
					}
					iLast = i;
				}
				if (s.charAt(i) <= '' || iLast > i)
					continue;
				sRet = sRet + s.substring(iLast, i) + "\\uc1" + " \\u"
						+ Integer.toString(s.charAt(i)) + ";";
				iLast = i + 1;
			}
			if (iLast <= s.length()) {
				sRet = sRet + s.substring(iLast, s.length());
			}
		}
		return sRet;
	}
}
