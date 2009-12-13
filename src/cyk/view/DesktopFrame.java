package cyk.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import cyk.controller.ActionAddRule;
import cyk.controller.ActionCheckGrammar;
import cyk.controller.ActionRandomWord;
import cyk.controller.ActionRemoveRule;
import cyk.model.CYKModel;
import cyk.model.interfaces.ICYKModel;

@SuppressWarnings("serial")
public class DesktopFrame extends JInternalFrame {

	// private JDesktopPane desktop;
	private RuleTableModel tableModel;
	private JTextField inputWordField;
	private JTable table;

	private ICYKModel model;

	public DesktopFrame(String title, JDesktopPane desktop) {
		super(title, true, true, true, true);
		// this.desktop = desktop;
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

		setLayout(new GridBagLayout());

		model = new CYKModel();

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

	public void load() {

	}

	public void save() {

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

		panel.add(new JButton(new ActionCheckGrammar(model)), constraints);

		constraints.gridy++;

		panel.add(new JLabel("Regeln"), constraints);
		constraints.gridy++;

		panel.add(new JButton(new ActionAddRule(model)), constraints);

		constraints.gridx++;
		constraints.anchor = GridBagConstraints.EAST;

		tableModel = new RuleTableModel(model);
		table = new JTable(tableModel);
		table.getTableHeader().setPreferredSize(new Dimension(0, 0));

		panel.add(new JButton(new ActionRemoveRule(model, table)), constraints);

		constraints.gridx = 0;
		constraints.gridy++;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 1;

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

		panel.add(new JButton(new ActionRandomWord(inputWordField)), constraints);

		return panel;
	}

	private void updateTable() {
		tableModel.fireTableDataChanged();
	}
}
