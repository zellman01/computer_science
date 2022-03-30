package server;

import server.Handler;

public interface Server {
	public void serverShutdown();
	public void serverShutdown(Handler h);
	public void removeHandler(Handler h);
}