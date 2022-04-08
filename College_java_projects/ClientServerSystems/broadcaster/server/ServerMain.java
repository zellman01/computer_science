package server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ServerMain implements Server {
	private ServerSocket ss;
	private ArrayList<ClientMessageHandler> cmh;
	
	public ServerMain() throws IOException {
		cmh = new ArrayList<ClientMessageHandler>();
		ss = new ServerSocket(8577);
		System.out.println("Server started.");
		while (true) {
			try {
				ClientMessageHandler tmp = new ClientMessageHandler(ss.accept(), this);
				boolean authorized = true;
				for (ClientMessageHandler cm : cmh) {
					if (cm.getID().equals(tmp.getID())) {
						authorized = false;
						tmp.send("DENY");
					}
				}
			
				if (authorized) {
					tmp.send("ACCEPT");
					tmp.authorized();
				}
				cmh.add(tmp);
			} catch (IOException e) {
				System.out.println("Error creating streams.");
			}
		}
	}
	
	public void broadcast(String str, ClientMessageHandler tmp) {
		for(ClientMessageHandler cm : cmh) {
			if (cm != tmp) {
				cm.send(str);
			}
		}
	}
	
	public void removeClient(ClientMessageHandler cm) {
		// Remove client from list
		cmh.remove(cm);
	}
	
	public static void main(String[] args) {
		try {
			new ServerMain();
		} catch (IOException e) {
			System.out.println("Port 8577 is currently in use by another application.");
		}
	}
}