/*
 * Decompiled with CFR 0_92.
 */
package File_Interfaces;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.swing.JFrame;
import stigviewergui.SV_CORE.ProgressUpdater;
import stigviewergui.SV_CORE.Util;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class CSVWriter {
	private ArrayList<ArrayList<String>> VList;
	private File OutFile;
	private JFrame myParent;

	public CSVWriter(JFrame Parent, ArrayList<ArrayList<String>> VulnList,
			File fOut) {
		this.VList = VulnList;
		this.OutFile = fOut;
		this.myParent = Parent;
	}

	public void Write() throws FileNotFoundException, IOException {
		ProgressUpdater prog = new ProgressUpdater(this.myParent,
				"Exporting CSV");
		prog.SetMessage("Exporting CSV File...");
		prog.run();
		float fMax = this.VList.size();
		FileOutputStream fos = new FileOutputStream(this.OutFile);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		for (int i = 0; i < this.VList.size(); ++i) {
			try {
				String sOutString = "";
				ArrayList<String> entry = this.VList.get(i);
				for (String s : entry) {
					sOutString = sOutString + "\"" + s + "\",";
				}
				sOutString = sOutString.substring(0, sOutString.length() - 1);
				sOutString = sOutString + '\n';
				prog.SetValue((int) ((float) i / fMax * 100.0f));
				byte[] ba = sOutString.getBytes();
				bos.write(ba);
				bos.flush();
				fos.flush();
				continue;
			} catch (Exception e) {
				if (!Util.bAllowPrintln)
					continue;
				e.printStackTrace();
			}
		}
		bos.close();
		fos.close();
		prog.close();
	}
}
