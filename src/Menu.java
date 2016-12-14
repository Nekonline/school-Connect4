import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import java.io.*;

import org.json.*;


public class Menu {
	
	static Scanner sc = new Scanner(System.in);
	
	static Token[] tokenTab = {new Token('X'), new Token('O'), new Token('Z'), new Token('N')};
	
	static File file = new File("log.txt");
	
	protected static void Log(String str2Write) {
	    
		try {
		  // creates a FileWriter Object
		  FileWriter writer = new FileWriter(file, true); 
		  
		  // Writes the content to the file
		  writer.write(str2Write); 
		  writer.flush();
		  writer.close();
			      
		} catch (IOException err) {
			System.out.println(err);
		}

	}
	
	// Asked for type + nickname player 
	// according to the number of player (<4) set in configuration.txt
	protected static Player SetPlayer(int i) throws SetPlayerException {
		
		String input, pseudo = "";
		String[] result;
		int y = 0;
		
		System.out.println("Player " + (i+1) + " ?");
		input = sc.nextLine();
		result = input.split("\\s");
		
		// Error : no nickname has been input 
		if (result.length == 1)
			throw new SetNicknameException(i+1);
		
		// Read through the scanner
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
		if (y == 1) {
			Log("Joueur "+(i+1)+" est ia "+ pseudo +"\n");
			return new Ai(pseudo, tokenTab[i]);
		} else {
			Log("Joueur "+(i+1)+" est humain "+ pseudo +"\n");
			return new Human(pseudo, tokenTab[i]);
		}
	}
	
	// Find on http://stackoverflow.com/questions/326390/how-do-i-create-a-java-string-from-the-contents-of-a-file
	static String readFile(String path, Charset encoding)  throws IOException {
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
	}
	
	
	public static void main(String[] args) {
		
		System.out.println("*****************\n** Connect  4  **\n*****************\n Welcome to the game !");
		// Default configuration
		int nbPlayer = 0, gridWidth = 0, gridLenght = 0, i, nbGame2Win = 0;
		
	    // creates the file
		try {
			file.createNewFile();
			PrintWriter writer = new PrintWriter(file);
			writer.print("");
			writer.close();
		} catch (IOException err) {
			System.out.println(err);
		}
	    
		// Read data from configuration.txt
		try {
			String str = readFile("configuration.txt", StandardCharsets.UTF_8);
			JSONObject obj = new JSONObject(str);
			nbPlayer = obj.getInt("nbPlayer");
			nbGame2Win = obj.getInt("nbGame2Win");
			gridWidth = obj.getInt("gridWidth");
			gridLenght = obj.getInt("gridLength");
		} catch(JSONException err) {
			System.out.println(err);
		} catch(IOException err) {
			System.out.println(err);
		}
		
		Player[] pTable = new Player[nbPlayer];
		int[] nbWinByplayer = new int[nbPlayer];
		for (i = 0; i < nbPlayer; i ++)
			nbWinByplayer[i] = 0;
		i = 0;
		
		// Create Player
		while (i < nbPlayer) {
			try {
				pTable[i] = SetPlayer(i);
				System.out.println(pTable[i].GetNickname());
				i++;
			} catch(SetPlayerException err) {
				Log("Erreur saisie Joueur "+(i+1) +"\n");
				System.out.println(err);
			}
		}
		
		// Create Grid
		Grid mygrid = new Grid(gridWidth, gridLenght);
		mygrid.initGrid();
		
		// Create Game
		Game myGame = new Game(pTable, mygrid);
		
		// Play Game
		i = -1;
		int j;
		do {
			Log("Manche commence\n");
			i = (i+1)%nbPlayer;
			j = myGame.PlayGame(i);
			// Someone won
			if (j==-1) {// Equality
				Log("Egalite\n");
			} else if (j== -2) { // "sortir"
				System.out.println(" Bye !");
				Log("Partie finie\n");
				return ;
				} else { 
				i = j;
				nbWinByplayer[i] ++;
				Log("Joueur " +(i+1)+" gagne\n");
			}
			Log("Score ");
			for (int k=0; k<nbPlayer; k++){
				if (k == nbPlayer-1 )
					Log(nbWinByplayer[k] +"\n");
				else
					Log(nbWinByplayer[k] +" - ");
			}
			mygrid.initGrid();
		} while (nbWinByplayer[i] != nbGame2Win);
		
		System.out.println(pTable[i].GetNickname() +" won the game ! Congratulations :)");
		Log("Partie finie\n");
		return ;
	}
	
}

