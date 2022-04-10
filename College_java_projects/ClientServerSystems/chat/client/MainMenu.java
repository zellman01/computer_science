package client;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame implements MenuInterface {
	private Client client;
	private JPanel buddyList;
	
	public MainMenu(Client client) {
		this.client = client;
		client.addMenuInterface(this);
		buddyList = new JPanel();
		displayBuddies();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void displayBuddies() {
		ArrayList<String> buddyString = client.obtainBuddies();
		remove(buddyList);
		buddyList.removeAll();
		for (String tmp : buddyString) {
			buddyList.add(addBuddyButton(tmp));
		}
		add(buddyList);
	}
	
	private JButton addBuddyButton(String name) {
		JButton tmp = new JButton(name);
		String[] tmp2 = name.split(" ");
		tmp.setActionCommand(tmp2[0]);
		tmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("User " + e.getActionCommand());
			}
		});
		if (tmp2.length != 2) {
			tmp.setEnabled(false);
		}
		return tmp;
	}
}