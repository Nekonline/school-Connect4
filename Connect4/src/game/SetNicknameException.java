package game;

public class SetNicknameException extends RuntimeException {

	private String str;
	
	public String getString(){
		return this.str;
	}
	
	public SetNicknameException(String str) {
		super("Alias : "+ str +" is invalid");
		this.str = str;
	}

}
