/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.mitre.ocil._1.BooleanQuestionTestAction;
import org.mitre.ocil._1.ChoiceQuestionTestAction;
import org.mitre.ocil._1.ItemBaseType;
import org.mitre.ocil._1.NumericQuestionTestAction;
import org.mitre.ocil._1.ResultChoiceType;
import org.mitre.ocil._1.StringQuestionTestAction;
import org.mitre.ocil._1.TextType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "QuestionTestActionType", propOrder = { "title", "whenUnknown",
		"whenNotTested", "whenNotApplicable", "whenError" })
@XmlSeeAlso(value = { StringQuestionTestAction.class,
		BooleanQuestionTestAction.class, NumericQuestionTestAction.class,
		ChoiceQuestionTestAction.class })
public class QuestionTestActionType extends ItemBaseType {
	protected TextType title;
	@XmlElement(name = "when_unknown")
	protected ResultChoiceType whenUnknown;
	@XmlElement(name = "when_not_tested")
	protected ResultChoiceType whenNotTested;
	@XmlElement(name = "when_not_applicable")
	protected ResultChoiceType whenNotApplicable;
	@XmlElement(name = "when_error")
	protected ResultChoiceType whenError;
	@XmlAttribute(name = "question_ref", required = 1)
	protected String questionRef;
	@XmlAttribute(name = "id", required = 1)
	protected String id;

	public TextType getTitle() {
		return this.title;
	}

	public void setTitle(TextType value) {
		this.title = value;
	}

	public ResultChoiceType getWhenUnknown() {
		return this.whenUnknown;
	}

	public void setWhenUnknown(ResultChoiceType value) {
		this.whenUnknown = value;
	}

	public ResultChoiceType getWhenNotTested() {
		return this.whenNotTested;
	}

	public void setWhenNotTested(ResultChoiceType value) {
		this.whenNotTested = value;
	}

	public ResultChoiceType getWhenNotApplicable() {
		return this.whenNotApplicable;
	}

	public void setWhenNotApplicable(ResultChoiceType value) {
		this.whenNotApplicable = value;
	}

	public ResultChoiceType getWhenError() {
		return this.whenError;
	}

	public void setWhenError(ResultChoiceType value) {
		this.whenError = value;
	}

	public String getQuestionRef() {
		return this.questionRef;
	}

	public void setQuestionRef(String value) {
		this.questionRef = value;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String value) {
		this.id = value;
	}
}
