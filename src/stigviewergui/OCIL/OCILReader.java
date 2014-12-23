/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.OCIL;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import org.mitre.ocil._1.BooleanQuestion;
import org.mitre.ocil._1.BooleanQuestionResult;
import org.mitre.ocil._1.BooleanQuestionTestAction;
import org.mitre.ocil._1.Choice;
import org.mitre.ocil._1.ChoiceGroup;
import org.mitre.ocil._1.ChoiceQuestion;
import org.mitre.ocil._1.ChoiceQuestionResult;
import org.mitre.ocil._1.ChoiceQuestionTestAction;
import org.mitre.ocil._1.GeneratorType;
import org.mitre.ocil._1.ItemBaseType;
import org.mitre.ocil._1.NumericQuestion;
import org.mitre.ocil._1.NumericQuestionResult;
import org.mitre.ocil._1.NumericQuestionTestAction;
import org.mitre.ocil._1.Ocil;
import org.mitre.ocil._1.OperationType;
import org.mitre.ocil._1.PatternType;
import org.mitre.ocil._1.QuestionResultType;
import org.mitre.ocil._1.QuestionType;
import org.mitre.ocil._1.Questionnaire;
import org.mitre.ocil._1.RangeType;
import org.mitre.ocil._1.ResultChoiceType;
import org.mitre.ocil._1.Results;
import org.mitre.ocil._1.StringQuestion;
import org.mitre.ocil._1.StringQuestionResult;
import org.mitre.ocil._1.StringQuestionTestAction;
import org.mitre.ocil._1.TestActionRef;
import org.mitre.ocil._1.TextType;
import org.mitre.ocil._1.WhenChoice;
import org.mitre.ocil._1.WhenEquals;
import org.mitre.ocil._1.WhenPattern;
import org.mitre.ocil._1.WhenRange;
import stigviewergui.OCIL.BooleanQuestion;
import stigviewergui.OCIL.BooleanQuestionTestAction;
import stigviewergui.OCIL.ChoiceQuestion;
import stigviewergui.OCIL.ChoiceQuestionTestAction;
import stigviewergui.OCIL.NumericQuestion;
import stigviewergui.OCIL.NumericQuestionTestAction;
import stigviewergui.OCIL.Question;
import stigviewergui.OCIL.QuestionnaireTestAction;
import stigviewergui.OCIL.ResultAction;
import stigviewergui.OCIL.StringQuestion;
import stigviewergui.OCIL.StringQuestionTestAction;
import stigviewergui.OCIL.TestAction;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class OCILReader {
	List<QuestionnaireTestAction> questionnaires = new ArrayList<QuestionnaireTestAction>();
	private Ocil ocil = new Ocil();
	private Map<String, Questionnaire> mapQuestionnaires = null;
	private Map<String, BooleanQuestionTestAction> mapBooleanQuestionTestActions = null;
	private Map<String, BooleanQuestion> mapBooleanQuestions = null;
	private Map<String, ChoiceQuestionTestAction> mapChoiceQuestionTestActions = null;
	private Map<String, ChoiceQuestion> mapChoiceQuestions = null;
	private Map<String, String> mapChoices = null;
	private Map<String, List<Choice>> mapChoiceGroups = null;
	private Map<String, NumericQuestionTestAction> mapNumericQuestionTestActions = null;
	private Map<String, NumericQuestion> mapNumericQuestions = null;
	private Map<String, StringQuestionTestAction> mapStringQuestionTestActions = null;
	private Map<String, StringQuestion> mapStringQuestions = null;
	private Map<String, Boolean> mapActive = new HashMap<String, Boolean>();

	OCILReader() {
	}

	public void open(String filename) throws IOException {
		try {
			JAXBContext jaxbCtx = JAXBContext.newInstance(this.ocil.getClass()
					.getPackage().getName());
			Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
			this.ocil = (Ocil) unmarshaller.unmarshal(new File(filename));
			if (!this.ocil.getGenerator().getSchemaVersion().toString()
					.equals("1.1")) {
				String t = new String();
				t = "OCIL version "
						+ this.ocil.getGenerator().getSchemaVersion()
								.toString() + " is not supported.";
				throw new JAXBException(t);
			}
		} catch (JAXBException ex) {
			throw new IOException(ex.getMessage());
		}
		this.mapQuestionnaires = new HashMap<String, Questionnaire>();
		for (Questionnaire q2 : this.ocil.getQuestionnaire()) {
			this.mapQuestionnaires.put(q2.getId(), q2);
		}
		this.mapBooleanQuestionTestActions = new HashMap<String, BooleanQuestionTestAction>();
		this.mapChoiceQuestionTestActions = new HashMap<String, ChoiceQuestionTestAction>();
		this.mapNumericQuestionTestActions = new HashMap<String, NumericQuestionTestAction>();
		this.mapStringQuestionTestActions = new HashMap<String, StringQuestionTestAction>();
		for (JAXBElement jaxbe2 : this.ocil.getTestAction()) {
			if (jaxbe2.getDeclaredType().getCanonicalName()
					.equals("org.mitre.ocil._1.BooleanQuestionTestAction")) {
				BooleanQuestionTestAction bqta = (BooleanQuestionTestAction) jaxbe2
						.getValue();
				this.mapBooleanQuestionTestActions.put(bqta.getId(), bqta);
				continue;
			}
			if (jaxbe2.getDeclaredType().getCanonicalName()
					.equals("org.mitre.ocil._1.ChoiceQuestionTestAction")) {
				ChoiceQuestionTestAction cqta = (ChoiceQuestionTestAction) jaxbe2
						.getValue();
				this.mapChoiceQuestionTestActions.put(cqta.getId(), cqta);
				continue;
			}
			if (jaxbe2.getDeclaredType().getCanonicalName()
					.equals("org.mitre.ocil._1.NumericQuestionTestAction")) {
				NumericQuestionTestAction nqta = (NumericQuestionTestAction) jaxbe2
						.getValue();
				this.mapNumericQuestionTestActions.put(nqta.getId(), nqta);
				continue;
			}
			if (jaxbe2.getDeclaredType().getCanonicalName()
					.equals("org.mitre.ocil._1.StringQuestionTestAction")) {
				StringQuestionTestAction sqta = (StringQuestionTestAction) jaxbe2
						.getValue();
				this.mapStringQuestionTestActions.put(sqta.getId(), sqta);
				continue;
			}
			System.out.println("Don't recognise QuestionTestAction type: "
					+ jaxbe2.getDeclaredType().getCanonicalName());
		}
		this.mapBooleanQuestions = new HashMap<String, BooleanQuestion>();
		this.mapChoiceQuestions = new HashMap<String, ChoiceQuestion>();
		this.mapNumericQuestions = new HashMap<String, NumericQuestion>();
		this.mapStringQuestions = new HashMap<String, StringQuestion>();
		for (JAXBElement jaxbe : this.ocil.getQuestion()) {
			if (jaxbe.getDeclaredType().getCanonicalName()
					.equals("org.mitre.ocil._1.BooleanQuestion")) {
				BooleanQuestion bq = (BooleanQuestion) jaxbe.getValue();
				this.mapBooleanQuestions.put(bq.getId(), bq);
				continue;
			}
			if (jaxbe.getDeclaredType().getCanonicalName()
					.equals("org.mitre.ocil._1.ChoiceQuestion")) {
				ChoiceQuestion cq = (ChoiceQuestion) jaxbe.getValue();
				this.mapChoiceQuestions.put(cq.getId(), cq);
				continue;
			}
			if (jaxbe.getDeclaredType().getCanonicalName()
					.equals("org.mitre.ocil._1.NumericQuestion")) {
				NumericQuestion nq = (NumericQuestion) jaxbe.getValue();
				this.mapNumericQuestions.put(nq.getId(), nq);
				continue;
			}
			if (jaxbe.getDeclaredType().getCanonicalName()
					.equals("org.mitre.ocil._1.StringQuestion")) {
				StringQuestion sq = (StringQuestion) jaxbe.getValue();
				this.mapStringQuestions.put(sq.getId(), sq);
				continue;
			}
			System.out.println("Don't recognise Question type: "
					+ jaxbe.getDeclaredType().getCanonicalName());
		}
		this.mapChoiceGroups = new HashMap<String, List<Choice>>();
		this.mapChoices = new HashMap<String, String>();
		for (ChoiceGroup cg : this.ocil.getChoiceGroup()) {
			ArrayList<Choice> newChoiceList = new ArrayList<Choice>();
			for (Choice c : cg.getChoice()) {
				newChoiceList.add(c);
				this.mapChoices.put(c.getId(), c.getValue());
			}
			this.mapChoiceGroups.put(cg.getId(), newChoiceList);
		}
		for (Questionnaire q : this.ocil.getQuestionnaire()) {
			if (q.isChildOnly())
				continue;
			if (this.mapActive.containsKey(q.getId())) {
				throw new IOException("Root questionnaire " + q.getId()
						+ " has already been processed.");
			}
			this.mapActive.put(q.getId(), Boolean.TRUE);
			QuestionnaireTestAction questionnaireTA = this
					.buildQuestionnaireTestAction(q);
			questionnaireTA.setPreviousTestAction(null);
			this.questionnaires.add(questionnaireTA);
			this.mapActive.put(q.getId(), Boolean.FALSE);
		}
		if (this.ocil.getResults() == null) {
			this.ocil.setResults(new Results());
		}
		this.mapQuestionnaires = null;
		this.mapBooleanQuestionTestActions = null;
		this.mapBooleanQuestions = null;
		this.mapChoiceQuestionTestActions = null;
		this.mapChoiceQuestions = null;
		this.mapChoices = null;
		this.mapChoiceGroups = null;
		this.mapNumericQuestionTestActions = null;
		this.mapNumericQuestions = null;
		this.mapStringQuestionTestActions = null;
		this.mapStringQuestions = null;
	}

	public List<QuestionnaireTestAction> getQuestionnaireTestActionList() {
		return this.questionnaires;
	}

	private QuestionnaireTestAction buildQuestionnaireTestAction(Questionnaire q)
			throws IOException {
		QuestionnaireTestAction newQTA = new QuestionnaireTestAction(q);
		ArrayList<TestAction> childTestActions = new ArrayList<TestAction>();
		for (TestActionRef tar : q.getActions().getTestActionRef()) {
			TestAction newTA = this.buildTestAction(tar);
			if (tar.isNegate()) {
				newTA.setNegate(!newTA.isNegate());
			}
			childTestActions.add(newTA);
		}
		newQTA.setTestActions(childTestActions);
		return newQTA;
	}

	private TestAction buildTestAction(TestActionRef testActionRef)
			throws IOException {
		TestAction newTA = null;
		if (this.mapActive.containsKey(testActionRef.getValue())) {
			if (this.mapActive.get(testActionRef.getValue()).booleanValue()) {
				throw new IOException("A loop has been found at "
						+ testActionRef.getValue());
			}
		} else {
			this.mapActive.put(testActionRef.getValue(), Boolean.TRUE);
		}
		if (this.mapQuestionnaires.containsKey(testActionRef.getValue())) {
			newTA = this.buildQuestionnaireTestAction(this.mapQuestionnaires
					.get(testActionRef.getValue()));
		} else if (this.mapBooleanQuestionTestActions.containsKey(testActionRef
				.getValue())) {
			newTA = this.buildBooleanQuestionTestAction(testActionRef);
		} else if (this.mapChoiceQuestionTestActions.containsKey(testActionRef
				.getValue())) {
			newTA = this.buildChoiceQuestionTestAction(testActionRef);
		} else if (this.mapStringQuestionTestActions.containsKey(testActionRef
				.getValue())) {
			newTA = this.buildStringQuestionTestAction(testActionRef);
		} else if (this.mapNumericQuestionTestActions.containsKey(testActionRef
				.getValue())) {
			newTA = this.buildNumericQuestionTestAction(testActionRef);
		} else {
			System.out.println("Given test action id does not exist: "
					+ testActionRef.getValue());
		}
		this.mapActive.put(testActionRef.getValue(), Boolean.FALSE);
		return newTA;
	}

	private stigviewergui.OCIL.BooleanQuestionTestAction buildBooleanQuestionTestAction(
			TestActionRef testActionRef) throws IOException {
		BooleanQuestionTestAction bqta = this.mapBooleanQuestionTestActions
				.get(testActionRef.getValue());
		BooleanQuestion bq = this.mapBooleanQuestions
				.get(bqta.getQuestionRef());
		stigviewergui.OCIL.BooleanQuestionTestAction booleanQuestionTA = new stigviewergui.OCIL.BooleanQuestionTestAction(
				bqta, bq);
		booleanQuestionTA.setWhenUnknown(this.buildResultAction(bqta
				.getWhenUnknown()));
		booleanQuestionTA.setWhenError(this.buildResultAction(bqta
				.getWhenUnknown()));
		booleanQuestionTA.setWhenNotTested(this.buildResultAction(bqta
				.getWhenNotTested()));
		booleanQuestionTA.setWhenNotApplicable(this.buildResultAction(bqta
				.getWhenNotApplicable()));
		booleanQuestionTA
				.setWhenTrue(this.buildResultAction(bqta.getWhenTrue()));
		booleanQuestionTA.setWhenFalse(this.buildResultAction(bqta
				.getWhenFalse()));
		return booleanQuestionTA;
	}

	private stigviewergui.OCIL.ChoiceQuestionTestAction buildChoiceQuestionTestAction(
			TestActionRef testActionRef) throws IOException {
		ChoiceQuestionTestAction cqta = this.mapChoiceQuestionTestActions
				.get(testActionRef.getValue());
		ChoiceQuestion cq = this.mapChoiceQuestions.get(cqta.getQuestionRef());
		stigviewergui.OCIL.ChoiceQuestionTestAction choiceQuestionTA = new stigviewergui.OCIL.ChoiceQuestionTestAction(
				cqta, cq);
		choiceQuestionTA.setWhenUnknown(this.buildResultAction(cqta
				.getWhenUnknown()));
		choiceQuestionTA.setWhenError(this.buildResultAction(cqta
				.getWhenUnknown()));
		choiceQuestionTA.setWhenNotTested(this.buildResultAction(cqta
				.getWhenNotTested()));
		choiceQuestionTA.setWhenNotApplicable(this.buildResultAction(cqta
				.getWhenNotApplicable()));
		for (Object choiceOrChoiceGroupRef : cq.getChoiceOrChoiceGroupRef()) {
			if (!choiceOrChoiceGroupRef.getClass().equals(Choice.class))
				continue;
			Choice choice = (Choice) choiceOrChoiceGroupRef;
			this.mapChoices.put(choice.getId(), choice.getValue());
		}
		stigviewergui.OCIL.ChoiceQuestion choiceQuestion = (stigviewergui.OCIL.ChoiceQuestion) choiceQuestionTA
				.getQuestion();
		for (WhenChoice whenChoice : cqta.getWhenChoice()) {
			List<String> choiceIDs = whenChoice.getChoiceRef();
			ResultAction resultAction = this.buildResultAction(whenChoice);
			for (String choiceID : choiceIDs) {
				choiceQuestion.getChoices().add(this.mapChoices.get(choiceID));
				choiceQuestionTA.getWhenChoices().add(resultAction);
				choiceQuestionTA.getChoiceIDs().add(choiceID);
			}
		}
		return choiceQuestionTA;
	}

	private stigviewergui.OCIL.NumericQuestionTestAction buildNumericQuestionTestAction(
			TestActionRef testActionRef) throws IOException {
		NumericQuestionTestAction nqta = this.mapNumericQuestionTestActions
				.get(testActionRef.getValue());
		NumericQuestion nq = this.mapNumericQuestions
				.get(nqta.getQuestionRef());
		stigviewergui.OCIL.NumericQuestionTestAction numericQuestionTA = new stigviewergui.OCIL.NumericQuestionTestAction(
				nqta, nq);
		numericQuestionTA.setWhenUnknown(this.buildResultAction(nqta
				.getWhenUnknown()));
		numericQuestionTA.setWhenError(this.buildResultAction(nqta
				.getWhenUnknown()));
		numericQuestionTA.setWhenNotTested(this.buildResultAction(nqta
				.getWhenNotTested()));
		numericQuestionTA.setWhenNotApplicable(this.buildResultAction(nqta
				.getWhenNotApplicable()));
		for (ResultChoiceType rct : nqta.getRest()) {
			if (rct.getClass().equals(WhenEquals.class)) {
				WhenEquals whenEquals = (WhenEquals) rct;
				ResultAction resultAction = this.buildResultAction(whenEquals);
				numericQuestionTA.addExactMatches(whenEquals.getValue(),
						resultAction);
				continue;
			}
			if (rct.getClass().equals(WhenRange.class)) {
				WhenRange whenRange = (WhenRange) rct;
				ArrayList<BigDecimal> mins = new ArrayList<BigDecimal>();
				ArrayList<BigDecimal> maxes = new ArrayList<BigDecimal>();
				for (RangeType range : whenRange.getRange()) {
					mins.add(range.getMin().getValue());
					maxes.add(range.getMax().getValue());
				}
				ResultAction resultAction = this.buildResultAction(whenRange);
				numericQuestionTA.addRangeMatches(mins, maxes, resultAction);
				continue;
			}
			System.out.println("Expected whenEquals or whenRange and got: "
					+ rct.getClass());
		}
		return numericQuestionTA;
	}

	private stigviewergui.OCIL.StringQuestionTestAction buildStringQuestionTestAction(
			TestActionRef testActionRef) throws IOException {
		StringQuestionTestAction sqta = this.mapStringQuestionTestActions
				.get(testActionRef.getValue());
		StringQuestion sq = this.mapStringQuestions.get(sqta.getQuestionRef());
		stigviewergui.OCIL.StringQuestionTestAction stringQuestionTA = new stigviewergui.OCIL.StringQuestionTestAction(
				sqta, sq);
		stringQuestionTA.setWhenUnknown(this.buildResultAction(sqta
				.getWhenUnknown()));
		stringQuestionTA.setWhenError(this.buildResultAction(sqta
				.getWhenUnknown()));
		stringQuestionTA.setWhenNotTested(this.buildResultAction(sqta
				.getWhenNotTested()));
		stringQuestionTA.setWhenNotApplicable(this.buildResultAction(sqta
				.getWhenNotApplicable()));
		for (WhenPattern whenPattern : sqta.getWhenPattern()) {
			ArrayList<Pattern> patternList = new ArrayList<Pattern>();
			for (PatternType pattern : whenPattern.getPattern()) {
				patternList.add(Pattern.compile(pattern.getValue()));
			}
			ResultAction resultAction = this.buildResultAction(whenPattern);
			stringQuestionTA.addWhenPattern(patternList, resultAction);
		}
		return stringQuestionTA;
	}

	private ResultAction buildResultAction(ResultChoiceType rct)
			throws IOException {
		ResultAction newRA = new ResultAction();
		if (rct == null) {
			newRA.setResult(ResultAction.ResultType.ERROR);
			return newRA;
		}
		if (rct.getResult() == null) {
			newRA.setResult(ResultAction.ResultType.ERROR);
		} else if (rct.getResult().equals("PASS")) {
			newRA.setResult(ResultAction.ResultType.PASS);
		} else if (rct.getResult().equals("FAIL")) {
			newRA.setResult(ResultAction.ResultType.FAIL);
		} else if (rct.getResult().equals("ERROR")) {
			newRA.setResult(ResultAction.ResultType.ERROR);
		} else if (rct.getResult().equals("UNKNOWN")) {
			newRA.setResult(ResultAction.ResultType.UNKNOWN);
		} else if (rct.getResult().equals("NOT_TESTED")) {
			newRA.setResult(ResultAction.ResultType.NOT_TESTED);
		} else if (rct.getResult().equals("NOT_APPLICABLE")) {
			newRA.setResult(ResultAction.ResultType.NOT_APPLICABLE);
		} else {
			System.out.println("Found no match for result '" + rct.getResult()
					+ "'");
		}
		if (rct.getTestActionRef() != null) {
			newRA.setTestAction(this.buildTestAction(rct.getTestActionRef()));
		}
		return newRA;
	}

	public void write() {
		TextType textType = new TextType();
		textType.setValue("This result set was made with the DISA STIG Viewer");
		this.ocil.getResults().setTitle(textType);
		try {
			JAXBContext jaxbCtx = JAXBContext.newInstance(this.ocil.getClass()
					.getPackage().getName());
			Marshaller marshaller = jaxbCtx.createMarshaller();
			marshaller.setProperty("jaxb.encoding", "UTF-8");
			marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
		} catch (JAXBException ex) {
			// empty catch block
		}
	}

	public void writeResults(QuestionnaireTestAction qTA) {
		Results.QuestionnaireResult qResult = new Results.QuestionnaireResult();
		qResult.setQuestionnaireRef(qTA.getId());
		qResult.setResult(qTA.getResultString());
		Results results = this.ocil.getResults();
		results.getQuestionnaireResult().add(qResult);
	}

	public void writeResults(TestAction testAction) {
		Results.TestActionResult taResult = new Results.TestActionResult();
		taResult.setTestActionRef(testAction.getId());
		taResult.setResult(testAction.getResultString());
		this.ocil.getResults().getTestActionResult().add(taResult);
	}

	public void writeResults(stigviewergui.OCIL.BooleanQuestion booleanQuestion) {
		BooleanQuestionResult bqResult = new BooleanQuestionResult();
		bqResult.setQuestionRef(booleanQuestion.getId());
		bqResult.setAnswer(booleanQuestion.getAnswer());
		QName qName = new QName("http://www.mitre.org/ocil/1.1",
				"question_result");
		JAXBElement<BooleanQuestionResult> jaxbe = new JAXBElement<BooleanQuestionResult>(
				qName, QuestionResultType.class, bqResult);
		this.ocil.getResults().getQuestionResult().add(jaxbe);
	}

	public void writeResults(stigviewergui.OCIL.ChoiceQuestion choiceQuestion) {
		ChoiceQuestionResult cqResult = new ChoiceQuestionResult();
		ChoiceQuestionResult.Answer cqAnswer = new ChoiceQuestionResult.Answer();
		cqResult.setQuestionRef(choiceQuestion.getId());
		cqAnswer.setChoiceRef(choiceQuestion.getAnswer());
		cqResult.setAnswer(cqAnswer);
		QName qName = new QName("http://www.mitre.org/ocil/1.1",
				"question_result");
		JAXBElement<ChoiceQuestionResult> jaxbe = new JAXBElement<ChoiceQuestionResult>(
				qName, QuestionResultType.class, cqResult);
		this.ocil.getResults().getQuestionResult().add(jaxbe);
	}

	public void writeResults(stigviewergui.OCIL.NumericQuestion numericQuestion) {
		NumericQuestionResult nqResult = new NumericQuestionResult();
		nqResult.setQuestionRef(numericQuestion.getId());
		nqResult.setAnswer(numericQuestion.getAnswer());
		QName qName = new QName("http://www.mitre.org/ocil/1.1",
				"question_result");
		JAXBElement<NumericQuestionResult> jaxbe = new JAXBElement<NumericQuestionResult>(
				qName, QuestionResultType.class, nqResult);
		this.ocil.getResults().getQuestionResult().add(jaxbe);
	}

	public void writeResults(stigviewergui.OCIL.StringQuestion stringQuestion) {
		StringQuestionResult nqResult = new StringQuestionResult();
		nqResult.setQuestionRef(stringQuestion.getId());
		nqResult.setAnswer(stringQuestion.getAnswer());
		QName qName = new QName("http://www.mitre.org/ocil/1.1",
				"question_result");
		JAXBElement<StringQuestionResult> jaxbe = new JAXBElement<StringQuestionResult>(
				qName, QuestionResultType.class, nqResult);
		this.ocil.getResults().getQuestionResult().add(jaxbe);
	}
}
