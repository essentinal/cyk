package cyk.model.exceptions;

/**
 * Exception für eine Regel, die überflüssig ist.
 * 
 * @author David
 *
 */
@SuppressWarnings("serial")
public class RuleNotNeededException extends Exception {
	/**
	 * Erzeugt eine neue RuleNotNeededException
	 * @param s
	 * 		zu übergebender Text
	 */
	public RuleNotNeededException() {
		super("Rule is unnecessary\n");
	}
}
