/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import ASR.LogicalTestType;
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
@XmlType(name = "CPEComplexResultItemType", propOrder = { "platform", "result" })
public class CPEComplexResultItemType {
	protected LogicalTestType platform;
	@XmlElement(required = 1)
	protected List<ResultValueType> result;
	@XmlAttribute(name = "cpeFinding", required = 1)
	protected OVALResultEnumeration cpeFinding;

	public LogicalTestType getPlatform() {
		return this.platform;
	}

	public void setPlatform(LogicalTestType value) {
		this.platform = value;
	}

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
}
