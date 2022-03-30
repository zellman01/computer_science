package server;

import shared.Talker;

import java.lang.Thread;
import java.io.IOException;
import javax.swing.DefaultListModel;

public class Handler extends Thread {
	private Talker t;
	private Server s;
	private boolean authMode;
	private boolean authorized;
	private String userAuthorized;
	private Banlist bans;
	
	public Handler(Talker t, Server s, Banlist bans) {
		this.t = t;
		this.s = s;
		this.bans = bans;
		authMode = false;
		authorized = false;
		userAuthorized = "";
	}
	
	protected void serverStop() throws IOException {
		t.writeLine("STOP");
	}
	
	protected void forceDisconnect() {
		try {
			t.writeLine("F-DISC");
		} catch (IOException e) {
			System.out.println("Unknown error");
			e.printStackTrace();
		}
	}
	
	public void run() {
		String cmd = "";
		do {
			try {
				cmd = t.readLine();
				messageHandler(cmd);
			} catch (IOException e) {
				System.out.println("ERROR: Client shut down unexpectedly - Killing thread.");
				cmd = "STOP";
			}
		} while (!cmd.equals("STOP") && !cmd.equals("S-STOP"));
		s.removeHandler(this);
	}
	
	// Write messages would be here
	private void messageHandler(String cmd) throws IOException {
		if (authMode) {
			if (cmd.startsWith("USER")) {
				cmd = cmd.substring(5, cmd.length());
				if (bans.checkUserBan(cmd)) {
					t.writeLine("BANNED");
					authMode = false;
				}
				if (cmd.equals("zellman01")) { // Username check code
					t.writeLine("AUTH-PASS");
					userAuthorized = "zellman01";
					return; // Leave this function to wait for password transmition
				} else {
					t.writeLine("UNAUTHORIZED");
					userAuthorized = "";
				}
			}
			
			if (cmd.startsWith("PASS")) {
				cmd = cmd.substring(5, cmd.length());
				if (cmd.equals("383rjps9fuj3%$%494nrwr!%u94r")) {
					authMode = false;
					t.writeLine("AUTHORIZED");
					authorized = true;
					return;
				} else {
					t.writeLine("UNAUTHORIZED");
					userAuthorized = "";
				}
			}
		}
		switch (cmd) {
		case "C-ACK":
			t.writeLine("S-ACK");
			break;
		case "HNK":
			t.writeLine("CONNECT-SUCCESS");
			break;
		case "STOP":
			t.writeLine("STOP");
			break;
		case "LOGIN":
			t.writeLine("AUTH-USER");
			authMode = true;
			break;
		case "S-STOP":
			if (!authorized) {
				t.writeLine("UNAUTHORIZED");
				break;
			}
			t.writeLine("STOP");
			s.serverShutdown(this);
			break;
		default: // Unauthorized/unsupported commands
			t.writeLine("STOP");
			break;
		}
	}
	
	public static int getFieldCount() {
		return 3;
		// IP Address, authorized, username authorized under
	}
	
	public String getAddress() {
		return t.getAddress();
	}
	
	public boolean getAuthorized() {
		return authorized;
	}
	
	public String getUserAuthorized() {
		return userAuthorized;
	}
}