package info.zellman01server.pixelmongym;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.service.sql.SqlService;

import com.google.common.reflect.TypeToken;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class Config {
	public final static TypeToken<Config> type = TypeToken.of(Config.class);
	@Setting(comment="Accepts either H2 or mySQL.")
	private String engine = "mysql";
	
	@Setting(comment="Username of the database")
	private String username = "username";
	
	@Setting(comment="Password of the username of the database")
	private String password = "password";
	
	@Setting(comment="Sets the address of the database to connect to")
	private String host = "127.0.0.1";
	
	@Setting(comment="The actual database to connect to")
	private String database = "database";
	
	@Setting(comment="An already existent table on the database to check against if it is active. It is better to set this to the a permission table inside of the database.")
	private String table = "table";
	
	@Setting(comment="Sets the port of the database to connect to")
	private int port = 2306;
	
	/**
	 * Gets the string to connect to the database with
	 * @return The string for database connection
	 */
	public String databaseAccess() {
		SqlService service = Sponge.getServiceManager().provide(SqlService.class).get();
		String url = service.getConnectionUrlFromAlias("").orElse("jdbc:" + engine + "://" + username + ":" + password + "@" + host + ":" 
				+ port + "/" + database);
		return url;
	}
	
	public void updateEngine(String eng) {
		engine = eng;
	}
	
	public void updateUser(String user) {
		username = user;
	}
	
	public void updatePassword(String pass) {
		password = pass;
	}
	
	public void updateHost(String host) {
		this.host = host;
	}
	
	public void updateDatabase(String db) {
		database = db;
	}
	
	public void updateTable(String table) {
		this.table = table;
	}
	
	public void updatePort(int port) {
		this.port = port;
	}
}