/*
 * Decompiled with CFR 0_92.
 */
package VMSASR;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "deviceRecord")
public class DeviceRecord {
	@XmlAttribute(name = "record_identifier", required = 1)
	protected String recordIdentifier;

	public String getRecordIdentifier() {
		return this.recordIdentifier;
	}

	public void setRecordIdentifier(String value) {
		this.recordIdentifier = value;
	}
}
