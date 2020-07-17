package game.bom.sqlConnection;

import java.sql.Connection;
import java.sql.SQLException;

import game.bom.sqlConnection.ConnectSQL;

public class SQL {
	private Connection connect;
	
	public SQL() {
		connect = new ConnectSQL().obtaionConnection();
		try { // Probably a better way to see if the connection is null
			if (connect != null);
		} catch(NullPointerException e) {
			System.err.println("There was an error with the SQL database");
			System.exit(300);
		}
	}
	
	public Connection getConnect() { return this.connect; }
	
	public void endConnection() {
		try {
			connect.close();
			System.out.println("Database connection closed.");
		} catch (SQLException e) {
			System.err.println("Database connection was already closed.");
			System.exit(301);
		}
	}
}
