/*
 * Decompiled with CFR 0_92.
 */
package VMSASR;

import VMSASR.Result;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "result" })
@XmlRootElement(name = "ruleComplianceItem")
public class RuleComplianceItem {
	@XmlElement(required = 1)
	protected Result result;
	@XmlAttribute(name = "ruleResult", required = 1)
	protected String ruleResult;

	public Result getResult() {
		return this.result;
	}

	public void setResult(Result value) {
		this.result = value;
	}

	public String getRuleResult() {
		return this.ruleResult;
	}

	public void setRuleResult(String value) {
		this.ruleResult = value;
	}
}
