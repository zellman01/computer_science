package game.bom.sqlConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import game.bom.error.ErrorCodes;
import game.bom.global.Globals;

public class ConnectSQL {
	private final String userName = "bom_user", password = "*58545256a*"; // SECURITY ISSUE 
	
	private Connection getConnection() throws SQLException {

	    Connection conn = null;
	    Properties connectionProps = new Properties();
	    connectionProps.put("user", this.userName );
	    connectionProps.put("password", this.password);
	    String sqlConn = "jdbc:mariadb://" +
                Globals.DATABASE_IP +
                ":3306/brawl_of_minds";
	    conn = DriverManager.getConnection(sqlConn, connectionProps);
	    connectionProps.clear();
	    System.out.println("Connected to database");
	    return conn;
	}
	
	protected Connection obtaionConnection() { // To handle the error internally in this class
		// Protected because nothing outside of this package should be using this
		Connection conn = null;
		try {
			conn = getConnection();
		} catch(SQLException e) {
			e.printStackTrace();
			System.err.println(ErrorCodes.E300);
			//System.exit(300);
		}
		return conn;
	}
}
