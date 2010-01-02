package cyk.model.interfaces;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.jdom.JDOMException;

import cyk.model.Rule;
import cyk.model.exceptions.GrammarException;
import cyk.model.exceptions.GrammarParseException;

public interface ICYKModel {
	public void addCYKModelListener(CYKModelListener listener);

	public void save(File file) throws FileNotFoundException, IOException;

	public void load(File file) throws JDOMException, IOException,
			GrammarParseException;

	public boolean parseWord(String word);

	public String getRandomWord(int length) throws GrammarException;

	public boolean checkGrammar();

	public int getSize();

	public void addRule();

	public void setRuleAt(Rule rule, int index);

	public Rule getRule(int i);

	public void removeRules(List<Object> values);

}
