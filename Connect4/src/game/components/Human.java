package game.components;

import game.HumanPlayIntException;
import game.HumanPlayStrException;

import java.util.Scanner;

public class Human extends Player {

	private Scanner sc = new Scanner(System.in);

	// Human object constructor
	public Human(String nickname, Token token) {
		this.nickname = nickname;
		this.token = token;
	}

	// Human has to input a number between [1,size] to play
	public int play(int size) throws HumanPlayIntException, HumanPlayStrException {
		System.out.println(this.nickname + "input a column :");
		int pos;

		// Check if the scanner's next token is an integer
		if (this.sc.hasNextInt() == false) {
			String str = this.sc.nextLine();
			if (str.length() == 0)
				str = this.sc.nextLine();
			if (str.equals("sortir")) // Check if the string is equal to "sortir"
				return -1;
			throw new HumanPlayStrException(str);
		} else {
			pos = this.sc.nextInt();
			if (1 > pos || pos > size) { // Check if the number written is between [1,size]
				throw new HumanPlayIntException(pos);
			}
			return pos - 1;
		}

	}

}