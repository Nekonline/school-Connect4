public class Game extends Menu {
	
	/* à modifier! */
	Player[] playerTab;
	Grid mygrid;
	
	protected Game(Player[] playerTab,Grid mygrid) {
		this.playerTab = playerTab;
		this.mygrid = mygrid;		
	}
	
	protected int PlayGame(){
		int column;
		int line = -1;
		mygrid.displayGrid();
		while (true) {
			for(int i=0; i<this.playerTab.length; i++) {
				column = -1;
				while (column == -1){
					try{
				
					column = this.playerTab[i].Play(this.mygrid.getWidth());
					line = mygrid.insertToken(column, this.playerTab[i].GetToken());
					mygrid.displayGrid();
					if (mygrid.checkWin(line, column, this.playerTab[i].GetToken()) == 1) {
						System.out.println(this.playerTab[i].GetNickname() + " won !");
						return i;
					}
					} catch(HumanPlayException err) {
						System.out.println(err);
						column = -1;
					} catch(java.util.InputMismatchException err) {
						System.out.println(err);
		//					if(this.playerTab[i] instanceof Human) TODO
		//						this.playerTab[i].nextScanner();
					} catch(GridInsertException err) {
						System.out.println(err);
						column = -1;
					}
				}
				System.out.println(column);
			}

		}
	}
	
//	public static void main(String[] args) {
//		
//		Token t1 = new Token('X');
//		Token t2 = new Token('0');
//		
//		Human p1 = new Human("toto",t1);
//		Human p2 = new Human("titi",t2);
//		int i = 0;
//		int j = 0;
//		int k=-1; 
//		Grid mygrid = new Grid(7,5); 
//		mygrid.initGrid();
//		mygrid.displayGrid();
//
//		
//		while (true) {
//			i = -1;
//			j = -1;
//			// Player 1
//			
//			while(i<0){
//				try{
//					i = p1.Play(7);
//					k = mygrid.insertToken(i, 't'); // get token player
//				} catch(HumanPlayException err) {
//					System.out.println(err);
//					i = -1;
//				}  catch(java.util.InputMismatchException err) {
//					System.out.println(err);
//					p1.nextScanner();
//				} 
//				catch(GridInsertException err) {
//					System.out.println(err);
//					i = -1;
//				}
//			}
//			System.out.println("toto a joué " + i);
//			mygrid.displayGrid();
//			if (mygrid.checkWin(k, i, 't') == 1) {
//				System.out.println("toto won !");
//				break;
//			}
//			
//			// Player 2
//			while(j<0){
//				try{
//					j = p2.Play(7);
//					k = mygrid.insertToken(j, 'o');
//				} catch(HumanPlayException err){
//					System.out.println(err);
//					j = -1;
//				} catch(java.util.InputMismatchException err){
//					System.out.println(err);
//					p2.nextScanner();
//				} 
//				catch(GridInsertException err){
//					System.out.println(err);
//					j = -1;
//				}
//			}
//			
//			
//			
//			System.out.println("titi a joué " + j);
//			mygrid.displayGrid();
//			if (mygrid.checkWin(k, j, 'o') == 1) {
//				System.out.println("titi won !");
//				break;
//			}
//		}
//		
//		
//		p2.closeScanner();
//		p1.closeScanner();
//		return;
//	}
	
}