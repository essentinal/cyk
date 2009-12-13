package cyk.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cyk.model.interfaces.CYKModelListener;
import cyk.model.interfaces.ICYKModel;

/**
 * Dies ist das Model, die Kernfunktionalität des Programms.
 * 
 * @author Stephan
 * 
 */
public class CYKModel implements ICYKModel {
	private final ArrayList<CYKModelListener> listeners = new ArrayList<CYKModelListener>();

	private final Grammar grammar = new Grammar();

	@Override
	public void addRule(Rule rule) {
		grammar.add(rule);
		fireModelChanged();
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
	public void removeRules(List<Object> values) {
		grammar.removeAll(values);
		fireModelChanged();
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

		// LOAD HERE

		fireModelChanged();
		return false;
	}

	@Override
	public boolean checkGrammar() {

		// ÜBERPRÜFUNG AUF VOLLSTÄNDIGKEIT

		return false;
	}

	@Override
	public void addCYKModelListener(CYKModelListener listener) {
		listeners.add(listener);
	}

	private void fireModelChanged() {
		for (CYKModelListener l : listeners) {
			l.modelChanged();
		}
	}
}
