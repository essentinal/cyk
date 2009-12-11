package cyk.view;

import javax.swing.table.AbstractTableModel;

import cyk.model.interfaces.ICYKModel;

@SuppressWarnings("serial")
public class RuleTableModel extends AbstractTableModel {
	private ICYKModel model;

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

	public void add(String s) {
		model.add(null);
	}

	@Override
	public String getColumnName(int column) {
		return "Regeln";
	}

	@Override
	public String getValueAt(int row, int column) {
		return model.getRule(row).toString();
	}

	public void remove(int[] indexes) {
		for (int i : indexes) {
			model.removeRule(i);
		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public void fireTableDataChanged() {
		// Collections.sort(list, new Comparator<String>() {
		// @Override
		// public int compare(String o1, String o2) {
		// return o1.compareTo(o2);
		// }
		// });
		super.fireTableDataChanged();
	}
}
