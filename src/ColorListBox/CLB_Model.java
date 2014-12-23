/*
 * Decompiled with CFR 0_92.
 */
package ColorListBox;

import java.awt.Color;

public class CLB_Model {
	public String sMemberName;
	protected int iSize;
	protected Color BackColor;
	protected Color TextColor;

	public CLB_Model(String sName, Color clBackColor, Color clTextColor) {
		this.sMemberName = sName;
		this.BackColor = clBackColor;
		this.TextColor = clTextColor;
	}

	public CLB_Model(String sName) {
		this.sMemberName = sName;
		this.BackColor = Color.white;
		this.TextColor = Color.black;
	}

	public void SetBackColor(Color cl) {
		this.BackColor = cl;
	}

	public void SetTextColor(Color cl) {
		this.TextColor = cl;
	}

	public void SetColors(Color clBack, Color clText) {
		this.SetBackColor(clBack);
		this.SetTextColor(clText);
	}

	public String toString() {
		return this.sMemberName;
	}
}
