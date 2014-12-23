/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.mitre.ocil._1.ResultChoiceType;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "value" })
@XmlRootElement(name = "when_equals")
public class WhenEquals extends ResultChoiceType {
	@XmlElement(required = 1)
	protected List<BigDecimal> value;

	public List<BigDecimal> getValue() {
		if (this.value == null) {
			this.value = new ArrayList<BigDecimal>();
		}
		return this.value;
	}
}
