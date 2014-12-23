/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "ContentType", namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/scan_data/0.41", propOrder = {
		"system", "systemVersion", "version" })
public class ContentType {
	protected String system;
	protected String systemVersion;
	protected String version;

	public String getSystem() {
		return this.system;
	}

	public void setSystem(String value) {
		this.system = value;
	}

	public String getSystemVersion() {
		return this.systemVersion;
	}

	public void setSystemVersion(String value) {
		this.systemVersion = value;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String value) {
		this.version = value;
	}
}
