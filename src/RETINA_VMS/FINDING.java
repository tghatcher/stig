/*
 * Decompiled with CFR 0_92.
 */
package RETINA_VMS;

import RETINA_VMS.FINDINGDETAILS;
import RETINA_VMS.FINDINGID;
import RETINA_VMS.SCRIPTRESULTS;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "authenticatedfinding", "tool",
		"toolversion", "scriptresults", "checksuccess", "findingid",
		"findingdetails", "findingstatus" })
@XmlRootElement(name = "FINDING")
public class FINDING {
	@XmlElement(name = "AUTHENTICATED_FINDING")
	protected boolean authenticatedfinding;
	@XmlElement(name = "TOOL", required = 1)
	protected String tool;
	@XmlElement(name = "TOOL_VERSION", required = 1)
	protected String toolversion;
	@XmlElement(name = "SCRIPT_RESULTS", required = 1)
	protected SCRIPTRESULTS scriptresults;
	@XmlElement(name = "CHECK_SUCCESS", required = 1)
	protected String checksuccess;
	@XmlElement(name = "FINDING_ID", required = 1)
	protected List<FINDINGID> findingid;
	@XmlElement(name = "FINDING_DETAILS", required = 1)
	protected FINDINGDETAILS findingdetails;
	@XmlElement(name = "FINDING_STATUS", required = 1)
	protected String findingstatus;

	public boolean isAUTHENTICATEDFINDING() {
		return this.authenticatedfinding;
	}

	public void setAUTHENTICATEDFINDING(boolean value) {
		this.authenticatedfinding = value;
	}

	public String getTOOL() {
		return this.tool;
	}

	public void setTOOL(String value) {
		this.tool = value;
	}

	public String getTOOLVERSION() {
		return this.toolversion;
	}

	public void setTOOLVERSION(String value) {
		this.toolversion = value;
	}

	public SCRIPTRESULTS getSCRIPTRESULTS() {
		return this.scriptresults;
	}

	public void setSCRIPTRESULTS(SCRIPTRESULTS value) {
		this.scriptresults = value;
	}

	public String getCHECKSUCCESS() {
		return this.checksuccess;
	}

	public void setCHECKSUCCESS(String value) {
		this.checksuccess = value;
	}

	public List<FINDINGID> getFINDINGID() {
		if (this.findingid == null) {
			this.findingid = new ArrayList<FINDINGID>();
		}
		return this.findingid;
	}

	public FINDINGDETAILS getFINDINGDETAILS() {
		return this.findingdetails;
	}

	public void setFINDINGDETAILS(FINDINGDETAILS value) {
		this.findingdetails = value;
	}

	public String getFINDINGSTATUS() {
		return this.findingstatus;
	}

	public void setFINDINGSTATUS(String value) {
		this.findingstatus = value;
	}
}
