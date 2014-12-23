/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.mitre.ocil._1.ItemBaseType;
import org.mitre.ocil._1.OperationType;
import org.mitre.ocil._1.Questionnaire;
import org.mitre.ocil._1.ReferenceType;
import org.mitre.ocil._1.TextType;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "CompoundTestActionType", propOrder = { "title", "description",
		"reference", "actions" })
@XmlSeeAlso(value = { Questionnaire.class })
public class CompoundTestActionType extends ItemBaseType {
	protected TextType title;
	protected TextType description;
	protected List<ReferenceType> reference;
	@XmlElement(required = 1)
	protected OperationType actions;

	public TextType getTitle() {
		return this.title;
	}

	public void setTitle(TextType value) {
		this.title = value;
	}

	public TextType getDescription() {
		return this.description;
	}

	public void setDescription(TextType value) {
		this.description = value;
	}

	public List<ReferenceType> getReference() {
		if (this.reference == null) {
			this.reference = new ArrayList<ReferenceType>();
		}
		return this.reference;
	}

	public OperationType getActions() {
		return this.actions;
	}

	public void setActions(OperationType value) {
		this.actions = value;
	}
}
