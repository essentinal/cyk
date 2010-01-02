package cyk.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.JDOMException;

import cyk.model.interfaces.CYKModelListener;
import cyk.model.interfaces.ICYKModel;
import cyk.util.XMLUtil;

/**
 * Dies ist das Model, die Kernfunktionalität des Programms.
 * 
 * @author Stephan
 * 
 */
public class CYKModel implements ICYKModel {
	public static final boolean USE_SIMPLE_FORMAT = true;

	private final ArrayList<CYKModelListener> listeners = new ArrayList<CYKModelListener>();

	private final Grammar grammar = new Grammar();

	public CYKModel() {
		// TODO: FOR DEBUGGING ONLY
		try {
			grammar.add(new Rule("S->A"));
			grammar.add(new Rule("A->a"));
		} catch (RuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void addRule() {
		try {
			grammar.add(0, new Rule("S->S"));
			fireModelChanged();
			fireRuleAdded();
		} catch (RuleException e) {
			e.printStackTrace();
		}
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
	public void save(File file) throws FileNotFoundException, IOException {
		XMLUtil.output(grammar.toXml(), file);
	}

	@Override
	public void load(File file) throws JDOMException, IOException,
			GrammarParseException {
		this.grammar.parse(XMLUtil.read(file));

		fireModelChanged();
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

	private void fireRuleAdded() {
		for (CYKModelListener l : listeners) {
			l.ruleAdded();
		}
	}
}
