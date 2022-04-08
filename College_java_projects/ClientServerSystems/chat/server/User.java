package server;

import java.io.*;
import java.util.ArrayList;

/**
 * Stores user information
*/
public class User {
	private String username;
	private String password;
	private ClientMessageHandler online;
	private ArrayList<String> buddies;
	private UserServer server;
	
	/**
	 * Reloads a user from a file
	 * @param dis The file to read the user from
	*/
	public User(DataInputStream dis, UserServer server) throws IOException {
		this.server = server;
		username = dis.readUTF();
		password = dis.readUTF();
		buddies = new ArrayList<String>();
		int buddyTotal = dis.readInt();
		for (int i = 0; i < buddyTotal; i++) {
			buddies.add(dis.readUTF());
		}
	}
	
	/**
	 * Check if the given password is correct, then log them in. Otherwise, fail the login.
	 * @param password The user given password to try
	 * @param online The message handler of the client, if they succeed
	*/
	public boolean login(String password, ClientMessageHandler online) {
		if (!this.password.equals(password)) {
			return false;
		}
		
		this.online = online;
		updateBuddies(true);
		return true;
	}
	
	/**
	 * Creates a user from a username and password. Also puts the message handler into the user
	*/
	public User(String username, String password, ClientMessageHandler online, UserServer server) {
		this.server = server;
		this.username = username;
		this.password = password;
		this.online = online;
		buddies = new ArrayList<String>();
	}
	
	/**
	 * Stores the user data
	 * @param dos File stream to save data
	*/
	public void store(DataOutputStream dos) throws IOException {
		dos.writeUTF(username);
		dos.writeUTF(password);
		dos.writeInt(buddies.size());
		for (int i = 0; i < buddies.size(); i++) {
			dos.writeUTF(buddies.get(i));
		}
	}
	
	/**
	 * Logs the user out.
	*/
	public void logout() {
		online = null;
		updateBuddies(false);
	}
	
	private void updateBuddies(boolean online) {
		for(String buddy : buddies) {
			User bud = server.getUser(buddy);
			if(bud.isConnected()) {
				if (online)
					bud.sendInformation("BUDDY-ONLINE " + username);
				else
					bud.sendInformation("BUDDY-OFFLINE " + username);
			}
		}
	}
	
	private void sendInformation(String str) {
		online.send(str);
	}
	
	public boolean isConnected() { return online != null; }
	
	public String getUsername() { return username; }
}