package main;

public class PlayerPlayIntException extends RuntimeException {
	
	private int badValue;
	
	public PlayerPlayIntException(int badValue) {
		super("Your input : " +badValue+ " is out of range !");
		this.badValue = badValue;
	}
	
}
