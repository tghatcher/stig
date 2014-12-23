/*
 * Decompiled with CFR 0_92.
 */
package VMSASR;

import VMSASR.BenchMarkID;
import VMSASR.RuleResult;
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
@XmlType(name = "", propOrder = { "benchMarkID", "ruleResult" })
@XmlRootElement(name = "benchmark")
public class Benchmark {
	@XmlElement(required = 1)
	protected BenchMarkID benchMarkID;
	@XmlElement(required = 1)
	protected List<RuleResult> ruleResult;
	@XmlAttribute(name = "version", required = 1)
	protected byte version;

	public BenchMarkID getBenchMarkID() {
		return this.benchMarkID;
	}

	public void setBenchMarkID(BenchMarkID value) {
		this.benchMarkID = value;
	}

	public List<RuleResult> getRuleResult() {
		if (this.ruleResult == null) {
			this.ruleResult = new ArrayList<RuleResult>();
		}
		return this.ruleResult;
	}

	public byte getVersion() {
		return this.version;
	}

	public void setVersion(byte value) {
		this.version = value;
	}
}
