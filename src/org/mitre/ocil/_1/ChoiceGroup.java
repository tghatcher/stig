/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.mitre.ocil._1.Choice;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "choice" })
@XmlRootElement(name = "choice_group")
public class ChoiceGroup {
	@XmlElement(required = 1)
	protected List<Choice> choice;
	@XmlAttribute(name = "id", required = 1)
	protected String id;

	public List<Choice> getChoice() {
		if (this.choice == null) {
			this.choice = new ArrayList<Choice>();
		}
		return this.choice;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String value) {
		this.id = value;
	}
}
