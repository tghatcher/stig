/*
 * Decompiled with CFR 0_92.
 */
package VMSASR;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "resource", "recordIdentifier" })
@XmlRootElement(name = "scanDataID", namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41")
public class ScanDataID {
	@XmlElement(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/cnd-core/0.41", required = 1)
	protected String resource;
	@XmlElement(name = "record_identifier", namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/cnd-core/0.41", required = 1)
	protected String recordIdentifier;

	public String getResource() {
		return this.resource;
	}

	public void setResource(String value) {
		this.resource = value;
	}

	public String getRecordIdentifier() {
		return this.recordIdentifier;
	}

	public void setRecordIdentifier(String value) {
		this.recordIdentifier = value;
	}
}
