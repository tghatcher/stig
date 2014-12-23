/*
 * Decompiled with CFR 0_92.
 */
package CL_Save;

import CL_Save.ASSET;
import CL_Save.STIGINFO;
import CL_Save.VULN;
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
@XmlType(name = "", propOrder = { "svversion", "asset", "stiginfo", "vuln" })
@XmlRootElement(name = "CHECKLIST")
public class CHECKLIST {
	@XmlElement(name = "SV_VERSION", required = 1)
	protected String svversion;
	@XmlElement(name = "ASSET", required = 1)
	protected ASSET asset;
	@XmlElement(name = "STIG_INFO", required = 1)
	protected STIGINFO stiginfo;
	@XmlElement(name = "VULN", required = 1)
	protected List<VULN> vuln;

	public String getSVVERSION() {
		return this.svversion;
	}

	public void setSVVERSION(String value) {
		this.svversion = value;
	}

	public ASSET getASSET() {
		return this.asset;
	}

	public void setASSET(ASSET value) {
		this.asset = value;
	}

	public STIGINFO getSTIGINFO() {
		return this.stiginfo;
	}

	public void setSTIGINFO(STIGINFO value) {
		this.stiginfo = value;
	}

	public List<VULN> getVULN() {
		if (this.vuln == null) {
			this.vuln = new ArrayList<VULN>();
		}
		return this.vuln;
	}
}
