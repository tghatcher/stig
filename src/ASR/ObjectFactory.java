/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import ASR.BenchmarkComplianceItemType;
import ASR.BenchmarkResultsType;
import ASR.BenchmarkRuleResultItemType;
import ASR.BenchmarkRuleResultsType;
import ASR.BenchmarkStatsType;
import ASR.CPEComplexResultItemType;
import ASR.CPEComplexResultsType;
import ASR.CPEResultItemType;
import ASR.CPEResultsType;
import ASR.CVEResultItemType;
import ASR.CVEResultsType;
import ASR.ComplexIdentType;
import ASR.ComplexInventoryFindingType;
import ASR.ContentType;
import ASR.DeviceListRecordType;
import ASR.DeviceRecordType;
import ASR.EntityIdentifierType;
import ASR.FactRefType;
import ASR.IPAddressType;
import ASR.LogicalTestType;
import ASR.ObjectListRecordType;
import ASR.ObjectRecordType;
import ASR.PlatformType;
import ASR.ResultPopulationCharacteristicsType;
import ASR.ResultValueType;
import ASR.ResultsPackageType;
import ASR.ScanDataIDType;
import ASR.ScanDataType;
import ASR.ScannerType;
import ASR.SysIdentResultItemType;
import ASR.SystemExtendedStringType;
import ASR.SystemIdentResultsType;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlRegistry
public class ObjectFactory {
	private static final QName _Content_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41",
			"Content");
	private static final QName _SystemIdentResults_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41",
			"SystemIdentResults");
	private static final QName _BenchmarkRuleResults_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41",
			"BenchmarkRuleResults");
	private static final QName _ResultPopulationCharacteristics_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41",
			"ResultPopulationCharacteristics");
	private static final QName _DeviceRecord_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41",
			"DeviceRecord");
	private static final QName _ObjectListRecord_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41",
			"ObjectListRecord");
	private static final QName _ScanDataID_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41",
			"ScanDataID");
	private static final QName _BenchmarkResults_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41",
			"BenchmarkResults");
	private static final QName _DeviceListRecord_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41",
			"DeviceListRecord");
	private static final QName _LogicalTest_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41",
			"LogicalTest");
	private static final QName _CVEResultItem_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41",
			"CVEResultItem");
	private static final QName _CVEResults_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41",
			"CVEResults");
	private static final QName _EntityIdentifier_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/cnd-core/0.41",
			"EntityIdentifier");
	private static final QName _BenchmarkComplianceItem_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41",
			"BenchmarkComplianceItem");
	private static final QName _FactRef_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41",
			"FactRef");
	private static final QName _Scanner_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41",
			"Scanner");
	private static final QName _ComplexInventoryFinding_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41",
			"ComplexInventoryFinding");
	private static final QName _ComplexIdent_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41",
			"ComplexIdent");
	private static final QName _CPEResultItem_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41",
			"CPEResultItem");
	private static final QName _Platform_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41",
			"Platform");
	private static final QName _CPEResults_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41",
			"CPEResults");
	private static final QName _ScanData_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41",
			"ScanData");
	private static final QName _BenchmarkRuleResultItem_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41",
			"BenchmarkRuleResultItem");
	private static final QName _CPEComplexResults_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41",
			"CPEComplexResults");
	private static final QName _BenchmarkStats_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41",
			"BenchmarkStats");
	private static final QName _SysIdentResultItem_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41",
			"SysIdentResultItem");
	private static final QName _ObjectRecord_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41",
			"ObjectRecord");
	private static final QName _IPAddress_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/cnd-core/0.41",
			"IPAddress");
	private static final QName _ResultValue_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41",
			"ResultValue");
	private static final QName _CPEComplexResultItem_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41",
			"CPEComplexResultItem");

	public CPEComplexResultItemType createCPEComplexResultItemType() {
		return new CPEComplexResultItemType();
	}

	public ScanDataIDType createScanDataIDType() {
		return new ScanDataIDType();
	}

	public CVEResultsType createCVEResultsType() {
		return new CVEResultsType();
	}

	public SystemIdentResultsType createSystemIdentResultsType() {
		return new SystemIdentResultsType();
	}

	public BenchmarkComplianceItemType createBenchmarkComplianceItemType() {
		return new BenchmarkComplianceItemType();
	}

	public CPEResultsType createCPEResultsType() {
		return new CPEResultsType();
	}

	public DeviceRecordType createDeviceRecordType() {
		return new DeviceRecordType();
	}

	public BenchmarkRuleResultItemType createBenchmarkRuleResultItemType() {
		return new BenchmarkRuleResultItemType();
	}

	public ScanDataType createScanDataType() {
		return new ScanDataType();
	}

	public SystemExtendedStringType createSystemExtendedStringType() {
		return new SystemExtendedStringType();
	}

	public ResultValueType createResultValueType() {
		return new ResultValueType();
	}

	public ComplexIdentType createComplexIdentType() {
		return new ComplexIdentType();
	}

	public BenchmarkResultsType createBenchmarkResultsType() {
		return new BenchmarkResultsType();
	}

	public BenchmarkRuleResultsType createBenchmarkRuleResultsType() {
		return new BenchmarkRuleResultsType();
	}

	public DeviceListRecordType createDeviceListRecordType() {
		return new DeviceListRecordType();
	}

	public ObjectRecordType createObjectRecordType() {
		return new ObjectRecordType();
	}

	public FactRefType createFactRefType() {
		return new FactRefType();
	}

	public IPAddressType createIPAddressType() {
		return new IPAddressType();
	}

	public CPEComplexResultsType createCPEComplexResultsType() {
		return new CPEComplexResultsType();
	}

	public LogicalTestType createLogicalTestType() {
		return new LogicalTestType();
	}

	public CVEResultItemType createCVEResultItemType() {
		return new CVEResultItemType();
	}

	public BenchmarkStatsType createBenchmarkStatsType() {
		return new BenchmarkStatsType();
	}

	public ComplexInventoryFindingType createComplexInventoryFindingType() {
		return new ComplexInventoryFindingType();
	}

	public ResultsPackageType createResultsPackageType() {
		return new ResultsPackageType();
	}

	public SysIdentResultItemType createSysIdentResultItemType() {
		return new SysIdentResultItemType();
	}

	public ResultPopulationCharacteristicsType createResultPopulationCharacteristicsType() {
		return new ResultPopulationCharacteristicsType();
	}

	public ContentType createContentType() {
		return new ContentType();
	}

	public CPEResultItemType createCPEResultItemType() {
		return new CPEResultItemType();
	}

	public ObjectListRecordType createObjectListRecordType() {
		return new ObjectListRecordType();
	}

	public EntityIdentifierType createEntityIdentifierType() {
		return new EntityIdentifierType();
	}

	public PlatformType createPlatformType() {
		return new PlatformType();
	}

	public ScannerType createScannerType() {
		return new ScannerType();
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41", name = "Content")
	public JAXBElement<ContentType> createContent(ContentType value) {
		return new JAXBElement<ContentType>(_Content_QNAME, ContentType.class,
				null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41", name = "SystemIdentResults")
	public JAXBElement<SystemIdentResultsType> createSystemIdentResults(
			SystemIdentResultsType value) {
		return new JAXBElement<SystemIdentResultsType>(
				_SystemIdentResults_QNAME, SystemIdentResultsType.class, null,
				value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41", name = "BenchmarkRuleResults")
	public JAXBElement<BenchmarkRuleResultsType> createBenchmarkRuleResults(
			BenchmarkRuleResultsType value) {
		return new JAXBElement<BenchmarkRuleResultsType>(
				_BenchmarkRuleResults_QNAME, BenchmarkRuleResultsType.class,
				null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41", name = "ResultPopulationCharacteristics")
	public JAXBElement<ResultPopulationCharacteristicsType> createResultPopulationCharacteristics(
			ResultPopulationCharacteristicsType value) {
		return new JAXBElement<ResultPopulationCharacteristicsType>(
				_ResultPopulationCharacteristics_QNAME,
				ResultPopulationCharacteristicsType.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41", name = "DeviceRecord")
	public JAXBElement<DeviceRecordType> createDeviceRecord(
			DeviceRecordType value) {
		return new JAXBElement<DeviceRecordType>(_DeviceRecord_QNAME,
				DeviceRecordType.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41", name = "ObjectListRecord")
	public JAXBElement<ObjectListRecordType> createObjectListRecord(
			ObjectListRecordType value) {
		return new JAXBElement<ObjectListRecordType>(_ObjectListRecord_QNAME,
				ObjectListRecordType.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41", name = "ScanDataID")
	public JAXBElement<ScanDataIDType> createScanDataID(ScanDataIDType value) {
		return new JAXBElement<ScanDataIDType>(_ScanDataID_QNAME,
				ScanDataIDType.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41", name = "BenchmarkResults")
	public JAXBElement<BenchmarkResultsType> createBenchmarkResults(
			BenchmarkResultsType value) {
		return new JAXBElement<BenchmarkResultsType>(_BenchmarkResults_QNAME,
				BenchmarkResultsType.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41", name = "DeviceListRecord")
	public JAXBElement<DeviceListRecordType> createDeviceListRecord(
			DeviceListRecordType value) {
		return new JAXBElement<DeviceListRecordType>(_DeviceListRecord_QNAME,
				DeviceListRecordType.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41", name = "LogicalTest")
	public JAXBElement<LogicalTestType> createLogicalTest(LogicalTestType value) {
		return new JAXBElement<LogicalTestType>(_LogicalTest_QNAME,
				LogicalTestType.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41", name = "CVEResultItem")
	public JAXBElement<CVEResultItemType> createCVEResultItem(
			CVEResultItemType value) {
		return new JAXBElement<CVEResultItemType>(_CVEResultItem_QNAME,
				CVEResultItemType.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41", name = "CVEResults")
	public JAXBElement<CVEResultsType> createCVEResults(CVEResultsType value) {
		return new JAXBElement<CVEResultsType>(_CVEResults_QNAME,
				CVEResultsType.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/cnd-core/0.41", name = "EntityIdentifier")
	public JAXBElement<EntityIdentifierType> createEntityIdentifier(
			EntityIdentifierType value) {
		return new JAXBElement<EntityIdentifierType>(_EntityIdentifier_QNAME,
				EntityIdentifierType.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41", name = "BenchmarkComplianceItem")
	public JAXBElement<BenchmarkComplianceItemType> createBenchmarkComplianceItem(
			BenchmarkComplianceItemType value) {
		return new JAXBElement<BenchmarkComplianceItemType>(
				_BenchmarkComplianceItem_QNAME,
				BenchmarkComplianceItemType.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41", name = "FactRef")
	public JAXBElement<FactRefType> createFactRef(FactRefType value) {
		return new JAXBElement<FactRefType>(_FactRef_QNAME, FactRefType.class,
				null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41", name = "Scanner")
	public JAXBElement<ScannerType> createScanner(ScannerType value) {
		return new JAXBElement<ScannerType>(_Scanner_QNAME, ScannerType.class,
				null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41", name = "ComplexInventoryFinding")
	public JAXBElement<ComplexInventoryFindingType> createComplexInventoryFinding(
			ComplexInventoryFindingType value) {
		return new JAXBElement<ComplexInventoryFindingType>(
				_ComplexInventoryFinding_QNAME,
				ComplexInventoryFindingType.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41", name = "ComplexIdent")
	public JAXBElement<ComplexIdentType> createComplexIdent(
			ComplexIdentType value) {
		return new JAXBElement<ComplexIdentType>(_ComplexIdent_QNAME,
				ComplexIdentType.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41", name = "CPEResultItem")
	public JAXBElement<CPEResultItemType> createCPEResultItem(
			CPEResultItemType value) {
		return new JAXBElement<CPEResultItemType>(_CPEResultItem_QNAME,
				CPEResultItemType.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41", name = "Platform")
	public JAXBElement<PlatformType> createPlatform(PlatformType value) {
		return new JAXBElement<PlatformType>(_Platform_QNAME,
				PlatformType.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41", name = "CPEResults")
	public JAXBElement<CPEResultsType> createCPEResults(CPEResultsType value) {
		return new JAXBElement<CPEResultsType>(_CPEResults_QNAME,
				CPEResultsType.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41", name = "ScanData")
	public JAXBElement<ScanDataType> createScanData(ScanDataType value) {
		return new JAXBElement<ScanDataType>(_ScanData_QNAME,
				ScanDataType.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41", name = "BenchmarkRuleResultItem")
	public JAXBElement<BenchmarkRuleResultItemType> createBenchmarkRuleResultItem(
			BenchmarkRuleResultItemType value) {
		return new JAXBElement<BenchmarkRuleResultItemType>(
				_BenchmarkRuleResultItem_QNAME,
				BenchmarkRuleResultItemType.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41", name = "CPEComplexResults")
	public JAXBElement<CPEComplexResultsType> createCPEComplexResults(
			CPEComplexResultsType value) {
		return new JAXBElement<CPEComplexResultsType>(_CPEComplexResults_QNAME,
				CPEComplexResultsType.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41", name = "BenchmarkStats")
	public JAXBElement<BenchmarkStatsType> createBenchmarkStats(
			BenchmarkStatsType value) {
		return new JAXBElement<BenchmarkStatsType>(_BenchmarkStats_QNAME,
				BenchmarkStatsType.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41", name = "SysIdentResultItem")
	public JAXBElement<SysIdentResultItemType> createSysIdentResultItem(
			SysIdentResultItemType value) {
		return new JAXBElement<SysIdentResultItemType>(
				_SysIdentResultItem_QNAME, SysIdentResultItemType.class, null,
				value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41", name = "ObjectRecord")
	public JAXBElement<ObjectRecordType> createObjectRecord(
			ObjectRecordType value) {
		return new JAXBElement<ObjectRecordType>(_ObjectRecord_QNAME,
				ObjectRecordType.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/cnd-core/0.41", name = "IPAddress")
	public JAXBElement<IPAddressType> createIPAddress(IPAddressType value) {
		return new JAXBElement<IPAddressType>(_IPAddress_QNAME,
				IPAddressType.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41", name = "ResultValue")
	public JAXBElement<ResultValueType> createResultValue(ResultValueType value) {
		return new JAXBElement<ResultValueType>(_ResultValue_QNAME,
				ResultValueType.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41", name = "CPEComplexResultItem")
	public JAXBElement<CPEComplexResultItemType> createCPEComplexResultItem(
			CPEComplexResultItemType value) {
		return new JAXBElement<CPEComplexResultItemType>(
				_CPEComplexResultItem_QNAME, CPEComplexResultItemType.class,
				null, value);
	}
}
