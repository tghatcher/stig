/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import ASR.DeviceListRecordType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "DeviceRecordType")
@XmlSeeAlso(value = { DeviceListRecordType.class })
public class DeviceRecordType {
	@XmlAttribute(name = "ipv4Address")
	protected String ipv4Address;
	@XmlAttribute(name = "ipv6Address")
	protected String ipv6Address;
	@XmlAttribute(name = "macAddress")
	protected String macAddress;
	@XmlAttribute(name = "resource")
	@XmlSchemaType(name = "anyURI")
	protected String resource;
	@XmlAttribute(name = "record_identifier", required = 1)
	protected String recordIdentifier;
	@XmlAttribute(name = "realm")
	@XmlSchemaType(name = "anyURI")
	protected String realm;
	@XmlAttribute(name = "host_name")
	@XmlSchemaType(name = "anyURI")
	protected String hostName;
	@XmlAttribute(name = "osGUID")
	protected String osGUID;

	public String getIpv4Address() {
		return this.ipv4Address;
	}

	public void setIpv4Address(String value) {
		this.ipv4Address = value;
	}

	public String getIpv6Address() {
		return this.ipv6Address;
	}

	public void setIpv6Address(String value) {
		this.ipv6Address = value;
	}

	public String getMacAddress() {
		return this.macAddress;
	}

	public void setMacAddress(String value) {
		this.macAddress = value;
	}

	public String getResource() {
		return this.resource;
	}

	public void setResource(String value) {
		this.resource = value;
	}

	public String getRecordIdentifier() {
		return this.recordIdentifier;
	}

	public void setRecordIdentifier(String value) {
		this.recordIdentifier = value;
	}

	public String getRealm() {
		return this.realm;
	}

	public void setRealm(String value) {
		this.realm = value;
	}

	public String getHostName() {
		return this.hostName;
	}

	public void setHostName(String value) {
		this.hostName = value;
	}

	public String getOsGUID() {
		return this.osGUID;
	}

	public void setOsGUID(String value) {
		this.osGUID = value;
	}
}
