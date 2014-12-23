/*
 * Decompiled with CFR 0_92.
 */
package ARF;

import ARF.AssessmentReport;
import ARF.Device;
import ARF.ReportObject;
import ARF.TConfiguration;
import ARF.TConnectionIp;
import ARF.TDeviceID;
import ARF.TFQDN;
import ARF.THostNetworkData;
import ARF.TIdentifiers;
import ARF.TNetworkConfiguration;
import ARF.TRealm;
import ARF.TSubnetMask;
import ARF.TTaggedBoolean;
import ARF.TTaggedString;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlRegistry
public class ObjectFactory {
	private static final QName _HostName_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/shared_data/device/0.41",
			"host_name");
	private static final QName _ConnectionIp_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/shared_data/device/0.41",
			"connection_ip");
	private static final QName _Identifiers_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/shared_data/device/0.41",
			"identifiers");
	private static final QName _IPv4_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/cnd-core/0.41",
			"IPv4");
	private static final QName _TaggedString_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/shared_data/tagged_value/0.41",
			"taggedString");
	private static final QName _Resource_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/cnd-core/0.41",
			"resource");
	private static final QName _NetworkConfiguration_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/shared_data/device/0.41",
			"network_configuration");
	private static final QName _Configuration_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/shared_data/device/0.41",
			"configuration");
	private static final QName _TaggedBoolean_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/shared_data/tagged_value/0.41",
			"taggedBoolean");
	private static final QName _NetworkInterfaceID_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/shared_data/device/0.41",
			"network_interface_ID");
	private static final QName _FQDN_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/shared_data/device/0.41",
			"FQDN");
	private static final QName _RecordIdentifier_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/cnd-core/0.41",
			"record_identifier");
	private static final QName _ConnectionMacAddress_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/shared_data/device/0.41",
			"connection_mac_address");
	private static final QName _Realm_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/shared_data/device/0.41",
			"realm");
	private static final QName _HostNetworkData_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/shared_data/device/0.41",
			"host_network_data");
	private static final QName _SubnetMask_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/shared_data/device/0.41",
			"subnet_mask");
	private static final QName _DeviceID_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/shared_data/device/0.41",
			"device_ID");

	public THostNetworkData createTHostNetworkData() {
		return new THostNetworkData();
	}

	public TDeviceID createTDeviceID() {
		return new TDeviceID();
	}

	public TConfiguration createTConfiguration() {
		return new TConfiguration();
	}

	public ReportObject createReportObject() {
		return new ReportObject();
	}

	public TSubnetMask createTSubnetMask() {
		return new TSubnetMask();
	}

	public TNetworkConfiguration createTNetworkConfiguration() {
		return new TNetworkConfiguration();
	}

	public Device createDevice() {
		return new Device();
	}

	public TIdentifiers createTIdentifiers() {
		return new TIdentifiers();
	}

	public TTaggedString createTTaggedString() {
		return new TTaggedString();
	}

	public AssessmentReport createAssessmentReport() {
		return new AssessmentReport();
	}

	public TTaggedBoolean createTTaggedBoolean() {
		return new TTaggedBoolean();
	}

	public TConnectionIp createTConnectionIp() {
		return new TConnectionIp();
	}

	public TFQDN createTFQDN() {
		return new TFQDN();
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/device/0.41", name = "host_name")
	public JAXBElement<String> createHostName(String value) {
		return new JAXBElement<String>(_HostName_QNAME, String.class, null,
				value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/device/0.41", name = "connection_ip")
	public JAXBElement<TConnectionIp> createConnectionIp(TConnectionIp value) {
		return new JAXBElement<TConnectionIp>(_ConnectionIp_QNAME,
				TConnectionIp.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/device/0.41", name = "identifiers")
	public JAXBElement<TIdentifiers> createIdentifiers(TIdentifiers value) {
		return new JAXBElement<TIdentifiers>(_Identifiers_QNAME,
				TIdentifiers.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/cnd-core/0.41", name = "IPv4")
	public JAXBElement<String> createIPv4(String value) {
		return new JAXBElement<String>(_IPv4_QNAME, String.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/tagged_value/0.41", name = "taggedString")
	public JAXBElement<TTaggedString> createTaggedString(TTaggedString value) {
		return new JAXBElement<TTaggedString>(_TaggedString_QNAME,
				TTaggedString.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/cnd-core/0.41", name = "resource")
	public JAXBElement<String> createResource(String value) {
		return new JAXBElement<String>(_Resource_QNAME, String.class, null,
				value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/device/0.41", name = "network_configuration")
	public JAXBElement<TNetworkConfiguration> createNetworkConfiguration(
			TNetworkConfiguration value) {
		return new JAXBElement<TNetworkConfiguration>(
				_NetworkConfiguration_QNAME, TNetworkConfiguration.class, null,
				value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/device/0.41", name = "configuration")
	public JAXBElement<TConfiguration> createConfiguration(TConfiguration value) {
		return new JAXBElement<TConfiguration>(_Configuration_QNAME,
				TConfiguration.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/tagged_value/0.41", name = "taggedBoolean")
	public JAXBElement<TTaggedBoolean> createTaggedBoolean(TTaggedBoolean value) {
		return new JAXBElement<TTaggedBoolean>(_TaggedBoolean_QNAME,
				TTaggedBoolean.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/device/0.41", name = "network_interface_ID")
	public JAXBElement<String> createNetworkInterfaceID(String value) {
		return new JAXBElement<String>(_NetworkInterfaceID_QNAME, String.class,
				null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/device/0.41", name = "FQDN")
	public JAXBElement<TFQDN> createFQDN(TFQDN value) {
		return new JAXBElement<TFQDN>(_FQDN_QNAME, TFQDN.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/cnd-core/0.41", name = "record_identifier")
	public JAXBElement<String> createRecordIdentifier(String value) {
		return new JAXBElement<String>(_RecordIdentifier_QNAME, String.class,
				null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/device/0.41", name = "connection_mac_address")
	public JAXBElement<String> createConnectionMacAddress(String value) {
		return new JAXBElement<String>(_ConnectionMacAddress_QNAME,
				String.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/device/0.41", name = "realm")
	public JAXBElement<TRealm> createRealm(TRealm value) {
		return new JAXBElement<TRealm>(_Realm_QNAME, TRealm.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/device/0.41", name = "host_network_data")
	public JAXBElement<THostNetworkData> createHostNetworkData(
			THostNetworkData value) {
		return new JAXBElement<THostNetworkData>(_HostNetworkData_QNAME,
				THostNetworkData.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/device/0.41", name = "subnet_mask")
	public JAXBElement<TSubnetMask> createSubnetMask(TSubnetMask value) {
		return new JAXBElement<TSubnetMask>(_SubnetMask_QNAME,
				TSubnetMask.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/device/0.41", name = "device_ID")
	public JAXBElement<TDeviceID> createDeviceID(TDeviceID value) {
		return new JAXBElement<TDeviceID>(_DeviceID_QNAME, TDeviceID.class,
				null, value);
	}
}
