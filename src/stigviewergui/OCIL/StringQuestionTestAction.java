/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.OCIL;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mitre.ocil._1.QuestionTestActionType;
import org.mitre.ocil._1.StringQuestion;
import org.mitre.ocil._1.StringQuestionTestAction;
import stigviewergui.OCIL.Question;
import stigviewergui.OCIL.ResultAction;
import stigviewergui.OCIL.StringQuestion;
import stigviewergui.OCIL.TestAction;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class stigviewergui.OCIL.StringQuestionTestAction
extends TestAction {
    private List<PatternMatch> whenPatterns = new ArrayList<PatternMatch>();

    stigviewergui.OCIL.StringQuestionTestAction(StringQuestionTestAction sqta, StringQuestion sq) {
        super(sqta);
        this.question = new stigviewergui.OCIL.StringQuestion(sqta, sq);
    }

    public void addWhenPattern(List<Pattern> patternList, ResultAction resultAction) {
        this.whenPatterns.add(new PatternMatch(patternList, resultAction));
    }

    public List<PatternMatch> getPatternMatches() {
        return this.whenPatterns;
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public class PatternMatch {
        private List<Pattern> patternList;
        private ResultAction resultAction;

        PatternMatch(List<Pattern> patternList, ResultAction resultAction) {
            this.patternList = null;
            this.resultAction = null;
            this.patternList = patternList;
            this.resultAction = resultAction;
        }

        public boolean isMatch(String answer) {
            if (this.patternList == null) {
                return false;
            }
            for (Pattern pattern : this.patternList) {
                if (!pattern.matcher((CharSequence)answer).matches()) continue;
                return true;
            }
            return false;
        }

        public List<Pattern> getPattern() {
            if (this.patternList == null) {
                this.patternList = new ArrayList<Pattern>();
            }
            return this.patternList;
        }

        public void setPattern(List<Pattern> patternList) {
            this.patternList = patternList;
        }

        public ResultAction getResultAction() {
            return this.resultAction;
        }

        public void setResultAction(ResultAction resultAction) {
            this.resultAction = resultAction;
        }
    }

}

