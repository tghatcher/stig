/*
 * Decompiled with CFR 0_92.
 */
package org.jdesktop.application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.RootPaneContainer;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;
import org.jdesktop.application.ApplicationAction;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.Task;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
final class DefaultInputBlocker extends Task.InputBlocker {
	private static final Logger logger = Logger
			.getLogger(DefaultInputBlocker.class.getName());
	private JDialog modalDialog = null;

	DefaultInputBlocker(Task task, Task.BlockingScope blockingScope,
			Object object, ApplicationAction applicationAction) {
		super(task, blockingScope, object, applicationAction);
	}

	private void setActionTargetBlocked(boolean bl) {
		Action action = (Action) this.getTarget();
		action.setEnabled(!bl);
	}

	private void setComponentTargetBlocked(boolean bl) {
		Component component = (Component) this.getTarget();
		component.setEnabled(!bl);
	}

	private void blockingDialogComponents(Component component,
			List<Component> list) {
		String string = component.getName();
		if (string != null && string.startsWith("BlockingDialog")) {
			list.add(component);
		}
		if (component instanceof Container) {
			for (Component component2 : ((Container) component).getComponents()) {
				this.blockingDialogComponents(component2, list);
			}
		}
	}

	private List<Component> blockingDialogComponents(Component component) {
		ArrayList<Component> arrayList = new ArrayList<Component>();
		this.blockingDialogComponents(component, arrayList);
		return arrayList;
	}

	private void injectBlockingDialogComponents(Component component) {
		ApplicationAction applicationAction;
		ResourceMap resourceMap = this.getTask().getResourceMap();
		if (resourceMap != null) {
			resourceMap.injectComponents(component);
		}
		if ((applicationAction = this.getAction()) != null) {
			ResourceMap resourceMap2 = applicationAction.getResourceMap();
			String string = applicationAction.getName();
			for (Component component2 : this
					.blockingDialogComponents(component)) {
				component2.setName(string + "." + component2.getName());
			}
			resourceMap2.injectComponents(component);
		}
	}

	private JDialog createBlockingDialog() {
		Object object;
		AbstractButton abstractButton;
		JOptionPane jOptionPane = new JOptionPane();
		if (this.getTask().getUserCanCancel()) {
			abstractButton = new JButton();
			abstractButton.setName("BlockingDialog.cancelButton");
			object = new ActionListener() {

				public void actionPerformed(ActionEvent actionEvent) {
					DefaultInputBlocker.this.getTask().cancel(true);
				}
			};
			abstractButton.addActionListener((ActionListener) object);
			jOptionPane.setOptions(new Object[] { abstractButton });
		} else {
			jOptionPane.setOptions(new Object[0]);
		}
		abstractButton = (Component) this.getTarget();
		object = this.getTask().getTitle();
		Object object2 = object == null ? "BlockingDialog" : object;
		final JDialog jDialog = jOptionPane.createDialog(abstractButton,
				(String) object2);
		jDialog.setModal(true);
		jDialog.setName("BlockingDialog");
		jDialog.setDefaultCloseOperation(0);
		WindowAdapter windowAdapter = new WindowAdapter() {

			public void windowClosing(WindowEvent windowEvent) {
				if (DefaultInputBlocker.this.getTask().getUserCanCancel()) {
					DefaultInputBlocker.this.getTask().cancel(true);
					jDialog.setVisible(false);
				}
			}
		};
		jDialog.addWindowListener(windowAdapter);
		jOptionPane.setName("BlockingDialog.optionPane");
		this.injectBlockingDialogComponents(jDialog);
		this.recreateOptionPaneMessage(jOptionPane);
		jDialog.pack();
		return jDialog;
	}

	private void recreateOptionPaneMessage(JOptionPane jOptionPane) {
		Object object = jOptionPane.getMessage();
		if (object instanceof String) {
			Font font = jOptionPane.getFont();
			final JTextArea jTextArea = new JTextArea((String) object);
			jTextArea.setFont(font);
			int n = jTextArea.getFontMetrics(font).getHeight();
			Insets insets = new Insets(0, 0, n, 24);
			jTextArea.setMargin(insets);
			jTextArea.setEditable(false);
			jTextArea.setWrapStyleWord(true);
			jTextArea.setBackground(jOptionPane.getBackground());
			JPanel jPanel = new JPanel(new BorderLayout());
			jPanel.add((Component) jTextArea, "Center");
			final JProgressBar jProgressBar = new JProgressBar();
			jProgressBar.setName("BlockingDialog.progressBar");
			jProgressBar.setIndeterminate(true);
			PropertyChangeListener propertyChangeListener = new PropertyChangeListener() {

				public void propertyChange(
						PropertyChangeEvent propertyChangeEvent) {
					if ("progress"
							.equals(propertyChangeEvent.getPropertyName())) {
						jProgressBar.setIndeterminate(false);
						jProgressBar.setValue((Integer) propertyChangeEvent
								.getNewValue());
						DefaultInputBlocker.this
								.updateStatusBarString(jProgressBar);
					} else if ("message".equals(propertyChangeEvent
							.getPropertyName())) {
						jTextArea.setText((String) propertyChangeEvent
								.getNewValue());
					}
				}
			};
			this.getTask().addPropertyChangeListener(propertyChangeListener);
			jPanel.add((Component) jProgressBar, "South");
			this.injectBlockingDialogComponents(jPanel);
			jOptionPane.setMessage(jPanel);
		}
	}

	private void updateStatusBarString(JProgressBar jProgressBar) {
		if (!jProgressBar.isStringPainted()) {
			return;
		}
		String string = "progressBarStringFormat";
		if (jProgressBar.getClientProperty(string) == null) {
			jProgressBar.putClientProperty(string, jProgressBar.getString());
		}
		String string2 = (String) jProgressBar.getClientProperty(string);
		if (jProgressBar.getValue() <= 0) {
			jProgressBar.setString("");
		} else if (string2 == null) {
			jProgressBar.setString(null);
		} else {
			double d = (double) jProgressBar.getValue() / 100.0;
			long l = this.getTask().getExecutionDuration(TimeUnit.SECONDS);
			long l2 = l / 60;
			long l3 = (long) (0.5 + (double) l / d) - l;
			long l4 = l3 / 60;
			String string3 = String.format(string2, l2, l - l2 * 60, l4, l3
					- l4 * 60);
			jProgressBar.setString(string3);
		}
	}

	private void showBusyGlassPane(boolean bl) {
		RootPaneContainer rootPaneContainer = null;
		for (Component component = (Component) this.getTarget(); component != null; component = component
				.getParent()) {
			if (!(component instanceof RootPaneContainer))
				continue;
			rootPaneContainer = (RootPaneContainer) component;
			break;
		}
		if (rootPaneContainer != null) {
			if (bl) {
				JMenuBar jMenuBar = rootPaneContainer.getRootPane()
						.getJMenuBar();
				if (jMenuBar != null) {
					jMenuBar.putClientProperty(this, jMenuBar.isEnabled());
					jMenuBar.setEnabled(false);
				}
				BusyGlassPane busyGlassPane = new BusyGlassPane();
				InputVerifier inputVerifier = new InputVerifier() {

					public boolean verify(JComponent jComponent) {
						return !jComponent.isVisible();
					}
				};
				busyGlassPane.setInputVerifier(inputVerifier);
				Component component2 = rootPaneContainer.getGlassPane();
				rootPaneContainer.getRootPane().putClientProperty(this,
						component2);
				rootPaneContainer.setGlassPane(busyGlassPane);
				busyGlassPane.setVisible(true);
				busyGlassPane.revalidate();
			} else {
				JMenuBar jMenuBar = rootPaneContainer.getRootPane()
						.getJMenuBar();
				if (jMenuBar != null) {
					boolean bl2 = (Boolean) jMenuBar.getClientProperty(this);
					jMenuBar.putClientProperty(this, null);
					jMenuBar.setEnabled(bl2);
				}
				Component component3 = (Component) rootPaneContainer
						.getRootPane().getClientProperty(this);
				rootPaneContainer.getRootPane().putClientProperty(this, null);
				if (!component3.isVisible()) {
					rootPaneContainer.getGlassPane().setVisible(false);
				}
				rootPaneContainer.setGlassPane(component3);
			}
		}
	}

	private int blockingDialogDelay() {
		ResourceMap resourceMap;
		Integer n = null;
		String string = "BlockingDialogTimer.delay";
		ApplicationAction applicationAction = this.getAction();
		if (applicationAction != null) {
			resourceMap = applicationAction.getResourceMap();
			String string2 = applicationAction.getName();
			n = resourceMap.getInteger(string2 + "." + string);
		}
		resourceMap = this.getTask().getResourceMap();
		if (n == null && resourceMap != null) {
			n = resourceMap.getInteger(string);
		}
		return n == null ? 0 : n;
	}

	private void showBlockingDialog(boolean bl) {
		if (bl) {
			Object object;
			if (this.modalDialog != null) {
				object = String.format("unexpected InputBlocker state [%s] %s",
						bl, this);
				logger.warning((String) object);
				this.modalDialog.dispose();
			}
			this.modalDialog = this.createBlockingDialog();
			object = new ActionListener() {

				public void actionPerformed(ActionEvent actionEvent) {
					if (DefaultInputBlocker.this.modalDialog != null) {
						DefaultInputBlocker.this.modalDialog.setVisible(true);
					}
				}
			};
			Timer timer = new Timer(this.blockingDialogDelay(),
					(ActionListener) object);
			timer.setRepeats(false);
			timer.start();
		} else if (this.modalDialog != null) {
			this.modalDialog.dispose();
			this.modalDialog = null;
		} else {
			String string = String.format(
					"unexpected InputBlocker state [%s] %s", bl, this);
			logger.warning(string);
		}
	}

	@Override
	protected void block() {
		switch (this.getScope()) {
		case ACTION: {
			this.setActionTargetBlocked(true);
			break;
		}
		case COMPONENT: {
			this.setComponentTargetBlocked(true);
			break;
		}
		case WINDOW:
		case APPLICATION: {
			this.showBusyGlassPane(true);
			this.showBlockingDialog(true);
		}
		}
	}

	@Override
	protected void unblock() {
		switch (this.getScope()) {
		case ACTION: {
			this.setActionTargetBlocked(false);
			break;
		}
		case COMPONENT: {
			this.setComponentTargetBlocked(false);
			break;
		}
		case WINDOW:
		case APPLICATION: {
			this.showBusyGlassPane(false);
			this.showBlockingDialog(false);
		}
		}
	}

	private static class BusyGlassPane extends JPanel {
		BusyGlassPane() {
			super(null, false);
			this.setVisible(false);
			this.setOpaque(false);
			this.setCursor(Cursor.getPredefinedCursor(3));
			MouseInputAdapter mouseInputAdapter = new MouseInputAdapter() {
			};
			this.addMouseMotionListener(mouseInputAdapter);
			this.addMouseListener(mouseInputAdapter);
		}

	}

}
