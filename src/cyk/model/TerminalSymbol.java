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

	/* (non-Javadoc)
	 * @see cyk.model.Symbol#isTerminal()
	 */
	@Override
	public boolean isTerminal() {
		return true;
	}
}
