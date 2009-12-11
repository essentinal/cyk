package cyk.model.interfaces;

import java.io.File;

import cyk.model.Rule;

public interface ICYKModel {
	public boolean save(File file);

	public boolean load(File file);

	public boolean parseWord();

	public int getSize();

	public void add(Rule rule);

	public Rule getRule(int i);

	public void removeRule(int i);

	public void addModelListener(CYKModelListener listener);

}
