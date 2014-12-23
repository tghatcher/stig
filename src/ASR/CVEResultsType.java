/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import ASR.CVEResultItemType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "CVEResultsType", propOrder = { "cveResultItem" })
public class CVEResultsType {
	@XmlElement(required = 1)
	protected List<CVEResultItemType> cveResultItem;
	@XmlAttribute(name = "cveID", required = 1)
	@XmlJavaTypeAdapter(value = CollapsedStringAdapter.class)
	protected String cveID;

	public List<CVEResultItemType> getCveResultItem() {
		if (this.cveResultItem == null) {
			this.cveResultItem = new ArrayList<CVEResultItemType>();
		}
		return this.cveResultItem;
	}

	public String getCveID() {
		return this.cveID;
	}

	public void setCveID(String value) {
		this.cveID = value;
	}
}
