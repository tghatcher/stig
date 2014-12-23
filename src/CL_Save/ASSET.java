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
@XmlType(name = "", propOrder = { "assettype", "hostname", "hostip", "hostmac",
		"hostguid", "targetkey", "assetval" })
@XmlRootElement(name = "ASSET")
public class ASSET {
	@XmlElement(name = "ASSET_TYPE", required = 1)
	protected String assettype;
	@XmlElement(name = "HOST_NAME", required = 1)
	protected String hostname;
	@XmlElement(name = "HOST_IP", required = 1)
	protected String hostip;
	@XmlElement(name = "HOST_MAC", required = 1)
	protected String hostmac;
	@XmlElement(name = "HOST_GUID", required = 1)
	protected String hostguid;
	@XmlElement(name = "TARGET_KEY", required = 1)
	protected String targetkey;
	@XmlElement(name = "ASSET_VAL", required = 1)
	protected List<ASSETVAL> assetval;

	public String getASSETTYPE() {
		return this.assettype;
	}

	public void setASSETTYPE(String value) {
		this.assettype = value;
	}

	public String getHOSTNAME() {
		return this.hostname;
	}

	public void setHOSTNAME(String value) {
		this.hostname = value;
	}

	public String getHOSTIP() {
		return this.hostip;
	}

	public void setHOSTIP(String value) {
		this.hostip = value;
	}

	public String getHOSTMAC() {
		return this.hostmac;
	}

	public void setHOSTMAC(String value) {
		this.hostmac = value;
	}

	public String getHOSTGUID() {
		return this.hostguid;
	}

	public void setHOSTGUID(String value) {
		this.hostguid = value;
	}

	public String getTARGETKEY() {
		return this.targetkey;
	}

	public void setTARGETKEY(String value) {
		this.targetkey = value;
	}

	public List<ASSETVAL> getASSETVAL() {
		if (this.assetval == null) {
			this.assetval = new ArrayList<ASSETVAL>();
		}
		return this.assetval;
	}

	@XmlAccessorType(value = XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "avname", "avdata" })
	public static class ASSETVAL {
		@XmlElement(name = "AV_NAME", required = 1)
		protected Object avname;
		@XmlElement(name = "AV_DATA", required = 1)
		protected Object avdata;

		public Object getAVNAME() {
			return this.avname;
		}

		public void setAVNAME(Object value) {
			this.avname = value;
		}

		public Object getAVDATA() {
			return this.avdata;
		}

		public void setAVDATA(Object value) {
			this.avdata = value;
		}
	}

}
