package cyk.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class CNFTextField extends JTextField implements KeyListener {
	private String text = " ->";

	public CNFTextField() {
		setText(text);
		addKeyListener(this);
	}

	@Override
	public void setText(String t) {
		this.text = t;
		super.setText(t);
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public void keyPressed(KeyEvent ev) {
		char c = ev.getKeyChar();
		int pos = getSelectionStart();

		System.out.println("pressed: " + c);
		System.out.println(pos);

		// KEYS ALLOWED

		if ((ev.getKeyCode() == KeyEvent.VK_TAB)) {
			if (pos == 0) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						setCaretPosition(3);
					}
				});
			} else {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						setCaretPosition(0);
					}
				});
			}
			ev.consume();
		} else if ((ev.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				|| (ev.getKeyCode() == KeyEvent.VK_LEFT)) {
			if (pos == 1) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						setCaretPosition(0);
					}
				});
				ev.consume();
			} else if (pos > 1 && pos <= 3) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						setCaretPosition(0);
					}
				});
				ev.consume();
			} else {
				return;
			}

		} else if ((ev.getKeyCode() == KeyEvent.VK_DELETE)
				|| (ev.getKeyCode() == KeyEvent.VK_RIGHT)) {
			if (pos == 0) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						setCaretPosition(3);
					}
				});
				ev.consume();
			}

		} else if (ev.getKeyCode() == KeyEvent.VK_ENTER) {
			return;
		}

		// LETTERS

		switch (pos) {
		case 0:
			if (Character.isLetter(c) && Character.isUpperCase(c)) {
				text = c + text.substring(1);
				setText(text);

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						setCaretPosition(3);
					}
				});
			}

			// break;
			return;
		case 1:
		case 2:
			if (Character.isLetterOrDigit(c)) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						setCaretPosition(3);
					}
				});
			}
			// break;
			return;
		case 3:
			if (Character.isLetter(c)) {
				text = text.substring(0, 3) + c;
				setText(text);

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						setCaretPosition(4);
					}
				});
			}
			// break;
			return;
		case 4:
			if (Character.isLetter(c)) {
				if (Character.isUpperCase(c) && Character.isUpperCase(text.charAt(3)))
					text = text.substring(0, 4) + c;
				setText(text);
			}
			// break;
			return;
		}

		// if (!Character.isISOControl(ev.getKeyChar()) || ev.isShiftDown()) {
		ev.consume();
		// }
	}

	@Override
	public void keyReleased(KeyEvent ev) {
		if (Character.isLetterOrDigit(ev.getKeyChar())) {
			ev.consume();
		}
	}

	@Override
	public void keyTyped(KeyEvent ev) {
		if (Character.isLetterOrDigit(ev.getKeyChar())) {
			ev.consume();
		}
	}
}
