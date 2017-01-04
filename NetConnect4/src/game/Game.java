package game;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import network.Client;

import game.components.Token;
import game.components.Grid;

public abstract class Game {

	protected Client client;
	protected Grid grid = new Grid(7, 5);
	protected Token[] tokenTab = {new Token('X'), new Token('O')};
	protected PrintWriter writer;
	protected BufferedInputStream reader;
	protected int nbToken2Win = 4;
	protected boolean isConnected;
	protected String[] tabNickname = new String[2];
	
	// In order to the type of the player, the method is defined differently
	public abstract int playGame();
	
	// The method implemented to read data through the buffer
	// Find on : https://openclassrooms.com/courses/java-et-la-programmation-reseau/les-sockets-cote-serveur
	protected String read() {
		String str = "";
		int stream = 0;
		byte[] b = new byte[4096];
		try {
			stream = this.reader.read(b);
		} catch (IOException err) {
			err.printStackTrace();
		}
		str = new String(b, 0, stream);
		return str;
	}
	
	// In order to the type of the player, the method is defined differently
	public abstract void shareNickname(String nickname);
		
	
}
