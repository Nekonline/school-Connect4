import java.util.Scanner; // mettre dans Menu.java après

public class Human extends Player {
	
	protected int Play(int size) throws BadInput {
		
		Scanner sc = new Scanner(System.in);
		
		try {
			System.out.println("Input column :");
			int pos = sc.nextInt();
			if (pos<size && pos>=0) {
				sc.close();
				return pos;
			} else
				sc.close();
				throw new BadInput(pos);
		} finally {
			sc.close();
		}
		
	}
	
	protected Human(String nickname) {
		this.nickname = nickname;
	}
	
}