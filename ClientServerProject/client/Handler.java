package client;

import java.io.IOException;
import java.io.File;

import shared.*;

public class Handler {
	private Talker t;
	//private boolean authorized;
	
	public Handler(Talker t) {
		this.t = t;
		//authorized = false;
	}
	
	public void start() {
		String cmd = "";
		// Write hello command
		try {
			t.writeLine("C-ACK");
		} catch (IOException e) {
			e.printStackTrace();
		}
		do {
			if (authorized) {
				// Start console window to directly interface with server
			}
			try {
				cmd = t.readLine();
				messageHandler(cmd);
			} catch (IOException e) {
				e.printStackTrace();	
			}
		} while ((!cmd.equals("STOP") && !cmd.equals("UNAUTHORIZED")));
	}
	
	public void messageHandler(String cmd) throws IOException {
		if (cmd.equals("S-ACK")) {
			t.writeLine("HNK");
		}
		
		if (cmd.equals("CONNECT-SUCCESS")) {
			//t.writeLine("LOGIN");
		}
		
		/*if (cmd.equals("AUTH-USER")) {
			t.writeLine("USER:zellman01");
		}
		
		if (cmd.equals("AUTH-PASS")) {
			t.writeLine("PASS:383rjps9fuj3%$%494nrwr!%u94r");
		}
		
		if (cmd.equals("AUTHORIZED")) {
			t.writeLine("S-STOP");
		}*/
		
		if (cmd.equals("STOP") || cmd.equals("UNAUTHORIZED")) {
			t.writeLine("STOP");
		}
	}
}