package cyk.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import cyk.view.CYKAboutDialog;

@SuppressWarnings("serial")
public class ActionAbout extends AbstractAction {

	public ActionAbout() {
		super("Über...");
		putValue(SHORT_DESCRIPTION, "Über dieses Programm");
	}

	public void actionPerformed(ActionEvent e) {
		new CYKAboutDialog();
	}
}