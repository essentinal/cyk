package cyk.model.interfaces;

/**
 * Listener zum Erkennen von Veränderungen im Model.
 * 
 * @author Stephan
 */
public interface CYKModelListener {
	
	/**
	 * Wird aufgerufen, wenn sich im Model etwas geändert hat.
	 */
	public void modelChanged();

	/**
	 * Wird aufgerufen, wenn eine Regel im Model hinzugefügt wurde.
	 */
	public void ruleAdded();
}
