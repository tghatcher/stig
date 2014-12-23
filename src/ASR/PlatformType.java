/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import ASR.LogicalTestType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "PlatformType", propOrder = { "logicalTest" })
public class PlatformType {
	@XmlElement(name = "logical-test", required = 1)
	protected LogicalTestType logicalTest;
	@XmlAttribute(name = "id", required = 1)
	@XmlSchemaType(name = "anyURI")
	protected String id;

	public LogicalTestType getLogicalTest() {
		return this.logicalTest;
	}

	public void setLogicalTest(LogicalTestType value) {
		this.logicalTest = value;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String value) {
		this.id = value;
	}
}
