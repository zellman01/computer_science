package server;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

import shared.*;

public class ServerStart implements Server {
	private ServerSocket ss;
	private Socket s;
	private boolean shutDown;
	private ArrayList<Handler> handlers;
	
	public ServerStart() throws IOException {
		ss = new ServerSocket(ConstVariables.portNum);
		shutDown = false;
		handlers = new ArrayList<Handler>();
	}
	
	public void start() throws IOException {
		while (!shutDown) {
			try {
				s = ss.accept();
				Talker t = new Talker(s);
				Handler h = new Handler(t, this);
				h.start();
				handlers.add(h);
			} catch (IOException e) {
				// Blank as this will happen a lot
			}
		}
	}
	
	public void serverShutdown() { 
		shutDown = true;
		try {
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void serverShutdown(Handler h) {
		shutDown = true;
		try {
			ss.close();
			// Shutdown other clients
			for (Handler e : handlers) {
				e.serverStop();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void removeHandler(Handler h) {
		handlers.remove(h);
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