/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.mitre.ocil._1.BooleanQuestionResult;
import org.mitre.ocil._1.ChoiceQuestionResult;
import org.mitre.ocil._1.NumericQuestionResult;
import org.mitre.ocil._1.StringQuestionResult;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "QuestionResultType")
@XmlSeeAlso(value = { StringQuestionResult.class, BooleanQuestionResult.class,
		ChoiceQuestionResult.class, NumericQuestionResult.class })
public class QuestionResultType {
	@XmlAttribute(name = "question_ref", required = 1)
	protected String questionRef;
	@XmlAttribute(name = "response")
	protected String response;

	public String getQuestionRef() {
		return this.questionRef;
	}

	public void setQuestionRef(String value) {
		this.questionRef = value;
	}

	public String getResponse() {
		if (this.response == null) {
			return "ANSWERED";
		}
		return this.response;
	}

	public void setResponse(String value) {
		this.response = value;
	}
}
