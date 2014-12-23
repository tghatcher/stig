/*
 * Decompiled with CFR 0_92.
 */
package VMS;

import VMS.FINDING;
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
@XmlType(name = "", propOrder = { "targetkey", "finding" })
@XmlRootElement(name = "TARGET")
public class TARGET {
	@XmlElement(name = "TARGET_KEY", required = 1)
	protected String targetkey;
	@XmlElement(name = "FINDING", required = 1)
	protected List<FINDING> finding;

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
}
