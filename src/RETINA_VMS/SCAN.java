/*
 * Decompiled with CFR 0_92.
 */
package RETINA_VMS;

import RETINA_VMS.IPSCANRANGE;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "scanid", "ipscanrange", "scanauditgroup",
		"totalfounddevices", "totalnoadmin", "scancredentials",
		"scandatestart", "scandatefinish", "scanresultsfile", "scanjobname",
		"scanengineversion", "scanauditversion" })
@XmlRootElement(name = "SCAN")
public class SCAN {
	@XmlElement(name = "SCAN_ID", required = 1)
	protected String scanid;
	@XmlElement(name = "IP_SCAN_RANGE", required = 1)
	protected IPSCANRANGE ipscanrange;
	@XmlElement(name = "SCAN_AUDIT_GROUP", required = 1)
	protected String scanauditgroup;
	@XmlElement(name = "TOTAL_FOUND_DEVICES")
	protected byte totalfounddevices;
	@XmlElement(name = "TOTAL_NO_ADMIN")
	protected byte totalnoadmin;
	@XmlElement(name = "SCAN_CREDENTIALS", required = 1)
	protected String scancredentials;
	@XmlElement(name = "SCAN_DATE_START", required = 1)
	protected XMLGregorianCalendar scandatestart;
	@XmlElement(name = "SCAN_DATE_FINISH", required = 1)
	protected XMLGregorianCalendar scandatefinish;
	@XmlElement(name = "SCAN_RESULTS_FILE", required = 1)
	protected String scanresultsfile;
	@XmlElement(name = "SCAN_JOBNAME", required = 1)
	protected String scanjobname;
	@XmlElement(name = "SCAN_ENGINE_VERSION", required = 1)
	protected String scanengineversion;
	@XmlElement(name = "SCAN_AUDIT_VERSION")
	protected short scanauditversion;

	public String getSCANID() {
		return this.scanid;
	}

	public void setSCANID(String value) {
		this.scanid = value;
	}

	public IPSCANRANGE getIPSCANRANGE() {
		return this.ipscanrange;
	}

	public void setIPSCANRANGE(IPSCANRANGE value) {
		this.ipscanrange = value;
	}

	public String getSCANAUDITGROUP() {
		return this.scanauditgroup;
	}

	public void setSCANAUDITGROUP(String value) {
		this.scanauditgroup = value;
	}

	public byte getTOTALFOUNDDEVICES() {
		return this.totalfounddevices;
	}

	public void setTOTALFOUNDDEVICES(byte value) {
		this.totalfounddevices = value;
	}

	public byte getTOTALNOADMIN() {
		return this.totalnoadmin;
	}

	public void setTOTALNOADMIN(byte value) {
		this.totalnoadmin = value;
	}

	public String getSCANCREDENTIALS() {
		return this.scancredentials;
	}

	public void setSCANCREDENTIALS(String value) {
		this.scancredentials = value;
	}

	public XMLGregorianCalendar getSCANDATESTART() {
		return this.scandatestart;
	}

	public void setSCANDATESTART(XMLGregorianCalendar value) {
		this.scandatestart = value;
	}

	public XMLGregorianCalendar getSCANDATEFINISH() {
		return this.scandatefinish;
	}

	public void setSCANDATEFINISH(XMLGregorianCalendar value) {
		this.scandatefinish = value;
	}

	public String getSCANRESULTSFILE() {
		return this.scanresultsfile;
	}

	public void setSCANRESULTSFILE(String value) {
		this.scanresultsfile = value;
	}

	public String getSCANJOBNAME() {
		return this.scanjobname;
	}

	public void setSCANJOBNAME(String value) {
		this.scanjobname = value;
	}

	public String getSCANENGINEVERSION() {
		return this.scanengineversion;
	}

	public void setSCANENGINEVERSION(String value) {
		this.scanengineversion = value;
	}

	public short getSCANAUDITVERSION() {
		return this.scanauditversion;
	}

	public void setSCANAUDITVERSION(short value) {
		this.scanauditversion = value;
	}
}
