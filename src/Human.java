import java.util.Scanner;

public class Human extends Player {
	
	Scanner sc = new Scanner(System.in);
	
	protected Human(String nickname, Token token) {
		this.nickname = nickname;
		this.token = token;
	}
	
	protected int Play(int size) throws HumanPlayIntException, HumanPlayStrException  {
		System.out.println("Input column :");
		int pos; // = this.sc.nextInt();
		
	   // check if the scanner's next token is an int
	   if (this.sc.hasNextInt() == false){
		   String str = this.sc.nextLine();
		   if (str.length() == 0)
			  str = this.sc.nextLine();
		   if (str.equals("sortir"))
				   return -1;
		   throw new HumanPlayStrException(str);
	   }
	   else {
		   pos = this.sc.nextInt();
		   if (1 > pos || pos > size)
				throw new HumanPlayIntException(pos);
		   return pos-1;
	   }	
	  
	}

	protected void nextScanner() {
		this.sc.next();
	}
	
	protected void closeScanner() {
		this.sc.close();
	}
	
}