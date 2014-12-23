/*
 * Decompiled with CFR 0_92.
 */
package ARF;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "T_realm")
@XmlEnum
public enum TRealm {
	HBSS, INTACTICS, SPOTDOMAIN, WORKGROUP;

	private TRealm() {
	}

	public String value() {
		return this.name();
	}

	public static TRealm fromValue(String v) {
		return TRealm.valueOf(v);
	}
}
