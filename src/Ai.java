public class Ai extends Player {
	
	protected Ai(String nickname) {
		this.nickname = nickname;
	}
	
	protected int Play(int size) {
		return (int)(Math.random() * size);
	}
	
}