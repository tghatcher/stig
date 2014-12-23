/*
 * Decompiled with CFR 0_92.
 */
package VMSASR;

import VMSASR.ScanDataID;
import VMSASR.Scanner;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "scanDataID", "executionLocation", "start",
		"end", "scanner" })
@XmlRootElement(name = "scanData")
public class ScanData {
	@XmlElement(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41", required = 1)
	protected ScanDataID scanDataID;
	@XmlElement(name = "execution_location", namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41", required = 1)
	protected String executionLocation;
	@XmlElement(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41", required = 1)
	protected String start;
	@XmlElement(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41", required = 1)
	protected String end;
	@XmlElement(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41", required = 1)
	protected Scanner scanner;

	public ScanDataID getScanDataID() {
		return this.scanDataID;
	}

	public void setScanDataID(ScanDataID value) {
		this.scanDataID = value;
	}

	public String getExecutionLocation() {
		return this.executionLocation;
	}

	public void setExecutionLocation(String value) {
		this.executionLocation = value;
	}

	public String getStart() {
		return this.start;
	}

	public void setStart(String value) {
		this.start = value;
	}

	public String getEnd() {
		return this.end;
	}

	public void setEnd(String value) {
		this.end = value;
	}

	public Scanner getScanner() {
		return this.scanner;
	}

	public void setScanner(Scanner value) {
		this.scanner = value;
	}
}
