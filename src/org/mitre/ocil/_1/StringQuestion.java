/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import org.mitre.ocil._1.QuestionType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "")
public class StringQuestion extends QuestionType {
	@XmlAttribute(name = "default_answer")
	protected String defaultAnswer;

	public String getDefaultAnswer() {
		return this.defaultAnswer;
	}

	public void setDefaultAnswer(String value) {
		this.defaultAnswer = value;
	}
}
