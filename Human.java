import java.util.Scanner; // mettre dans Menu.java après

public class Human extends Player {
	
	Scanner sc = new Scanner(System.in);
	
	protected int Play(int size) throws HumanPlayException{
		System.out.println("Input column :");
		int pos = this.sc.nextInt();	
		
		if (0 > pos || pos > size)
			throw new HumanPlayException(pos);
		return pos;
	}
	
	//	protected int Play(int size) throws HumanPlayException{
//		
//		int pos = -1;
//		while (0 > pos || pos > size) {
//			System.out.println("Input column :");
//			pos = this.sc.nextInt();	
//			if (0 > pos || pos > size)
//				throw new HumanPlayException(pos);
//		}
//		return pos;
//	}
	
	protected Human(String nickname) {
		this.nickname = nickname;
	}
	
	protected void closeScanner() {
		this.sc.close();
	}
	
	protected void nextScanner() {
		this.sc.next();
	}
}