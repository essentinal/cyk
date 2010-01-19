package cyk.model;

import org.jdom.Element;

import cyk.model.exceptions.GrammarParseException;
import cyk.model.exceptions.RuleException;
import cyk.model.interfaces.IXML;

/**
 * Dies ist eine Regel in CNF. Eine Regel hat eine linke Seite, die ein
 * Nichtterminalzeichen sein muss und eine rechte Seite, die aus einem
 * Terminalzeichen oder 2 Nichtterminalzeichen bestehen darf.
 * 
 * @author Stephan
 * 
 */
public class Rule implements IXML {
	private NonTerminalSymbol left;
	private SymbolList right;

	/**
	 * Erzeugt eine CNF-Regel aus einem String.
	 * 
	 * @param s String der einzulesen ist
	 * @throws RuleException keine gültige CNF-Regel
	 */
	public Rule(String s) throws RuleException {
		if (!s.matches("[A-Z]->[A-Z][A-Z]") && !s.matches("[A-Z]->[a-z0-9]")) {
			throw new RuleException(s + " is no valid rule!");
		}

		left = new NonTerminalSymbol(s.charAt(0));

		// Rechte Seite kann max 2 stellen haben
		right = new SymbolList(2);

		for (int i = s.indexOf("->") + 2; i < s.length(); i++) {
			char c = s.charAt(i);

			Symbol sym;
			if (Character.isUpperCase(c)) {
				// if c == A
				sym = new NonTerminalSymbol(c);
			} else {
				// if c == a
				sym = new TerminalSymbol(c);
			}
			right.add(sym);

		}

		if (right.size() == 1 && left.equals(right.get(0))) {
			throw new RuleException(s + " is an unnecessary rule!");
		}
	}

	/**
	 * Erzeugt eine CNF-Regel aus einem XML-Element.
	 * 
	 * @param e Element, das geparst werden soll
	 * @throws GrammarParseException Grammatik kann nicht geparst werden
	 */
	public Rule(Element e) throws GrammarParseException {
		parse(e);
	}

	/**
	 * Gibt die linke Seite der Regel zurück.
	 * @return Linke Seite
	 */
	public NonTerminalSymbol getLeft() {
		return left;
	}

	/**
	 * Gibt die rechte Seite der Regel zurück.
	 * @return Rechte Seite
	 */
	public SymbolList getRight() {
		return right;
	}

	/**
	 * Gibt zurück, ob diese Regel eine Startregel ist.
	 * @return Ist die Regel eine Startregel
	 */
	public boolean isStartRule() {
		return left.getCharacter() == 'S';
	}

	/**
	 * Gibt zurück, ob diese Regel eine TerminalRegel ist.
	 * @return Ist die Regel eine TerminalRegel
	 */
	public boolean isTerminalRule() {
		return right.get(0).isTerminal();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Rule) {
			return ((Rule) o).toString().equals(this.toString());
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return left.toString() + "->" + right.toString();
	}

	/* (non-Javadoc)
	 * @see cyk.model.interfaces.IXML#parse(org.jdom.Element)
	 */
	@Override
	public void parse(Element element) throws GrammarParseException {
		try {
			left = (NonTerminalSymbol) Symbol.parse(element.getChild("left")
					.getChild("symbol"));

			right = new SymbolList(element.getChild("right"));
		} catch (NullPointerException e) {
			throw new GrammarParseException(element);
		}
	}

	/* (non-Javadoc)
	 * @see cyk.model.interfaces.IXML#toXml()
	 */
	@Override
	public Element toXml() {
		Element rule = new Element("rule");

		Element leftE = new Element("left");
		rule.addContent(leftE);
		leftE.addContent(left.toXml());

		rule.addContent(right.toXml());

		return rule;
	}
}
