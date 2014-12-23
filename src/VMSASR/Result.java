/*
 * Decompiled with CFR 0_92.
 */
package VMSASR;

import VMSASR.DeviceRecord;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "deviceRecord" })
@XmlRootElement(name = "result")
public class Result {
	@XmlElement(required = 1)
	protected List<DeviceRecord> deviceRecord;
	@XmlAttribute(name = "count", required = 1)
	protected String count;

	public List<DeviceRecord> getDeviceRecord() {
		if (this.deviceRecord == null) {
			this.deviceRecord = new ArrayList<DeviceRecord>();
		}
		return this.deviceRecord;
	}

	public String getCount() {
		return this.count;
	}

	public void setCount(String value) {
		this.count = value;
	}
}
