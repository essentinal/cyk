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

/**
 * Dies ist das Hauptfenster der Anwendung.
 * 
 * @author Stephan
 */
@SuppressWarnings("serial")
public class CYKMainFrame extends JFrame implements InternalFrameListener {
	public static final String APPLICATION_NAME = "CYK Algorithmus Demonstrator";
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static File lastFileChooserDirectory = new File("grammar");
	private JDesktopPane desktop;

	private Action actionSave, actionCloseFrame, actionCascade, actionTile;

	/**
	 * Erzeugt ein neues CYKMainFrame.
	 */
	public CYKMainFrame() {
		super(APPLICATION_NAME);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		desktop = new Desktop();

		desktop.setBackground(UIManager.getColor("Label.background").darker());

		add(desktop);

		// Menüleiste initialisieren
		setJMenuBar(buildMenuBar());

		int maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
		int maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
		int x = (maxX - WIDTH) / 2;
		int y = (maxY - HEIGHT) / 2;

		setLocation(x, y);
		setSize(WIDTH, HEIGHT);
	}

	/**
	 * Initialisiert die Menüleiste des Fensters.
	 * 
	 * @return Menüleiste
	 */
	private JMenuBar buildMenuBar() {
		JMenuBar menu = new JMenuBar();

		// Dateimenü
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

		// Fenstermenü
		menuItem = new JMenu("Fenster");
		menuItem.setToolTipText("Fenstermenü");

		menuItem.add(actionCascade = new ActionCascadeViews(desktop));
		menuItem.add(actionTile = new ActionTileViews(desktop));

		menu.add(menuItem);

		// Hilfemenü
		menuItem = new JMenu("Hilfe");
		menuItem.setToolTipText("Hilfemenü");

		menuItem.add(new ActionAbout());

		menu.add(menuItem);

		updateActions();

		return menu;
	}

	/**
	 * Erzeugt ein neues DesktopFrame (und damit auch eine neue Grammatik) und
	 * fügt dieses dem Desktop hinzu.
	 */
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

	/**
	 * Führt das Laden einer Grammatik aus einer Datei durch. Dabei öffnet sich
	 * ein JFileChooser zum Auswählen der Datei.
	 */
	public void load() {
		if (!CYKMainFrame.lastFileChooserDirectory.isDirectory()){
			CYKMainFrame.lastFileChooserDirectory = CYKMainFrame.lastFileChooserDirectory.getParentFile();
		}
		
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

	/**
	 * Schließt das aktuelle Frame.
	 */
	public void closeActiveFrame() {
		desktop.getSelectedFrame().dispose();
	}

	/**
	 * Aktualisiert die Actions zum Speichern und Schließen des aktuellen
	 * Fensters, je nachdem ob ein Fenster ausgewählt/geöffnet ist.
	 */
	private void updateActions() {
		JInternalFrame f = desktop.getSelectedFrame();
		boolean b = (f != null);

		actionCloseFrame.setEnabled(b);
		actionSave.setEnabled(b);

		b = desktop.getAllFrames().length > 1;

		actionTile.setEnabled(b);
		actionCascade.setEnabled(b);
	}

	/**
	 * Callback-Methode des InternalFrameListeners. Bei Veränderungen wird updateActions() aufgerufen. 
	 * @see javax.swing.event.InternalFrameListener#internalFrameActivated(javax.swing.event.InternalFrameEvent)
	 */
	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		updateActions();
	}

	/**
	 * Callback-Methode des InternalFrameListeners. Bei Veränderungen wird updateActions() aufgerufen. 
	 * @see javax.swing.event.InternalFrameListener#internalFrameClosed(javax.swing.event.InternalFrameEvent)
	 */
	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		updateActions();
	}

	/**
	 * Callback-Methode des InternalFrameListeners. Bei Veränderungen wird updateActions() aufgerufen. 
	 * @see javax.swing.event.InternalFrameListener#internalFrameClosing(javax.swing.event.InternalFrameEvent)
	 */
	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		updateActions();
	}

	/**
	 * Callback-Methode des InternalFrameListeners. Bei Veränderungen wird updateActions() aufgerufen. 
	 * @see javax.swing.event.InternalFrameListener#internalFrameDeactivated(javax.swing.event.InternalFrameEvent)
	 */
	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
		updateActions();
	}

	/**
	 * Callback-Methode des InternalFrameListeners. Bei Veränderungen wird updateActions() aufgerufen. 
	 * @see javax.swing.event.InternalFrameListener#internalFrameDeiconified(javax.swing.event.InternalFrameEvent)
	 */
	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
		updateActions();
	}

	/**
	 * Callback-Methode des InternalFrameListeners. Bei Veränderungen wird updateActions() aufgerufen. 
	 * @see javax.swing.event.InternalFrameListener#internalFrameIconified(javax.swing.event.InternalFrameEvent)
	 */
	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
		updateActions();
	}

	/**
	 * Callback-Methode des InternalFrameListeners. Bei Veränderungen wird updateActions() aufgerufen. 
	 * @see javax.swing.event.InternalFrameListener#internalFrameOpened(javax.swing.event.InternalFrameEvent)
	 */
	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		updateActions();
	}

}
