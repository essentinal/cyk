package cyk.model.exceptions;


/**
 * Exception für eine Grammatik, die nich in CNF ist.
 * 
 * @author David
 */
@SuppressWarnings("serial")
public class GrammarIsNotInCnfException extends Exception {	
	/**
	 * Erzeugt eine neue GrammarIsNotInCnfException
	 * @param s
	 * 		zu übergebender Text
	 */
	public GrammarIsNotInCnfException(String s) {
		super(s);
	}
}
