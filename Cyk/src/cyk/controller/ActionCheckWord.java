package cyk.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cyk.model.interfaces.ICYKModel;

@SuppressWarnings("serial")
public class ActionCheckWord extends AbstractAction {
	private final ICYKModel model;
	private final JTextField textField;

	public ActionCheckWord(ICYKModel model, JTextField textField) {
		super("Wort überprüfen");
		this.model = model;
		this.textField = textField;

		putValue(AbstractAction.SHORT_DESCRIPTION,
				"Überprüfen, ob das Wort in der Sprache ist");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String word = textField.getText();
		boolean b = model.parseWord(word);

		JOptionPane.showMessageDialog(null, "Das Wort \"" + word + "\" wurde "
				+ (b ? "" : "nicht ") + "vom CYK Algorithmus erkannt.",
				"Erkennung abgeschlossen", b ? JOptionPane.INFORMATION_MESSAGE
						: JOptionPane.ERROR_MESSAGE);
	}
}
