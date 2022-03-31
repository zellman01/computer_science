package server;

import java.util.Hashtable;
import java.util.Enumeration;
import java.io.*;

public class UserList extends Hashtable<String, User> {
	public UserList(String fileName) {
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream(fileName));
			int totalUsers = dis.readInt();
			for (int i = 0; i < totalUsers; i++) {
				User tmp = new User(dis);
				put(tmp.getUsername(), tmp);
			}
		} catch (IOException e) {
			// Create a base UserList without a file, then create said file
		}
	}
	
	public void store(DataOutputStream dos) throws IOException {
		dos.writeInt(size());
		Enumeration users = elements();
		while (users.hasMoreElements())
			users.nextElement().store(dos);
	}
	
	public void createUser(String username, String password) {
		User tmp = new User(username, password);
		put(username, tmp);
	}
}