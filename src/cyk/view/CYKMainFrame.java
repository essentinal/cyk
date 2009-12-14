package cyk.view;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import cyk.controller.ActionCloseFrame;
import cyk.controller.ActionExit;
import cyk.controller.ActionNew;

@SuppressWarnings("serial")
public class CYKMainFrame extends JFrame implements InternalFrameListener {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	private JDesktopPane desktop;

	private Action actionSave, actionCloseFrame;

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

		menuItem.add(new ActionNew(this));

		menuItem.add(new ActionOpen());

		actionSave = new ActionSave();
		actionSave.setEnabled(false);
		menuItem.add(actionSave);

		actionCloseFrame = new ActionCloseFrame(this);
		actionCloseFrame.setEnabled(false);
		menuItem.add(actionCloseFrame);

		menuItem.add(new ActionExit());

		menu.add(menuItem);

		return menu;
	}

	public void newDesktopFrame() {
		DesktopFrame frame = new DesktopFrame("test", desktop);
		frame.addInternalFrameListener(this);

		desktop.add(frame);

		frame.moveToFront();
		frame.requestFocusInWindow();
		try {
			frame.setSelected(true);
		} catch (PropertyVetoException e) {
		}
	}

	private class ActionOpen extends AbstractAction {
		public ActionOpen() {
			super("Öffnen...");
		}

		@Override
		public void actionPerformed(ActionEvent e) {

		}
	}

	private class ActionSave extends AbstractAction {
		public ActionSave() {
			super("Speichern unter");
		}

		@Override
		public void actionPerformed(ActionEvent e) {

		}
	}

	public void closeActiveFrame() {
		desktop.getSelectedFrame().dispose();
	}

	private void updateSaveAction() {
		JInternalFrame f = desktop.getSelectedFrame();
		boolean b = (f != null);

		actionCloseFrame.setEnabled(b);
		actionSave.setEnabled(b);

	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		updateSaveAction();
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		updateSaveAction();
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		updateSaveAction();
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
		updateSaveAction();
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
		updateSaveAction();
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
		updateSaveAction();
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		updateSaveAction();
	}

}
