/*
 * Decompiled with CFR 0_92.
 */
package RETINA_VMS;

import RETINA_VMS.FINDING;
import RETINA_VMS.PROTOCOL;
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
@XmlType(name = "", propOrder = { "targetkey", "finding", "protocol" })
@XmlRootElement(name = "TARGET")
public class TARGET {
	@XmlElement(name = "TARGET_KEY", required = 1)
	protected String targetkey;
	@XmlElement(name = "FINDING", required = 1)
	protected List<FINDING> finding;
	@XmlElement(name = "PROTOCOL", required = 1)
	protected List<PROTOCOL> protocol;

	public String getTARGETKEY() {
		return this.targetkey;
	}

	public void setTARGETKEY(String value) {
		this.targetkey = value;
	}

	public List<FINDING> getFINDING() {
		if (this.finding == null) {
			this.finding = new ArrayList<FINDING>();
		}
		return this.finding;
	}

	public List<PROTOCOL> getPROTOCOL() {
		if (this.protocol == null) {
			this.protocol = new ArrayList<PROTOCOL>();
		}
		return this.protocol;
	}
}
