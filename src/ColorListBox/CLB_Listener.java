/*
 * Decompiled with CFR 0_92.
 */
package ColorListBox;

import ColorListBox.ColorListBox;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JList;

public class CLB_Listener implements MouseListener, KeyListener {
	protected ColorListBox m_parent;
	protected JList m_list;

	public CLB_Listener(ColorListBox parent) {
		this.m_parent = parent;
		this.m_list = parent.jList1;
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}
}
