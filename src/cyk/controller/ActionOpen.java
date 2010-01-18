package cyk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import cyk.view.CYKMainFrame;

@SuppressWarnings("serial")
public class ActionOpen extends AbstractAction {
	private final CYKMainFrame frame;

	public ActionOpen(CYKMainFrame frame) {
		super("Öffnen...");
		putValue(SHORT_DESCRIPTION, "Grammatikdatei öffnen");
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O,
				KeyEvent.CTRL_DOWN_MASK));
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.load();
	}
}
