package cyk.view;

import javax.swing.table.AbstractTableModel;

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
		return String.class;
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
	public String getValueAt(int row, int column) {
		return model.getRule(row).toString();
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public void fireTableDataChanged() {
		super.fireTableDataChanged();
	}

	@Override
	public void modelChanged() {
		fireTableDataChanged();
	}
}
