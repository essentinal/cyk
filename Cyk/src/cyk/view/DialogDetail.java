package cyk.view;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class DialogDetail extends JDialog {

	private JLabel iconLabel = null;
	private JLabel textLabel;
	private JToggleButton detailButton;
	private JButton closeButton;
	private JScrollPane detailPane;
	private JTextArea detailArea;

	public enum IconType {
		ErrorIcon, InformationIcon;
		public String toString() {
			return Character.toLowerCase(name().charAt(0)) + name().substring(1);
		};
	}

	public DialogDetail() {
		super((Frame) null);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);

		setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();

		// icon
		constraints.insets = new Insets(20, 20, 20, 20);
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.0;
		constraints.weighty = 0.0;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		iconLabel = new JLabel();
		add(iconLabel, constraints);

		// text
		constraints.insets = new Insets(5, 10, 10, 10);
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.BOTH;
		textLabel = new JLabel();
		add(textLabel, constraints);

		// details toggle button
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.weightx = 0.0;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.NONE;
		detailButton = new JToggleButton(new ActionDetail());
		add(detailButton, constraints);

		// platzhalter
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.NONE;
		add(new JLabel(), constraints);

		// close button
		constraints.gridx = 3;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 0.0;
		constraints.weighty = 0.0;
		constraints.fill = GridBagConstraints.NONE;
		closeButton = new JButton(new ActionClose());
		add(closeButton, constraints);

		// details
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.gridheight = 1;
		constraints.weightx = 0.0;
		constraints.weighty = 1.0;
		constraints.fill = GridBagConstraints.BOTH;
		detailArea = new JTextArea();
		detailArea.setEditable(false);
		// detailArea.setLineWrap(true);
		detailPane = new JScrollPane(detailArea);
		detailPane.setMinimumSize(new Dimension(200, 250));
		detailPane.setPreferredSize(new Dimension(200, 250));
		detailPane.setVisible(false);
		add(detailPane, constraints);

		pack();
		setLocationRelativeTo(null);

	}

	public void setIcon(IconType type) {
		try {
			iconLabel.setIcon(UIManager.getIcon("OptionPane." + type.toString()));
			pack();
		} catch (Exception e) {
			// nicht so wichtig
		}
	}

	public void setText(String s) {
		textLabel.setText(s);
		pack();
	}

	public void setDetailsVisible(boolean visible) {
		detailButton.setSelected(visible);
		detailPane.setVisible(visible);
		pack();
	}

	public void setDetail(String s) {
		detailArea.setText(s);
	}

	public class ActionDetail extends AbstractAction {
		public ActionDetail() {
			super("Details");

			putValue(Action.SHORT_DESCRIPTION, "Zeige/verberge Details");
		}

		public void actionPerformed(ActionEvent e) {
			setDetailsVisible(detailButton.isSelected());
		}
	}

	public class ActionClose extends AbstractAction {
		public ActionClose() {
			super("Schließen");
			putValue(Action.SHORT_DESCRIPTION, "Den Dialog schließen");
		}

		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
}
