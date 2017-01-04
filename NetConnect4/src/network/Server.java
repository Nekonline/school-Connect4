package network;

import java.io.IOException;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.ServerSocket;

public class Server {
	
	private InetAddress address;
	private ServerSocket sServer;
	private int port;
	
	public InetAddress getAddress() { return this.address; }
	public int getPort() { return this.port; }
	public ServerSocket getSockServer() { return this.sServer; }
	
	// Server Constructor
	public Server() {
		try {
			this.address = InetAddress.getLocalHost();
			System.out.println("Server's address : " + this.address.getHostAddress());
		} catch (UnknownHostException err) {
			err.printStackTrace();
		}
		
		try { // Create a server socket with the local address
			// and the first port number available
			this.sServer = new ServerSocket(0, 1, this.address);
			this.port = this.sServer.getLocalPort();
			System.out.println("Server's port number : " + this.port);
		} catch (IOException err) {
			err.printStackTrace();
		}
	}
	
}
