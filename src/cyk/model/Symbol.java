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

	/**
	 * Erzeugt ein neues Symbol aus einem Character.
	 * 
	 * @param character Character, der das Symbol repräsentiert.
	 */
	public Symbol(char character) {
		this.character = character;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Symbol) {
			return ((Symbol) obj).character == character;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.valueOf(character);
	}

	/**
	 * Gibt den Character zurück.
	 * @return Character
	 */
	public char getCharacter() {
		return character;
	}

	/**
	 * Gibt zurück, ob das Symbol ein TerminalSymbol ist.
	 * @return Ist das Symbol ein TerminalSymbol
	 */
	public abstract boolean isTerminal();

	/**
	 * Erzeugt ein Symbol aus einem XML-Element.
	 * 
	 * @param element XML-Element, das das Symbol enthält.
	 * @return das neue Symbol
	 * @throws GrammarParseException XML enthält keine gültige Symbolbeschreibung
	 */
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

	/**
	 * Gibt die XML-Repräsentation des Objekts zurück.
	 * @return XML-Repräsentation des Objekts
	 */
	public Element toXml() {
		Element symbol = new Element("symbol");
		symbol.addContent(toString());
		return symbol;
	}
}
