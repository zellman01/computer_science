package server.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLStatement {
	private SQL database;

	public Statements(SQL db) {
		database = db;
	}

	public ResultSet execStatements(String statement) throws SQLException {
		ResultSet rs = null;
		PreparedStatement stmt;
		Connection con = database.getConnect();
		stmt = con.prepareStatement(statement);
		stmt.execute();
		rs = stmt.getResultSet();
		stmt.close();
		return rs;
	}

	public ResultSet execStatements(String statement, String param) throws SQLException {
		ResultSet rs = null;
		PreparedStatement stmt;
		Connection con = database.getConnect();
		stmt = con.prepareStatement(statement);
		stmt.setString(1, param);
		stmt.execute();
		rs = stmt.getResultSet();
		return rs;
	}

	public ResultSet execStatements(String statement, String[] paramList) throws SQLException {
		ResultSet rs = null;
		PreparedStatement stmt;
		Connection con = database.getConnect();
		stmt = con.prepareStatement(statement);
		for (int i = 0; i < paramList.length; i++) {
			stmt.setString(i+1, paramList[i]);
		}
		stmt.execute();
		rs = stmt.getResultSet();
		return rs;
	}