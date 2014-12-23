/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.mitre.ocil._1.TextType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "ReferenceType")
public class ReferenceType extends TextType {
	@XmlAttribute(name = "href")
	@XmlSchemaType(name = "anyURI")
	protected String href;

	public String getHref() {
		return this.href;
	}

	public void setHref(String value) {
		this.href = value;
	}
}
