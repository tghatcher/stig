/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.mitre.ocil._1.CompoundTestActionType;
import org.mitre.ocil._1.QuestionTestActionType;
import org.mitre.ocil._1.QuestionType;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "ItemBaseType", propOrder = { "notes" })
@XmlSeeAlso(value = { QuestionTestActionType.class, QuestionType.class,
		CompoundTestActionType.class })
public class ItemBaseType {
	protected List<String> notes;

	public List<String> getNotes() {
		if (this.notes == null) {
			this.notes = new ArrayList<String>();
		}
		return this.notes;
	}
}
