package cyk.model.interfaces;

import org.jdom.Element;

import cyk.model.exceptions.GrammarParseException;

/**
 * Interface zur Umwandlung von Daten in das XML-Format und zum Erzeugen von
 * Daten aus dem XML-Format.
 * 
 * @author Stephan
 */
public interface IXML {
	
	/**
	 * Gibt die XML-Repräsentation des Objekts zurück.
	 * @return JDOM-Element
	 */
	public Element toXml();

	/**
	 * Liest Daten aus einem XML-Element ein.
	 * 
	 * @param element XML-Repräsentation des Objekts
	 * @throws GrammarParseException Grammatik kann nicht geparsed werden.
	 */
	public void parse(Element element) throws GrammarParseException;
}
