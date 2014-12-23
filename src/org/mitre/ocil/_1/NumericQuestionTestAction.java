/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;
import org.mitre.ocil._1.QuestionTestActionType;
import org.mitre.ocil._1.ResultChoiceType;
import org.mitre.ocil._1.WhenEquals;
import org.mitre.ocil._1.WhenRange;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "rest" })
public class NumericQuestionTestAction extends QuestionTestActionType {
	@XmlElementRefs(value = {
			@XmlElementRef(name = "when_range", namespace = "http://www.mitre.org/ocil/1.1", type = WhenRange.class, required = 0),
			@XmlElementRef(name = "when_equals", namespace = "http://www.mitre.org/ocil/1.1", type = WhenEquals.class, required = 0) })
	protected List<ResultChoiceType> rest;

	public List<ResultChoiceType> getRest() {
		if (this.rest == null) {
			this.rest = new ArrayList<ResultChoiceType>();
		}
		return this.rest;
	}
}
