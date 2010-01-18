package cyk.model;

import java.util.ArrayList;
import java.util.Iterator;

import org.jdom.Element;

import cyk.model.exceptions.GrammarParseException;
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

	public SymbolList(Element element) throws GrammarParseException {
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
	public void parse(Element element) throws GrammarParseException {
		Iterator<?> it = element.getChildren("symbol").iterator();
		while (it.hasNext()) {
			Element symbolE = (Element) it.next();
			add(Symbol.parse(symbolE));
		}
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
