/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import ASR.ComplexIdentType;
import ASR.ComplexInventoryFindingType;
import ASR.OVALResultEnumeration;
import ASR.ResultValueType;
import ASR.XCCDFResultEnumType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "SysIdentResultItemType", propOrder = { "complexIdent",
		"complexInventoryFinding", "result" })
public class SysIdentResultItemType {
	protected ComplexIdentType complexIdent;
	@XmlElement(name = "ComplexInventoryFinding")
	protected ComplexInventoryFindingType complexInventoryFinding;
	protected List<ResultValueType> result;
	@XmlAttribute(name = "identBooleanFinding")
	protected OVALResultEnumeration identBooleanFinding;
	@XmlAttribute(name = "identComplFinding")
	protected XCCDFResultEnumType identComplFinding;
	@XmlAttribute(name = "identInventoryFinding")
	protected String identInventoryFinding;
	@XmlAttribute(name = "ident")
	protected String ident;
	@XmlAttribute(name = "benchmark")
	protected String benchmark;
	@XmlAttribute(name = "profile")
	protected String profile;

	public ComplexIdentType getComplexIdent() {
		return this.complexIdent;
	}

	public void setComplexIdent(ComplexIdentType value) {
		this.complexIdent = value;
	}

	public ComplexInventoryFindingType getComplexInventoryFinding() {
		return this.complexInventoryFinding;
	}

	public void setComplexInventoryFinding(ComplexInventoryFindingType value) {
		this.complexInventoryFinding = value;
	}

	public List<ResultValueType> getResult() {
		if (this.result == null) {
			this.result = new ArrayList<ResultValueType>();
		}
		return this.result;
	}

	public OVALResultEnumeration getIdentBooleanFinding() {
		return this.identBooleanFinding;
	}

	public void setIdentBooleanFinding(OVALResultEnumeration value) {
		this.identBooleanFinding = value;
	}

	public XCCDFResultEnumType getIdentComplFinding() {
		return this.identComplFinding;
	}

	public void setIdentComplFinding(XCCDFResultEnumType value) {
		this.identComplFinding = value;
	}

	public String getIdentInventoryFinding() {
		return this.identInventoryFinding;
	}

	public void setIdentInventoryFinding(String value) {
		this.identInventoryFinding = value;
	}

	public String getIdent() {
		return this.ident;
	}

	public void setIdent(String value) {
		this.ident = value;
	}

	public String getBenchmark() {
		return this.benchmark;
	}

	public void setBenchmark(String value) {
		this.benchmark = value;
	}

	public String getProfile() {
		return this.profile;
	}

	public void setProfile(String value) {
		this.profile = value;
	}
}
