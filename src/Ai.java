public class Ai extends Player {
	
	protected Ai(String nickname,Token token) {
		this.nickname = nickname;
		this.token = token;
	}
	
	protected int Play(int size) {
		return (int)(Math.random() * size);
	}
	
}