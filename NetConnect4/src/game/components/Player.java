package game.components;

import main.PlayerPlayIntException;
import main.PlayerPlayStrException;

import java.util.Scanner;

public class Player {

	private String nickname;
	private Token token;

	public String getNickname() { return this.nickname; }
	public Token getToken() { return this.token; }
	
	// Player constructor
	public Player(String nickname, Token token) {
		this.nickname = nickname;
		this.token = token;
	}

	public int play(int size) throws PlayerPlayIntException, PlayerPlayStrException {
		System.out.println(this.nickname + " input a column :");
		Scanner sc = new Scanner(System.in);
		if (sc.hasNextInt() == false) {
			String str = sc.nextLine();
			if (str.length() == 0)
				str = sc.nextLine();
			throw new PlayerPlayStrException(str);
		} else {
			int toPlay = sc.nextInt();
			if (1 > toPlay || toPlay > size)
				throw new PlayerPlayIntException(toPlay);
			return toPlay - 1;
		}
	}

}