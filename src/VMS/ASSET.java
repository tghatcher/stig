/*
 * Decompiled with CFR 0_92.
 */
package VMS;

import VMS.ASSETID;
import VMS.ASSETTYPE;
import VMS.ELEMENT;
import VMS.TARGET;
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
@XmlType(name = "", propOrder = { "assetid", "assettype", "workstation",
		"element", "target" })
@XmlRootElement(name = "ASSET")
public class ASSET {
	@XmlElement(name = "ASSET_ID", required = 1)
	protected List<ASSETID> assetid;
	@XmlElement(name = "ASSET_TYPE", required = 1)
	protected ASSETTYPE assettype;
	@XmlElement(name = "WORKSTATION", required = 1)
	protected String workstation;
	@XmlElement(name = "ELEMENT", required = 1)
	protected List<ELEMENT> element;
	@XmlElement(name = "TARGET", required = 1)
	protected List<TARGET> target;

	public List<ASSETID> getASSETID() {
		if (this.assetid == null) {
			this.assetid = new ArrayList<ASSETID>();
		}
		return this.assetid;
	}

	public ASSETTYPE getASSETTYPE() {
		return this.assettype;
	}

	public void setASSETTYPE(ASSETTYPE value) {
		this.assettype = value;
	}

	public String getWORKSTATION() {
		return this.workstation;
	}

	public void setWORKSTATION(String value) {
		this.workstation = value;
	}

	public List<ELEMENT> getELEMENT() {
		if (this.element == null) {
			this.element = new ArrayList<ELEMENT>();
		}
		return this.element;
	}

	public List<TARGET> getTARGET() {
		if (this.target == null) {
			this.target = new ArrayList<TARGET>();
		}
		return this.target;
	}
}
