package cyk.model.exceptions;

/**
 * Exception für eine Grammatik, die nicht abgeleitet werden kann.
 * 
 * @author David
 */
@SuppressWarnings("serial")
public class GrammarNoDeriveException extends Exception {
	/**
	 * Erzeugt eine neue GrammarNoDeriveException
	 * @param s
	 * 		zu übergebender Text
	 */
	public GrammarNoDeriveException(String s) {
		super(s);
	}
}
