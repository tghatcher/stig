/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import org.mitre.ocil._1.PriorityType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "value" })
@XmlRootElement(name = "test_action_ref")
public class TestActionRef {
	@XmlValue
	protected String value;
	@XmlAttribute(name = "negate")
	protected Boolean negate;
	@XmlAttribute(name = "priority")
	protected PriorityType priority;

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isNegate() {
		if (this.negate == null) {
			return false;
		}
		return this.negate;
	}

	public void setNegate(Boolean value) {
		this.negate = value;
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
}
