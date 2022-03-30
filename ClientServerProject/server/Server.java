package server;

import server.Handler;

// TODO: Tell each client to shutdown

public interface Server {
	/**
	 * @deprecated Use other function to fully shutdown the server
	*/
	@Deprecated
	public void serverShutdown();
	
	public void serverShutdown(Handler h);
	
	public void removeHandler(Handler h);
}