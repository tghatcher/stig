/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;
import org.w3c.dom.Element;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "GeneratorType", propOrder = { "productName", "productVersion",
		"author", "schemaVersion", "timestamp", "any" })
public class GeneratorType {
	@XmlElement(name = "product_name")
	protected String productName;
	@XmlElement(name = "product_version")
	protected String productVersion;
	protected List<Author> author;
	@XmlElement(name = "schema_version", required = 1)
	protected BigDecimal schemaVersion;
	@XmlElement(required = 1)
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar timestamp;
	@XmlAnyElement
	protected List<Element> any;

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String value) {
		this.productName = value;
	}

	public String getProductVersion() {
		return this.productVersion;
	}

	public void setProductVersion(String value) {
		this.productVersion = value;
	}

	public List<Author> getAuthor() {
		if (this.author == null) {
			this.author = new ArrayList<Author>();
		}
		return this.author;
	}

	public BigDecimal getSchemaVersion() {
		return this.schemaVersion;
	}

	public void setSchemaVersion(BigDecimal value) {
		this.schemaVersion = value;
	}

	public XMLGregorianCalendar getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(XMLGregorianCalendar value) {
		this.timestamp = value;
	}

	public List<Element> getAny() {
		if (this.any == null) {
			this.any = new ArrayList<Element>();
		}
		return this.any;
	}

	@XmlAccessorType(value = XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "value" })
	public static class Author {
		@XmlValue
		protected String value;
		@XmlAttribute(name = "organization")
		protected String organization;

		public String getValue() {
			return this.value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getOrganization() {
			return this.organization;
		}

		public void setOrganization(String value) {
			this.organization = value;
		}
	}

}
