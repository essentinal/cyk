package cyk.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class DesktopFrame extends JInternalFrame {

	// private JDesktopPane desktop;
	private RuleTableModel model;
	private JTextField inputWordField;
	private JTable table;

	public DesktopFrame(String title, JDesktopPane desktop) {
		super(title, true, true, true, true);
		// this.desktop = desktop;
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

		setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 0.4;
		constraints.weighty = 1.0;

		add(buildLeftPanel(), constraints);

		constraints.gridx++;
		constraints.weightx = 0.6;
		add(buildRightPanel(), constraints);

		int numframes = (desktop.getAllFrames().length) % 5;
		setLocation(numframes * 5, numframes * 5);
		setSize(desktop.getWidth() - 20, desktop.getHeight() - 20);
		setVisible(true);

	}

	private JPanel buildLeftPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Grammatik"));
		panel.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.WEST;

		panel.add(new JButton(new ActionCheckGrammar()), constraints);

		constraints.gridy++;

		panel.add(new JLabel("Regeln"), constraints);
		constraints.gridy++;

		panel.add(new JButton(new ActionAddParam()), constraints);

		constraints.gridx++;
		constraints.anchor = GridBagConstraints.EAST;

		panel.add(new JButton(new ActionRemoveParam()), constraints);

		constraints.gridx = 0;
		constraints.gridy++;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 1;

		model = new RuleTableModel();
		table = new JTable(model);
		table.getTableHeader().setPreferredSize(new Dimension(0, 0));

		panel.add(new JScrollPane(table), constraints);
		updateTable();

		return panel;

	}

	private JPanel buildRightPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Eingabewort"));
		panel.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weighty = 1.0;
		constraints.anchor = GridBagConstraints.NORTHWEST;

		panel.add(new JLabel("Eingabewort"), constraints);

		constraints.gridx++;
		constraints.anchor = GridBagConstraints.NORTHEAST;
		constraints.weightx = 1.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;

		inputWordField = new JTextField();
		panel.add(inputWordField, constraints);

		// constraints.gridx = 0;
		constraints.gridx++;
		constraints.weightx = 0.0;
		constraints.fill = GridBagConstraints.NONE;

		panel.add(new JButton(new ActionRandomWord()), constraints);

		return panel;
	}

	private void updateTable() {
		model.fireTableDataChanged();
	}

	private class ActionAddParam extends AbstractAction {
		public ActionAddParam() {
			super("Hinzufügen...");
			putValue(AbstractAction.SHORT_DESCRIPTION, "Eine neue Regel hinzufügen");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String s = JOptionPane.showInputDialog(null,
					"Neue Regel im Format A->a oder A->A eingeben");

			if (s != null) {
				if (s.matches("[A-Z]->.")) {
					// terminalzeichen
					model.add(s);
					updateTable();
				} else if (s.matches("[A-Z]->[A-Z]*")) {
					// nichtterminalzeichen
					model.add(s);
					updateTable();
				} else {
					JOptionPane.showMessageDialog(null,
							"Dies entspricht nicht den Regeln A->a oder A->A.");
				}

			}
		}
	}

	private class ActionRemoveParam extends AbstractAction {
		public ActionRemoveParam() {
			super("Entfernen");
			putValue(AbstractAction.SHORT_DESCRIPTION,
					"Die ausgewählte Regel entfernen");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (table.getSelectedRow() > -1) {
				model.remove(table.getSelectedRows());
				updateTable();
			}
		}
	}

	private class ActionCheckGrammar extends AbstractAction {
		public ActionCheckGrammar() {
			super("Grammatik überprüfen");
			putValue(AbstractAction.SHORT_DESCRIPTION,
					"Überprüfen, ob die Grammatik in Chomsky-Normalform ist");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("check grammar");
		}
	}

	private class ActionRandomWord extends AbstractAction {
		public ActionRandomWord() {
			super("Zufallswort...");
			putValue(AbstractAction.SHORT_DESCRIPTION, "Ein Wort zufällig erzeugen");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			new DialogRandomWord(inputWordField);
		}
	}
}
