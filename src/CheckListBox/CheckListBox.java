/*
 * Decompiled with CFR 0_92.
 */
package CheckListBox;

import CheckListBox.CHK_CellRenderer;
import CheckListBox.CHK_Model;
import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.GroupLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class CheckListBox extends JPanel {
	private Vector<CHK_Model> itemList;
	private Vector<String> itemNames;
	JList jList1;
	private JScrollPane jScrollPane1;

	public CheckListBox() {
		this.initComponents();
		this.itemList = new Vector();
		this.itemNames = new Vector();
		CHK_CellRenderer CLB_RENDER = new CHK_CellRenderer();
		this.jList1.setCellRenderer(CLB_RENDER);
		this.jList1.setListData(this.itemList);
		this.SetCustomMouseDoubleClickHandler();
	}

	private void initComponents() {
		this.jScrollPane1 = new JScrollPane();
		this.jList1 = new JList();
		this.setInheritsPopupMenu(true);
		this.jList1.setInheritsPopupMenu(true);
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

	private void SetCustomMouseDoubleClickHandler() {
		final CheckListBox me = this;
		this.jList1.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					me.ToggleCheck(me.getSelectedIndex());
				}
			}
		});
	}

	public Vector<String> getItems() {
		this.itemNames.clear();
		for (CHK_Model mod : this.itemList) {
			this.itemNames.add(mod.sMemberName);
		}
		return this.itemNames;
	}

	public void add(String name, boolean bIsSet) {
		CHK_Model temp = new CHK_Model(name, bIsSet);
		if (temp != null) {
			this.itemList.add(temp);
			this.jList1.setListData(this.itemList);
			this.jList1.updateUI();
		}
	}

	public void SetModelArray(Vector<CHK_Model> List) {
		this.itemList = List;
		this.jList1.setListData(this.itemList);
		this.jList1.updateUI();
	}

	public void clearList() {
		this.itemList.clear();
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

	public void setitemNames(Vector<String> itemNames) {
		this.itemNames = itemNames;
		this.itemList.clear();
		for (String s : itemNames) {
			this.itemList.add(new CHK_Model(s));
		}
		this.jList1.setListData(this.itemList);
		this.jList1.updateUI();
	}

	public int getItemCount() {
		return this.itemList.size();
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

	public void ChangeEntry(String s) {
		this.jList1.getModel().getElementAt(this.jList1.getSelectedIndex());
	}

	public void ToggleCheck(int i) {
		if (i >= 0) {
			this.itemList.get(i).Toggle();
			this.jList1.updateUI();
		}
	}

	public boolean GetIsChecked(int i) {
		return this.itemList.get(i).IsChecked();
	}

	public void SetPopupMenu(JPopupMenu pop) {
		this.jList1.setComponentPopupMenu(pop);
	}

}
