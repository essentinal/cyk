package cyk.model;

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
}
