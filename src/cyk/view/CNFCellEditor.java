package cyk.view;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import cyk.CYK;
import cyk.model.Rule;
import cyk.model.exceptions.RuleException;

/**
 * Erweiterter CellEditor zur Eingabe von Regeln in CNF.
 * 
 * @see javax.swing.AbstractCellEditor
 * @author Stephan
 */
@SuppressWarnings("serial")
public class CNFCellEditor extends AbstractCellEditor implements
		TableCellEditor {
	private CNFTextField textField;

	/**
	 * Erzeugt einen neuen CNFCellEditor
	 */
	public CNFCellEditor() {
		textField = new CNFTextField();
	}

	/*
	 * Initialisiert das Editor-Textfeld zur Bearbeitung einer Regel.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.table.TableCellEditor#getTableCellEditorComponent(javax.swing
	 * .JTable, java.lang.Object, boolean, int, int)
	 */
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		if (value instanceof Rule) {
			textField.setText(value.toString());
			textField.setCaretPosition(0);
			textField.requestFocusInWindow();
		}
		return textField;
	}

	/*
	 * Zellen sollen durch Doppelklick editierbar sein.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.AbstractCellEditor#isCellEditable(java.util.EventObject)
	 */
	@Override
	public boolean isCellEditable(EventObject e) {
		if (e instanceof MouseEvent) {
			return ((MouseEvent) e).getClickCount() >= 2;
		}
		return true;
	}

	/*
	 * Gibt die Regel zurück, die durch den Editor erzeugt wurde.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.CellEditor#getCellEditorValue()
	 */
	public Object getCellEditorValue() {
		if (CYK.DEBUG) {
			System.out.println("CNFCellEditor.getCellEditorValue() - "
					+ textField.getText());
		}
		String text = textField.getText();
		Rule rule;
		try {
			rule = new Rule(text);
		} catch (RuleException e) {
			text = textField.getOriginalText();
			try {
				rule = new Rule(text);
			} catch (RuleException e1) {
				return null;
			}
		}
		return rule;
	}

	/**
	 * Fordert den Focus im aktuellen Fenster an.
	 */
	public void requestFocus() {
		textField.requestFocusInWindow();
	}
}
