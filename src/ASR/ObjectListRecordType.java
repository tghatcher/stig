/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import ASR.ObjectRecordType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "ObjectListRecordType")
public class ObjectListRecordType extends ObjectRecordType {
	@XmlAttribute(name = "opsAttrID")
	protected String opsAttrID;
	@XmlAttribute(name = "opsAttrResource")
	@XmlSchemaType(name = "anyURI")
	protected String opsAttrResource;

	public String getOpsAttrID() {
		return this.opsAttrID;
	}

	public void setOpsAttrID(String value) {
		this.opsAttrID = value;
	}

	public String getOpsAttrResource() {
		return this.opsAttrResource;
	}

	public void setOpsAttrResource(String value) {
		this.opsAttrResource = value;
	}
}
