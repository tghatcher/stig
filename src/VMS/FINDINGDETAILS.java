/*
 * Decompiled with CFR 0_92.
 */
package VMS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "value" })
@XmlRootElement(name = "FINDING_DETAILS")
public class FINDINGDETAILS {
	@XmlValue
	protected String value;
	@XmlAttribute(name = "OVERRIDE", required = 1)
	protected String override;

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOVERRIDE() {
		return this.override;
	}

	public void setOVERRIDE(String value) {
		this.override = value;
	}
}
