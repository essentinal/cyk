package cyk.model;


public class Rule {
	private NotTerminalSymbol left;
	private SymbolList right;

	@Override
	public String toString() {
		return left.toString() + right.toString();
	}

}
