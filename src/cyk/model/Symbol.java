package cyk.model;

import org.jdom.Element;

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
	public String toString() {
		return String.valueOf(character);
	}

	public char getCharacter() {
		return character;
	}

	public abstract boolean isTerminal();

	public static Symbol parse(Element element) {
		char c = element.getText().charAt(0);

		if (element.getAttributeValue("terminal").equals("true")) {
			return new TerminalSymbol(c);
		} else {
			return new NotTerminalSymbol(c);
		}

	}

	public Element toXml() {
		Element symbol = new Element("symbol");
		symbol.addContent(toString());

		if (!CYKModel.USE_SIMPLE_FORMAT) {
			symbol.setAttribute("terminal", String.valueOf(isTerminal()));
		}

		return symbol;
	}
}
