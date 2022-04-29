package client;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.Element;

public class ChatWindow extends JDialog implements WindowListener {
	private String buddyUsername;
	private Client client;
	private JButton startChat;
	private JButton send;
	private JTextField message;
	private JEditorPane editor;
	private boolean started;
	
	public ChatWindow(String buddyUsername, Client client) {
		this.buddyUsername = buddyUsername;
		this.client = client;
		addWindowListener(this);
		setTitle(buddyUsername);
		
		send = new JButton("Send message");
		send.setEnabled(false);
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.send("CHAT " + buddyUsername + " " + message.getText().trim());
				addText(message.getText().trim(), client.getUsername());
				message.setText("");
			}
		});
		
		startChat = new JButton("Start Chat");
		startChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Pull up chat window on buddy's side, if they are online
				client.send("CHAT-START " + buddyUsername);
				client.send("BUDDY-STATUS " + buddyUsername);
			}
		});
		
		message = new JTextField(50);
		
		JPanel control = new JPanel();
		control.add(startChat);
		control.add(message);
		control.add(send);
		add(control, BorderLayout.SOUTH);
		
		JPanel chatScreen = new JPanel();
		
		editor = new JEditorPane();
		editor.setEditable(false);
		editor.setContentType("text/html");
		
		editor.setText("<div align = \"center\">"	+
			"<font color=\"green\">"				+
				"Chatting with " + buddyUsername 	+
			"</font>"								+
		"</div>");
		
		add(editor);
		setSize(1000,500);
		setVisible(true);
	}
	
	public void addText(String txt, String sender) {
		if (sender.equals(buddyUsername)) {
			addTextInternal("<div align = \"left\">" +
							"<font color = \"blue\">" +
							buddyUsername + ":" +
							"</font>" +
							txt +
							"</div>");
		} else {
			addTextInternal("<div align = \"right\">" +
							"<font color = \"red\">" +
							sender + ":" +
							"</font>" +
							txt +
							"</div>");
		}
	}
	
	private void addTextInternal(String txt) {
		HTMLDocument doc;
		Element html;
		Element body;
		
		doc = (HTMLDocument)editor.getDocument();
		html = doc.getRootElements()[0];
		body = html.getElement(1);
		
		try {
			doc.insertBeforeEnd(body, txt);
			editor.setCaretPosition(editor.getDocument().getLength());
		} catch (Exception e) {
			System.out.println("Error inserting text.");
		}
	}
	
	public boolean checkBuddy(String username) {
		return username.equals(buddyUsername);
	}
	
	public void authorized() {
		send.setEnabled(true);
		startChat.setEnabled(false);
		started = true;
	}
	
	public void unauthorized() {
		send.setEnabled(false);
		JOptionPane.showMessageDialog(this, "Buddy has went offline/ended the chat.");
		startChat.setEnabled(true);
		started = false;
	}
	
	public void windowActivated(WindowEvent e) {}
	
	public void windowClosed(WindowEvent e) {}
	
	public void windowClosing(WindowEvent e) {
		System.out.println("Testing2");
			client.send("CHAT-ENDING " + buddyUsername);
			client.removeChatWindow(this);
		}
	}
	
	public void windowDeactivated(WindowEvent e) {}
	
	//public void windowDeiconified​(WindowEvent e) {}
	
	public void windowDeiconified(WindowEvent e) {}
	
	//public void windowIconified(WindowEvent e) {}
	
	public void windowIconified(WindowEvent e) {}
	
	public void windowOpened(WindowEvent e) {}
}