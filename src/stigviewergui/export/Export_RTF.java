/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.export;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import stigviewergui.STIGSearch;
import stigviewergui.STIGViewerConfig;
import stigviewergui.SV_CORE.STIG;
import stigviewergui.SV_CORE.Util;
import stigviewergui.SV_CORE.Vuln;
import stigviewergui.SV_CORE.VulnStringGen;
import stigviewergui.export.Exporter;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Export_RTF extends Exporter {
	private STIGSearch sExport;
	private File fOut;
	private ArrayList<Vuln> vOut;
	private STIGViewerConfig myConfig;
	private VulnStringGen VulnDisplay;

	public Export_RTF(STIGSearch sOut, File f, Exporter.STIGOutType sT,
			STIGViewerConfig Conf) {
		this.sExport = sOut;
		this.fOut = f;
		this.myConfig = Conf;
		this.VulnDisplay = new VulnStringGen(this.sExport, this.myConfig);
		switch (sT) {
		case Profile: {
			this.vOut = sOut.GetProfileList();
			break;
		}
		case CurrentSearch: {
			this.vOut = sOut.GetDisplayList();
			break;
		}
		default: {
			this.vOut = sOut.GetRootList();
		}
		}
	}

	public ArrayList<Vuln> GetExportList() {
		return this.vOut;
	}

	public File GetExportFile() {
		return this.fOut;
	}

	public void Export() {
		block3: {
			String sOut = "";
			String sHeader = "";
			sHeader = this.FormatSTIGHeader();
			ArrayList<String> sVulns = this.FormatVulnList();
			sOut = sOut
					+ "{\\rtf1\\ansi\\ansicpg1252\\deff0\\titlepg {\\fonttbl {\\f0 "
					+ this.myConfig.sGetFont() + ";}}";
			sOut = sOut + "{\\header \\pard\\plain \\ltrpar\\qc "
					+ this.sExport.getCurrentSTIG().getSTIG_Classification()
					+ "}";
			sOut = sOut + "{\\footer \\pard\\plain \\ltrpar\\qc "
					+ this.sExport.getCurrentSTIG().getSTIG_Classification()
					+ "}";
			sOut = sOut + sHeader;
			sOut = sOut + "\\pard\\par \\page ";
			sOut = sOut + "\\f0 ";
			sVulns = this.FormatVulnList();
			for (int i = 0; i < sVulns.size(); ++i) {
				sOut = sOut + sVulns.get(i);
			}
			sOut = sOut + "\\par }";
			try {
				FileOutputStream fWrite = new FileOutputStream(this.fOut);
				BufferedOutputStream bos = new BufferedOutputStream(fWrite);
				bos.write(sOut.getBytes("UTF-8"));
				bos.close();
			} catch (Exception e) {
				if (!Util.bAllowPrintln)
					break block3;
				e.printStackTrace();
			}
		}
	}

	public String Ex_toPrint() {
		String sOut = "";
		String sHeader = "";
		sHeader = this.FormatSTIGHeader();
		ArrayList<String> sVulns = this.FormatVulnList();
		sOut = sOut
				+ "{\\rtf1\\ansi\\ansicpg1252{\\fonttbl{\\f0\\fnil\\fcharset1\\fprq2 Arial;}{\\f1\\fnil\\fcharset1\\fprq2 Wingdings;}{\\f2\\fnil\\fcharset1\\fprq2 Monospaced;}}{\\colortbl;\\red0\\green0\\blue0;\\red0\\green0\\blue128;\\red0\\green0\\blue192;\\red0\\green0\\blue255;\\red0\\green128\\blue0;\\red0\\green128\\blue128;\\red0\\green128\\blue192;\\red0\\green128\\blue255;\\red0\\green192\\blue0;\\red0\\green192\\blue128;\\red0\\green192\\blue192;\\red0\\green192\\blue255;\\red0\\green255\\blue0;\\red0\\green255\\blue128;\\red0\\green255\\blue192;\\red0\\green255\\blue255;\\red128\\green0\\blue0;\\red128\\green0\\blue128;\\red128\\green0\\blue192;\\red128\\green0\\blue255;\\red128\\green128\\blue0;\\red128\\green128\\blue128;\\red128\\green128\\blue192;\\red128\\green128\\blue255;\\red128\\green192\\blue0;\\red128\\green192\\blue128;\\red128\\green192\\blue192;\\red128\\green192\\blue255;\\red128\\green255\\blue0;\\red128\\green255\\blue128;\\red128\\green255\\blue192;\\red128\\green255\\blue255;\\red192\\green0\\blue0;\\red192\\green0\\blue128;\\red192\\green0\\blue192;\\red192\\green0\\blue255;\\red192\\green128\\blue0;\\red192\\green128\\blue128;\\red192\\green128\\blue192;\\red192\\green128\\blue255;\\red192\\green192\\blue0;\\red192\\green192\\blue128;\\red192\\green192\\blue192;\\red192\\green192\\blue255;\\red192\\green255\\blue0;\\red192\\green255\\blue128;\\red192\\green255\\blue192;\\red192\\green255\\blue255;\\red255\\green0\\blue0;\\red255\\green0\\blue128;\\red255\\green0\\blue192;\\red255\\green0\\blue255;\\red255\\green128\\blue0;\\red255\\green128\\blue128;\\red255\\green128\\blue192;\\red255\\green128\\blue255;\\red255\\green192\\blue0;\\red255\\green192\\blue128;\\red255\\green192\\blue192;\\red255\\green192\\blue255;\\red255\\green255\\blue0;\\red255\\green255\\blue128;\\red255\\green255\\blue192;\\red255\\green255\\blue255;}\\pard \\qr ";
		sOut = sOut + "{\\header \\pard\\plain \\ltrpar\\qc "
				+ this.sExport.getCurrentSTIG().getSTIG_Classification() + "}";
		sOut = sOut + "{\\footer \\pard\\plain \\ltrpar\\qc "
				+ this.sExport.getCurrentSTIG().getSTIG_Classification() + "}";
		sOut = sOut + sHeader;
		sOut = sOut + " \\page ";
		sOut = sOut + "\\f0 ";
		sVulns = this.FormatVulnList();
		for (int i = 0; i < sVulns.size(); ++i) {
			sOut = sOut + sVulns.get(i);
		}
		sOut = sOut + " \\li0\\ri0\\fi0\\sa0\\sb0\\sl240\\itap0\\par}";
		return sOut;
	}

	private String FormatSTIGHeader() {
		String sRet = "";
		STIG sOut = this.sExport.getCurrentSTIG();
		sRet = sRet + "\\par \\par \\par";
		sRet = sRet + "{\\pard\\plain \\ltrpar\\qc "
				+ sOut.getSTIG_Classification() + "\\par }";
		sRet = sRet + "\\par \\par \\par";
		sRet = sRet + "{\\pard\\plain \\ltrpar\\qc " + sOut.getSTIG_Title()
				+ "\\par }";
		sRet = sRet + "{\\pard\\plain \\ltrpar\\qc " + sOut.getSTIG_Version()
				+ "\\par }";
		sRet = sRet + "{\\pard\\plain \\ltrpar\\qc "
				+ sOut.getSTIG_Release_Info() + "\\par \\par }";
		sRet = sRet + "{\\pard\\plain \\ltrpar\\qc "
				+ sOut.getSTIG_Description() + "\\par }";
		return sRet;
	}

	private ArrayList<String> FormatVulnList() {
		ArrayList<String> asRet = new ArrayList<String>();
		for (int i = 0; i < this.vOut.size(); ++i) {
			String sBody = "";
			sBody = sBody + "\\trowd\\trgaph144 ";
			sBody = sBody
					+ "\\clbrdrt\\brdrs\\clbrdrl\\brdrs\\clbrdrb\\brdrs\\clbrdrr\\brdrs ";
			sBody = sBody + "\\cellx9360 ";
			sBody = sBody
					+ this.VulnDisplay.GetRTFHeader(this.vOut.get(i), true);
			sBody = sBody + " \\intbl\\cell \\row ";
			sBody = sBody + "\\trowd\\trgaph144 ";
			sBody = sBody
					+ "\\clbrdrt\\brdrs\\clbrdrl\\brdrs\\clbrdrb\\brdrs\\clbrdrr\\brdrs ";
			sBody = sBody + "\\cellx9360 ";
			sBody = sBody + this.VulnDisplay.GetRTFBody(this.vOut.get(i), true);
			sBody = sBody + " \\intbl\\cell \\row {\\pard \\par}";
			asRet.add(sBody);
		}
		return asRet;
	}

}
