package client;

import java.net.*;
import java.io.*;
import javax.swing.SwingUtilities;
import shared.*;

public class ServerMessageHandler implements Runnable {
	private Talker talker;
	private Client client;
	
	public ServerMessageHandler(Socket s, String id, Client client) throws IOException {
		talker = new Talker(s);
		talker.writeLine("ID: " + id);
		String response = talker.readLine();
		this.client = client;
		if (response.equals("ACCEPT"))
			new Thread(this).start();
		else
			throw new IOException("ERROR");
	}
	
	public void sendMessage(String str) throws IOException {
		if (!str.equals("")) talker.writeLine(str);
	}
	
	private void handleMessage(String str) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				client.updateMessage(str);
			}
		});
	}
	
	public void run() {
		try {
			while (true) {
				handleMessage(talker.readLine());
			}
		} catch (IOException e) {
			System.out.println("Server has died.");
			System.exit(2);
		}
	}
}