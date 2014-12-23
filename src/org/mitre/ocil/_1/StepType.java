/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import org.mitre.ocil._1.ReferenceType;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "StepType", propOrder = { "description", "reference", "step" })
public class StepType {
	protected String description;
	protected List<ReferenceType> reference;
	protected List<StepType> step;
	@XmlAttribute(name = "is_done")
	protected Boolean isDone;
	@XmlAttribute(name = "is_required")
	protected Boolean isRequired;

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String value) {
		this.description = value;
	}

	public List<ReferenceType> getReference() {
		if (this.reference == null) {
			this.reference = new ArrayList<ReferenceType>();
		}
		return this.reference;
	}

	public List<StepType> getStep() {
		if (this.step == null) {
			this.step = new ArrayList<StepType>();
		}
		return this.step;
	}

	public boolean isIsDone() {
		if (this.isDone == null) {
			return false;
		}
		return this.isDone;
	}

	public void setIsDone(Boolean value) {
		this.isDone = value;
	}

	public boolean isIsRequired() {
		if (this.isRequired == null) {
			return true;
		}
		return this.isRequired;
	}

	public void setIsRequired(Boolean value) {
		this.isRequired = value;
	}
}
