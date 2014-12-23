/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import org.mitre.ocil._1.QuestionType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "")
public class NumericQuestion extends QuestionType {
	@XmlAttribute(name = "default_answer")
	protected BigDecimal defaultAnswer;

	public BigDecimal getDefaultAnswer() {
		return this.defaultAnswer;
	}

	public void setDefaultAnswer(BigDecimal value) {
		this.defaultAnswer = value;
	}
}
