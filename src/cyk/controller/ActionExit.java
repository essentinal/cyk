package cyk.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class ActionExit extends AbstractAction {
	public ActionExit() {
		super("Beenden");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}
