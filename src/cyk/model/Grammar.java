package cyk.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Dies ist eine Grammatik in Form einer Liste von Regeln. Werden Regeln
 * hinzugefügt, wird die Liste sortiert.
 * 
 * @author Stephan
 * 
 */
@SuppressWarnings("serial")
public class Grammar extends ArrayList<Rule> {
	public void sort() {
		Collections.sort(this, new Comparator<Rule>() {
			@Override
			public int compare(Rule r1, Rule r2) {
				int i = r1.toString().compareTo(r2.toString());

				boolean r1Term = r1.getRight().get(0).isTerminal();
				boolean r2Term = r2.getRight().get(0).isTerminal();

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
}
