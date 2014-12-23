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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "BenchmarkComplianceItemType", propOrder = { "result" })
public class BenchmarkComplianceItemType {
	@XmlElement(required = 1)
	protected List<ResultValueType> result;
	@XmlAttribute(name = "benchmarkResultStatus", required = 1)
	protected XCCDFResultEnumType benchmarkResultStatus;
	@XmlAttribute(name = "scoreType")
	@XmlSchemaType(name = "anyURI")
	protected String scoreType;

	public List<ResultValueType> getResult() {
		if (this.result == null) {
			this.result = new ArrayList<ResultValueType>();
		}
		return this.result;
	}

	public XCCDFResultEnumType getBenchmarkResultStatus() {
		return this.benchmarkResultStatus;
	}

	public void setBenchmarkResultStatus(XCCDFResultEnumType value) {
		this.benchmarkResultStatus = value;
	}

	public String getScoreType() {
		if (this.scoreType == null) {
			return "default";
		}
		return this.scoreType;
	}

	public void setScoreType(String value) {
		this.scoreType = value;
	}
}
