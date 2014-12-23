/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.OCIL;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.ResourceMap;
import stigviewergui.OCIL.BooleanQuestion;
import stigviewergui.OCIL.ChoiceQuestion;
import stigviewergui.OCIL.Instructions;
import stigviewergui.OCIL.NumericQuestion;
import stigviewergui.OCIL.OCILDriver;
import stigviewergui.OCIL.OCILHandler;
import stigviewergui.OCIL.OCILTableModel;
import stigviewergui.OCIL.Question;
import stigviewergui.OCIL.Questionnaire;
import stigviewergui.OCIL.Step;
import stigviewergui.OCIL.StringQuestion;
import stigviewergui.STIGViewerGUIApp;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class DriverTest extends JFrame implements OCILHandler {
	OCILDriver driver = null;
	private OCILTableModel tableModel = new OCILTableModel();
	private JPanel booleanQuestionPanel;
	private JTextArea booleanQuestionTextArea;
	private JButton choiceQuestionButton;
	private JPanel choiceQuestionPanel;
	private JTextArea choiceQuestionTextArea;
	private JTextField choiceQuestionTextField;
	private JButton falseButton;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane3;
	private JScrollPane jScrollPane4;
	private JScrollPane jScrollPane5;
	private JButton numericQuestionButton;
	private JPanel numericQuestionPanel;
	private JTextArea numericQuestionTextArea;
	private JTextField numericQuestionTextField;
	private JTable questionnaireTable;
	private JPanel questionnaireTablePanel;
	private JButton stringQuestionButton;
	private JPanel stringQuestionPanel;
	private JTextArea stringQuestionTextArea;
	private JTextField stringQuestionTextField;
	private JButton trueButton;

	public DriverTest() {
		this.initComponents();
		this.driver = new OCILDriver(this);
		try {
			this.driver.open("C:\\Windows7STIG_OCIL-ocil.xml");
		} catch (IOException ex) {
			System.exit(0);
		}
		this.tableModel.setQuestionnaireList(this.driver.getQuestionnaires());
		this.questionnaireTable.setModel(this.tableModel);
		CardLayout layout = (CardLayout) this.getContentPane().getLayout();
		layout.show(this.getContentPane(), "questionnaireTableCard");
	}

	public String stepsToString(List<Step> steps, int numberOfTabs) {
		if (steps == null || steps.isEmpty()) {
			return new String();
		}
		boolean spacesPerTab = true;
		String instructions = "";
		String indentation = "";
		for (int i = 0; i < numberOfTabs; ++i) {
			indentation = indentation + "\t";
		}
		for (Step step : steps) {
			instructions = instructions + indentation + "- "
					+ step.getDescription() + "\n";
			instructions = instructions
					+ this.stepsToString(step.getSteps(), numberOfTabs + 1);
		}
		return instructions;
	}

	public String questionText(Question question) {
		String questionText = "";
		questionText = questionText + question.getTitle() + "\n\n";
		for (String line : question.getQuestionText()) {
			questionText = questionText + line + "\n";
		}
		questionText = questionText + "\n";
		questionText = questionText + question.getInstructions().getTitle()
				+ "\n";
		questionText = questionText
				+ this.stepsToString(question.getInstructions().getSteps(), 1);
		return questionText;
	}

	@Override
	public void startBooleanQuestion(BooleanQuestion booleanQuestion) {
		if (booleanQuestion.getModel().equals(
				(Object) BooleanQuestion.Model.YES_NO)) {
			this.trueButton.setText("Yes");
			this.falseButton.setText("No");
		} else if (booleanQuestion.getModel().equals(
				(Object) BooleanQuestion.Model.TRUE_FALSE)) {
			this.trueButton.setText("True");
			this.falseButton.setText("False");
		}
		this.booleanQuestionTextArea
				.setText(this.questionText(booleanQuestion));
		CardLayout layout = (CardLayout) this.getContentPane().getLayout();
		layout.show(this.getContentPane(), "booleanQuestionCard");
	}

	@Override
	public void startChoiceQuestion(ChoiceQuestion choiceQuestion) {
		String qText = this.questionText(choiceQuestion);
		qText = qText + "Pick one choice:\n";
		int index = 1;
		for (String choice : choiceQuestion.getChoices()) {
			qText = qText + index + ". " + choice + "\n";
			++index;
		}
		this.choiceQuestionTextArea.setText(qText);
		this.choiceQuestionTextField.setText("");
		this.choiceQuestionPanel.getRootPane().setDefaultButton(
				this.choiceQuestionButton);
		CardLayout layout = (CardLayout) this.getContentPane().getLayout();
		layout.show(this.getContentPane(), "choiceQuestionCard");
		this.choiceQuestionTextField.grabFocus();
	}

	@Override
	public void startNumericQuestion(NumericQuestion numericQuestion) {
		this.numericQuestionTextArea
				.setText(this.questionText(numericQuestion));
		this.numericQuestionTextField.setText("");
		this.numericQuestionPanel.getRootPane().setDefaultButton(
				this.numericQuestionButton);
		CardLayout layout = (CardLayout) this.getContentPane().getLayout();
		layout.show(this.getContentPane(), "numericQuestionCard");
		this.numericQuestionTextField.grabFocus();
	}

	@Override
	public void startStringQuestion(StringQuestion stringQuestion) {
		this.stringQuestionTextArea.setText(this.questionText(stringQuestion));
		this.stringQuestionTextField.setText("");
		this.stringQuestionPanel.getRootPane().setDefaultButton(
				this.stringQuestionButton);
		CardLayout layout = (CardLayout) this.getContentPane().getLayout();
		layout.show(this.getContentPane(), "stringQuestionCard");
		this.stringQuestionTextField.grabFocus();
	}

	@Override
	public void endQuestionnaire() {
		CardLayout layout = (CardLayout) this.getContentPane().getLayout();
		layout.show(this.getContentPane(), "questionnaireTableCard");
	}

	private void initComponents() {
		this.booleanQuestionPanel = new JPanel();
		this.trueButton = new JButton();
		this.falseButton = new JButton();
		this.jScrollPane3 = new JScrollPane();
		this.booleanQuestionTextArea = new JTextArea();
		this.stringQuestionPanel = new JPanel();
		this.stringQuestionTextField = new JTextField();
		this.stringQuestionButton = new JButton();
		this.jScrollPane1 = new JScrollPane();
		this.stringQuestionTextArea = new JTextArea();
		this.jLabel2 = new JLabel();
		this.numericQuestionPanel = new JPanel();
		this.numericQuestionTextField = new JTextField();
		this.numericQuestionButton = new JButton();
		this.jScrollPane4 = new JScrollPane();
		this.numericQuestionTextArea = new JTextArea();
		this.jLabel1 = new JLabel();
		this.questionnaireTablePanel = new JPanel();
		this.jScrollPane2 = new JScrollPane();
		this.questionnaireTable = new JTable();
		this.choiceQuestionPanel = new JPanel();
		this.choiceQuestionTextField = new JTextField();
		this.choiceQuestionButton = new JButton();
		this.jLabel3 = new JLabel();
		this.jScrollPane5 = new JScrollPane();
		this.choiceQuestionTextArea = new JTextArea();
		this.setDefaultCloseOperation(3);
		this.setName("Form");
		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent evt) {
				DriverTest.this.formWindowClosing(evt);
			}
		});
		this.getContentPane().setLayout(new CardLayout());
		this.booleanQuestionPanel.setName("booleanQuestionPanel");
		ResourceMap resourceMap = ((STIGViewerGUIApp) Application
				.getInstance(STIGViewerGUIApp.class)).getContext()
				.getResourceMap(DriverTest.class);
		this.trueButton.setText(resourceMap.getString("trueButton.text",
				new Object[0]));
		this.trueButton.setName("trueButton");
		this.trueButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				DriverTest.this.trueButtonActionPerformed(evt);
			}
		});
		this.falseButton.setText(resourceMap.getString("falseButton.text",
				new Object[0]));
		this.falseButton.setName("falseButton");
		this.falseButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				DriverTest.this.falseButtonActionPerformed(evt);
			}
		});
		this.jScrollPane3.setName("jScrollPane3");
		this.booleanQuestionTextArea.setColumns(20);
		this.booleanQuestionTextArea.setEditable(false);
		this.booleanQuestionTextArea.setLineWrap(true);
		this.booleanQuestionTextArea.setRows(5);
		this.booleanQuestionTextArea.setTabSize(4);
		this.booleanQuestionTextArea.setName("booleanQuestionTextArea");
		this.jScrollPane3.setViewportView(this.booleanQuestionTextArea);
		GroupLayout booleanQuestionPanelLayout = new GroupLayout(
				this.booleanQuestionPanel);
		this.booleanQuestionPanel.setLayout(booleanQuestionPanelLayout);
		booleanQuestionPanelLayout
				.setHorizontalGroup(booleanQuestionPanelLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								GroupLayout.Alignment.TRAILING,
								booleanQuestionPanelLayout
										.createSequentialGroup()
										.addGroup(
												booleanQuestionPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.TRAILING)
														.addGroup(
																booleanQuestionPanelLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				this.jScrollPane3,
																				-1,
																				400,
																				32767))
														.addGroup(
																GroupLayout.Alignment.LEADING,
																booleanQuestionPanelLayout
																		.createSequentialGroup()
																		.addGap(191,
																				191,
																				191)
																		.addComponent(
																				this.trueButton)
																		.addGap(18,
																				18,
																				18)
																		.addComponent(
																				this.falseButton)))
										.addContainerGap()));
		booleanQuestionPanelLayout
				.setVerticalGroup(booleanQuestionPanelLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								GroupLayout.Alignment.TRAILING,
								booleanQuestionPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(this.jScrollPane3, -1,
												249, 32767)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												booleanQuestionPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																this.trueButton)
														.addComponent(
																this.falseButton))
										.addContainerGap()));
		this.getContentPane().add((Component) this.booleanQuestionPanel,
				"booleanQuestionCard");
		this.stringQuestionPanel.setName("stringQuestionPanel");
		this.stringQuestionTextField.setText(resourceMap.getString(
				"stringQuestionTextField.text", new Object[0]));
		this.stringQuestionTextField.setName("stringQuestionTextField");
		this.stringQuestionButton.setText(resourceMap.getString(
				"stringQuestionButton.text", new Object[0]));
		this.stringQuestionButton.setName("stringQuestionButton");
		this.stringQuestionButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				DriverTest.this.stringQuestionButtonActionPerformed(evt);
			}
		});
		this.jScrollPane1.setName("jScrollPane1");
		this.stringQuestionTextArea.setColumns(20);
		this.stringQuestionTextArea.setEditable(false);
		this.stringQuestionTextArea.setLineWrap(true);
		this.stringQuestionTextArea.setRows(5);
		this.stringQuestionTextArea.setName("stringQuestionTextArea");
		this.jScrollPane1.setViewportView(this.stringQuestionTextArea);
		this.jLabel2.setText(resourceMap.getString("jLabel2.text",
				new Object[0]));
		this.jLabel2.setName("jLabel2");
		GroupLayout stringQuestionPanelLayout = new GroupLayout(
				this.stringQuestionPanel);
		this.stringQuestionPanel.setLayout(stringQuestionPanelLayout);
		stringQuestionPanelLayout
				.setHorizontalGroup(stringQuestionPanelLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								stringQuestionPanelLayout
										.createSequentialGroup()
										.addGroup(
												stringQuestionPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addGroup(
																stringQuestionPanelLayout
																		.createSequentialGroup()
																		.addGap(61,
																				61,
																				61)
																		.addComponent(
																				this.jLabel2)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				this.stringQuestionTextField,
																				-2,
																				100,
																				-2)
																		.addGap(33,
																				33,
																				33)
																		.addComponent(
																				this.stringQuestionButton))
														.addGroup(
																stringQuestionPanelLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				this.jScrollPane1,
																				-1,
																				400,
																				32767)))
										.addContainerGap()));
		stringQuestionPanelLayout
				.setVerticalGroup(stringQuestionPanelLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								GroupLayout.Alignment.TRAILING,
								stringQuestionPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(this.jScrollPane1, -1,
												249, 32767)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												stringQuestionPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.TRAILING,
																false)
														.addComponent(
																this.jLabel2,
																-1, -1, 32767)
														.addComponent(
																this.stringQuestionButton,
																-1, -1, 32767)
														.addComponent(
																this.stringQuestionTextField,
																-2, -1, -2))
										.addContainerGap()));
		this.getContentPane().add((Component) this.stringQuestionPanel,
				"stringQuestionCard");
		this.numericQuestionPanel.setName("numericQuestionPanel");
		this.numericQuestionTextField.setText(resourceMap.getString(
				"numericQuestionTextField.text", new Object[0]));
		this.numericQuestionTextField.setName("numericQuestionTextField");
		this.numericQuestionButton.setText(resourceMap.getString(
				"numericQuestionButton.text", new Object[0]));
		this.numericQuestionButton.setName("numericQuestionButton");
		this.numericQuestionButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				DriverTest.this.numericQuestionButtonActionPerformed(evt);
			}
		});
		this.jScrollPane4.setName("jScrollPane4");
		this.numericQuestionTextArea.setColumns(20);
		this.numericQuestionTextArea.setEditable(false);
		this.numericQuestionTextArea.setLineWrap(true);
		this.numericQuestionTextArea.setRows(5);
		this.numericQuestionTextArea.setName("numericQuestionTextArea");
		this.jScrollPane4.setViewportView(this.numericQuestionTextArea);
		this.jLabel1.setText(resourceMap.getString("jLabel1.text",
				new Object[0]));
		this.jLabel1.setName("jLabel1");
		GroupLayout numericQuestionPanelLayout = new GroupLayout(
				this.numericQuestionPanel);
		this.numericQuestionPanel.setLayout(numericQuestionPanelLayout);
		numericQuestionPanelLayout
				.setHorizontalGroup(numericQuestionPanelLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								numericQuestionPanelLayout
										.createSequentialGroup()
										.addGap(88, 88, 88)
										.addComponent(this.jLabel1)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												this.numericQuestionTextField,
												-1, 72, 32767)
										.addGap(26, 26, 26)
										.addComponent(
												this.numericQuestionButton)
										.addGap(124, 124, 124))
						.addGroup(
								numericQuestionPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(this.jScrollPane4, -1,
												400, 32767).addContainerGap()));
		numericQuestionPanelLayout
				.setVerticalGroup(numericQuestionPanelLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								GroupLayout.Alignment.TRAILING,
								numericQuestionPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(this.jScrollPane4, -1,
												249, 32767)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												numericQuestionPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																this.numericQuestionButton,
																GroupLayout.Alignment.TRAILING,
																-1, -1, 32767)
														.addGroup(
																numericQuestionPanelLayout
																		.createParallelGroup(
																				GroupLayout.Alignment.BASELINE)
																		.addComponent(
																				this.numericQuestionTextField,
																				-2,
																				-1,
																				-2)
																		.addComponent(
																				this.jLabel1,
																				-1,
																				-1,
																				32767)))
										.addContainerGap()));
		this.getContentPane().add((Component) this.numericQuestionPanel,
				"numericQuestionCard");
		this.questionnaireTablePanel.setName("questionnaireTablePanel");
		this.jScrollPane2.setName("jScrollPane2");
		this.questionnaireTable.setModel(new DefaultTableModel(new Object[0][],
				new String[] { "Title 1", "Title 2" }));
		this.questionnaireTable.setName("questionnaireTable");
		this.questionnaireTable.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent evt) {
				DriverTest.this.questionnaireTableMouseClicked(evt);
			}
		});
		this.jScrollPane2.setViewportView(this.questionnaireTable);
		this.questionnaireTable
				.getColumnModel()
				.getColumn(0)
				.setHeaderValue(
						resourceMap.getString(
								"questionnaireTable.columnModel.title0",
								new Object[0]));
		this.questionnaireTable
				.getColumnModel()
				.getColumn(1)
				.setHeaderValue(
						resourceMap.getString(
								"questionnaireTable.columnModel.title1",
								new Object[0]));
		GroupLayout questionnaireTablePanelLayout = new GroupLayout(
				this.questionnaireTablePanel);
		this.questionnaireTablePanel.setLayout(questionnaireTablePanelLayout);
		questionnaireTablePanelLayout
				.setHorizontalGroup(questionnaireTablePanelLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(this.jScrollPane2, -1, 420, 32767));
		questionnaireTablePanelLayout
				.setVerticalGroup(questionnaireTablePanelLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(this.jScrollPane2, -1, 300, 32767));
		this.getContentPane().add((Component) this.questionnaireTablePanel,
				"questionnaireTableCard");
		this.choiceQuestionPanel.setName("choiceQuestionPanel");
		this.choiceQuestionTextField.setText(resourceMap.getString(
				"choiceQuestionTextField.text", new Object[0]));
		this.choiceQuestionTextField.setName("choiceQuestionTextField");
		this.choiceQuestionButton.setText(resourceMap.getString(
				"choiceQuestionButton.text", new Object[0]));
		this.choiceQuestionButton.setName("choiceQuestionButton");
		this.choiceQuestionButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				DriverTest.this.choiceQuestionButtonActionPerformed(evt);
			}
		});
		this.jLabel3.setText(resourceMap.getString("jLabel3.text",
				new Object[0]));
		this.jLabel3.setName("jLabel3");
		this.jScrollPane5.setName("jScrollPane5");
		this.choiceQuestionTextArea.setColumns(20);
		this.choiceQuestionTextArea.setEditable(false);
		this.choiceQuestionTextArea.setRows(5);
		this.choiceQuestionTextArea.setName("choiceQuestionTextArea");
		this.jScrollPane5.setViewportView(this.choiceQuestionTextArea);
		GroupLayout choiceQuestionPanelLayout = new GroupLayout(
				this.choiceQuestionPanel);
		this.choiceQuestionPanel.setLayout(choiceQuestionPanelLayout);
		choiceQuestionPanelLayout
				.setHorizontalGroup(choiceQuestionPanelLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								choiceQuestionPanelLayout
										.createSequentialGroup()
										.addGroup(
												choiceQuestionPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addGroup(
																choiceQuestionPanelLayout
																		.createSequentialGroup()
																		.addGap(82,
																				82,
																				82)
																		.addComponent(
																				this.jLabel3)
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				this.choiceQuestionTextField,
																				-2,
																				54,
																				-2)
																		.addGap(18,
																				18,
																				18)
																		.addComponent(
																				this.choiceQuestionButton))
														.addGroup(
																choiceQuestionPanelLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				this.jScrollPane5,
																				-1,
																				400,
																				32767)))
										.addContainerGap()));
		choiceQuestionPanelLayout
				.setVerticalGroup(choiceQuestionPanelLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								GroupLayout.Alignment.TRAILING,
								choiceQuestionPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(this.jScrollPane5, -1,
												249, 32767)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												choiceQuestionPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																this.choiceQuestionTextField,
																-2, -1, -2)
														.addComponent(
																this.jLabel3)
														.addComponent(
																this.choiceQuestionButton))
										.addContainerGap()));
		this.getContentPane().add((Component) this.choiceQuestionPanel,
				"choiceQuestionCard");
		this.pack();
	}

	private void trueButtonActionPerformed(ActionEvent evt) {
		this.driver.endBooleanQuestion(true);
	}

	private void falseButtonActionPerformed(ActionEvent evt) {
		this.driver.endBooleanQuestion(false);
	}

	private void stringQuestionButtonActionPerformed(ActionEvent evt) {
		this.stringQuestionPanel.getRootPane().setDefaultButton(null);
		this.driver.endStringQuestion(this.stringQuestionTextField.getText());
	}

	private void numericQuestionButtonActionPerformed(ActionEvent evt) {
		BigDecimal response;
		this.numericQuestionPanel.getRootPane().setDefaultButton(null);
		try {
			response = new BigDecimal(this.numericQuestionTextField.getText());
		} catch (NumberFormatException ex) {
			return;
		}
		this.driver.endNumericQuestion(response);
	}

	private void questionnaireTableMouseClicked(MouseEvent evt) {
		if (evt.getClickCount() == 2) {
			this.driver.startQuestionnaire(this.questionnaireTable
					.getSelectedRow());
		}
	}

	private void choiceQuestionButtonActionPerformed(ActionEvent evt) {
		int response;
		this.numericQuestionPanel.getRootPane().setDefaultButton(null);
		try {
			response = Integer.parseInt(this.choiceQuestionTextField.getText());
		} catch (NumberFormatException ex) {
			return;
		}
		this.driver.endChoiceQuestion(response - 1);
	}

	private void formWindowClosing(WindowEvent evt) {
		int response = JOptionPane.showConfirmDialog(this,
				"Do you want to save the results?", "Woo! Title!", 0);
		if (response == 0) {
			this.driver.write();
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				new DriverTest().setVisible(true);
			}
		});
	}

}
