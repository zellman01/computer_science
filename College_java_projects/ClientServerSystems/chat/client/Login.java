package client;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class Login extends JDialog implements ActionListener {
	private MessageHandler smh;
	private JLabel username;
	private JLabel password;
	private JTextField usernameInput;
	private JPasswordField passwordInput;
	private JButton login;
	private JButton register;
	private JButton exit;
	
	public Login(MessageHandler smh) {
		this.smh = smh;
		username = new JLabel("Input username: ");
		usernameInput = new JTextField(15);
		password = new JLabel("Input password: ");
		passwordInput = new JPasswordField(15);
		login = makeButton("Login");
		register = makeButton("Register");
		exit = makeButton("Exit");
		
		JPanel jp = new JPanel();
		
		jp.add(username);
		jp.add(usernameInput);
		jp.add(password);
		jp.add(passwordInput);
		jp.add(login);
		jp.add(register);
		add(jp);
		setSize(500,500);
		pack();
		
		setTitle("Login to chat");
		setModal(true);
		setVisible(true);
	}
	
	private JButton makeButton(String label) {
		JButton b = new JButton(label);
		b.setActionCommand(label.toUpperCase());
		b.addActionListener(this);
		return b;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("EXIT")) {
			System.exit(0);
		}
		
		if (e.getActionCommand().equals("REGISTER")) {
			try {
				smh.register(usernameInput.getText(), new String(passwordInput.getPassword()));
				dispose();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(this, "Server is unreachable.", "Connection Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if (e.getActionCommand().equals("LOGIN")) {
			try {
				smh.login(usernameInput.getText(), new String(passwordInput.getPassword()));
				dispose();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(this, "Server is unreachable.", "Connection Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}