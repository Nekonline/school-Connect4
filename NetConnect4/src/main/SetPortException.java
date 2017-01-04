package main;

public class SetPortException extends RuntimeException {
	
	private String str;
	
	public String getString() { return this.str; }
	
	public SetPortException(String str) {
		super("Your input : " +str+ " is not a port number !");
		this.str = str;
	}

}
