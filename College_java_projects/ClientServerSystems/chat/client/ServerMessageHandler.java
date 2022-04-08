package client;

import java.net.*;
import java.io.IOException;
import javax.swing.JOptionPane;
import shared.*;

public class ServerMessageHandler implements Runnable, MessageHandler {
	private Talker talker;
	
	public ServerMessageHandler(Socket s) throws IOException {
		talker = new Talker(s);
		new Thread(this).start();
	}
	
	private void messageHandler(String str) throws IOException {
		String[] str2 = str.split(" ");
		switch (str2[0]) {
		case "REGISTER-FAILURE":
			// Show error message in JOPtionPane
			break;
		case "REGISTER-FAILURE-2":
			// Show error
			break;
		case "LOGIN-FAILURE":
			// Show error
			break;
		case "LOGIN-FAILURE-2":
			// Show error
			break;
		case "REGISTER-SUCCESS":
			// Finish login
			break;
		case "LOGIN-SUCCESS":
			// Finish login
			break;
		}
	}
	
	public void run() {
		try {
			while (true) {
				messageHandler(talker.readLine());
			}
		} catch (IOException e) {
			System.out.println("Server has died.");
		}
	}
	
	public void register(String username, String password) throws IOException {
		String[] test = username.split(" ");
		if (test.length > 1) {
			JOptionPane.showMessageDialog(null, "Your username cannot have a space.");
			return; // Fail on registering client side
		}
		test = password.split(" ");
		if (test.length > 1) {
			JOptionPane.showMessageDialog(null, "Your password cannot have a space.");
			return; // Fail on registering client side
		}
		account("REGISTER", username, password);
	}
	
	public void login(String username, String password) throws IOException {
		account("LOGIN", username, password);
	}
	
	private void account(String action, String username, String password) throws IOException {
		talker.writeLine(action + " " + username + " " + password);
	}
}