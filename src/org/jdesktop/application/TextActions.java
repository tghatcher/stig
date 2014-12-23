/*
 * Decompiled with CFR 0_92.
 */
package org.jdesktop.application;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.FlavorEvent;
import java.awt.datatransfer.FlavorListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JComponent;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.Caret;
import javax.swing.text.JTextComponent;
import org.jdesktop.application.AbstractBean;
import org.jdesktop.application.Action;
import org.jdesktop.application.ApplicationActionMap;
import org.jdesktop.application.ApplicationContext;

class TextActions extends AbstractBean {
	private final ApplicationContext context;
	private final CaretListener textComponentCaretListener;
	private final PropertyChangeListener textComponentPCL;
	private final String markerActionKey = "TextActions.markerAction";
	private final Action markerAction;
	private boolean copyEnabled = false;
	private boolean cutEnabled = false;
	private boolean pasteEnabled = false;
	private boolean deleteEnabled = false;

	public TextActions(ApplicationContext applicationContext) {
		this.context = applicationContext;
		this.markerAction = new AbstractAction() {

			public void actionPerformed(ActionEvent actionEvent) {
			}
		};
		this.textComponentCaretListener = new TextComponentCaretListener();
		this.textComponentPCL = new TextComponentPCL();
		this.getClipboard().addFlavorListener(new ClipboardListener());
	}

	private ApplicationContext getContext() {
		return this.context;
	}

	private JComponent getFocusOwner() {
		return this.getContext().getFocusOwner();
	}

	private Clipboard getClipboard() {
		return this.getContext().getClipboard();
	}

	void updateFocusOwner(JComponent jComponent, JComponent jComponent2) {
		JTextComponent jTextComponent;
		if (jComponent instanceof JTextComponent) {
			jTextComponent = (JTextComponent) jComponent;
			jTextComponent.removeCaretListener(this.textComponentCaretListener);
			jTextComponent.removePropertyChangeListener(this.textComponentPCL);
		}
		if (jComponent2 instanceof JTextComponent) {
			jTextComponent = (JTextComponent) jComponent2;
			this.maybeInstallTextActions(jTextComponent);
			this.updateTextActions(jTextComponent);
			jTextComponent.addCaretListener(this.textComponentCaretListener);
			jTextComponent.addPropertyChangeListener(this.textComponentPCL);
		} else if (jComponent2 == null) {
			this.setCopyEnabled(false);
			this.setCutEnabled(false);
			this.setPasteEnabled(false);
			this.setDeleteEnabled(false);
		}
	}

	private void updateTextActions(JTextComponent jTextComponent) {
		Caret caret = jTextComponent.getCaret();
		boolean bl = caret.getDot() != caret.getMark();
		boolean bl2 = jTextComponent.isEditable();
		boolean bl3 = this.getClipboard().isDataFlavorAvailable(
				DataFlavor.stringFlavor);
		this.setCopyEnabled(bl);
		this.setCutEnabled(bl2 && bl);
		this.setDeleteEnabled(bl2 && bl);
		this.setPasteEnabled(bl2 && bl3);
	}

	private void maybeInstallTextActions(JTextComponent jTextComponent) {
		ActionMap actionMap = jTextComponent.getActionMap();
		if (actionMap.get("TextActions.markerAction") == null) {
			actionMap.put("TextActions.markerAction", this.markerAction);
			ApplicationActionMap applicationActionMap = this.getContext()
					.getActionMap(this.getClass(), this);
			for (Object object : applicationActionMap.keys()) {
				actionMap.put(object, applicationActionMap.get(object));
			}
		}
	}

	private int getCurrentEventModifiers() {
		int n = 0;
		AWTEvent aWTEvent = EventQueue.getCurrentEvent();
		if (aWTEvent instanceof InputEvent) {
			n = ((InputEvent) aWTEvent).getModifiers();
		} else if (aWTEvent instanceof ActionEvent) {
			n = ((ActionEvent) aWTEvent).getModifiers();
		}
		return n;
	}

	private void invokeTextAction(JTextComponent jTextComponent, String string) {
		ActionMap actionMap = jTextComponent.getActionMap().getParent();
		long l = EventQueue.getMostRecentEventTime();
		int n = this.getCurrentEventModifiers();
		ActionEvent actionEvent = new ActionEvent(jTextComponent, 1001, string,
				l, n);
		actionMap.get(string).actionPerformed(actionEvent);
	}

	@org.jdesktop.application.Action(enabledProperty = "cutEnabled")
	public void cut(ActionEvent actionEvent) {
		Object object = actionEvent.getSource();
		if (object instanceof JTextComponent) {
			this.invokeTextAction((JTextComponent) object, "cut");
		}
	}

	public boolean isCutEnabled() {
		return this.cutEnabled;
	}

	public void setCutEnabled(boolean bl) {
		boolean bl2 = this.cutEnabled;
		this.cutEnabled = bl;
		this.firePropertyChange("cutEnabled", bl2, this.cutEnabled);
	}

	@org.jdesktop.application.Action(enabledProperty = "copyEnabled")
	public void copy(ActionEvent actionEvent) {
		Object object = actionEvent.getSource();
		if (object instanceof JTextComponent) {
			this.invokeTextAction((JTextComponent) object, "copy");
		}
	}

	public boolean isCopyEnabled() {
		return this.copyEnabled;
	}

	public void setCopyEnabled(boolean bl) {
		boolean bl2 = this.copyEnabled;
		this.copyEnabled = bl;
		this.firePropertyChange("copyEnabled", bl2, this.copyEnabled);
	}

	@org.jdesktop.application.Action(enabledProperty = "pasteEnabled")
	public void paste(ActionEvent actionEvent) {
		Object object = actionEvent.getSource();
		if (object instanceof JTextComponent) {
			this.invokeTextAction((JTextComponent) object, "paste");
		}
	}

	public boolean isPasteEnabled() {
		return this.pasteEnabled;
	}

	public void setPasteEnabled(boolean bl) {
		boolean bl2 = this.pasteEnabled;
		this.pasteEnabled = bl;
		this.firePropertyChange("pasteEnabled", bl2, this.pasteEnabled);
	}

	@org.jdesktop.application.Action(enabledProperty = "deleteEnabled")
	public void delete(ActionEvent actionEvent) {
		Object object = actionEvent.getSource();
		if (object instanceof JTextComponent) {
			this.invokeTextAction((JTextComponent) object, "delete-next");
		}
	}

	public boolean isDeleteEnabled() {
		return this.deleteEnabled;
	}

	public void setDeleteEnabled(boolean bl) {
		boolean bl2 = this.deleteEnabled;
		this.deleteEnabled = bl;
		this.firePropertyChange("deleteEnabled", bl2, this.deleteEnabled);
	}

	private final class ClipboardListener implements FlavorListener {
		private ClipboardListener() {
		}

		public void flavorsChanged(FlavorEvent flavorEvent) {
			JComponent jComponent = TextActions.this.getFocusOwner();
			if (jComponent instanceof JTextComponent) {
				TextActions.this.updateTextActions((JTextComponent) jComponent);
			}
		}
	}

	private final class TextComponentCaretListener implements CaretListener {
		private TextComponentCaretListener() {
		}

		public void caretUpdate(CaretEvent caretEvent) {
			TextActions.this.updateTextActions((JTextComponent) caretEvent
					.getSource());
		}
	}

	private final class TextComponentPCL implements PropertyChangeListener {
		private TextComponentPCL() {
		}

		public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
			String string = propertyChangeEvent.getPropertyName();
			if (string == null || "editable".equals(string)) {
				TextActions.this
						.updateTextActions((JTextComponent) propertyChangeEvent
								.getSource());
			}
		}
	}

}
