/*
 * Decompiled with CFR 0_92.
 */
package RETINA_VMS;

import RETINA_VMS.ASSET;
import RETINA_VMS.SCAN;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "scanAndASSET" })
@XmlRootElement(name = "IMPORT_FILE")
public class IMPORTFILE {
	@XmlElements(value = {
			@XmlElement(name = "ASSET", required = 1, type = ASSET.class),
			@XmlElement(name = "SCAN", required = 1, type = SCAN.class) })
	protected List<Object> scanAndASSET;

	public List<Object> getSCANAndASSET() {
		if (this.scanAndASSET == null) {
			this.scanAndASSET = new ArrayList<Object>();
		}
		return this.scanAndASSET;
	}
}
