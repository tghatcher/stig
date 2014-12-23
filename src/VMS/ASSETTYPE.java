/*
 * Decompiled with CFR 0_92.
 */
package VMS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "assettypekey" })
@XmlRootElement(name = "ASSET_TYPE")
public class ASSETTYPE {
	@XmlElement(name = "ASSET_TYPE_KEY", required = 1)
	protected String assettypekey;

	public String getASSETTYPEKEY() {
		return this.assettypekey;
	}

	public void setASSETTYPEKEY(String value) {
		this.assettypekey = value;
	}
}
