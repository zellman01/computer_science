package server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ServerMain implements Server, UserServer {
	private ServerSocket ss;
	private UserList userList;
	private ArrayList<ClientMessageHandler> notLoggedIn;
	
	public ServerMain() throws IOException {
		userList = new UserList("UserFile.dat", this);
		notLoggedIn = new ArrayList<ClientMessageHandler>();
		ss = new ServerSocket(8578);
		System.out.println("Server Started.");
		while (true) {
			try {
				notLoggedIn.add(new ClientMessageHandler(ss.accept(), this));
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
	
	public synchronized void createUser(String username, String password, ClientMessageHandler cmh) throws IOException {
		if (userList.createUser(username, password, cmh, this))
			userList.store(new DataOutputStream(new FileOutputStream("UserFile.dat")));
	}
	
	public void logout(String username) {
		User tmp = getUser(username);
		tmp.logout();
	}
	
	public static void main(String[] args) {
		System.out.println("Starting server");
		try {
			new ServerMain();
		} catch (IOException e) {
			System.out.println("Port 8578 is currently in use by another application.");
		}
	}
}