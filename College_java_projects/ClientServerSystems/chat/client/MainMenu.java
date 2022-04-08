package client;

import javax.swing.*;

public class MainMenu extends JFrame {
	private ServerMessageHandler smh;
	
	public MainMenu(ServerMessageHandler smh) {
		this.smh = smh;
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}