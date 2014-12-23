/*
 * Decompiled with CFR 0_92.
 */
package CL_Save;

import CL_Save.STIGDATA;
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
@XmlType(name = "", propOrder = { "stigdata", "status", "findingdetails",
		"comments", "severityoverride", "severityjustification", "vulndata" })
@XmlRootElement(name = "VULN")
public class VULN {
	@XmlElement(name = "STIG_DATA", required = 1)
	protected List<STIGDATA> stigdata;
	@XmlElement(name = "STATUS", required = 1)
	protected String status;
	@XmlElement(name = "FINDING_DETAILS", required = 1)
	protected String findingdetails;
	@XmlElement(name = "COMMENTS", required = 1)
	protected String comments;
	@XmlElement(name = "SEVERITY_OVERRIDE", required = 1)
	protected String severityoverride;
	@XmlElement(name = "SEVERITY_JUSTIFICATION", required = 1)
	protected String severityjustification;
	@XmlElement(name = "VULN_DATA", required = 1)
	protected List<VULNDATA> vulndata;

	public List<STIGDATA> getSTIGDATA() {
		if (this.stigdata == null) {
			this.stigdata = new ArrayList<STIGDATA>();
		}
		return this.stigdata;
	}

	public String getSTATUS() {
		return this.status;
	}

	public void setSTATUS(String value) {
		this.status = value;
	}

	public String getFINDINGDETAILS() {
		return this.findingdetails;
	}

	public void setFINDINGDETAILS(String value) {
		this.findingdetails = value;
	}

	public String getCOMMENTS() {
		return this.comments;
	}

	public void setCOMMENTS(String value) {
		this.comments = value;
	}

	public String getSEVERITYOVERRIDE() {
		return this.severityoverride;
	}

	public void setSEVERITYOVERRIDE(String value) {
		this.severityoverride = value;
	}

	public String getSEVERITYJUSTIFICATION() {
		return this.severityjustification;
	}

	public void setSEVERITYJUSTIFICATION(String value) {
		this.severityjustification = value;
	}

	public List<VULNDATA> getVULNDATA() {
		if (this.vulndata == null) {
			this.vulndata = new ArrayList<VULNDATA>();
		}
		return this.vulndata;
	}

	@XmlAccessorType(value = XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "vdname", "vddata" })
	public static class VULNDATA {
		@XmlElement(name = "VD_NAME", required = 1)
		protected String vdname;
		@XmlElement(name = "VD_DATA", required = 1)
		protected String vddata;

		public String getVDNAME() {
			return this.vdname;
		}

		public void setVDNAME(String value) {
			this.vdname = value;
		}

		public String getVDDATA() {
			return this.vddata;
		}

		public void setVDDATA(String value) {
			this.vddata = value;
		}
	}

}
