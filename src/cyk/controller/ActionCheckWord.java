package cyk.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cyk.model.interfaces.ICYKModel;
import cyk.view.DialogDetail;
import cyk.view.DialogDetail.IconType;

@SuppressWarnings("serial")
public class ActionCheckWord extends AbstractAction {
	private final ICYKModel model;
	private final JTextField textField;

	public ActionCheckWord(ICYKModel model, JTextField textField) {
		super("CYK-Algorithmus starten");
		this.model = model;
		this.textField = textField;

		putValue(AbstractAction.SHORT_DESCRIPTION,
				"Überprüfen, ob das Wort in der Sprache ist");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String word = textField.getText();
		boolean b = model.parseWord(word);

		String text = "Das Wort \"" + word + "\" wurde " + (b ? "" : "nicht ")
				+ "vom CYK Algorithmus erkannt.";

		DialogDetail dd = new DialogDetail();

		try {
			String[][] cykTable = model.getTable();
			StringBuilder sb = new StringBuilder();

			sb.append("Wort:\n");
			sb.append("    ");
			for (int j = 0; j < word.length(); j++) {
				sb.append(String.format("%-3c ", word.charAt(j)));
			}
			sb.append("\n\n");

			sb.append("CYK-Tabelle:\n");
			sb.append("    ");
			for (int j = 0; j < cykTable.length; j++) {
				sb.append(String.format("%-3d ", j));
			}
			sb.append('\n');

			for (int i = 0; i < cykTable.length; i++) {
				sb.append(String.format("%-2d: ", i));
				for (int j = 0; j < cykTable.length - i; j++) {
					String tmp = !cykTable[i][j].isEmpty() ? cykTable[i][j] : "X";
					sb.append(String.format("%-3s ", tmp));
				}
				sb.append('\n');
			}
			dd.setTitle("Erkennung abgeschlossen");
			dd.setText(text);
			dd.setDetail(sb.toString());
			dd.setIcon(b ? IconType.InformationIcon : IconType.ErrorIcon);
			dd.setVisible(true);

		} catch (Exception ex) {
			// wenn ein fehler auftritt, z.b. cyktable nicht verfügbar, messagedialog
			// anzeigen
			JOptionPane.showMessageDialog(null, text, "Erkennung abgeschlossen",
					b ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
		}
	}
}
