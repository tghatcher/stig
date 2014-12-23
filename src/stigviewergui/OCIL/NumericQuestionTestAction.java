/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.OCIL;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.mitre.ocil._1.NumericQuestion;
import org.mitre.ocil._1.NumericQuestionTestAction;
import org.mitre.ocil._1.QuestionTestActionType;
import stigviewergui.OCIL.NumericQuestion;
import stigviewergui.OCIL.Question;
import stigviewergui.OCIL.ResultAction;
import stigviewergui.OCIL.TestAction;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class stigviewergui.OCIL.NumericQuestionTestAction
extends TestAction {
    List<ExactMatch> exactMatches = new ArrayList<ExactMatch>();
    List<RangeMatch> rangeMatches = new ArrayList<RangeMatch>();

    stigviewergui.OCIL.NumericQuestionTestAction(NumericQuestionTestAction nqta, NumericQuestion nq) {
        super(nqta);
        this.question = new stigviewergui.OCIL.NumericQuestion(nqta, nq);
    }

    public void addExactMatches(List<BigDecimal> values, ResultAction resultAction) {
        this.exactMatches.add(new ExactMatch(values, resultAction));
    }

    public void addRangeMatches(List<BigDecimal> mins, List<BigDecimal> maxes, ResultAction resultAction) {
        this.rangeMatches.add(new RangeMatch(mins, maxes, resultAction));
    }

    public List<ExactMatch> getExactMatches() {
        return this.exactMatches;
    }

    public List<RangeMatch> getRangeMatches() {
        return this.rangeMatches;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public class RangeMatch {
        private List<BigDecimal> mins;
        private List<BigDecimal> maxes;
        private ResultAction resultAction;

        public RangeMatch(List<BigDecimal> mins, List<BigDecimal> maxes, ResultAction resultAction) {
            this.mins = null;
            this.maxes = null;
            this.resultAction = null;
            this.mins = mins;
            this.maxes = maxes;
            this.resultAction = resultAction;
        }

        public boolean isMatch(BigDecimal answer) {
            if (this.mins == null || this.maxes == null) {
                return false;
            }
            for (int index = 0; index < this.mins.size(); ++index) {
                if (answer.compareTo(this.mins.get(index)) < 0 || answer.compareTo(this.maxes.get(index)) > 0) continue;
                return true;
            }
            return false;
        }

        public ResultAction getResultAction() {
            return this.resultAction;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public class ExactMatch {
        private List<BigDecimal> values;
        private ResultAction resultAction;

        ExactMatch(List<BigDecimal> values, ResultAction resultAction) {
            this.values = null;
            this.resultAction = null;
            this.values = values;
            this.resultAction = resultAction;
        }

        public boolean isMatch(BigDecimal answer) {
            if (this.values == null) {
                return false;
            }
            for (BigDecimal value : this.values) {
                if (!answer.equals(value)) continue;
                return true;
            }
            return false;
        }

        public ResultAction getResultAction() {
            return this.resultAction;
        }
    }

}

