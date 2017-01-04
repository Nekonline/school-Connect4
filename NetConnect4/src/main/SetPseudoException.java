package main;

public class SetPseudoException extends RuntimeException {
	
	private String badValue;
	
	public String getString() { return this.badValue; }
	
	public SetPseudoException(String badValue) {
		super("Your input : " +badValue+ " is not allowed !");
		this.badValue = badValue;
	}
	
}
