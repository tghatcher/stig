/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.OCIL;

import java.util.ArrayList;
import java.util.List;
import org.mitre.ocil._1.ChoiceQuestion;
import org.mitre.ocil._1.ChoiceQuestionTestAction;
import org.mitre.ocil._1.QuestionTestActionType;
import stigviewergui.OCIL.ChoiceQuestion;
import stigviewergui.OCIL.Question;
import stigviewergui.OCIL.ResultAction;
import stigviewergui.OCIL.TestAction;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class stigviewergui.OCIL.ChoiceQuestionTestAction
extends TestAction {
    private List<ResultAction> whenChoices = new ArrayList<ResultAction>();
    private List<String> choiceIDs = new ArrayList<String>();

    stigviewergui.OCIL.ChoiceQuestionTestAction(ChoiceQuestionTestAction cqta, ChoiceQuestion cq) {
        super(cqta);
        this.question = new stigviewergui.OCIL.ChoiceQuestion(cqta, cq);
    }

    public List<ResultAction> getWhenChoices() {
        return this.whenChoices;
    }

    public List<String> getChoiceIDs() {
        return this.choiceIDs;
    }
}

