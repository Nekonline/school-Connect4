package main;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import network.Client;
import network.Server;

import game.*;

public class Main {
	
	private static Scanner sc = new Scanner(System.in);
	private static int choice = 0;
	private static String nickname;
	private static int pseudo = 1;
	private static Client client;
	private static Game game;
	
	// Ask to input a no empty nickname
	private static int setPseudo() throws SetPseudoException {
		System.out.println("Player set a nickname, please :");
		nickname = sc.nextLine();
		if (nickname.length() == 0) {
			throw new SetPseudoException(nickname);
		} else if (nickname.equals("sortir")) {
			throw new SetPseudoException(nickname);
		}
		return 0;
	}
	
	// Ask to input a number between [1, 3] to choose an option in the menu
	private static int choose() {
		if (sc.hasNextInt() == false) {
			String str = sc.nextLine();
			if (str.length() == 0)
				str = sc.nextLine();
			throw new ChooseStrException(str);
		} else {
			int choice = sc.nextInt();
			if (1 > choice || choice > 3)
				throw new ChooseIntException(choice);
			return choice;
		}
	}
	
	public static void main(String[] args) {
		System.out.println("*****************\n**  Connect 4  **\n*****************\nWelcome to the game !\n");
		
		while (pseudo == 1) {
			try {
				pseudo = setPseudo(); // Input a nickname for the player
			} catch (SetPseudoException err) {
				if (err.getString().equals("sortir")) {
					System.out.println("Bye !");
					return;
				} else {
					System.out.println(err);
				}
			}
		}
		
		while (choice == 0) {
			System.out.println(nickname + " press :\n1 - to create a game.\n2 - to join a game.\n3 - to exit.\n");
			try {
				choice = choose();
				switch (choice) { // Check your choice
				case 1: // You want to create a game
					Server server = new Server(); // So you are a server for a temporary time
					try {
						System.out.println("Wait your friend " + nickname + ".\nBut if you want to quit, please press : Ctrl+c\n");
						Socket sock = server.getSockServer().accept(); // When you accepted the other player connection
						client = new Client(sock, nickname); // You create a sock client with right parameters to have a link
						// with the other player and to exchange informations
						server.getSockServer().close(); // Close server socket because it is unused
						System.out.println("Your friend joined you !\nEnjoy !\n");
						game = new GameFirstPlayer(client); // You are the first player in this case
						break;
					} catch (IOException err) {
						err.printStackTrace();
					}
				case 2: // You chose to join a game
					client = new Client(nickname); // So you have to input the server informations
					// to be connected with the other player
					game = new GameSecondPlayer(client); // You are the second player in this case
					break;
				case 3: // You chose to quit
					System.out.println("Bye !");
					return;
				}
			} catch (ChooseStrException err) {
				if (err.getString().equals("sortir")) { // If you want to quit
					System.out.println("Bye !"); // By taping "sortir"
					return;
				} else {
					System.out.println(err);
					choice = 0;
				}
			} catch (ChooseIntException err) {
				System.out.println(err);
				choice = 0;
			}
		}

		game.shareNickname(nickname); // Exchange the nicknames of the different players
		game.playGame(); // Start one game
		
		try {
			client.getSock().close();
		} catch (IOException err) {
			err.printStackTrace();
			client.setSock(null);
		}
		
		return;
	}

}
