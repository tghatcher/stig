/*
 * Decompiled with CFR 0_92.
 */
package File_Interfaces;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class VMS_Keys {
	private ArrayList<VMS_KeyStore> myKeyStore = new ArrayList();
	private ArrayList<VMS_KeyStore> DisplayList = new ArrayList();

	public void open(InputStream is) throws FileNotFoundException,
			XMLStreamException {
		XMLInputFactory f = XMLInputFactory.newInstance();
		XMLStreamReader r = f.createXMLStreamReader(is);
		while (r.hasNext()) {
			r.next();
			if (!r.isStartElement() || !r.getLocalName().equals("Element"))
				continue;
			this.buildVMS_KeyItem(r);
		}
	}

	private void buildVMS_KeyItem(XMLStreamReader r) throws XMLStreamException {
		VMS_KeyStore entry = new VMS_KeyStore();
		entry.sKey = r.getAttributeValue(0);
		entry.sDesc = r.getAttributeValue(1);
		entry.sTEF = r.getAttributeValue(2);
		if (entry.sTEF.equals("1")) {
			this.myKeyStore.add(entry);
		}
	}

	/*
	 * Enabled force condition propagation Lifted jumps to return sites
	 */
	public ArrayList<VMS_KeyStore> LiveSearch(String s) {
		if (!s.equals("")) {
			ArrayList<String> SearchList = new ArrayList<String>();
			if (s.contains((CharSequence) " ")) {
				while (s.indexOf(32) >= 0) {
					SearchList.add(s.substring(0, s.indexOf(32)));
					s = s.substring(s.indexOf(32) + 1);
				}
				SearchList.add(s);
			} else {
				SearchList.add(s);
			}
			this.DisplayList.clear();
			for (int i = 0; i < this.myKeyStore.size(); ++i) {
				boolean bDispKey = true;
				for (int j = 0; j < SearchList.size(); ++j) {
					if (this.myKeyStore.get((int) i).sDesc.toLowerCase()
							.contains(
									(CharSequence) ((String) SearchList.get(j))
											.toLowerCase()))
						continue;
					bDispKey = false;
				}
				this.DisplayList.add(this.myKeyStore.get(i));
			}
			return this.DisplayList;
		}
		for (int i = 0; i < this.myKeyStore.size(); ++i) {
			this.DisplayList.add(this.myKeyStore.get(i));
		}
		return this.DisplayList;
	}

	public String GetKeyVal(String sName) {
		for (int i = 0; i < this.myKeyStore.size(); ++i) {
			if (!this.myKeyStore.get((int) i).sDesc.equals(sName))
				continue;
			return this.myKeyStore.get((int) i).sKey;
		}
		return "";
	}

	public String GetKeyName(String keyNum) {
		for (int i = 0; i < this.myKeyStore.size(); ++i) {
			if (!this.myKeyStore.get((int) i).sKey.equals(keyNum))
				continue;
			return this.myKeyStore.get((int) i).sDesc;
		}
		return "";
	}

	public ArrayList<VMS_KeyStore> GetDisplayList() {
		return this.DisplayList;
	}

	public class VMS_KeyStore {
		public String sKey;
		public String sDesc;
		public String sTEF;

		public VMS_KeyStore() {
			this.sKey = "";
			this.sDesc = "";
			this.sTEF = "";
		}
	}

}
