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
public class Export_HTML extends Exporter {
	private STIGSearch sExport;
	private File fOut;
	private ArrayList<Vuln> vOut;
	private VulnStringGen VulnDisplay;

	public Export_HTML(STIGSearch sOut, File f, Exporter.STIGOutType sT,
			STIGViewerConfig Config) {
		this.sExport = sOut;
		this.fOut = f;
		this.VulnDisplay = new VulnStringGen(this.sExport, Config);
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
		block2: {
			String sOut = "";
			String sHeader = "";
			String sVulns = "";
			int TableWidth = 800;
			sHeader = this.VulnDisplay.GetHTMLHeader(TableWidth);
			sVulns = this.VulnDisplay.GetHTMLBody(TableWidth);
			sOut = sOut + "<head>";
			sOut = sOut + "<h1 style=\"text-align:center\">"
					+ this.sExport.getCurrentSTIG().getSTIG_Classification()
					+ "</h1><br>";
			sOut = sOut + sHeader;
			sOut = sOut + "</head>";
			sOut = sOut + "<body>";
			sOut = sOut + sVulns;
			sOut = sOut + "<h1 style=\"text-align:center\">"
					+ this.sExport.getCurrentSTIG().getSTIG_Classification()
					+ "</h1><br>";
			sOut = sOut + "</body>";
			try {
				FileOutputStream fWrite = new FileOutputStream(this.fOut);
				BufferedOutputStream bos = new BufferedOutputStream(fWrite);
				bos.write(sOut.getBytes("US-ASCII"));
				bos.close();
			} catch (Exception e) {
				if (!Util.bAllowPrintln)
					break block2;
				e.printStackTrace();
			}
		}
	}

	public String Ex_toPrint() {
		String sOut = "";
		String sHeader = "";
		String sVulns = "";
		int TableWidth = 450;
		sHeader = this.VulnDisplay.GetHTMLHeader(TableWidth);
		sVulns = this.VulnDisplay.GetHTMLBody(TableWidth);
		sOut = sOut + "<head>";
		sOut = sOut + "<h1 style=\"text-align:center\">"
				+ this.sExport.getCurrentSTIG().getSTIG_Classification()
				+ "</h1><br>";
		sOut = sOut + sHeader;
		sOut = sOut + "</head>";
		sOut = sOut + "<body>";
		sOut = sOut + sVulns;
		sOut = sOut + "<h1 style=\"text-align:center\">"
				+ this.sExport.getCurrentSTIG().getSTIG_Classification()
				+ "</h1><br>";
		sOut = sOut + "</body>";
		return sOut;
	}

}
