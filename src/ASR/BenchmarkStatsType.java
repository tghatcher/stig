/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "BenchmarkStatsType")
public class BenchmarkStatsType {
	@XmlAttribute(name = "minScore")
	protected BigDecimal minScore;
	@XmlAttribute(name = "maxScore")
	protected BigDecimal maxScore;
	@XmlAttribute(name = "aveScore")
	protected BigDecimal aveScore;
	@XmlAttribute(name = "minPassScore")
	protected BigDecimal minPassScore;
	@XmlAttribute(name = "scoreType")
	@XmlSchemaType(name = "anyURI")
	protected String scoreType;

	public BigDecimal getMinScore() {
		return this.minScore;
	}

	public void setMinScore(BigDecimal value) {
		this.minScore = value;
	}

	public BigDecimal getMaxScore() {
		return this.maxScore;
	}

	public void setMaxScore(BigDecimal value) {
		this.maxScore = value;
	}

	public BigDecimal getAveScore() {
		return this.aveScore;
	}

	public void setAveScore(BigDecimal value) {
		this.aveScore = value;
	}

	public BigDecimal getMinPassScore() {
		return this.minPassScore;
	}

	public void setMinPassScore(BigDecimal value) {
		this.minPassScore = value;
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
