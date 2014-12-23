/*
 * Decompiled with CFR 0_92.
 */
package VMSASR;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class PrResource extends JAXBElement<String> {
	protected static final QName NAME = new QName(
			"http://metadata.dod.mil/mdr/ns/netops/net_defense/summary_res/0.41",
			"resource");

	public PrResource(String value) {
		super(NAME, String.class, null, value);
	}

	public PrResource() {
		super(NAME, String.class, null, null);
	}
}
