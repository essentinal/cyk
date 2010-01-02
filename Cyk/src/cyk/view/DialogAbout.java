package cyk.view;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.lang.reflect.Method;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import cyk.controller.ActionCloseDialog;

@SuppressWarnings("serial")
public class DialogAbout extends JDialog {
	private static final String TEXT_ABOUT = "<html><h2>"
			+ CYKMainFrame.APPLICATION_NAME
			+ "</h2><h3>"
			+ "(c) Copyright David Walter und Stephan Dreyer 2010</h3>"
			+ "Dieses Programm entstand im Rahmen des Kurses \"Theorie der Informatik\" an der <a href='http://fh-brandenburg.de/'>Fachhochschule Brandenburg</a> und<br/> "
			+ "dient zur Demonstration des <a href='http://de.wikipedia.org/wiki/Cocke-Younger-Kasami-Algorithmus'>Cocke-Younger-Kasami-Algorithmus</a>, kurz CYK-Algorithmus. <br/>"
			+ "Der CYK-Algorithmus wurde in den 1960er Jahren zur Erkennung einer Grammatik in <a href='http://de.wikipedia.org/wiki/Chomsky-Normalform'>Chomsky-Normalform</a> entwickelt.<br/><br/>"
			+ "<b>Eine Grammatik in Chomsky-Normalform (CNF) kann aus folgenden Regeln bestehen.</b><br/><br/>"
			+ "Die linke Seite einer Regel muss immer aus genau einem Nichtterminalzeichen bestehen.<br/>"
			+ "Die rechte Seite kann aus einem oder zwei Nichtterminalzeichen bestehen:<br/>"
			+ "A->B<br/>"
			+ "A->BC<br/><br/>"
			+ "Die rechte Seite kann aus genau einem Terminalzeichen bestehen:<br/>"
			+ "A->a<br/><br/>"
			+ "Zur Vollständigkeit muss immer mindestens eine Startregel existieren.<br/>"
			+ "Diese muss das Nichtterminalzeichen S enthalten:<br/>"
			+ "S->A<br/></html>";

	public DialogAbout() {
		super((Frame) null, "Über dieses Programm", true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);

		setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(15, 15, 15, 15);
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.CENTER;

		JEditorPane textLabel = new JEditorPane("text/html", TEXT_ABOUT);
		textLabel.setEditable(false);
		textLabel.setFocusable(false);
		textLabel.setOpaque(false);
		textLabel.addHyperlinkListener(new HyperlinkListener() {
			public void hyperlinkUpdate(HyperlinkEvent hle) {
				if (HyperlinkEvent.EventType.ACTIVATED.equals(hle.getEventType())) {
					try {
						openBrowser(hle.getURL().toString());
					} catch (Exception e) {
						// ignorieren
					}
				}
			}
		});
		add(textLabel, constraints);

		constraints.gridy++;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(15, 15, 15, 15);

		add(new JButton(new ActionCloseDialog(this)), constraints);

		pack();
		setLocationRelativeTo(null);

		setVisible(true);

	}

	private void openBrowser(String url) throws Exception {
		String osName = System.getProperty("os.name");
		try {
			if (osName.startsWith("Mac OS")) {
				Class<?> fileMgr = Class.forName("com.apple.eio.FileManager");
				Method openURL = fileMgr.getDeclaredMethod("openURL",
						new Class[] { String.class });
				openURL.invoke(null, new Object[] { url });
			} else if (osName.startsWith("Windows")) {
				Runtime.getRuntime()
						.exec("rundll32 url.dll,FileProtocolHandler " + url);
			} else {
				String[] browsers = { "firefox", "opera", "konqueror", "epiphany",
						"mozilla", "netscape" };
				String browser = null;
				for (int count = 0; count < browsers.length && browser == null; count++)
					if (Runtime.getRuntime().exec(
							new String[] { "which", browsers[count] }).waitFor() == 0) {
						browser = browsers[count];
						if (browser == null) {
							throw new Exception("Could not find web browser");
						} else {
							Runtime.getRuntime().exec(new String[] { browser, url });
						}
					}
			}
		} catch (Exception e) {
		}
	}
}
