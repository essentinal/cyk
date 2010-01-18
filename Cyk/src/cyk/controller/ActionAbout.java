package cyk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import cyk.view.DialogAbout;

@SuppressWarnings("serial")
public class ActionAbout extends AbstractAction {

	public ActionAbout() {
		super("Info...");
		putValue(SHORT_DESCRIPTION, "Über dieses Programm");
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_H,
				KeyEvent.CTRL_DOWN_MASK));
	}

	public void actionPerformed(ActionEvent e) {
		new DialogAbout();
	}
}