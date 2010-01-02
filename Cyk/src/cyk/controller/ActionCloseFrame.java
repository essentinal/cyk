package cyk.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import cyk.view.CYKMainFrame;

@SuppressWarnings("serial")
public class ActionCloseFrame extends AbstractAction {
	private final CYKMainFrame frame;

	public ActionCloseFrame(CYKMainFrame frame) {
		super("Grammatik schlieﬂen");
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		frame.closeActiveFrame();
	}
}