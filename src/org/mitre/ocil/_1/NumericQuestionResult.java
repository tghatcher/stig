/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.mitre.ocil._1.QuestionResultType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "numeric_question_result", propOrder = { "answer" })
public class NumericQuestionResult extends QuestionResultType {
	@XmlElement(required = 1, nillable = 1)
	protected BigDecimal answer;

	public BigDecimal getAnswer() {
		return this.answer;
	}

	public void setAnswer(BigDecimal value) {
		this.answer = value;
	}
}
