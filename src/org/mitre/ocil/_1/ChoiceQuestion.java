/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;
import org.mitre.ocil._1.Choice;
import org.mitre.ocil._1.QuestionType;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "choiceOrChoiceGroupRef" })
public class ChoiceQuestion extends QuestionType {
	@XmlElements(value = {
			@XmlElement(name = "choice_group_ref", type = String.class),
			@XmlElement(name = "choice", type = Choice.class) })
	protected List<Object> choiceOrChoiceGroupRef;
	@XmlAttribute(name = "default_answer_ref")
	protected String defaultAnswerRef;

	public List<Object> getChoiceOrChoiceGroupRef() {
		if (this.choiceOrChoiceGroupRef == null) {
			this.choiceOrChoiceGroupRef = new ArrayList<Object>();
		}
		return this.choiceOrChoiceGroupRef;
	}

	public String getDefaultAnswerRef() {
		return this.defaultAnswerRef;
	}

	public void setDefaultAnswerRef(String value) {
		this.defaultAnswerRef = value;
	}
}
