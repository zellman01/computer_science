package client;

import java.io.IOException;

public interface MessageHandler {
	public void register(String username, String password, Login login) throws IOException ;
	public void login(String username, String password, Login login) throws IOException;
}