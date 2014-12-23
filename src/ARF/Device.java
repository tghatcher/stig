/*
 * Decompiled with CFR 0_92.
 */
package ARF;

import ARF.TConfiguration;
import ARF.TDeviceID;
import ARF.TIdentifiers;
import ARF.TTaggedBoolean;
import ARF.TTaggedString;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "deviceID", "identifiers", "configuration",
		"taggedString", "taggedBoolean" })
@XmlRootElement(name = "device", namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/assessment_report/0.41")
public class Device {
	@XmlElement(name = "device_ID", required = 1)
	protected TDeviceID deviceID;
	protected TIdentifiers identifiers;
	@XmlElement(required = 1)
	protected TConfiguration configuration;
	@XmlElement(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/tagged_value/0.41", required = 1)
	protected List<TTaggedString> taggedString;
	@XmlElement(namespace = "http://metadata.dod.mil/mdr/ns/netops/shared_data/tagged_value/0.41", required = 1)
	protected TTaggedBoolean taggedBoolean;
	@XmlAttribute(name = "timestamp", required = 1)
	protected String timestamp;

	public TDeviceID getDeviceID() {
		return this.deviceID;
	}

	public void setDeviceID(TDeviceID value) {
		this.deviceID = value;
	}

	public TIdentifiers getIdentifiers() {
		return this.identifiers;
	}

	public void setIdentifiers(TIdentifiers value) {
		this.identifiers = value;
	}

	public TConfiguration getConfiguration() {
		return this.configuration;
	}

	public void setConfiguration(TConfiguration value) {
		this.configuration = value;
	}

	public List<TTaggedString> getTaggedString() {
		if (this.taggedString == null) {
			this.taggedString = new ArrayList<TTaggedString>();
		}
		return this.taggedString;
	}

	public TTaggedBoolean getTaggedBoolean() {
		return this.taggedBoolean;
	}

	public void setTaggedBoolean(TTaggedBoolean value) {
		this.taggedBoolean = value;
	}

	public String getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(String value) {
		this.timestamp = value;
	}
}
