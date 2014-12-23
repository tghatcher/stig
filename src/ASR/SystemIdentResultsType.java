/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import ASR.SysIdentResultItemType;
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
@XmlType(name = "SystemIdentResultsType", propOrder = { "identResultItem" })
public class SystemIdentResultsType {
	@XmlElement(required = 1)
	protected List<SysIdentResultItemType> identResultItem;
	@XmlAttribute(name = "system", required = 1)
	protected String system;

	public List<SysIdentResultItemType> getIdentResultItem() {
		if (this.identResultItem == null) {
			this.identResultItem = new ArrayList<SysIdentResultItemType>();
		}
		return this.identResultItem;
	}

	public String getSystem() {
		return this.system;
	}

	public void setSystem(String value) {
		this.system = value;
	}
}
