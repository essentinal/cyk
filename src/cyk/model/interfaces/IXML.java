package cyk.model.interfaces;

import org.jdom.Element;

import cyk.model.GrammarParseException;

public interface IXML {
	public Element toXml();

	public void parse(Element element) throws GrammarParseException;
}
