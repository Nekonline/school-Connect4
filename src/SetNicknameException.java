public class SetNicknameException extends RuntimeException {

	private int i;
	
	public SetNicknameException(int i) {
		super("Player " + i +" : your alias is invalid");
		this.i = i;
	}
	
	public SetNicknameException(String str) {
		super("Alias : "+ str +" is invalid");
	
	}
	

}
