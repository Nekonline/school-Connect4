public abstract class Player{
	
	protected String nickname;
	protected Token token;
	
	protected String GetNickname() { return this.nickname; }
	protected Token GetToken() { return this.token; }
	
	protected void SetNickname(String newnickname) throws SetNicknameException {
		if (nickname.length() >= 1)
			this.nickname = newnickname;
		else
			throw new SetNicknameException(newnickname); 
	}
	
	protected abstract int Play(int size);
	
}