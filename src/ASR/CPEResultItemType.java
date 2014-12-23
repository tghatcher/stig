/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import ASR.OVALResultEnumeration;
import ASR.ResultValueType;
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
@XmlType(name = "CPEResultItemType", propOrder = { "result" })
public class CPEResultItemType {
	@XmlElement(required = 1)
	protected List<ResultValueType> result;
	@XmlAttribute(name = "cpeFinding", required = 1)
	protected OVALResultEnumeration cpeFinding;
	@XmlAttribute(name = "platformName", required = 1)
	protected String platformName;

	public List<ResultValueType> getResult() {
		if (this.result == null) {
			this.result = new ArrayList<ResultValueType>();
		}
		return this.result;
	}

	public OVALResultEnumeration getCpeFinding() {
		return this.cpeFinding;
	}

	public void setCpeFinding(OVALResultEnumeration value) {
		this.cpeFinding = value;
	}

	public String getPlatformName() {
		return this.platformName;
	}

	public void setPlatformName(String value) {
		this.platformName = value;
	}
}
