/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.OCIL;

import java.math.BigDecimal;
import org.mitre.ocil._1.NumericQuestion;
import org.mitre.ocil._1.NumericQuestionTestAction;
import org.mitre.ocil._1.QuestionTestActionType;
import org.mitre.ocil._1.QuestionType;
import stigviewergui.OCIL.Question;

public class stigviewergui.OCIL.NumericQuestion
extends Question {
    private BigDecimal answer = null;
    private BigDecimal default_answer = null;

    stigviewergui.OCIL.NumericQuestion(NumericQuestionTestAction nqta, NumericQuestion nq) {
        super(nqta, nq);
        this.default_answer = nq.getDefaultAnswer();
    }

    public BigDecimal getDefault_answer() {
        return this.default_answer;
    }

    public BigDecimal getAnswer() {
        return this.answer;
    }

    public void setAnswer(BigDecimal answer) {
        this.answer = answer;
    }
}

