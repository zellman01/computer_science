package game.bom.sqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import game.bom.sqlConnection.SQL;

/**
 * SQL Statements class
 * @author zellman01
 * @version 0.1.0
 * @since 0.1.0
 */
public class Statements {
	private SQL database;

	public Statements(SQL db) {
		database = db;
	}

	public ResultSet execStatements(String statement) {
		ResultSet rs = null;
		PreparedStatement stmt;
		try {
			Connection con = database.getConnect();
			stmt = con.prepareStatement(statement);
			stmt.execute();
			rs = stmt.getResultSet();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet execStatements(String statement, String param) {
		ResultSet rs = null;
		PreparedStatement stmt;
		try {
			Connection con = database.getConnect();
			stmt = con.prepareStatement(statement);
			stmt.setString(1, param);
			stmt.execute();
			rs = stmt.getResultSet();
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
