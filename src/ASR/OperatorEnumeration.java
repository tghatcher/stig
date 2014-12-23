/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "operatorEnumeration")
@XmlEnum
public enum OperatorEnumeration {
	AND, OR;

	private OperatorEnumeration() {
	}

	public String value() {
		return this.name();
	}

	public static OperatorEnumeration fromValue(String v) {
		return OperatorEnumeration.valueOf(v);
	}
}
