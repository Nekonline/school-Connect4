package game;

import game.components.*;

public class Game extends Menu {
	
	private Player[] playerTab;
	private Grid mygrid;
	private int nbToken2Win;
	
	// Game object constructor
	protected Game(Player[] playerTab,Grid mygrid, int nbToken2Win) {
		this.playerTab = playerTab;
		this.mygrid = mygrid;
		this.nbToken2Win = nbToken2Win;
	}
	
	// Method which implements the game proceeding 
	protected int playGame(int firstPlayer) {
		int column, i;
		int line = -1;
		this.mygrid.displayGrid();
		
		for (i = firstPlayer; i < this.playerTab.length; i = (i+1)%this.playerTab.length) {
			column = -1;
			
			while (column == -1){
			// If the player input is incorrect or not equal to "sortir"
			// the player has to input something right in order the next player can play	
				try{
					column = this.playerTab[i].play(this.mygrid.getWidth());
					if (column == -1) // Check if the player wrote "sortir"
						return -2;
					line = this.mygrid.insertToken(column, this.playerTab[i].getToken());
					this.mygrid.displayGrid();
					
					log("Joueur "+(i+1)+" joue "+(column+1)+"\n");
					// Check if the player won the game
					if (this.mygrid.checkWin(line, column, this.playerTab[i].getToken(), this.nbToken2Win) == 1) {
						System.out.println(this.playerTab[i].getNickname() + " won !");
						return i;
					}
					
					// Check if the grid is full
					if (this.mygrid.checkEquality() == 1) {
						System.out.println("Equality !");
						return -1;
					}
					
				} catch(HumanPlayIntException err) {
					System.out.println(err);
					log("Erreur colonne non valide " + err.getInt() +"\n");
				} catch(HumanPlayStrException err) {
					System.out.println(err);
					log("Erreur saisie colonne "+ err.getString() +"\n");
				} catch(GridInsertException err) {
					System.out.println(err);
					log("Erreur colonne pleine " + (column +1) +"\n");
				}
			}
			
		}
		// never reached
		return -5;
	}
}
