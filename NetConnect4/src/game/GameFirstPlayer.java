package game;

import game.components.*;

import network.Client;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import main.GridInsertException;
import main.PlayerPlayIntException;
import main.PlayerPlayStrException;

public class GameFirstPlayer extends Game {
	
	private Player player;
	private boolean finished = false;
	private boolean read = false;
	
	// Constructor
	public GameFirstPlayer(Client client) {
		this.isConnected = true;
		this.client = client;
		this.player = new Player(this.client.getNickname(), this.tokenTab[1]);
	}
	
	// This method is required when the player is the launcher of the game 
	public int playGame() {	
		Token tokenAdv = this.tokenTab[0];
		Socket SockClient = this.client.getSock();
		this.grid.initGrid();
		this.grid.displayGrid();
		while (this.finished == false) { // Check if the game is finished
			// Check if the data are read correctly and if the other is always connected
			if (this.read == false && this.isConnected == true) {
				try{
					this.reader = new BufferedInputStream(SockClient.getInputStream());
					
					String toRead = read(); // Data are read
					if (toRead.toUpperCase().equals("QUIT")) { // Check if the other player quit the game
						System.out.println(this.tabNickname[1] + " quit the game.");
						this.isConnected = false;
					} else { // Display the token where the other player put it
						int column = Integer.parseInt(toRead); // Convert the string in integer to use the method insertToken
						int line = this.grid.insertToken(column, tokenAdv);
						this.grid.displayGrid();
						
						for (int k = 0; k < 2; k ++) { // Check if one of the players win the game
							if (this.grid.checkWin(line, column, this.tokenTab[k], this.nbToken2Win) == 1) {
								System.out.println(this.tabNickname[1] + " won !");
								this.finished = true;
							}
						}
						
						// Check if the grid is full
						if (this.grid.checkEquality() == 1) {
							System.out.println("Equality !");
							this.finished = true;
						}
					}
					
					this.read = true;
					
				} catch(GridInsertException err) {
					System.out.println(err);
				} catch (IOException err) {
					err.printStackTrace();
				}
			}
			
			try {
				this.writer = new PrintWriter(SockClient.getOutputStream());
				
				int column = this.player.play(this.grid.getWidth());
				int line = this.grid.insertToken(column, this.player.getToken());
				this.grid.displayGrid();
				
				if (isConnected == true) { // If the other player is connected, the data are sent
					String toSend = Integer.toString(column);
					this.writer.write(toSend);
					this.writer.flush();
				}
				
				for (int k = 0; k < 2; k ++) {
					if (this.grid.checkWin(line, column, this.tokenTab[k], this.nbToken2Win) == 1) {
						System.out.println("You won !");
						this.finished = true;
					}
				}
				
				if (this.grid.checkEquality() == 1) {
					System.out.println("Equality !");
					this.finished = true;
				}
				
				this.read = false;
	
			} catch(PlayerPlayIntException err) {
				System.out.println(err);
			} catch(PlayerPlayStrException err) {
				if (err.getString().equals("sortir")) {
					System.out.println("Bye !");
					this.writer.write("QUIT");
					this.writer.flush();
					this.finished = true;
				} else
					System.out.println(err);				
			} catch(GridInsertException err) {
				System.out.println(err);
			} catch (IOException err) {
				err.printStackTrace();
			}
		}
		return 0;
	}
	
	// This method share the nicknames of the different players
	public void shareNickname (String nickname) {
		this.tabNickname[0] = nickname;
		try{ // In this case the nickname of the current player is sent first
			this.writer = new PrintWriter(this.client.getSock().getOutputStream());
			this.writer.write(nickname);
			this.writer.flush();
			// And after the nickname of the other player is read
			this.reader = new BufferedInputStream(this.client.getSock().getInputStream());
			this.tabNickname[1] = read();
			System.out.println("You play against" + this.tabNickname[1] + ".");
		} catch (IOException err) {
			err.printStackTrace();
		}
	}

}
