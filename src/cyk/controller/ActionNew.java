package cyk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import cyk.view.CYKMainFrame;

/**
 * Action zum Erzeugen einer neuen Grammatik.
 * 
 * @author Stephan
 */
@SuppressWarnings("serial")
public class ActionNew extends AbstractAction {
	private final CYKMainFrame frame;

	public ActionNew(CYKMainFrame frame) {
		super("Neue Grammatik");
		putValue(SHORT_DESCRIPTION, "Neue Grammatik erstellen");
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N,
				KeyEvent.CTRL_DOWN_MASK));
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.newDesktopFrame();
	}
}
