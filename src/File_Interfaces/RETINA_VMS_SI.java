/*
 * Decompiled with CFR 0_92.
 */
package File_Interfaces;

import RETINA_VMS.IMPORTFILE;
import RETINA_VMS.ObjectFactory;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class RETINA_VMS_SI {
	private File fMyFile;

	public RETINA_VMS_SI(File f) {
		this.fMyFile = f;
	}

	public IMPORTFILE GetResults() {
		return this.Unmarshal();
	}

	private IMPORTFILE Unmarshal() {
		ObjectFactory OF = new ObjectFactory();
		IMPORTFILE myIn = OF.createIMPORTFILE();
		try {
			JAXBContext jaxbCtx = JAXBContext.newInstance(myIn.getClass()
					.getPackage().getName());
			Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
			myIn = (IMPORTFILE) unmarshaller.unmarshal(this.fMyFile);
		} catch (JAXBException ex) {
			myIn = null;
		}
		return myIn;
	}
}
