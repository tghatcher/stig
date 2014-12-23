/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.OCIL;

import org.mitre.ocil._1.QuestionTestActionType;
import org.mitre.ocil._1.QuestionType;
import org.mitre.ocil._1.StringQuestion;
import org.mitre.ocil._1.StringQuestionTestAction;
import stigviewergui.OCIL.Question;

public class stigviewergui.OCIL.StringQuestion
extends Question {
    private String answer = null;
    private String defaultAnswer = null;

    stigviewergui.OCIL.StringQuestion(StringQuestionTestAction sqta, StringQuestion sq) {
        super(sqta, sq);
        this.defaultAnswer = sq.getDefaultAnswer();
    }

    public String getDefaultAnswer() {
        return this.defaultAnswer;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

