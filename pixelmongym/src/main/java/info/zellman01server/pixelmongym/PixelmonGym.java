package info.zellman01server.pixelmongym;

import org.spongepowered.api.plugin.Plugin;
//import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;

import com.google.inject.Inject;

import info.zellman01server.pixelmongym.commands.e4.Elite4Messager;
import info.zellman01server.pixelmongym.commands.e4.EliteFourClose;
import info.zellman01server.pixelmongym.commands.e4.EliteFourList;
import info.zellman01server.pixelmongym.commands.e4.EliteFourOpen;
import info.zellman01server.pixelmongym.commands.gym.AcceptGymChallenge;
import info.zellman01server.pixelmongym.commands.gym.CloseGymCommand;
import info.zellman01server.pixelmongym.commands.gym.CreateGym;
import info.zellman01server.pixelmongym.commands.gym.DenyGymChallenge;
import info.zellman01server.pixelmongym.commands.gym.GymChallenge;
import info.zellman01server.pixelmongym.commands.gym.GymList;
import info.zellman01server.pixelmongym.commands.gym.OpenGymCommand;
import info.zellman01server.pixelmongym.commands.staff.DeleteGym;
import info.zellman01server.pixelmongym.commands.staff.NewChampion;
import info.zellman01server.pixelmongym.utilities.DeprecatedCommand;
import info.zellman01server.pixelmongym.utilities.SQL;

import java.sql.SQLException;

import org.slf4j.Logger;

@Plugin(id = "pixelmongym", name = "Pixelmon Gym", version= "1.0", description = "N/A")
public class PixelmonGym  {
	// TODO: Kill the connection to the database, even on failed stuff
	private Config pluginConfiguration;
	private SQL pluginDatabase;

	private void registerCommands() {
		// Call other functions to get commands built, use this as a way to organize each command
		buildChampionCommands();
		buildEliteFourCommands();
		buildGymLeaderCommands();
		buildUserCommands();
	}
	
	private void preRegisterCommands() {
		buildOwnerCommands();
		buildStaffCommands();
	}

	private void buildOwnerCommands() {
		CommandSpec command = CommandSpec.builder()
				.description(Text.of("Declares the new champion for the server"))
				.permission(Permissions.ownerPermission)
				.executor(new NewChampion())
				.build();
		
		Sponge.getCommandManager().register(this, command, "championnew");
	}

	private void buildStaffCommands() {
		CommandSpec command = CommandSpec.builder()
				.description(Text.of("Forcefully deletes a user's gym"))
				.permission(Permissions.adminPermission)
				.arguments(
						GenericArguments.onlyOne(GenericArguments.player(Text.of("player")))
						)
				.executor(new DeleteGym(pluginDatabase, logger))
				.build();
		Sponge.getCommandManager().register(this, command, "gymdelete");
	}

	private void buildChampionCommands() {
		CommandSpec command = CommandSpec.builder()
				.description(Text.of("Sens an emergency message to the Elite 4 members that they are needed."))
				.permission(Permissions.championPermission)
				.permission(Permissions.adminPermission)
				.executor(new Elite4Messager())
				.build();
		Sponge.getCommandManager().register(this, command, "e4emergencymessage", "e4emermes");
	}

	private void buildEliteFourCommands() {
		CommandSpec command = null;
		// Player only
		command = CommandSpec.builder()
				.description(Text.of("States you are accepting challenges for the elite four"))
				.permission(Permissions.elite4Permission)
				.executor(new EliteFourOpen())
				.build();
		Sponge.getCommandManager().register(this, command, "e4open");
		
		// Player only
		command = CommandSpec.builder()
				.description(Text.of("States you are no longer accepting challenges for now"))
				.permission(Permissions.elite4Permission)
				.executor(new EliteFourClose())
				.build();
		Sponge.getCommandManager().register(this, command, "e4close");
	}

	private  void buildGymLeaderCommands() {
		CommandSpec command = null;
		// Player only
		command = CommandSpec.builder()
				.description(Text.of("Creates a gym that you operate"))
				.permission(Permissions.leaderPermission)
				.arguments(
						GenericArguments.string(Text.of("gym name"))
						)
				.executor(new CreateGym(pluginDatabase, logger))
				.build();
		Sponge.getCommandManager().register(this, command, "gymcreate");

		// Player only
		command = CommandSpec.builder()
				.description(Text.of("Opens your gym for other users to challenge you"))
				.permission(Permissions.leaderPermission)
				.executor(new OpenGymCommand(pluginDatabase, logger))
				.build();
		Sponge.getCommandManager().register(this, command, "gymopen");
		

		// Player only
		command = CommandSpec.builder()
				.description(Text.of("Closes your gym"))
				.permission(Permissions.leaderPermission)
				.executor(new CloseGymCommand())
				.build();
		Sponge.getCommandManager().register(this, command, "gymclose");
		
		
		command = CommandSpec.builder()
				.description(Text.of("Accepts a challenge from a user"))
				.permission(Permissions.leaderPermission)
				.arguments(
						GenericArguments.onlyOne(GenericArguments.player(Text.of("player")))
						)
				.executor(new AcceptGymChallenge())
				.build();
		Sponge.getCommandManager().register(this, command, "gymchallengeaccept");
		
		command = CommandSpec.builder()
				.description(Text.of("Denies a challenge from a user."))
				.permission(Permissions.leaderPermission)
				.arguments(
						GenericArguments.onlyOne(GenericArguments.player(Text.of("player")))
						)
				.executor(new DenyGymChallenge())
				.build();
		Sponge.getCommandManager().register(this, command, "gymchallengedeny");
	}

	private void buildUserCommands() {
		CommandSpec command = CommandSpec.builder()
				.description(Text.of("Displays basic information of the plugin"))
				.permission(Permissions.memberPermission)
				.executor((CommandSource src, CommandContext args) -> {
					src.sendMessage(Text.of("Version: 1.0.0"));
					src.sendMessage(Text.of("Programmer: zellman01"));
					return CommandResult.success();
				})
				.build();
		Sponge.getCommandManager().register(this, command, "gyminfo");

		command = CommandSpec.builder()
				.description(Text.of("List all of the gyms, statuses, and the leaders of each"))
				.permission(Permissions.memberPermission)
				.executor(new GymList(pluginDatabase, logger))
				.build();
		Sponge.getCommandManager().register(this, command, "gymlist");

		// Player only
		command = CommandSpec.builder()
				.description(Text.of("Challenge an open gym"))
				.permission(Permissions.memberPermission)
				.arguments(
						GenericArguments.string(Text.of("gym name"))
						)
				.executor(new GymChallenge(pluginDatabase, logger))
				.build();
		Sponge.getCommandManager().register(this, command, "gymchallenge");

		command = CommandSpec.builder()
				.description(Text.of("Lists the Elite 4 members and the champion"))
				.permission(Permissions.memberPermission)
				.executor(new EliteFourList(pluginDatabase, logger))
				.build();
		Sponge.getCommandManager().register(this, command, "e4list");
	}

	@Inject
	private Logger logger;


	@Listener
	public void preInit(GamePreInitializationEvent event) {
		logger.info("Pixelmon Gym is a custom plugin for this server made by zellman01");
		logger.info("If you are seeing this message, DO NOT DISTRIBUTE the jar file of this plugin.");
		logger.info("There is some sensitive information on the plugin that will jepordize the server if it was released.");
	}

	@Listener
	public void onServerStart(GameInitializationEvent event) {
		logger.info("Pixelmon Gym starting up...");
		pluginConfiguration = new Config();
		pluginDatabase = new SQL(pluginConfiguration, logger);
		// Update these to default variables
		logger.info("Registering SQL engine informaiton");
		pluginConfiguration.updateEngine("mysql");
		pluginConfiguration.updateHost("na02-sql.pebblehost.com");
		pluginConfiguration.updateUser("customer_172195_liteeconomy");
		pluginConfiguration.updatePassword("lasPSx!DggbDBcb9lT8P");
		pluginConfiguration.updateDatabase("customer_172195_liteeconomy");
		pluginConfiguration.updatePort(3306);
		logger.info("SQL engine information loaded");
		if (pluginConfiguration.createTables) {
			logger.info("Creating database tables");
			try {
				pluginDatabase.createTables();
				logger.info("Tables were created successfully");
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error("Table creation could not be finished");
			}
		}
		logger.info("Loading commands");
		preRegisterCommands();
		logger.info("Loaded staff commands");
		registerCommands();
		logger.info("Loaded user commands");
	}

	@Listener
	public void onServerStart(GameStartedServerEvent event) {
		logger.info("Pixelmon Gym started");
	}

	@Listener
	public void onServerStopped(GameStoppedServerEvent event) {
		logger.info("Pixelmon gym shutting down...");
		// Shutdown tasks
		pluginConfiguration = null;
		pluginDatabase = null;
		logger.info("Pixelmon gym shut down");
	}
}
