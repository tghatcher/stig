/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import org.mitre.ocil._1.ReferenceType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "TextType", propOrder = { "value" })
@XmlSeeAlso(value = { ReferenceType.class })
public class TextType {
	@XmlValue
	protected String value;
	@XmlAttribute(name = "lang")
	protected String lang;

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLang() {
		return this.lang;
	}

	public void setLang(String value) {
		this.lang = value;
	}
}
