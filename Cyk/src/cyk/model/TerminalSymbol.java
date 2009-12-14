package cyk.model;

/**
 * Klasse für ein Terminalzeichen.
 * 
 * @author Stephan
 * 
 */
public class TerminalSymbol extends Symbol {
	public TerminalSymbol(char character) {
		super(character);

	}

	@Override
	public boolean isTerminal() {
		return true;
	}
}
