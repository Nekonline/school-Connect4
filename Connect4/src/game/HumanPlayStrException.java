package game;

public class HumanPlayStrException extends RuntimeException {

	private String str;
	
	public String getString(){
		return this.str;
	}
	
	public HumanPlayStrException(String str) {
		super("Your input is not an integer : "+ str +". It's not allowed");
		this.str = str;
	}

}