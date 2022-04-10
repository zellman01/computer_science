package client;

import java.net.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import shared.*;

public class ServerMessageHandler implements Runnable, MessageHandler, Client {
	private Talker talker;
	private Login login;
	private ArrayList<String> buddies;
	private MenuInterface menuInterface;
	
	public ServerMessageHandler(Socket s) throws IOException {
		talker = new Talker(s);
		buddies = new ArrayList<String>();
		new Thread(this).start();
	}
	
	private void messageHandler(String str) throws IOException {
		String[] str2 = str.split(" ");
		switch (str2[0]) {
		case "REGISTER-FAILURE":
			JOptionPane.showMessageDialog(null, "Username already exists.");
			break;
		case "REGISTER-FAILURE-2":
		case "LOGIN-FAILURE-2":
			JOptionPane.showMessageDialog(null, "Should never happen.");
			break;
		case "LOGIN-FAILURE":
			JOptionPane.showMessageDialog(null, "Username/password incorrect.");
			break;
		case "REGISTER-SUCCESS":
			// Finish login
			login.finished();
			break;
		case "LOGIN-SUCCESS":
			// Finish login
			login.finished();
			break;
		case "BUDDY-ONLINE":
			addBuddy(true, str2[1]);
			break;
		case "BUDDY-OFFLINE":
			addBuddy(false, str2[1]);
			break;
		}
	}
	
	private void addBuddy(boolean online, String buddy) {
		if (online) {
			if (buddies.contains(buddy)) {
				// Edit the buddy to be online
			} else {
				buddies.add(buddy + " *");
			}
		} else {
			if (buddies.contains(buddy + " *")) {
				// Edit the buddy to be offline
			} else {
				buddies.add(buddy);
			}
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				menuInterface.displayBuddies();
			}
		});
	}
	
	public void addMenuInterface(MenuInterface mi) {
		menuInterface = mi;
	}
	
	public ArrayList<String> obtainBuddies() {
		return buddies;
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
	
	public void register(String username, String password, Login login) throws IOException {
		this.login = login;
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
	
	public void login(String username, String password, Login login) throws IOException {
		this.login = login;
		account("LOGIN", username, password);
	}
	
	private void account(String action, String username, String password) throws IOException {
		talker.writeLine(action + " " + username + " " + password);
	}
}