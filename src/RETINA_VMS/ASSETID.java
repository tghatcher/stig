/*
 * Decompiled with CFR 0_92.
 */
package RETINA_VMS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "value" })
@XmlRootElement(name = "ASSET_ID")
public class ASSETID {
	@XmlValue
	protected String value;
	@XmlAttribute(name = "TYPE", required = 1)
	protected String type;

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTYPE() {
		return this.type;
	}

	public void setTYPE(String value) {
		this.type = value;
	}
}
