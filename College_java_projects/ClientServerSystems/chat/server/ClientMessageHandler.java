package server;

import shared.*;
import java.net.*;
import java.io.*;

/**
 * Client connection handler
*/
public class ClientMessageHandler implements Runnable {
	private Talker talker;
	private Server server;
	
	/**
	 * Starts handling clients
	 * @param s The Socket of the client
	 * @param se The Server's memory address
	*/
	public ClientMessageHandler(Socket s, Server se) {
		talker = new Talker(s);
		server = se;
		new Thread(this).start();
	}

	/**
	 * Handles messages coming in from the client
	 * @param str The string recieved by the client
	 * @throws IOException If the client suddenly disconnects
	*/
	private void messageHandler(String str) throws IOException {
		switch (str) {
		case "LOGIN":
			if (logon()) {
				talker.writeLine("LOGIN-SUCCESS");
				server.authorized(this);
			} else
				talker.writeLine("LOGIN-FAILURE");
			break;
		case "REGISTER":
			if (register()) {
				talker.writeLine("REGISTER-SUCCESS"); // Logs in user after they register
				server.authorized(this);
			} else
				talker.writeLine("REGISTER-FAILURE");
			break;
		default:
			System.out.println("Potentially bad client connected - Removing");
			throws IOException("Bad Client");
			break;
		}
	}
	
	private boolean logon() throws IOException {
		String username = talker.readLine();
		if (server.userExists(username)) {
			String password = talker.readLine();
			if (server.getUser(username).login(password)) {
				return true; // Successful login
			} else {
				return false; // Password did not match
			}
		}
		return false; // If the user did not exist
	}
	
	private boolean register() throws IOException {
		String username = talker.readLine();
		if (!server.userExists(username)) {
			String password = talker.readLine();
			server.createUser(username, password, this);
			return true;
		}
		return false;
	}
	
	/**
	 * The executing thread after the connection has been made to the client
	*/
	public void run() {
		try {
			while (true) {
				messageHandler(talker.readLine());
			}
		} catch (IOException e) {
			System.out.println("Client had died.");
		}
	}
}