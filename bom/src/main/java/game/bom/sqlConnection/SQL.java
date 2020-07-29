package game.bom.sqlConnection;

import java.sql.Connection;
import java.sql.SQLException;

import game.bom.sqlConnection.ConnectSQL;
import game.bom.error.ErrorCodes;

public class SQL {
	private Connection connect;

	public SQL() {
		startConnection();
	}

	public Connection getConnect() { return this.connect; }

	public void endConnection() {
		try {
			if (!connect.isClosed()) {
				connect.close();
				System.out.println("Database connection closed.");
			}
		} catch (SQLException | NullPointerException e) {
			System.err.println(ErrorCodes.E900);
			//System.exit(900);
		}
	}

	public void startConnection() {
		connect = new ConnectSQL().obtaionConnection();
		checkConnection();
	}
	
	private void checkConnection() {
		try { // Probably a better way to see if the connection is null
			if (connect != null);
		} catch(NullPointerException e) {
			System.err.println(ErrorCodes.E300);
			//System.exit(300);
		}
	}
}
