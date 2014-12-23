/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import ASR.DeviceRecordType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "DeviceListRecordType")
public class DeviceListRecordType extends DeviceRecordType {
	@XmlAttribute(name = "devOS")
	protected String devOS;
	@XmlAttribute(name = "opsAttrID")
	protected String opsAttrID;
	@XmlAttribute(name = "opsAttrResource")
	@XmlSchemaType(name = "anyURI")
	protected String opsAttrResource;
	@XmlAttribute(name = "managed")
	protected Boolean managed;

	public String getDevOS() {
		return this.devOS;
	}

	public void setDevOS(String value) {
		this.devOS = value;
	}

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

	public Boolean isManaged() {
		return this.managed;
	}

	public void setManaged(Boolean value) {
		this.managed = value;
	}
}
