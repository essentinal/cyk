package cyk.model;

import java.io.File;

import cyk.model.interfaces.ICYKModel;

/**
 * Dies ist das Model, die Kernfunktionalität des Programms.
 * 
 * @author Stephan
 * 
 */
public class CYKModel implements ICYKModel {
	private final Grammar grammar = new Grammar();

	@Override
	public void add(Rule rule) {
		grammar.add(rule);
	}

	@Override
	public Rule getRule(int i) {
		return grammar.get(i);
	}

	@Override
	public int getSize() {
		return grammar.size();
	}

	@Override
	public void removeRule(int i) {
		grammar.remove(i);
	}

	@Override
	public boolean parseWord(String word) {

		// DO THE THING HERE

		return false;
	}

	@Override
	public boolean save(File file) {

		return false;
	}

	@Override
	public boolean load(File file) {

		return false;
	}

	@Override
	public boolean checkGrammar() {

		// ÜBERPRÜFUNG AUF VOLLSTÄNDIGKEIT

		return false;
	}

}
