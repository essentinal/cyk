package cyk.model.interfaces;

import java.io.File;

import cyk.model.Rule;

public interface ICYKModel {
	public boolean save(File file);

	public boolean load(File file);

	public boolean parseWord(String word);

	public boolean checkGrammar();

	public int getSize();

	public void add(Rule rule);

	public Rule getRule(int i);

	public void removeRule(int i);

}
