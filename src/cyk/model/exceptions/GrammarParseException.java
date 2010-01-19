package cyk.model.exceptions;

import org.jdom.Element;

import cyk.util.XMLUtil;

/**
 * Exception für eine Grammatik, die nicht geparst werden kann.
 * 
 * @author Dolioz
 *
 */
@SuppressWarnings("serial")
public class GrammarParseException extends Exception {
	/**
	 * Erzeugt eine neue GrammarParseException
	 * @param s
	 * 		zu übergebender Text
	 */
	public GrammarParseException(Element e) {
		super("Error parsing grammar\n" + XMLUtil.toString(e));
	}
}
