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
import org.mitre.ocil._1.WhenChoice;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "whenChoice" })
public class ChoiceQuestionTestAction extends QuestionTestActionType {
	@XmlElement(name = "when_choice", required = 1)
	protected List<WhenChoice> whenChoice;

	public List<WhenChoice> getWhenChoice() {
		if (this.whenChoice == null) {
			this.whenChoice = new ArrayList<WhenChoice>();
		}
		return this.whenChoice;
	}
}
