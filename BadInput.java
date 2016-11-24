public class BadInput extends RuntimeException {

	private int badValue;
	
	public BadInput(int badValue) {
		super("You input a out of range value : "+badValue+". It's not allowed");
		this.badValue = badValue;
	}

}