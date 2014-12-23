/*
 * Decompiled with CFR 0_92.
 */
package VMS;

import VMS.ASSET;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "asset" })
@XmlRootElement(name = "IMPORT_FILE")
public class IMPORTFILE {
	@XmlElement(name = "ASSET", required = 1)
	protected List<ASSET> asset;

	public List<ASSET> getASSET() {
		if (this.asset == null) {
			this.asset = new ArrayList<ASSET>();
		}
		return this.asset;
	}
}
