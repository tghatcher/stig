/*
 * Decompiled with CFR 0_92.
 */
package CheckListBox;

import CheckListBox.CHK_Model;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class CHK_CellRenderer extends JCheckBox implements ListCellRenderer {
	protected static Border m_noFocusBorder = new EmptyBorder(1, 1, 1, 1);

	public CHK_CellRenderer() {
		this.setOpaque(true);
		this.setBorder(m_noFocusBorder);
	}

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		CHK_Model myModel = (CHK_Model) value;
		if (value != null) {
			this.setText(value.toString());
			this.setSelected(myModel.bIsChecked);
		}
		this.setBackground(isSelected ? list.getSelectionBackground() : list
				.getBackground());
		this.setForeground(isSelected ? list.getSelectionForeground() : list
				.getForeground());
		this.setFont(list.getFont());
		this.setBorder(isSelected ? BorderFactory.createEtchedBorder(
				Color.BLACK, Color.LIGHT_GRAY) : m_noFocusBorder);
		return this;
	}
}
