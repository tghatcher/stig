/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.mitre.ocil._1.CompoundTestActionType;
import org.mitre.ocil._1.PriorityType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "questionnaire")
public class Questionnaire extends CompoundTestActionType {
	@XmlAttribute(name = "id", required = 1)
	protected String id;
	@XmlAttribute(name = "priority")
	protected PriorityType priority;
	@XmlAttribute(name = "child_only")
	protected Boolean childOnly;

	public String getId() {
		return this.id;
	}

	public void setId(String value) {
		this.id = value;
	}

	public PriorityType getPriority() {
		if (this.priority == null) {
			return PriorityType.LOW;
		}
		return this.priority;
	}

	public void setPriority(PriorityType value) {
		this.priority = value;
	}

	public boolean isChildOnly() {
		if (this.childOnly == null) {
			return false;
		}
		return this.childOnly;
	}

	public void setChildOnly(Boolean value) {
		this.childOnly = value;
	}
}
