/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import ASR.BenchmarkRuleResultItemType;
import ASR.SystemExtendedStringType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "BenchmarkRuleResultsType", propOrder = { "ident",
		"ruleComplianceItem" })
public class BenchmarkRuleResultsType {
	protected List<SystemExtendedStringType> ident;
	@XmlElement(required = 1)
	protected List<BenchmarkRuleResultItemType> ruleComplianceItem;
	@XmlAttribute(name = "ruleID", required = 1)
	protected String ruleID;

	public List<SystemExtendedStringType> getIdent() {
		if (this.ident == null) {
			this.ident = new ArrayList<SystemExtendedStringType>();
		}
		return this.ident;
	}

	public List<BenchmarkRuleResultItemType> getRuleComplianceItem() {
		if (this.ruleComplianceItem == null) {
			this.ruleComplianceItem = new ArrayList<BenchmarkRuleResultItemType>();
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
