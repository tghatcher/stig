/*
 * Decompiled with CFR 0_92.
 */
package org.mitre.ocil._1;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "RangeType", propOrder = { "min", "max" })
public class RangeType {
	protected Min min;
	protected Max max;

	public Min getMin() {
		return this.min;
	}

	public void setMin(Min value) {
		this.min = value;
	}

	public Max getMax() {
		return this.max;
	}

	public void setMax(Max value) {
		this.max = value;
	}

	@XmlAccessorType(value = XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "value" })
	public static class Min {
		@XmlValue
		protected BigDecimal value;
		@XmlAttribute(name = "inclusive")
		protected Boolean inclusive;

		public BigDecimal getValue() {
			return this.value;
		}

		public void setValue(BigDecimal value) {
			this.value = value;
		}

		public boolean isInclusive() {
			if (this.inclusive == null) {
				return true;
			}
			return this.inclusive;
		}

		public void setInclusive(Boolean value) {
			this.inclusive = value;
		}
	}

	@XmlAccessorType(value = XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "value" })
	public static class Max {
		@XmlValue
		protected BigDecimal value;
		@XmlAttribute(name = "inclusive")
		protected Boolean inclusive;

		public BigDecimal getValue() {
			return this.value;
		}

		public void setValue(BigDecimal value) {
			this.value = value;
		}

		public boolean isInclusive() {
			if (this.inclusive == null) {
				return true;
			}
			return this.inclusive;
		}

		public void setInclusive(Boolean value) {
			this.inclusive = value;
		}
	}

}
