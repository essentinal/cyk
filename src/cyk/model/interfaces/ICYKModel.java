package cyk.model.interfaces;

import java.io.File;
import java.util.List;

import cyk.model.Rule;

public interface ICYKModel {
	public void addCYKModelListener(CYKModelListener listener);

	public boolean save(File file);

	public boolean load(File file);

	public boolean parseWord(String word);

	public boolean checkGrammar();

	public int getSize();

	public void addRule(Rule rule);

	public Rule getRule(int i);

	public void removeRules(List<Object> values);

}
