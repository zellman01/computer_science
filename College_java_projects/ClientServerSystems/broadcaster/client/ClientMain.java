package client;

import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;

public class ClientMain extends JFrame implements ActionListener, Client {
	private JButton send;
	private JButton exit;
	private JLabel recievedMessage;
	private JTextField textField;
	private ServerMessageHandler smh;
	
	public ClientMain() {
		String id = JOptionPane.showInputDialog("Please input your username.");
		if (id == null) {
			System.exit(1);
		}
		if (id.trim().equals("")) {
			System.exit(1);
		}
		textField = new JTextField(15);
		recievedMessage = new JLabel();
		exit = makeButton("Exit");
		send = makeButton("Send");
		try {
			smh = new ServerMessageHandler(new Socket("127.0.0.1", 8577), id, this);
		} catch (IOException e) {
			System.out.println("Could not start the client.");
			System.exit(1);
		}
		setTitle(id);
		JPanel tmp = new JPanel();
		tmp.add(textField);
		tmp.add(send);
		tmp.add(exit);
		tmp.add(recievedMessage);
		add(tmp);
		setSize(250,250);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void updateMessage(String str) {
		recievedMessage.setText(str);
	}
	
	private JButton makeButton(String label) {
		JButton b = new JButton(label);
		b.setActionCommand(label.toUpperCase());
		b.addActionListener(this);
		return b;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("SEND")) {
			try {
				smh.sendMessage(textField.getText().trim());
			} catch (IOException e1) {
				System.out.println("Server has died.");
				System.exit(2);
			}
		}
		
		if (e.getActionCommand().equals("EXIT")) {
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		new ClientMain();
	}
}