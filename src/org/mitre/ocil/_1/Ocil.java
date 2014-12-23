/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.mitre.ocil._1.ChoiceGroup;
import org.mitre.ocil._1.DocumentType;
import org.mitre.ocil._1.GeneratorType;
import org.mitre.ocil._1.ItemBaseType;
import org.mitre.ocil._1.QuestionType;
import org.mitre.ocil._1.Questionnaire;
import org.mitre.ocil._1.Results;
import org.mitre.ocil._1.ScopeType;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "generator", "document", "questionnaire",
		"testAction", "question", "choiceGroup", "results" })
@XmlRootElement(name = "ocil")
public class Ocil {
	@XmlElement(required = 1)
	protected GeneratorType generator;
	protected DocumentType document;
	@XmlElement(required = 1)
	protected List<Questionnaire> questionnaire;
	@XmlElementRef(name = "test_action", namespace = "http://www.mitre.org/ocil/1.1", type = JAXBElement.class)
	protected List<JAXBElement<? extends ItemBaseType>> testAction;
	@XmlElementRef(name = "question", namespace = "http://www.mitre.org/ocil/1.1", type = JAXBElement.class)
	protected List<JAXBElement<? extends QuestionType>> question;
	@XmlElement(name = "choice_group")
	protected List<ChoiceGroup> choiceGroup;
	protected Results results;
	@XmlAttribute(name = "scope")
	protected ScopeType scope;

	public GeneratorType getGenerator() {
		return this.generator;
	}

	public void setGenerator(GeneratorType value) {
		this.generator = value;
	}

	public DocumentType getDocument() {
		return this.document;
	}

	public void setDocument(DocumentType value) {
		this.document = value;
	}

	public List<Questionnaire> getQuestionnaire() {
		if (this.questionnaire == null) {
			this.questionnaire = new ArrayList<Questionnaire>();
		}
		return this.questionnaire;
	}

	public List<JAXBElement<? extends ItemBaseType>> getTestAction() {
		if (this.testAction == null) {
			this.testAction = new ArrayList<JAXBElement<? extends ItemBaseType>>();
		}
		return this.testAction;
	}

	public List<JAXBElement<? extends QuestionType>> getQuestion() {
		if (this.question == null) {
			this.question = new ArrayList<JAXBElement<? extends QuestionType>>();
		}
		return this.question;
	}

	public List<ChoiceGroup> getChoiceGroup() {
		if (this.choiceGroup == null) {
			this.choiceGroup = new ArrayList<ChoiceGroup>();
		}
		return this.choiceGroup;
	}

	public Results getResults() {
		return this.results;
	}

	public void setResults(Results value) {
		this.results = value;
	}

	public ScopeType getScope() {
		if (this.scope == null) {
			return ScopeType.FULL;
		}
		return this.scope;
	}

	public void setScope(ScopeType value) {
		this.scope = value;
	}
}
