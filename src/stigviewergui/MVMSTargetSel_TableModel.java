/*
 * Decompiled with CFR 0_92.
 */
package stigviewergui;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class MVMSTargetSel_TableModel extends AbstractTableModel {
	String[] ColumnNames = new String[] { "STIG Name", "Asset", "AssetID" };
	ArrayList<String[]> Rows = new ArrayList();

	public void addRow(String[] sa) {
		if (sa.length == this.ColumnNames.length) {
			this.Rows.add(sa);
		}
	}

	public int getRowCount() {
		return this.Rows.size();
	}

	public int getColumnCount() {
		return this.ColumnNames.length;
	}

	public void setValueAt(String s, int column, int row) {
		this.Rows.get((int) row)[column] = s;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.Rows.get(rowIndex)[columnIndex];
	}

	public String getColumnName(int columnIndex) {
		return this.ColumnNames[columnIndex];
	}
}
