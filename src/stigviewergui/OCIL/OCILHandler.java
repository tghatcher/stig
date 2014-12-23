/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.OCIL;

import stigviewergui.OCIL.BooleanQuestion;
import stigviewergui.OCIL.ChoiceQuestion;
import stigviewergui.OCIL.NumericQuestion;
import stigviewergui.OCIL.StringQuestion;

public interface OCILHandler {
	public void startBooleanQuestion(BooleanQuestion var1);

	public void startChoiceQuestion(ChoiceQuestion var1);

	public void startNumericQuestion(NumericQuestion var1);

	public void startStringQuestion(StringQuestion var1);

	public void endQuestionnaire();
}
