public abstract class Player {
	
	protected String nickname;
	
	protected String GetNickname() { return this.nickname; }
	protected void SetNickname(String newnickname) { this.nickname = newnickname; }
	
	protected abstract int Play(int size);
	
}