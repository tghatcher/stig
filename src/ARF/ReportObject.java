/*
 * Decompiled with CFR 0_92.
 */
package ARF;

import ARF.Device;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "device" })
@XmlRootElement(name = "reportObject", namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/assessment_report/0.41")
public class ReportObject {
	@XmlElement(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/assessment_report/0.41", required = 1)
	protected Device device;

	public Device getDevice() {
		return this.device;
	}

	public void setDevice(Device value) {
		this.device = value;
	}
}
