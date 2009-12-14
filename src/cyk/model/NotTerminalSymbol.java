package cyk.model;

/**
 * Klasse f�r ein Nichtterminalzeichen.
 * 
 * @author Stephan
 * 
 */
public class NotTerminalSymbol extends Symbol {
	public NotTerminalSymbol(char character) {
		super(character);

	}

	@Override
	public boolean isTerminal() {
		return false;
	}

}
