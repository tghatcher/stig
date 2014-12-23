/*
 * Decompiled with CFR 0_92.
 */
package ARF;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "AT_2", namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/tagged_value/0.41")
@XmlEnum
public enum AT2 {
	MC_AFEE_E_PO_MANAGED("McAfee ePO Managed");

	private final String value;

	private AT2(String v) {
		this.value = v;
	}

	public String value() {
		return this.value;
	}

	public static AT2 fromValue(String v) {
		for (AT2 c : AT2.values()) {
			if (!c.value.equals(v))
				continue;
			return c;
		}
		throw new IllegalArgumentException(v);
	}
}
