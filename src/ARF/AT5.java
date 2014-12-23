/*
 * Decompiled with CFR 0_92.
 */
package ARF;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "AT_5", namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/tagged_value/0.41")
@XmlEnum
public enum AT5 {
	CPU_SPEED("CPUSpeed"), CPU_TYPE("CPUType"), MC_AFEE_E_PO_AGENT_GUID(
			"McAfee ePO Agent GUID"), OS_BUILD_NUM("OSBuildNum"), OS_FAMILY(
			"OSFamily"), OS_PLATFORM("OSPlatform"), OS_SERVICE_PACK_VER(
			"OSServicePackVer"), OS_TYPE("OSType"), OS_VERSION("OSVersion");

	private final String value;

	private AT5(String v) {
		this.value = v;
	}

	public String value() {
		return this.value;
	}

	public static AT5 fromValue(String v) {
		for (AT5 c : AT5.values()) {
			if (!c.value.equals(v))
				continue;
			return c;
		}
		throw new IllegalArgumentException(v);
	}
}
