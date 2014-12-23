/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.OCIL;

import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import stigviewergui.OCIL.BooleanQuestion;
import stigviewergui.OCIL.BooleanQuestionTestAction;
import stigviewergui.OCIL.ChoiceQuestion;
import stigviewergui.OCIL.ChoiceQuestionTestAction;
import stigviewergui.OCIL.NumericQuestion;
import stigviewergui.OCIL.NumericQuestionTestAction;
import stigviewergui.OCIL.OCILHandler;
import stigviewergui.OCIL.OCILReader;
import stigviewergui.OCIL.Question;
import stigviewergui.OCIL.Questionnaire;
import stigviewergui.OCIL.QuestionnaireTestAction;
import stigviewergui.OCIL.ResultAction;
import stigviewergui.OCIL.StringQuestion;
import stigviewergui.OCIL.StringQuestionTestAction;
import stigviewergui.OCIL.TestAction;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class OCILDriver {
	private OCILHandler handler = null;
	private TestAction currentTestAction = null;
	private OCILReader reader = new OCILReader();

	OCILDriver(OCILHandler handler) {
		this.handler = handler;
	}

	public void open(String filename) throws IOException {
		this.reader.open(filename);
	}

	public void write() {
		this.reader.write();
	}

	public List<Questionnaire> getQuestionnaires() {
		ArrayList<Questionnaire> questionnaires = new ArrayList<Questionnaire>();
		for (QuestionnaireTestAction questionnaireTA : this.reader
				.getQuestionnaireTestActionList()) {
			questionnaires.add(questionnaireTA.getQuestionnaire());
		}
		return questionnaires;
	}

	public void startQuestionnaire(int questionnaireIndex) {
		this.startQuestionnaire(this.reader.getQuestionnaireTestActionList()
				.get(questionnaireIndex));
	}

	public void startQuestionnaire(QuestionnaireTestAction qTA) {
		System.out.println("OCILDriver.startQuestionnaire()");
		qTA.setCurrentTestAction(0);
		this.doTestAction(qTA.getTestActions().get(0), qTA);
	}

	private void continueQuestionnaire(QuestionnaireTestAction qTA) {
		System.out.println("OCILDriver.continueQuestionnaire()");
		qTA.setCurrentTestAction(qTA.getCurrentTestAction() + 1);
		if (qTA.getCurrentTestAction() == qTA.getTestActions().size()) {
			qTA.setResult(this.applyOperator(qTA.getTestActions(),
					qTA.getOperator()));
			qTA.getQuestionnaire().setResponse(qTA.getResultString());
			this.reader.writeResults(qTA);
			this.returnResult(qTA);
		} else {
			this.doTestAction(
					qTA.getTestActions().get(qTA.getCurrentTestAction()), qTA);
		}
	}

	private void returnResult(TestAction testAction) {
		if (testAction.isNegate()) {
			testAction.setResult(this.negateResult(testAction.getResult()));
		}
		if (testAction.getClass() != QuestionnaireTestAction.class) {
			this.reader.writeResults(testAction);
		}
		System.out.println("OCILDriver.returnResult() -> "
				+ testAction.getResultString());
		if (testAction.getPreviousTestAction() == null) {
			System.out.println("OCILHandler.endQuestionnaire()");
			this.handler.endQuestionnaire();
			return;
		}
		ResultAction.ResultType result = testAction.getResult();
		if (testAction.getPreviousTestAction().getClass()
				.equals(QuestionnaireTestAction.class)) {
			this.continueQuestionnaire((QuestionnaireTestAction) testAction
					.getPreviousTestAction());
		} else {
			TestAction previousTA = testAction.getPreviousTestAction();
			previousTA.setResult(result);
			this.returnResult(previousTA);
		}
	}

	private void doTestAction(TestAction testAction, TestAction previousTA) {
		this.currentTestAction = testAction;
		testAction.setPreviousTestAction(previousTA);
		if (testAction.getClass().equals(QuestionnaireTestAction.class)) {
			this.startQuestionnaire((QuestionnaireTestAction) testAction);
		} else if (testAction.getClass()
				.equals(BooleanQuestionTestAction.class)) {
			System.out.println("OCILHandler.startBooleanQuestion()");
			BooleanQuestionTestAction booleanQuestionTA = (BooleanQuestionTestAction) testAction;
			this.handler
					.startBooleanQuestion((BooleanQuestion) booleanQuestionTA
							.getQuestion());
		} else if (testAction.getClass().equals(ChoiceQuestionTestAction.class)) {
			System.out.println("OCILHandler.startChoiceQuestion()");
			ChoiceQuestionTestAction choiceQuestionTA = (ChoiceQuestionTestAction) testAction;
			this.handler.startChoiceQuestion((ChoiceQuestion) choiceQuestionTA
					.getQuestion());
		} else if (testAction.getClass()
				.equals(NumericQuestionTestAction.class)) {
			System.out.println("OCILHandler.startNumericQuestion()");
			NumericQuestionTestAction numericQuestionTA = (NumericQuestionTestAction) testAction;
			this.handler
					.startNumericQuestion((NumericQuestion) numericQuestionTA
							.getQuestion());
		} else if (testAction.getClass().equals(StringQuestionTestAction.class)) {
			System.out.println("OCILHandler.startStringQuestion()");
			StringQuestionTestAction stringQuestionTA = (StringQuestionTestAction) testAction;
			this.handler.startStringQuestion((StringQuestion) stringQuestionTA
					.getQuestion());
		} else {
			System.out
					.println("Found no recognisable test action in doTestAction!");
		}
	}

	private void doResultAction(ResultAction resultAction) {
		ResultAction.ResultType result;
		if (resultAction == null) {
			System.out.println("ResultAction is null");
			result = ResultAction.ResultType.ERROR;
		} else if (resultAction.getTestAction() == null) {
			result = resultAction.getResult();
		} else {
			this.doTestAction(resultAction.getTestAction(),
					this.currentTestAction);
			return;
		}
		this.currentTestAction.setResult(result);
		this.returnResult(this.currentTestAction);
	}

	public void endBooleanQuestion(boolean answer) {
		System.out.println("OCILDriver.endBooleanQuestion()");
		if (!this.currentTestAction.getClass().equals(
				BooleanQuestionTestAction.class)) {
			System.out.println("This is not a boolean question!: "
					+ this.currentTestAction.getClass());
		}
		BooleanQuestionTestAction booleanQuestionTA = (BooleanQuestionTestAction) this.currentTestAction;
		BooleanQuestion booleanQuestion = (BooleanQuestion) booleanQuestionTA
				.getQuestion();
		booleanQuestion.setAnswer(answer);
		this.reader.writeResults(booleanQuestion);
		if (answer) {
			this.doResultAction(booleanQuestionTA.getWhenTrue());
		} else {
			this.doResultAction(booleanQuestionTA.getWhenFalse());
		}
	}

	public void endChoiceQuestion(int answer) {
		System.out.println("OCILDriver.endChoiceQuestion()");
		if (!this.currentTestAction.getClass().equals(
				ChoiceQuestionTestAction.class)) {
			System.out.println("This is not a choice question!: "
					+ this.currentTestAction.getClass());
		}
		ChoiceQuestionTestAction choiceQuestionTA = (ChoiceQuestionTestAction) this.currentTestAction;
		ChoiceQuestion choiceQuestion = (ChoiceQuestion) choiceQuestionTA
				.getQuestion();
		choiceQuestion.setAnswer(choiceQuestionTA.getChoiceIDs().get(answer));
		this.reader.writeResults(choiceQuestion);
		if (answer < 0 || answer >= choiceQuestionTA.getWhenChoices().size()) {
			this.doResultAction(new ResultAction(ResultAction.ResultType.ERROR));
		}
		this.doResultAction(choiceQuestionTA.getWhenChoices().get(answer));
	}

	public void endNumericQuestion(BigDecimal answer) {
		System.out.println("OCILDriver.endStringQuestion()");
		if (!this.currentTestAction.getClass().equals(
				NumericQuestionTestAction.class)) {
			System.out.println("This is not a numeric question!: "
					+ this.currentTestAction.getClass());
		}
		NumericQuestionTestAction numericQuestionTA = (NumericQuestionTestAction) this.currentTestAction;
		NumericQuestion numericQuestion = (NumericQuestion) numericQuestionTA
				.getQuestion();
		numericQuestion.setResponse("ANSWERED");
		numericQuestion.setAnswer(answer);
		this.reader.writeResults(numericQuestion);
		for (NumericQuestionTestAction.ExactMatch em : numericQuestionTA
				.getExactMatches()) {
			if (!em.isMatch(answer))
				continue;
			this.doResultAction(em.getResultAction());
			return;
		}
		for (NumericQuestionTestAction.RangeMatch rm : numericQuestionTA
				.getRangeMatches()) {
			if (!rm.isMatch(answer))
				continue;
			this.doResultAction(rm.getResultAction());
			return;
		}
		this.doResultAction(new ResultAction(ResultAction.ResultType.ERROR));
	}

	public void endStringQuestion(String answer) {
		System.out.println("OCILDriver.endStringQuestion()");
		if (!this.currentTestAction.getClass().equals(
				StringQuestionTestAction.class)) {
			System.out.println("This is not a string question!: "
					+ this.currentTestAction.getClass());
		}
		StringQuestionTestAction stringQuestionTA = (StringQuestionTestAction) this.currentTestAction;
		StringQuestion stringQuestion = (StringQuestion) stringQuestionTA
				.getQuestion();
		stringQuestion.setAnswer(answer);
		this.reader.writeResults(stringQuestion);
		for (StringQuestionTestAction.PatternMatch pm : stringQuestionTA
				.getPatternMatches()) {
			if (!pm.isMatch(answer))
				continue;
			this.doResultAction(pm.getResultAction());
			return;
		}
		this.doResultAction(new ResultAction(ResultAction.ResultType.ERROR));
	}

	private ResultAction.ResultType applyOperator(List<TestAction> testActions,
			OperatorType operator) {
		boolean existsPass = false;
		boolean existsFail = false;
		boolean existsError = false;
		boolean existsUnknown = false;
		boolean existsNotTested = false;
		boolean existsNotApplicable = false;
		for (TestAction testAction : testActions) {
			ResultAction.ResultType result = testAction.getResult();
			if (result.equals((Object) ResultAction.ResultType.PASS)) {
				existsPass = true;
				continue;
			}
			if (result.equals((Object) ResultAction.ResultType.FAIL)) {
				existsFail = true;
				continue;
			}
			if (result.equals((Object) ResultAction.ResultType.ERROR)) {
				existsError = true;
				continue;
			}
			if (result.equals((Object) ResultAction.ResultType.UNKNOWN)) {
				existsUnknown = true;
				continue;
			}
			if (result.equals((Object) ResultAction.ResultType.NOT_TESTED)) {
				existsNotTested = true;
				continue;
			}
			if (!result.equals((Object) ResultAction.ResultType.NOT_APPLICABLE))
				continue;
			existsNotApplicable = true;
		}
		if (operator.equals((Object) OperatorType.AND)) {
			if (existsFail) {
				return ResultAction.ResultType.FAIL;
			}
			if (existsError) {
				return ResultAction.ResultType.ERROR;
			}
			if (existsUnknown) {
				return ResultAction.ResultType.UNKNOWN;
			}
			if (existsNotTested) {
				return ResultAction.ResultType.NOT_TESTED;
			}
			if (existsPass) {
				return ResultAction.ResultType.PASS;
			}
			if (existsNotApplicable) {
				return ResultAction.ResultType.NOT_APPLICABLE;
			}
			return ResultAction.ResultType.NOT_TESTED;
		}
		if (operator.equals((Object) OperatorType.OR)) {
			if (existsFail) {
				return ResultAction.ResultType.PASS;
			}
			if (existsError) {
				return ResultAction.ResultType.ERROR;
			}
			if (existsUnknown) {
				return ResultAction.ResultType.UNKNOWN;
			}
			if (existsNotTested) {
				return ResultAction.ResultType.NOT_TESTED;
			}
			if (existsPass) {
				return ResultAction.ResultType.FAIL;
			}
			if (existsNotApplicable) {
				return ResultAction.ResultType.NOT_APPLICABLE;
			}
			return ResultAction.ResultType.NOT_TESTED;
		}
		return ResultAction.ResultType.ERROR;
	}

	private ResultAction.ResultType negateResult(ResultAction.ResultType result) {
		ResultAction.ResultType ret = result
				.equals((Object) ResultAction.ResultType.PASS) ? ResultAction.ResultType.FAIL
				: (result.equals((Object) ResultAction.ResultType.FAIL) ? ResultAction.ResultType.PASS
						: result);
		return ret;
	}

	public static enum OperatorType {
		AND, OR;

		private OperatorType() {
		}
	}

}
