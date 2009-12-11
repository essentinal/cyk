package cyk.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Dies ist eine Grammatik in Form einer Liste von Regeln.
 * 
 * @author Stephan
 * 
 */
@SuppressWarnings("serial")
public class Grammar extends ArrayList<Rule> {
	private void sort() {
		Collections.sort(this, new Comparator<Rule>() {
			@Override
			public int compare(Rule o1, Rule o2) {
				return o1.toString().compareTo(o2.toString());
			}
		});
	}

	@Override
	public boolean add(Rule e) {
		boolean b = super.add(e);
		sort();
		return b;
	}
}
