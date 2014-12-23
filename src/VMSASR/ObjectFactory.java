/*
 * Decompiled with CFR 0_92.
 */
package VMSASR;

import VMSASR.BenchMarkID;
import VMSASR.Benchmark;
import VMSASR.DeviceRecord;
import VMSASR.PopulationCharacteristics;
import VMSASR.PrResource;
import VMSASR.Result;
import VMSASR.ResultsPackage;
import VMSASR.RuleComplianceItem;
import VMSASR.RuleResult;
import VMSASR.ScanData;
import VMSASR.ScanDataID;
import VMSASR.Scanner;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlRegistry
public class ObjectFactory {
	private static final QName _End_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41",
			"end");
	private static final QName _RecordIdentifier_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/cnd-core/0.41",
			"record_identifier");
	private static final QName _ProductName_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41",
			"product_name");
	private static final QName _Start_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41",
			"start");
	private static final QName _ProductVersion_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41",
			"product_version");
	private static final QName _Resource_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/cnd-core/0.41",
			"resource");
	private static final QName _ExecutionLocation_QNAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41",
			"execution_location");

	public BenchMarkID createBenchMarkID() {
		return new BenchMarkID();
	}

	public ScanData createScanData() {
		return new ScanData();
	}

	public ResultsPackage createResultsPackage() {
		return new ResultsPackage();
	}

	public PopulationCharacteristics createPopulationCharacteristics() {
		return new PopulationCharacteristics();
	}

	public RuleResult createRuleResult() {
		return new RuleResult();
	}

	public Result createResult() {
		return new Result();
	}

	public Benchmark createBenchmark() {
		return new Benchmark();
	}

	public Scanner createScanner() {
		return new Scanner();
	}

	public ScanDataID createScanDataID() {
		return new ScanDataID();
	}

	public DeviceRecord createDeviceRecord() {
		return new DeviceRecord();
	}

	public RuleComplianceItem createRuleComplianceItem() {
		return new RuleComplianceItem();
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41", name = "resource")
	public PrResource createPrResource(String value) {
		return new PrResource(value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41", name = "end")
	public JAXBElement<String> createEnd(String value) {
		return new JAXBElement<String>(_End_QNAME, String.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/cnd-core/0.41", name = "record_identifier")
	public JAXBElement<String> createRecordIdentifier(String value) {
		return new JAXBElement<String>(_RecordIdentifier_QNAME, String.class,
				null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41", name = "product_name")
	public JAXBElement<String> createProductName(String value) {
		return new JAXBElement<String>(_ProductName_QNAME, String.class, null,
				value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41", name = "start")
	public JAXBElement<String> createStart(String value) {
		return new JAXBElement<String>(_Start_QNAME, String.class, null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41", name = "product_version")
	public JAXBElement<String> createProductVersion(String value) {
		return new JAXBElement<String>(_ProductVersion_QNAME, String.class,
				null, value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/net_defense/cnd-core/0.41", name = "resource")
	public JAXBElement<String> createResource(String value) {
		return new JAXBElement<String>(_Resource_QNAME, String.class, null,
				value);
	}

	@XmlElementDecl(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41", name = "execution_location")
	public JAXBElement<String> createExecutionLocation(String value) {
		return new JAXBElement<String>(_ExecutionLocation_QNAME, String.class,
				null, value);
	}
}
