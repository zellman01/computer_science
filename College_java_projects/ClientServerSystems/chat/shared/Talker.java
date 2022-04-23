package shared;

import java.net.Socket;
import java.io.*;

public class Talker {
	private DataOutputStream dos;
	private BufferedReader dis;
	private String username;
	
	public Talker(Socket s) throws IOException {
		dos = new DataOutputStream(s.getOutputStream());
		dis = new BufferedReader(new InputStreamReader(new DataInputStream(s.getInputStream())));
		username = "NULL";
	}
	
	public void clear() throws IOException {
		dis.read();
	}
	
	public void assignUsername(String username) {
		this.username = username;
	}
	
	public String readLine() throws IOException {
		clear();
		clear();
		String str = dis.readLine();
		System.out.println(username + " READ: " + str);
		return str;
	}
	
	public void writeLine(String line) throws IOException {
		line += "\n";
		System.out.println(username + " SEND: " + line);
		dos.writeUTF(line);
		dos.flush();
	}
}