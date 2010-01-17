package cyk.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.JDOMException;

import cyk.model.exceptions.GrammarIncompleteException;
import cyk.model.exceptions.GrammarNoDeriveException;
import cyk.model.exceptions.GrammarNoStartruleException;
import cyk.model.exceptions.GrammarParseException;
import cyk.model.exceptions.RuleException;
import cyk.model.interfaces.CYKModelListener;
import cyk.model.interfaces.ICYKModel;
import cyk.util.XMLUtil;

/**
 * Dies ist das Model, die Kernfunktionalität des Programms.
 * 
 * @author Stephan
 * 
 */
public class CYKModel implements ICYKModel {
	private final ArrayList<CYKModelListener> listeners = new ArrayList<CYKModelListener>();
	private final Grammar grammar = new Grammar();

	// die tabelle für den cyk-algorithmus
	private String cykTable[][];

	public CYKModel() {
		// TODO: FOR DEBUGGING ONLY
		try {
			grammar.add(new Rule("S->AA"));
			grammar.add(new Rule("A->AA"));
			grammar.add(new Rule("A->a"));
		} catch (RuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void addRule() {
		try {
			grammar.add(0, new Rule("S->A"));
			fireModelChanged();
			fireRuleAdded();
		} catch (RuleException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setRuleAt(Rule rule, int index) {
		grammar.set(index, rule);
		grammar.sort();
		fireModelChanged();
	}

	@Override
	public Rule getRule(int i) {
		return grammar.get(i);
	}

	@Override
	public int getSize() {
		return grammar.size();
	}

	@Override
	public void removeRules(List<Object> values) {
		grammar.removeAll(values);
		fireModelChanged();
	}

	@Override
	public boolean parseWord(String word) {
		int i, j, k, y, z;
		int n = word.length();
		char leftNonTerminal, rightNonTerminal;
		char wordCharArray[] = word.toCharArray();
		String tmpString;
		cykTable = new String[n][n];

		// initCykTable row = 0
		for (i = 0; i < n; i++) {
			cykTable[0][i] = "";
			for (Rule rule : grammar) {
				if (rule.getRight().size() == 1) {
					if (wordCharArray[i] == rule.getRight().get(0).getCharacter()) {
						cykTable[0][i] = cykTable[0][i] + rule.getLeft().toString();
					}
				}
			}
		}

		// initCykTable row = 1..n-1
		for (i = 1; i < n; i++) {
			for (j = 0; j < n; j++) {
				cykTable[i][j] = "";
			}
		}

		// do algorithm
		for (i = 1; i < n; i++) {
			for (j = 0; j < n - i; j++) {
				// calculate Table
				cykTable[i][j] = "";
				for (k = 0; k < i; k++) {
					for (Rule rule : grammar) {
						if (rule.getRight().size() == 2) {
							leftNonTerminal = rule.getRight().get(0).getCharacter();
							rightNonTerminal = rule.getRight().get(1).getCharacter();

							tmpString = cykTable[k][j];
							for (y = 0; y < tmpString.length(); y++) {
								if (leftNonTerminal == tmpString.charAt(y)) {
									tmpString = cykTable[j + k][i - k];
									System.out.println(i + "," + j + ":" + tmpString);
									for (z = 0; z < tmpString.length(); z++) {
										if (rightNonTerminal == tmpString.charAt(z)) {
											cykTable[i][j] = cykTable[i][j]
													+ rule.getLeft().toString();
										}
									}
								}
							}
						}
					}
				}
			}
		}

		// nur zur ausgabe
		System.out.println("\n---TABLE---\n");
		for (i = 0; i < cykTable.length; i++) {
			System.out.format("%-2d: ", i);
			for (j = 0; j < cykTable.length - i; j++) {
				String tmp = !cykTable[i][j].isEmpty() ? cykTable[i][j] : "X";
				System.out.format("%-3s", tmp + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("-----------\n");

		// is S in cykTable[n-1][0]?
		tmpString = cykTable[n - 1][0];
		System.out.println("zu testendes Feld in der Tabelle: " + tmpString);
		for (i = 0; i < tmpString.length(); i++) {
			if (tmpString.charAt(i) == 'S') {
				return true;
			}
		}

		return false;
	}

	@Override
	public String[][] getTable() {
		return cykTable;
	}

	@Override
	public void save(File file) throws FileNotFoundException, IOException {
		XMLUtil.output(grammar.toXml(), file);
	}

	@Override
	public void load(File file) throws JDOMException, IOException,
			GrammarParseException {
		this.grammar.parse(XMLUtil.read(file));

		fireModelChanged();
	}

	@Override
	public boolean checkGrammar() {

		// ÜBERPRÜFUNG AUF VOLLSTÄNDIGKEIT

		return false;
	}

	@Override
	public void addCYKModelListener(CYKModelListener listener) {
		listeners.add(listener);
	}

	private void fireModelChanged() {
		for (CYKModelListener l : listeners) {
			l.modelChanged();
		}
	}

	private void fireRuleAdded() {
		for (CYKModelListener l : listeners) {
			l.ruleAdded();
		}
	}

	public String getRandomWord(int length) throws GrammarIncompleteException,
			GrammarNoDeriveException, GrammarNoStartruleException {
		return new RandomWord(grammar, length).derive("S");
	}

}
