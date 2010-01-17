package cyk.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import cyk.controller.ActionCheckGrammar;
import cyk.controller.ActionCheckWord;
import cyk.controller.ActionRandomWord;
import cyk.controller.ActionTableAddRule;
import cyk.controller.ActionTableRemoveRule;
import cyk.model.CYKModel;
import cyk.model.Rule;
import cyk.model.interfaces.CYKModelListener;
import cyk.model.interfaces.ICYKModel;

@SuppressWarnings("serial")
public class DesktopFrame extends JInternalFrame implements CYKModelListener {
	private RuleTableModel tableModel;
	private JTextField inputWordField;
	private JTable table;
	private AbstractAction actionRemoveRule, actionCheckWord;

	private ICYKModel model;

	public DesktopFrame(String title, JDesktopPane desktop) {
		super(title, true, true, true, true);

		model = new CYKModel();

		init(desktop);
	}

	public DesktopFrame(String title, JDesktopPane desktop, ICYKModel model) {
		super(title, true, true, true, true);

		this.model = model;

		init(desktop);
	}

	private void init(JDesktopPane desktop) {
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
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTH;
		add(buildRightPanel(), constraints);

		int numframes = (desktop.getAllFrames().length) % 5;
		setLocation(numframes * 15, numframes * 15);
		setSize(700, 500);
		setVisible(true);
	}

	public void save() {
		boolean ok = false;

		JFileChooser jfc = new JFileChooser(CYKMainFrame.lastFileChooserDirectory);
		jfc
				.setFileFilter(new FileNameExtensionFilter("XML Grammatik-Datei", "xml"));

		while (!ok) {
			int n = jfc.showSaveDialog(null);
			if (n == JFileChooser.CANCEL_OPTION) {
				return;
			}

			File file = jfc.getSelectedFile();
			if (file != null) {
				if (!file.getName().endsWith(".xml")) {
					file = new File(file.getAbsolutePath() + ".xml");
				}

				if (file.exists()) {
					n = JOptionPane.showConfirmDialog(this, "Die Datei " + file.getName()
							+ " existiert schon. Soll die Datei überschrieben werden?.",
							"Datei existiert schon", JOptionPane.YES_NO_CANCEL_OPTION);
					if (n == JOptionPane.CANCEL_OPTION) {
						return;
					} else if (n == JOptionPane.YES_OPTION) {
						ok = true;
					}
				} else {
					ok = true;
				}

				if (ok) {
					try {
						CYKMainFrame.lastFileChooserDirectory = file.getParentFile();

						model.save(file);
						setTitle(file.toString());
						JOptionPane.showMessageDialog(this,
								"Die Grammatik wurde in der Datei " + file.getName()
										+ " gespeichert.", "Datei gespeichert",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (FileNotFoundException e) {
						// e.printStackTrace();
						JOptionPane
								.showMessageDialog(this, "Die Datei " + file.getName()
										+ " wurde nicht gefunden.", "Fehler",
										JOptionPane.ERROR_MESSAGE);
					} catch (IOException e) {
						// e.printStackTrace();
						JOptionPane.showMessageDialog(this,
								"Ausgabefehler beim Schreiben der Datei " + file.getName()
										+ ".", "Fehler", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}

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

		panel.add(new JButton(new ActionTableAddRule(model)), constraints);

		constraints.gridx++;
		constraints.anchor = GridBagConstraints.EAST;

		model.addCYKModelListener(this);
		tableModel = new RuleTableModel(model);
		table = new JTable(tableModel);
		table.setDefaultEditor(Rule.class, new CNFCellEditor());
		table.getTableHeader().setPreferredSize(new Dimension(0, 0));
		table.addKeyListener(new KeyAdapter() {
			private boolean edit = false;

			@Override
			public void keyPressed(KeyEvent e) {
				if (Character.isLetterOrDigit(e.getKeyChar())) {
					int row = table.getSelectedRow();
					table.editCellAt(row, 0);
					((CNFCellEditor) table.getCellEditor(0, 0)).requestFocus();
					e.consume();
					edit = true;
				} else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					actionRemoveRule.actionPerformed(null);
					e.consume();
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER
						&& table.getSelectedRow() == table.getRowCount() - 1) {
					model.addRule();
					e.consume();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				if (edit) {
					e.consume();
					edit = false;
				}
			}
		});

		panel.add(new JButton(actionRemoveRule = new ActionTableRemoveRule(model,
				table)), constraints);

		constraints.gridx = 0;
		constraints.gridy++;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1;
		constraints.weighty = 1;

		panel.add(new JScrollPane(table), constraints);

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
		inputWordField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateAction();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateAction();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateAction();
			}
		});

		panel.add(inputWordField, constraints);

		constraints.gridx++;
		constraints.weightx = 0.0;
		constraints.fill = GridBagConstraints.NONE;

		panel.add(new JButton(new ActionRandomWord(inputWordField, model)),
				constraints);

		constraints.gridx = 0;
		constraints.gridy++;
		constraints.weightx = 0.0;

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridwidth = 3;

		actionCheckWord = new ActionCheckWord(model, inputWordField);
		actionCheckWord.setEnabled(false);
		panel.add(new JButton(actionCheckWord), constraints);

		return panel;
	}

	private void updateAction() {
		actionCheckWord.setEnabled(!inputWordField.getText().isEmpty());
	}

	@Override
	public void modelChanged() {
	}

	@Override
	public void ruleAdded() {
		table.editCellAt(0, 0);
		((CNFCellEditor) table.getCellEditor(0, 0)).requestFocus();
	}
}
