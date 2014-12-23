/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ScopeType")
@XmlEnum
public enum ScopeType {
	FULL, SHORT;

	private ScopeType() {
	}

	public String value() {
		return this.name();
	}

	public static ScopeType fromValue(String v) {
		return ScopeType.valueOf(v);
	}
}
