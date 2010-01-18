package cyk.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import cyk.CYK;
import cyk.model.exceptions.GrammarIncompleteException;
import cyk.model.exceptions.GrammarNoDeriveException;
import cyk.model.exceptions.GrammarNoStartruleException;
import cyk.util.RandomIterator;

public class RandomWord {
	private HashMap<Character, List<SymbolList>> rules = new HashMap<Character, List<SymbolList>>();
	private HashMap<Integer, List<String>> derivations = new HashMap<Integer, List<String>>();
	private int length;

	private static final int MAX_DEPTH = 100;
	private static final int MAX_WIDTH = 100;
	private Random rand = new Random();

	public RandomWord(Grammar grammar, int length) {
		this.length = length;
		for (Rule r : grammar) {
			List<SymbolList> sl = rules.get(r.getLeft().getCharacter());
			if (sl == null) {
				sl = new ArrayList<SymbolList>();
				rules.put(r.getLeft().getCharacter(), sl);
			}
			sl.add(r.getRight());

		}

		if (CYK.DEBUG) {
			for (Character c : rules.keySet()) {
				System.out.println("put " + c + ":" + rules.get(c) + " into map");
			}
		}
	}

	/**
	 * Dies ist eine Linksableitung eines Strings mit Hilfe der gegebenen Regeln.
	 * 
	 * @param s
	 *          String, der abzuleiten ist.
	 * @return Abgeleiteter String
	 */
	public String derive(String s) throws GrammarIncompleteException,
			GrammarNoDeriveException, GrammarNoStartruleException {
		if (!rules.containsKey(s.charAt(0))) {
			throw new GrammarNoStartruleException(
					"The start rule is missing for this grammar");
		}

		replaceNonTerminals(s, s, 0);

		List<String> strings = null;
		int len = length;

		while (strings == null) {
			strings = derivations.get(len--);

			if (len < 0) {
				throw new GrammarNoDeriveException("Grammar can not be derived");
			}
		}

		if (CYK.DEBUG) {
			System.out.println(derivations);
		}

		int i = rand.nextInt(strings.size());

		return strings.get(i);
	}

	/**
	 * Nichtterminalzeichen rekursiv ersetzen
	 * 
	 * @param s
	 *          String, in dem ersetzt werden soll
	 * @param temp
	 *          String, der die Ableitungen zwischenspeichert
	 * @param depth
	 *          Aktuelle Ableitungstiefe
	 * @throws StackOverflowError
	 */
	private void replaceNonTerminals(String s, String temp, int depth)
			throws GrammarIncompleteException {
		if (CYK.DEBUG) {
			System.out.println(s);
			System.out.println(temp);
		}

		if (s.length() > length || depth > MAX_DEPTH) {
			return;
		}

		int len = s.length();
		List<String> strings = derivations.get(len);
		if (strings != null && strings.size() > MAX_WIDTH) {
			return;
		}

		if (s.matches("[a-z0-9]+")) {
			if (strings == null) {
				strings = new ArrayList<String>();
				derivations.put(len, strings);
			}
			strings.add(s);
		}

		char c;
		int i = 0;

		// gehe zum ersten Nichtterminalzeichen
		while (!Character.isUpperCase(c = s.charAt(i))) {
			i++;
			if (i >= s.length()) {
				return;
			}
		}

		List<SymbolList> ll = rules.get(c);

		try {

			// iteriere zufällig durch die Liste der Regeln
			RandomIterator<SymbolList> rl = new RandomIterator<SymbolList>(ll);
			while (rl.hasNext()) {
				String s2 = s.replaceFirst(String.valueOf(c), rl.next().toString());
				replaceNonTerminals(s2, temp + "->" + s2, depth + 1);
			}
		} catch (NullPointerException e) {
			throw new GrammarIncompleteException("Nonterminalsymbol " + c
					+ " has no rule");
		}
	}
}
