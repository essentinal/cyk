package cyk.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cyk.model.interfaces.CYKModelListener;
import cyk.model.interfaces.ICYKModel;

/**
 * Dies ist das Model, die Kernfunktionalit�t des Programms.
 * 
 * @author Stephan
 * 
 */
public class CYKModel implements ICYKModel {
	private final ArrayList<CYKModelListener> listeners = new ArrayList<CYKModelListener>();

	private final Grammar grammar = new Grammar();

	public CYKModel() {
		// TODO: FOR DEBUGGING ONLY
		grammar.add(new Rule("S->A"));
		grammar.add(new Rule("A->a"));
	}

	@Override
	public void addRule() {
		grammar.add(0, new Rule("S->S"));
		fireModelChanged();
		fireRuleAdded();
	}

	@Override
	public void setRuleAt(Rule rule, int index) {
		grammar.set(index, rule);
		grammar.sort();
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

		// �BERPR�FUNG AUF VOLLST�NDIGKEIT

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

	private void fireRuleAdded() {
		for (CYKModelListener l : listeners) {
			l.ruleAdded();
		}
	}
}
