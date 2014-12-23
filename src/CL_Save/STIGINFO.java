/*
 * Decompiled with CFR 0_92.
 */
package CL_Save;

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
@XmlType(name = "", propOrder = { "stigtitle", "sidata" })
@XmlRootElement(name = "STIG_INFO")
public class STIGINFO {
	@XmlElement(name = "STIG_TITLE", required = 1)
	protected String stigtitle;
	@XmlElement(name = "SI_DATA", required = 1)
	protected List<SIDATA> sidata;

	public String getSTIGTITLE() {
		return this.stigtitle;
	}

	public void setSTIGTITLE(String value) {
		this.stigtitle = value;
	}

	public List<SIDATA> getSIDATA() {
		if (this.sidata == null) {
			this.sidata = new ArrayList<SIDATA>();
		}
		return this.sidata;
	}

	@XmlAccessorType(value = XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "sidname", "siddata" })
	public static class SIDATA {
		@XmlElement(name = "SID_NAME", required = 1)
		protected String sidname;
		@XmlElement(name = "SID_DATA", required = 1)
		protected String siddata;

		public String getSIDNAME() {
			return this.sidname;
		}

		public void setSIDNAME(String value) {
			this.sidname = value;
		}

		public String getSIDDATA() {
			return this.siddata;
		}

		public void setSIDDATA(String value) {
			this.siddata = value;
		}
	}

}
