package cyk.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import cyk.model.interfaces.ICYKModel;

@SuppressWarnings("serial")
public class ActionTableAddRule extends AbstractAction {
	private final ICYKModel model;

	public ActionTableAddRule(ICYKModel model) {
		super("Hinzuf�gen");
		this.model = model;

		putValue(AbstractAction.SHORT_DESCRIPTION, "Eine neue Regel hinzuf�gen");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// String s = JOptionPane.showInputDialog(null,
		// "Neue Regel im Format A->a oder A->A eingeben");
		//
		// if (s != null) {
		// if (s.matches("[A-Z]->.")) {
		// // terminalzeichen
		// model.addRule(new Rule(s));
		// } else if (s.matches("[A-Z]->[A-Z]*")) {
		// // nichtterminalzeichen
		// model.addRule(new Rule(s));
		// } else {
		// JOptionPane.showMessageDialog(null,
		// "Dies entspricht nicht den Regeln A->a oder A->A.");
		// }
		//
		// }
		model.addRule();
	}
}