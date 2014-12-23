/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.OCIL;

import java.util.ArrayList;
import java.util.List;
import org.mitre.ocil._1.Instructions;
import org.mitre.ocil._1.StepType;
import org.mitre.ocil._1.TextType;
import stigviewergui.OCIL.Step;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class stigviewergui.OCIL.Instructions {
    private String title = "";
    private List<Step> steps = new ArrayList<Step>();

    stigviewergui.OCIL.Instructions(Instructions i) {
        if (i == null) {
            return;
        }
        this.title = i.getTitle().getValue();
        for (StepType step : i.getStep()) {
            this.steps.add(new Step(step));
        }
    }

    public List<Step> getSteps() {
        return this.steps;
    }

    public String getTitle() {
        return this.title;
    }
}

