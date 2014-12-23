/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.OCIL;

import stigviewergui.OCIL.TestAction;

public class ResultAction {
	private ResultType result = null;
	private TestAction testAction = null;

	ResultAction() {
	}

	ResultAction(ResultType result) {
		this.result = result;
	}

	public ResultType getResult() {
		return this.result;
	}

	public void setResult(ResultType result) {
		this.result = result;
	}

	public TestAction getTestAction() {
		return this.testAction;
	}

	public void setTestAction(TestAction testAction) {
		this.testAction = testAction;
	}

	public static enum ResultType {
		PASS, FAIL, ERROR, UNKNOWN, NOT_TESTED, NOT_APPLICABLE;

		private ResultType() {
		}
	}

}
