/*
 * Decompiled with CFR 0_92.
 */
package VMSASR;

import VMSASR.PrResource;
import VMSASR.ScanData;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "resource", "scanData" })
@XmlRootElement(name = "PopulationCharacteristics")
public class PopulationCharacteristics {
	@XmlElementRef(name = "resource", namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41", type = PrResource.class)
	protected PrResource resource;
	@XmlElement(required = 1)
	protected ScanData scanData;
	@XmlAttribute(name = "populationSize", required = 1)
	protected byte populationSize;

	public PrResource getResource() {
		return this.resource;
	}

	public void setResource(PrResource value) {
		this.resource = value;
	}

	public ScanData getScanData() {
		return this.scanData;
	}

	public void setScanData(ScanData value) {
		this.scanData = value;
	}

	public byte getPopulationSize() {
		return this.populationSize;
	}

	public void setPopulationSize(byte value) {
		this.populationSize = value;
	}
}
