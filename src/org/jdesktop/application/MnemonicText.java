/*
 * Decompiled with CFR 0_92.
 */
package org.jdesktop.application;

import java.text.StringCharacterIterator;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JLabel;

class MnemonicText {
	private static final String DISPLAYED_MNEMONIC_INDEX_KEY = "SwingDisplayedMnemonicIndexKey";

	private MnemonicText() {
	}

	public static void configure(Object object, String string) {
		String string2 = string;
		int n = -1;
		int n2 = 0;
		int n3 = MnemonicText.mnemonicMarkerIndex(string, '&');
		if (n3 == -1) {
			n3 = MnemonicText.mnemonicMarkerIndex(string, '_');
		}
		if (n3 != -1) {
			string2 = string2.substring(0, n3) + string2.substring(n3 + 1);
			n = n3;
			StringCharacterIterator stringCharacterIterator = new StringCharacterIterator(
					string, n3);
			n2 = MnemonicText.mnemonicKey(stringCharacterIterator.next());
		}
		if (object instanceof Action) {
			MnemonicText.configureAction((Action) object, string2, n2, n);
		} else if (object instanceof AbstractButton) {
			MnemonicText.configureButton((AbstractButton) object, string2, n2,
					n);
		} else if (object instanceof JLabel) {
			MnemonicText.configureLabel((JLabel) object, string2, n2, n);
		} else {
			throw new IllegalArgumentException("unrecognized target type "
					+ object);
		}
	}

	private static int mnemonicMarkerIndex(String string, char c) {
		if (string == null || string.length() < 2) {
			return -1;
		}
		StringCharacterIterator stringCharacterIterator = new StringCharacterIterator(
				string);
		int n = 0;
		while (n != -1) {
			if ((n = string.indexOf(c, n)) != -1) {
				stringCharacterIterator.setIndex(n);
				char c2 = stringCharacterIterator.previous();
				stringCharacterIterator.setIndex(n);
				char c3 = stringCharacterIterator.next();
				boolean bl = c2 == '\'' && c3 == '\'';
				boolean bl2 = Character.isWhitespace(c3);
				if (!(bl || bl2 || c3 == '\uffff')) {
					return n;
				}
			}
			if (n == -1)
				continue;
			++n;
		}
		return -1;
	}

	private static int mnemonicKey(char n) {
		int n2 = n;
		if (n2 >= 97 && n2 <= 122) {
			n2 -= 32;
		}
		return n2;
	}

	private static void configureAction(Action action, String string, int n,
			int n2) {
		action.putValue("Name", string);
		if (n != 0) {
			action.putValue("MnemonicKey", n);
		}
		if (n2 != -1) {
			action.putValue("SwingDisplayedMnemonicIndexKey", n2);
		}
	}

	private static void configureButton(AbstractButton abstractButton,
			String string, int n, int n2) {
		abstractButton.setText(string);
		if (n != 0) {
			abstractButton.setMnemonic(n);
		}
		if (n2 != -1) {
			abstractButton.setDisplayedMnemonicIndex(n2);
		}
	}

	private static void configureLabel(JLabel jLabel, String string, int n,
			int n2) {
		jLabel.setText(string);
		if (n != 0) {
			jLabel.setDisplayedMnemonic(n);
		}
		if (n2 != -1) {
			jLabel.setDisplayedMnemonicIndex(n2);
		}
	}
}
