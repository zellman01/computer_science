package info.zellman01server.pixelmongym.utilities;

import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.service.sql.SqlService;

import info.zellman01server.pixelmongym.Config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class SQL {
	private SqlService sql;
	private Config configInfo;
	private Logger logger;

	public SQL(Config config, Logger log) {
		configInfo = config;
		logger = log;
	}

	private DataSource getDataSource(String url) throws SQLException {
		if (sql == null) {
			sql = Sponge.getServiceManager().provide(SqlService.class).get();
		}
		return sql.getDataSource(url);
	}

	public void createTables()  throws SQLException {
		ArrayList<String> list = new ArrayList<>();
		list.add("CREATE TABLE `gyms" + configInfo.tablePrefix + "`("
				+ "ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "OwnerUUID VARCHAR(36) NOT NULL UNIQUE,"
				+ "OwnerUsername VARCHAR(16) NOT NULL,"
				+ "GymName VARCHAR(50) NOT NULL,"
				+ "Status VARCHAR(30) NOT NULL DEFAULT 'OFF');");
		list.add("CREATE TABLE `elite4" + configInfo.tablePrefix + "`("
				+ "ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "UUID VARCHAR(36) UNIQUE NOT NULL,"
				+ "username VARCHAR(16) NOT NULL,"
				+ "position INT NOT NULL UNIQUE);"); // Position 5 means champion
		list.add("ALTER TABLE `elite4" + configInfo.tablePrefix + "` ADD INDEX(`username`);");
		/*list.add("CREATE TABLE `gym_teams" + configInfo.tablePrefix + "`("
				+ "UUID VARCHAR(36) UNIQUE NOT NULL PRIMARY KEY,"
				+ "numPokemon INT NOT NULL DEFAULT '0',"
				+ "Pokemon1_Species VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon1_Ability VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon1_Level VARCHAR(30) NOT NULL DEFAULT '1',"
				+ "Pokemon1_IV VARCHAR(30) NOT NULL DEFAULT '0/0/0/0/0/0',"
				+ "Pokemon1_EV VARCHAR(30) NOT NULL DEFAULT '0/0/0/0/0/0',"
				+ "Pokemon1_Stats VARCHAR(30) NOT NULL DEFAULT '0/0/0/0/0/0',"
				+ "Pokemon1_DynamaxLevel VARCHAR(30) NOT NULL DEFAULT '0',"
				+ "Pokemon1_Shiny VARCHAR(30) NOT NULL DEFAULT 'N',"
				+ "Pokemon1_Gigantamax VARCHAR(30) NOT NULL DEFAULT 'N',"
				+ "Pokemon1_Gender VARCHAR(30) NOT NULL DEFAULT 'M',"
				+ "Pokemon1_Friendship VARCHAR(30) NOT NULL DEFAULT '0',"
				+ "Pokemon1_Nature VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon1_Move1 VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon1_Move2 VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon1_Move3 VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon1_Move4 VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon2_Species VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon2_Ability VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon2_Level VARCHAR(30) NOT NULL DEFAULT '1',"
				+ "Pokemon2_IV VARCHAR(30) NOT NULL DEFAULT '0/0/0/0/0/0',"
				+ "Pokemon2_EV VARCHAR(30) NOT NULL DEFAULT '0/0/0/0/0/0',"
				+ "Pokemon2_Stats VARCHAR(30) NOT NULL DEFAULT '0/0/0/0/0/0',"
				+ "Pokemon2_DynamaxLevel VARCHAR(30) NOT NULL DEFAULT '0',"
				+ "Pokemon2_Shiny VARCHAR(30) NOT NULL DEFAULT 'N',"
				+ "Pokemon2_Gigantamax VARCHAR(30) NOT NULL DEFAULT 'N',"
				+ "Pokemon2_Gender VARCHAR(30) NOT NULL DEFAULT 'M',"
				+ "Pokemon2_Friendship VARCHAR(30) NOT NULL DEFAULT '0',"
				+ "Pokemon2_Nature VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon2_Move1 VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon2_Move2 VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon2_Move3 VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon2_Move4 VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon3_Species VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon3_Ability VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon3_Level VARCHAR(30) NOT NULL DEFAULT '1',"
				+ "Pokemon3_IV VARCHAR(30) NOT NULL DEFAULT '0/0/0/0/0/0',"
				+ "Pokemon3_EV VARCHAR(30) NOT NULL DEFAULT '0/0/0/0/0/0',"
				+ "Pokemon3_Stats VARCHAR(30) NOT NULL DEFAULT '0/0/0/0/0/0',"
				+ "Pokemon3_DynamaxLevel VARCHAR(30) NOT NULL DEFAULT '0',"
				+ "Pokemon3_Shiny VARCHAR(30) NOT NULL DEFAULT 'N',"
				+ "Pokemon3_Gigantamax VARCHAR(30) NOT NULL DEFAULT 'N',"
				+ "Pokemon3_Gender VARCHAR(30) NOT NULL DEFAULT 'M',"
				+ "Pokemon3_Friendship VARCHAR(30) NOT NULL DEFAULT '0',"
				+ "Pokemon3_Nature VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon3_Move1 VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon3_Move2 VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon3_Move3 VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon3_Move4 VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon4_Species VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon4_Ability VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon4_Level VARCHAR(30) NOT NULL DEFAULT '1',"
				+ "Pokemon4_IV VARCHAR(30) NOT NULL DEFAULT '0/0/0/0/0/0',"
				+ "Pokemon4_EV VARCHAR(30) NOT NULL DEFAULT '0/0/0/0/0/0',"
				+ "Pokemon4_Stats VARCHAR(30) NOT NULL DEFAULT '0/0/0/0/0/0',"
				+ "Pokemon4_DynamaxLevel VARCHAR(30) NOT NULL DEFAULT '0',"
				+ "Pokemon4_Shiny VARCHAR(30) NOT NULL DEFAULT 'N',"
				+ "Pokemon4_Gigantamax VARCHAR(30) NOT NULL DEFAULT 'N',"
				+ "Pokemon4_Gender VARCHAR(30) NOT NULL DEFAULT 'M',"
				+ "Pokemon4_Friendship VARCHAR(30) NOT NULL DEFAULT '0',"
				+ "Pokemon4_Nature VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon4_Move1 VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon4_Move2 VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon4_Move3 VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon4_Move4 VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon5_Species VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon5_Ability VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon5_Level VARCHAR(30) NOT NULL DEFAULT '1',"
				+ "Pokemon5_IV VARCHAR(30) NOT NULL DEFAULT '0/0/0/0/0/0',"
				+ "Pokemon5_EV VARCHAR(30) NOT NULL DEFAULT '0/0/0/0/0/0',"
				+ "Pokemon5_Stats VARCHAR(30) NOT NULL DEFAULT '0/0/0/0/0/0',"
				+ "Pokemon5_DynamaxLevel VARCHAR(30) NOT NULL DEFAULT '0',"
				+ "Pokemon5_Shiny VARCHAR(30) NOT NULL DEFAULT 'N',"
				+ "Pokemon5_Gigantamax VARCHAR(30) NOT NULL DEFAULT 'N',"
				+ "Pokemon5_Gender VARCHAR(30) NOT NULL DEFAULT 'M',"
				+ "Pokemon5_Friendship VARCHAR(30) NOT NULL DEFAULT '0',"
				+ "Pokemon5_Nature VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon5_Move1 VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon5_Move2 VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon5_Move3 VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon5_Move4 VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon6_Species VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon6_Ability VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon6_Level VARCHAR(30) NOT NULL DEFAULT '1',"
				+ "Pokemon6_IV VARCHAR(30) NOT NULL DEFAULT '0/0/0/0/0/0',"
				+ "Pokemon6_EV VARCHAR(30) NOT NULL DEFAULT '0/0/0/0/0/0',"
				+ "Pokemon6_Stats VARCHAR(30) NOT NULL DEFAULT '0/0/0/0/0/0',"
				+ "Pokemon6_DynamaxLevel VARCHAR(30) NOT NULL DEFAULT '0',"
				+ "Pokemon6_Shiny VARCHAR(30) NOT NULL DEFAULT 'N',"
				+ "Pokemon6_Gigantamax VARCHAR(30) NOT NULL DEFAULT 'N',"
				+ "Pokemon6_Gender VARCHAR(30) NOT NULL DEFAULT 'M',"
				+ "Pokemon6_Friendship VARCHAR(30) NOT NULL DEFAULT '0',"
				+ "Pokemon6_Nature VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon6_Move1 VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon6_Move2 VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon6_Move3 VARCHAR(30) NOT NULL DEFAULT 'NA',"
				+ "Pokemon6_Move4 VARCHAR(30) NOT NULL DEFAULT 'NA');");*/
		String url = configInfo.databaseAccess();
		Connection conn = getDataSource(url).getConnection();
		for (int i = 0; i < list.size(); i++) {
			PreparedStatement stmt = conn.prepareStatement(list.get(i));
			try {
				stmt.execute();
			} catch (SQLException e) {
				conn.close();
				throw e; // Rethrow the same error after closing the connection
			}
		}
		conn.close();
	}

	public void addGym(String gymName, String UUID, String username) throws SQLException {
		String url = configInfo.databaseAccess();
		String sql = "INSERT INTO `gyms" + configInfo.tablePrefix + "`(`ownerUUID`,`OwnerUsername`, `GymName`) VALUES ('" + UUID + "','" + username + "','" + gymName + "');";

		Connection conn = getDataSource(url).getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		try {
			stmt.execute();
		} catch (SQLException e) {
			conn.close();
			throw e; // Rethrow the same error after closing the connection
		}

		/*sql = "INSERT INTO `gym_teams" + configInfo.tablePrefix + "`" + "(`UUID`) VALUES ('" + UUID + "');";
		stmt = conn.prepareStatement(sql);
		try {
			stmt.execute();
		} catch (SQLException e) {
			conn.close();
			throw e; // Rethrow same error after closing connection
		}*/
		conn.close();
	}

	public boolean gymOwned(String UUID) throws SQLException {
		String url = configInfo.databaseAccess();
		String sql = "SELECT * FROM `gyms" + configInfo.tablePrefix + "`;";

		Connection conn = getDataSource(url).getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		try {
			stmt.execute();
		} catch (SQLException e) {
			conn.close();
			throw e; // Rethrow the same error after closing the connection
		}
		ResultSet rs = stmt.getResultSet();
		while (rs.next()) {
			if (rs.getString("ownerUUID").equals(UUID)) {
				conn.close();
				return true;
			}
		}
		conn.close();
		return false;
	}

	public void deleteGym(String UUID) throws SQLException {
		String url = configInfo.databaseAccess();
		String sql = "DELETE FROM `gyms" + configInfo.tablePrefix + "` WHERE `OwnerUUID`==" + UUID + ";";
		//String sql2 = "SELECT * FROM `gyms_teams" + configInfo.tablePrefix + "` WHERE `UUID`==" + UUID + ";";

		Connection conn = getDataSource(url).getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		try {
			stmt.execute();
		} catch (SQLException e) {
			conn.close();
			throw e;
		}
		/*stmt = conn.prepareStatement(sql2);
		try {
			stmt.execute();
		} catch (SQLException e) {
			conn.close();
			throw e;
		}
		ResultSet rs = stmt.getResultSet();
		if (rs.next()) {
			String sql3 = "DELETE FROM `gyms_teams" + configInfo.tablePrefix + "` WHERE `UUID`==" + UUID + ";";
			stmt = conn.prepareStatement(sql3);
			stmt.execute();
		}*/
		conn.close();
	}

	public boolean gymStatus(String UUID) throws SQLException {
		String url = configInfo.databaseAccess();
		String sql = "SELECT * FROM `gyms" + configInfo.tablePrefix + "` WHERE `OwnerUUID`=='" + UUID + "';";

		Connection conn = getDataSource(url).getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		try {
			stmt.execute();
		} catch (SQLException e) {
			conn.close();
			throw e;
		}
		ResultSet rs = stmt.getResultSet();
		if (rs.next()) {
			return rs.getString(5) == "ON";
		}

		conn.close();
		return false;
	}

	/**
	 * Switch the gym status to open or close, depending on what is in the database already.
	 * @param UUID The player's UUID
	 * @throws SQLException If something goes wrong with the SQL database
	 */
	public void switchGymStatus(String UUID) throws SQLException {
		String url = configInfo.databaseAccess();
		String sql = "SELECT * FROM `gyms" + configInfo.tablePrefix + "` WHERE `OwnerUUID`=='" + UUID + "';";
		boolean test = false;

		Connection conn = getDataSource(url).getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		try {
			stmt.execute();
		} catch (SQLException e) {
			conn.close();
			throw e;
		}
		ResultSet rs = stmt.getResultSet();
		if (rs.next()) {
			test = rs.getBoolean(5);
		}

		conn.close();

		String sql1 = "UPDATE `gyms" + configInfo.tablePrefix + "` SET `Status` = ";
		if (test) sql1 += "OFF";
		else sql1 += "ON";

		sql1 += "WHERE `OwnerUUID`=='" + UUID + "';";

		conn = getDataSource(url).getConnection();
		stmt = conn.prepareStatement(sql1);
		try {
			stmt.execute();
		} catch (SQLException e) {
			conn.close();
			throw e;
		}
		conn.close();
	}

	public String getGymOwner(String gymName) throws SQLException {
		String url = configInfo.databaseAccess();
		String sql = "SELECT * FROM `gyms" + configInfo.tablePrefix + "` WHERE `GymName`=='" + gymName + "';";

		Connection conn = getDataSource(url).getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		try {
			stmt.execute();
		} catch (SQLException e) {
			conn.close();
			throw e;
		}
		conn.close();
		ResultSet rs = stmt.getResultSet();
		if (rs.next()) {
			return rs.getString(3);
		}
		return "ERROR1/2";
	}

	public String getGymOwnerID(String gymName) throws SQLException {
		String url = configInfo.databaseAccess();
		String sql = "SELECT * FROM `gyms" + configInfo.tablePrefix + "` WHERE `GymName`=='" + gymName + "';";

		Connection conn = getDataSource(url).getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		try {
			stmt.execute();
		} catch (SQLException e) {
			conn.close();
			throw e;
		}
		conn.close();
		ResultSet rs = stmt.getResultSet();
		if (rs.next()) {
			return rs.getString(2);
		}
		return "ERROR1/2";
	}

	/**
	 * Gets the list of all gyms
	 * @return ArrayList of all gyms registered
	 * @throws SQLException
	 */
	public ArrayList<String> gymList() throws SQLException {
		String url = configInfo.databaseAccess();
		String sql = "SELECT * FROM `gyms" + configInfo.tablePrefix + "`;";
		ArrayList<String> result = new ArrayList<>();

		Connection conn = getDataSource(url).getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		try {
			stmt.execute();
		} catch (SQLException e) {
			conn.close();
			throw e; // Rethrow the same error after closing the connection
		}
		ResultSet rs = stmt.getResultSet();
		while (rs.next()) {
			String add = "Gym: " + rs.getString("GymName") + " Owner: " + rs.getString("OwnerUsername");
			result.add(add);
		}
		conn.close();
		return result;
	}

	public ArrayList<String> e4List() throws SQLException {
		String url = configInfo.databaseAccess();
		String sql = "SELECT * FROM `elite4" + configInfo.tablePrefix + "`;";

		Connection conn = getDataSource(url).getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		try {
			stmt.execute();
		} catch (SQLException e) {
			conn.close();
			throw e;
		}
		ResultSet rs = stmt.getResultSet();
		ArrayList<String> result = new ArrayList<>();
		while (rs.next()) {
			String add = "Elite 4 membner: " + rs.getString(3) + " Position: ";
			String posNum = rs.getString(4);
			switch (posNum) {
			case "5":
				add += "Champion";
				break;
			default:
				add += posNum;
				break;
			}
			result.add(add);
		}
		return result;
	}

	/**
	 * Adds the gym teams into the gym team table
	 * @param teamArray Must contain the information in the same order as the table
	 * @deprecated Logistically impossible. Relying on the players to put their gym team in before a gym battle
	 */
	/*public void addGymTeam(String[] teamArray) throws SQLException {
		final String table = "`gym_teams" + configInfo.tablePrefix + "`";
		final int totalPokemon = Integer.parseInt(teamArray[1]);
		int arrayStartIndex = 2, processedPokemon = 0; // Processed Pokemon are ones already in the statements (used as index for row names)

		Connection conn = getDataSource(configInfo.databaseAccess()).getConnection();

		if (processedPokemon == totalPokemon) return; // Don't even bother if the total pokemon on the team is not more than 0 (should never be possible, but just in case)

		String sql1 = "UPDATE " + table + " SET numPokemon = '" + teamArray[1] + "` WHERE UUID=='" + teamArray[0] + "';";
		PreparedStatement stmt = conn.prepareStatement(sql1);
		try {
		stmt.execute(); // Create the record in the database
		} catch (SQLException e) {
			conn.close();
			throw e; // Rethrow the same error after closing the connection
		}

		while (processedPokemon < totalPokemon) {
			processedPokemon++;
			String temp = "Pokemon" + processedPokemon + "_";
			String statement = "UPDATE " + table;
			statement += "SET " + temp + "Species = '" + teamArray[arrayStartIndex] + "',";

			statement += temp + "Ability = '" + teamArray[arrayStartIndex+1] + "',";

			statement += temp + "Level = '" + teamArray[arrayStartIndex+2] + "',";

			statement += temp + "IV = '" + teamArray[arrayStartIndex+3] + "',";

			statement += temp + "EV = '" + teamArray[arrayStartIndex+4] + "',";

			statement += temp + "Stats = '" + teamArray[arrayStartIndex+5] + "',";

			statement += temp + "DynamaxLevel = '" + teamArray[arrayStartIndex+6] + "',";

			statement += temp + "Shiny = '" + teamArray[arrayStartIndex+7] + "',";

			statement += temp + "Gigantamax = '" + teamArray[arrayStartIndex+8] + "',";

			statement += temp + "Gender = '" + teamArray[arrayStartIndex+9] + "',";

			statement += temp + "Friendship = '" + teamArray[arrayStartIndex+10] + "',";

			// Add nature
			statement += temp + "Nature = '" + teamArray[arrayStartIndex+11] + "',";

			statement += temp + "Move1 = '" + teamArray[arrayStartIndex+12] + "',";

			statement += temp + "Move2 = '" + teamArray[arrayStartIndex+13] + "',";

			statement += temp + "Move3 = '" + teamArray[arrayStartIndex+14] + "',";

			statement += temp + "Move4 = '" + teamArray[arrayStartIndex+15] + "',";

			statement += "WHERE UUID=='" + teamArray[0] + "';";

			arrayStartIndex += 16; // Should set it to the next Pokemon

			stmt = conn.prepareStatement(statement);
			try {
				stmt.execute();
			} catch (SQLException e) {
				conn.close();
				logger.error("Unable to fully update gym team record for " + teamArray[0] + ". Manual removal of the record is suggested.");
				throw e;
			}
		}
		// After the above is done, it should have written the data over into the database
		conn.close();
	}*/
}
