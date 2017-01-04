package main;

public class ChooseStrException extends RuntimeException {

	private String badValue;
	
	public String getString() { return this.badValue; }
	
	public ChooseStrException(String badValue) {
		super("Your input : " +badValue+ " is not allowed !");
		this.badValue = badValue;
	}
	
}
