package server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ServerMain implements Server {
	private ServerSocket ss;
	private UserList userList;
	private ArrayList<ClientMessageHandler> notLoggedIn;
	
	public ServerMain() throws IOException {
		userList = new UserList();
		notLoggedIn = new ArrayList<ClientMessageHandler>();
		ss = new ServerSocket(8578);
		while (true) {
			try {
				notLoggedIn.add(new ClientMessageHandler(ss.accept()));
			} catch (IOException e) {
				System.out.println("Error creating streams.");
			}
		}
	}
	
	public void authorized(ClientMessageHandler cmh) {
		notLoggedIn.remove(cmh);
	}
	
	public boolean userExists(String username) {
		return userList.containsKey(username);
	}
	
	public User getUser(String username) {
		return userList.get(username);
	}
	
	public synchronized void createUser(String username, String password) {
		userList.createUser(username, password);
		userList.store();
	}
	
	public static void main(String[] args) {
		try {
			new ServerMain();
		} catch (IOException e) {
			System.out.println("Port 8578 is currently in use by another application.");
		}
	}
}