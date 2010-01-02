package cyk.model;

import java.util.ArrayList;

import org.jdom.Element;

import cyk.model.interfaces.IXML;

/**
 * Einfache Liste von Symbolen mit überschriebener toString()-Methode.
 * 
 * @author Stephan
 * 
 */
@SuppressWarnings("serial")
public class SymbolList extends ArrayList<Symbol> implements IXML {
	public SymbolList() {
	}

	public SymbolList(Element element) {
		parse(element);
	}

	public SymbolList(int size) {
		super(size);
	}

	@Override
	public String toString() {
		String str = "";
		for (Symbol s : this) {
			str += s.toString();
		}
		return str;
	}

	@Override
	public void parse(Element element) {

	}

	@Override
	public Element toXml() {
		Element root = new Element("right");
		for (Symbol s : this) {
			root.addContent(s.toXml());
		}
		return root;
	}

}
