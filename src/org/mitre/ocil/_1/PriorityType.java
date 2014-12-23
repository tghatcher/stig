/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "PriorityType")
@XmlEnum
public enum PriorityType {
	HIGH, MEDIUM, LOW;

	private PriorityType() {
	}

	public String value() {
		return this.name();
	}

	public static PriorityType fromValue(String v) {
		return PriorityType.valueOf(v);
	}
}
