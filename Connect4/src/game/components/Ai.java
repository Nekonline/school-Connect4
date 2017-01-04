package game.components;

public class Ai extends Player {
	
	// AI object constructor
	public Ai(String nickname,Token token) {
		this.nickname = nickname;
		this.token = token;
	}
	
	// The AI plays a number between ]0, size[
	public int play(int size) {
		return (int)(Math.random() * size );
	}
	
}