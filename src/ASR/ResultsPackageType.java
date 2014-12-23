/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import ASR.BenchmarkResultsType;
import ASR.CPEComplexResultsType;
import ASR.CPEResultsType;
import ASR.CVEResultsType;
import ASR.DeviceListRecordType;
import ASR.ObjectListRecordType;
import ASR.ResultPopulationCharacteristicsType;
import ASR.SystemIdentResultsType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "populationCharacteristics", "benchmark",
		"cpe", "cve", "cpeComplexResults", "systemIdent", "deviceListItem",
		"objectListItem" })
@XmlRootElement(name = "ResultsPackageType")
public class ResultsPackageType {
	@XmlElement(name = "PopulationCharacteristics", required = 1)
	protected ResultPopulationCharacteristicsType populationCharacteristics;
	protected List<BenchmarkResultsType> benchmark;
	protected List<CPEResultsType> cpe;
	protected List<CVEResultsType> cve;
	protected List<CPEComplexResultsType> cpeComplexResults;
	protected List<SystemIdentResultsType> systemIdent;
	protected List<DeviceListRecordType> deviceListItem;
	protected List<ObjectListRecordType> objectListItem;

	public ResultPopulationCharacteristicsType getPopulationCharacteristics() {
		return this.populationCharacteristics;
	}

	public void setPopulationCharacteristics(
			ResultPopulationCharacteristicsType value) {
		this.populationCharacteristics = value;
	}

	public List<BenchmarkResultsType> getBenchmark() {
		if (this.benchmark == null) {
			this.benchmark = new ArrayList<BenchmarkResultsType>();
		}
		return this.benchmark;
	}

	public List<CPEResultsType> getCpe() {
		if (this.cpe == null) {
			this.cpe = new ArrayList<CPEResultsType>();
		}
		return this.cpe;
	}

	public List<CVEResultsType> getCve() {
		if (this.cve == null) {
			this.cve = new ArrayList<CVEResultsType>();
		}
		return this.cve;
	}

	public List<CPEComplexResultsType> getCpeComplexResults() {
		if (this.cpeComplexResults == null) {
			this.cpeComplexResults = new ArrayList<CPEComplexResultsType>();
		}
		return this.cpeComplexResults;
	}

	public List<SystemIdentResultsType> getSystemIdent() {
		if (this.systemIdent == null) {
			this.systemIdent = new ArrayList<SystemIdentResultsType>();
		}
		return this.systemIdent;
	}

	public List<DeviceListRecordType> getDeviceListItem() {
		if (this.deviceListItem == null) {
			this.deviceListItem = new ArrayList<DeviceListRecordType>();
		}
		return this.deviceListItem;
	}

	public List<ObjectListRecordType> getObjectListItem() {
		if (this.objectListItem == null) {
			this.objectListItem = new ArrayList<ObjectListRecordType>();
		}
		return this.objectListItem;
	}
}
