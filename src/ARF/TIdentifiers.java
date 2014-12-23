/*
 * Decompiled with CFR 0_92.
 */
package ARF;

import ARF.TFQDN;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "T_identifiers", propOrder = { "fqdn" })
public class TIdentifiers {
	@XmlElement(name = "FQDN", required = 1)
	protected TFQDN fqdn;

	public TFQDN getFQDN() {
		return this.fqdn;
	}

	public void setFQDN(TFQDN value) {
		this.fqdn = value;
	}
}
