/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.mitre.ocil._1.QuestionTestActionType;
import org.mitre.ocil._1.ResultChoiceType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "whenTrue", "whenFalse" })
public class BooleanQuestionTestAction extends QuestionTestActionType {
	@XmlElement(name = "when_true", required = 1)
	protected ResultChoiceType whenTrue;
	@XmlElement(name = "when_false", required = 1)
	protected ResultChoiceType whenFalse;

	public ResultChoiceType getWhenTrue() {
		return this.whenTrue;
	}

	public void setWhenTrue(ResultChoiceType value) {
		this.whenTrue = value;
	}

	public ResultChoiceType getWhenFalse() {
		return this.whenFalse;
	}

	public void setWhenFalse(ResultChoiceType value) {
		this.whenFalse = value;
	}
}
