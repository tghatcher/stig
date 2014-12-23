/*
 * Decompiled with CFR 0_92.
 */
package ARF;

import ARF.TConnectionIp;
import ARF.TSubnetMask;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "T_host_network_data", propOrder = { "connectionMacAddress",
		"connectionIp", "subnetMask" })
public class THostNetworkData {
	@XmlElement(name = "connection_mac_address", required = 1)
	protected String connectionMacAddress;
	@XmlElement(name = "connection_ip", required = 1)
	protected TConnectionIp connectionIp;
	@XmlElement(name = "subnet_mask", required = 1)
	protected TSubnetMask subnetMask;

	public String getConnectionMacAddress() {
		return this.connectionMacAddress;
	}

	public void setConnectionMacAddress(String value) {
		this.connectionMacAddress = value;
	}

	public TConnectionIp getConnectionIp() {
		return this.connectionIp;
	}

	public void setConnectionIp(TConnectionIp value) {
		this.connectionIp = value;
	}

	public TSubnetMask getSubnetMask() {
		return this.subnetMask;
	}

	public void setSubnetMask(TSubnetMask value) {
		this.subnetMask = value;
	}
}
