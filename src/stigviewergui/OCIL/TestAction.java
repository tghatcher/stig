/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.OCIL;

import org.mitre.ocil._1.QuestionTestActionType;
import stigviewergui.OCIL.ItemBaseType;
import stigviewergui.OCIL.Question;
import stigviewergui.OCIL.ResultAction;

public abstract class TestAction extends ItemBaseType {
	protected ResultAction.ResultType result = ResultAction.ResultType.NOT_TESTED;
	protected boolean negate = false;
	protected ResultAction whenUnknown = null;
	protected ResultAction whenNotTested = null;
	protected ResultAction whenNotApplicable = null;
	protected ResultAction whenError = null;
	protected TestAction previousTestAction = null;
	protected Question question = null;

	TestAction(QuestionTestActionType qtat) {
		if (qtat == null) {
			return;
		}
		this.id = qtat.getId();
	}

	public TestAction getPreviousTestAction() {
		return this.previousTestAction;
	}

	public void setPreviousTestAction(TestAction previousTestAction) {
		this.previousTestAction = previousTestAction;
	}

	public Question getQuestion() {
		return this.question;
	}

	public boolean isNegate() {
		return this.negate;
	}

	public void setNegate(boolean negate) {
		this.negate = negate;
	}

	public ResultAction.ResultType getResult() {
		return this.result;
	}

	public String getResultString() {
		if (this.result.equals((Object) ResultAction.ResultType.PASS)) {
			return "PASS";
		}
		if (this.result.equals((Object) ResultAction.ResultType.FAIL)) {
			return "FAIL";
		}
		if (this.result.equals((Object) ResultAction.ResultType.ERROR)) {
			return "ERROR";
		}
		if (this.result.equals((Object) ResultAction.ResultType.UNKNOWN)) {
			return "UNKNOWN";
		}
		if (this.result.equals((Object) ResultAction.ResultType.NOT_TESTED)) {
			return "NOT_TESTED";
		}
		if (this.result.equals((Object) ResultAction.ResultType.NOT_APPLICABLE)) {
			return "NOT_APPLICABLE";
		}
		return "Error in TestAction::getResultString";
	}

	public void setResult(ResultAction.ResultType result) {
		this.result = result;
	}

	public ResultAction getWhenError() {
		return this.whenError;
	}

	public void setWhenError(ResultAction whenError) {
		this.whenError = whenError;
	}

	public ResultAction getWhenNotApplicable() {
		return this.whenNotApplicable;
	}

	public void setWhenNotApplicable(ResultAction whenNotApplicable) {
		this.whenNotApplicable = whenNotApplicable;
	}

	public ResultAction getWhenNotTested() {
		return this.whenNotTested;
	}

	public void setWhenNotTested(ResultAction whenNotTested) {
		this.whenNotTested = whenNotTested;
	}

	public ResultAction getWhenUnknown() {
		return this.whenUnknown;
	}

	public void setWhenUnknown(ResultAction whenUnknown) {
		this.whenUnknown = whenUnknown;
	}
}
