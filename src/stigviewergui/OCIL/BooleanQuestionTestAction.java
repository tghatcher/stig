/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.OCIL;

import org.mitre.ocil._1.BooleanQuestion;
import org.mitre.ocil._1.BooleanQuestionTestAction;
import org.mitre.ocil._1.QuestionTestActionType;
import stigviewergui.OCIL.BooleanQuestion;
import stigviewergui.OCIL.Question;
import stigviewergui.OCIL.ResultAction;
import stigviewergui.OCIL.TestAction;

public class stigviewergui.OCIL.BooleanQuestionTestAction
extends TestAction {
    private ResultAction whenTrue;
    private ResultAction whenFalse;

    stigviewergui.OCIL.BooleanQuestionTestAction(BooleanQuestionTestAction bqta, BooleanQuestion bq) {
        super(bqta);
        this.question = new stigviewergui.OCIL.BooleanQuestion(bqta, bq);
    }

    public ResultAction getWhenFalse() {
        return this.whenFalse;
    }

    public void setWhenFalse(ResultAction whenFalse) {
        this.whenFalse = whenFalse;
    }

    public ResultAction getWhenTrue() {
        return this.whenTrue;
    }

    public void setWhenTrue(ResultAction whenTrue) {
        this.whenTrue = whenTrue;
    }
}

