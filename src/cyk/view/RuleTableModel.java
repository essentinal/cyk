package cyk.view;

import javax.swing.table.AbstractTableModel;

import cyk.model.Rule;
import cyk.model.interfaces.CYKModelListener;
import cyk.model.interfaces.ICYKModel;

@SuppressWarnings("serial")
public class RuleTableModel extends AbstractTableModel implements
		CYKModelListener {
	private final ICYKModel model;

	public RuleTableModel(ICYKModel model) {
		this.model = model;
		model.addCYKModelListener(this);
	}

	@Override
	public Class<?> getColumnClass(int col) {
		return Rule.class;
	}

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public int getRowCount() {
		return model.getSize();
	}

	@Override
	public String getColumnName(int column) {
		return "Regeln";
	}

	@Override
	public Rule getValueAt(int row, int column) {
		return model.getRule(row);
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		if (value instanceof Rule) {
			model.setRuleAt((Rule) value, rowIndex);
		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return true;
	}

	@Override
	public void modelChanged() {
		fireTableDataChanged();
	}

	@Override
	public void ruleAdded() {
	}
}
