package cyk.model.interfaces;

import org.jdom.Element;

import cyk.model.exceptions.GrammarParseException;

public interface IXML {
	public Element toXml();

	public void parse(Element element) throws GrammarParseException;
}