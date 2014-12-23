/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import stigviewergui.STIGViewerConfig;
import stigviewergui.SV_CORE.Util;

public class STIGViewerConfigFileHandler {
	private File MyConfigFile;
	private STIGViewerConfig myConfig;

	STIGViewerConfigFileHandler(File f, STIGViewerConfig Configuration) {
		this.MyConfigFile = f;
		this.myConfig = Configuration;
	}

	private String GetToken(String sTokenID, String sRaw) {
		String sOut = "";
		if (sRaw.contains((CharSequence) sTokenID)) {
			int end;
			int start;
			for (end = start = sRaw.indexOf((String) sTokenID)
					+ sTokenID.length(); end < sRaw.length()
					&& sRaw.charAt(end) != ';'; ++end) {
			}
			sOut = sRaw.substring(start, end);
		}
		return sOut;
	}

	public void LoadConfigFromFile() {
		block27: {
			try {
				String sToken;
				String s;
				String sTemp;
				int i;
				block26: {
					Color C;
					ArrayList<String> asRet;
					FileInputStream fIS = new FileInputStream(this.MyConfigFile);
					int iFileSize = fIS.available();
					byte[] bIn = new byte[iFileSize];
					fIS.read(bIn);
					char[] cIn = new char[iFileSize];
					for (int i2 = 0; i2 < iFileSize; ++i2) {
						cIn[i2] = bIn[i2];
					}
					fIS.close();
					sTemp = new String(cIn);
					sToken = "";
					sToken = "Font:";
					if (sTemp.contains((CharSequence) sToken)) {
						s = this.GetToken(sToken, sTemp);
						this.myConfig.SetFont(s);
					}
					if (sTemp.contains((CharSequence) (sToken = "FontSize:"))) {
						s = this.GetToken(sToken, sTemp);
						this.myConfig.SetFontSize(Integer.decode(s));
					}
					if (sTemp.contains((CharSequence) (sToken = "TextColor:"))) {
						s = this.GetToken(sToken, sTemp);
						C = new Color(Integer.decode(s));
						this.myConfig.SetFontColor(C);
					}
					if (sTemp.contains((CharSequence) (sToken = "BackColor:"))) {
						s = this.GetToken(sToken, sTemp);
						C = new Color(Integer.decode(s));
						this.myConfig.SetBackColor(C);
					}
					if (sTemp.contains((CharSequence) (sToken = "CCI_DATA:"))) {
						boolean bCCI = true;
						boolean bCCI_Desc = false;
						boolean bCCI_Map = true;
						String s2 = this.GetToken(sToken, sTemp);
						if (s2.equals("true") || s2.equals("t")) {
							bCCI = true;
							sToken = "CCI_DESC:";
							s2 = this.GetToken(sToken, sTemp);
							bCCI_Desc = s2.equals("true") || s2.equals("t");
							sToken = "CCI_MAP:";
							s2 = this.GetToken(sToken, sTemp);
							bCCI_Map = s2.equals("true") || s2.equals("t");
						} else {
							bCCI = false;
							bCCI_Desc = false;
							bCCI_Map = false;
						}
						this.myConfig.SetCCI(bCCI, bCCI_Desc, bCCI_Map);
					}
					if (sTemp.contains((CharSequence) (sToken = "Shown:"))) {
						s = this.GetToken(sToken, sTemp);
						asRet = new ArrayList<String>();
						i = 0;
						while (s.length() > 0) {
							i = s.indexOf(44);
							if (i != -1) {
								asRet.add(s.substring(0, i));
								s = s.substring(i + 1);
								continue;
							}
							asRet.add(s);
							s = "";
						}
						this.myConfig.SetShowList(asRet);
					}
					if (sTemp.contains((CharSequence) (sToken = "NotShown:"))) {
						s = this.GetToken(sToken, sTemp);
						asRet = new ArrayList();
						i = 0;
						while (s.length() > 0) {
							i = s.indexOf(44);
							if (i != -1) {
								asRet.add(s.substring(0, i));
								s = s.substring(i + 1);
								continue;
							}
							asRet.add(s);
							s = "";
						}
						this.myConfig.SetHideList(asRet);
					}
					if (sTemp.contains((CharSequence) (sToken = "CVDM:"))) {
						s = this.GetToken(sToken, sTemp);
						try {
							Boolean b = Boolean.valueOf(s);
							this.myConfig.bSetColorBlindMode(b);
						} catch (Exception e) {
							if (!Util.bAllowPrintln)
								break block26;
							e.printStackTrace();
						}
					}
				}
				if (sTemp.contains((CharSequence) (sToken = "Chk_Sort_Order:"))) {
					s = this.GetToken(sToken, sTemp);
					String[] sa = new String[] { "", "", "", "" };
					try {
						i = 0;
						int scount = 0;
						while (s.length() > 0) {
							i = s.indexOf(44);
							if (i != -1) {
								sa[scount] = s.substring(0, i);
								s = s.substring(i + 1);
							} else {
								sa[scount] = s;
								s = "";
							}
							++scount;
						}
						this.myConfig.SetStatusSortList(sa);
					} catch (Exception e) {
					}
				}
			} catch (Exception e) {
				if (!Util.bAllowPrintln)
					break block27;
				e.printStackTrace();
			}
		}
	}

	private String OutputString() {
		String sTemp;
		String sOut = "";
		String nl = "\r\n";
		sOut = sOut + "Font:" + this.myConfig.sGetFont() + ";" + nl;
		sOut = sOut + "FontSize:" + this.myConfig.getFontSize() + ";" + nl;
		sOut = sOut + "TextColor:" + this.myConfig.GetTextColor().getRGB()
				+ ";" + nl;
		sOut = sOut + "BackColor:"
				+ this.myConfig.GetBackgroundColor().getRGB() + ";" + nl;
		sOut = sOut + "CCI_DATA:"
				+ String.valueOf(this.myConfig.CheckIfShow_CCI()) + ";" + nl;
		sOut = sOut + "CCI_DESC:"
				+ String.valueOf(this.myConfig.CheckIfShow_CCIDesc()) + ";"
				+ nl;
		sOut = sOut + "CCI_MAP:"
				+ String.valueOf(this.myConfig.CheckIfShow_CCIMap()) + ";" + nl;
		sOut = sOut + "Shown:";
		if (!this.myConfig.GetShowList().isEmpty()) {
			sTemp = "";
			for (String s : this.myConfig.GetShowList()) {
				sTemp = sTemp + s + ",";
			}
			sTemp = sTemp.substring(0, sTemp.length() - 1);
			sOut = sOut + sTemp;
		}
		sOut = sOut + ";" + nl;
		sOut = sOut + "NotShown:";
		if (!this.myConfig.GetHideList().isEmpty()) {
			sTemp = "";
			for (String s : this.myConfig.GetHideList()) {
				sTemp = sTemp + s + ",";
			}
			sTemp = sTemp.substring(0, sTemp.length() - 1);
			sOut = sOut + sTemp;
		}
		sOut = sOut + ";" + nl;
		sOut = sOut + "CVDM:" + this.myConfig.bUseColorBlindMode() + ";" + nl;
		sOut = sOut + "Chk_Sort_Order:";
		if (this.myConfig.GetStatusSortList().length == 4) {
			sTemp = "";
			for (String s : this.myConfig.GetStatusSortList()) {
				sTemp = sTemp + s + ",";
			}
			sTemp = sTemp.substring(0, sTemp.length() - 1);
			sOut = sOut + sTemp;
		}
		sOut = sOut + ";" + nl;
		return sOut;
	}

	public void SaveConfiguration() throws FileNotFoundException, IOException {
		FileOutputStream fOS = new FileOutputStream(this.MyConfigFile);
		fOS.write(this.OutputString().getBytes());
		fOS.close();
	}
}
