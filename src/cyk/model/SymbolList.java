package cyk.model;

import java.util.ArrayList;
import java.util.Iterator;

import org.jdom.Element;

import cyk.model.exceptions.GrammarParseException;
import cyk.model.interfaces.IXML;

/**
 * Liste von Symbolen. Die toString()-Methode ist zur besseren Ausgabe
 * überschrieben und die Liste kann von XML geparst bzw in XML umgewandelt
 * werden.
 * 
 * @author Stephan
 * 
 */
@SuppressWarnings("serial")
public class SymbolList extends ArrayList<Symbol> implements IXML {
	public SymbolList() {
	}

	/**
	 * Erzeugt eine neue Symbolliste aus einem XML-Element.
	 * 
	 * @param element XML-Element, das die Liste enthält
	 * @throws GrammarParseException Liste kann nicht geparst werden
	 */
	public SymbolList(Element element) throws GrammarParseException {
		parse(element);
	}

	/**
	 * Erzeugt eine leere Symbolliste einer vorgegebenen Größe.
	 * 
	 * @param size Größe
	 */
	public SymbolList(int size) {
		super(size);
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractCollection#toString()
	 */
	@Override
	public String toString() {
		String str = "";
		for (Symbol s : this) {
			str += s.toString();
		}
		return str;
	}

	/* (non-Javadoc)
	 * @see cyk.model.interfaces.IXML#parse(org.jdom.Element)
	 */
	@Override
	public void parse(Element element) throws GrammarParseException {
		Iterator<?> it = element.getChildren("symbol").iterator();
		while (it.hasNext()) {
			Element symbolE = (Element) it.next();
			add(Symbol.parse(symbolE));
		}
	}

	/* (non-Javadoc)
	 * @see cyk.model.interfaces.IXML#toXml()
	 */
	@Override
	public Element toXml() {
		Element root = new Element("right");
		for (Symbol s : this) {
			root.addContent(s.toXml());
		}
		return root;
	}

}
