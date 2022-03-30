package client;

import java.net.Socket;
import java.io.IOException;

import client.*;
import shared.*;

public class ClientStart implements Client {
	private Socket s;
	
	public ClientStart() throws IOException {
		s = new Socket(ConstVariables.host, ConstVariables.portNum);
	}
	
	public void start() throws IOException {
		Talker t = new Talker(s);
		Handler h = new Handler(t);
		h.start();
	}
	
	public void clientShutdown() {
		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			ClientStart cs = new ClientStart();
			cs.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}