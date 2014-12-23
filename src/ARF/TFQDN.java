/*
 * Decompiled with CFR 0_92.
 */
package ARF;

import ARF.TRealm;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "T_FQDN", propOrder = { "realm", "hostName" })
public class TFQDN {
	@XmlElement(required = 1)
	protected TRealm realm;
	@XmlElement(name = "host_name", required = 1)
	protected String hostName;

	public TRealm getRealm() {
		return this.realm;
	}

	public void setRealm(TRealm value) {
		this.realm = value;
	}

	public String getHostName() {
		return this.hostName;
	}

	public void setHostName(String value) {
		this.hostName = value;
	}
}
