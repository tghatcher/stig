/*
 * Decompiled with CFR 0_92.
 */
package ARF;

import ARF.AT2;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "T_taggedBoolean", namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/tagged_value/0.41")
public class TTaggedBoolean {
	@XmlAttribute(name = "value", required = 1)
	protected boolean value;
	@XmlAttribute(name = "name", required = 1)
	protected AT2 name;

	public boolean isValue() {
		return this.value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public AT2 getName() {
		return this.name;
	}

	public void setName(AT2 value) {
		this.name = value;
	}
}
