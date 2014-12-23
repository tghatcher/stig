/*
 * Decompiled with CFR 0_92.
 */
package File_Interfaces;

import VMS.ASSET;
import VMS.ASSETID;
import VMS.ASSETTYPE;
import VMS.ELEMENT;
import VMS.FINDING;
import VMS.FINDINGDETAILS;
import VMS.FINDINGID;
import VMS.IMPORTFILE;
import VMS.ObjectFactory;
import VMS.TARGET;
import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import stigviewergui.SV_CORE.Util;

public class VMS_SI {
	ObjectFactory Make;
	IMPORTFILE myFile;
	File fMyFile;

	public VMS_SI(File fOutFile) {
		this.fMyFile = fOutFile;
		this.Make = new ObjectFactory();
		this.myFile = this.Make.createIMPORTFILE();
		this.myFile.getASSET().add(this.Make.createASSET());
		this.myFile.getASSET().get(0).getASSETID();
	}

	public void AddASSETID(String sType, String sValue) {
		ASSETID asid = this.Make.createASSETID();
		asid.setTYPE(sType);
		asid.setValue(sValue);
		this.myFile.getASSET().get(0).getASSETID().add(asid);
	}

	public void AddASSETIDs(String sAssetName, String sMAC, String sIP) {
		this.AddASSETID("ASSET NAME", sAssetName);
		this.AddASSETID("MAC ADDRESS", sMAC);
		this.AddASSETID("IP ADDRESS", sIP);
	}

	public void AddElement(String Key) {
		List<ELEMENT> lELE = this.myFile.getASSET().get(0).getELEMENT();
		boolean bAddElementKey = true;
		for (ELEMENT E : lELE) {
			if (!E.getELEMENTKEY().equals(Key))
				continue;
			bAddElementKey = false;
		}
		if (bAddElementKey) {
			ELEMENT ELE = this.Make.createELEMENT();
			ELE.setELEMENTKEY(Key);
			lELE.add(ELE);
		}
	}

	public void AddTarget(String Key) {
		List<TARGET> lTARGET = this.myFile.getASSET().get(0).getTARGET();
		boolean bAddTargetKey = true;
		for (TARGET T : lTARGET) {
			if (!T.getTARGETKEY().equals(Key))
				continue;
			bAddTargetKey = false;
		}
		if (bAddTargetKey) {
			TARGET T2 = this.Make.createTARGET();
			T2.setTARGETKEY(Key);
			T2.getFINDING();
			lTARGET.add(T2);
		}
	}

	public void SetIsWorkstation() {
		this.myFile.getASSET().get(0).setWORKSTATION("1");
	}

	public void SetAssetType(String s) {
		this.myFile.getASSET().get(0).setASSETTYPE(this.Make.createASSETTYPE());
		this.myFile.getASSET().get(0).getASSETTYPE().setASSETTYPEKEY(s);
	}

	public void AddFINDING(String sTargetkey, String sfIDType,
			String sfIDValue, String sFindingStatus, String sfDetValue,
			String sSev_Over_Code, String sSev_Over_Text,
			String sScriptResults, String sComment, String sTool,
			String sToolVersion) {
		FINDING find = this.Make.createFINDING();
		FINDINGID fID = this.Make.createFINDINGID();
		fID.setTYPE(sfIDType);
		fID.setValue(sfIDValue);
		find.setFINDINGID(fID);
		find.setFINDINGSTATUS(sFindingStatus);
		FINDINGDETAILS fDet = this.Make.createFINDINGDETAILS();
		fDet.setOVERRIDE("O");
		fDet.setValue(sfDetValue);
		find.setFINDINGDETAILS(fDet);
		if (!sSev_Over_Code.equals("")) {
			find.setSEVOVERRIDECODE(sSev_Over_Code);
			find.setSEVOVERRIDETEXT(sSev_Over_Text);
		}
		find.setSCRIPTRESULTS(sScriptResults);
		find.setCOMMENT(sComment);
		find.setTOOL(sTool);
		find.setTOOLVERSION(sToolVersion);
		find.setAUTHENTICATEDFINDING(true);
		for (TARGET T : this.myFile.getASSET().get(0).getTARGET()) {
			if (!T.getTARGETKEY().equals(sTargetkey))
				continue;
			T.getFINDING().add(find);
		}
	}

	public void Print() {
		block2: {
			try {
				JAXBContext jaxbCtx = JAXBContext.newInstance(this.myFile
						.getClass().getPackage().getName());
				Marshaller marshaller = jaxbCtx.createMarshaller();
				marshaller.setProperty("jaxb.encoding", "UTF-8");
				marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
				marshaller.marshal((Object) this.myFile, this.fMyFile);
			} catch (JAXBException ex) {
				if (!Util.bAllowPrintln)
					break block2;
				ex.printStackTrace();
			}
		}
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
