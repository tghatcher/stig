/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import ASR.CPEComplexResultItemType;
import ASR.LogicalTestType;
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
@XmlType(name = "CPEComplexResultsType", propOrder = { "platform",
		"cpeResultItem" })
public class CPEComplexResultsType {
	@XmlElement(required = 1)
	protected LogicalTestType platform;
	protected List<CPEComplexResultItemType> cpeResultItem;
	@XmlAttribute(name = "singleListing")
	protected Boolean singleListing;

	public LogicalTestType getPlatform() {
		return this.platform;
	}

	public void setPlatform(LogicalTestType value) {
		this.platform = value;
	}

	public List<CPEComplexResultItemType> getCpeResultItem() {
		if (this.cpeResultItem == null) {
			this.cpeResultItem = new ArrayList<CPEComplexResultItemType>();
		}
		return this.cpeResultItem;
	}

	public boolean isSingleListing() {
		if (this.singleListing == null) {
			return true;
		}
		return this.singleListing;
	}

	public void setSingleListing(Boolean value) {
		this.singleListing = value;
	}
}
