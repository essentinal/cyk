package cyk.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Textfeld, welches nur CNF akzeptiert. Wird benutzt vom
 * <code>cyk.view.CNFCellEditor</code>.
 * 
 * @author Stephan
 */
@SuppressWarnings("serial")
public class CNFTextField extends JTextField implements KeyListener {
	private String text = " ->", originalText = "S->AA";

	/**
	 * Erzeugt ein neues CNFTextField mit einer leeren Regel.
	 */
	public CNFTextField() {
		setText(text);
		addKeyListener(this);
	}

	/**
	 * Setzt den Text und speichert diesen noch zwischen.
	 * 
	 * @see javax.swing.text.JTextComponent#setText(java.lang.String)
	 */
	@Override
	public void setText(String t) {
		this.text = t;
		this.originalText = t;
		super.setText(t);
	}

	/**
	 * Getter für OriginalText.
	 * 
	 * @return OriginalText
	 */
	public String getOriginalText() {
		return originalText;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.text.JTextComponent#getText()
	 */
	@Override
	public String getText() {
		return text;
	}

	/**
	 * Callback-Methode des Keylisteners. Wertet Tastatureingaben aus.
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent ev) {
		final char c = ev.getKeyChar();
		int pos = getSelectionStart();

		setSelectionStart(pos);
		setSelectionEnd(pos);

		// erlaubte Tasten

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

		// Buchstaben

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

		// wenn bis hier nicht returned wurde, wird der Event konsumiert, also nicht
		// weitergereicht
		ev.consume();
	}

	/**
	 * Callback-Methode des Keylisteners. Wertet Tastatureingaben aus.
	 * 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent ev) {
		if (Character.isLetterOrDigit(ev.getKeyChar())
				|| ev.getKeyCode() == KeyEvent.VK_BACK_SPACE
				|| ev.getKeyCode() == KeyEvent.VK_DELETE) {
			ev.consume();
		}
	}

	/**
	 * Callback-Methode des Keylisteners. Wertet Tastatureingaben aus.
	 * 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
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
