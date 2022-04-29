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
		setSize(500,500);
		setTitle(client.getUsername());
		setVisible(true);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {}
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
		revalidate();
		repaint();
	}
	
	private JButton addBuddyButton(String name) {
		JButton tmp = new JButton(name);
		String[] tmp2 = name.split(" ");
		tmp.setActionCommand(tmp2[0]);
		tmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.addChatWindow(new ChatWindow(e.getActionCommand(), client));
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
			if (tmp != null && !tmp.equals("") && !tmp.equals(client.getUsername())) {
				client.send("ADD-BUDDY " + tmp);
			}
		}
	}
}