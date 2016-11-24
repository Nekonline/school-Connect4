public class Ai extends Player {

	protected int Play(int size) {
		return (int)(Math.random() * size);
	}
	
	protected Ai(String nickname) {
		this.nickname = nickname;
	}
	
}