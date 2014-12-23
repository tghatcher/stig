/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.OCIL;

import java.util.ArrayList;
import java.util.List;
import org.mitre.ocil._1.OperationType;
import org.mitre.ocil._1.OperatorType;
import org.mitre.ocil._1.QuestionTestActionType;
import org.mitre.ocil._1.Questionnaire;
import stigviewergui.OCIL.OCILDriver;
import stigviewergui.OCIL.Questionnaire;
import stigviewergui.OCIL.TestAction;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class QuestionnaireTestAction extends TestAction {
	private stigviewergui.OCIL.Questionnaire questionnaire;
	private List<TestAction> testActions = new ArrayList<TestAction>();
	private OCILDriver.OperatorType operator;
	private int currentTestAction = 0;

	QuestionnaireTestAction(Questionnaire q) {
		super(null);
		this.id = q.getId();
		this.negate = q.getActions().isNegate();
		this.operator = q.getActions().getOperation()
				.equals((Object) OperatorType.OR) ? OCILDriver.OperatorType.OR
				: OCILDriver.OperatorType.AND;
		this.questionnaire = new stigviewergui.OCIL.Questionnaire(q);
		this.questionnaire.setResponse(this.getResultString());
	}

	public stigviewergui.OCIL.Questionnaire getQuestionnaire() {
		return this.questionnaire;
	}

	public int getCurrentTestAction() {
		return this.currentTestAction;
	}

	public void setCurrentTestAction(int currentTestAction) {
		this.currentTestAction = currentTestAction;
	}

	public List<TestAction> getTestActions() {
		return this.testActions;
	}

	public void setTestActions(List<TestAction> testActions) {
		this.testActions = testActions;
	}

	public OCILDriver.OperatorType getOperator() {
		return this.operator;
	}

	public void setOperator(OCILDriver.OperatorType operator) {
		this.operator = operator;
	}
}
