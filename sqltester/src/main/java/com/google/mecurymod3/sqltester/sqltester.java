package com.google.mecurymod3.sqltester;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.asset.Asset;
//import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.GameReloadEvent;
import org.spongepowered.api.event.game.state.GameAboutToStartServerEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
//import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.service.sql.SqlService;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.channel.MessageChannel;
import org.spongepowered.api.text.format.TextColors;

import com.google.inject.Inject;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;


@Plugin(id = "sqltester", name = "SQL Tester", version = "0.1.91", description = "Tests a SQL server from the given configuration.")
public class sqltester  {
	private boolean successStartup = true, serverStarted = false, failedConnection = false, noCred = false;
	@Inject
	@DefaultConfig(sharedRoot = false)
	private ConfigurationLoader<CommentedConfigurationNode> loader;
	private Config config;

	@Inject
	@DefaultConfig(sharedRoot = false)
	private Path path;

	@Inject
	private Logger logger;

	@Inject
	private Game game;

	private SqlService sql;
	private Task task;

	String engine, username, password, host, database, table;
	int port, retestMinutes;
	private double confVersion = 1.2;
	private boolean configLoaded = false;

	@Listener
	public void preInit(GamePreInitializationEvent event) throws IOException, ObjectMappingException {
		Asset conf = game.getAssetManager().getAsset(this, "default.conf").get();
		ConfigurationNode root;
		logger.info("Loading SQL server settings...");
		try {
			if (!Files.exists(path))
				conf.copyToFile(path);
			root = loader.load();
			if (root.getNode("version").getDouble() < confVersion) {
				root.mergeValuesFrom(loadDefault());
				root.getNode("version").setValue(confVersion);
				loader.save(root);
			}
			if (root.getNode("version").getDouble() > confVersion)
				logger.warn("You may potentially be using a higher version of this plugin. The configuration file may not work correctly.");
			config = root.getValue(Config.type);
			engine = config.getEngine();
			username = config.getUsername();
			password = config.getPassword();
			if (password.equals("password")) {
				logger.warn("Choose a database account with a stronger password.");
				disablePlugin();
			}
			host = config.getHost();
			database = config.getDatabase();
			table = config.getTable();
			port = config.getPort();
			retestMinutes = config.getRetestTime();
			if (retestMinutes < 1)
				logger.warn("retestMinutes is set to less than 1. This may cause a slight impact on preformance.");
			configLoaded = true;
		} catch(IOException ex) {
			logger.error("Cannot load default configuration file.");
			disablePlugin();
			throw ex;
		} catch (ObjectMappingException ex) {
			logger.error("Invalid config file!");
			disablePlugin();
			throw ex;
		}
		if (configLoaded)
			logger.info("Configuration loaded successfully.");
	}

	private void disablePlugin() {
		logger.error("Cannot load plugin. Disabling plugin.");
		noCred = true;
		game.getEventManager().unregisterPluginListeners(this);

	}

	private ConfigurationNode loadDefault() throws IOException {
		return HoconConfigurationLoader.builder().setURL(game.getAssetManager().getAsset(this, "default.conf").get().getUrl()).build().load(loader.getDefaultOptions());
	}

	@Listener
	public void onServerStart(GameAboutToStartServerEvent event) {
		logger.info("Testing SQL settings...");
		SQLTest();
	}


	/*CommandSpec Functions = CommandSpec.builder()
			.description(Text.of("Disables server testing"))
			.permission("sqltester.command.admin.functionality")
			.executor(new TestFunction())
			.build();

	// Command registration
	//@Listener
	//public void onServerStart(GameStartingServerEvent event) {
		//Sponge.getCommandManager().register(this, Functions, "sqltest");
	//}*/

	@Listener
	public void reload(GameReloadEvent event) throws IOException, ObjectMappingException {
		ConfigurationNode root;
		logger.info("Loading new SQL database configuration...");
		try {
		root = loader.load();
		if (root.getNode("version").getDouble() > confVersion)
			logger.warn("You may potentially be using a higher version of this plugin. The configuration file may not work correctly.");
		config = root.getValue(Config.type);
		engine = config.getEngine();
		username = config.getUsername();
		password = config.getPassword();
		if (password == "password") {
			logger.warn("Choose a database account with a stronger password.");
			disablePlugin();
		}
		host = config.getHost();
		database = config.getDatabase();
		table = config.getTable();
		port = config.getPort();
		retestMinutes = config.getRetestTime();
		if (retestMinutes < 1)
			logger.warn("retestMinutes is set to less than 1. This may cause a slight impact on preformance.");
		configLoaded = true;
		} catch(IOException ex) {
			logger.error("Cannot load default configuration file.");
			disablePlugin();
			throw ex;
		} catch (ObjectMappingException ex) {
			logger.error("Invalid config file!");
			disablePlugin();
			throw ex;
		}
		if (failedConnection)
			MessageChannel.TO_ALL.send(Text.builder("[Broadcast] FATAL ERROR: DATABASE UNDETECTIBLE.")
					.color(TextColors.RED).build());
		else
			MessageChannel.TO_ALL.send(Text.builder("[Broadcast] DATABASE DETECTED.")
					.color(TextColors.GREEN).build());
	}

	public void SQLTest() {
		try {
			SqlService service = Sponge.getServiceManager().provide(SqlService.class).get();
			String url = service.getConnectionUrlFromAlias("").orElse("jdbc:" + engine + "://" + username + ":" + password + "@" + host + ":" 
					+ port + "/" + database);
			test(url);
		} catch(Exception ex) {
			if (!noCred)
				logger.warn("ERROR: Could not connect to the database provided in configuration.");
			logger.debug("SQL error: " + ex);
			successStartup = false;
		}
	}

	private void test(String url) {
		String query = "SELECT * FROM " + table;
		try {
			myMethodThatQueries(url, query);
			logger.info("Connection successful.");
			if (failedConnection)
				MessageChannel.TO_ALL.send(Text.builder("[Broadcast] DATABASE DETECTED.")
						.color(TextColors.GREEN).build());
			failedConnection = false;
		} catch(Exception ex) {
			if (!noCred)
				logger.warn("ERROR: Could not connect to the database provided in configuration.");
			logger.debug("SQL error: " + ex);
			if (serverStarted) {
				if (!failedConnection)
					MessageChannel.TO_ALL.send(Text.builder("[Broadcast] FATAL ERROR: DATABASE UNDETECTIBLE.")
							.color(TextColors.RED).build());
			} else
				logger.warn("Database was unable to be detected. This should be fixed as soon as possible.");
			failedConnection = true;
		}
	}

	@Listener
	public void onServerStart(GameStartedServerEvent event) {
		if (successStartup)
			logger.info("Successfully running.");
		else {
			if (!noCred)
				logger.error("Database was unable to be found/connected to.");
			MessageChannel.TO_ALL.send(Text.builder("[Broadcast] FATAL ERROR: DATABASE UNDETECTIBLE.")
					.color(TextColors.RED).build());
			failedConnection = true;
		}
		serverStarted = true;
		task = Task.builder().execute((new Runnable() {
			public void run() {
				SQLTest();
			}})).interval(retestMinutes, TimeUnit.MINUTES).name("SQL Tester").async().submit(this);
	}

	@Listener
	public void onServerStop(GameStoppedServerEvent event) {
		logger.info("Shutting down...");
		task.cancel();
		serverStarted = false;
		logger.info("Successfully shut down.");
	}

	public DataSource getDataSource(String jdbcUrl) throws SQLException {
		if (sql == null) {
			sql = Sponge.getServiceManager().provide(SqlService.class).get();
		}
		return sql.getDataSource(jdbcUrl);
	}

	// Later on
	public void myMethodThatQueries(String uri, String sql) throws SQLException {
		Connection conn = null;
		try {
			conn = getDataSource(uri).getConnection();
		} catch(final Exception e) {
			logger.error("Invalid credentials supplied.");
			disablePlugin();
			throw e;
		}
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet results = stmt.executeQuery();
		
		while (results.next()) {
			
		}
		conn.close();
		if (!conn.isClosed()) {
			logger.warn("Database connection could not be closed for some reason. This may lead to a memory leak issue.");
		}
	}
}
