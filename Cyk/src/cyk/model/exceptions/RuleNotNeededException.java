package cyk.model.exceptions;

@SuppressWarnings("serial")
public class RuleNotNeededException extends Exception {
	public RuleNotNeededException() {
		super("Rule is unnecessary\n");
	}
}
