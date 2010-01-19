package cyk.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;

/**
 * Action zum Schließen eines Dialogs.
 * 
 * @author Stephan
 */
@SuppressWarnings("serial")
public class ActionCloseDialog extends AbstractAction {
	private final JDialog dialog;

	public ActionCloseDialog(JDialog dialog) {
		super("Schließen");
		putValue(AbstractAction.SHORT_DESCRIPTION, "Dieses Fenster schließen");

		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dialog.dispose();
	}
}
