package cyk.model.exceptions;

/**
 * Exception für eine Grammatik, die eine Endlosschleife enthält.
 * 
 * @author David
 *
 */
@SuppressWarnings("serial")
public class GrammarHasALoopRuleException extends Exception{
	/**
	 * Erzeugt eine neue GrammarHasALoopRuleException.
	 * @param s
	 * 		zu übergebender Text
	 */
	public GrammarHasALoopRuleException(String s) {
		super(s);
	}
}
