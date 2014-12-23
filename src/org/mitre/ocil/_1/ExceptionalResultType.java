/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ExceptionalResultType")
@XmlEnum
public enum ExceptionalResultType {
	UNKNOWN, ERROR, NOT_TESTED, NOT_APPLICABLE;

	private ExceptionalResultType() {
	}

	public String value() {
		return this.name();
	}

	public static ExceptionalResultType fromValue(String v) {
		return ExceptionalResultType.valueOf(v);
	}
}
