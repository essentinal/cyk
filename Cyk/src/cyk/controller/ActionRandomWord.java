package cyk.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTextField;

import cyk.model.interfaces.ICYKModel;
import cyk.view.DialogRandomWord;

@SuppressWarnings("serial")
public class ActionRandomWord extends AbstractAction {
	private final JTextField inputWordField;
	private final ICYKModel model;

	public ActionRandomWord(JTextField inputWordField, ICYKModel model) {
		super("Zufallswort...");
		this.inputWordField = inputWordField;
		this.model = model;

		putValue(AbstractAction.SHORT_DESCRIPTION, "Ein Wort zufällig erzeugen");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new DialogRandomWord(inputWordField, model);
	}
}