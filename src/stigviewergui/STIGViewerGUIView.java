/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui;

import FileFilters.FileFilterFactory;
import File_Interfaces.CCIReader;
import File_Interfaces.CSV_Export_Selector;
import File_Interfaces.SaveFile_SI;
import ModularCheckList.ModCL;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.accessibility.AccessibleContext;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;
import javax.swing.ListModel;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.Caret;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationActionMap;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.TaskMonitor;
import stigviewergui.Clipboard;
import stigviewergui.Config_CallBack;
import stigviewergui.Filter;
import stigviewergui.Readme;
import stigviewergui.STIGSearch;
import stigviewergui.STIGViewerConfig;
import stigviewergui.STIGViewerConfigFileHandler;
import stigviewergui.STIGViewerGUIAboutBox;
import stigviewergui.STIGViewerGUIApp;
import stigviewergui.SV_CORE.Pair;
import stigviewergui.SV_CORE.ProgressUpdater;
import stigviewergui.SV_CORE.STIG;
import stigviewergui.SV_CORE.SV_Checklist;
import stigviewergui.SV_CORE.Util;
import stigviewergui.SV_CORE.Vuln;
import stigviewergui.SV_CORE.VulnStringGen;
import stigviewergui.export.Export_HTML;
import stigviewergui.export.Export_Print;
import stigviewergui.export.Export_RTF;
import stigviewergui.export.Exporter;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class STIGViewerGUIView extends FrameView implements Config_CallBack {
	private DefaultListModel ListModel;
	private DefaultListModel VulnList;
	private DefaultTreeModel TreeModel;
	private JFileChooser SV_FC;
	private STIGSearch Searcher;
	private VulnStringGen VulnDisplay;
	private STIGSearch.SortType mySort;
	private STIGViewerConfig myConfig;
	private ClipboardOwner CBO;
	private stigviewergui.Clipboard myVulnClip;
	FileFilterFactory xmlFilter;
	FileFilterFactory rtfFilter;
	FileFilterFactory csvFilter;
	FileFilterFactory htmlFilter;
	FileFilterFactory zipFilter;
	boolean bHasConfigFolder;
	private ArrayList<Pair<String, byte[]>> UnsavedFileList;
	private CCIReader myCCI;
	private String csLocalDataFolderName;
	private String ev_UserLocal;
	private static final String csConfigFileName = "Config.cnf";
	private String ev_UserHome;
	private static String CCI_Data_File = "";
	private static String Readme_File = "";
	private static String ChangeLog_File = "";
	private String sBody;
	private String sHeader;
	private JMenuItem AddToClip;
	private JScrollPane BodyScroll;
	private JMenu Checklist;
	private JMenuItem CloseSTIG;
	private JMenuItem Config;
	private JPopupMenu CopyMenu;
	private JMenuItem CreateSaveFolder;
	private JMenuItem DeleteSaveFolder;
	private JMenuItem ExpCSV;
	private JMenuItem ExpHTML;
	private JMenuItem ExpRTF;
	private JMenuItem H_pop_copy;
	private JPopupMenu HeaderCopyMenu;
	private JMenuItem ImportSTIG;
	private JMenuItem ImportSTIGZip;
	private JMenuItem NewFromCurrent;
	private JMenuItem NewFromList;
	private JMenuItem OpenChecklist;
	private JTree STIGTree;
	private JCheckBox SearchAllSTIGs;
	private JCheckBoxMenuItem ShowOnlyUnique;
	private JComboBox SortBox;
	private JPanel StatusPanel;
	private JButton VClipAccess;
	private JMenuItem ViewChangeLog;
	private JMenuItem ViewReadme;
	private JPopupMenu VulnListMenu;
	private JButton btn_Inc_Exc;
	private JButton jButton1;
	private JButton jButton2;
	private JButton jButton3;
	private JComboBox jComboBox1;
	private JComboBox jComboBox3;
	private JEditorPane jEditorPane1;
	private JEditorPane jEditorPane2;
	private JLabel jLabel1;
	private JList jList1;
	private JList jList2;
	private JMenu jMenu1;
	private JMenuItem jMenuItem1;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JPanel jPanel3;
	private JPanel jPanel4;
	private JPanel jPanel5;
	private JPanel jPanel6;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane3;
	private JScrollPane jScrollPane5;
	private JSeparator jSeparator1;
	private JSplitPane jSplitPane1;
	private JSplitPane jSplitPane2;
	private JSplitPane jSplitPane3;
	private JTextField jTextField1;
	private JPopupMenu jTreePopUp;
	private JPanel mainPanel;
	private JMenuBar menuBar;
	private JMenu optionsMenu;
	private JMenuItem pop_Copy;
	private final Timer messageTimer;
	private final Timer busyIconTimer;
	private final Icon idleIcon;
	private final Icon[] busyIcons;
	private int busyIconIndex;
	private JDialog aboutBox;

	/*
	 * Unable to fully structure code Enabled aggressive block sorting Enabled
	 * unnecessary exception pruning Lifted jumps to return sites
	 */
	public STIGViewerGUIView(SingleFrameApplication app) {
        block20 : {
            block19 : {
                super(app);
                this.ListModel = null;
                this.VulnList = null;
                this.TreeModel = null;
                this.SV_FC = null;
                this.Searcher = null;
                this.mySort = null;
                this.xmlFilter = null;
                this.rtfFilter = null;
                this.csvFilter = null;
                this.htmlFilter = null;
                this.zipFilter = null;
                this.csLocalDataFolderName = "STIGV_AppData";
                this.ev_UserLocal = "";
                this.ev_UserHome = "";
                this.sBody = "";
                this.sHeader = "";
                this.busyIcons = new Icon[15];
                this.busyIconIndex = 0;
                resMap = ((STIGViewerGUIApp)Application.getInstance(STIGViewerGUIApp.class)).getContext().getResourceMap(STIGViewerGUIView.class);
                Util.SetWindowHeader(resMap.getString("Application.version", new Object[0]), this.getFrame());
                STIGViewerGUIView.CCI_Data_File = resMap.getString("Application.CCI_File", new Object[0]);
                STIGViewerGUIView.Readme_File = resMap.getString("Application.Readme_File", new Object[0]);
                STIGViewerGUIView.ChangeLog_File = resMap.getString("Application.ChangeLog_File", new Object[0]);
                this.ev_UserHome = System.getProperty("user.home");
                if (Util.bAllowPrintln) {
                    System.out.println("UserHome: " + this.ev_UserHome);
                }
                this.myConfig = new STIGViewerConfig(this);
                this.UnsavedFileList = new ArrayList<E>();
                this.initComponents();
                this.myVulnClip = new stigviewergui.Clipboard();
                this.ListModel = new DefaultListModel<E>();
                this.jList1.setModel(this.ListModel);
                this.TreeModel = new DefaultTreeModel(null);
                top = new DefaultMutableTreeNode("STIGs");
                this.TreeModel.setRoot(top);
                this.STIGTree.setModel(this.TreeModel);
                this.VulnList = new DefaultListModel<E>();
                this.jList2.setModel(this.VulnList);
                this.mySort = STIGSearch.SortType.STIGID;
                this.SV_FC = new JFileChooser();
                this.SV_FC.setFileSelectionMode(2);
                this.myCCI = new CCIReader();
                fIn = this.myCCI.getClass().getClassLoader().getResourceAsStream(STIGViewerGUIView.CCI_Data_File);
                try {
                    this.myCCI.open(fIn);
                }
                catch (Exception ex) {
                    if (!Util.bAllowPrintln) break block19;
                    ex.printStackTrace();
                }
            }
            this.Searcher = new STIGSearch(this.myCCI);
            this.VulnDisplay = new VulnStringGen(this.Searcher, this.myConfig);
            this.CBO = new ClipboardOwner(){

                public void lostOwnership(Clipboard clipboard, Transferable contents) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }
            };
            this.UpdateSTIGCount(0);
            bLockSavepoint = false;
            this.ev_UserLocal = System.getenv("LOCALAPPDATA");
            this.myConfig.SetSlashWindows();
            if (!Util.bHasAlphNum(this.ev_UserLocal)) {
                this.ev_UserLocal = System.getenv("APPDATA");
                this.myConfig.SetSlashWindows();
            }
            if (!Util.bHasAlphNum(this.ev_UserLocal) && Util.bHasAlphNum(this.ev_UserHome)) {
                this.ev_UserLocal = this.ev_UserHome;
                this.myConfig.SetSlashUNIX();
            }
            if (Util.bAllowPrintln) {
                System.out.println("SP Dir: " + this.ev_UserLocal);
            }
            if (!Util.bHasAlphNum(this.ev_UserLocal)) {
                bLockSavepoint = true;
            }
            this.getFrame().update(this.getFrame().getGraphics());
            if (!bLockSavepoint) {
                try {
                    fConfigFolder = new File(this.ev_UserLocal + this.myConfig.GetSlash() + this.csLocalDataFolderName);
                    fConfigFile = new File(fConfigFolder.getPath() + this.myConfig.GetSlash() + "Config.cnf");
                    if (fConfigFile.exists()) {
                        SVCFH = new STIGViewerConfigFileHandler(fConfigFile, this.myConfig);
                        SVCFH.LoadConfigFromFile();
                        this.CreateSaveFolder.setEnabled(false);
                        this.DeleteSaveFolder.setEnabled(true);
                        this.bHasConfigFolder = true;
                        fSTIGFolder = new File(fConfigFolder.getPath() + this.myConfig.GetSlash() + "STIGs");
                        if (fSTIGFolder.exists() && fSTIGFolder.isDirectory() && (STIGsToImport = fSTIGFolder.listFiles()).length > 0) {
                            loadProgress = new ProgressUpdater(this.getFrame(), "Prepairing STIGs");
                            loadProgress.SetMessage("Loading Saved STIGs...");
                            loadProgress.SetValue(0);
                            loadProgress.run();
                            for (i = 0; i < STIGsToImport.length; ++i) {
                                loadProgress.SetValue((int)((float)i / (float)STIGsToImport.length * 100.0f));
                                this.QuickLoadSTIG(STIGsToImport[i]);
                            }
                            this.SetupSelectionBoxes();
                            this.DisplaySTIG();
                            this.UpdateRuleDisplay(this.Searcher.STIGSort(this.mySort));
                            loadProgress.close();
                            this.jList2.requestFocus();
                        } else {
                            ** GOTO lbl106
                        }
                        break block20;
                    }
                    this.CreateSaveFolder.setEnabled(true);
                    this.DeleteSaveFolder.setEnabled(false);
                    this.bHasConfigFolder = false;
                }
                catch (Exception e) {
                    if (Util.bAllowPrintln) {
                        e.printStackTrace();
                    } else {
                        ** GOTO lbl106
                    }
                    break block20;
                }
            }
            this.CreateSaveFolder.setEnabled(false);
            this.DeleteSaveFolder.setEnabled(false);
        }
        if (this.Searcher.GetNumberOfSTIGs() < 1) {
            this.NewFromCurrent.setEnabled(false);
            this.NewFromList.setEnabled(false);
        }
        this.xmlFilter = new FileFilterFactory("XML Files", "xml");
        this.rtfFilter = new FileFilterFactory("RTF Files (Word)", "rtf");
        this.csvFilter = new FileFilterFactory("CSV Files (Excel)", "csv");
        this.htmlFilter = new FileFilterFactory("HTML Files", "html");
        this.zipFilter = new FileFilterFactory("ZIP Archives", "zip");
        resourceMap = this.getResourceMap();
        messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        this.messageTimer = new Timer(messageTimeout, new ActionListener(){

            public void actionPerformed(ActionEvent e) {
            }
        });
        this.messageTimer.setRepeats(false);
        busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        i = 0;
        do {
            if (i >= this.busyIcons.length) {
                this.busyIconTimer = new Timer(busyAnimationRate, new ActionListener(){

                    public void actionPerformed(ActionEvent e) {
                        STIGViewerGUIView.this.busyIconIndex = (STIGViewerGUIView.this.busyIconIndex + 1) % STIGViewerGUIView.this.busyIcons.length;
                    }
                });
                this.idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
                taskMonitor = new TaskMonitor(this.getApplication().getContext());
                taskMonitor.addPropertyChangeListener(new PropertyChangeListener(){

                    public void propertyChange(PropertyChangeEvent evt) {
                        String propertyName = evt.getPropertyName();
                        if ("started".equals(propertyName)) {
                            if (!STIGViewerGUIView.this.busyIconTimer.isRunning()) {
                                STIGViewerGUIView.this.busyIconIndex = 0;
                                STIGViewerGUIView.this.busyIconTimer.start();
                            }
                        } else if ("done".equals(propertyName)) {
                            STIGViewerGUIView.this.busyIconTimer.stop();
                        } else if ("message".equals(propertyName)) {
                            String text = (String)evt.getNewValue();
                            STIGViewerGUIView.this.messageTimer.restart();
                        } else if ("progress".equals(propertyName)) {
                            int value = (Integer)evt.getNewValue();
                        }
                    }
                });
                return;
            }
            this.busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
            ++i;
        } while (true);
    }

	@org.jdesktop.application.Action
	public void showAboutBox() {
		if (this.aboutBox == null) {
			JFrame mainFrame = STIGViewerGUIApp.getApplication().getMainFrame();
			this.aboutBox = new STIGViewerGUIAboutBox(mainFrame);
			this.aboutBox.setLocationRelativeTo(mainFrame);
		}
		STIGViewerGUIApp.getApplication().show(this.aboutBox);
	}

	@org.jdesktop.application.Action
	public void print() throws IOException {
		Export_Print myPrinter;
		Exporter.STIGOutType sOT;
		String sText;
		File fOutput;
		block2: {
			myPrinter = new Export_Print(STIGViewerGUIApp.getApplication());
			sText = "";
			sOT = Exporter.STIGOutType.CurrentSearch;
			String sTempFileName = this.Searcher.getCurrentSTIG()
					.getSTIG_Title() + "_TEMP";
			fOutput = null;
			try {
				fOutput = File.createTempFile(sTempFileName, ".rtf");
			} catch (Exception e) {
				if (!Util.bAllowPrintln)
					break block2;
				e.printStackTrace();
			}
		}
		Export_HTML RTFX = new Export_HTML(this.Searcher, fOutput, sOT,
				this.myConfig);
		sText = RTFX.Ex_toPrint();
		myPrinter.PrintData(sText);
	}

	private void initComponents() {
		this.mainPanel = new JPanel();
		this.jSplitPane1 = new JSplitPane();
		this.jSplitPane2 = new JSplitPane();
		this.jPanel1 = new JPanel();
		this.jSplitPane3 = new JSplitPane();
		this.BodyScroll = new JScrollPane();
		this.jEditorPane2 = new JEditorPane();
		this.jScrollPane3 = new JScrollPane();
		this.jEditorPane1 = new JEditorPane();
		this.jPanel3 = new JPanel();
		this.jPanel5 = new JPanel();
		this.jScrollPane5 = new JScrollPane();
		this.jList2 = new JList();
		this.SortBox = new JComboBox();
		this.jPanel2 = new JPanel();
		this.jSeparator1 = new JSeparator();
		this.jPanel4 = new JPanel();
		this.jButton2 = new JButton();
		this.jScrollPane2 = new JScrollPane();
		this.jList1 = new JList();
		this.jButton3 = new JButton();
		this.jButton1 = new JButton();
		this.jComboBox1 = new JComboBox();
		this.jTextField1 = new JTextField();
		this.btn_Inc_Exc = new JButton();
		this.jPanel6 = new JPanel();
		this.SearchAllSTIGs = new JCheckBox();
		this.jComboBox3 = new JComboBox();
		this.jScrollPane1 = new JScrollPane();
		this.STIGTree = new JTree();
		this.menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu();
		this.ImportSTIG = new JMenuItem();
		this.ImportSTIGZip = new JMenuItem();
		this.jMenu1 = new JMenu();
		this.ExpHTML = new JMenuItem();
		this.ExpRTF = new JMenuItem();
		this.ExpCSV = new JMenuItem();
		this.jMenuItem1 = new JMenuItem();
		JMenuItem exitMenuItem = new JMenuItem();
		this.Checklist = new JMenu();
		this.OpenChecklist = new JMenuItem();
		this.NewFromCurrent = new JMenuItem();
		this.NewFromList = new JMenuItem();
		this.optionsMenu = new JMenu();
		this.Config = new JMenuItem();
		this.CreateSaveFolder = new JMenuItem();
		this.DeleteSaveFolder = new JMenuItem();
		JMenu helpMenu = new JMenu();
		JMenuItem aboutMenuItem = new JMenuItem();
		this.ViewReadme = new JMenuItem();
		this.ViewChangeLog = new JMenuItem();
		this.jTreePopUp = new JPopupMenu();
		this.CloseSTIG = new JMenuItem();
		this.CopyMenu = new JPopupMenu();
		this.pop_Copy = new JMenuItem();
		this.HeaderCopyMenu = new JPopupMenu();
		this.H_pop_copy = new JMenuItem();
		this.StatusPanel = new JPanel();
		this.jLabel1 = new JLabel();
		this.VClipAccess = new JButton();
		this.VulnListMenu = new JPopupMenu();
		this.AddToClip = new JMenuItem();
		this.ShowOnlyUnique = new JCheckBoxMenuItem();
		this.mainPanel.setMinimumSize(new Dimension(800, 600));
		this.mainPanel.setName("mainPanel");
		this.mainPanel.setPreferredSize(new Dimension(800, 600));
		this.mainPanel.setLayout(new GridBagLayout());
		this.jSplitPane1.setBorder(null);
		this.jSplitPane1.setDividerLocation(190);
		this.jSplitPane1.setCursor(new Cursor(0));
		this.jSplitPane1.setName("jSplitPane1");
		this.jSplitPane1.setPreferredSize(new Dimension(600, 400));
		this.jSplitPane2.setBorder(null);
		this.jSplitPane2.setDividerLocation(250);
		this.jSplitPane2.setDividerSize(7);
		this.jSplitPane2.setResizeWeight(0.2);
		this.jSplitPane2.setContinuousLayout(true);
		this.jSplitPane2.setCursor(new Cursor(0));
		this.jSplitPane2.setMinimumSize(new Dimension(150, 613));
		this.jSplitPane2.setName("jSplitPane2");
		this.jSplitPane2.setOneTouchExpandable(true);
		this.jPanel1.setName("jPanel1");
		this.jSplitPane3.setBorder(null);
		this.jSplitPane3.setDividerLocation(90);
		this.jSplitPane3.setOrientation(0);
		this.jSplitPane3.setContinuousLayout(true);
		this.jSplitPane3.setName("jSplitPane3");
		this.BodyScroll.setName("BodyScroll");
		this.jEditorPane2.setBorder(BorderFactory.createEtchedBorder());
		this.jEditorPane2.setContentType("text/rtf");
		this.jEditorPane2.setEditable(false);
		this.jEditorPane2.setFont(this.Config.getFont());
		this.jEditorPane2.setComponentPopupMenu(this.CopyMenu);
		this.jEditorPane2.setName("jEditorPane2");
		this.BodyScroll.setViewportView(this.jEditorPane2);
		this.jSplitPane3.setRightComponent(this.BodyScroll);
		this.jScrollPane3.setHorizontalScrollBarPolicy(31);
		this.jScrollPane3.setName("jScrollPane3");
		this.jEditorPane1.setBorder(BorderFactory.createEtchedBorder());
		this.jEditorPane1.setContentType("text/rtf");
		this.jEditorPane1.setEditable(false);
		this.jEditorPane1.setFont(this.Config.getFont());
		this.jEditorPane1.setComponentPopupMenu(this.HeaderCopyMenu);
		this.jEditorPane1.setMinimumSize(new Dimension(106, 90));
		this.jEditorPane1.setName("jEditorPane1");
		this.jEditorPane1.setPreferredSize(new Dimension(106, 90));
		this.jScrollPane3.setViewportView(this.jEditorPane1);
		this.jSplitPane3.setLeftComponent(this.jScrollPane3);
		GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
		this.jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(this.jSplitPane3,
				-1, 377, 32767));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(this.jSplitPane3,
				-1, 595, 32767));
		this.jSplitPane2.setRightComponent(this.jPanel1);
		this.jPanel3.setMinimumSize(new Dimension(250, 613));
		this.jPanel3.setName("jPanel3");
		this.jPanel3.setLayout(new BorderLayout());
		this.jPanel5.setName("jPanel5");
		this.jPanel5.setLayout(new BorderLayout());
		this.jScrollPane5.setName("jScrollPane5");
		this.jList2.setComponentPopupMenu(this.VulnListMenu);
		this.jList2.setName("jList2");
		this.jList2.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent evt) {
				STIGViewerGUIView.this.jList2ValueChanged(evt);
			}
		});
		this.jScrollPane5.setViewportView(this.jList2);
		this.jPanel5.add((Component) this.jScrollPane5, "Center");
		this.SortBox.setModel(new DefaultComboBoxModel<String>(new String[] {
				"STIG ID", "VUL ID", "RULE ID" }));
		this.SortBox.setMinimumSize(new Dimension(50, 18));
		this.SortBox.setName("SortBox");
		this.SortBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerGUIView.this.SortBoxActionPerformed(evt);
			}
		});
		this.jPanel5.add((Component) this.SortBox, "North");
		this.jPanel3.add((Component) this.jPanel5, "Center");
		this.jSplitPane2.setLeftComponent(this.jPanel3);
		this.jSplitPane1.setRightComponent(this.jSplitPane2);
		this.jPanel2.setMaximumSize(new Dimension(180, 32767));
		this.jPanel2.setName("jPanel2");
		this.jPanel2.setPreferredSize(new Dimension(150, 505));
		this.jSeparator1.setName("jSeparator1");
		this.jPanel4.setBorder(BorderFactory.createEtchedBorder());
		this.jPanel4.setName("jPanel4");
		ResourceMap resourceMap = ((STIGViewerGUIApp) Application
				.getInstance(STIGViewerGUIApp.class)).getContext()
				.getResourceMap(STIGViewerGUIView.class);
		this.jButton2.setText(resourceMap.getString("jButton2.text",
				new Object[0]));
		this.jButton2.setName("jButton2");
		this.jButton2.addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent evt) {
				STIGViewerGUIView.this.jButton2MouseReleased(evt);
			}
		});
		this.jScrollPane2.setName("jScrollPane2");
		this.jList1.setName("jList1");
		this.jList1.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent evt) {
				STIGViewerGUIView.this.jList1KeyPressed(evt);
			}
		});
		this.jScrollPane2.setViewportView(this.jList1);
		this.jButton3.setText(resourceMap.getString("jButton3.text",
				new Object[0]));
		this.jButton3.setName("jButton3");
		this.jButton3.addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent evt) {
				STIGViewerGUIView.this.jButton3MouseReleased(evt);
			}
		});
		this.jButton1.setText(resourceMap.getString("btn_Search.text",
				new Object[0]));
		this.jButton1.setName("btn_Search");
		this.jButton1.addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent evt) {
				STIGViewerGUIView.this.btn_Search_MouseReleased(evt);
			}
		});
		this.jComboBox1.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Keyword", "Title", "STIG ID", "VUL ID", "RULE ID",
				"IA Control", "CAT I", "CAT II", "CAT III" }));
		this.jComboBox1.setName("jComboBox1");
		this.jComboBox1.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {
				STIGViewerGUIView.this.jComboBox1PropertyChange(evt);
			}
		});
		this.jTextField1.setText(resourceMap.getString("jTextField1.text",
				new Object[0]));
		this.jTextField1.setName("jTextField1");
		this.jTextField1.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent evt) {
				STIGViewerGUIView.this.jTextField1KeyReleased(evt);
			}
		});
		this.btn_Inc_Exc.setText(resourceMap.getString("btn_Inc_Exc.text",
				new Object[0]));
		this.btn_Inc_Exc.setToolTipText(resourceMap.getString(
				"btn_Inc_Exc.toolTipText", new Object[0]));
		this.btn_Inc_Exc.setName("btn_Inc_Exc");
		this.btn_Inc_Exc.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerGUIView.this.btn_Inc_ExcActionPerformed(evt);
			}
		});
		GroupLayout jPanel4Layout = new GroupLayout(this.jPanel4);
		this.jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel4Layout.createSequentialGroup().addGap(2, 2, 2)
								.addComponent(this.btn_Inc_Exc).addGap(2, 2, 2)
								.addComponent(this.jComboBox1, 0, 141, 32767))
				.addComponent(this.jScrollPane2,
						GroupLayout.Alignment.TRAILING, -1, 186, 32767)
				.addComponent(this.jButton3, -1, 186, 32767)
				.addComponent(this.jButton2, -1, 186, 32767)
				.addComponent(this.jButton1, -1, 186, 32767)
				.addComponent(this.jTextField1, -1, 186, 32767));
		jPanel4Layout
				.setVerticalGroup(jPanel4Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel4Layout
										.createSequentialGroup()
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																this.jComboBox1,
																-2, -1, -2)
														.addComponent(
																this.btn_Inc_Exc))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(this.jTextField1, -2, -1,
												-2)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(this.jButton1)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(this.jButton2)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(this.jButton3)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(this.jScrollPane2, -1,
												167, 32767)));
		this.jPanel6.setName("jPanel6");
		this.SearchAllSTIGs.setText(resourceMap.getString(
				"SearchAllSTIGs.text", new Object[0]));
		this.SearchAllSTIGs.setName("SearchAllSTIGs");
		this.SearchAllSTIGs.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent evt) {
				STIGViewerGUIView.this.SearchAllSTIGsMouseClicked(evt);
			}
		});
		this.jComboBox3.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Profile (none)", "MAC I - PUBLIC", "MAC I - SENSITIVE",
				"MAC I - CLASSIFIED", "MAC II - PUBLIC", "MAC II - SENSITIVE",
				"MAC II - CLASSIFIED", "MAC III - PUBLIC",
				"MAC III - SENSITIVE", "MAC III - CLASSIFIED" }));
		this.jComboBox3.setName("jComboBox3");
		this.jComboBox3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerGUIView.this.jComboBox3ActionPerformed(evt);
			}
		});
		this.jScrollPane1.setName("jScrollPane1");
		this.STIGTree.setBorder(BorderFactory.createEtchedBorder());
		DefaultMutableTreeNode treeNode1 = new DefaultMutableTreeNode("root");
		this.STIGTree.setModel(new DefaultTreeModel(treeNode1));
		this.STIGTree.setComponentPopupMenu(this.jTreePopUp);
		this.STIGTree.setDragEnabled(true);
		this.STIGTree.setDropMode(DropMode.ON_OR_INSERT);
		this.STIGTree.setName("STIGTree");
		this.STIGTree.addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent evt) {
				STIGViewerGUIView.this.STIGTreeMouseReleased(evt);
			}
		});
		this.STIGTree.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent evt) {
				STIGViewerGUIView.this.STIGTreeKeyReleased(evt);
			}
		});
		this.jScrollPane1.setViewportView(this.STIGTree);
		GroupLayout jPanel6Layout = new GroupLayout(this.jPanel6);
		this.jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout.setHorizontalGroup(jPanel6Layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(this.jComboBox3, 0, 190, 32767)
				.addComponent(this.SearchAllSTIGs,
						GroupLayout.Alignment.TRAILING, -1, 190, 32767)
				.addComponent(this.jScrollPane1, -1, 190, 32767));
		jPanel6Layout
				.setVerticalGroup(jPanel6Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								GroupLayout.Alignment.TRAILING,
								jPanel6Layout
										.createSequentialGroup()
										.addComponent(this.jScrollPane1, -1,
												186, 32767)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(this.jComboBox3, -2, 23,
												-2)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(this.SearchAllSTIGs)));
		GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
		this.jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(this.jPanel6, -1, -1, 32767)
				.addComponent(this.jSeparator1, -1, 190, 32767)
				.addComponent(this.jPanel4, -1, -1, 32767));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel2Layout
								.createSequentialGroup()
								.addComponent(this.jPanel6, -2, -1, -2)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.jSeparator1, -2, 7, -2)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(this.jPanel4, -2, -1, -2)
								.addGap(110, 110, 110)));
		this.jSplitPane1.setLeftComponent(this.jPanel2);
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = 1;
		gridBagConstraints.anchor = 18;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		this.mainPanel.add((Component) this.jSplitPane1, gridBagConstraints);
		this.menuBar.setName("menuBar");
		fileMenu.setText(resourceMap.getString("fileMenu.text", new Object[0]));
		this.ImportSTIG.setAccelerator(KeyStroke.getKeyStroke(73, 2));
		this.ImportSTIG.setText(resourceMap.getString("ImportSTIG.text",
				new Object[0]));
		this.ImportSTIG.setName("ImportSTIG");
		this.ImportSTIG.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerGUIView.this.ImportSTIGActionPerformed(evt);
			}
		});
		fileMenu.add(this.ImportSTIG);
		this.ImportSTIGZip.setText(resourceMap.getString("ImportSTIGZip.text",
				new Object[0]));
		this.ImportSTIGZip.setName("ImportSTIGZip");
		this.ImportSTIGZip.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerGUIView.this.ImportSTIGZipActionPerformed(evt);
			}
		});
		fileMenu.add(this.ImportSTIGZip);
		this.jMenu1
				.setText(resourceMap.getString("jMenu1.text", new Object[0]));
		this.jMenu1.setName("jMenu1");
		this.ExpHTML.setText(resourceMap.getString("ExpHTML.text",
				new Object[0]));
		this.ExpHTML.setName("ExpHTML");
		this.ExpHTML.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerGUIView.this.ExpHTMLActionPerformed(evt);
			}
		});
		this.jMenu1.add(this.ExpHTML);
		this.ExpRTF
				.setText(resourceMap.getString("ExpRTF.text", new Object[0]));
		this.ExpRTF.setName("ExpRTF");
		this.ExpRTF.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerGUIView.this.ExpRTFActionPerformed(evt);
			}
		});
		this.jMenu1.add(this.ExpRTF);
		this.ExpCSV
				.setText(resourceMap.getString("ExpCSV.text", new Object[0]));
		this.ExpCSV.setName("ExpCSV");
		this.ExpCSV.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerGUIView.this.ExpCSVActionPerformed(evt);
			}
		});
		this.jMenu1.add(this.ExpCSV);
		fileMenu.add(this.jMenu1);
		ApplicationActionMap actionMap = ((STIGViewerGUIApp) Application
				.getInstance(STIGViewerGUIApp.class)).getContext()
				.getActionMap(STIGViewerGUIView.class, this);
		this.jMenuItem1.setAction(actionMap.get("print"));
		this.jMenuItem1.setText(resourceMap.getString("jMenuItem1.text",
				new Object[0]));
		this.jMenuItem1.setToolTipText(resourceMap.getString(
				"jMenuItem1.toolTipText", new Object[0]));
		this.jMenuItem1.setName("jMenuItem1");
		fileMenu.add(this.jMenuItem1);
		this.jMenuItem1.getAccessibleContext().setAccessibleDescription(
				resourceMap.getString(
						"jMenuItem1.AccessibleContext.accessibleDescription",
						new Object[0]));
		exitMenuItem.setAction(actionMap.get("quit"));
		exitMenuItem.setName("exitMenuItem");
		fileMenu.add(exitMenuItem);
		this.menuBar.add(fileMenu);
		this.Checklist.setText(resourceMap.getString("Checklist.text",
				new Object[0]));
		this.Checklist.setName("Checklist");
		this.OpenChecklist.setText(resourceMap.getString("OpenChecklist.text",
				new Object[0]));
		this.OpenChecklist.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerGUIView.this.OpenChecklistActionPerformed(evt);
			}
		});
		this.Checklist.add(this.OpenChecklist);
		this.NewFromCurrent.setText(resourceMap.getString(
				"NewFromCurrent.text", new Object[0]));
		this.NewFromCurrent.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerGUIView.this.NewFromCurrentActionPerformed(evt);
			}
		});
		this.Checklist.add(this.NewFromCurrent);
		this.NewFromList.setText(resourceMap.getString("NewFromList.text",
				new Object[0]));
		this.NewFromList.setName("NewFromList");
		this.NewFromList.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerGUIView.this.NewFromListActionPerformed(evt);
			}
		});
		this.Checklist.add(this.NewFromList);
		this.menuBar.add(this.Checklist);
		this.optionsMenu.setText(resourceMap.getString("optionsMenu.text",
				new Object[0]));
		this.optionsMenu.setName("optionsMenu");
		this.Config
				.setText(resourceMap.getString("Config.text", new Object[0]));
		this.Config.setName("Config");
		this.Config.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerGUIView.this.ConfigActionPerformed(evt);
			}
		});
		this.optionsMenu.add(this.Config);
		this.CreateSaveFolder.setText(resourceMap.getString(
				"CreateSaveFolder.text", new Object[0]));
		this.CreateSaveFolder.setName("CreateSaveFolder");
		this.CreateSaveFolder.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerGUIView.this.CreateSaveFolderActionPerformed(evt);
			}
		});
		this.optionsMenu.add(this.CreateSaveFolder);
		this.DeleteSaveFolder.setText(resourceMap.getString(
				"DeleteSaveFolder.text", new Object[0]));
		this.DeleteSaveFolder.setName("DeleteSaveFolder");
		this.DeleteSaveFolder.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerGUIView.this.DeleteSaveFolderActionPerformed(evt);
			}
		});
		this.optionsMenu.add(this.DeleteSaveFolder);
		this.menuBar.add(this.optionsMenu);
		helpMenu.setText(resourceMap.getString("helpMenu.text", new Object[0]));
		helpMenu.setName("helpMenu");
		aboutMenuItem.setAction(actionMap.get("showAboutBox"));
		aboutMenuItem.setName("aboutMenuItem");
		helpMenu.add(aboutMenuItem);
		this.ViewReadme.setText(resourceMap.getString("ViewReadme.text",
				new Object[0]));
		this.ViewReadme.setName("ViewReadme");
		this.ViewReadme.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerGUIView.this.ViewReadmeActionPerformed(evt);
			}
		});
		helpMenu.add(this.ViewReadme);
		this.ViewChangeLog.setText(resourceMap.getString("ViewChangeLog.text",
				new Object[0]));
		this.ViewChangeLog.setName("ViewChangeLog");
		this.ViewChangeLog.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerGUIView.this.ViewChangeLogActionPerformed(evt);
			}
		});
		helpMenu.add(this.ViewChangeLog);
		this.menuBar.add(helpMenu);
		this.jTreePopUp.setName("jTreePopUp");
		this.CloseSTIG.setText(resourceMap.getString("addTreeNode.text",
				new Object[0]));
		this.CloseSTIG.setName("addTreeNode");
		this.CloseSTIG.addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent evt) {
				STIGViewerGUIView.this.CloseSTIGMouseReleased(evt);
			}
		});
		this.jTreePopUp.add(this.CloseSTIG);
		this.CopyMenu.setName("CopyMenu");
		this.pop_Copy.setText(resourceMap.getString("pop_Copy.text",
				new Object[0]));
		this.pop_Copy.setName("pop_Copy");
		this.pop_Copy.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerGUIView.this.pop_CopyActionPerformed(evt);
			}
		});
		this.CopyMenu.add(this.pop_Copy);
		this.HeaderCopyMenu.setName("HeaderCopyMenu");
		this.H_pop_copy.setText(resourceMap.getString("H_pop_copy.text",
				new Object[0]));
		this.H_pop_copy.setName("H_pop_copy");
		this.H_pop_copy.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerGUIView.this.H_pop_copyActionPerformed(evt);
			}
		});
		this.HeaderCopyMenu.add(this.H_pop_copy);
		this.StatusPanel.setName("StatusPanel");
		this.jLabel1.setHorizontalAlignment(4);
		this.jLabel1.setText(resourceMap.getString("jLabel1.text",
				new Object[0]));
		this.jLabel1.setHorizontalTextPosition(4);
		this.jLabel1.setMaximumSize(new Dimension(1000, 14));
		this.jLabel1.setName("jLabel1");
		this.jLabel1.setOpaque(true);
		this.VClipAccess.setText(resourceMap.getString("VClipAccess.text",
				new Object[0]));
		this.VClipAccess.setEnabled(false);
		this.VClipAccess.setName("VClipAccess");
		this.VClipAccess.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerGUIView.this.VClipAccessActionPerformed(evt);
			}
		});
		GroupLayout StatusPanelLayout = new GroupLayout(this.StatusPanel);
		this.StatusPanel.setLayout(StatusPanelLayout);
		StatusPanelLayout.setHorizontalGroup(StatusPanelLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
						StatusPanelLayout
								.createSequentialGroup()
								.addGap(196, 196, 196)
								.addComponent(this.jLabel1, -2, 201, -2)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED,
										355, 32767)
								.addComponent(this.VClipAccess)));
		StatusPanelLayout.setVerticalGroup(StatusPanelLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
						StatusPanelLayout
								.createParallelGroup(
										GroupLayout.Alignment.BASELINE)
								.addComponent(this.jLabel1, -2, -1, -2)
								.addComponent(this.VClipAccess, -2, 18, -2)));
		this.VulnListMenu.setName("VulnListMenu");
		this.AddToClip.setText(resourceMap.getString("AddToClip.text",
				new Object[0]));
		this.AddToClip.setEnabled(false);
		this.AddToClip.setName("AddToClip");
		this.AddToClip.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerGUIView.this.AddToClipActionPerformed(evt);
			}
		});
		this.VulnListMenu.add(this.AddToClip);
		this.ShowOnlyUnique.setText(resourceMap.getString(
				"ShowOnlyUnique.text", new Object[0]));
		this.ShowOnlyUnique.setName("ShowOnlyUnique");
		this.ShowOnlyUnique.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				STIGViewerGUIView.this.ShowOnlyUniqueActionPerformed(evt);
			}
		});
		this.VulnListMenu.add(this.ShowOnlyUnique);
		this.setComponent(this.mainPanel);
		this.setMenuBar(this.menuBar);
		this.setStatusBar(this.StatusPanel);
		this.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {
				STIGViewerGUIView.this.formPropertyChange(evt);
			}
		});
	}

	private void jComboBox1PropertyChange(PropertyChangeEvent evt) {
		String s = this.jComboBox1.getSelectedItem().toString();
		if (s.equals("CAT I") || s.equals("CAT II") || s.equals("CAT III")) {
			this.jTextField1.setText("");
			this.jTextField1.setEnabled(false);
		} else {
			this.jTextField1.setEnabled(true);
		}
	}

	private void RemoveSTIG(TreePath PathTemp) {
		block10: {
			try {
				block9: {
					File FileToRemove;
					if (this.bHasConfigFolder
							&& (FileToRemove = new File(this.ev_UserLocal
									+ this.myConfig.GetSlash()
									+ this.csLocalDataFolderName
									+ this.myConfig.GetSlash()
									+ "STIGs"
									+ this.myConfig.GetSlash()
									+ PathTemp.getLastPathComponent()
											.toString())).exists()) {
						try {
							FileToRemove.delete();
						} catch (Exception e) {
							if (!Util.bAllowPrintln)
								break block9;
							e.printStackTrace();
						}
					}
				}
				this.Searcher.SetCurrentStig(PathTemp.toString());
				String s = this.Searcher.RemoveCurrentSTIG();
				this.TreeModel.removeNodeFromParent((MutableTreeNode) PathTemp
						.getLastPathComponent());
				if (s != null) {
					this.STIGTree.setSelectionPath(new TreePath(s));
				} else {
					this.STIGTree.setSelectionPath(null);
				}
				this.STIGTree.removeSelectionPath(this.STIGTree
						.getSelectionPath());
				this.VulnList.clear();
				this.jEditorPane1.setText("");
				this.jEditorPane2.setText("");
				if (this.Searcher.GetNumberOfSTIGs() < 1) {
					this.NewFromCurrent.setEnabled(false);
					this.NewFromList.setEnabled(false);
				}
			} catch (Exception e) {
				if (!Util.bAllowPrintln)
					break block10;
				e.printStackTrace();
			}
		}
		this.STIGTreeMouseReleased(null);
	}

	private void CloseSTIGMouseReleased(MouseEvent evt) {
		TreePath[] Paths = this.STIGTree.getSelectionPaths();
		if (Paths != null && Paths.length > 0) {
			for (TreePath path : Paths) {
				this.RemoveSTIG(path);
			}
		}
	}

	private void jTextField1KeyReleased(KeyEvent evt) {
		Integer i = evt.getKeyCode();
		if (i == 10) {
			MouseEvent e = null;
			this.btn_Search_MouseReleased(e);
		}
	}

	private void btn_Search_MouseReleased(MouseEvent evt) {
		String s = this.jComboBox1.getSelectedItem().toString();
		String FieldText = this.jTextField1.getText();
		Filter f = new Filter();
		Boolean isAction = false;
		Boolean bIsExclude = false;
		String pm = this.btn_Inc_Exc.getText() + " ";
		if (this.btn_Inc_Exc.getText().equals("+")) {
			bIsExclude = false;
		} else if (this.btn_Inc_Exc.getText().equals("-")) {
			bIsExclude = true;
		}
		if (s.equals("Keyword") && FieldText.length() > 0) {
			this.ListModel.addElement(pm + s + ": " + FieldText);
			f.SetFilterType(Filter.FilterType.Keyword, bIsExclude);
			f.SetFilterText(FieldText);
			isAction = true;
		} else if (s.equals("Title") && FieldText.length() > 0) {
			this.ListModel.addElement(pm + s + ": " + FieldText);
			f.SetFilterType(Filter.FilterType.Title, bIsExclude);
			f.SetFilterText(FieldText);
			isAction = true;
		} else if (s.equals("VUL ID") && FieldText.length() > 0) {
			this.ListModel.addElement(pm + s + ": " + FieldText);
			f.SetFilterType(Filter.FilterType.VULID, bIsExclude);
			f.SetFilterText(FieldText);
			isAction = true;
		} else if (s.equals("STIG ID") && FieldText.length() > 0) {
			this.ListModel.addElement(pm + s + ": " + FieldText);
			f.SetFilterType(Filter.FilterType.STIGID, bIsExclude);
			f.SetFilterText(FieldText);
			isAction = true;
		} else if (s.equals("RULE ID") && FieldText.length() > 0) {
			this.ListModel.addElement(pm + s + ": " + FieldText);
			f.SetFilterType(Filter.FilterType.RULEID, bIsExclude);
			f.SetFilterText(FieldText);
			isAction = true;
		} else if (s.equals("CAT I")) {
			if (!(this.ListModel.contains("+CAT I") || this.ListModel
					.contains("-CAT I"))) {
				this.ListModel.addElement(pm + "CAT I");
				f.SetFilterType(Filter.FilterType.CATI, bIsExclude);
				isAction = true;
			} else {
				isAction = false;
			}
		} else if (s.equals("CAT II")) {
			if (!(this.ListModel.contains("+CAT II") || this.ListModel
					.contains("-CAT II"))) {
				this.ListModel.addElement(pm + "CAT II");
				f.SetFilterType(Filter.FilterType.CATII, bIsExclude);
				isAction = true;
			} else {
				isAction = false;
			}
		} else if (s.equals("CAT III")) {
			if (!(this.ListModel.contains("+CAT III") || this.ListModel
					.contains("-CAT III"))) {
				this.ListModel.addElement(pm + "CAT III");
				f.SetFilterType(Filter.FilterType.CATIII, bIsExclude);
				isAction = true;
			} else {
				isAction = false;
			}
		} else if (s.equals("IA Control") && FieldText.length() > 0) {
			this.ListModel.addElement(pm + s + ": " + FieldText);
			f.SetFilterType(Filter.FilterType.IACONTROLS, bIsExclude);
			f.SetFilterText(FieldText);
			isAction = true;
		} else if (s.equals("CCI Ref") && FieldText.length() > 0) {
			this.ListModel.addElement(pm + s + ": " + FieldText);
			f.SetFilterType(Filter.FilterType.CCIREF, bIsExclude);
			f.SetFilterText(FieldText);
			isAction = true;
		}
		this.jTextField1.setText("");
		if (isAction.booleanValue()) {
			this.jList2.requestFocus();
			this.UpdateRuleDisplay(this.Searcher.AddFilter(f));
		}
	}

	private void jButton2MouseReleased(MouseEvent evt) {
		int[] iSelected = this.jList1.getSelectedIndices();
		while (iSelected.length != 0) {
			this.UpdateRuleDisplay(this.Searcher.RemoveFilter(iSelected[0]));
			this.ListModel.remove(iSelected[0]);
			iSelected = this.jList1.getSelectedIndices();
		}
		this.SortBoxActionPerformed(null);
	}

	private void jList1KeyPressed(KeyEvent evt) {
		Integer i = evt.getKeyCode();
		if (i == 127 || i == 8) {
			this.jButton2MouseReleased(null);
		}
	}

	private void jButton3MouseReleased(MouseEvent evt) {
		this.Searcher.ClearSearch();
		this.ListModel.removeAllElements();
		this.SortBoxActionPerformed(null);
	}

	private void ImportSTIGActionPerformed(ActionEvent evt) {
		this.SV_FC.setApproveButtonText("Import");
		this.SV_FC.setFileFilter(this.xmlFilter);
		int iReturn = this.SV_FC.showOpenDialog(this.mainPanel);
		if (iReturn == 0) {
			File fReturn = this.SV_FC.getSelectedFile();
			this.jButton3MouseReleased(null);
			if (fReturn.getName().toLowerCase()
					.contains((CharSequence) "xccdf")
					&& fReturn.getName().contains((CharSequence) ".xml")) {
				if (this.bHasConfigFolder) {
					File fSTIGFolder = new File(this.ev_UserLocal
							+ this.myConfig.GetSlash()
							+ this.csLocalDataFolderName
							+ this.myConfig.GetSlash() + "STIGs");
					if (!fSTIGFolder.exists()) {
						fSTIGFolder.mkdir();
					}
					String[] sFileList = fSTIGFolder.list();
					boolean bFileAlreadyImported = false;
					for (String s : sFileList) {
						if (!s.contains((CharSequence) fReturn.getName()))
							continue;
						bFileAlreadyImported = true;
					}
					if (!bFileAlreadyImported) {
						this.WriteSTIGToSave(fReturn, fSTIGFolder);
						this.LoadSTIG(fReturn);
					} else {
						int iAns = JOptionPane.showConfirmDialog(
								this.getFrame(),
								"STIG Already Imported! Overwrite?");
						block0: switch (iAns) {
						case 0: {
							this.WriteSTIGToSave(fReturn, fSTIGFolder);
							break;
						}
						case 1: {
							int iAns2 = JOptionPane.showConfirmDialog(
									this.getFrame(),
									"Open STIG Without Importing?",
									"Open STIG", 0);
							switch (iAns2) {
							case 0: {
								this.LoadSTIG(fReturn);
								break block0;
							}
							case 1: {
								break block0;
							}
							}
							break;
						}
						}
					}
				} else {
					this.PushSTIGtoHolder(fReturn);
					this.LoadSTIG(fReturn);
				}
			} else {
				JOptionPane
						.showMessageDialog(
								this.getFrame(),
								"Import Failed: The file you attempted to import does not contain 'xccdf' within the file name.");
			}
		}
	}

	private void PushSTIGtoHolder(File f) {
		block2: {
			try {
				FileInputStream fin = new FileInputStream(f);
				BufferedInputStream BIS = new BufferedInputStream(fin);
				byte[] bTemp = new byte[BIS.available()];
				BIS.read(bTemp);
				this.UnsavedFileList.add(new Pair<String, byte[]>(f.getName(),
						bTemp));
				BIS.close();
				fin.close();
			} catch (Exception e) {
				if (!Util.bAllowPrintln)
					break block2;
				e.printStackTrace();
			}
		}
	}

	private void WriteSTIGToSave(File fSource, File fSTIGFolder) {
		block2: {
			File fOutput = new File(fSTIGFolder.getPath()
					+ this.myConfig.GetSlash() + fSource.getName());
			try {
				FileInputStream fIn = new FileInputStream(fSource);
				FileOutputStream fOut = new FileOutputStream(fOutput);
				byte[] bTemp = new byte[fIn.available()];
				fIn.read(bTemp);
				fOut.write(bTemp);
				fIn.close();
				fOut.close();
			} catch (IOException e) {
				if (!Util.bAllowPrintln)
					break block2;
				e.printStackTrace();
			}
		}
	}

	private void WriteSTIGToSave(byte[] bOut, String sName, File fSTIGFolder) {
		block2: {
			File fOutput = new File(fSTIGFolder.getPath()
					+ this.myConfig.GetSlash() + sName);
			try {
				FileOutputStream fOut = new FileOutputStream(fOutput);
				fOut.write(bOut);
				fOut.close();
			} catch (IOException e) {
				if (!Util.bAllowPrintln)
					break block2;
				e.printStackTrace();
			}
		}
	}

	private File WriteSTIGToSave(InputStream IS, String sName, File fSTIGFolder) {
		File fOutput = new File(fSTIGFolder.getPath()
				+ this.myConfig.GetSlash() + sName);
		try {
			BufferedInputStream BIS = new BufferedInputStream(IS);
			FileOutputStream fOut = new FileOutputStream(fOutput);
			byte[] bTemp = new byte[BIS.available()];
			BIS.read(bTemp);
			fOut.write(bTemp);
			BIS.close();
			fOut.close();
			return fOutput;
		} catch (IOException e) {
			if (Util.bAllowPrintln) {
				e.printStackTrace();
			}
			return null;
		}
	}

	private void SetupSelectionBoxes() {
		DefaultComboBoxModel<String> ProfileListCBM = new DefaultComboBoxModel<String>();
		ProfileListCBM.addElement("Profile (none)");
		for (String s : this.Searcher.getCurrentSTIGProfileList()) {
			ProfileListCBM.addElement(s);
		}
		this.jComboBox3.setModel(ProfileListCBM);
		DefaultComboBoxModel<String> DCBM = new DefaultComboBoxModel<String>(
				new String[] { "Keyword", "Title", "STIG ID", "VUL ID",
						"RULE ID", "IA Control", "CAT I", "CAT II", "CAT III" });
		DefaultComboBoxModel<String> CBM = new DefaultComboBoxModel<String>();
		CBM.addElement("STIG ID");
		CBM.addElement("VUL ID");
		CBM.addElement("RULE ID");
		if (this.Searcher.bCurrentRefHasCCI()) {
			CBM.addElement("CCI ID");
			DCBM.addElement("CCI Ref");
		}
		this.SortBox.setModel(CBM);
		this.SortBox.setSelectedItem(this.GetSortName(this.mySort));
		this.jComboBox1.setModel(DCBM);
	}

	private String GetSortName(STIGSearch.SortType Sort) {
		String s = Sort.name();
		int i = s.indexOf("ID");
		s = s.substring(0, i) + " " + s.substring(i);
		return s;
	}

	private void LoadSTIG(File f) {
		if (f.getName().toLowerCase().contains((CharSequence) "xccdf")
				&& f.getName().contains((CharSequence) ".xml")) {
			this.STIGTree.setExpandsSelectedPaths(true);
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
					f.getName());
			this.TreeModel.insertNodeInto(newNode,
					(DefaultMutableTreeNode) this.TreeModel.getRoot(),
					this.TreeModel.getChildCount(this.TreeModel.getRoot()));
			this.STIGTree.setSelectionPath(new TreePath(this.TreeModel
					.getPathToRoot(newNode)));
			this.Searcher.LoadSTIG(f, this.STIGTree.getSelectionPath()
					.toString());
			this.SetupSelectionBoxes();
			this.DisplaySTIG();
			this.UpdateRuleDisplay(this.Searcher.STIGSort(this.mySort));
			if (!this.NewFromCurrent.isEnabled()) {
				this.NewFromCurrent.setEnabled(true);
			}
			if (!this.NewFromList.isEnabled()) {
				this.NewFromList.setEnabled(true);
			}
			this.jList2.requestFocus();
		} else {
			JOptionPane
					.showMessageDialog(
							this.getFrame(),
							"Open Failed: The file you attempted to open does not contain 'xccdf' within the file name.");
		}
	}

	private void QuickLoadSTIG(File f) {
		if (f.getName().toLowerCase().contains((CharSequence) "xccdf")
				&& f.getName().contains((CharSequence) ".xml")) {
			this.STIGTree.setExpandsSelectedPaths(true);
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
					f.getName());
			this.TreeModel.insertNodeInto(newNode,
					(DefaultMutableTreeNode) this.TreeModel.getRoot(),
					this.TreeModel.getChildCount(this.TreeModel.getRoot()));
			this.STIGTree.setSelectionPath(new TreePath(this.TreeModel
					.getPathToRoot(newNode)));
			this.Searcher.LoadSTIG(f, this.STIGTree.getSelectionPath()
					.toString());
			if (!this.NewFromCurrent.isEnabled()) {
				this.NewFromCurrent.setEnabled(true);
			}
			if (!this.NewFromList.isEnabled()) {
				this.NewFromList.setEnabled(true);
			}
		} else {
			JOptionPane
					.showMessageDialog(
							this.getFrame(),
							"Open Failed: The file you attempted to open does not contain 'xccdf' within the file name.");
		}
	}

	private void DisplaySTIG() {
		this.jList2.clearSelection();
		STIG stig = this.Searcher.getCurrentSTIG();
		if (stig != null) {
			this.jEditorPane1.setBackground(this.myConfig.GetBackgroundColor());
			this.jEditorPane2.setBackground(this.myConfig.GetBackgroundColor());
			this.sHeader = "{\\rtf1\\ansi\\f0\\pard {\\fonttbl {\\f0 "
					+ this.myConfig.sGetFont() + ";}}";
			this.sHeader = this.sHeader + "\\fs"
					+ Integer.toString(this.myConfig.getFontSize()) + " ";
			this.sHeader = this.sHeader + "{\\colortbl;\\red"
					+ this.myConfig.GetTextColor().getRed() + "\\green"
					+ this.myConfig.GetTextColor().getGreen() + "\\blue"
					+ this.myConfig.GetTextColor().getBlue() + ";}\\cf1";
			this.sHeader = this.sHeader + "{\\b " + stig.getSTIG_Title()
					+ "} \\par ";
			this.sHeader = this.sHeader + "Version: " + stig.getSTIG_Version()
					+ " \\par ";
			this.sHeader = this.sHeader + stig.getSTIG_Release_Info()
					+ " \\par }";
			this.jEditorPane1.setText(this.sHeader);
			this.sBody = "{\\rtf1\\ansi\\f0\\pard {\\fonttbl {\\f0 "
					+ this.myConfig.sGetFont() + ";}}";
			this.sBody = this.sBody + "\\fs"
					+ Integer.toString(this.myConfig.getFontSize()) + " ";
			this.sBody = this.sBody + "{\\colortbl;\\red"
					+ this.myConfig.GetTextColor().getRed() + "\\green"
					+ this.myConfig.GetTextColor().getGreen() + "\\blue"
					+ this.myConfig.GetTextColor().getBlue() + ";}\\cf1";
			this.sBody = this.sBody + stig.getSTIG_Description() + "\\par}";
			this.jEditorPane2.setText(this.sBody);
		}
		Caret c2 = this.jEditorPane1.getCaret();
		c2.moveDot(0);
		this.jEditorPane1.setCaret(c2);
		Caret c = this.jEditorPane2.getCaret();
		c.moveDot(0);
		this.jEditorPane2.setCaret(c);
	}

	private void UpdateRuleDisplay(ArrayList<Vuln> DispVulns) {
		if (DispVulns != null) {
			DefaultListModel<String> tempList = new DefaultListModel<String>();
			for (int i = 0; i < DispVulns.size(); ++i) {
				Vuln v = DispVulns.get(i);
				if (this.SortBox.getSelectedItem().toString().equals("STIG ID")) {
					tempList.addElement(v.getAttr(Vuln.VulnAttr.Rule_Ver)
							+ " - " + v.getAttr(Vuln.VulnAttr.Group_Title));
					continue;
				}
				if (this.SortBox.getSelectedItem().toString().equals("VUL ID")) {
					tempList.addElement(v.getAttr(Vuln.VulnAttr.Vuln_Num)
							+ " - " + v.getAttr(Vuln.VulnAttr.Group_Title));
					continue;
				}
				if (this.SortBox.getSelectedItem().toString().equals("RULE ID")) {
					if (v.getAttr(Vuln.VulnAttr.Rule_ID).contains(
							(CharSequence) "rule")) {
						tempList.addElement(v.getAttr(Vuln.VulnAttr.Rule_ID)
								.substring(
										0,
										v.getAttr(Vuln.VulnAttr.Rule_ID)
												.indexOf("_rule"))
								+ " - " + v.getAttr(Vuln.VulnAttr.Group_Title));
						continue;
					}
					tempList.addElement(v.getAttr(Vuln.VulnAttr.Rule_ID)
							+ " - " + v.getAttr(Vuln.VulnAttr.Group_Title));
					continue;
				}
				if (!this.SortBox.getSelectedItem().toString().equals("CCI ID"))
					continue;
				String sTemp = v.GetCCIVal(0);
				if (v.GetCCIVals().size() > 1) {
					for (int iS = 1; iS < v.GetCCIVals().size(); ++iS) {
						sTemp = sTemp + ", " + v.GetCCIVal(iS);
					}
				}
				tempList.addElement(sTemp + " - "
						+ v.getAttr(Vuln.VulnAttr.Group_Title));
			}
			this.VulnList = tempList;
			this.jList2.setModel(this.VulnList);
			if (this.VulnList.size() > 0) {
				this.jList2.setSelectedIndex(0);
			}
			this.UpdateSTIGCount(this.VulnList.size());
		}
	}

	private void UpdateSTIGCount(int i) {
		String sDisp = "";
		sDisp = sDisp + Integer.toString(i) + " Rules Shown";
		if (this.jList2.getSelectedIndex() >= 0) {
			sDisp = sDisp + "|| Displaying Rule: "
					+ this.jList2.getSelectedIndex();
		}
		this.jLabel1.setText(sDisp);
	}

	private void UpdateStigCountDisp() {
		String sDisp = "";
		if (this.Searcher.GetDisplayList() != null
				&& this.Searcher.GetDisplayList().size() > 0) {
			sDisp = sDisp
					+ Integer.toString(this.Searcher.GetDisplayList().size())
					+ " Rules Shown";
			if (this.jList2.getSelectedIndex() >= 0) {
				sDisp = sDisp + "|| Displaying Rule: "
						+ (this.jList2.getSelectedIndex() + 1);
			}
		} else {
			sDisp = sDisp + Integer.toString(0) + " Rules Shown";
		}
		this.jLabel1.setText(sDisp);
	}

	public int GetSelectedVuln() {
		return this.jList2.getSelectedIndex();
	}

	public STIGSearch GetSearcher() {
		return this.Searcher;
	}

	public void DisplayVuln(int iVuln) {
		if (iVuln >= 0) {
			this.jEditorPane1.setBackground(this.myConfig.GetBackgroundColor());
			this.jEditorPane2.setBackground(this.myConfig.GetBackgroundColor());
			Vuln myVuln = this.Searcher.GetDisplayList().get(iVuln);
			this.jEditorPane1.setText(this.VulnDisplay.GetRTFHeader(myVuln));
			this.jEditorPane2.setText(this.VulnDisplay.GetRTFBody(myVuln));
			Caret c2 = this.jEditorPane1.getCaret();
			c2.moveDot(0);
			this.jEditorPane1.setCaret(c2);
			Caret c = this.jEditorPane2.getCaret();
			c.moveDot(0);
			this.jEditorPane2.setCaret(c);
		}
	}

	private void jList2ValueChanged(ListSelectionEvent evt) {
		int x = this.jList2.getSelectedIndex();
		if (x >= 0 && x < this.VulnList.size()) {
			this.DisplayVuln(x);
		} else {
			this.jEditorPane1.setText("");
			this.jEditorPane2.setText("");
		}
		this.UpdateStigCountDisp();
	}

	private void jComboBox3ActionPerformed(ActionEvent evt) {
		String Pro = this.jComboBox3.getSelectedItem().toString();
		if (Pro.equals("Profile (none)")) {
			Pro = "";
		}
		this.jButton3MouseReleased(null);
		this.Searcher.SetProfile(Pro);
		this.UpdateRuleDisplay(this.Searcher.STIGSort(this.mySort));
	}

	private void SortBoxActionPerformed(ActionEvent evt) {
		String s = this.SortBox.getSelectedItem().toString();
		if (s.equals("STIG ID")) {
			this.mySort = STIGSearch.SortType.STIGID;
		} else if (s.equals("RULE ID")) {
			this.mySort = STIGSearch.SortType.RULEID;
		} else if (s.equals("VUL ID")) {
			this.mySort = STIGSearch.SortType.VULID;
		} else if (s.equals("CCI ID")) {
			this.mySort = STIGSearch.SortType.CCIID;
		}
		this.UpdateRuleDisplay(this.Searcher.STIGSort(this.mySort));
	}

	private void STIGTreeMouseReleased(MouseEvent evt) {
		if (this.STIGTree.getSelectionPath() != null) {
			if (this.SearchAllSTIGs.isSelected()) {
				this.SearchAllSTIGs.setSelected(false);
			}
			if (this.Searcher.getCurrentSTIG() != null) {
				this.jButton3MouseReleased(null);
				this.Searcher.SetCurrentStig(this.STIGTree.getSelectionPath()
						.toString());
				this.SetupSelectionBoxes();
				if (this.mySort.equals((Object) STIGSearch.SortType.CCIID)
						&& !this.Searcher.getCurrentSTIG().getHasCCI()) {
					this.mySort = STIGSearch.SortType.STIGID;
				}
			}
			this.UpdateRuleDisplay(this.Searcher.STIGSort(this.mySort));
			this.DisplaySTIG();
		}
	}

	private void STIGTreeKeyReleased(KeyEvent evt) {
		Integer i = evt.getKeyCode();
		if (i == 127 || i == 8) {
			this.CloseSTIGMouseReleased(null);
		} else {
			this.STIGTreeMouseReleased(null);
		}
	}

	private void ConfigActionPerformed(ActionEvent evt) {
		this.myConfig.SetConfigDefaultDisplay();
		this.myConfig.setLocation(this.getFrame().getX() + 50, this.getFrame()
				.getY() + 50);
		this.myConfig.Show(this);
		this.getFrame().setEnabled(false);
	}

	@Override
	public void ReturnPostUpdate() {
		this.getFrame().setEnabled(true);
		this.DisplayVuln(this.GetSelectedVuln());
		if (this.bHasConfigFolder) {
			this.CreateSaveFolderActionPerformed(null);
		}
		if (this.jList2.getSelectedIndex() <= 0) {
			this.DisplaySTIG();
		}
		this.getFrame().requestFocus();
	}

	private void formPropertyChange(PropertyChangeEvent evt) {
		if (this.getFrame().isEnabled()) {
			this.DisplayVuln(this.jList2.getSelectedIndex());
		}
	}

	private void pop_CopyActionPerformed(ActionEvent evt) {
		String s = this.jEditorPane2.getSelectedText();
		this.CopyToClipboard(s);
	}

	private void H_pop_copyActionPerformed(ActionEvent evt) {
		String s = this.jEditorPane1.getSelectedText();
		this.CopyToClipboard(s);
	}

	private void ExpRTFActionPerformed(ActionEvent evt) {
		this.SV_FC = new JFileChooser();
		this.SV_FC.setApproveButtonText("Export");
		this.SV_FC.setFileFilter(this.rtfFilter);
		this.SV_FC.setSelectedFile(new File(this.Searcher.getCurrentSTIG()
				.getSTIG_FileName_NoExt() + ".rtf"));
		int iReturn = this.SV_FC.showOpenDialog(this.mainPanel);
		if (iReturn == 0) {
			boolean bFileExists = this.SV_FC.getSelectedFile().exists();
			boolean bContinueWithExport = true;
			if (bFileExists
					&& JOptionPane.showConfirmDialog(this.getFrame(),
							"File Exists: Do you want to overwrite?") == 1) {
				bContinueWithExport = false;
			}
			if (bContinueWithExport) {
				Exporter.STIGOutType sOT = Exporter.STIGOutType.CurrentSearch;
				File fOutput = this.SV_FC.getSelectedFile();
				Export_RTF RTFX = new Export_RTF(this.Searcher, fOutput, sOT,
						this.myConfig);
				RTFX.Export();
			}
		}
	}

	private void CreateSaveFolderActionPerformed(ActionEvent evt) {
		block7: {
			File Test = new File(this.ev_UserLocal + this.myConfig.GetSlash()
					+ this.csLocalDataFolderName + this.myConfig.GetSlash()
					+ "Config.cnf");
			try {
				File baseDir = new File(this.ev_UserLocal
						+ this.myConfig.GetSlash() + this.csLocalDataFolderName);
				File STIGDir = new File(this.ev_UserLocal
						+ this.myConfig.GetSlash() + this.csLocalDataFolderName
						+ this.myConfig.GetSlash() + "STIGs");
				if (!baseDir.exists()) {
					baseDir.mkdir();
				}
				if (!STIGDir.exists()) {
					STIGDir.mkdir();
				}
				Test.createNewFile();
				STIGViewerConfigFileHandler CFH = new STIGViewerConfigFileHandler(
						Test, this.myConfig);
				CFH.SaveConfiguration();
				this.CreateSaveFolder.setEnabled(false);
				this.DeleteSaveFolder.setEnabled(true);
				this.bHasConfigFolder = true;
				if (this.UnsavedFileList.size() > 0) {
					File fSTIGFolder = new File(this.ev_UserLocal
							+ this.myConfig.GetSlash()
							+ this.csLocalDataFolderName
							+ this.myConfig.GetSlash() + "STIGs");
					for (Pair<String, byte[]> stig : this.UnsavedFileList) {
						this.WriteSTIGToSave(stig.getSecond(), stig.getFirst(),
								fSTIGFolder);
					}
					this.UnsavedFileList.clear();
				}
			} catch (Exception ex) {
				if (!Util.bAllowPrintln)
					break block7;
				ex.printStackTrace();
			}
		}
	}

	private boolean RecursiveDelete(File fIn) {
		File[] files;
		for (File f : files = fIn.listFiles()) {
			if (f.isDirectory()) {
				this.RecursiveDelete(f);
				continue;
			}
			f.delete();
		}
		return fIn.delete();
	}

	private void DeleteSaveFolderActionPerformed(ActionEvent evt) {
		block6: {
			if (this.bHasConfigFolder) {
				try {
					int iAns = JOptionPane.showConfirmDialog(this.getFrame(),
							"Are you sure you want to delete all saved data?",
							"Delete Local Savepoint", 0);
					switch (iAns) {
					case 0: {
						File fToDelete = new File(this.ev_UserLocal
								+ this.myConfig.GetSlash()
								+ this.csLocalDataFolderName);
						if (!this.RecursiveDelete(fToDelete))
							break;
						this.DeleteSaveFolder.setEnabled(false);
						this.CreateSaveFolder.setEnabled(true);
						this.bHasConfigFolder = false;
						break;
					}
					}
				} catch (Exception e) {
					if (!Util.bAllowPrintln)
						break block6;
					e.printStackTrace();
				}
			}
		}
	}

	private void NewFromCurrentActionPerformed(ActionEvent evt) {
		SV_Checklist chk = new SV_Checklist(this.Searcher.GetRootList(),
				this.Searcher.getCurrentSTIG(), true, this.myConfig);
		Util.SetupAssetKeys(this.getFrame(), chk.getRawList(), false);
		ModCL modCL = new ModCL(ModCL.ModCL_Mode.Checklist, this.myConfig, chk,
				this.myCCI, this);
		ArrayList<String> s = new ArrayList<String>();
		s.add("STIG ID");
		s.add("VUL ID");
		s.add("RULE ID");
		if (this.Searcher.getCurrentSTIG().getHasCCI()) {
			s.add("CCI ID");
		}
		modCL.setSortList(s);
		modCL.setLocation(this.getFrame().getX() + 50,
				this.getFrame().getY() + 50);
		modCL.setVisible(true);
	}

	private void NewFromListActionPerformed(ActionEvent evt) {
		STIG st = new STIG();
		st.setSTIG_Title("Custom Checklist");
		SV_Checklist chk = new SV_Checklist(this.Searcher.GetDisplayList(), st,
				true, this.myConfig);
		Util.SetupAssetKeys(this.getFrame(), chk.getRawList(), false);
		ModCL modCL = new ModCL(ModCL.ModCL_Mode.Checklist, this.myConfig, chk,
				this.myCCI, this);
		ArrayList<String> s = new ArrayList<String>();
		s.add("STIG ID");
		s.add("VUL ID");
		s.add("RULE ID");
		if (this.Searcher.bCurrentRefHasCCI()) {
			s.add("CCI ID");
		}
		modCL.setSortList(s);
		modCL.setLocation(this.getFrame().getX() + 50,
				this.getFrame().getY() + 50);
		modCL.setVisible(true);
	}

	private String FileNameOnly(String sInName) {
		String sRet = sInName;
		if (sRet.contains((CharSequence) "/")) {
			sRet = sRet.substring(sRet.lastIndexOf("/"));
		} else if (sRet.contains((CharSequence) "\\")) {
			sRet = sRet.substring(sRet.lastIndexOf("\\"));
		}
		return sRet;
	}

	private boolean LoadSTIGFromZIP(String sZipFile) {
		boolean bRet = false;
		try {
			ZipFile zf = new ZipFile(sZipFile);
			try {
				Enumeration<? extends ZipEntry> e = zf.entries();
				while (e.hasMoreElements()) {
					ZipEntry ze = e.nextElement();
					if (ze.getName().endsWith(".xml")
							&& ze.getName().toLowerCase()
									.contains((CharSequence) "xccdf")) {
						bRet = true;
						if (this.bHasConfigFolder) {
							File fSTIGFolder = new File(this.ev_UserLocal
									+ this.myConfig.GetSlash()
									+ this.csLocalDataFolderName
									+ this.myConfig.GetSlash() + "STIGs");
							if (!fSTIGFolder.exists()) {
								fSTIGFolder.mkdir();
							}
							String[] sFileList = fSTIGFolder.list();
							boolean bFileAlreadyImported = false;
							for (String s : sFileList) {
								if (!s.contains((CharSequence) ze.getName()))
									continue;
								bFileAlreadyImported = true;
							}
							if (!bFileAlreadyImported) {
								File fReturn = this.WriteSTIGToSave(
										zf.getInputStream(ze),
										this.FileNameOnly(ze.getName()),
										fSTIGFolder);
								this.LoadSTIG(fReturn);
							}
						}
					}
					if (!ze.getName().endsWith(".zip"))
						continue;
					File fFolder = new File(this.ev_UserLocal
							+ this.myConfig.GetSlash()
							+ this.csLocalDataFolderName
							+ this.myConfig.GetSlash() + "temp");
					if (!fFolder.exists()) {
						fFolder.mkdir();
					}
					File fWrite = new File(fFolder.getPath()
							+ this.FileNameOnly(ze.getName()));
					BufferedInputStream BIS = new BufferedInputStream(
							zf.getInputStream(ze));
					FileOutputStream FOS = new FileOutputStream(fWrite);
					byte[] b = new byte[BIS.available()];
					BIS.read(b);
					FOS.write(b);
					BIS.close();
					FOS.close();
					if (this.LoadSTIGFromZIP(fWrite.getPath())) {
						bRet = true;
					}
					fWrite.delete();
				}
			} finally {
				zf.close();
				return bRet;
			}
		} catch (Exception e) {
			if (Util.bAllowPrintln) {
				e.printStackTrace();
			}
			return false;
		}
	}

	private void ImportSTIGZipActionPerformed(ActionEvent evt) {
		int iReturn;
		this.SV_FC.setApproveButtonText("Import");
		this.SV_FC.setFileFilter(this.zipFilter);
		if (!this.bHasConfigFolder) {
			iReturn = JOptionPane
					.showConfirmDialog(
							this.getFrame(),
							"Importing from a ZIP archive requires a Savepoint.  Create one now?",
							"Savepoint Required", 0);
			if (iReturn == 0) {
				this.CreateSaveFolderActionPerformed(null);
			} else {
				return;
			}
		}
		if ((iReturn = this.SV_FC.showOpenDialog(this.mainPanel)) == 0) {
			File fReturn = this.SV_FC.getSelectedFile();
			this.jButton3MouseReleased(null);
			if (!this.LoadSTIGFromZIP(fReturn.getPath())) {
				JOptionPane.showMessageDialog(this.getFrame(),
						"No xccdf files found in ZIP Archive.");
			}
		}
	}

	private void ExpHTMLActionPerformed(ActionEvent evt) {
		this.SV_FC = new JFileChooser();
		this.SV_FC.setApproveButtonText("Export");
		this.SV_FC.setFileFilter(this.htmlFilter);
		this.SV_FC.setSelectedFile(new File(this.Searcher.getCurrentSTIG()
				.getSTIG_FileName_NoExt() + ".html"));
		int iReturn = this.SV_FC.showOpenDialog(this.mainPanel);
		if (iReturn == 0) {
			Exporter.STIGOutType sOT = Exporter.STIGOutType.CurrentSearch;
			File fOutput = this.SV_FC.getSelectedFile();
			if (fOutput.exists()) {
				if (JOptionPane.showConfirmDialog(this.getFrame(),
						"File allready exists, overwrite?", "HTML Export", 0) == 0) {
					Export_HTML HTMLX = new Export_HTML(this.Searcher, fOutput,
							sOT, this.myConfig);
					HTMLX.Export();
				}
			} else {
				Export_HTML HTMLX = new Export_HTML(this.Searcher, fOutput,
						sOT, this.myConfig);
				HTMLX.Export();
			}
		}
	}

	private void VClipAccessActionPerformed(ActionEvent evt) {
	}

	private void AddToClipActionPerformed(ActionEvent evt) {
		for (int i = 0; i < this.jList2.getSelectedIndices().length; ++i) {
			this.myVulnClip.AddVul(this.Searcher.GetDisplayList().get(
					this.jList2.getSelectedIndices()[i]));
		}
	}

	private void ViewReadmeActionPerformed(ActionEvent evt) {
		Readme myReadme = new Readme("Readme");
		InputStream fIn = this.myCCI.getClass().getClassLoader()
				.getResourceAsStream(Readme_File);
		BufferedInputStream BIS = new BufferedInputStream(fIn);
		try {
			byte[] bin = new byte[BIS.available()];
			BIS.read(bin);
			String s = new String(bin);
			myReadme.SetText(s);
			myReadme.setLocation(this.getFrame().getX() + 50, this.getFrame()
					.getY() + 50);
			myReadme.setVisible(true);
			myReadme.setAlwaysOnTop(true);
			fIn.close();
		} catch (IOException ex) {
			// empty catch block
		}
	}

	private void SearchAllSTIGsMouseClicked(MouseEvent evt) {
		if (this.SearchAllSTIGs.isSelected()) {
			this.Searcher.SearchAllSTIGs();
			this.UpdateRuleDisplay(this.Searcher.STIGSort(this.mySort));
		} else {
			this.STIGTreeMouseReleased(null);
		}
		this.SetupSelectionBoxes();
	}

	private void LoadChecklist(File f) {
		SaveFile_SI CLSave = new SaveFile_SI(f);
		CLSave.LoadFromSave();
		boolean bHasCCI = false;
		if (CLSave != null) {
			ArrayList<Vuln> avList = CLSave.GetVulnList();
			for (Vuln v : avList) {
				for (String s : v.GetCCIVals()) {
					CCIReader.CCI_Store tmp = this.myCCI.getCCIbyName(s);
					if (tmp == null)
						continue;
					v.addCCI(tmp);
					if (bHasCCI)
						continue;
					bHasCCI = true;
				}
			}
			SV_Checklist chk = new SV_Checklist(avList, CLSave.GetSTIGName(),
					false, this.myConfig);
			ModCL modCL = new ModCL(ModCL.ModCL_Mode.Checklist, this.myConfig,
					chk, this.myCCI, this, CLSave.GetAsset(), f);
			ArrayList<String> s = new ArrayList<String>();
			s.add("STIG ID");
			s.add("VUL ID");
			s.add("RULE ID");
			if (bHasCCI) {
				s.add("CCI ID");
			}
			modCL.setSortList(s);
			modCL.setLocation(this.getFrame().getX() + 50, this.getFrame()
					.getY() + 50);
			modCL.setVisible(true);
		}
	}

	private void OpenChecklistActionPerformed(ActionEvent evt) {
		JFileChooser fSel = new JFileChooser();
		FileFilterFactory CheckFilter = new FileFilterFactory(
				"CheckList Files", "ckl");
		fSel.setFileFilter(CheckFilter);
		fSel.setApproveButtonText("Open");
		int iRet = fSel.showOpenDialog(this.getFrame());
		if (iRet == 0) {
			File fIn = fSel.getSelectedFile();
			this.LoadChecklist(fIn);
		}
	}

	private void btn_Inc_ExcActionPerformed(ActionEvent evt) {
		if (this.btn_Inc_Exc.getText().equals("+")) {
			this.btn_Inc_Exc.setText("-");
			this.btn_Inc_Exc.setToolTipText("Exclude");
		} else if (this.btn_Inc_Exc.getText().equals("-")) {
			this.btn_Inc_Exc.setText("+");
			this.btn_Inc_Exc.setToolTipText("Include Only");
		}
	}

	private void ViewChangeLogActionPerformed(ActionEvent evt) {
		Readme myReadme = new Readme("Change Log");
		InputStream fIn = this.myCCI.getClass().getClassLoader()
				.getResourceAsStream(ChangeLog_File);
		BufferedInputStream BIS = new BufferedInputStream(fIn);
		try {
			byte[] bin = new byte[BIS.available()];
			BIS.read(bin);
			String s = new String(bin);
			myReadme.SetText(s);
			myReadme.setLocation(this.getFrame().getX() + 50, this.getFrame()
					.getY() + 50);
			myReadme.setVisible(true);
			myReadme.setAlwaysOnTop(true);
			fIn.close();
		} catch (IOException ex) {
			// empty catch block
		}
	}

	private void ShowOnlyUniqueActionPerformed(ActionEvent evt) {
		boolean b = this.ShowOnlyUnique.isSelected();
		this.Searcher.setShowOnlyUniqueRules(b);
		if (b) {
			this.Searcher.RefreshSearch();
		} else if (this.Searcher.bIsSearchingAllSTIGs().booleanValue()) {
			this.Searcher.SearchAllSTIGs();
		} else {
			this.Searcher.RawSearch();
		}
		this.UpdateRuleDisplay(this.Searcher.STIGSort(this.mySort));
	}

	private void ExpCSVActionPerformed(ActionEvent evt) {
		CSV_Export_Selector csv = new CSV_Export_Selector(this.myConfig, false);
		csv.SetVulnList(this.Searcher.GetDisplayList());
		csv.setLocation(this.getFrame().getX() + 100,
				this.getFrame().getY() + 100);
		csv.setVisible(true);
	}

	private void CopyToClipboard(String s) {
		StringSelection stringSel = new StringSelection(s);
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
		clip.setContents(stringSel, this.CBO);
	}

}
