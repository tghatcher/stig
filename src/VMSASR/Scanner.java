/*
 * Decompiled with CFR 0_92.
 */
package VMSASR;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "productName", "productVersion" })
@XmlRootElement(name = "scanner", namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41")
public class Scanner {
	@XmlElement(name = "product_name", namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41", required = 1)
	protected String productName;
	@XmlElement(name = "product_version", namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41", required = 1)
	protected String productVersion;

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String value) {
		this.productName = value;
	}

	public String getProductVersion() {
		return this.productVersion;
	}

	public void setProductVersion(String value) {
		this.productVersion = value;
	}
}
