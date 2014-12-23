/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ScanLocationList", namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41")
@XmlEnum
public enum ScanLocationList {
	HOST("host"), NETWORK_LOCAL("network_local"), NETWORK_REMOTE(
			"network_remote");

	private final String value;

	private ScanLocationList(String v) {
		this.value = v;
	}

	public String value() {
		return this.value;
	}

	public static ScanLocationList fromValue(String v) {
		for (ScanLocationList c : ScanLocationList.values()) {
			if (!c.value.equals(v))
				continue;
			return c;
		}
		throw new IllegalArgumentException(v);
	}
}
