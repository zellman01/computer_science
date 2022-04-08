package client;

import java.net.*;
import java.io.IOException;

public class ClientMain {
	private ServerMessageHandler smh;
	
	public ClientMain() throws IOException {
		smh = new ServerMessageHandler(new Socket("127.0.0.1", 8578));
		new Login(smh);
		
	}
	
	public static void main(String[] args) {
		System.out.println("Not worked on yet.");
	}
}