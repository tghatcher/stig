/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "system-extended-stringType", propOrder = { "value" })
public class SystemExtendedStringType {
	@XmlValue
	protected String value;
	@XmlAttribute(name = "system")
	protected String system;

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getSystem() {
		return this.system;
	}

	public void setSystem(String value) {
		this.system = value;
	}
}
