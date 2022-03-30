package server.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLConnector {
	private String userName = "", password = "";
	
	private final String IP = "", NAME = "/"; // Put database name after /
	private final int PORT = 1234;
	
	private Connection getConnection() throws SQLException {
		Conection conn = null;
		Properties prop = new Properties();
		prop.put("user", userName);
		prop.put("password", password);
		password = null;
		String sqlConn = ":jbdc:mysql://" + IP + ":" + PORT + NAME";
		return conn;
	}
	
	protected Connection getConnection() {
		Connection conn = null;
		try {
			conn = getConnection();
		} catch (SQLException e) {
			e.printStackTrace(); // TODO: Shutdown server due to error
		}
		return conn;
	}
}