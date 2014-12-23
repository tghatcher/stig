/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import org.mitre.ocil._1.BooleanQuestionModelType;
import org.mitre.ocil._1.QuestionType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "")
public class BooleanQuestion extends QuestionType {
	@XmlAttribute(name = "default_answer")
	protected Boolean defaultAnswer;
	@XmlAttribute(name = "model")
	protected BooleanQuestionModelType model;

	public Boolean isDefaultAnswer() {
		return this.defaultAnswer;
	}

	public void setDefaultAnswer(Boolean value) {
		this.defaultAnswer = value;
	}

	public BooleanQuestionModelType getModel() {
		if (this.model == null) {
			return BooleanQuestionModelType.MODEL_YES_NO;
		}
		return this.model;
	}

	public void setModel(BooleanQuestionModelType value) {
		this.model = value;
	}
}
