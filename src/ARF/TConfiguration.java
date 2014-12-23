/*
 * Decompiled with CFR 0_92.
 */
package ARF;

import ARF.TNetworkConfiguration;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "T_configuration", propOrder = { "networkConfiguration" })
public class TConfiguration {
	@XmlElement(name = "network_configuration", required = 1)
	protected TNetworkConfiguration networkConfiguration;

	public TNetworkConfiguration getNetworkConfiguration() {
		return this.networkConfiguration;
	}

	public void setNetworkConfiguration(TNetworkConfiguration value) {
		this.networkConfiguration = value;
	}
}
