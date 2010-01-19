package cyk.model.exceptions;

@SuppressWarnings("serial")
public class RuleHasNoEscapeException extends Exception {
	public RuleHasNoEscapeException() {
		super("Rule has no escape!\n");
	}
}

