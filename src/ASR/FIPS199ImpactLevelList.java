/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "FIPS199ImpactLevelList", namespace = "http://scap.nist.gov/schema/cia_enums/0.1")
@XmlEnum
public enum FIPS199ImpactLevelList {
	LOW("low"), MODERATE("moderate"), HIGH("high");

	private final String value;

	private FIPS199ImpactLevelList(String v) {
		this.value = v;
	}

	public String value() {
		return this.value;
	}

	public static FIPS199ImpactLevelList fromValue(String v) {
		for (FIPS199ImpactLevelList c : FIPS199ImpactLevelList.values()) {
			if (!c.value.equals(v))
				continue;
			return c;
		}
		throw new IllegalArgumentException(v);
	}
}
