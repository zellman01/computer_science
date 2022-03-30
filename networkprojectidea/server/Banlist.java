package server;

import java.util.ArrayList;

public class Banlist {
	private ArrayList<String> ips;
	private ArrayList<String> users;
	
	public Banlist() {
		ips = new ArrayList<String>();
		users = new ArrayList<String>();
	}
	
	protected void addIPBan(String ip) {
		ips.add(ip);
	}
	
	protected void addUserBan(String user) {
		users.add(user);
	}
	
	public boolean checkIPBan(String ip) {
		return ips.contains(ip);
	}
	
	public boolean checkUserBan(String user) {
		return users.contains(user);
	}
}