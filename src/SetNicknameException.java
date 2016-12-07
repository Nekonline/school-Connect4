public class SetNicknameException extends RuntimeException {

	private String badNickname;
	
	public SetNicknameException(String badNickname) {
		super("Your alias is invalid");
		this.badNickname = badNickname;
	}

}
