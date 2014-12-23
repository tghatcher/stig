/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "BooleanQuestionModelType")
@XmlEnum
public enum BooleanQuestionModelType {
	MODEL_YES_NO, MODEL_TRUE_FALSE;

	private BooleanQuestionModelType() {
	}

	public String value() {
		return this.name();
	}

	public static BooleanQuestionModelType fromValue(String v) {
		return BooleanQuestionModelType.valueOf(v);
	}
}
