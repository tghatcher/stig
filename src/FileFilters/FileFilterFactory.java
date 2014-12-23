/*
 * Decompiled with CFR 0_92.
 */
package FileFilters;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class FileFilterFactory extends FileFilter {
	String sExtension;
	String sDescription;

	public FileFilterFactory(String Description, String Extension) {
		this.sExtension = Extension;
		this.sDescription = Description;
	}

	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		String sFileName = f.getName();
		if (sFileName != null) {
			String myExt;
			int i = sFileName.length() - 1;
			boolean bExtFound = false;
			while (!(bExtFound || i <= 0)) {
				if (sFileName.charAt(i) == '.') {
					bExtFound = true;
					continue;
				}
				--i;
			}
			if (bExtFound
					&& (myExt = sFileName.substring(i + 1, sFileName.length())) != null
					&& myExt.equals(this.sExtension)) {
				return true;
			}
		}
		return false;
	}

	public String getDescription() {
		return this.sDescription;
	}
}
