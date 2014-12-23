/*
 * Decompiled with CFR 0_92.
 */
package CL_Save;

import CL_Save.ASSET;
import CL_Save.CHECKLIST;
import CL_Save.STIGDATA;
import CL_Save.STIGINFO;
import CL_Save.VULN;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlRegistry
public class ObjectFactory {
	private static final QName _STIGTITLE_QNAME = new QName("", "STIG_TITLE");
	private static final QName _COMMENTS_QNAME = new QName("", "COMMENTS");
	private static final QName _SEVERITYJUSTIFICATION_QNAME = new QName("",
			"SEVERITY_JUSTIFICATION");
	private static final QName _HOSTMAC_QNAME = new QName("", "HOST_MAC");
	private static final QName _STATUS_QNAME = new QName("", "STATUS");
	private static final QName _HOSTNAME_QNAME = new QName("", "HOST_NAME");
	private static final QName _VULNATTRIBUTE_QNAME = new QName("",
			"VULN_ATTRIBUTE");
	private static final QName _FINDINGDETAILS_QNAME = new QName("",
			"FINDING_DETAILS");
	private static final QName _SEVERITYOVERRIDE_QNAME = new QName("",
			"SEVERITY_OVERRIDE");
	private static final QName _ASSETTYPE_QNAME = new QName("", "ASSET_TYPE");
	private static final QName _ATTRIBUTEDATA_QNAME = new QName("",
			"ATTRIBUTE_DATA");
	private static final QName _HOSTGUID_QNAME = new QName("", "HOST_GUID");
	private static final QName _TARGETKEY_QNAME = new QName("", "TARGET_KEY");
	private static final QName _HOSTIP_QNAME = new QName("", "HOST_IP");

	public VULN createVULN() {
		return new VULN();
	}

	public ASSET.ASSETVAL createASSETASSETVAL() {
		return new ASSET.ASSETVAL();
	}

	public STIGINFO createSTIGINFO() {
		return new STIGINFO();
	}

	public VULN.VULNDATA createVULNVULNDATA() {
		return new VULN.VULNDATA();
	}

	public STIGINFO.SIDATA createSTIGINFOSIDATA() {
		return new STIGINFO.SIDATA();
	}

	public STIGDATA createSTIGDATA() {
		return new STIGDATA();
	}

	public ASSET createASSET() {
		return new ASSET();
	}

	public CHECKLIST createCHECKLIST() {
		return new CHECKLIST();
	}

	@XmlElementDecl(namespace = "", name = "STIG_TITLE")
	public JAXBElement<String> createSTIGTITLE(String value) {
		return new JAXBElement<String>(_STIGTITLE_QNAME, String.class, null,
				value);
	}

	@XmlElementDecl(namespace = "", name = "COMMENTS")
	public JAXBElement<String> createCOMMENTS(String value) {
		return new JAXBElement<String>(_COMMENTS_QNAME, String.class, null,
				value);
	}

	@XmlElementDecl(namespace = "", name = "SEVERITY_JUSTIFICATION")
	public JAXBElement<String> createSEVERITYJUSTIFICATION(String value) {
		return new JAXBElement<String>(_SEVERITYJUSTIFICATION_QNAME,
				String.class, null, value);
	}

	@XmlElementDecl(namespace = "", name = "HOST_MAC")
	public JAXBElement<String> createHOSTMAC(String value) {
		return new JAXBElement<String>(_HOSTMAC_QNAME, String.class, null,
				value);
	}

	@XmlElementDecl(namespace = "", name = "STATUS")
	public JAXBElement<String> createSTATUS(String value) {
		return new JAXBElement<String>(_STATUS_QNAME, String.class, null, value);
	}

	@XmlElementDecl(namespace = "", name = "HOST_NAME")
	public JAXBElement<String> createHOSTNAME(String value) {
		return new JAXBElement<String>(_HOSTNAME_QNAME, String.class, null,
				value);
	}

	@XmlElementDecl(namespace = "", name = "VULN_ATTRIBUTE")
	public JAXBElement<String> createVULNATTRIBUTE(String value) {
		return new JAXBElement<String>(_VULNATTRIBUTE_QNAME, String.class,
				null, value);
	}

	@XmlElementDecl(namespace = "", name = "FINDING_DETAILS")
	public JAXBElement<String> createFINDINGDETAILS(String value) {
		return new JAXBElement<String>(_FINDINGDETAILS_QNAME, String.class,
				null, value);
	}

	@XmlElementDecl(namespace = "", name = "SEVERITY_OVERRIDE")
	public JAXBElement<String> createSEVERITYOVERRIDE(String value) {
		return new JAXBElement<String>(_SEVERITYOVERRIDE_QNAME, String.class,
				null, value);
	}

	@XmlElementDecl(namespace = "", name = "ASSET_TYPE")
	public JAXBElement<String> createASSETTYPE(String value) {
		return new JAXBElement<String>(_ASSETTYPE_QNAME, String.class, null,
				value);
	}

	@XmlElementDecl(namespace = "", name = "ATTRIBUTE_DATA")
	public JAXBElement<String> createATTRIBUTEDATA(String value) {
		return new JAXBElement<String>(_ATTRIBUTEDATA_QNAME, String.class,
				null, value);
	}

	@XmlElementDecl(namespace = "", name = "HOST_GUID")
	public JAXBElement<String> createHOSTGUID(String value) {
		return new JAXBElement<String>(_HOSTGUID_QNAME, String.class, null,
				value);
	}

	@XmlElementDecl(namespace = "", name = "TARGET_KEY")
	public JAXBElement<String> createTARGETKEY(String value) {
		return new JAXBElement<String>(_TARGETKEY_QNAME, String.class, null,
				value);
	}

	@XmlElementDecl(namespace = "", name = "HOST_IP")
	public JAXBElement<String> createHOSTIP(String value) {
		return new JAXBElement<String>(_HOSTIP_QNAME, String.class, null, value);
	}
}
