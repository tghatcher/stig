/*
 * Decompiled with CFR 0_92.
 */
package RETINA_VMS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "portid", "servicename" })
@XmlRootElement(name = "PORT")
public class PORT {
	@XmlElement(name = "PORT_ID")
	protected int portid;
	@XmlElement(name = "SERVICE_NAME", required = 1)
	protected String servicename;

	public int getPORTID() {
		return this.portid;
	}

	public void setPORTID(int value) {
		this.portid = value;
	}

	public String getSERVICENAME() {
		return this.servicename;
	}

	public void setSERVICENAME(String value) {
		this.servicename = value;
	}
}
