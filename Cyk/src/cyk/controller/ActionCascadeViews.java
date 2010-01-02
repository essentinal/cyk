package cyk.controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;

import javax.swing.AbstractAction;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

@SuppressWarnings("serial")
public class ActionCascadeViews extends AbstractAction {
	private final JDesktopPane desktop;

	public ActionCascadeViews(JDesktopPane desktop) {
		super("Kaskadiert anordnen");
		putValue(SHORT_DESCRIPTION, "Alle Fenster kaskadierend anordnen");

		this.desktop = desktop;
	}

	public void actionPerformed(ActionEvent e) {
		Dimension d = desktop.getSize();
		JInternalFrame[] frames = desktop.getAllFrames();
		int n = frames.length;
		if (n == 0) {
			return;
		}
		int s = frames[0].getRootPane().getLocation().y;
		int m = n * s;
		int w = d.width - m;
		int h = d.height - m;

		for (JInternalFrame f : frames) {
			try {
				f.setMaximum(false);
			} catch (PropertyVetoException e1) {
			}
		}

		System.out.println("s: " + s);

		for (int i = 0; i < n; i++) {
			frames[n - i - 1].setBounds(i * s, i * s, w + s, h + s);
		}
	}
}
