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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import cyk.controller.ActionCancelDialog;

@SuppressWarnings("serial")
public class DialogRandomWord extends JDialog {
	private static int lastLength = 5;

	private JSpinner numberSpinner;
	private JTextField textField;

	public DialogRandomWord(JTextField textField) {
		super((Frame) null, "Zufallswort erzeugen", true);
		this.textField = textField;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);

		setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.WEST;

		add(new JLabel("Länge des Zufallsworts"), constraints);

		constraints.gridx++;
		constraints.anchor = GridBagConstraints.EAST;

		numberSpinner = new JSpinner(new SpinnerNumberModel(lastLength, 0, 20, 1));
		add(numberSpinner, constraints);

		constraints.gridx = 0;
		constraints.gridy++;
		constraints.anchor = GridBagConstraints.WEST;

		add(new JButton(new ActionOK()), constraints);

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

			char[] word = new char[length];

			for (int i = 0; i < length; i++) {
				word[i] = (char) (Math.random() * 24 + 97);

				System.out.println(word[i]);
			}

			textField.setText(new String(word));
			dispose();
		}
	}
}
