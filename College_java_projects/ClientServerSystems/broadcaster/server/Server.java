package server;

public interface Server {
	public void removeClient(ClientMessageHandler cm);
	public void broadcast(String str, ClientMessageHandler tmp);
}