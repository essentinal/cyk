package cyk.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import cyk.model.exceptions.GrammarIncompleteException;
import cyk.model.exceptions.GrammarIsNotInCnfException;
import cyk.model.exceptions.RuleNotNeededException;
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
	public void actionPerformed(ActionEvent ev) {
		try {
			model.checkGrammar();
			JOptionPane.showMessageDialog(null, "Diese ist eine gültige Grammatik in Chomsky-Normalform.", 
					"Grammatik überprüft", JOptionPane.INFORMATION_MESSAGE );		
		} catch (RuleNotNeededException e) {
			JOptionPane.showMessageDialog(null, "Diese ist eine gültige Grammatik in Chomsky-Normalform, " +
					"aber enthält überflüssige Regeln.", "Grammatik überprüft",
					JOptionPane.INFORMATION_MESSAGE );
		} catch (GrammarIsNotInCnfException e) {
			JOptionPane.showMessageDialog(null, "Diese ist keine gültige Grammatik in Chomsky-Normalform. " +
					e.getMessage(), "Grammatik überprüft", JOptionPane.ERROR_MESSAGE );
		} catch (GrammarIncompleteException e) {
			JOptionPane.showMessageDialog(null, "Diese ist keine gültige Grammatik in Chomsky-Normalform. " +
					e.getMessage(), "Grammatik überprüft", JOptionPane.ERROR_MESSAGE );
		}
	}
}
