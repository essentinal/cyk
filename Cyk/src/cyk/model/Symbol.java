package cyk.model;

import org.jdom.Element;

import cyk.model.exceptions.GrammarParseException;

/**
 * Oberklasse für Symbole. Ein Symbol ist abstrakt gesehen einfach nur ein
 * Character.
 * 
 * @author Stephan
 * 
 */
public abstract class Symbol {
	private final char character;

	public Symbol(char character) {
		this.character = character;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Symbol) {
			return ((Symbol) obj).character == character;
		}
		return false;
	}

	@Override
	public String toString() {
		return String.valueOf(character);
	}

	public char getCharacter() {
		return character;
	}

	public abstract boolean isTerminal();

	public static Symbol parse(Element element) throws GrammarParseException {
		if (!element.getName().equals("symbol") || element.getText() == null
				|| element.getText().isEmpty()) {
			throw new GrammarParseException(element);
		}

		char c = element.getText().charAt(0);

		if (Character.isUpperCase(c)) {
			return new NonTerminalSymbol(c);
		} else {
			return new TerminalSymbol(c);
		}
	}

	public Element toXml() {
		Element symbol = new Element("symbol");
		symbol.addContent(toString());
		return symbol;
	}
}
