/*
 * Decompiled with CFR 0_92.
 */
package File_Interfaces;

import CL_Save.ASSET;
import CL_Save.CHECKLIST;
import CL_Save.ObjectFactory;
import CL_Save.STIGDATA;
import CL_Save.STIGINFO;
import CL_Save.VULN;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import stigviewergui.SV_CORE.SV_Checklist;
import stigviewergui.SV_CORE.Util;
import stigviewergui.SV_CORE.Vuln;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SaveFile_SI {
	private File myFile;
	ArrayList<Vuln> VulnList;
	String STIGTitle;
	Asset myAsset;

	public SaveFile_SI(File fXML) {
		this.myFile = fXML;
	}

	public ArrayList<Vuln> GetVulnList() {
		return this.VulnList;
	}

	public String GetSTIGName() {
		return this.STIGTitle;
	}

	public Asset GetAsset() {
		return this.myAsset;
	}

	public void LoadFromSave() {
		this.VulnList = new ArrayList();
		this.STIGTitle = "";
		this.myAsset = new Asset();
		CHECKLIST myCL = this.Unmarshal();
		if (myCL != null) {
			ASSET AST = myCL.getASSET();
			this.myAsset.AssetType = AST.getASSETTYPE();
			this.myAsset.HostGUID = AST.getHOSTGUID();
			this.myAsset.HostIP = AST.getHOSTIP();
			this.myAsset.HostMAC = AST.getHOSTMAC();
			this.myAsset.HostName = AST.getHOSTNAME();
			this.myAsset.TargetKey = AST.getTARGETKEY();
			for (ASSET.ASSETVAL a : AST.getASSETVAL()) {
				if (a.getAVNAME().equals("WORKSTATION")) {
					if (a.getAVDATA().equals("TRUE")) {
						this.myAsset.bIsWorkstation = true;
					} else if (a.getAVDATA().equals("FALSE")) {
						this.myAsset.bIsWorkstation = false;
					}
				}
				if (!a.getAVNAME().equals("ROLE"))
					continue;
				this.myAsset.Role = a.getAVDATA().toString();
			}
			this.STIGTitle = myCL.getSTIGINFO().getSTIGTITLE();
			List<VULN> vList = myCL.getVULN();
			for (VULN V : vList) {
				Vuln vul;
				block15: {
					vul = new Vuln();
					vul.setCHK_Notes(V.getFINDINGDETAILS());
					vul.setCheckComment(V.getCOMMENTS());
					vul.setCheckSevJust(V.getSEVERITYJUSTIFICATION());
					vul.setCheckSevOverride(V.getSEVERITYOVERRIDE());
					try {
						vul.setCheckState(SV_Checklist.CheckState.valueOf(V
								.getSTATUS()));
					} catch (Exception e) {
						if (!Util.bAllowPrintln)
							break block15;
						e.printStackTrace();
					}
				}
				List<STIGDATA> lSTIGD = V.getSTIGDATA();
				for (STIGDATA SD : lSTIGD) {
					try {
						if (!SD.getVULNATTRIBUTE().equals("CCI_REF")) {
							vul.setAttr(Vuln.VulnAttr.valueOf(SD
									.getVULNATTRIBUTE()), SD.getATTRIBUTEDATA());
							continue;
						}
						vul.addCCIVal(SD.getATTRIBUTEDATA());
					} catch (Exception e) {
						if (!Util.bAllowPrintln)
							continue;
						e.printStackTrace();
					}
				}
				this.VulnList.add(vul);
			}
		} else if (Util.bAllowPrintln) {
			System.out.println("Import Failure");
		}
	}

	public void WriteCheckList(Asset AssetIN, ArrayList<Vuln> vaIn,
			String sSTIGTitle) throws JAXBException {
		ObjectFactory SaveFile = new ObjectFactory();
		CHECKLIST myCL = SaveFile.createCHECKLIST();
		myCL.setSVVERSION(Util.GetAppName() + " : " + Util.GetAppVersion());
		myCL.setASSET(SaveFile.createASSET());
		myCL.setSTIGINFO(SaveFile.createSTIGINFO());
		ASSET myASSET = myCL.getASSET();
		myASSET.setASSETTYPE(AssetIN.AssetType);
		myASSET.setHOSTGUID(AssetIN.HostGUID);
		myASSET.setHOSTIP(AssetIN.HostIP);
		myASSET.setHOSTMAC(AssetIN.HostMAC);
		myASSET.setHOSTNAME(AssetIN.HostName);
		myASSET.setTARGETKEY(AssetIN.TargetKey);
		List<ASSET.ASSETVAL> av = myASSET.getASSETVAL();
		ASSET.ASSETVAL Role = new ASSET.ASSETVAL();
		Role.setAVNAME("ROLE");
		Role.setAVDATA(AssetIN.Role);
		av.add(Role);
		STIGINFO mySTIGINFO = myCL.getSTIGINFO();
		mySTIGINFO.setSTIGTITLE(sSTIGTitle);
		for (Vuln v : vaIn) {
			Vuln.VulnAttr[] vaa;
			VULN myV = SaveFile.createVULN();
			myV.setSTATUS(v.getCheckState().toString());
			myV.setCOMMENTS(v.getCheckComment());
			myV.setFINDINGDETAILS(v.getCHK_Notes());
			myV.setSEVERITYJUSTIFICATION(v.getCheckSevJust());
			myV.setSEVERITYOVERRIDE(v.getCheckSevOverride());
			for (Vuln.VulnAttr va : vaa = Vuln.VulnAttr.values()) {
				if (va.equals((Object) Vuln.VulnAttr.NULL))
					continue;
				STIGDATA SD = SaveFile.createSTIGDATA();
				SD.setVULNATTRIBUTE(va.name());
				SD.setATTRIBUTEDATA(v.getAttr(va));
				myV.getSTIGDATA().add(SD);
			}
			for (String s : v.GetCCIVals()) {
				STIGDATA SD = SaveFile.createSTIGDATA();
				SD.setVULNATTRIBUTE("CCI_REF");
				SD.setATTRIBUTEDATA(s);
				myV.getSTIGDATA().add(SD);
			}
			myCL.getVULN().add(myV);
		}
		this.Marshal(myCL);
	}

	private void Marshal(CHECKLIST myCL) throws JAXBException {
		JAXBContext jaxbCtx = JAXBContext.newInstance(myCL.getClass()
				.getPackage().getName());
		Marshaller marshaller = jaxbCtx.createMarshaller();
		marshaller.setProperty("jaxb.encoding", "UTF-8");
		marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
		marshaller.marshal((Object) myCL, this.myFile);
	}

	private CHECKLIST Unmarshal() {
		ObjectFactory OF = new ObjectFactory();
		CHECKLIST myCL = OF.createCHECKLIST();
		try {
			JAXBContext jaxbCtx = JAXBContext.newInstance(myCL.getClass()
					.getPackage().getName());
			Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
			myCL = (CHECKLIST) unmarshaller.unmarshal(this.myFile);
		} catch (JAXBException ex) {
			myCL = null;
		}
		return myCL;
	}

	public static class Asset {
		public String AssetType = "";
		public String HostGUID = "";
		public String HostIP = "";
		public String HostMAC = "";
		public String HostName = "";
		public String TargetKey = "";
		public String Role = "";
		public boolean bIsWorkstation = false;
	}

}
