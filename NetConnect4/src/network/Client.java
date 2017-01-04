package network;

import java.util.Scanner;

import java.io.IOException;

import java.net.Socket;
import java.net.UnknownHostException;

import main.SetPortException;
import main.SetAddressException;

public class Client {
	
	private String nickname;
	private String address;
	private int port;
	private Socket sClient;
	
	public String getNickname() { return this.nickname; }
	public String getAddress() { return this.address; }
	public Socket getSock() { return this.sClient; }
	public void setSock(Socket sock) { this.sClient = sock; }

	// Ask to input an IPV4 Address
	private int setAddress() throws SetAddressException {
		Scanner sc = new Scanner(System.in);
		System.out.println("\nEnter an address :");
		String input = sc.nextLine();
		String[] address = input.split("\\.");
		if (address.length != 4) { // Check if the size is correct
			throw new SetAddressException(input);
		}
		for (int i = 0; i < 4; i ++) { // Check if each members is between [0, 255]
			if (0 > Integer.parseInt(address[i]) || 255 < Integer.parseInt(address[i])) {
				throw new SetAddressException(input);
			}
		}
		this.address = input;
		return 0;
	}
	
	// Ask to input a port number
	private int setPort() throws SetPortException {
		Scanner sc = new Scanner(System.in);
		System.out.println("\nEnter a port number :");
		if (sc.hasNextInt() == false) { // Check if you input an integer
			String str = sc.nextLine();
			if (str.length() == 0)
				str = sc.nextLine();
			throw new SetPortException(str);
		} else {
			this.port =  sc.nextInt();
			return 0;
		}
	}
	
	// Client constructor used when you know the server address and the server port number
	public Client(String nickname) {
		this.nickname = nickname;
		int addr = 1;
		while (addr == 1) {
			try {
				addr = setAddress(); // First you have to input the server address within the IPV4 protocol
			} catch (SetAddressException err) {
				if (err.getString().equals("sortir")) {
					System.out.println("You can quit by pushing Ctrl + c\nSorry it's this only way for the moment.");
				} else {
					System.out.println(err);
				}
			}
		}
		int port = 1;
		while (port == 1) {
			try { // Then you have to input the port number
				port = setPort();
			} catch (SetPortException err) {
				if (err.getString().equals("sortir")) {
					System.out.println("You can quit by pushing Ctrl + c\nSorry it's this only way for the moment.");
				} else {
					System.out.println(err);
				}
			}
		}
		try { // Finally, the client socket is created
			this.sClient = new Socket(this.address, this.port);
		} catch (UnknownHostException err) {
			err.printStackTrace();
		} catch (IOException err) {
			err.printStackTrace();
		}
	}
	
	// Client constructor when you already know the client socket
	public Client(Socket sock, String nickname) {
		this.nickname = nickname;
		this.address = sock.getInetAddress().getHostAddress();
		this.port = sock.getLocalPort();
		this.sClient = sock;
	}

}
