package cyk.model;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class SymbolList extends ArrayList<Symbol> {

	@Override
	public String toString() {
		String str = "";
		for (Symbol s : this) {
			str += s.toString();
		}
		return str;
	}

}
