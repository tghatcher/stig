/*
 * Decompiled with CFR 0_92.
 */
package RETINA_VMS;

import RETINA_VMS.PORT;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "protocolname", "port" })
@XmlRootElement(name = "PROTOCOL")
public class PROTOCOL {
	@XmlElement(name = "PROTOCOL_NAME", required = 1)
	protected String protocolname;
	@XmlElement(name = "PORT", required = 1)
	protected PORT port;

	public String getPROTOCOLNAME() {
		return this.protocolname;
	}

	public void setPROTOCOLNAME(String value) {
		this.protocolname = value;
	}

	public PORT getPORT() {
		return this.port;
	}

	public void setPORT(PORT value) {
		this.port = value;
	}
}
