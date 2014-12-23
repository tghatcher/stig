/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.mitre.ocil._1.PatternType;
import org.mitre.ocil._1.ResultChoiceType;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "pattern" })
@XmlRootElement(name = "when_pattern")
public class WhenPattern extends ResultChoiceType {
	@XmlElement(required = 1)
	protected List<PatternType> pattern;

	public List<PatternType> getPattern() {
		if (this.pattern == null) {
			this.pattern = new ArrayList<PatternType>();
		}
		return this.pattern;
	}
}
