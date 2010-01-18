package cyk.controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JTable;

import cyk.CYK;
import cyk.model.interfaces.ICYKModel;

@SuppressWarnings("serial")
public class ActionTableRemoveRule extends AbstractAction {
	private final ICYKModel model;
	private final JTable table;

	public ActionTableRemoveRule(final ICYKModel model, final JTable table) {
		super("Entfernen");
		this.model = model;
		this.table = table;

		putValue(AbstractAction.SHORT_DESCRIPTION,
				"Die ausgewählte Regel entfernen");
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		if (table.getSelectedRow() > -1) {
			ArrayList<Object> toRemove = new ArrayList<Object>();

			for (int i : table.getSelectedRows()) {
				toRemove.add(model.getRule(i));
				if (CYK.DEBUG) {
					System.out.println(model.getRule(i));
				}
			}

			model.removeRules(toRemove);
		}
	}
}
