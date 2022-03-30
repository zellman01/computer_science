package server;

import shared.Talker;

import java.lang.Thread;
import java.io.IOException;

public class Handler extends Thread {
	private Talker t;
	private Server s;
	//private boolean authMode;
	//private boolean authorized;
	
	public Handler(Talker t, Server s) {
		this.t = t;
		this.s = s;
		//authMode = false;
		//authorized = false;
	}
	
	protected void serverStop() throws IOException {
		t.writeLine("STOP");
	}
	
	public void run() {
		String cmd = "";
		do {
			try {
				cmd = t.readLine();
				messageHandler(cmd);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} while (!cmd.equals("STOP") && !cmd.equals("S-STOP"));
		s.removeHandler(this);
	}
	
	// Write messages would be here
	private void messageHandler(String cmd) throws IOException {
		/*if (authMode) {
			if (cmd.startsWith("USER")) {
				cmd = cmd.substring(5, cmd.length());
				if (cmd.equals("zellman01")) { // Username check code
					t.writeLine("AUTH-PASS");
					return; // Leave this function to wait for password transmition
				} else {
					t.writeLine("UNAUTHORIZED");
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
				}
			}
		}*/
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
		//case "LOGIN":
			//t.writeLine("AUTH-USER");
			//authMode = true;
			//break;
		case "S-STOP":
			/*if (!authorized) {
				t.writeLine("UNAUTHORIZED");
				break;
			}*/
			t.writeLine("STOP");
			// TODO: Notify other clients to shut down
			s.serverShutdown(this);
			break;
		default: // Unauthorized/unsupported commands
			t.writeLine("STOP");
			break;
		}
	}
}