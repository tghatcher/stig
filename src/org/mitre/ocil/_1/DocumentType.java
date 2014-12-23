/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "DocumentType", propOrder = { "title", "description", "notice" })
public class DocumentType {
	@XmlElement(required = 1)
	protected String title;
	protected List<String> description;
	protected List<String> notice;

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String value) {
		this.title = value;
	}

	public List<String> getDescription() {
		if (this.description == null) {
			this.description = new ArrayList<String>();
		}
		return this.description;
	}

	public List<String> getNotice() {
		if (this.notice == null) {
			this.notice = new ArrayList<String>();
		}
		return this.notice;
	}
}
