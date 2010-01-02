package cyk.model;

import org.jdom.Element;

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

	public Rule(String s) throws RuleException {
		if (!s.matches("[A-Z]->[A-Z]+") && !s.matches("[A-Z]->[a-z0-9]")) {
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
	}

	public Rule(Element e) throws GrammarParseException {
		parse(e);
	}

	public NonTerminalSymbol getLeft() {
		return left;
	}

	public SymbolList getRight() {
		return right;
	}

	public boolean isStartRule() {
		return left.getCharacter() == 'S';
	}

	public boolean isTerminalRule() {
		return right.get(0).isTerminal();
	}

	@Override
	public String toString() {
		return left.toString() + "->" + right.toString();
	}

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

	@Override
	public Element toXml() {
		Element rule = new Element("rule");

		if (!CYKModel.USE_SIMPLE_FORMAT) {
			rule.setAttribute("startrule", String.valueOf(isStartRule()));
		}

		Element leftE = new Element("left");
		rule.addContent(leftE);
		leftE.addContent(left.toXml());

		rule.addContent(right.toXml());

		return rule;
	}
}
