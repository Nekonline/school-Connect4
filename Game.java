public class Game {
	
	public static void main(String[] args) {
		Human p1 = new Human("toto");
		Ai p2 = new Ai("titi");
		int i = 0;
		int j = 0;
		
		try {
			i = p1.Play(7);
		} catch (BadInput err) {
			System.out.println(err);
			i = p1.Play(7);
		} finally {
			System.out.println("toto a joué " + i);
		}
		
		try {
			j = p2.Play(7);
			
		} catch (BadInput err) {
			System.out.println(err);
			j = p2.Play(7);
		} finally {
			System.out.println("titi a joué " + j);
		}
			
	}
	
}