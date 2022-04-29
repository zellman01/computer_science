package client;

import java.util.ArrayList;

public interface Client {
	public ArrayList<String> obtainBuddies();
	public void addMenuInterface(MenuInterface mi);
	public void send(String msg);
	public String getUsername();
	public void addChatWindow(ChatWindow cw);
	public void removeChatWindow(ChatWindow cw);
}