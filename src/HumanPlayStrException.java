public class HumanPlayStrException extends RuntimeException {

	private String str;
	
	public String GetString(){
		return this.str;
	}
	public HumanPlayStrException(String str) {
		super("Your input is not an integer : "+ str +". It's not allowed");
		this.str = str;
	}

}