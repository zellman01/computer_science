package server;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

import shared.*;
import server.gui.ServerGUI;

public class ServerStart implements Server {
	private ServerSocket ss;
	private Socket s;
	private boolean shutDown;
	private ArrayList<Handler> handlers;
	private ServerGUI guiClass;
	private Banlist bans;
	
	public ServerStart() throws IOException {
		ss = new ServerSocket(ConstVariables.portNum);
		shutDown = false;
		handlers = new ArrayList<Handler>();
		bans = new Banlist();
		guiClass = new ServerGUI(this, bans);
	}
	
	public void start() throws IOException {
		guiClass.setupWindow();
		while (!shutDown) {
			try {
				s = ss.accept();
				Talker t = new Talker(s);
				if (bans.checkIPBan(t.getAddress())) {
					t.writeLine("IPBANNED");
					t = null;
					s.close();
				} else {
					Handler h = new Handler(t, this, bans);
					h.start();
					handlers.add(h);
					guiClass.addHandler(h);
				}
			} catch (IOException e) {
				// Blank as this will happen a lot
			}
		}
		System.exit(0);
	}
	
	public void serverShutdown(Handler h) {
		shutDown = true;
		try {
			ss.close();
			for (Handler e : handlers) {
				if (e != h)
					e.serverStop();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void serverShutdown() {
		shutDown = true;
		try {
			ss.close();
			for (Handler e : handlers) {
				e.serverStop();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void removeHandler(Handler h) {
		handlers.remove(h);
		guiClass.removeHandler(h);
	}
	
	public static void main(String[] args) {
		try {
			ServerStart ss = new ServerStart();
			ss.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}