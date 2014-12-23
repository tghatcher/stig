/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "value" })
@XmlRootElement(name = "choice")
public class Choice {
	@XmlValue
	protected String value;
	@XmlAttribute(name = "id", required = 1)
	protected String id;

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String value) {
		this.id = value;
	}
}
