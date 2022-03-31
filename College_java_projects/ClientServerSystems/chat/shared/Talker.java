package shared;

import java.net.Socket;
import java.io.*;

public class Talker {
	private DataOutputStream dos;
	private BufferedReader dis;
	
	public Talker(Socket s) throws IOException {
		dos = new DataOutputStream(s.getOutputStream());
		dis = new BufferedReader(new InputStreamReader(new DataInputStream(s.getInputStream())));
	}
	
	public void clear() throws IOException {
		dis.read();
	}
	
	public String readLine() throws IOException {
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
}