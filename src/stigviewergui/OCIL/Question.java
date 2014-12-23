/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.OCIL;

import java.util.List;
import org.mitre.ocil._1.Instructions;
import org.mitre.ocil._1.QuestionTestActionType;
import org.mitre.ocil._1.QuestionType;
import org.mitre.ocil._1.TextType;
import stigviewergui.OCIL.Instructions;
import stigviewergui.OCIL.ItemBaseType;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class Question extends ItemBaseType {
	protected String title = "";
	protected String response = "";
	protected List<String> questionText = null;
	protected stigviewergui.OCIL.Instructions instructions = null;

	Question() {
	}

	Question(QuestionTestActionType qtat, QuestionType qt) {
		this.id = qt.getId();
		this.notes = qtat.getNotes();
		if (qtat.getTitle() != null && qtat.getTitle().getValue() != null) {
			this.title = qtat.getTitle().getValue();
		}
		this.questionText = qt.getQuestionText();
		this.instructions = new stigviewergui.OCIL.Instructions(
				qt.getInstructions());
	}

	public String getTitle() {
		return this.title;
	}

	public stigviewergui.OCIL.Instructions getInstructions() {
		return this.instructions;
	}

	public List<String> getQuestionText() {
		return this.questionText;
	}

	public String getResponse() {
		return this.response;
	}

	public void setResponse(String answer) {
		this.response = answer;
	}
}
