package cyk.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;

@SuppressWarnings("serial")
public class ActionCancelDialog extends AbstractAction {
	private final JDialog dialog;

	public ActionCancelDialog(JDialog dialog) {
		super("Abbrechen");
		putValue(AbstractAction.SHORT_DESCRIPTION, "Abbrechen");

		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dialog.dispose();
	}
}
