package game;

public class HumanPlayIntException extends RuntimeException {

	private int badValue;
	
	public int getInt() {
		return this.badValue;
	}
	
	public HumanPlayIntException(int badValue) {
		super("You input a out of range value : "+badValue+". It's not allowed");
		this.badValue = badValue;
	}
}