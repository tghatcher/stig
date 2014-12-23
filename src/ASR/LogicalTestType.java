/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import ASR.FactRefType;
import ASR.OperatorEnumeration;
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
@XmlType(name = "LogicalTestType", propOrder = { "logicalTest", "factRef" })
public class LogicalTestType {
	@XmlElement(name = "logical-test")
	protected List<LogicalTestType> logicalTest;
	@XmlElement(name = "fact-ref")
	protected List<FactRefType> factRef;
	@XmlAttribute(name = "operator", required = 1)
	protected OperatorEnumeration operator;
	@XmlAttribute(name = "negate", required = 1)
	protected boolean negate;

	public List<LogicalTestType> getLogicalTest() {
		if (this.logicalTest == null) {
			this.logicalTest = new ArrayList<LogicalTestType>();
		}
		return this.logicalTest;
	}

	public List<FactRefType> getFactRef() {
		if (this.factRef == null) {
			this.factRef = new ArrayList<FactRefType>();
		}
		return this.factRef;
	}

	public OperatorEnumeration getOperator() {
		return this.operator;
	}

	public void setOperator(OperatorEnumeration value) {
		this.operator = value;
	}

	public boolean isNegate() {
		return this.negate;
	}

	public void setNegate(boolean value) {
		this.negate = value;
	}
}
