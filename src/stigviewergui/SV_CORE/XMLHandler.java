/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.SV_CORE;

import File_Interfaces.CCIReader;
import java.io.PrintStream;
import java.util.Stack;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import stigviewergui.SV_CORE.STIG;
import stigviewergui.SV_CORE.Util;
import stigviewergui.SV_CORE.Vuln;

public class XMLHandler extends DefaultHandler {
	private STIG currSTIG;
	private boolean inProfile;
	private boolean inTitle;
	private boolean inDes;
	private boolean inVer;
	private boolean inVuln;
	private boolean inRule;
	private boolean inIdent;
	private boolean inReference;
	private String currProfile;
	private String GroupID;
	private String GroupTitle;
	CCIReader myCCI;
	private Vuln currVuln;
	private Stack<StringBuffer> bufs;

	public XMLHandler(XMLReader parser, STIG currSTIG, CCIReader CCIR) {
		this.currSTIG = currSTIG;
		this.myCCI = CCIR;
		this.inVuln = false;
		this.inRule = false;
		this.inIdent = false;
		this.inReference = false;
		this.inProfile = false;
		this.inTitle = true;
		this.inDes = true;
		this.inVer = true;
		this.currProfile = null;
		this.bufs = new Stack();
		this.GroupID = "";
		this.GroupTitle = "";
	}

	public static void parseXML(String Filename, STIG currSTIG) {
		block2: {
			try {
				XMLReader parser = XMLReaderFactory.createXMLReader();
				XMLHandler handler = new XMLHandler(parser, currSTIG, null);
				parser.setContentHandler(handler);
				parser.parse(Filename);
			} catch (Exception e) {
				if (!Util.bAllowPrintln)
					break block2;
				System.err.println(e);
			}
		}
	}

	public void startElement(String namespaceURI, String localName,
			String qualifiedName, Attributes atts) {
		block30: {
			try {
				String idref;
				String id = atts.getValue("id");
				this.bufs.push(new StringBuffer());
				if (!(id == null || this.inVuln || this.inRule)) {
					if (localName.equals("Profile")) {
						this.inProfile = true;
						this.currProfile = id;
						this.currSTIG.addProfile(this.currProfile);
					} else if (localName.equals("Group")) {
						this.GroupID = id;
						this.inVuln = true;
					} else if (localName.equals("notice")) {
						this.currSTIG
								.setSTIG_notice(atts.getValue("id").trim());
					} else if (localName.equals("Rule")) {
						this.inRule = true;
						this.currVuln = new Vuln();
						this.currVuln.setAttr(Vuln.VulnAttr.Vuln_Num,
								atts.getValue("id"));
						this.currVuln.setAttr(Vuln.VulnAttr.Group_Title, "");
						this.currVuln.setAttr(Vuln.VulnAttr.STIGRef,
								this.currSTIG.getSTIG_Title());
						this.currVuln.setAttr(Vuln.VulnAttr.Rule_ID,
								atts.getValue("id"));
						this.currVuln.setAttr(Vuln.VulnAttr.Severity,
								atts.getValue("severity"));
					}
				} else if (this.inRule) {
					if (localName.equals("ident")) {
						if (atts.getValue("system").contains(
								(CharSequence) "iase.disa.mil/cci")) {
							this.inIdent = true;
						}
					} else if (localName.equals("check-content-ref")) {
						if (atts.getValue("name").contains(
								(CharSequence) "oval:")) {
							this.currVuln.setAttr(
									Vuln.VulnAttr.Check_Content_Ref,
									atts.getValue("name") + " :: "
											+ atts.getValue("href"));
						}
					} else if (localName.equals("reference")) {
						this.inReference = true;
					}
				} else if (this.inVuln) {
					if (localName.equals("Rule")) {
						this.inRule = true;
						this.currVuln = new Vuln();
						this.currVuln.setAttr(Vuln.VulnAttr.Vuln_Num,
								this.GroupID);
						this.currVuln.setAttr(Vuln.VulnAttr.Group_Title,
								this.GroupTitle);
						this.currVuln.setAttr(Vuln.VulnAttr.STIGRef,
								this.currSTIG.getSTIG_Title());
						this.currVuln.setAttr(Vuln.VulnAttr.Rule_ID,
								atts.getValue("id"));
						this.currVuln.setAttr(Vuln.VulnAttr.Severity,
								atts.getValue("severity"));
					}
				} else if (this.inProfile
						&& (idref = atts.getValue("idref")) != null) {
					this.currSTIG.addVuln(this.currSTIG.getsLastProfile(),
							idref);
				}
			} catch (Exception e) {
				if (!Util.bAllowPrintln)
					break block30;
				e.printStackTrace();
			}
		}
	}

	public void endElement(String namespaceURI, String localName,
			String qualifiedName) {
		block54: {
			try {
				String Tag_Contents = this.bufs.pop().toString();
				if (this.inVuln && localName.equals("title")) {
					this.GroupTitle = Tag_Contents;
				}
				if (this.inRule) {
					if (!this.inReference) {
						if (localName.equals("version")) {
							this.currVuln.setAttr(Vuln.VulnAttr.Rule_Ver,
									Tag_Contents);
						} else if (localName.equals("title")) {
							this.currVuln.setAttr(Vuln.VulnAttr.Rule_Title,
									Tag_Contents);
						} else if (localName.equals("description")) {
							String Vuln_Dis = "";
							if (Tag_Contents
									.contains((CharSequence) "<VulnDiscussion>")) {
								Vuln_Dis = Tag_Contents.substring(Tag_Contents
										.indexOf("<VulnDiscussion>") + 16,
										Tag_Contents
												.indexOf("</VulnDiscussion>"));
							}
							if (Tag_Contents
									.contains((CharSequence) "<IAControls>")) {
								this.currVuln
										.setAttr(
												Vuln.VulnAttr.IA_Controls,
												Tag_Contents.substring(
														Tag_Contents
																.indexOf("<IAControls>") + 12,
														Tag_Contents
																.indexOf("</IAControls>")));
							}
							if (Tag_Contents
									.contains((CharSequence) "<FalsePositives>")) {
								this.currVuln
										.setAttr(
												Vuln.VulnAttr.False_Positives,
												Tag_Contents.substring(
														Tag_Contents
																.indexOf("<FalsePositives>") + 16,
														Tag_Contents
																.indexOf("</FalsePositives>")));
							}
							if (Tag_Contents
									.contains((CharSequence) "<FalseNegatives>")) {
								this.currVuln
										.setAttr(
												Vuln.VulnAttr.False_Negatives,
												Tag_Contents.substring(
														Tag_Contents
																.indexOf("<FalseNegatives>") + 16,
														Tag_Contents
																.indexOf("</FalseNegatives>")));
							}
							if (Tag_Contents
									.contains((CharSequence) "<Documentable>")) {
								this.currVuln
										.setAttr(
												Vuln.VulnAttr.Documentable,
												Tag_Contents.substring(
														Tag_Contents
																.indexOf("<Documentable>") + 14,
														Tag_Contents
																.indexOf("</Documentable>")));
							}
							if (Tag_Contents
									.contains((CharSequence) "<Mitigations>")) {
								this.currVuln
										.setAttr(
												Vuln.VulnAttr.Mitigations,
												Tag_Contents.substring(
														Tag_Contents
																.indexOf("<Mitigations>") + 13,
														Tag_Contents
																.indexOf("</Mitigations>")));
							}
							if (Tag_Contents
									.contains((CharSequence) "<SecurityOverrideGuidance>")) {
								this.currVuln
										.setAttr(
												Vuln.VulnAttr.Security_Override_Guidance,
												Tag_Contents.substring(
														Tag_Contents
																.indexOf("<SecurityOverrideGuidance>") + 26,
														Tag_Contents
																.indexOf("</SecurityOverrideGuidance>")));
							}
							if (Tag_Contents
									.contains((CharSequence) "<SeverityOverrideGuidance>")) {
								this.currVuln
										.setAttr(
												Vuln.VulnAttr.Security_Override_Guidance,
												Tag_Contents.substring(
														Tag_Contents
																.indexOf("<SeverityOverrideGuidance>") + 26,
														Tag_Contents
																.indexOf("</SeverityOverrideGuidance>")));
							}
							if (Tag_Contents
									.contains((CharSequence) "<PotentialImpacts>")) {
								this.currVuln
										.setAttr(
												Vuln.VulnAttr.Potential_Impact,
												Tag_Contents.substring(
														Tag_Contents
																.indexOf("<PotentialImpacts>") + 18,
														Tag_Contents
																.indexOf("</PotentialImpacts>")));
							}
							if (Tag_Contents
									.contains((CharSequence) "<ThirdPartyTools>")) {
								this.currVuln
										.setAttr(
												Vuln.VulnAttr.Third_Party_Tools,
												Tag_Contents.substring(
														Tag_Contents
																.indexOf("<ThirdPartyTools>") + 17,
														Tag_Contents
																.indexOf("</ThirdPartyTools>")));
							}
							if (Tag_Contents
									.contains((CharSequence) "<MitigationControl>")) {
								this.currVuln
										.setAttr(
												Vuln.VulnAttr.Mitigation_Control,
												Tag_Contents.substring(
														Tag_Contents
																.indexOf("<MitigationControl>") + 19,
														Tag_Contents
																.indexOf("</MitigationControl>")));
							}
							if (Tag_Contents
									.contains((CharSequence) "<Responsibility>")) {
								this.currVuln
										.setAttr(
												Vuln.VulnAttr.Responsibility,
												Tag_Contents.substring(
														Tag_Contents
																.indexOf("<Responsibility>") + 16,
														Tag_Contents
																.indexOf("</Responsibility>")));
							}
							this.currVuln.setAttr(Vuln.VulnAttr.Vuln_Discuss,
									Vuln_Dis);
						} else if (localName.equals("fixtext")) {
							String s = this.currVuln
									.getAttr(Vuln.VulnAttr.Fix_Text);
							s = s.equals("") ? s + Tag_Contents : s + '\n'
									+ '\n' + Tag_Contents;
							this.currVuln.setAttr(Vuln.VulnAttr.Fix_Text, s);
						} else if (localName.equals("check-content")) {
							String s = this.currVuln
									.getAttr(Vuln.VulnAttr.Check_Content);
							s = s.equals("") ? s + Tag_Contents : s + '\n'
									+ '\n' + Tag_Contents;
							this.currVuln.setAttr(Vuln.VulnAttr.Check_Content,
									s);
						} else if (localName.equals("ident") && this.inIdent) {
							if (!Tag_Contents.contains((CharSequence) ",")) {
								this.currVuln.addCCIVal(Tag_Contents);
								CCIReader.CCI_Store TempCCI = this.myCCI
										.getCCIbyName(Tag_Contents);
								this.currVuln.addCCI(TempCCI);
							} else {
								int iStart = 0;
								for (int i = 0; i < Tag_Contents.length(); ++i) {
									if (Tag_Contents.charAt(i) != ','
											&& i != Tag_Contents.length() - 1)
										continue;
									String sTemp = "";
									if (i != Tag_Contents.length() - 1) {
										sTemp = Tag_Contents.substring(iStart,
												i);
										iStart = i + 2;
									} else {
										sTemp = Tag_Contents.substring(iStart);
									}
									this.currVuln.addCCIVal(sTemp);
									CCIReader.CCI_Store TempCCI = this.myCCI
											.getCCIbyName(sTemp);
									this.currVuln.addCCI(TempCCI);
								}
							}
							if (!this.currSTIG.getHasCCI()) {
								this.currSTIG.setHasCCI(true);
							}
							this.inIdent = false;
						}
					} else {
						if (localName.equals("identifier")) {
							this.currVuln.setAttr(Vuln.VulnAttr.TargetKey,
									Tag_Contents);
						}
						if (localName.equals("reference")) {
							this.inReference = false;
						}
					}
				}
				if (localName.equals("Profile")) {
					this.inProfile = false;
				} else if (localName.equals("Rule")) {
					this.inRule = false;
					this.currSTIG.addVulnObj(this.currVuln);
				} else if (localName.equals("Group")) {
					this.GroupID = "";
					this.GroupTitle = "";
					this.inVuln = false;
				} else if (this.inTitle && localName.equals("title")) {
					this.inTitle = false;
					this.currSTIG.setSTIG_Title(Tag_Contents);
				} else if (this.inDes && localName.equals("description")) {
					this.inDes = false;
					this.currSTIG.setSTIG_Description(Tag_Contents);
				} else if (this.inVer && localName.equals("version")) {
					this.inVer = false;
					this.currSTIG.setSTIG_Version(Tag_Contents);
				} else if (localName.equals("plain-text")) {
					this.currSTIG.setSTIG_Release_Info(Tag_Contents);
				} else if (localName.equals("source")) {
					this.currSTIG.setSTIG_source(Tag_Contents.trim());
				}
			} catch (Exception e) {
				if (!Util.bAllowPrintln)
					break block54;
				e.printStackTrace();
			}
		}
	}

	public void characters(char[] ch, int start, int length) {
		this.bufs.lastElement().append(ch, start, length);
	}
}
