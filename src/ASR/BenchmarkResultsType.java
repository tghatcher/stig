/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import ASR.BenchmarkComplianceItemType;
import ASR.BenchmarkRuleResultsType;
import ASR.BenchmarkStatsType;
import ASR.EntityIdentifierType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "BenchmarkResultsType", propOrder = { "benchMarkID",
		"benchmarkStats", "benchmarkComplianceItem", "ruleResult" })
public class BenchmarkResultsType {
	@XmlElement(required = 1)
	protected EntityIdentifierType benchMarkID;
	protected List<BenchmarkStatsType> benchmarkStats;
	protected List<BenchmarkComplianceItemType> benchmarkComplianceItem;
	protected List<BenchmarkRuleResultsType> ruleResult;
	@XmlAttribute(name = "profile")
	protected String profile;
	@XmlAttribute(name = "version")
	protected String version;
	@XmlAttribute(name = "benchmarkPubDate")
	@XmlSchemaType(name = "date")
	protected XMLGregorianCalendar benchmarkPubDate;

	public EntityIdentifierType getBenchMarkID() {
		return this.benchMarkID;
	}

	public void setBenchMarkID(EntityIdentifierType value) {
		this.benchMarkID = value;
	}

	public List<BenchmarkStatsType> getBenchmarkStats() {
		if (this.benchmarkStats == null) {
			this.benchmarkStats = new ArrayList<BenchmarkStatsType>();
		}
		return this.benchmarkStats;
	}

	public List<BenchmarkComplianceItemType> getBenchmarkComplianceItem() {
		if (this.benchmarkComplianceItem == null) {
			this.benchmarkComplianceItem = new ArrayList<BenchmarkComplianceItemType>();
		}
		return this.benchmarkComplianceItem;
	}

	public List<BenchmarkRuleResultsType> getRuleResult() {
		if (this.ruleResult == null) {
			this.ruleResult = new ArrayList<BenchmarkRuleResultsType>();
		}
		return this.ruleResult;
	}

	public String getProfile() {
		return this.profile;
	}

	public void setProfile(String value) {
		this.profile = value;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String value) {
		this.version = value;
	}

	public XMLGregorianCalendar getBenchmarkPubDate() {
		return this.benchmarkPubDate;
	}

	public void setBenchmarkPubDate(XMLGregorianCalendar value) {
		this.benchmarkPubDate = value;
	}
}
