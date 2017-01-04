package game.components;

import game.SetNicknameException;

public abstract class Player {
	
	protected String nickname;
	protected Token token;
	
	public String getNickname() { return this.nickname; }
	public Token getToken() { return this.token; }
	
	// Check if the nickname is not the empty string
	protected void setNickname(String newnickname) throws SetNicknameException {
		if (nickname.length() >= 1)
			this.nickname = newnickname;
		else
			throw new SetNicknameException(newnickname); 
	}
	
	// According to the different type of player, the method is implemented differently
	public abstract int play(int size);
	
}