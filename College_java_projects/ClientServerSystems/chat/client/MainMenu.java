package client;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class MainMenu extends JFrame implements MenuInterface, ActionListener {
	private Client client;
	private JPanel buddyList;
	private JButton addBuddy;
	
	public MainMenu(Client client) {
		this.client = client;
		client.addMenuInterface(this);
		addBuddy = new JButton("Add buddy");
		addBuddy.addActionListener(this);
		addBuddy.setActionCommand("ADDBUDDY");
		buddyList = new JPanel();
		add(addBuddy, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setsize(500,500);
		setVisible(true);
		displayBuddies();
	}
	
	public void displayBuddies() {
		ArrayList<String> buddyString = client.obtainBuddies();
		remove(buddyList);
		buddyList.removeAll();
		for (String tmp : buddyString) {
			buddyList.add(addBuddyButton(tmp));
		}
		add(buddyList);
		add(addBuddy, BorderLayout.SOUTH);
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
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("ADDBUDDY")) {
			String tmp = JOptionPane.showInputDialog("Enter the potential buddy's username").trim();
			if (tmp != null && !tmp.equals("")) {
				client.send("ADD-BUDDY " + tmp);
			}
		}
	}
}