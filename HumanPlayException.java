public class HumanPlayException extends RuntimeException {

	private int badValue;
	
	public HumanPlayException(int badValue) {
		super("You input a out of range value : "+badValue+". It's not allowed");
		this.badValue = badValue;
	}

}