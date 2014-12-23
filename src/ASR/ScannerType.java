/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import ASR.ScannerTypeList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "ScannerType", namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41", propOrder = {
		"productName", "productVersion", "scannerType" })
public class ScannerType {
	@XmlElement(name = "product_name")
	protected String productName;
	@XmlElement(name = "product_version")
	protected String productVersion;
	protected ScannerTypeList scannerType;

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

	public ScannerTypeList getScannerType() {
		return this.scannerType;
	}

	public void setScannerType(ScannerTypeList value) {
		this.scannerType = value;
	}
}
