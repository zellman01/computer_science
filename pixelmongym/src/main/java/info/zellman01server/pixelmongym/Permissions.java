package info.zellman01server.pixelmongym;

public class Permissions {
	// Given to everyone
	public final static String memberPermission = "gym.member";
	
	// Given only to the people who can be a gym leader
	public final static String leaderPermission = "gym.leader";
	
	// Given to only the five members in the elite 4
	public final static String elite4Permission = "gym.e4";
	
	// Given only to the champion
	public final static String championPermission = "gym.champion";
	
	// Given to staff members of the server, for general plugin management (Mods and higher)
	public final static String staffPermission = "gym.staff";
	
	// Given to high ranked staff members (admins and higher)
	public final static String adminPermission = "gym.admin";
	
	// Given only to the owners of the server, for direct management (like database updates)
	public final static String ownerPermission = "gym.owner";
	
	// Given only to players who are banned from performing actions in this plugin
	public final static String bannedPermission = "gym.banned";
}
