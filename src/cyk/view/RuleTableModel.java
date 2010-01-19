package cyk.view;

import javax.swing.table.AbstractTableModel;

import cyk.model.Rule;
import cyk.model.interfaces.CYKModelListener;
import cyk.model.interfaces.ICYKModel;

/**
 * TableModel für CNF-Regeln.
 * 
 * @author Stephan
 */
@SuppressWarnings("serial")
public class RuleTableModel extends AbstractTableModel implements
		CYKModelListener {
	private final ICYKModel model;

	/**
	 * Erzeugt ein neues RuleTableModel mit dem angebenen CYKModel.
	 * 
	 * @param model CYKModel
	 */
	public RuleTableModel(ICYKModel model) {
		this.model = model;
		model.addCYKModelListener(this);
	}

	/* 
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	@Override
	public Class<?> getColumnClass(int col) {
		// Klasse ist immer Rule
		return Rule.class;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		// immer 1 Spalte
		return 1;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		// entspricht der Anzahl der Regeln
		return model.getSize();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int column) {
		// Überschrift der Spalte
		return "Regeln";
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Rule getValueAt(int row, int column) {
		// gibt die Regel der aktuellen Zeile zurück
		return model.getRule(row);
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object, int, int)
	 */
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		if (value instanceof Rule) {
			// setzt die Regel der aktuellen Zeile
			model.setRuleAt((Rule) value, rowIndex);
		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		// alle Zellen sind editierbar
		return true;
	}

	/* (non-Javadoc)
	 * @see cyk.model.interfaces.CYKModelListener#modelChanged()
	 */
	@Override
	public void modelChanged() {
		fireTableDataChanged();
	}

	/* (non-Javadoc)
	 * @see cyk.model.interfaces.CYKModelListener#ruleAdded()
	 */
	@Override
	public void ruleAdded() {
		// nicht benötigt
	}
}
