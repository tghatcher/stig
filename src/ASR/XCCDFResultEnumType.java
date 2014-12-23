/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "XCCDFResultEnumType")
@XmlEnum
public enum XCCDFResultEnumType {
	PASS("pass"), FAIL("fail"), ERROR("error"), UNKNOWN("unknown"), NOTAPPLICABLE(
			"notapplicable"), NOTCHECKED("notchecked"), NOTSELECTED(
			"notselected"), INFORMATIONAL("informational"), FIXED("fixed"), FAIL_POAM_PROVIDED(
			"fail - POAM provided"), NOT_REPORTED("not_reported");

	private final String value;

	private XCCDFResultEnumType(String v) {
		this.value = v;
	}

	public String value() {
		return this.value;
	}

	public static XCCDFResultEnumType fromValue(String v) {
		for (XCCDFResultEnumType c : XCCDFResultEnumType.values()) {
			if (!c.value.equals(v))
				continue;
			return c;
		}
		throw new IllegalArgumentException(v);
	}
}
