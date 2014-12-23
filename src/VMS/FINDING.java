/*
 * Decompiled with CFR 0_92.
 */
package VMS;

import VMS.FINDINGDETAILS;
import VMS.FINDINGID;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "findingid", "findingstatus",
		"findingdetails", "sevoverridecode", "sevoverridetext",
		"scriptresults", "comment", "tool", "toolversion",
		"authenticatedfinding" })
@XmlRootElement(name = "FINDING")
public class FINDING {
	@XmlElement(name = "FINDING_ID", required = 1)
	protected FINDINGID findingid;
	@XmlElement(name = "FINDING_STATUS", required = 1)
	protected String findingstatus;
	@XmlElement(name = "FINDING_DETAILS", required = 1)
	protected FINDINGDETAILS findingdetails;
	@XmlElement(name = "SEV_OVERRIDE_CODE", required = 1)
	protected String sevoverridecode;
	@XmlElement(name = "SEV_OVERRIDE_TEXT", required = 1)
	protected String sevoverridetext;
	@XmlElement(name = "SCRIPT_RESULTS", required = 1)
	protected String scriptresults;
	@XmlElement(name = "COMMENT", required = 1)
	protected String comment;
	@XmlElement(name = "TOOL", required = 1)
	protected String tool;
	@XmlElement(name = "TOOL_VERSION", required = 1)
	protected String toolversion;
	@XmlElement(name = "AUTHENTICATED_FINDING")
	protected boolean authenticatedfinding;

	public FINDINGID getFINDINGID() {
		return this.findingid;
	}

	public void setFINDINGID(FINDINGID value) {
		this.findingid = value;
	}

	public String getFINDINGSTATUS() {
		return this.findingstatus;
	}

	public void setFINDINGSTATUS(String value) {
		this.findingstatus = value;
	}

	public FINDINGDETAILS getFINDINGDETAILS() {
		return this.findingdetails;
	}

	public void setFINDINGDETAILS(FINDINGDETAILS value) {
		this.findingdetails = value;
	}

	public String getSEVOVERRIDECODE() {
		return this.sevoverridecode;
	}

	public void setSEVOVERRIDECODE(String value) {
		this.sevoverridecode = value;
	}

	public String getSEVOVERRIDETEXT() {
		return this.sevoverridetext;
	}

	public void setSEVOVERRIDETEXT(String value) {
		this.sevoverridetext = value;
	}

	public String getSCRIPTRESULTS() {
		return this.scriptresults;
	}

	public void setSCRIPTRESULTS(String value) {
		this.scriptresults = value;
	}

	public String getCOMMENT() {
		return this.comment;
	}

	public void setCOMMENT(String value) {
		this.comment = value;
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

	public boolean isAUTHENTICATEDFINDING() {
		return this.authenticatedfinding;
	}

	public void setAUTHENTICATEDFINDING(boolean value) {
		this.authenticatedfinding = value;
	}
}
