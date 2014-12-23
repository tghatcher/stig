/*
 * Decompiled with CFR 0_92.
 */
package ColorListBox;

import ColorListBox.CLB_CellRenderer;
import ColorListBox.CLB_EventHandler;
import ColorListBox.CLB_Model;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;
import javax.swing.GroupLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ColorListBox extends JPanel {
	private Vector<CLB_Model> itemList;
	private Vector<String> itemNames;
	private CLB_EventHandler ListSelect;
	JList jList1;
	private JScrollPane jScrollPane1;

	public ColorListBox(CLB_EventHandler ListSelection) {
		this.initComponents();
		this.ListSelect = ListSelection;
		this.itemList = new Vector();
		this.itemNames = new Vector();
		CLB_CellRenderer CLB_RENDER = new CLB_CellRenderer();
		this.jList1.setCellRenderer(CLB_RENDER);
		this.jList1.setListData(this.itemList);
	}

	public ColorListBox() {
		this.initComponents();
		this.itemList = new Vector();
		this.itemNames = new Vector();
		CLB_CellRenderer CLB_RENDER = new CLB_CellRenderer();
		this.jList1.setCellRenderer(CLB_RENDER);
		this.jList1.setListData(this.itemList);
	}

	private void initComponents() {
		this.jScrollPane1 = new JScrollPane();
		this.jList1 = new JList();
		this.setInheritsPopupMenu(true);
		this.jList1.setInheritsPopupMenu(true);
		this.jList1.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent evt) {
				ColorListBox.this.jList1ValueChanged(evt);
			}
		});
		this.jList1.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {
				ColorListBox.this.jList1PropertyChange(evt);
			}
		});
		this.jList1.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent evt) {
				ColorListBox.this.jList1KeyReleased(evt);
			}
		});
		this.jScrollPane1.setViewportView(this.jList1);
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane1,
				-1, 198, 32767));
		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane1,
				-1, 326, 32767));
	}

	private void jList1ValueChanged(ListSelectionEvent evt) {
		this.ListSelect.CL_ListSelectionEvent(evt);
	}

	private void jList1KeyReleased(KeyEvent evt) {
		this.ListSelect.CL_KeyReleased(evt);
	}

	private void jList1PropertyChange(PropertyChangeEvent evt) {
	}

	public Vector<String> getItems() {
		this.itemNames.clear();
		for (CLB_Model mod : this.itemList) {
			this.itemNames.add(mod.sMemberName);
		}
		return this.itemNames;
	}

	public void add(String name, Color clBack, Color clText) {
		CLB_Model temp = new CLB_Model(name, clBack, clText);
		if (temp != null) {
			this.itemList.add(temp);
			this.jList1.setListData(this.itemList);
			this.jList1.updateUI();
		}
	}

	public void SetModelArray(Vector<CLB_Model> List) {
		this.itemList = List;
		this.jList1.setListData(this.itemList);
		this.jList1.updateUI();
	}

	public void clearList() {
		this.itemList.clear();
		this.jList1.updateUI();
	}

	public void SetCarot(int i) {
		int x = 16;
		int min = this.jScrollPane1.getVerticalScrollBar().getMinimum();
		int max = this.jScrollPane1.getVerticalScrollBar().getMaximum();
		if ((i *= x) < min) {
			i = min;
		}
		if (i > max) {
			i = max;
		}
		this.jScrollPane1.getVerticalScrollBar().setValue(i);
	}

	public void SetColors(Color clBack, Color clText) {
		((CLB_Model) this.jList1.getSelectedValue()).SetColors(clBack, clText);
	}

	public void SetColorOf(int i, Color clBack, Color clText) {
		this.itemList.get(i).SetColors(clBack, clText);
	}

	public void setitemNames(Vector<String> itemNames) {
		this.itemNames = itemNames;
		this.itemList.clear();
		for (String s : itemNames) {
			this.itemList.add(new CLB_Model(s));
		}
		this.jList1.setListData(this.itemList);
		this.jList1.updateUI();
	}

	public void setSelectedIndex(int i) {
		this.jList1.setSelectedIndex(i);
	}

	public int getSelectedIndex() {
		return this.jList1.getSelectedIndex();
	}

	public int[] getSelectedIndecies() {
		return this.jList1.getSelectedIndices();
	}

	public void setSelectedIndecies(int[] ia) {
		this.jList1.setSelectedIndices(ia);
	}

	public int getLastSelectedIndex() {
		return 0;
	}

	public void ChangeEntry(String s) {
		this.jList1.getModel().getElementAt(this.jList1.getSelectedIndex());
	}

	public void SetPopupMenu(JPopupMenu pop) {
		this.jList1.setComponentPopupMenu(pop);
	}

}
