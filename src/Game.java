public class Game extends Menu {
	
	/* à modifier bite ! */
	
	public static void main(String[] args) {
		
		Human p1 = new Human("toto");
		Human p2 = new Human("titi");
		int i = 0;
		int j = 0;
		int k=-1; 
		Grid mygrid = new Grid(7,5); 
		mygrid.initGrid();
		mygrid.displayGrid();

		
		while (true) {
			i = -1;
			j = -1;
			// Player 1
			
			while(i<0){
				try{
					i = p1.Play(7);
					k = mygrid.insertToken(i, 't');
				} catch(HumanPlayException err) {
					System.out.println(err);
					i = -1;
				}  catch(java.util.InputMismatchException err) {
					System.out.println(err);
					p1.nextScanner();
				} 
				catch(GridInsertException err) {
					System.out.println(err);
					i = -1;
				}
			}
			System.out.println("toto a joué " + i);
			mygrid.displayGrid();
			if (mygrid.checkWin(k, i, 't') == 1) {
				System.out.println("toto won !");
				break;
			}
			
			// Player 2
			while(j<0){
				try{
					j = p2.Play(7);
					k = mygrid.insertToken(j, 'o');
				} catch(HumanPlayException err){
					System.out.println(err);
					j = -1;
				} catch(java.util.InputMismatchException err){
					System.out.println(err);
					p2.nextScanner();
				} 
				catch(GridInsertException err){
					System.out.println(err);
					j = -1;
				}
			}
			
			
			
			System.out.println("titi a joué " + j);
			mygrid.displayGrid();
			if (mygrid.checkWin(k, j, 'o') == 1) {
				System.out.println("titi won !");
				break;
			}
		}
		
		
		p2.closeScanner();
		p1.closeScanner();
		return;
	}
	
}