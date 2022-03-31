package server;

public interface Server {
	public void authorized(ClientMessageHandler cmh);
	public boolean userExists(String username);
	public User getUser(String username);
	public void createUser(String username, String password);
}