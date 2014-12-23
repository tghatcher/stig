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
@XmlType(name = "CVEResultItemType", propOrder = { "result" })
public class CVEResultItemType {
	@XmlElement(required = 1)
	protected List<ResultValueType> result;
	@XmlAttribute(name = "cveFinding", required = 1)
	protected OVALResultEnumeration cveFinding;

	public List<ResultValueType> getResult() {
		if (this.result == null) {
			this.result = new ArrayList<ResultValueType>();
		}
		return this.result;
	}

	public OVALResultEnumeration getCveFinding() {
		return this.cveFinding;
	}

	public void setCveFinding(OVALResultEnumeration value) {
		this.cveFinding = value;
	}
}
