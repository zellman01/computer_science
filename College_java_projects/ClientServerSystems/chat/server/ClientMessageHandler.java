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
	public ClientMessageHandler(Socket s, Server se) throws IOException {
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
		String[] str2 = str.split(" ");
		System.out.println(str2[0]);
		switch (str2[0]) {
		case "LOGIN":
			if (str2.length != 3) {
				talker.writeLine("LOGIN-FAILURE-2");
				break; // Forces out of the case to prevent the rest to execute
			}
			if (login(str2)) {
				talker.writeLine("LOGIN-SUCCESS");
				server.authorized(this);
			} else
				talker.writeLine("LOGIN-FAILURE");
			break;
		case "REGISTER":
			if (str2.length != 3) {
				talker.writeLine("REGISTER-FAILURE-2");
				break;
			}
			if (register(str2)) {
				talker.writeLine("REGISTER-SUCCESS"); // Logs in user after they register
				server.authorized(this);
			} else
				talker.writeLine("REGISTER-FAILURE");
			break;
		default:
			System.out.println("Potentially bad client connected - Removing");
			throw new IOException("Bad Client");
		}
	}
	
	private boolean login(String[] str) throws IOException {
		String username = str[1];
		if (server.userExists(username)) {
			String password = str[2];
			if (server.getUser(username).login(password, this))
				return true; // Logged in
			else
				return false; // Password did not match
		}
		return false; // User not found
	}
	
	private boolean register(String[] str) throws IOException {
		String username = str[1];
		if (!server.userExists(username)) {
			String password = str[2];
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