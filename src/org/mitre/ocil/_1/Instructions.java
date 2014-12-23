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
import org.mitre.ocil._1.StepType;
import org.mitre.ocil._1.TextType;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "title", "step" })
@XmlRootElement(name = "instructions")
public class Instructions {
	@XmlElement(required = 1)
	protected TextType title;
	@XmlElement(required = 1)
	protected List<StepType> step;

	public TextType getTitle() {
		return this.title;
	}

	public void setTitle(TextType value) {
		this.title = value;
	}

	public List<StepType> getStep() {
		if (this.step == null) {
			this.step = new ArrayList<StepType>();
		}
		return this.step;
	}
}
