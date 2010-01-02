package cyk.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import cyk.model.interfaces.ICYKModel;

@SuppressWarnings("serial")
public class ActionCheckGrammar extends AbstractAction {
	private final ICYKModel model;

	public ActionCheckGrammar(ICYKModel model) {
		super("Grammatik �berpr�fen");
		this.model = model;

		putValue(AbstractAction.SHORT_DESCRIPTION,
				"�berpr�fen, ob die Grammatik in Chomsky-Normalform ist");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean b = model.checkGrammar();

		JOptionPane.showMessageDialog(null, "Diese ist " + (b ? "eine " : "keine ")
				+ "g�ltige Grammatik in Chomsky-Normalform.", "Grammatik �berpr�ft",
				b ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);

		System.out.println("check grammar");
	}
}
