/*
 * Decompiled with CFR 0_92.
 */
package ARF;

import ARF.ReportObject;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "reportObject" })
@XmlRootElement(name = "AssessmentReport", namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/assessment_report/0.41")
public class AssessmentReport {
	@XmlElement(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/assessment_report/0.41", required = 1)
	protected List<ReportObject> reportObject;

	public List<ReportObject> getReportObject() {
		if (this.reportObject == null) {
			this.reportObject = new ArrayList<ReportObject>();
		}
		return this.reportObject;
	}
}
