/*
 * Decompiled with CFR 0_92.
 */
package RETINA_VMS;

import RETINA_VMS.ASSET;
import RETINA_VMS.ASSETID;
import RETINA_VMS.ELEMENT;
import RETINA_VMS.FINDING;
import RETINA_VMS.FINDINGDETAILS;
import RETINA_VMS.FINDINGID;
import RETINA_VMS.IMPORTFILE;
import RETINA_VMS.IPSCANRANGE;
import RETINA_VMS.PORT;
import RETINA_VMS.PROTOCOL;
import RETINA_VMS.SCAN;
import RETINA_VMS.SCRIPTRESULTS;
import RETINA_VMS.TARGET;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlRegistry
public class ObjectFactory {
	private static final QName _PROTOCOLNAME_QNAME = new QName("",
			"PROTOCOL_NAME");
	private static final QName _SCANAUDITVERSION_QNAME = new QName("",
			"SCAN_AUDIT_VERSION");
	private static final QName _FINDINGSTATUS_QNAME = new QName("",
			"FINDING_STATUS");
	private static final QName _SCANENGINEVERSION_QNAME = new QName("",
			"SCAN_ENGINE_VERSION");
	private static final QName _SCANRESULTSFILE_QNAME = new QName("",
			"SCAN_RESULTS_FILE");
	private static final QName _TOOLVERSION_QNAME = new QName("",
			"TOOL_VERSION");
	private static final QName _PORTID_QNAME = new QName("", "PORT_ID");
	private static final QName _SCANAUDITGROUP_QNAME = new QName("",
			"SCAN_AUDIT_GROUP");
	private static final QName _CHECKSUCCESS_QNAME = new QName("",
			"CHECK_SUCCESS");
	private static final QName _SCANJOBNAME_QNAME = new QName("",
			"SCAN_JOBNAME");
	private static final QName _TOTALFOUNDDEVICES_QNAME = new QName("",
			"TOTAL_FOUND_DEVICES");
	private static final QName _TOTALNOADMIN_QNAME = new QName("",
			"TOTAL_NO_ADMIN");
	private static final QName _ASSETTOOL_QNAME = new QName("", "ASSET_TOOL");
	private static final QName _SCANDATESTART_QNAME = new QName("",
			"SCAN_DATE_START");
	private static final QName _SCANID_QNAME = new QName("", "SCAN_ID");
	private static final QName _TOOL_QNAME = new QName("", "TOOL");
	private static final QName _SCANCREDENTIALS_QNAME = new QName("",
			"SCAN_CREDENTIALS");
	private static final QName _SCANDATEFINISH_QNAME = new QName("",
			"SCAN_DATE_FINISH");
	private static final QName _AUTHENTICATEDFINDING_QNAME = new QName("",
			"AUTHENTICATED_FINDING");
	private static final QName _ASSETTOOLVERSION_QNAME = new QName("",
			"ASSET_TOOL_VERSION");
	private static final QName _SERVICENAME_QNAME = new QName("",
			"SERVICE_NAME");
	private static final QName _TARGETKEY_QNAME = new QName("", "TARGET_KEY");

	public ELEMENT createELEMENT() {
		return new ELEMENT();
	}

	public FINDINGID createFINDINGID() {
		return new FINDINGID();
	}

	public TARGET createTARGET() {
		return new TARGET();
	}

	public ASSET createASSET() {
		return new ASSET();
	}

	public ASSETID createASSETID() {
		return new ASSETID();
	}

	public PROTOCOL createPROTOCOL() {
		return new PROTOCOL();
	}

	public IMPORTFILE createIMPORTFILE() {
		return new IMPORTFILE();
	}

	public FINDING createFINDING() {
		return new FINDING();
	}

	public FINDINGDETAILS createFINDINGDETAILS() {
		return new FINDINGDETAILS();
	}

	public SCAN createSCAN() {
		return new SCAN();
	}

	public IPSCANRANGE createIPSCANRANGE() {
		return new IPSCANRANGE();
	}

	public PORT createPORT() {
		return new PORT();
	}

	public SCRIPTRESULTS createSCRIPTRESULTS() {
		return new SCRIPTRESULTS();
	}

	@XmlElementDecl(namespace = "", name = "PROTOCOL_NAME")
	public JAXBElement<String> createPROTOCOLNAME(String value) {
		return new JAXBElement<String>(_PROTOCOLNAME_QNAME, String.class, null,
				value);
	}

	@XmlElementDecl(namespace = "", name = "SCAN_AUDIT_VERSION")
	public JAXBElement<Short> createSCANAUDITVERSION(Short value) {
		return new JAXBElement<Short>(_SCANAUDITVERSION_QNAME, Short.class,
				null, value);
	}

	@XmlElementDecl(namespace = "", name = "FINDING_STATUS")
	public JAXBElement<String> createFINDINGSTATUS(String value) {
		return new JAXBElement<String>(_FINDINGSTATUS_QNAME, String.class,
				null, value);
	}

	@XmlElementDecl(namespace = "", name = "SCAN_ENGINE_VERSION")
	public JAXBElement<String> createSCANENGINEVERSION(String value) {
		return new JAXBElement<String>(_SCANENGINEVERSION_QNAME, String.class,
				null, value);
	}

	@XmlElementDecl(namespace = "", name = "SCAN_RESULTS_FILE")
	public JAXBElement<String> createSCANRESULTSFILE(String value) {
		return new JAXBElement<String>(_SCANRESULTSFILE_QNAME, String.class,
				null, value);
	}

	@XmlElementDecl(namespace = "", name = "TOOL_VERSION")
	public JAXBElement<String> createTOOLVERSION(String value) {
		return new JAXBElement<String>(_TOOLVERSION_QNAME, String.class, null,
				value);
	}

	@XmlElementDecl(namespace = "", name = "PORT_ID")
	public JAXBElement<Integer> createPORTID(Integer value) {
		return new JAXBElement<Integer>(_PORTID_QNAME, Integer.class, null,
				value);
	}

	@XmlElementDecl(namespace = "", name = "SCAN_AUDIT_GROUP")
	public JAXBElement<String> createSCANAUDITGROUP(String value) {
		return new JAXBElement<String>(_SCANAUDITGROUP_QNAME, String.class,
				null, value);
	}

	@XmlElementDecl(namespace = "", name = "CHECK_SUCCESS")
	public JAXBElement<String> createCHECKSUCCESS(String value) {
		return new JAXBElement<String>(_CHECKSUCCESS_QNAME, String.class, null,
				value);
	}

	@XmlElementDecl(namespace = "", name = "SCAN_JOBNAME")
	public JAXBElement<String> createSCANJOBNAME(String value) {
		return new JAXBElement<String>(_SCANJOBNAME_QNAME, String.class, null,
				value);
	}

	@XmlElementDecl(namespace = "", name = "TOTAL_FOUND_DEVICES")
	public JAXBElement<Byte> createTOTALFOUNDDEVICES(Byte value) {
		return new JAXBElement<Byte>(_TOTALFOUNDDEVICES_QNAME, Byte.class,
				null, value);
	}

	@XmlElementDecl(namespace = "", name = "TOTAL_NO_ADMIN")
	public JAXBElement<Byte> createTOTALNOADMIN(Byte value) {
		return new JAXBElement<Byte>(_TOTALNOADMIN_QNAME, Byte.class, null,
				value);
	}

	@XmlElementDecl(namespace = "", name = "ASSET_TOOL")
	public JAXBElement<String> createASSETTOOL(String value) {
		return new JAXBElement<String>(_ASSETTOOL_QNAME, String.class, null,
				value);
	}

	@XmlElementDecl(namespace = "", name = "SCAN_DATE_START")
	public JAXBElement<XMLGregorianCalendar> createSCANDATESTART(
			XMLGregorianCalendar value) {
		return new JAXBElement<XMLGregorianCalendar>(_SCANDATESTART_QNAME,
				XMLGregorianCalendar.class, null, value);
	}

	@XmlElementDecl(namespace = "", name = "SCAN_ID")
	public JAXBElement<String> createSCANID(String value) {
		return new JAXBElement<String>(_SCANID_QNAME, String.class, null, value);
	}

	@XmlElementDecl(namespace = "", name = "TOOL")
	public JAXBElement<String> createTOOL(String value) {
		return new JAXBElement<String>(_TOOL_QNAME, String.class, null, value);
	}

	@XmlElementDecl(namespace = "", name = "SCAN_CREDENTIALS")
	public JAXBElement<String> createSCANCREDENTIALS(String value) {
		return new JAXBElement<String>(_SCANCREDENTIALS_QNAME, String.class,
				null, value);
	}

	@XmlElementDecl(namespace = "", name = "SCAN_DATE_FINISH")
	public JAXBElement<XMLGregorianCalendar> createSCANDATEFINISH(
			XMLGregorianCalendar value) {
		return new JAXBElement<XMLGregorianCalendar>(_SCANDATEFINISH_QNAME,
				XMLGregorianCalendar.class, null, value);
	}

	@XmlElementDecl(namespace = "", name = "AUTHENTICATED_FINDING")
	public JAXBElement<Boolean> createAUTHENTICATEDFINDING(Boolean value) {
		return new JAXBElement<Boolean>(_AUTHENTICATEDFINDING_QNAME,
				Boolean.class, null, value);
	}

	@XmlElementDecl(namespace = "", name = "ASSET_TOOL_VERSION")
	public JAXBElement<String> createASSETTOOLVERSION(String value) {
		return new JAXBElement<String>(_ASSETTOOLVERSION_QNAME, String.class,
				null, value);
	}

	@XmlElementDecl(namespace = "", name = "SERVICE_NAME")
	public JAXBElement<String> createSERVICENAME(String value) {
		return new JAXBElement<String>(_SERVICENAME_QNAME, String.class, null,
				value);
	}

	@XmlElementDecl(namespace = "", name = "TARGET_KEY")
	public JAXBElement<String> createTARGETKEY(String value) {
		return new JAXBElement<String>(_TARGETKEY_QNAME, String.class, null,
				value);
	}
}
