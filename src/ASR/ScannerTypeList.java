/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ScannerTypeList", namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41")
@XmlEnum
public enum ScannerTypeList {
	PASSIVE_SENSOR("Passive Sensor"), ACTIVE_UNAUTHENTICATED_SENSOR(
			"Active Unauthenticated Sensor"), ACTIVE_AUTHENTICATED_SENSOR(
			"Active Authenticated Sensor"), DIRECTORY("Directory"), SENSOR_REPOSITORY(
			"Sensor Repository");

	private final String value;

	private ScannerTypeList(String v) {
		this.value = v;
	}

	public String value() {
		return this.value;
	}

	public static ScannerTypeList fromValue(String v) {
		for (ScannerTypeList c : ScannerTypeList.values()) {
			if (!c.value.equals(v))
				continue;
			return c;
		}
		throw new IllegalArgumentException(v);
	}
}
