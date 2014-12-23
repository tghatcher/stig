/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import ASR.ContentType;
import ASR.ScanDataIDType;
import ASR.ScanLocationList;
import ASR.ScannerType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "ScanDataType", namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41", propOrder = {
		"scanDataID", "authenticated", "executionLocation", "start", "end",
		"scanner", "content" })
public class ScanDataType {
	@XmlElement(required = 1)
	protected ScanDataIDType scanDataID;
	@XmlElement(defaultValue = "false")
	protected Boolean authenticated;
	@XmlElement(name = "execution_location")
	protected ScanLocationList executionLocation;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar start;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar end;
	protected ScannerType scanner;
	protected List<ContentType> content;

	public ScanDataIDType getScanDataID() {
		return this.scanDataID;
	}

	public void setScanDataID(ScanDataIDType value) {
		this.scanDataID = value;
	}

	public Boolean isAuthenticated() {
		return this.authenticated;
	}

	public void setAuthenticated(Boolean value) {
		this.authenticated = value;
	}

	public ScanLocationList getExecutionLocation() {
		return this.executionLocation;
	}

	public void setExecutionLocation(ScanLocationList value) {
		this.executionLocation = value;
	}

	public XMLGregorianCalendar getStart() {
		return this.start;
	}

	public void setStart(XMLGregorianCalendar value) {
		this.start = value;
	}

	public XMLGregorianCalendar getEnd() {
		return this.end;
	}

	public void setEnd(XMLGregorianCalendar value) {
		this.end = value;
	}

	public ScannerType getScanner() {
		return this.scanner;
	}

	public void setScanner(ScannerType value) {
		this.scanner = value;
	}

	public List<ContentType> getContent() {
		if (this.content == null) {
			this.content = new ArrayList<ContentType>();
		}
		return this.content;
	}
}
