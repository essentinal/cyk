package cyk.model;

import org.jdom.Element;

import cyk.util.XMLUtil;

@SuppressWarnings("serial")
public class GrammarParseException extends Exception {
	public GrammarParseException(Element e) {
		super("Error parsing grammar\n" + XMLUtil.toString(e));
	}
}
