/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import ASR.CPEResultItemType;
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
@XmlType(name = "CPEResultsType", propOrder = { "platform", "cpeResultItem" })
public class CPEResultsType {
	@XmlElement(required = 1)
	protected String platform;
	protected List<CPEResultItemType> cpeResultItem;
	@XmlAttribute(name = "singleListing")
	protected Boolean singleListing;

	public String getPlatform() {
		return this.platform;
	}

	public void setPlatform(String value) {
		this.platform = value;
	}

	public List<CPEResultItemType> getCpeResultItem() {
		if (this.cpeResultItem == null) {
			this.cpeResultItem = new ArrayList<CPEResultItemType>();
		}
		return this.cpeResultItem;
	}

	public boolean isSingleListing() {
		if (this.singleListing == null) {
			return false;
		}
		return this.singleListing;
	}

	public void setSingleListing(Boolean value) {
		this.singleListing = value;
	}
}
