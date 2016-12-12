import java.util.Scanner;

public class Human extends Player {
	
	Scanner sc = new Scanner(System.in);
	
	protected Human(String nickname, Token token) {
		this.nickname = nickname;
		this.token = token;
	}
	
	protected int Play(int size) throws HumanPlayException {
		System.out.println("Input column :");
		int pos = this.sc.nextInt();	
		
		if (0 > pos || pos > size)
			throw new HumanPlayException(pos);
		return pos;
	}

	protected void nextScanner() {
		this.sc.next();
	}
	
	protected void closeScanner() {
		this.sc.close();
	}
	
}