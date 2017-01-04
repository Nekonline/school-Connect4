package main;

public class ChooseIntException extends RuntimeException {

	private int badValue;
		
	public ChooseIntException(int badValue) {
		super("Your input : " +badValue+ " is not in list !");
		this.badValue = badValue;
	}
	
}

