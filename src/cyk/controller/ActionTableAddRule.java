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
		model.addRule();
	}
}
