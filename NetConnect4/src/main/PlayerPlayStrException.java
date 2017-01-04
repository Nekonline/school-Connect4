package main;

public class PlayerPlayStrException extends RuntimeException {
	
	private String badValue;
	
	public String getString() { return this.badValue; }
	
	public PlayerPlayStrException(String badValue) {
		super("Your input : " +badValue+ " is not allowed !");
		this.badValue = badValue;
	}
	
}
