package cyk.view;

import java.awt.Toolkit;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.Action;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jdom.JDOMException;

import cyk.controller.ActionAbout;
import cyk.controller.ActionCascadeViews;
import cyk.controller.ActionCloseFrame;
import cyk.controller.ActionExit;
import cyk.controller.ActionNew;
import cyk.controller.ActionOpen;
import cyk.controller.ActionSave;
import cyk.controller.ActionTileViews;
import cyk.model.CYKModel;
import cyk.model.exceptions.GrammarParseException;
import cyk.model.interfaces.ICYKModel;

@SuppressWarnings("serial")
public class CYKMainFrame extends JFrame implements InternalFrameListener {
	public static final String APPLICATION_NAME = "CYK Algorithmus Demonstrator";
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static File lastFileChooserDirectory = new File("grammar");
	private JDesktopPane desktop;

	private Action actionSave, actionCloseFrame, actionCascade, actionTile;

	public CYKMainFrame() {
		super(APPLICATION_NAME);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		desktop = new Desktop();

		desktop.setBackground(UIManager.getColor("Label.background").darker());

		add(desktop);

		// init menu bar
		setJMenuBar(buildMenuBar());

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

		menuItem.addSeparator();

		menuItem.add(new ActionOpen(this));

		actionSave = new ActionSave(desktop);
		menuItem.add(actionSave);

		menuItem.addSeparator();

		actionCloseFrame = new ActionCloseFrame(this);
		menuItem.add(actionCloseFrame);

		menuItem.addSeparator();

		menuItem.add(new ActionExit());

		menu.add(menuItem);

		menuItem = new JMenu("Fenster");
		menuItem.setToolTipText("Fenstermenü");

		menuItem.add(actionCascade = new ActionCascadeViews(desktop));
		menuItem.add(actionTile = new ActionTileViews(desktop));

		menu.add(menuItem);

		menuItem = new JMenu("Hilfe");
		menuItem.setToolTipText("Hilfemenü");

		menuItem.add(new ActionAbout());

		menu.add(menuItem);

		updateSaveAction();

		return menu;
	}

	public void newDesktopFrame() {
		DesktopFrame frame = new DesktopFrame("Neue Grammatik", desktop);
		frame.addInternalFrameListener(this);

		desktop.add(frame);

		frame.moveToFront();
		frame.requestFocusInWindow();
		try {
			frame.setSelected(true);
		} catch (PropertyVetoException e) {
		}
	}

	public void load() {
		JFileChooser jfc = new JFileChooser(CYKMainFrame.lastFileChooserDirectory);
		jfc
				.setFileFilter(new FileNameExtensionFilter("XML Grammatik-Datei", "xml"));

		int n = jfc.showOpenDialog(null);
		if (n == JFileChooser.CANCEL_OPTION) {
			return;
		}

		File file = jfc.getSelectedFile();
		try {
			CYKMainFrame.lastFileChooserDirectory = file.getParentFile();
			ICYKModel model = new CYKModel();
			model.load(file);

			DesktopFrame frame = new DesktopFrame(file.toString(), desktop, model);
			frame.addInternalFrameListener(this);

			desktop.add(frame);

			frame.moveToFront();
			frame.requestFocusInWindow();
			try {
				frame.setSelected(true);
			} catch (PropertyVetoException e) {
			}

		} catch (FileNotFoundException e) {
			// e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Die Datei " + file.getName()
					+ " wurde nicht gefunden.", "Fehler", JOptionPane.ERROR_MESSAGE);
			return;
		} catch (IOException e) {
			// e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Eingabefehler beim Lesen der Datei "
					+ file.getName() + ".", "Fehler", JOptionPane.ERROR_MESSAGE);
			return;
		} catch (JDOMException e) {
			// e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Fehler beim Lesen der Datei "
					+ file.getName()
					+ ". Die Datei ist keine gültige XML-Grammatikdatei.", "Lesefehler",
					JOptionPane.ERROR_MESSAGE);
		} catch (GrammarParseException e) {
			// e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Fehler beim Lesen der Datei "
					+ file.getName() + ". Die Datei enthält keine gültige Grammatik.",
					"Lesefehler", JOptionPane.ERROR_MESSAGE);
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

		b = desktop.getAllFrames().length > 1;

		actionTile.setEnabled(b);
		actionCascade.setEnabled(b);
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
