/*
 * Decompiled with CFR 0_92.
 */
package ColorListBox;

import ColorListBox.CLB_Model;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class CLB_CellRenderer extends JLabel implements ListCellRenderer {
	protected static Border m_noFocusBorder = new EmptyBorder(1, 1, 1, 1);

	public CLB_CellRenderer() {
		this.setOpaque(true);
		this.setBorder(m_noFocusBorder);
	}

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		CLB_Model myModel = (CLB_Model) value;
		if (value != null) {
			this.setText(value.toString());
			if (isSelected) {
				int Dark = 30;
				int Red = myModel.BackColor.getRed();
				int Green = myModel.BackColor.getGreen();
				int Blue = myModel.BackColor.getBlue();
				Red = Red - Dark >= 0 ? (Red -= Dark) : 0;
				Green = Green - Dark >= 0 ? (Green -= Dark) : 0;
				Blue = Blue - Dark >= 0 ? (Blue -= Dark) : 0;
				Color Back = new Color(Red, Green, Blue);
				this.setBackground(Back);
				this.setForeground(myModel.TextColor);
			} else {
				this.setBackground(myModel.BackColor);
				this.setForeground(myModel.TextColor);
			}
		} else {
			this.setBackground(isSelected ? list.getSelectionBackground()
					: list.getBackground());
			this.setForeground(isSelected ? list.getSelectionForeground()
					: list.getForeground());
		}
		this.setFont(list.getFont());
		this.setBorder(isSelected ? BorderFactory.createEtchedBorder(
				Color.BLACK, Color.LIGHT_GRAY) : m_noFocusBorder);
		return this;
	}
}
