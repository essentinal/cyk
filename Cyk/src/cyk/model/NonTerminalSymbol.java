package cyk.model;

/**
 * Klasse für ein Nichtterminalzeichen.
 * 
 * @author Stephan
 * 
 */
public class NonTerminalSymbol extends Symbol {
	public NonTerminalSymbol(char character) {
		super(character);

	}

	@Override
	public boolean isTerminal() {
		return false;
	}

}
