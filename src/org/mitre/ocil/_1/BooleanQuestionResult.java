/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.mitre.ocil._1.QuestionResultType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "boolean_question_result", propOrder = { "answer" })
public class BooleanQuestionResult extends QuestionResultType {
	@XmlElement(required = 1, type = Boolean.class, nillable = 1)
	protected Boolean answer;

	public Boolean isAnswer() {
		return this.answer;
	}

	public void setAnswer(Boolean value) {
		this.answer = value;
	}
}
