/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.mitre.ocil._1.OperatorType;
import org.mitre.ocil._1.PriorityType;
import org.mitre.ocil._1.TestActionRef;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "OperationType", propOrder = { "testActionRef" })
public class OperationType {
	@XmlElement(name = "test_action_ref", required = 1)
	protected List<TestActionRef> testActionRef;
	@XmlAttribute(name = "operation")
	protected OperatorType operation;
	@XmlAttribute(name = "negate")
	protected Boolean negate;
	@XmlAttribute(name = "priority")
	protected PriorityType priority;

	public List<TestActionRef> getTestActionRef() {
		if (this.testActionRef == null) {
			this.testActionRef = new ArrayList<TestActionRef>();
		}
		return this.testActionRef;
	}

	public OperatorType getOperation() {
		if (this.operation == null) {
			return OperatorType.AND;
		}
		return this.operation;
	}

	public void setOperation(OperatorType value) {
		this.operation = value;
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
