public abstract class Player {
	
	protected String nickname;
	protected Token token;
	
	protected String getNickname() { return this.nickname; }
	
	protected void setNickname(String newnickname) throws SetNicknameException {
		if (nickname.length() >= 1)
			this.nickname = newnickname;
		else
			throw new SetNicknameException(newnickname); 
	}
	
	protected abstract int Play(int size);
	
}