/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import ASR.ConfidentialityLevelList;
import ASR.DeviceRecordType;
import ASR.FIPS199ImpactLevelList;
import ASR.ObjectRecordType;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "ResultValueType", propOrder = { "deviceRecord", "objectRecord" })
public class ResultValueType {
	protected List<DeviceRecordType> deviceRecord;
	protected List<ObjectRecordType> objectRecord;
	@XmlAttribute(name = "count", required = 1)
	@XmlSchemaType(name = "nonNegativeInteger")
	protected BigInteger count;
	@XmlAttribute(name = "macLevel")
	protected BigInteger macLevel;
	@XmlAttribute(name = "confLevel")
	protected ConfidentialityLevelList confLevel;
	@XmlAttribute(name = "fipsConf")
	protected FIPS199ImpactLevelList fipsConf;
	@XmlAttribute(name = "fipsAvail")
	protected FIPS199ImpactLevelList fipsAvail;
	@XmlAttribute(name = "fipsInteg")
	protected FIPS199ImpactLevelList fipsInteg;
	@XmlAttribute(name = "organization")
	protected String organization;
	@XmlAttribute(name = "region")
	protected String region;
	@XmlAttribute(name = "location")
	protected String location;
	@XmlAttribute(name = "domain")
	@XmlSchemaType(name = "anyURI")
	protected String domain;
	@XmlAttribute(name = "role")
	protected String role;
	@XmlAttribute(name = "function")
	protected String function;
	@XmlAttribute(name = "profile")
	protected String profile;
	@XmlAttribute(name = "opsAttrID")
	protected String opsAttrID;
	@XmlAttribute(name = "opsAttrResource")
	@XmlSchemaType(name = "anyURI")
	protected String opsAttrResource;

	public List<DeviceRecordType> getDeviceRecord() {
		if (this.deviceRecord == null) {
			this.deviceRecord = new ArrayList<DeviceRecordType>();
		}
		return this.deviceRecord;
	}

	public List<ObjectRecordType> getObjectRecord() {
		if (this.objectRecord == null) {
			this.objectRecord = new ArrayList<ObjectRecordType>();
		}
		return this.objectRecord;
	}

	public BigInteger getCount() {
		return this.count;
	}

	public void setCount(BigInteger value) {
		this.count = value;
	}

	public BigInteger getMacLevel() {
		return this.macLevel;
	}

	public void setMacLevel(BigInteger value) {
		this.macLevel = value;
	}

	public ConfidentialityLevelList getConfLevel() {
		return this.confLevel;
	}

	public void setConfLevel(ConfidentialityLevelList value) {
		this.confLevel = value;
	}

	public FIPS199ImpactLevelList getFipsConf() {
		return this.fipsConf;
	}

	public void setFipsConf(FIPS199ImpactLevelList value) {
		this.fipsConf = value;
	}

	public FIPS199ImpactLevelList getFipsAvail() {
		return this.fipsAvail;
	}

	public void setFipsAvail(FIPS199ImpactLevelList value) {
		this.fipsAvail = value;
	}

	public FIPS199ImpactLevelList getFipsInteg() {
		return this.fipsInteg;
	}

	public void setFipsInteg(FIPS199ImpactLevelList value) {
		this.fipsInteg = value;
	}

	public String getOrganization() {
		return this.organization;
	}

	public void setOrganization(String value) {
		this.organization = value;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String value) {
		this.region = value;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String value) {
		this.location = value;
	}

	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String value) {
		this.domain = value;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String value) {
		this.role = value;
	}

	public String getFunction() {
		return this.function;
	}

	public void setFunction(String value) {
		this.function = value;
	}

	public String getProfile() {
		return this.profile;
	}

	public void setProfile(String value) {
		this.profile = value;
	}

	public String getOpsAttrID() {
		return this.opsAttrID;
	}

	public void setOpsAttrID(String value) {
		this.opsAttrID = value;
	}

	public String getOpsAttrResource() {
		return this.opsAttrResource;
	}

	public void setOpsAttrResource(String value) {
		this.opsAttrResource = value;
	}
}
