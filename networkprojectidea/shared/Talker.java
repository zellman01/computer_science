package shared;

import java.net.Socket;
import java.io.*;

public class Talker {
	private DataOutputStream dos;
	private BufferedReader dis;
	private final String ipAddress;
	
	public Talker(Socket s) throws IOException {
		dos = new DataOutputStream(s.getOutputStream());
		dis = new BufferedReader(new InputStreamReader(new DataInputStream(s.getInputStream())));
		ipAddress = s.getLocalAddress().getHostAddress();
	}
	
	public void clear() throws IOException {
		dis.read();
	}
	
	public String readLine() throws IOException { // Throws SocketConnection indirectly
		clear();
		clear();
		String str = dis.readLine();
		System.out.println("READ: " + str);
		return str;
	}
	
	public void writeLine(String line) throws IOException {
		line += "\n";
		System.out.println("SEND: " + line);
		dos.writeUTF(line);
		dos.flush();
	}
	
	public String getAddress() {
		return ipAddress;
	}
}