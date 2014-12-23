/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import ASR.ObjectListRecordType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "ObjectRecordType")
@XmlSeeAlso(value = { ObjectListRecordType.class })
public class ObjectRecordType {
	@XmlAttribute(name = "resource")
	@XmlSchemaType(name = "anyURI")
	protected String resource;
	@XmlAttribute(name = "record_identifier")
	protected String recordIdentifier;
	@XmlAttribute(name = "name")
	protected String name;

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

	public String getName() {
		return this.name;
	}

	public void setName(String value) {
		this.name = value;
	}
}
