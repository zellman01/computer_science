package info.zellman01server.pixelmongym;

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
}