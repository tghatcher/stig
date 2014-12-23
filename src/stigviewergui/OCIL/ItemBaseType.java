/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.OCIL;

import java.util.List;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class ItemBaseType {
	protected String id;
	protected List<String> notes;

	public String getId() {
		return this.id;
	}

	public List<String> getNotes() {
		return this.notes;
	}
}
