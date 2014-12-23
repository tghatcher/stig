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
import stigviewergui.SV_CORE.Pair;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class CCIReader {
	private ArrayList<CCI_Store> CCI_List;

	public void open(InputStream is) throws FileNotFoundException,
			XMLStreamException {
		XMLInputFactory f = XMLInputFactory.newInstance();
		XMLStreamReader r = f.createXMLStreamReader(is);
		this.CCI_List = new ArrayList();
		while (r.hasNext()) {
			r.next();
			if (!r.isStartElement() || !r.getLocalName().equals("cci_item"))
				continue;
			this.buildCCIItem(r);
		}
	}

	private void buildCCIItem(XMLStreamReader r) throws XMLStreamException {
		CCI_Store entry = new CCI_Store();
		entry.sID = r.getAttributeValue(0);
		while (r.hasNext()) {
			r.next();
			if (r.isEndElement() && r.getLocalName().equals("cci_item")) {
				this.CCI_List.add(entry);
				return;
			}
			if (r.isStartElement() && r.getLocalName().equals("reference")) {
				entry.sRefs.add(new Pair<String, String>(
						r.getAttributeValue(1), r.getAttributeValue(4)));
			}
			if (!r.isStartElement() || !r.getLocalName().equals("definition"))
				continue;
			entry.sDefinition = r.getElementText();
		}
	}

	public CCI_Store getCCIbyName(String CCI_ID) {
		for (int i = 0; i < this.CCI_List.size(); ++i) {
			if (!this.CCI_List.get((int) i).sID.equals(CCI_ID))
				continue;
			return this.CCI_List.get(i);
		}
		return null;
	}

	public ArrayList<CCI_Store> getCCIList() {
		return this.CCI_List;
	}

	public static class CCI_Store {
		public String sID = "";
		public String sStatus = "";
		public String sPublishDate = "";
		public String sContributor = "";
		public String sDefinition = "";
		public ArrayList<Pair<String, String>> sRefs = new ArrayList();
	}

}
