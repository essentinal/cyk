package cyk.view;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import cyk.model.Rule;
import cyk.model.exceptions.RuleException;

@SuppressWarnings("serial")
public class CNFCellEditor extends AbstractCellEditor implements
		TableCellEditor {
	private CNFTextField textField;

	public CNFCellEditor() {
		textField = new CNFTextField();
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		if (value instanceof Rule) {
			textField.setText(value.toString());
			textField.setCaretPosition(0);
			textField.requestFocusInWindow();
		}
		return textField;
	}

	@Override
	public boolean isCellEditable(EventObject e) {
		if (e instanceof MouseEvent) {
			return ((MouseEvent) e).getClickCount() >= 2;
		}
		return true;
	}

	public Object getCellEditorValue() {
		System.out.println("Editor value: " + textField.getText());
		String text = textField.getText();
		Rule rule;
		try {
			rule = new Rule(text);
		} catch (RuleException e) {
			text = textField.getOriginalText();
			try {
				rule = new Rule(text);
			} catch (RuleException e1) {
				e1.printStackTrace();
				return null;
			}
		}
		return rule;
	}

	public void requestFocus() {
		textField.requestFocusInWindow();
	}
}
