public class SetPlayerException extends RuntimeException {
	private int badPlayer;
	
	public SetPlayerException(int badPlayer) {
		super("The type for the player " + (badPlayer) + " doesn't exist or your alias is invalid !");
		this.badPlayer = badPlayer;
	}
}
