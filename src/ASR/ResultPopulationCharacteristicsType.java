/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import ASR.PlatformType;
import ASR.ScanDataType;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "ResultPopulationCharacteristicsType", propOrder = { "cpeType",
		"resource", "orgResource", "regionResource", "locationResource",
		"opsAttrResource", "scanData" })
public class ResultPopulationCharacteristicsType {
	protected PlatformType cpeType;
	@XmlElement(required = 1)
	@XmlSchemaType(name = "anyURI")
	protected String resource;
	@XmlSchemaType(name = "anyURI")
	protected String orgResource;
	@XmlSchemaType(name = "anyURI")
	protected String regionResource;
	@XmlSchemaType(name = "anyURI")
	protected String locationResource;
	@XmlSchemaType(name = "anyURI")
	protected String opsAttrResource;
	protected ScanDataType scanData;
	@XmlAttribute(name = "populationSize", required = 1)
	@XmlSchemaType(name = "nonNegativeInteger")
	protected BigInteger populationSize;
	@XmlAttribute(name = "populationAppliesTo")
	@XmlSchemaType(name = "nonNegativeInteger")
	protected BigInteger populationAppliesTo;
	@XmlAttribute(name = "populationAssessed")
	@XmlSchemaType(name = "nonNegativeInteger")
	protected BigInteger populationAssessed;

	public PlatformType getCpeType() {
		return this.cpeType;
	}

	public void setCpeType(PlatformType value) {
		this.cpeType = value;
	}

	public String getResource() {
		return this.resource;
	}

	public void setResource(String value) {
		this.resource = value;
	}

	public String getOrgResource() {
		return this.orgResource;
	}

	public void setOrgResource(String value) {
		this.orgResource = value;
	}

	public String getRegionResource() {
		return this.regionResource;
	}

	public void setRegionResource(String value) {
		this.regionResource = value;
	}

	public String getLocationResource() {
		return this.locationResource;
	}

	public void setLocationResource(String value) {
		this.locationResource = value;
	}

	public String getOpsAttrResource() {
		return this.opsAttrResource;
	}

	public void setOpsAttrResource(String value) {
		this.opsAttrResource = value;
	}

	public ScanDataType getScanData() {
		return this.scanData;
	}

	public void setScanData(ScanDataType value) {
		this.scanData = value;
	}

	public BigInteger getPopulationSize() {
		return this.populationSize;
	}

	public void setPopulationSize(BigInteger value) {
		this.populationSize = value;
	}

	public BigInteger getPopulationAppliesTo() {
		return this.populationAppliesTo;
	}

	public void setPopulationAppliesTo(BigInteger value) {
		this.populationAppliesTo = value;
	}

	public BigInteger getPopulationAssessed() {
		return this.populationAssessed;
	}

	public void setPopulationAssessed(BigInteger value) {
		this.populationAssessed = value;
	}
}
