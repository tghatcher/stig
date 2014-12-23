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
@XmlType(name = "string_question_result", propOrder = { "answer" })
public class StringQuestionResult extends QuestionResultType {
	@XmlElement(required = 1, nillable = 1)
	protected String answer;

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String value) {
		this.answer = value;
	}
}
