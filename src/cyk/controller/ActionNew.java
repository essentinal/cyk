package cyk.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import cyk.view.CYKMainFrame;

@SuppressWarnings("serial")
public class ActionNew extends AbstractAction {
	private final CYKMainFrame frame;

	public ActionNew(CYKMainFrame frame) {
		super("Neu");
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.newDesktopFrame();
	}
}
