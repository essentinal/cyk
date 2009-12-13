package cyk.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import cyk.model.Rule;
import cyk.model.interfaces.ICYKModel;

@SuppressWarnings("serial")
public class ActionAddRule extends AbstractAction {
	private final ICYKModel model;

	public ActionAddRule(ICYKModel model) {
		super("Hinzufügen...");
		this.model = model;

		putValue(AbstractAction.SHORT_DESCRIPTION, "Eine neue Regel hinzufügen");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = JOptionPane.showInputDialog(null,
				"Neue Regel im Format A->a oder A->A eingeben");

		if (s != null) {
			if (s.matches("[A-Z]->.")) {
				// terminalzeichen
				model.addRule(new Rule(s));
			} else if (s.matches("[A-Z]->[A-Z]*")) {
				// nichtterminalzeichen
				model.addRule(new Rule(s));
			} else {
				JOptionPane.showMessageDialog(null,
						"Dies entspricht nicht den Regeln A->a oder A->A.");
			}

		}
	}
}
