/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.mitre.ocil._1.QuestionResultType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "choice_question_result", propOrder = { "answer" })
public class ChoiceQuestionResult extends QuestionResultType {
	@XmlElement(required = 1, nillable = 1)
	protected Answer answer;

	public Answer getAnswer() {
		return this.answer;
	}

	public void setAnswer(Answer value) {
		this.answer = value;
	}

	@XmlAccessorType(value = XmlAccessType.FIELD)
	@XmlType(name = "")
	public static class Answer {
		@XmlAttribute(name = "choice_ref")
		protected String choiceRef;

		public String getChoiceRef() {
			return this.choiceRef;
		}

		public void setChoiceRef(String value) {
			this.choiceRef = value;
		}
	}

}
