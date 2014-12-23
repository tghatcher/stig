/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "IPAddressType", namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/cnd-core/0.41", propOrder = {
		"iPv4", "iPv6" })
public class IPAddressType {
	@XmlElement(name = "IPv4")
	protected String iPv4;
	@XmlElement(name = "IPv6")
	protected String iPv6;

	public String getIPv4() {
		return this.iPv4;
	}

	public void setIPv4(String value) {
		this.iPv4 = value;
	}

	public String getIPv6() {
		return this.iPv6;
	}

	public void setIPv6(String value) {
		this.iPv6 = value;
	}
}
