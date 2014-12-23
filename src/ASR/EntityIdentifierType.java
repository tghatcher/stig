/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import ASR.ScanDataIDType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "EntityIdentifierType", namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/cnd-core/0.41", propOrder = {
		"resource", "recordIdentifier" })
@XmlSeeAlso(value = { ScanDataIDType.class })
public class EntityIdentifierType {
	@XmlElement(required = 1)
	@XmlSchemaType(name = "anyURI")
	protected String resource;
	@XmlElement(name = "record_identifier", required = 1)
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
