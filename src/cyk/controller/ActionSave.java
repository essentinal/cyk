package cyk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JDesktopPane;
import javax.swing.KeyStroke;

import cyk.view.DesktopFrame;

/**
 * Action zum Speichern einer Grammatik.
 * 
 * @author Stephan
 */
@SuppressWarnings("serial")
public class ActionSave extends AbstractAction {
	private final JDesktopPane desktop;

	public ActionSave(JDesktopPane desktop) {
		super("Speichern unter...");
		putValue(SHORT_DESCRIPTION, "Grammatik in Datei speichern");
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S,
				KeyEvent.CTRL_DOWN_MASK));
		this.desktop = desktop;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DesktopFrame df = (DesktopFrame) desktop.getSelectedFrame();
		if (df != null) {
			df.save();
		}
	}
}