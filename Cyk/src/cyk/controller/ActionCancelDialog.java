package cyk.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;

/**
 * Action zum Abbrechen und Schließen eines Dialoges.
 * 
 * @author Stephan
 */
@SuppressWarnings("serial")
public class ActionCancelDialog extends AbstractAction {
	private final JDialog dialog;

	public ActionCancelDialog(JDialog dialog) {
		super("Abbrechen");
		putValue(AbstractAction.SHORT_DESCRIPTION,
				"Aktion abbrechen und Fenster schließen");

		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dialog.dispose();
	}
}
