package server;

import shared.*;
import java.net.*;
import java.io.*;

public class ClientMessageHandler implements Runnable {
	private String id;
	private Talker talker;
	private Server server;
	
	public ClientMessageHandler(Socket so, Server se) throws IOException {
		talker = new Talker(so);
		String tmp = talker.readLine();
		id = tmp.substring(4, tmp.length());
		server = se;
	}
	
	public void authorized() {
		new Thread(this).start();
	}
	
	private void messageHandler(String str) {
		String tmp = id + ": " + str;
		server.broadcast(tmp, this);
	}
	
	public String getID() { return id; }
	
	public void run() {
		try {
			while (true) {
				messageHandler(talker.readLine());
			}
		} catch (IOException e) {
			server.removeClient(this);
		}
	}
	
	public void send(String str) {
		try {
			talker.writeLine(str);
		} catch (IOException e) {
			System.out.println("Client has disconnected - Stopping thread execution.");
			server.removeClient(this);
		}
	}
}