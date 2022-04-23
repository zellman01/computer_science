package server;

import java.util.Hashtable;
import java.util.Enumeration;
import java.io.*;

public class UserList extends Hashtable<String, User> {
	public UserList(String fileName, UserServer server) {
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream(fileName));
			int totalUsers = dis.readInt();
			for (int i = 0; i < totalUsers; i++) {
				User tmp = new User(dis, server);
				put(tmp.getUsername(), tmp);
			}
		} catch (IOException e) {
			// Create a base UserList without a file, then create said file
		}
	}
	
	public void store(DataOutputStream dos) throws IOException {
		dos.writeInt(size());
		Enumeration<User> users = elements();
		while (users.hasMoreElements()) {
			users.nextElement().store(dos);
		}
	}
	
	public boolean createUser(String username, String password, ClientMessageHandler cmh, UserServer server) {
		// Check username against command list - block if it is one of them
		User tmp = new User(username, password, cmh, server);
		put(username, tmp);
		return true;
	}
}