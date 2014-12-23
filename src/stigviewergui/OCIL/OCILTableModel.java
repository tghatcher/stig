/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui.OCIL;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import stigviewergui.OCIL.Questionnaire;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class OCILTableModel extends AbstractTableModel {
	private String[] columnNames = new String[] { "Questionnaire", "Result" };
	private List<Questionnaire> questionnaireList = null;

	public void setQuestionnaireList(List<Questionnaire> questionnaireList) {
		this.questionnaireList = questionnaireList;
	}

	@Override
	public int getRowCount() {
		if (this.questionnaireList == null) {
			return 0;
		}
		return this.questionnaireList.size();
	}

	@Override
	public int getColumnCount() {
		return this.columnNames.length;
	}

	@Override
	public Object getValueAt(int row, int column) {
		Questionnaire questionnaire = this.questionnaireList.get(row);
		switch (column) {
		case 0: {
			return "" + (row + 1) + ". " + questionnaire.getTitle();
		}
		case 1: {
			return questionnaire.getResponse();
		}
		}
		throw new AssertionError();
	}

	public Questionnaire getQuestionnaire(int index) {
		return this.questionnaireList.get(index);
	}

	@Override
	public String getColumnName(int columnIndex) {
		return this.columnNames[columnIndex];
	}
}
