/*
 * Decompiled with CFR 0_92.
 */
package VMSASR;

import VMSASR.RuleComplianceItem;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "ruleComplianceItem" })
@XmlRootElement(name = "ruleResult")
public class RuleResult {
	@XmlElement(required = 1)
	protected List<RuleComplianceItem> ruleComplianceItem;
	@XmlAttribute(name = "ruleID", required = 1)
	protected String ruleID;

	public List<RuleComplianceItem> getRuleComplianceItem() {
		if (this.ruleComplianceItem == null) {
			this.ruleComplianceItem = new ArrayList<RuleComplianceItem>();
		}
		return this.ruleComplianceItem;
	}

	public String getRuleID() {
		return this.ruleID;
	}

	public void setRuleID(String value) {
		this.ruleID = value;
	}
}
