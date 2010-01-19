package cyk.model.exceptions;

/**
 * Exception für eine Grammatik, die keine Startregel enthält.
 * 
 * @author David
 *
 */
@SuppressWarnings("serial")
public class GrammarNoStartruleException extends Exception {
	/**
	 * Erzeugt eine neue GrammarNoStartruleException
	 * @param s
	 * 		zu übergebender Text
	 */
	public GrammarNoStartruleException(String s) {
		super(s);
	}
}
