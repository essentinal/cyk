package cyk.model;

import java.util.ArrayList;

/**
 * Einfache Liste von Symbolen mit überschriebener toString()-Methode.
 * 
 * @author Stephan
 * 
 */
@SuppressWarnings("serial")
public class SymbolList extends ArrayList<Symbol> {
	public SymbolList() {
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

}
