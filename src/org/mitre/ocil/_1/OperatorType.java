/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "OperatorType")
@XmlEnum
public enum OperatorType {
	AND, OR;

	private OperatorType() {
	}

	public String value() {
		return this.name();
	}

	public static OperatorType fromValue(String v) {
		return OperatorType.valueOf(v);
	}
}
