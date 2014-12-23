/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "OVALResultEnumeration")
@XmlEnum
public enum OVALResultEnumeration {
	TRUE("true"), FALSE("false"), UNKNOWN("unknown"), ERROR("error"), NOT_EVALUATED(
			"not evaluated"), NOT_APPLICABLE("not applicable"), FALSE_POAM_PROVIDED(
			"false - POAM provided"), NOT_REPORTED("not_reported");

	private final String value;

	private OVALResultEnumeration(String v) {
		this.value = v;
	}

	public String value() {
		return this.value;
	}

	public static OVALResultEnumeration fromValue(String v) {
		for (OVALResultEnumeration c : OVALResultEnumeration.values()) {
			if (!c.value.equals(v))
				continue;
			return c;
		}
		throw new IllegalArgumentException(v);
	}
}
