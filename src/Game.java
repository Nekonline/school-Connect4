public class Game extends Menu {
	
	Player[] playerTab;
	Grid mygrid;
	boolean[] playerPlayed;
	
	protected Game(Player[] playerTab,Grid mygrid) {
		this.playerTab = playerTab;
		this.mygrid = mygrid;
		this.playerPlayed = new boolean[playerTab.length];
		
		for(int i = 0; i < playerTab.length; i++)
			playerPlayed[i] = false;
		
	}
	
	protected int PlayGame(int firstPlayer) {
		int column, i;
		int line = -1;
		mygrid.displayGrid();
		
		for (i = firstPlayer; i < this.playerTab.length; i = (i+1)%this.playerTab.length) {
			column = -1;
			
			while (column == -1){
				
				try{
					column = this.playerTab[i].Play(this.mygrid.getWidth());
					if (column == -1)
						return -2;
					line = mygrid.insertToken(column, this.playerTab[i].GetToken());
					mygrid.displayGrid();
					
					Log("Joueur "+(i+1)+" joue "+(column+1)+"\n");
					
					if (mygrid.checkWin(line, column, this.playerTab[i].GetToken()) == 1) {
						System.out.println(this.playerTab[i].GetNickname() + " won !");
						return i;
					}
					if (mygrid.CheckEquality() == 1) {
						System.out.println( "Equality :( !");
						return -1;
					}
					
				} catch(HumanPlayIntException err) {
					System.out.println(err);
					Log("Erreur colonne non valide " + err.GetInt() +"\n");
				} catch(HumanPlayStrException err) {
					System.out.println(err);
					Log("Erreur saisie colonne "+ err.GetString() +"\n");					
				} catch(GridInsertException err) {
					System.out.println(err);
					Log("Erreur colonne pleine " + (column +1) +"\n");
				}
			}
			
		}
		// never reached
		return i;
		
	}

}
