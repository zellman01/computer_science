package server.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class SQL {
	private Connection connection;
	
	public SQL() {
		startConnection();
	}
	
	protected Connection getConnection() { return connection; }
	
	private void startConnection() {
		connection = new SQLConnector().obtainConnection();
		// Check if the connection is live
	}
	
	protected void endConnection() {
		try {
			if (!connection.isClosed())
				connection.close();
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
	}
}