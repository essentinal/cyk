package cyk.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import org.jdom.Element;

import cyk.model.exceptions.GrammarParseException;
import cyk.model.exceptions.RuleException;
import cyk.model.interfaces.IXML;
import cyk.util.XMLUtil;

/**
 * Dies ist eine Grammatik in Form einer Liste von Regeln. Werden Regeln
 * hinzugefügt, wird die Liste sortiert.
 * 
 * @author Stephan
 * 
 */
@SuppressWarnings("serial")
public class Grammar extends ArrayList<Rule> implements IXML {

	public Grammar() {

	}

	public void sort() {
		Collections.sort(this, new Comparator<Rule>() {
			@Override
			public int compare(Rule r1, Rule r2) {
				int i = r1.toString().compareTo(r2.toString());

				boolean r1Term = r1.isTerminalRule();
				boolean r2Term = r2.isTerminalRule();

				if (r1Term && !r2Term) {
					i += 20;
				} else if (!r1Term && r2Term) {
					i -= 20;
				}

				if (r1.isStartRule() && !r2.isStartRule()) {
					i -= 40;
				} else if (!r1.isStartRule() && r2.isStartRule()) {
					i += 40;
				}

				return i;
			}
		});
	}

	@Override
	public void parse(Element element) throws GrammarParseException {
		clear();

		if (!element.getName().equals("grammar")) {
			throw new GrammarParseException(element);
		}

		Iterator<?> it = element.getChildren("rule").iterator();
		while (it.hasNext()) {
			Element ruleE = (Element) it.next();
			Rule rule = new Rule(ruleE);
			add(rule);
		}
	}

	@Override
	public Element toXml() {
		Element root = new Element("grammar");

		for (Rule r : this) {
			root.addContent(r.toXml());
		}

		return root;
	}

	public static void main(String[] args) throws RuleException {
		Grammar g = new Grammar();
		g.add(new Rule("S->A"));
		g.add(new Rule("A->AB"));
		g.add(new Rule("A->a"));
		g.add(new Rule("B->b"));

		XMLUtil.print(g.toXml());
	}
}
