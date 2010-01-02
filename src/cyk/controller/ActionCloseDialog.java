package cyk.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;

@SuppressWarnings("serial")
public class ActionCloseDialog extends AbstractAction {
	private final JDialog dialog;

	public ActionCloseDialog(JDialog dialog) {
		super("Schlie�en");
		putValue(AbstractAction.SHORT_DESCRIPTION, "Dieses Fenster schlie�en");

		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dialog.dispose();
	}
}
