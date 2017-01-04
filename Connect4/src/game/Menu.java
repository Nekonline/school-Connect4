package game;

import game.components.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;

import org.json.JSONException;
import org.json.JSONObject;


public class Menu {
	
	static Scanner sc = new Scanner(System.in);
	
	static Token[] tokenTab = {new Token('X'), new Token('O'), new Token('Z'), new Token('N')};
	
	static File file = new File("txt/log.txt");
	
	protected static void log(String str2Write) {
	    
		try {
		  // Creates a FileWriter Object
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
	// According to the number of player (<4) set in configuration.txt
	protected static Player setPlayer(int i) throws SetPlayerException, SetNicknameException {
		
		String input, pseudo = "";
		String[] result;
		int y = 0;
		
		System.out.println("Player " + (i+1) + " ?");
		input = sc.nextLine();
		result = input.split("\\s");
		
		// Error : no nickname has been input 
		if (result.length == 1) {
			throw new SetNicknameException(result[0]);
		}
		
		// Read through the scanner to find the type and the nickname of the player
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
			log("Joueur "+(i+1)+" est ia "+ pseudo +"\n");
			return new Ai(pseudo, tokenTab[i]);
		} else {
			log("Joueur "+(i+1)+" est humain "+ pseudo +"\n");
			return new Human(pseudo, tokenTab[i]);
		}
	}
	
	// Find on http://stackoverflow.com/questions/326390/how-do-i-create-a-java-string-from-the-contents-of-a-file
	static String readFile(String path, Charset encoding)  throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	
	
	public static void main(String[] args) {
		System.out.println("*****************\n**  Connect 4  **\n*****************\nWelcome to the game !");
		// Default configuration
		int nbPlayer = 0, gridWidth = 0, gridLenght = 0, i, nbGame2Win = 0, nbToken2Win = 0;
		
	    // Creates the file
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
			String str = readFile("txt/configuration.txt", StandardCharsets.UTF_8);
			JSONObject obj = new JSONObject(str);
			nbPlayer = obj.getInt("nbPlayer");
			nbGame2Win = obj.getInt("nbGame2Win");
			nbToken2Win = obj.getInt("nbToken2Win");
			gridWidth = obj.getInt("gridWidth");
			gridLenght = obj.getInt("gridLength");
		} catch(JSONException err) {
			System.out.println(err);
		} catch(IOException err) {
			System.out.println(err);
		}
		
		// Create array of player
		Player[] pTable = new Player[nbPlayer];
		int[] nbWinByplayer = new int[nbPlayer];
		for (i = 0; i < nbPlayer; i ++)
			nbWinByplayer[i] = 0;
		i = 0;
		
		
		// Create Player
		while (i < nbPlayer) {
			try {
				pTable[i] = setPlayer(i);
				if (pTable[i].getNickname().equals("sortir ")) { // If the player input a good
					System.out.println("Bye !"); // type but want to exit just after
					log("Partie finie\n"); // i.e he puts for example : "ia sortir"
					return;
				}
				i++;
			} catch(SetPlayerException err) {
				log("Erreur saisie Joueur "+(i+1) +"\n");
				System.out.println(err);
			} catch(SetNicknameException err) {
				if (err.getString().equals("sortir")) { // If the player input "sortir" 
					System.out.println("Bye !");
					log("Partie finie\n");
					return;
				} else {
					log("Erreur saisie Joueur "+(i+1) +"\n");
					System.out.println(err);
				}
			}
		}
		
		// Create Grid
		Grid mygrid = new Grid(gridWidth, gridLenght);
		mygrid.initGrid();
		
		// Create Game
		Game myGame = new Game(pTable, mygrid, nbToken2Win);
		
		// Play Game
		i = -1;
		int j;
		do {
			log("Manche commence\n");
			i = (i+1)%nbPlayer;
			j = myGame.playGame(i);
			switch (j) {
			case -1: // Equality
				log("Egalite\n");
				break;
			case -2: // "sortir"
				System.out.println("Bye !");
				log("Partie finie\n");
				return;
			case -5:
				System.out.println("Big problem in the algorithm that we have never met !");
				return;
			default : // Someone won
				i = j;
				nbWinByplayer[i] ++;
				log("Joueur " +(i+1)+" gagne\n");
				break;
			}
			log("Score "); // Print the score in the file log.txt
			for (int k=0; k<nbPlayer; k++) {
				if (k == nbPlayer-1 )
					log(nbWinByplayer[k] +"\n");
				else
					log(nbWinByplayer[k] +" - ");
			}
			mygrid.initGrid(); // When the previous game is finished but the number of game
			// to win is not reach, a new game is launched
		} while (nbWinByplayer[i] != nbGame2Win);
		
		System.out.println(pTable[i].getNickname() +" won the game ! Congratulations :)");
		log("Partie finie\n");
		return;
	}
	
}

