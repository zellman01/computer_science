package game.bom.testing;

import java.sql.ResultSet;
import java.sql.SQLException;

import game.bom.sqlConnection.SQL;
import game.bom.sqlConnection.Statements;


public class Tests {
	public static void main(String[] args) {
		SQL test = new SQL();
		
		Statements stmt = new Statements(test);
		
		ResultSet rs = stmt.execStatements("SELECT * FROM card_data");
		
		try {
			System.out.println(stmt.format(rs));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test.endConnection();
	}
}
