package cyk.controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JTable;

import cyk.model.interfaces.ICYKModel;

@SuppressWarnings("serial")
public class ActionRemoveRule extends AbstractAction {
	private final ICYKModel model;
	private final JTable table;

	public ActionRemoveRule(final ICYKModel model, final JTable table) {
		super("Entfernen");
		this.model = model;
		this.table = table;

		putValue(AbstractAction.SHORT_DESCRIPTION,
				"Die ausgew�hlte Regel entfernen");
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		if (table.getSelectedRow() > -1) {
			ArrayList<Object> toRemove = new ArrayList<Object>();

			for (int i : table.getSelectedRows()) {
				toRemove.add(model.getRule(i));
				System.out.println(model.getRule(i));
			}

			model.removeRules(toRemove);
		}
	}
}
