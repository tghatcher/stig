/*
 * Decompiled with CFR 0_92.
 */
package CheckListBox;

public class CHK_Model {
	public String sMemberName;
	protected int iSize;
	boolean bIsChecked;

	public CHK_Model(String sName, boolean bChecked) {
		this.sMemberName = sName;
		this.bIsChecked = bChecked;
	}

	public CHK_Model(String sName) {
		this.sMemberName = sName;
		this.bIsChecked = false;
	}

	public void setChecked() {
		this.bIsChecked = true;
	}

	public void setNotChecked() {
		this.bIsChecked = false;
	}

	public void Toggle() {
		this.bIsChecked = !this.bIsChecked;
	}

	public boolean IsChecked() {
		return this.bIsChecked;
	}

	public String toString() {
		return this.sMemberName;
	}
}
