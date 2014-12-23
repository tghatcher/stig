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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.mitre.ocil._1.BooleanQuestion;
import org.mitre.ocil._1.ChoiceQuestion;
import org.mitre.ocil._1.Instructions;
import org.mitre.ocil._1.ItemBaseType;
import org.mitre.ocil._1.NumericQuestion;
import org.mitre.ocil._1.StringQuestion;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "QuestionType", propOrder = { "questionText", "instructions" })
@XmlSeeAlso(value = { ChoiceQuestion.class, BooleanQuestion.class,
		StringQuestion.class, NumericQuestion.class })
public class QuestionType extends ItemBaseType {
	@XmlElement(name = "question_text", required = 1)
	protected List<String> questionText;
	protected Instructions instructions;
	@XmlAttribute(name = "id", required = 1)
	protected String id;

	public List<String> getQuestionText() {
		if (this.questionText == null) {
			this.questionText = new ArrayList<String>();
		}
		return this.questionText;
	}

	public Instructions getInstructions() {
		return this.instructions;
	}

	public void setInstructions(Instructions value) {
		this.instructions = value;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String value) {
		this.id = value;
	}
}
