package cyk.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class CNFTextField extends JTextField implements KeyListener {
	private String text = " ->", originalText = "S->S";

	public CNFTextField() {
		setText(text);
		addKeyListener(this);

	}

	@Override
	public void setText(String t) {
		this.text = t;
		this.originalText = t;
		super.setText(t);
	}

	public String getOriginalText() {
		return originalText;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public void keyPressed(KeyEvent ev) {
		final char c = ev.getKeyChar();
		int pos = getSelectionStart();

		setSelectionStart(pos);
		setSelectionEnd(pos);

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
		} else if ((ev.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				|| (ev.getKeyCode() == KeyEvent.VK_LEFT)) {
			if (pos == 1) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						setCaretPosition(0);
					}
				});
			} else if (pos > 1 && pos < 4) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						setCaretPosition(0);
					}
				});
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
			} else {
				return;
			}
		} else if (ev.getKeyCode() == KeyEvent.VK_ENTER) {
			return;
		}

		// LETTERS

		switch (pos) {
		case 0:
			if (Character.isLetter(c) && Character.isUpperCase(c)) {
				setText(c + text.substring(1));

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						setCaretPosition(3);
					}
				});
				return;
			}
		case 1:
		case 2:
			if (Character.isLetterOrDigit(c)) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						setCaretPosition(3);
					}
				});
				return;
			}
		case 3:
			if (Character.isLetterOrDigit(c)) {
				setText(text.substring(0, 3) + c);
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						setCaretPosition(4);
					}
				});
				return;
			}
		case 4:
			if (Character.isLetter(c)) {
				if (Character.isUpperCase(c) && Character.isUpperCase(text.charAt(3)))
					setText(text.substring(0, 4) + c);
				return;
			}
		}

		ev.consume();
	}

	@Override
	public void keyReleased(KeyEvent ev) {
		if (Character.isLetterOrDigit(ev.getKeyChar())
				|| ev.getKeyCode() == KeyEvent.VK_BACK_SPACE
				|| ev.getKeyCode() == KeyEvent.VK_DELETE) {
			ev.consume();
		}
	}

	@Override
	public void keyTyped(KeyEvent ev) {
		text = super.getText();
		if (Character.isLetterOrDigit(ev.getKeyChar())
				|| ev.getKeyCode() == KeyEvent.VK_BACK_SPACE
				|| ev.getKeyCode() == KeyEvent.VK_DELETE) {
			ev.consume();
		}
	}
}
