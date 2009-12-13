package cyk.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTextField;

import cyk.view.DialogRandomWord;

@SuppressWarnings("serial")
public class ActionRandomWord extends AbstractAction {
	private final JTextField inputWordField;

	public ActionRandomWord(JTextField inputWordField) {
		super("Zufallswort...");
		this.inputWordField = inputWordField;

		putValue(AbstractAction.SHORT_DESCRIPTION, "Ein Wort zufällig erzeugen");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new DialogRandomWord(inputWordField);
	}
}