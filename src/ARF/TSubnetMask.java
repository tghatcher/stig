/*
 * Decompiled with CFR 0_92.
 */
package ARF;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "T_subnet_mask", propOrder = { "iPv4" })
public class TSubnetMask {
	@XmlElement(name = "IPv4", namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/cnd-core/0.41", required = 1)
	protected String iPv4;

	public String getIPv4() {
		return this.iPv4;
	}

	public void setIPv4(String value) {
		this.iPv4 = value;
	}
}
