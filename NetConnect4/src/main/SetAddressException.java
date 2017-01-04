package main;

public class SetAddressException extends RuntimeException {
	
	private String str;
	
	public String getString() { return this.str; }
	
	public SetAddressException(String str) {
		super("Your input : " +str+ " is not an address !");
		this.str= str;
	}

}
