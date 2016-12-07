import java.util.Scanner;

public class Menu {
	
	static Scanner sc = new Scanner(System.in);
	
	
	protected static Player SetPlayer(int i) throws SetPlayerException {
		
		String input, pseudo = "";
		String[] result;
		int y = 0;
		
		System.out.println("Player " + (i+1) + " ?");
		input = sc.nextLine();
		result = input.split("\\s");
		
		if (result.length == 1)
			throw new SetPlayerException(i);
		
		for (int x = 0; x<result.length; x++) {
			if (x == 0) {
				if (result[x].equals("ia"))
					y = 1;
			
				else if (result[x].equals("humain"))
					y = 2;
				
				else
					throw new SetPlayerException(i+1);
			}
			
			else
				pseudo = pseudo.concat(result[x] + " ");
			
		}
		
		if (y == 1)
			return new Ai(pseudo);
		else
			return new Human(pseudo);
		
	}
	
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to -Qu'on n'est fort !- \n (Connect4 loliloul)");
		
		int nbPlayer = 2, i = 0;
		Player[] pTable = new Player[nbPlayer];
		
		while (i < nbPlayer) {
			try {
				pTable[i] = SetPlayer(i);
				System.out.println(pTable[i].getNickname());
				i++;
			} catch(SetPlayerException err) {
				System.out.println(err);
			}
		}
		
	}

}
