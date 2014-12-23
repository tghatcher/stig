/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.mitre.ocil._1.TestActionRef;
import org.mitre.ocil._1.WhenChoice;
import org.mitre.ocil._1.WhenEquals;
import org.mitre.ocil._1.WhenPattern;
import org.mitre.ocil._1.WhenRange;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "ResultChoiceType", propOrder = { "result", "testActionRef" })
@XmlSeeAlso(value = { WhenEquals.class, WhenRange.class, WhenChoice.class,
		WhenPattern.class })
public class ResultChoiceType {
	protected String result;
	@XmlElement(name = "test_action_ref")
	protected TestActionRef testActionRef;

	public String getResult() {
		return this.result;
	}

	public void setResult(String value) {
		this.result = value;
	}

	public TestActionRef getTestActionRef() {
		return this.testActionRef;
	}

	public void setTestActionRef(TestActionRef value) {
		this.testActionRef = value;
	}
}
