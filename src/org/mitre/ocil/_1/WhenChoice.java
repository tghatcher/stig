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
import org.mitre.ocil._1.ResultChoiceType;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "choiceRef" })
@XmlRootElement(name = "when_choice")
public class WhenChoice extends ResultChoiceType {
	@XmlElement(name = "choice_ref", required = 1)
	protected List<String> choiceRef;

	public List<String> getChoiceRef() {
		if (this.choiceRef == null) {
			this.choiceRef = new ArrayList<String>();
		}
		return this.choiceRef;
	}
}
