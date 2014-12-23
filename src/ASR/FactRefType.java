/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "FactRefType")
public class FactRefType {
	@XmlAttribute(name = "name", required = 1)
	protected String name;

	public String getName() {
		return this.name;
	}

	public void setName(String value) {
		this.name = value;
	}
}
