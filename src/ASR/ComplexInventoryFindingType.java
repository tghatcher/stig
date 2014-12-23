/*
 * Decompiled with CFR 0_92.
 */
package ASR;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "ComplexInventoryFindingType", propOrder = { "any" })
public class ComplexInventoryFindingType {
	@XmlAnyElement(lax = 1)
	protected Object any;

	public Object getAny() {
		return this.any;
	}

	public void setAny(Object value) {
		this.any = value;
	}
}
