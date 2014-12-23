/*
 * Decompiled with CFR 0_92.
 */
package VMS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "elementkey" })
@XmlRootElement(name = "ELEMENT")
public class ELEMENT {
	@XmlElement(name = "ELEMENT_KEY", required = 1)
	protected String elementkey;

	public String getELEMENTKEY() {
		return this.elementkey;
	}

	public void setELEMENTKEY(String value) {
		this.elementkey = value;
	}
}
