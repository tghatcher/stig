/*
 * Decompiled with CFR 0_92.
 */
package RETINA_VMS;

import RETINA_VMS.ASSETID;
import RETINA_VMS.ELEMENT;
import RETINA_VMS.TARGET;
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
@XmlType(name = "", propOrder = { "assettool", "assettoolversion", "assetid",
		"element", "target" })
@XmlRootElement(name = "ASSET")
public class ASSET {
	@XmlElement(name = "ASSET_TOOL", required = 1)
	protected String assettool;
	@XmlElement(name = "ASSET_TOOL_VERSION", required = 1)
	protected String assettoolversion;
	@XmlElement(name = "ASSET_ID", required = 1)
	protected List<ASSETID> assetid;
	@XmlElement(name = "ELEMENT", required = 1)
	protected ELEMENT element;
	@XmlElement(name = "TARGET", required = 1)
	protected TARGET target;

	public String getASSETTOOL() {
		return this.assettool;
	}

	public void setASSETTOOL(String value) {
		this.assettool = value;
	}

	public String getASSETTOOLVERSION() {
		return this.assettoolversion;
	}

	public void setASSETTOOLVERSION(String value) {
		this.assettoolversion = value;
	}

	public List<ASSETID> getASSETID() {
		if (this.assetid == null) {
			this.assetid = new ArrayList<ASSETID>();
		}
		return this.assetid;
	}

	public ELEMENT getELEMENT() {
		return this.element;
	}

	public void setELEMENT(ELEMENT value) {
		this.element = value;
	}

	public TARGET getTARGET() {
		return this.target;
	}

	public void setTARGET(TARGET value) {
		this.target = value;
	}
}
