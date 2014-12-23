/*
 * Decompiled with CFR 0_92.
 */
package VMSASR;

import VMSASR.Benchmark;
import VMSASR.PopulationCharacteristics;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "populationCharacteristics", "benchmark" })
@XmlRootElement(name = "ResultsPackage")
public class ResultsPackage {
	@XmlElement(name = "PopulationCharacteristics", required = 1)
	protected PopulationCharacteristics populationCharacteristics;
	@XmlElement(required = 1)
	protected Benchmark benchmark;

	public PopulationCharacteristics getPopulationCharacteristics() {
		return this.populationCharacteristics;
	}

	public void setPopulationCharacteristics(PopulationCharacteristics value) {
		this.populationCharacteristics = value;
	}

	public Benchmark getBenchmark() {
		return this.benchmark;
	}

	public void setBenchmark(Benchmark value) {
		this.benchmark = value;
	}
}
