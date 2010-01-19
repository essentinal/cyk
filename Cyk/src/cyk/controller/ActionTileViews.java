package cyk.controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.AbstractAction;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.KeyStroke;

/**
 * Action zum gekachelten Anordnen der Fenster.
 * 
 * @author Stephan
 */
@SuppressWarnings("serial")
public class ActionTileViews extends AbstractAction {

	private final JDesktopPane desktop;

	public ActionTileViews(JDesktopPane desktop) {
		super("Gekachelt anordnen");
		putValue(SHORT_DESCRIPTION, "Alle Fenster gekachelt anordnen");
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_G,
				KeyEvent.CTRL_DOWN_MASK));
		this.desktop = desktop;
	}

	public void actionPerformed(ActionEvent e) {
		Dimension d = desktop.getSize();
		JInternalFrame[] frames = desktop.getAllFrames();
		int n = frames.length;
		if (n == 0) {
			return;
		}
		int nx = (int) Math.sqrt(n);
		int ny = (int) (Math.ceil(((double) frames.length) / nx));
		int ymax = frames.length - nx * (ny - 1);
		int w, h;

		for (JInternalFrame f : frames) {
			try {
				f.setMaximum(false);
			} catch (PropertyVetoException e1) {
			}
		}

		if (ymax == 0) {
			ny--;
			h = d.height / ny;
		} else {
			h = d.height / ny;
			if (ymax < ny) {
				ny--;
				w = d.width / ymax;
				for (int x = 0; x < ymax; x++) {
					frames[nx * ny + x].setBounds(x * w, ny * h, w, h);
				}
			}
		}

		w = d.width / nx;
		for (int y = 0; y < ny; y++) {
			for (int x = 0; x < nx; x++) {
				frames[x + y * nx].setBounds(x * w, y * h, w, h);
			}
		}
	}
}