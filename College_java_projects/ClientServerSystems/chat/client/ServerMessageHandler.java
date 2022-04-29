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
	private String username;
	private ArrayList<ChatWindow> openChats;
	
	public ServerMessageHandler(Socket s) throws IOException {
		talker = new Talker(s);
		buddies = new ArrayList<String>();
		openChats = new ArrayList<ChatWindow>();
		new Thread(this).start();
	}
	
	public String getUsername() {
		return username;
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
		case "LOGIN-SUCCESS":
			// Finish login
			username = login.getUsername();
			talker.assignUsername(username);
			login.finished();
			obtainBuddiesFromServer();
			break;
		case "BUDDY-ONLINE":
			addBuddy(true, str2[1]);
			break;
		case "BUDDY-OFFLINE":
			addBuddy(false, str2[1]);
			break;
		case "BUDDY-REQUEST":
			buddyRequest(str2[1]);
			break;
		case "CHAT-BEGIN":
			beginChat(str2[1]);
			break;
		case "BUDDY-ON":
			authorizeChat(str2[1]);
			break;
		case "BUDDY-OFF":
		case "CHAT-ENDED":
			unauthorizeChat(str2[1]);
			break;
		case "CHAT":
			updateChat(str2[1], str2[2]);
			break;
		}
	}
	
	public void addChatWindow(ChatWindow cw) {
		openChats.add(cw);
	}
	
	public void removeChatWindow(ChatWindow cw) {
		openChats.remove(cw);
	}
	
	private void updateChat(String buddy, String message) {
		for (ChatWindow cw : openChats) {
			if (cw.checkBuddy(buddy)) {
				cw.addText(message, buddy);
				break;
			}
		}
	}
	
	private void authorizeChat(String buddy) {
		for (ChatWindow cw : openChats) {
			if (cw.checkBuddy(buddy)) {
				cw.authorized();
				break;
			}
		}
	}
	
	private void unauthorizeChat(String buddy) {
		for (ChatWindow cw : openChats) {
			if (cw.checkBuddy(buddy)) {
				cw.unauthorized();
				break;
			}
		}
	}
	
	private void beginChat(String buddy) {
		openChats.add(new ChatWindow(buddy, this));
		authorizeChat(buddy);
	}
	
	private void addBuddy(boolean online, String buddy) {
		if (online) {
			if (buddies.contains(buddy)) {
				// Edit the buddy to be online
				buddies.set(buddies.indexOf(buddy), buddy + " *");
			} else {
				buddies.add(buddy + " *");
			}
		} else {
			if (buddies.contains(buddy + " *")) {
				buddies.set(buddies.indexOf(buddy + " *"), buddy);
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
	
	private void obtainBuddiesFromServer() {
		send("BUDDY-LIST");
	}
	
	private void buddyRequest(String name) {
		int choise = JOptionPane.showConfirmDialog(null, "Would you like to add " + name + " as a buddy?");
		if (choise == JOptionPane.OK_OPTION) {
			send("BUDDY-ACCEPTED " + name);
		}
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
			System.exit(1);
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
	
	public void send(String msg) {
		try {
			talker.writeLine(msg);
		} catch (IOException e) {
			System.out.println("Server has died.");
			System.exit(1);
		}
	}
	
	public void login(String username, String password, Login login) throws IOException {
		this.login = login;
		account("LOGIN", username, password);
	}
	
	private void account(String action, String username, String password) throws IOException {
		talker.writeLine(action + " " + username + " " + password);
	}
}