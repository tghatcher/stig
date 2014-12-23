/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.mitre.ocil._1.QuestionTestActionType;
import org.mitre.ocil._1.WhenPattern;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "whenPattern" })
public class StringQuestionTestAction extends QuestionTestActionType {
	@XmlElement(name = "when_pattern", required = 1)
	protected List<WhenPattern> whenPattern;

	public List<WhenPattern> getWhenPattern() {
		if (this.whenPattern == null) {
			this.whenPattern = new ArrayList<WhenPattern>();
		}
		return this.whenPattern;
	}
}
