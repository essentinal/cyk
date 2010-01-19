package cyk.model.exceptions;

/**
 * Exception für eine unvollständige Grammatik.
 * 
 * @author David
 */
@SuppressWarnings("serial")
public class GrammarIncompleteException extends Exception {
	/**
	 * Erzeugt eine neue GrammarIncompleteException.
	 * @param s
	 * 		zu übergebender Text
	 */
	public GrammarIncompleteException(String s) {
		super(s);
	}
}
