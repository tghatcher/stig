/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ConfidentialityLevelList", namespace = "http://scap.nist.gov/schema/cia_enums/0.1")
@XmlEnum
public enum ConfidentialityLevelList {
	PUBLIC("Public"), SENSITIVE("Sensitive"), CLASSIFIED("Classified");

	private final String value;

	private ConfidentialityLevelList(String v) {
		this.value = v;
	}

	public String value() {
		return this.value;
	}

	public static ConfidentialityLevelList fromValue(String v) {
		for (ConfidentialityLevelList c : ConfidentialityLevelList.values()) {
			if (!c.value.equals(v))
				continue;
			return c;
		}
		throw new IllegalArgumentException(v);
	}
}
