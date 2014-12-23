/*
 * Decompiled with CFR 0_92.
 */
package VMS;

import VMS.ASSET;
import VMS.ASSETID;
import VMS.ASSETTYPE;
import VMS.ELEMENT;
import VMS.FINDING;
import VMS.FINDINGDETAILS;
import VMS.FINDINGID;
import VMS.IMPORTFILE;
import VMS.TARGET;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlRegistry
public class ObjectFactory {
	private static final QName _ELEMENTKEY_QNAME = new QName(
			"urn:FindingImport", "ELEMENT_KEY");
	private static final QName _FINDINGSTATUS_QNAME = new QName(
			"urn:FindingImport", "FINDING_STATUS");
	private static final QName _TOOL_QNAME = new QName("urn:FindingImport",
			"TOOL");
	private static final QName _ASSETTYPEKEY_QNAME = new QName(
			"urn:FindingImport", "ASSET_TYPE_KEY");
	private static final QName _WORKSTATION_QNAME = new QName(
			"urn:FindingImport", "WORKSTATION");
	private static final QName _COMMENT_QNAME = new QName("urn:FindingImport",
			"COMMENT");
	private static final QName _SCRIPTRESULTS_QNAME = new QName(
			"urn:FindingImport", "SCRIPT_RESULTS");
	private static final QName _AUTHENTICATEDFINDING_QNAME = new QName(
			"urn:FindingImport", "AUTHENTICATED_FINDING");
	private static final QName _TOOLVERSION_QNAME = new QName(
			"urn:FindingImport", "TOOL_VERSION");
	private static final QName _TARGETKEY_QNAME = new QName(
			"urn:FindingImport", "TARGET_KEY");
	private static final QName _SEVOVERRIDETEXT_QNAME = new QName(
			"urn:FindingImport", "SEV_OVERRIDE_TEXT");
	private static final QName _SEVOVERRIDECODE_QNAME = new QName(
			"urn:FindingImport", "SEV_OVERRIDE_CODE");

	public FINDING createFINDING() {
		return new FINDING();
	}

	public ELEMENT createELEMENT() {
		return new ELEMENT();
	}

	public FINDINGID createFINDINGID() {
		return new FINDINGID();
	}

	public ASSETTYPE createASSETTYPE() {
		return new ASSETTYPE();
	}

	public ASSET createASSET() {
		return new ASSET();
	}

	public IMPORTFILE createIMPORTFILE() {
		return new IMPORTFILE();
	}

	public TARGET createTARGET() {
		return new TARGET();
	}

	public FINDINGDETAILS createFINDINGDETAILS() {
		return new FINDINGDETAILS();
	}

	public ASSETID createASSETID() {
		return new ASSETID();
	}

	@XmlElementDecl(namespace = "urn:FindingImport", name = "ELEMENT_KEY")
	public JAXBElement<String> createELEMENTKEY(String value) {
		return new JAXBElement<String>(_ELEMENTKEY_QNAME, String.class, null,
				value);
	}

	@XmlElementDecl(namespace = "urn:FindingImport", name = "FINDING_STATUS")
	public JAXBElement<String> createFINDINGSTATUS(String value) {
		return new JAXBElement<String>(_FINDINGSTATUS_QNAME, String.class,
				null, value);
	}

	@XmlElementDecl(namespace = "urn:FindingImport", name = "TOOL")
	public JAXBElement<String> createTOOL(String value) {
		return new JAXBElement<String>(_TOOL_QNAME, String.class, null, value);
	}

	@XmlElementDecl(namespace = "urn:FindingImport", name = "ASSET_TYPE_KEY")
	public JAXBElement<String> createASSETTYPEKEY(String value) {
		return new JAXBElement<String>(_ASSETTYPEKEY_QNAME, String.class, null,
				value);
	}

	@XmlElementDecl(namespace = "urn:FindingImport", name = "WORKSTATION")
	public JAXBElement<String> createWORKSTATION(String value) {
		return new JAXBElement<String>(_WORKSTATION_QNAME, String.class, null,
				value);
	}

	@XmlElementDecl(namespace = "urn:FindingImport", name = "COMMENT")
	public JAXBElement<String> createCOMMENT(String value) {
		return new JAXBElement<String>(_COMMENT_QNAME, String.class, null,
				value);
	}

	@XmlElementDecl(namespace = "urn:FindingImport", name = "SCRIPT_RESULTS")
	public JAXBElement<String> createSCRIPTRESULTS(String value) {
		return new JAXBElement<String>(_SCRIPTRESULTS_QNAME, String.class,
				null, value);
	}

	@XmlElementDecl(namespace = "urn:FindingImport", name = "AUTHENTICATED_FINDING")
	public JAXBElement<Boolean> createAUTHENTICATEDFINDING(Boolean value) {
		return new JAXBElement<Boolean>(_AUTHENTICATEDFINDING_QNAME,
				Boolean.class, null, value);
	}

	@XmlElementDecl(namespace = "urn:FindingImport", name = "TOOL_VERSION")
	public JAXBElement<String> createTOOLVERSION(String value) {
		return new JAXBElement<String>(_TOOLVERSION_QNAME, String.class, null,
				value);
	}

	@XmlElementDecl(namespace = "urn:FindingImport", name = "TARGET_KEY")
	public JAXBElement<String> createTARGETKEY(String value) {
		return new JAXBElement<String>(_TARGETKEY_QNAME, String.class, null,
				value);
	}

	@XmlElementDecl(namespace = "urn:FindingImport", name = "SEV_OVERRIDE_TEXT")
	public JAXBElement<String> createSEVOVERRIDETEXT(String value) {
		return new JAXBElement<String>(_SEVOVERRIDETEXT_QNAME, String.class,
				null, value);
	}

	@XmlElementDecl(namespace = "urn:FindingImport", name = "SEV_OVERRIDE_CODE")
	public JAXBElement<String> createSEVOVERRIDECODE(String value) {
		return new JAXBElement<String>(_SEVOVERRIDECODE_QNAME, String.class,
				null, value);
	}
}
