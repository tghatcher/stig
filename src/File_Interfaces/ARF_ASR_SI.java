/*
 * Decompiled with CFR 0_92.
 */
package File_Interfaces;

import ARF.AssessmentReport;
import ARF.Device;
import ARF.ObjectFactory;
import ARF.ReportObject;
import ARF.TConfiguration;
import ARF.TConnectionIp;
import ARF.TDeviceID;
import ARF.TFQDN;
import ARF.THostNetworkData;
import ARF.TIdentifiers;
import ARF.TNetworkConfiguration;
import ARF.TRealm;
import ARF.TSubnetMask;
import VMSASR.BenchMarkID;
import VMSASR.Benchmark;
import VMSASR.DeviceRecord;
import VMSASR.ObjectFactory;
import VMSASR.PopulationCharacteristics;
import VMSASR.PrResource;
import VMSASR.Result;
import VMSASR.ResultsPackage;
import VMSASR.RuleComplianceItem;
import VMSASR.RuleResult;
import VMSASR.ScanData;
import VMSASR.ScanDataID;
import VMSASR.Scanner;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import stigviewergui.SV_CORE.SV_Checklist;
import stigviewergui.SV_CORE.Util;
import stigviewergui.SV_CORE.Vuln;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ARF_ASR_SI {
	ObjectFactory Make;
	AssessmentReport myAR;
	ResultsPackage myRP;
	File fARF;
	File fASR;
	int RecordID = 0;

	public ARF_ASR_SI(File ARF_File, File ASR_File) {
		this.fARF = ARF_File;
		this.fASR = ASR_File;
		this.Make = new ObjectFactory();
		this.myAR = this.Make.createAssessmentReport();
		this.RecordID = 0;
	}

	public void AddReportObject(String sHostName, String sResourceID,
			String sMAC, String sIP, String sNetworkInterfaceID,
			String sSubnetMask) {
		List<ReportObject> myROs = this.myAR.getReportObject();
		ReportObject RO = this.Make.createReportObject();
		Device DT = this.Make.createDevice();
		TDeviceID myDID = this.Make.createTDeviceID();
		myDID.setResource(Util.GetAppName());
		myDID.setRecordIdentifier(sResourceID);
		this.RecordID = Integer.parseInt(sResourceID);
		DT.setDeviceID(myDID);
		TIdentifiers DIT = this.Make.createTIdentifiers();
		TFQDN myFQDN = this.Make.createTFQDN();
		myFQDN.setRealm(TRealm.HBSS);
		myFQDN.setHostName(sHostName);
		DIT.setFQDN(myFQDN);
		DT.setIdentifiers(DIT);
		TConfiguration CT = this.Make.createTConfiguration();
		TNetworkConfiguration NCT = this.Make.createTNetworkConfiguration();
		NCT.setNetworkInterfaceID(sNetworkInterfaceID);
		THostNetworkData HNDT = this.Make.createTHostNetworkData();
		TConnectionIp IP = this.Make.createTConnectionIp();
		IP.setIPv4(sIP);
		HNDT.setConnectionIp(IP);
		HNDT.setConnectionMacAddress(sMAC);
		TSubnetMask IPAT = this.Make.createTSubnetMask();
		IPAT.setIPv4(sSubnetMask);
		HNDT.setSubnetMask(IPAT);
		NCT.setHostNetworkData(HNDT);
		CT.setNetworkConfiguration(NCT);
		DT.setConfiguration(CT);
		RO.setDevice(DT);
		myROs.add(RO);
	}

	private String getFormatedDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String s = dateFormat.format(date);
		s = s.replace(' ', 'T');
		return s;
	}

	public void AddResults(ArrayList<Vuln> Vuls, String BenchmarkName,
			String BenchmarkVersion) {
		VMSASR.ObjectFactory make = new VMSASR.ObjectFactory();
		this.myRP = make.createResultsPackage();
		PopulationCharacteristics PC = make.createPopulationCharacteristics();
		PC.setPopulationSize(1);
		PrResource PRR = make.createPrResource(Util.GetAppName());
		PC.setResource(PRR);
		ScanData SD = make.createScanData();
		SD.setStart(this.getFormatedDate());
		SD.setExecutionLocation("host");
		ScanDataID SDID = make.createScanDataID();
		SDID.setRecordIdentifier(this.getFormatedDate());
		SDID.setResource(Util.GetAppName());
		SD.setScanDataID(SDID);
		Scanner Scan = make.createScanner();
		Scan.setProductName(Util.GetAppName());
		Scan.setProductVersion(Util.GetAppVersion());
		SD.setScanner(Scan);
		SD.setEnd(this.getFormatedDate());
		PC.setScanData(SD);
		this.myRP.setPopulationCharacteristics(PC);
		Benchmark bm = make.createBenchmark();
		bm.setVersion(1);
		BenchMarkID BMID = make.createBenchMarkID();
		BMID.setRecordIdentifier(BenchmarkName);
		BMID.setResource("DISA STIG");
		bm.setBenchMarkID(BMID);
		List<RuleResult> lRR = bm.getRuleResult();
		for (Vuln v : Vuls) {
			RuleResult RR = make.createRuleResult();
			RR.setRuleID(v.getAttr(Vuln.VulnAttr.Rule_ID));
			List<RuleComplianceItem> lRCI = RR.getRuleComplianceItem();
			RuleComplianceItem RCI = make.createRuleComplianceItem();
			Result res = make.createResult();
			res.setCount("1");
			List<DeviceRecord> lDR = res.getDeviceRecord();
			DeviceRecord DR = make.createDeviceRecord();
			DR.setRecordIdentifier(Integer.toString(this.RecordID));
			lDR.add(DR);
			RCI.setResult(res);
			if (v.getCheckState().equals(
					(Object) SV_Checklist.CheckState.NotAFinding)) {
				RCI.setRuleResult("pass");
			} else if (v.getCheckState().equals(
					(Object) SV_Checklist.CheckState.Open)) {
				RCI.setRuleResult("fail");
			} else if (v.getCheckState().equals(
					(Object) SV_Checklist.CheckState.Not_Applicable)) {
				RCI.setRuleResult("notapplicable");
			} else if (v.getCheckState().equals(
					(Object) SV_Checklist.CheckState.Not_Reviewed)) {
				RCI.setRuleResult("notchecked");
			}
			lRCI.add(RCI);
			lRR.add(RR);
		}
		this.myRP.setBenchmark(bm);
	}

	public void WriteARF() {
		block2: {
			try {
				JAXBContext jaxbCtx = JAXBContext.newInstance(this.myAR
						.getClass().getPackage().getName());
				Marshaller marshaller = jaxbCtx.createMarshaller();
				marshaller.setProperty("jaxb.encoding", "UTF-8");
				marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
				marshaller.marshal((Object) this.myAR, this.fARF);
			} catch (Exception ex) {
				if (!Util.bAllowPrintln)
					break block2;
				ex.printStackTrace();
			}
		}
	}

	public void WriteASR() {
		block2: {
			try {
				JAXBContext jaxbCtx = JAXBContext.newInstance(this.myRP
						.getClass().getPackage().getName());
				Marshaller marshaller = jaxbCtx.createMarshaller();
				marshaller.setProperty("jaxb.encoding", "UTF-8");
				marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
				marshaller.marshal((Object) this.myRP, this.fASR);
			} catch (Exception ex) {
				if (!Util.bAllowPrintln)
					break block2;
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] Args) {
		File fARF = new File("C:\\TestARF.xml");
		File fASR = new File("C:\\TestASR.xml");
		ARF_ASR_SI test = new ARF_ASR_SI(fARF, fASR);
		test.AddReportObject("TestResource", "101", "00:11:22:33",
				"192.168.1.1", "NetworkID", "255.255.255.128");
		ArrayList<Vuln> va = new ArrayList<Vuln>();
		Vuln v = new Vuln();
		v.setAttr(Vuln.VulnAttr.Rule_ID, "SampleRuleID");
		v.setCheckState(SV_Checklist.CheckState.Open);
		va.add(v);
		Vuln v2 = new Vuln();
		v2.setAttr(Vuln.VulnAttr.Rule_ID, "SampleRuleID2");
		v2.setCheckState(SV_Checklist.CheckState.NotAFinding);
		va.add(v2);
		test.AddResults(va, "TestName", "TestVersion");
		test.WriteARF();
		test.WriteASR();
	}
}
