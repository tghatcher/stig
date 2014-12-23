/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.OCIL;

import stigviewergui.OCIL.ResultAction;

public class Choice {
	private String text;
	private ResultAction resultAction;

	public String getText() {
		return this.text;
	}

	public void setText(String description) {
		this.text = description;
	}

	public ResultAction getResultAction() {
		return this.resultAction;
	}

	public void setResultAction(ResultAction resultAction) {
		this.resultAction = resultAction;
	}

	public String toString() {
		return this.text;
	}
}
