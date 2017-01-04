package game;

import game.components.Player;
import game.components.Token;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import main.GridInsertException;
import main.PlayerPlayIntException;
import main.PlayerPlayStrException;

import network.Client;

public class GameSecondPlayer extends Game {
	
	private Player player;
	private boolean finished = false;
	
	public GameSecondPlayer(Client client) {
		this.isConnected = true;
		this.client = client;
		this.player = new Player(this.client.getNickname(), this.tokenTab[0]);
	}
	
	public int playGame() {
		Token tokenAdv = this.tokenTab[1];
		Socket SockClient = this.client.getSock();
		this.grid.initGrid();
		this.grid.displayGrid();
		while (this.finished == false) { // Check if the game is finished
			try{
				this.writer = new PrintWriter(SockClient.getOutputStream());
				this.reader = new BufferedInputStream(SockClient.getInputStream());
				
				int column = this.player.play(this.grid.getWidth());
				int line = this.grid.insertToken(column, this.player.getToken());
				this.grid.displayGrid();
				
				for (int k = 0; k < 2; k ++) { // Check if one of the players win the game
					if (this.grid.checkWin(line, column, this.tokenTab[k], this.nbToken2Win) == 1) {
						System.out.println("You won !");
						this.finished = true;
					}
				}
				
				// Check if the grid is full
				if (this.grid.checkEquality() == 1) {
					System.out.println("Equality !");
					this.finished = true;
				}
				
				if (this.isConnected == true) { // If the other player is connected, the data are sent
					String toSend = Integer.toString(column);
					this.writer.write(toSend);
					this.writer.flush();
					
					String toRead = read();
					if (toRead.toUpperCase().equals("QUIT")) { // Check if the other player quit the game
						System.out.println(this.tabNickname[1] + " quit the game.");
						this.isConnected = false;
					} else {
						column = Integer.parseInt(toRead); // Convert the string in integer to use the method insertToken
						line = this.grid.insertToken(column, tokenAdv);
						this.grid.displayGrid();
						
						for (int k = 0; k < 2; k ++) {
							if (this.grid.checkWin(line, column, this.tokenTab[k], this.nbToken2Win) == 1) {
								System.out.println(this.tabNickname[1] + " won !");
								this.finished = true;
							}
						}
						
						if (this.grid.checkEquality() == 1) {
							System.out.println( "Equality !");
							this.finished = true;
						}
					}
				}
				
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
		try{ // In this case the nickname of the other player is read first
			this.reader = new BufferedInputStream(this.client.getSock().getInputStream());
			this.tabNickname[1] = read();
			System.out.println("You play against" + this.tabNickname[1] + ".");
			// And after the nickname of the current player is sent
			this.writer = new PrintWriter(this.client.getSock().getOutputStream());
			this.writer.write(nickname);
			this.writer.flush();
		} catch (IOException err) {
			err.printStackTrace();
		}
	}

}
