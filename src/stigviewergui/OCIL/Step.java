/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.OCIL;

import java.util.ArrayList;
import java.util.List;
import org.mitre.ocil._1.ReferenceType;
import org.mitre.ocil._1.StepType;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Step {
	private String description;
	private List<Step> steps = new ArrayList<Step>();
	private List<String> references = new ArrayList<String>();

	Step(StepType s) {
		this.description = s.getDescription();
		for (StepType step : s.getStep()) {
			this.steps.add(new Step(step));
		}
		for (ReferenceType ref : s.getReference()) {
			this.references.add(ref.getHref());
		}
	}

	public String getDescription() {
		return this.description;
	}

	public List<Step> getSteps() {
		return this.steps;
	}

	public List<String> getReferences() {
		return this.references;
	}
}
