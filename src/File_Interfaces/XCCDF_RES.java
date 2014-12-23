/*
 * Decompiled with CFR 0_92.
 */
package File_Interfaces;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import stigviewergui.SV_CORE.Util;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public final class XCCDF_RES {
	File myInFile;
	ArrayList<xccdfResItem> myItemList;
	String sOS_Name;
	String sOS_Version;
	String sArchitecture;
	String sPrimary_Host_Name;
	ArrayList<String> sIP_Address;
	ArrayList<String> sMAC_Address;
	boolean bResultSet;

	public String getsArchitecture() {
		return this.sArchitecture;
	}

	public void setsArchitecture(String sArchitecture) {
		this.sArchitecture = sArchitecture;
	}

	public ArrayList<String> getsIP_Address() {
		return this.sIP_Address;
	}

	public void setsIP_Address(ArrayList<String> sIP_Address) {
		this.sIP_Address = sIP_Address;
	}

	public ArrayList<String> getsMAC_Address() {
		return this.sMAC_Address;
	}

	public void setsMAC_Address(ArrayList<String> sMAC_Address) {
		this.sMAC_Address = sMAC_Address;
	}

	public String getsOS_Name() {
		return this.sOS_Name;
	}

	public void setsOS_Name(String sOS_Name) {
		this.sOS_Name = sOS_Name;
	}

	public String getsOS_Version() {
		return this.sOS_Version;
	}

	public void setsOS_Version(String sOS_Version) {
		this.sOS_Version = sOS_Version;
	}

	public String getsPrimary_Host_Name() {
		return this.sPrimary_Host_Name;
	}

	public void setsPrimary_Host_Name(String sPrimary_Host_Name) {
		this.sPrimary_Host_Name = sPrimary_Host_Name;
	}

	public XCCDF_RES(File f) {
		block2: {
			this.myInFile = f;
			this.myItemList = new ArrayList();
			this.sOS_Name = "";
			this.sOS_Version = "";
			this.sArchitecture = "";
			this.sPrimary_Host_Name = "";
			this.sIP_Address = new ArrayList();
			this.sMAC_Address = new ArrayList();
			try {
				FileInputStream fiRaw = new FileInputStream(this.myInFile);
				BufferedInputStream bis = new BufferedInputStream(fiRaw);
				byte[] bin = new byte[bis.available()];
				bis.read(bin);
				String Raw = new String(bin);
				bis.close();
				fiRaw.close();
				FileInputStream FIS = new FileInputStream(this.myInFile);
				this.open(FIS, Raw);
			} catch (Exception ex) {
				if (!Util.bAllowPrintln)
					break block2;
				ex.printStackTrace();
			}
		}
	}

	private String GetRawResult(String sRef, String sRaw) {
		String sRet = "";
		int iRef = sRaw.lastIndexOf(sRef);
		int iEnd = sRaw.indexOf("rule-result>", iRef) + "rule-result>".length();
		int iStart = iRef;
		char c = sRaw.charAt(iStart);
		while (c != '<') {
			c = sRaw.charAt(--iStart);
		}
		sRet = sRaw.substring(iStart, iEnd);
		return sRet;
	}

	public void open(InputStream is, String sRaw) throws FileNotFoundException,
			XMLStreamException, IOException {
		XMLInputFactory f = XMLInputFactory.newInstance();
		XMLStreamReader r = f.createXMLStreamReader(is);
		while (r.hasNext()) {
			r.next();
			if (r.isStartElement() && r.getLocalName().equals("target-facts")) {
				this.GrabSystemInfo(r);
			}
			if (!r.isStartElement() || !r.getLocalName().equals("rule-result"))
				continue;
			this.bResultSet = false;
			this.buildCCIItem(r, sRaw);
		}
	}

	private void GrabSystemInfo(XMLStreamReader r) throws XMLStreamException {
		boolean bReturn = false;
		while (r.hasNext()) {
			r.next();
			if (r.isStartElement() && r.getLocalName().equals("fact")
					&& r.getAttributeValue(0).equals("system_info")) {
				String s = r.getElementText();
				StringReader SR = new StringReader(s);
				XMLInputFactory XIF = XMLInputFactory.newInstance();
				XMLStreamReader rx = XIF.createXMLStreamReader(SR);
				while (rx.hasNext()) {
					rx.next();
					if (!rx.isStartElement())
						continue;
					if (rx.getLocalName().equals("os_name")) {
						this.sOS_Name = rx.getElementText();
					}
					if (rx.getLocalName().equals("os_version")) {
						this.sOS_Version = rx.getElementText();
					}
					if (rx.getLocalName().equals("architecture")) {
						this.sArchitecture = rx.getElementText();
					}
					if (rx.getLocalName().equals("primary_host_name")) {
						this.sPrimary_Host_Name = rx.getElementText();
					}
					if (rx.getLocalName().equals("ip_address")) {
						this.sIP_Address.add(rx.getElementText());
					}
					if (!rx.getLocalName().equals("mac_address"))
						continue;
					this.sMAC_Address.add(rx.getElementText());
				}
				bReturn = true;
			}
			if (r.isStartElement()
					&& r.getLocalName().equals("fact")
					&& r.getAttributeValue(0)
							.contains((CharSequence) "os_name")) {
				this.sOS_Name = r.getElementText();
			}
			if (r.isStartElement()
					&& r.getLocalName().equals("fact")
					&& r.getAttributeValue(0).contains(
							(CharSequence) "os_version")) {
				this.sOS_Version = r.getElementText();
			}
			if (r.isStartElement()
					&& r.getLocalName().equals("fact")
					&& r.getAttributeValue(0).contains(
							(CharSequence) "processor")) {
				this.sArchitecture = r.getElementText();
			}
			if (r.isStartElement()
					&& r.getLocalName().equals("fact")
					&& r.getAttributeValue(0).contains(
							(CharSequence) "host_name")) {
				this.sPrimary_Host_Name = r.getElementText();
			}
			if (r.isStartElement() && r.getLocalName().equals("fact")
					&& r.getAttributeValue(0).contains((CharSequence) "ipv4")) {
				this.sIP_Address.add(r.getElementText());
			}
			if (r.isStartElement() && r.getLocalName().equals("fact")
					&& r.getAttributeValue(0).contains((CharSequence) "mac")) {
				this.sMAC_Address.add(r.getElementText());
			}
			if (r.isStartElement()
					&& !r.getLocalName().contains((CharSequence) "fact")) {
				bReturn = true;
			}
			if (!bReturn)
				continue;
			return;
		}
	}

	private void buildCCIItem(XMLStreamReader r, String sRaw)
			throws XMLStreamException {
		int iAttNum = 0;
		xccdfResItem entry = new xccdfResItem();
		for (int i = 0; i < r.getAttributeCount(); ++i) {
			if (!r.getAttributeLocalName(i).equals("idref"))
				continue;
			iAttNum = i;
		}
		entry.RuleID = r.getAttributeValue(iAttNum);
		entry.OVALRef = this.GetRawResult(entry.RuleID, sRaw);
		while (r.hasNext()) {
			r.next();
			if (r.isEndElement() && r.getLocalName().equals("rule-result")) {
				this.myItemList.add(entry);
				return;
			}
			if (!r.isStartElement() || !r.getLocalName().equals("result")
					|| this.bResultSet)
				continue;
			entry.Result = r.getElementText();
			this.bResultSet = true;
		}
	}

	public ArrayList<xccdfResItem> GetResults() {
		return this.myItemList;
	}

	public class xccdfResItem {
		public String RuleID;
		public String Result;
		public String OVALRef;

		public xccdfResItem() {
			this.RuleID = "";
			this.Result = "";
			this.OVALRef = "";
		}
	}

}
