package cyk.view;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import cyk.controller.ActionCancelDialog;
import cyk.model.exceptions.GrammarIncompleteException;
import cyk.model.exceptions.GrammarNoDeriveException;
import cyk.model.exceptions.GrammarNoStartruleException;
import cyk.model.interfaces.ICYKModel;

@SuppressWarnings("serial")
public class DialogRandomWord extends JDialog {
	private static int lastLength = 5;

	private JSpinner numberSpinner;
	private final JTextField textField;
	private final ICYKModel model;

	public DialogRandomWord(JTextField textField, ICYKModel model) {
		super((Frame) null, "Zufallswort erzeugen", true);
		this.textField = textField;
		this.model = model;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);

		setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.WEST;

		add(new JLabel("L�nge des Zufallsworts"), constraints);

		constraints.gridx++;
		constraints.anchor = GridBagConstraints.EAST;

		numberSpinner = new JSpinner(new SpinnerNumberModel(lastLength, 0, 20, 1));
		add(numberSpinner, constraints);

		constraints.gridx = 0;
		constraints.gridy++;
		constraints.anchor = GridBagConstraints.WEST;

		JButton b = new JButton(new ActionOK());

		add(b, constraints);
		getRootPane().setDefaultButton(b);

		constraints.gridx++;
		constraints.anchor = GridBagConstraints.EAST;

		add(new JButton(new ActionCancelDialog(this)), constraints);

		pack();
		setLocationRelativeTo(null);

		setVisible(true);

	}

	private class ActionOK extends AbstractAction {
		public ActionOK() {
			super("OK");
			putValue(AbstractAction.SHORT_DESCRIPTION, "Zufallswort jetzt erzeugen");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int length = (Integer) numberSpinner.getValue();
			lastLength = length;

			try {
				String word = model.getRandomWord(length);

				textField.setText(new String(word));
			} catch (GrammarIncompleteException ex) {
				textField.setText("");
				JOptionPane.showMessageDialog(null,
						"Fehler beim Erzeugen des Worts. Die Grammatik ist unvollst�ndig.",
						"Grammatikfehler", JOptionPane.ERROR_MESSAGE);
			} catch (GrammarNoDeriveException ex) {
				textField.setText("");
				JOptionPane
						.showMessageDialog(
								null,
								"Fehler beim Erzeugen des Worts. Mit dieser Grammatik kann kein Wort der L�nge "
										+ length + " oder weniger erzeugt werden.",
								"Grammatikfehler", JOptionPane.ERROR_MESSAGE);
			} catch (GrammarNoStartruleException ex) {
				textField.setText("");
				JOptionPane
						.showMessageDialog(
								null,
								"Fehler beim Erzeugen des Worts. Diese Grammatik hat keine Startregel.",
								"Grammatikfehler", JOptionPane.ERROR_MESSAGE);
			}
			dispose();
		}
	}
}