package info.zellman01server.pixelmongym;

import org.spongepowered.api.plugin.Plugin;
//import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;

import com.google.inject.Inject;

import org.slf4j.Logger;

@Plugin(id = "pixelmongym", name = "Pixelmon Gym", version= "0.1", description = "N/A")
public class PixelmonGym 
{
	private Config pluginConfiguration;
	
	private void registerCommands() {
		// Call other functions to get commands built, use this as a way to organize each command
		CommandSpec command = CommandSpec.builder()
				.description(Text.of("Test command"))
				.permission("")
				.executor(new TestCommand())
				.build();
		
		Sponge.getCommandManager().register(this, command, "pokeping");
	}
	
	@Inject
	private Logger logger;
	
	
	@Listener
	public void preInit(GamePreInitializationEvent event) {
		logger.info("Pixelmon Gym is a custom plugin for this server made by zellman01");
		logger.info("If you are seeing this message, DO NOT DISTRIBUTE the jar file of this plugin.");
		logger.info("There are some sensitive information on the plugin that will jepordize the server if it was released.");
	}
	
	@Listener
	public void onServerStart(GameInitializationEvent event) {
		logger.info("Pixelmon Gym starting up...");
		pluginConfiguration = new Config();
		// Update these to default variables (add commands later to be able to change mid game)
		pluginConfiguration.updateEngine("");
		pluginConfiguration.updateHost("");
		pluginConfiguration.updateUser("");
		pluginConfiguration.updatePassword("");
		pluginConfiguration.updateDatabase("");
		pluginConfiguration.updatePort(0);
		pluginConfiguration.updateTable("");
		registerCommands();
	}

	@Listener
	public void onServerStart(GameStartedServerEvent event) {
		logger.info("Pixelmon Gym started");
	}
}
