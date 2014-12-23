/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.OCIL;

import org.mitre.ocil._1.BooleanQuestion;
import org.mitre.ocil._1.BooleanQuestionModelType;
import org.mitre.ocil._1.BooleanQuestionTestAction;
import org.mitre.ocil._1.QuestionTestActionType;
import org.mitre.ocil._1.QuestionType;
import stigviewergui.OCIL.Question;

public class stigviewergui.OCIL.BooleanQuestion
extends Question {
    private Model model;
    private Boolean defaultAnswer = null;
    private Boolean answer = null;

    stigviewergui.OCIL.BooleanQuestion(BooleanQuestionTestAction bqta, BooleanQuestion bq) {
        super(bqta, bq);
        this.defaultAnswer = bq.isDefaultAnswer();
        this.model = bq.getModel().equals((Object)BooleanQuestionModelType.MODEL_TRUE_FALSE) ? Model.TRUE_FALSE : Model.YES_NO;
    }

    public Model getModel() {
        return this.model;
    }

    public boolean getDefaultAnswer() {
        return this.defaultAnswer;
    }

    public Boolean getAnswer() {
        return this.answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }

    public static enum Model {
        YES_NO,
        TRUE_FALSE;
        

        private Model() {
        }
    }

}

