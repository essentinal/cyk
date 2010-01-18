package cyk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import cyk.view.CYKMainFrame;

@SuppressWarnings("serial")
public class ActionCloseFrame extends AbstractAction {
	private final CYKMainFrame frame;

	public ActionCloseFrame(CYKMainFrame frame) {
		super("Grammatik schließen");
		putValue(SHORT_DESCRIPTION, "Das Grammatikfenster schließen");
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C,
				KeyEvent.CTRL_DOWN_MASK));
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.closeActiveFrame();
	}
}