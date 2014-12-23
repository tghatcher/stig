/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.mitre.ocil._1.BooleanQuestion;
import org.mitre.ocil._1.BooleanQuestionResult;
import org.mitre.ocil._1.BooleanQuestionTestAction;
import org.mitre.ocil._1.Choice;
import org.mitre.ocil._1.ChoiceGroup;
import org.mitre.ocil._1.ChoiceQuestion;
import org.mitre.ocil._1.ChoiceQuestionResult;
import org.mitre.ocil._1.ChoiceQuestionTestAction;
import org.mitre.ocil._1.CompoundTestActionType;
import org.mitre.ocil._1.DocumentType;
import org.mitre.ocil._1.GeneratorType;
import org.mitre.ocil._1.Instructions;
import org.mitre.ocil._1.ItemBaseType;
import org.mitre.ocil._1.NumericQuestion;
import org.mitre.ocil._1.NumericQuestionResult;
import org.mitre.ocil._1.NumericQuestionTestAction;
import org.mitre.ocil._1.Ocil;
import org.mitre.ocil._1.OperationType;
import org.mitre.ocil._1.PatternType;
import org.mitre.ocil._1.QuestionResultType;
import org.mitre.ocil._1.QuestionTestActionType;
import org.mitre.ocil._1.QuestionType;
import org.mitre.ocil._1.Questionnaire;
import org.mitre.ocil._1.RangeType;
import org.mitre.ocil._1.ReferenceType;
import org.mitre.ocil._1.ResultChoiceType;
import org.mitre.ocil._1.Results;
import org.mitre.ocil._1.StepType;
import org.mitre.ocil._1.StringQuestion;
import org.mitre.ocil._1.StringQuestionResult;
import org.mitre.ocil._1.StringQuestionTestAction;
import org.mitre.ocil._1.TestActionRef;
import org.mitre.ocil._1.TextType;
import org.mitre.ocil._1.WhenChoice;
import org.mitre.ocil._1.WhenEquals;
import org.mitre.ocil._1.WhenPattern;
import org.mitre.ocil._1.WhenRange;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlRegistry
public class ObjectFactory {
	private static final QName _BooleanQuestion_QNAME = new QName(
			"http://www.mitre.org/ocil/1.1", "boolean_question");
	private static final QName _ChoiceQuestion_QNAME = new QName(
			"http://www.mitre.org/ocil/1.1", "choice_question");
	private static final QName _BooleanQuestionResult_QNAME = new QName(
			"http://www.mitre.org/ocil/1.1", "boolean_question_result");
	private static final QName _ChoiceQuestionResult_QNAME = new QName(
			"http://www.mitre.org/ocil/1.1", "choice_question_result");
	private static final QName _NumericQuestionResult_QNAME = new QName(
			"http://www.mitre.org/ocil/1.1", "numeric_question_result");
	private static final QName _ChoiceQuestionTestAction_QNAME = new QName(
			"http://www.mitre.org/ocil/1.1", "choice_question_test_action");
	private static final QName _StringQuestionResult_QNAME = new QName(
			"http://www.mitre.org/ocil/1.1", "string_question_result");
	private static final QName _QuestionTestAction_QNAME = new QName(
			"http://www.mitre.org/ocil/1.1", "question_test_action");
	private static final QName _QuestionResult_QNAME = new QName(
			"http://www.mitre.org/ocil/1.1", "question_result");
	private static final QName _Step_QNAME = new QName(
			"http://www.mitre.org/ocil/1.1", "step");
	private static final QName _NumericQuestionTestAction_QNAME = new QName(
			"http://www.mitre.org/ocil/1.1", "numeric_question_test_action");
	private static final QName _Question_QNAME = new QName(
			"http://www.mitre.org/ocil/1.1", "question");
	private static final QName _TestAction_QNAME = new QName(
			"http://www.mitre.org/ocil/1.1", "test_action");
	private static final QName _BooleanQuestionTestAction_QNAME = new QName(
			"http://www.mitre.org/ocil/1.1", "boolean_question_test_action");
	private static final QName _NumericQuestion_QNAME = new QName(
			"http://www.mitre.org/ocil/1.1", "numeric_question");
	private static final QName _StringQuestion_QNAME = new QName(
			"http://www.mitre.org/ocil/1.1", "string_question");
	private static final QName _StringQuestionTestAction_QNAME = new QName(
			"http://www.mitre.org/ocil/1.1", "string_question_test_action");

	public ChoiceQuestionTestAction createChoiceQuestionTestAction() {
		return new ChoiceQuestionTestAction();
	}

	public QuestionType createQuestionType() {
		return new QuestionType();
	}

	public Results createResults() {
		return new Results();
	}

	public NumericQuestionTestAction createNumericQuestionTestAction() {
		return new NumericQuestionTestAction();
	}

	public Questionnaire createQuestionnaire() {
		return new Questionnaire();
	}

	public RangeType.Max createRangeTypeMax() {
		return new RangeType.Max();
	}

	public WhenPattern createWhenPattern() {
		return new WhenPattern();
	}

	public Choice createChoice() {
		return new Choice();
	}

	public Instructions createInstructions() {
		return new Instructions();
	}

	public ItemBaseType createItemBaseType() {
		return new ItemBaseType();
	}

	public NumericQuestionResult createNumericQuestionResult() {
		return new NumericQuestionResult();
	}

	public WhenEquals createWhenEquals() {
		return new WhenEquals();
	}

	public GeneratorType createGeneratorType() {
		return new GeneratorType();
	}

	public OperationType createOperationType() {
		return new OperationType();
	}

	public ChoiceGroup createChoiceGroup() {
		return new ChoiceGroup();
	}

	public RangeType.Min createRangeTypeMin() {
		return new RangeType.Min();
	}

	public Ocil createOcil() {
		return new Ocil();
	}

	public Results.QuestionnaireResult createResultsQuestionnaireResult() {
		return new Results.QuestionnaireResult();
	}

	public BooleanQuestion createBooleanQuestion() {
		return new BooleanQuestion();
	}

	public ChoiceQuestionResult.Answer createChoiceQuestionResultAnswer() {
		return new ChoiceQuestionResult.Answer();
	}

	public StringQuestion createStringQuestion() {
		return new StringQuestion();
	}

	public StringQuestionResult createStringQuestionResult() {
		return new StringQuestionResult();
	}

	public ChoiceQuestionResult createChoiceQuestionResult() {
		return new ChoiceQuestionResult();
	}

	public WhenRange createWhenRange() {
		return new WhenRange();
	}

	public RangeType createRangeType() {
		return new RangeType();
	}

	public Results.TestActionResult createResultsTestActionResult() {
		return new Results.TestActionResult();
	}

	public BooleanQuestionTestAction createBooleanQuestionTestAction() {
		return new BooleanQuestionTestAction();
	}

	public CompoundTestActionType createCompoundTestActionType() {
		return new CompoundTestActionType();
	}

	public NumericQuestion createNumericQuestion() {
		return new NumericQuestion();
	}

	public DocumentType createDocumentType() {
		return new DocumentType();
	}

	public GeneratorType.Author createGeneratorTypeAuthor() {
		return new GeneratorType.Author();
	}

	public ChoiceQuestion createChoiceQuestion() {
		return new ChoiceQuestion();
	}

	public ReferenceType createReferenceType() {
		return new ReferenceType();
	}

	public ResultChoiceType createResultChoiceType() {
		return new ResultChoiceType();
	}

	public StepType createStepType() {
		return new StepType();
	}

	public TextType createTextType() {
		return new TextType();
	}

	public BooleanQuestionResult createBooleanQuestionResult() {
		return new BooleanQuestionResult();
	}

	public StringQuestionTestAction createStringQuestionTestAction() {
		return new StringQuestionTestAction();
	}

	public QuestionResultType createQuestionResultType() {
		return new QuestionResultType();
	}

	public TestActionRef createTestActionRef() {
		return new TestActionRef();
	}

	public QuestionTestActionType createQuestionTestActionType() {
		return new QuestionTestActionType();
	}

	public WhenChoice createWhenChoice() {
		return new WhenChoice();
	}

	public PatternType createPatternType() {
		return new PatternType();
	}

	@XmlElementDecl(namespace = "http://www.mitre.org/ocil/1.1", name = "boolean_question", substitutionHeadNamespace = "http://www.mitre.org/ocil/1.1", substitutionHeadName = "question")
	public JAXBElement<BooleanQuestion> createBooleanQuestion(
			BooleanQuestion value) {
		return new JAXBElement<BooleanQuestion>(_BooleanQuestion_QNAME,
				BooleanQuestion.class, null, value);
	}

	@XmlElementDecl(namespace = "http://www.mitre.org/ocil/1.1", name = "choice_question", substitutionHeadNamespace = "http://www.mitre.org/ocil/1.1", substitutionHeadName = "question")
	public JAXBElement<ChoiceQuestion> createChoiceQuestion(ChoiceQuestion value) {
		return new JAXBElement<ChoiceQuestion>(_ChoiceQuestion_QNAME,
				ChoiceQuestion.class, null, value);
	}

	@XmlElementDecl(namespace = "http://www.mitre.org/ocil/1.1", name = "boolean_question_result", substitutionHeadNamespace = "http://www.mitre.org/ocil/1.1", substitutionHeadName = "question_result")
	public JAXBElement<QuestionResultType> createBooleanQuestionResult(
			QuestionResultType value) {
		return new JAXBElement<QuestionResultType>(
				_BooleanQuestionResult_QNAME, QuestionResultType.class, null,
				value);
	}

	@XmlElementDecl(namespace = "http://www.mitre.org/ocil/1.1", name = "choice_question_result", substitutionHeadNamespace = "http://www.mitre.org/ocil/1.1", substitutionHeadName = "question_result")
	public JAXBElement<QuestionResultType> createChoiceQuestionResult(
			QuestionResultType value) {
		return new JAXBElement<QuestionResultType>(_ChoiceQuestionResult_QNAME,
				QuestionResultType.class, null, value);
	}

	@XmlElementDecl(namespace = "http://www.mitre.org/ocil/1.1", name = "numeric_question_result", substitutionHeadNamespace = "http://www.mitre.org/ocil/1.1", substitutionHeadName = "question_result")
	public JAXBElement<QuestionResultType> createNumericQuestionResult(
			QuestionResultType value) {
		return new JAXBElement<QuestionResultType>(
				_NumericQuestionResult_QNAME, QuestionResultType.class, null,
				value);
	}

	@XmlElementDecl(namespace = "http://www.mitre.org/ocil/1.1", name = "choice_question_test_action", substitutionHeadNamespace = "http://www.mitre.org/ocil/1.1", substitutionHeadName = "question_test_action")
	public JAXBElement<ChoiceQuestionTestAction> createChoiceQuestionTestAction(
			ChoiceQuestionTestAction value) {
		return new JAXBElement<ChoiceQuestionTestAction>(
				_ChoiceQuestionTestAction_QNAME,
				ChoiceQuestionTestAction.class, null, value);
	}

	@XmlElementDecl(namespace = "http://www.mitre.org/ocil/1.1", name = "string_question_result", substitutionHeadNamespace = "http://www.mitre.org/ocil/1.1", substitutionHeadName = "question_result")
	public JAXBElement<QuestionResultType> createStringQuestionResult(
			QuestionResultType value) {
		return new JAXBElement<QuestionResultType>(_StringQuestionResult_QNAME,
				QuestionResultType.class, null, value);
	}

	@XmlElementDecl(namespace = "http://www.mitre.org/ocil/1.1", name = "question_test_action", substitutionHeadNamespace = "http://www.mitre.org/ocil/1.1", substitutionHeadName = "test_action")
	public JAXBElement<QuestionTestActionType> createQuestionTestAction(
			QuestionTestActionType value) {
		return new JAXBElement<QuestionTestActionType>(
				_QuestionTestAction_QNAME, QuestionTestActionType.class, null,
				value);
	}

	@XmlElementDecl(namespace = "http://www.mitre.org/ocil/1.1", name = "question_result")
	public JAXBElement<QuestionResultType> createQuestionResult(
			QuestionResultType value) {
		return new JAXBElement<QuestionResultType>(_QuestionResult_QNAME,
				QuestionResultType.class, null, value);
	}

	@XmlElementDecl(namespace = "http://www.mitre.org/ocil/1.1", name = "step")
	public JAXBElement<StepType> createStep(StepType value) {
		return new JAXBElement<StepType>(_Step_QNAME, StepType.class, null,
				value);
	}

	@XmlElementDecl(namespace = "http://www.mitre.org/ocil/1.1", name = "numeric_question_test_action", substitutionHeadNamespace = "http://www.mitre.org/ocil/1.1", substitutionHeadName = "question_test_action")
	public JAXBElement<NumericQuestionTestAction> createNumericQuestionTestAction(
			NumericQuestionTestAction value) {
		return new JAXBElement<NumericQuestionTestAction>(
				_NumericQuestionTestAction_QNAME,
				NumericQuestionTestAction.class, null, value);
	}

	@XmlElementDecl(namespace = "http://www.mitre.org/ocil/1.1", name = "question")
	public JAXBElement<QuestionType> createQuestion(QuestionType value) {
		return new JAXBElement<QuestionType>(_Question_QNAME,
				QuestionType.class, null, value);
	}

	@XmlElementDecl(namespace = "http://www.mitre.org/ocil/1.1", name = "test_action")
	public JAXBElement<ItemBaseType> createTestAction(ItemBaseType value) {
		return new JAXBElement<ItemBaseType>(_TestAction_QNAME,
				ItemBaseType.class, null, value);
	}

	@XmlElementDecl(namespace = "http://www.mitre.org/ocil/1.1", name = "boolean_question_test_action", substitutionHeadNamespace = "http://www.mitre.org/ocil/1.1", substitutionHeadName = "question_test_action")
	public JAXBElement<BooleanQuestionTestAction> createBooleanQuestionTestAction(
			BooleanQuestionTestAction value) {
		return new JAXBElement<BooleanQuestionTestAction>(
				_BooleanQuestionTestAction_QNAME,
				BooleanQuestionTestAction.class, null, value);
	}

	@XmlElementDecl(namespace = "http://www.mitre.org/ocil/1.1", name = "numeric_question", substitutionHeadNamespace = "http://www.mitre.org/ocil/1.1", substitutionHeadName = "question")
	public JAXBElement<NumericQuestion> createNumericQuestion(
			NumericQuestion value) {
		return new JAXBElement<NumericQuestion>(_NumericQuestion_QNAME,
				NumericQuestion.class, null, value);
	}

	@XmlElementDecl(namespace = "http://www.mitre.org/ocil/1.1", name = "string_question", substitutionHeadNamespace = "http://www.mitre.org/ocil/1.1", substitutionHeadName = "question")
	public JAXBElement<StringQuestion> createStringQuestion(StringQuestion value) {
		return new JAXBElement<StringQuestion>(_StringQuestion_QNAME,
				StringQuestion.class, null, value);
	}

	@XmlElementDecl(namespace = "http://www.mitre.org/ocil/1.1", name = "string_question_test_action", substitutionHeadNamespace = "http://www.mitre.org/ocil/1.1", substitutionHeadName = "question_test_action")
	public JAXBElement<StringQuestionTestAction> createStringQuestionTestAction(
			StringQuestionTestAction value) {
		return new JAXBElement<StringQuestionTestAction>(
				_StringQuestionTestAction_QNAME,
				StringQuestionTestAction.class, null, value);
	}
}
