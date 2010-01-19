package cyk.model.interfaces;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.jdom.JDOMException;

import cyk.model.Rule;
import cyk.model.exceptions.GrammarIncompleteException;
import cyk.model.exceptions.GrammarIsNotInCnfException;
import cyk.model.exceptions.GrammarNoDeriveException;
import cyk.model.exceptions.GrammarNoStartruleException;
import cyk.model.exceptions.GrammarParseException;
import cyk.model.exceptions.RuleNotNeededException;

/**
 * Interface für das CYKModel. Alle Klassen kennen nicht das Model, sondern nur
 * dieses Interface.
 * 
 * @author Stephan
 */
public interface ICYKModel {
	/**
	 * Registriert einen neuen CYKModelListener am Model.
	 * 
	 * @param listener
	 *          Listener
	 */
	public void addCYKModelListener(CYKModelListener listener);

	/**
	 * Speichert die Grammatik in der gegebenen Datei.
	 * 
	 * @param file
	 *          Datei, in der die Grammatik gespeichert werden soll.
	 * @throws FileNotFoundException
	 *           Datei nicht gefunden.
	 * @throws IOException
	 *           Input-/Outputfehler
	 */
	public void save(File file) throws FileNotFoundException, IOException;

	/**
	 * Lädt eine Grammatik aus einer Datei.
	 * 
	 * @param file
	 *          Datei, aus der geladen werden soll.
	 * @throws JDOMException
	 *           XML-Datei kann nicht geparsed werden.
	 * @throws IOException
	 *           Input-/Outputfehler
	 * @throws GrammarParseException
	 *           Grammatik kann nicht geparsed werden.
	 */
	public void load(File file) throws JDOMException, IOException,
			GrammarParseException;

	/**
	 * Führt die Erkennung eines Worts aus.
	 * 
	 * @param word
	 *          Zu erkennendes Wort
	 * @return Wort wurde erkannt oder nicht
	 */
	public boolean parseWord(String word);

	/**
	 * Erzeugt zufällig ein Wort der gegebenen Länge.
	 * 
	 * @param length
	 *          Länge des Worts
	 * @return Zufallswort
	 * @throws GrammarIncompleteException
	 *           Grammatik ist nicht komplett
	 * @throws GrammarNoDeriveException
	 *           Es gibt keine Ableitung der vorgegebenen Länge
	 * @throws GrammarNoStartruleException
	 *           Es gibt keine Startregel
	 */
	public String getRandomWord(int length) throws GrammarIncompleteException,
			GrammarNoDeriveException, GrammarNoStartruleException;

	/**
	 * Überprüft, ob die Grammatik in gültiger CNF vorliegt.
	 * 
	 * @throws RuleNotNeededException
	 *           Überflüssige Regel gefunden
	 * @throws GrammarIsNotInCnfException
	 *           Grammatik weicht von der CNF-Notation ab
	 * @throws GrammarIncompleteException
	 *           Grammatik ist unvollständig
	 */
	public void checkGrammar() throws RuleNotNeededException,
			GrammarIsNotInCnfException, GrammarIncompleteException;

	/**
	 * Gibt die CYK-Tabelle zurück. Bevor <code>parseWord()</code> aufgerufen
	 * wurde, ist diese immer <code>null</code>.
	 * 
	 * @return CYK-Tabelle
	 */
	public String[][] getTable();

	/**
	 * Gibt die Anzahl der Regeln zurück.
	 * 
	 * @return Anzahl der Regeln
	 */
	public int getSize();

	/**
	 * Neue Regel hinzufügen
	 */
	public void addRule();

	/**
	 * Ersetzt die Regel an der angegebenen Stelle.
	 * 
	 * @param rule neue Regel
	 * @param index Index der ersetzt werden soll
	 */
	public void setRuleAt(Rule rule, int index);

	/**
	 * Gibt die Regel an der gegebenen Stelle zurück.
	 * 
	 * @param i Index
	 * @return Regel
	 */
	public Rule getRule(int i);

	/**
	 * Entfernt eine Liste von Regeln aus der Grammatik.
	 * 
	 * @param values Liste von Regeln
	 */
	public void removeRules(List<Object> values);

}
