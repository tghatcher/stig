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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.mitre.ocil._1.QuestionResultType;
import org.mitre.ocil._1.TextType;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "title", "target", "targetAddress",
		"questionnaireResult", "questionResult", "testActionResult" })
@XmlRootElement(name = "results")
public class Results {
	protected TextType title;
	protected List<String> target;
	@XmlElement(name = "target_address")
	protected List<String> targetAddress;
	@XmlElement(name = "questionnaire_result")
	protected List<QuestionnaireResult> questionnaireResult;
	@XmlElementRef(name = "question_result", namespace = "http://www.mitre.org/ocil/1.1", type = JAXBElement.class, required = 0)
	protected List<JAXBElement<QuestionResultType>> questionResult;
	@XmlElement(name = "test_action_result")
	protected List<TestActionResult> testActionResult;
	@XmlAttribute(name = "start_time")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar startTime;
	@XmlAttribute(name = "end_time")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar endTime;

	public TextType getTitle() {
		return this.title;
	}

	public void setTitle(TextType value) {
		this.title = value;
	}

	public List<String> getTarget() {
		if (this.target == null) {
			this.target = new ArrayList<String>();
		}
		return this.target;
	}

	public List<String> getTargetAddress() {
		if (this.targetAddress == null) {
			this.targetAddress = new ArrayList<String>();
		}
		return this.targetAddress;
	}

	public List<QuestionnaireResult> getQuestionnaireResult() {
		if (this.questionnaireResult == null) {
			this.questionnaireResult = new ArrayList<QuestionnaireResult>();
		}
		return this.questionnaireResult;
	}

	public List<JAXBElement<QuestionResultType>> getQuestionResult() {
		if (this.questionResult == null) {
			this.questionResult = new ArrayList<JAXBElement<QuestionResultType>>();
		}
		return this.questionResult;
	}

	public List<TestActionResult> getTestActionResult() {
		if (this.testActionResult == null) {
			this.testActionResult = new ArrayList<TestActionResult>();
		}
		return this.testActionResult;
	}

	public XMLGregorianCalendar getStartTime() {
		return this.startTime;
	}

	public void setStartTime(XMLGregorianCalendar value) {
		this.startTime = value;
	}

	public XMLGregorianCalendar getEndTime() {
		return this.endTime;
	}

	public void setEndTime(XMLGregorianCalendar value) {
		this.endTime = value;
	}

	@XmlAccessorType(value = XmlAccessType.FIELD)
	@XmlType(name = "")
	public static class TestActionResult {
		@XmlAttribute(name = "test_action_ref", required = 1)
		protected String testActionRef;
		@XmlAttribute(name = "result", required = 1)
		protected String result;

		public String getTestActionRef() {
			return this.testActionRef;
		}

		public void setTestActionRef(String value) {
			this.testActionRef = value;
		}

		public String getResult() {
			return this.result;
		}

		public void setResult(String value) {
			this.result = value;
		}
	}

	@XmlAccessorType(value = XmlAccessType.FIELD)
	@XmlType(name = "")
	public static class QuestionnaireResult {
		@XmlAttribute(name = "questionnaire_ref", required = 1)
		protected String questionnaireRef;
		@XmlAttribute(name = "result", required = 1)
		protected String result;

		public String getQuestionnaireRef() {
			return this.questionnaireRef;
		}

		public void setQuestionnaireRef(String value) {
			this.questionnaireRef = value;
		}

		public String getResult() {
			return this.result;
		}

		public void setResult(String value) {
			this.result = value;
		}
	}

}
