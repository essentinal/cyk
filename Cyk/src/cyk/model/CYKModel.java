package cyk.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.JDOMException;

import cyk.CYK;
import cyk.model.exceptions.GrammarIncompleteException;
import cyk.model.exceptions.GrammarIsNotInCnfException;
import cyk.model.exceptions.GrammarNoDeriveException;
import cyk.model.exceptions.GrammarNoStartruleException;
import cyk.model.exceptions.GrammarParseException;
import cyk.model.exceptions.RuleException;
import cyk.model.exceptions.RuleHasNoEscapeException;
import cyk.model.exceptions.RuleNotNeededException;
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

		if (CYK.DEBUG) {
			// FOR DEBUGGING ONLY
			try {
				grammar.add(new Rule("S->AA"));
				grammar.add(new Rule("A->AA"));
				grammar.add(new Rule("A->a"));
			} catch (RuleException e) {
			}
		}

	}

	@Override
	public void addRule() {
		try {
			grammar.add(0, new Rule("S->AA"));
			fireModelChanged();
			fireRuleAdded();
		} catch (RuleException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setRuleAt(Rule rule, int index) {
		if (!getRule(index).equals(rule)) {
			grammar.set(index, rule);
			grammar.sort();
			fireModelChanged();
		}
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
				for (k = 1; k <= i; k++) {
					for (Rule rule : grammar) {
						if (rule.getRight().size() == 2) {
							leftNonTerminal = rule.getRight().get(0).getCharacter();
							rightNonTerminal = rule.getRight().get(1).getCharacter();

							tmpString = cykTable[k - 1][j];
							if (CYK.DEBUG) {
								System.out.println(rule);
								System.out.println(i + "," + j + ":" + tmpString);
							}
							for (y = 0; y < tmpString.length(); y++) {
								if (leftNonTerminal == tmpString.charAt(y)) {
									tmpString = cykTable[i - k][j + k];
									for (z = 0; z < tmpString.length(); z++) {
										if (rightNonTerminal == tmpString.charAt(z)
												&& !cykTable[i][j].contains(rule.getLeft().toString())) {
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
		if (CYK.DEBUG) {
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
		}

		// is S in cykTable[n-1][0]?
		tmpString = cykTable[n - 1][0];
		if (CYK.DEBUG) {
			System.out.println("zu testendes Feld in der Tabelle: " + tmpString);
		}
		if (tmpString.contains("S")) {
			return true;
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
	public void checkGrammar() throws RuleNotNeededException, GrammarIsNotInCnfException, GrammarIncompleteException, RuleHasNoEscapeException {
		boolean s = false;
		
		for(Rule rule: grammar ) {
			if(rule.getLeft().getCharacter() == 'S') {
				s = true;
			}
		}
		
		if(!s) {
			throw new GrammarIncompleteException("Die Grammatik enthält keine Startregel.");
		}
		
		for(Rule rule: grammar ) {
			//Regeln der Form X->Y überprüfen   Y muss Terminalsymbol sein
			if(rule.getRight().size() == 1) {
				if(rule.getRight().get(0) instanceof NonTerminalSymbol) {
					throw new GrammarIsNotInCnfException("Die Grammatik enthält eine Regel der Form A->B.");
				}
			//Regeln der Form X->YZ überprüfen   Y und Z müssen Nichtterminalsymbole sein
			} else if(rule.getRight().size() == 2) {
				if(rule.getRight().get(0) instanceof TerminalSymbol || rule.getRight().get(1) instanceof TerminalSymbol) {
					throw new GrammarIsNotInCnfException("Die Grammatik enthält eine Regel der Form A->aA, A->Aa oder A->aa.");
				}
			//Gibt es weitere Regeln mit unzulässiger Form
			} else if(rule.getRight().size() == 0){
				throw new GrammarIsNotInCnfException("Die Grammatik enthält eine Regel, die keine rechte Seite hat.");
			} else {
				throw new GrammarIsNotInCnfException("Die Grammatik enthält eine Regel, die auf der rechten Seite mehr als 2 Zeichen hat.");
			}
		}
		
		NonTerminalSymbol tmpRuleRightLeft, tmpRuleRightRight;
		boolean a = false;
		
		//Überprüfung auf Sackgassenregeln
		for(Rule aktRule: grammar) {
			if(aktRule.getRight().size() == 2) {
				tmpRuleRightLeft = (NonTerminalSymbol) aktRule.getRight().get(0);
				tmpRuleRightRight = (NonTerminalSymbol) aktRule.getRight().get(1);
				a = false;
				for(Rule rules: grammar) {
					if(tmpRuleRightLeft.getCharacter() == rules.getLeft().getCharacter()) {
						a = true;
						break;
					}
				}
				if(!a) {
					throw new RuleHasNoEscapeException();
				}
				
				a = false;
				for(Rule rules: grammar) {
					if(tmpRuleRightRight.getCharacter() == rules.getLeft().getCharacter()) {
						a = true;
						break;
					}
				}
				if(!a) {
					throw new RuleHasNoEscapeException();
				}				
			}		
		}
		
		NonTerminalSymbol tmpRuleLeft;
		boolean b = false;
		
		//Überprüfung auf unnötige Regeln (Bsp.: S->A A->1 B->0 | B ist unnötig)
		for(Rule aktRule: grammar) {
			tmpRuleLeft = aktRule.getLeft();
			b = false;
			if(tmpRuleLeft.getCharacter() != 'S'){
				for(Rule rules: grammar) {
					if(rules.getRight().size() == 1) {
						if(rules.getRight().get(0).getCharacter() == tmpRuleLeft.getCharacter()){
							b = true;
							break;
						}
					} else if(rules.getRight().size() == 2){
						if(rules.getRight().get(0).getCharacter() == tmpRuleLeft.getCharacter() || 
								rules.getRight().get(1).getCharacter() == tmpRuleLeft.getCharacter()){
							b = true;
							break;
						}
					}
				}
				if(!b) {
					throw new RuleNotNeededException();
				}
			} 
		}			
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
