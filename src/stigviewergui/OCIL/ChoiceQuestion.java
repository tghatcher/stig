/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.OCIL;

import java.util.ArrayList;
import java.util.List;
import org.mitre.ocil._1.ChoiceQuestion;
import org.mitre.ocil._1.ChoiceQuestionTestAction;
import org.mitre.ocil._1.QuestionTestActionType;
import org.mitre.ocil._1.QuestionType;
import stigviewergui.OCIL.Question;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class stigviewergui.OCIL.ChoiceQuestion
extends Question {
    private List<String> choices = new ArrayList<String>();
    private Integer defaultAnswer = null;
    private String answer = "";

    stigviewergui.OCIL.ChoiceQuestion(ChoiceQuestionTestAction cqta, ChoiceQuestion cq) {
        super(cqta, cq);
    }

    public List<String> getChoices() {
        return this.choices;
    }

    public Integer getDefaultAnswer() {
        return this.defaultAnswer;
    }

    public void setDefaultAnswer(Integer defaultAnswer) {
        this.defaultAnswer = defaultAnswer;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

