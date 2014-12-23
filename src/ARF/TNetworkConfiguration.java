/*
 * Decompiled with CFR 0_92.
 */
package ARF;

import ARF.THostNetworkData;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "T_network_configuration", propOrder = { "networkInterfaceID",
		"hostNetworkData" })
public class TNetworkConfiguration {
	@XmlElement(name = "network_interface_ID", required = 1)
	protected String networkInterfaceID;
	@XmlElement(name = "host_network_data", required = 1)
	protected THostNetworkData hostNetworkData;

	public String getNetworkInterfaceID() {
		return this.networkInterfaceID;
	}

	public void setNetworkInterfaceID(String value) {
		this.networkInterfaceID = value;
	}

	public THostNetworkData getHostNetworkData() {
		return this.hostNetworkData;
	}

	public void setHostNetworkData(THostNetworkData value) {
		this.hostNetworkData = value;
	}
}
