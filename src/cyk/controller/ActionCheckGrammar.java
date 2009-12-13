package cyk.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import cyk.model.interfaces.ICYKModel;

@SuppressWarnings("serial")
public class ActionCheckGrammar extends AbstractAction {
	private final ICYKModel model;

	public ActionCheckGrammar(ICYKModel model) {
		super("Grammatik überprüfen");
		this.model = model;

		putValue(AbstractAction.SHORT_DESCRIPTION,
				"Überprüfen, ob die Grammatik in Chomsky-Normalform ist");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.checkGrammar();
		System.out.println("check grammar");
	}
}
