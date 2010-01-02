package cyk.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import cyk.view.CYKAboutDialog;

@SuppressWarnings("serial")
public class ActionAbout extends AbstractAction {

	public ActionAbout() {
		super("�ber...");
		putValue(SHORT_DESCRIPTION, "�ber dieses Programm");
	}

	public void actionPerformed(ActionEvent e) {
		new CYKAboutDialog();
	}
}