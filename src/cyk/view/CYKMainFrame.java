package cyk.view;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

@SuppressWarnings("serial")
public class CYKMainFrame extends JFrame {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	private JDesktopPane desktop;

	private ActionSave actionSave;

	public CYKMainFrame() {
		super("CYK Algorithmus");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// init menu bar
		setJMenuBar(buildMenuBar());

		desktop = new JDesktopPane();

		add(desktop);

		int maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
		int maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
		int x = (maxX - WIDTH) / 2;
		int y = (maxY - HEIGHT) / 2;

		setLocation(x, y);
		setSize(WIDTH, HEIGHT);
	}

	private JMenuBar buildMenuBar() {
		JMenuBar menu = new JMenuBar();

		JMenu menuItem = new JMenu("Datei");
		menuItem.setToolTipText("Dateimenü");

		menuItem.add(new ActionNew());

		menuItem.add(new ActionOpen());

		menuItem.add(actionSave = new ActionSave());
		actionSave.setEnabled(false);

		menuItem.add(new ActionExit());

		menu.add(menuItem);

		return menu;
	}

	private class ActionNew extends AbstractAction {
		public ActionNew() {
			super("Neu");
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			DesktopFrame frame = new DesktopFrame("test", desktop);

			desktop.add(frame);

			frame.moveToFront();
			frame.requestFocus();

		}
	}

	private class ActionOpen extends AbstractAction {
		public ActionOpen() {
			super("Öffnen...");
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {

		}
	}

	private class ActionSave extends AbstractAction {
		public ActionSave() {
			super("Speichern unter");
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {

		}
	}

	private class ActionExit extends AbstractAction {
		public ActionExit() {
			super("Beenden");
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
	}

}
