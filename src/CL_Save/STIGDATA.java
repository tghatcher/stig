/*
 * Decompiled with CFR 0_92.
 */
package CL_Save;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "vulnattribute", "attributedata" })
@XmlRootElement(name = "STIG_DATA")
public class STIGDATA {
	@XmlElement(name = "VULN_ATTRIBUTE", required = 1)
	protected String vulnattribute;
	@XmlElement(name = "ATTRIBUTE_DATA", required = 1)
	protected String attributedata;

	public String getVULNATTRIBUTE() {
		return this.vulnattribute;
	}

	public void setVULNATTRIBUTE(String value) {
		this.vulnattribute = value;
	}

	public String getATTRIBUTEDATA() {
		return this.attributedata;
	}

	public void setATTRIBUTEDATA(String value) {
		this.attributedata = value;
	}
}
