/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import ASR.ResultValueType;
import ASR.XCCDFResultEnumType;
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
@XmlType(name = "BenchmarkRuleResultItemType", propOrder = { "result" })
public class BenchmarkRuleResultItemType {
	@XmlElement(required = 1)
	protected List<ResultValueType> result;
	@XmlAttribute(name = "ruleResult", required = 1)
	protected XCCDFResultEnumType ruleResult;

	public List<ResultValueType> getResult() {
		if (this.result == null) {
			this.result = new ArrayList<ResultValueType>();
		}
		return this.result;
	}

	public XCCDFResultEnumType getRuleResult() {
		return this.ruleResult;
	}

	public void setRuleResult(XCCDFResultEnumType value) {
		this.ruleResult = value;
	}
}
