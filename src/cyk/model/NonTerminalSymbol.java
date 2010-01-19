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

	/* (non-Javadoc)
	 * @see cyk.model.Symbol#isTerminal()
	 */
	@Override
	public boolean isTerminal() {
		return false;
	}

}
