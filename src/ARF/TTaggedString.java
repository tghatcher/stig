/*
 * Decompiled with CFR 0_92.
 */
package ARF;

import ARF.AT5;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "T_taggedString", namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/tagged_value/0.41")
public class TTaggedString {
	@XmlAttribute(name = "value", required = 1)
	protected String value;
	@XmlAttribute(name = "name", required = 1)
	protected AT5 name;

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public AT5 getName() {
		return this.name;
	}

	public void setName(AT5 value) {
		this.name = value;
	}
}
