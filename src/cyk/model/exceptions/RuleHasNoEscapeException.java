package cyk.model.exceptions;


/**
 * Exception für eine Regel, die auf der rechten Seite Nichtterminalsymbole enthält,
 * für die es keine Regel gibt.
 * 
 * @author David
 *
 */
@SuppressWarnings("serial")
public class RuleHasNoEscapeException extends Exception {
	/**
	 * Erzeugt eine neue RuleHasNoEscapeException
	 * @param s
	 * 		zu übergebender Text
	 */
	public RuleHasNoEscapeException() {
		super("Rule has no escape!\n");
	}
}

