package game.bom.sqlConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import game.bom.sqlConnection.SQL;

public class Statements {
	private SQL database;

	public Statements(SQL db) {
		database = db;
	}

	public ResultSet execStatements(String statement) {
		ResultSet rs = null;
		Statement stmt;
		try {
			stmt = database.getConnect().createStatement();
			//stmt.executeUpdate("SELECT * FROM `brawl_of_minds`.`card_data`");
			stmt.executeUpdate(statement);
			rs = stmt.getResultSet();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public String format(ResultSet rs) throws SQLException {
		String str = "";
		while (rs.next()) {
			str += "Card name: " + rs.getString(2);
			str += "\n ID: " + rs.getInt(1);
			str += "\n HP: " + rs.getInt(3);
			str += "\n ATK: " + rs.getInt(4);
			str += "\n Type: " + rs.getString(5) + "\n";
		}
		if (str.equals(""))
			str = "No results found.";
		return str;
	}
}
