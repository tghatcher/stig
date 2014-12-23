/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import ASR.PlatformType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "ComplexIdentType", propOrder = { "cpePlatform", "any" })
public class ComplexIdentType {
	@XmlElement(name = "CPEPlatform")
	protected PlatformType cpePlatform;
	@XmlAnyElement(lax = 1)
	protected Object any;

	public PlatformType getCPEPlatform() {
		return this.cpePlatform;
	}

	public void setCPEPlatform(PlatformType value) {
		this.cpePlatform = value;
	}

	public Object getAny() {
		return this.any;
	}

	public void setAny(Object value) {
		this.any = value;
	}
}
