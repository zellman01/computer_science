package info.zellman01server.pixelmongym;

import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;

import com.google.inject.Inject;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import java.io.IOException;
import java.nio.file.Path;

import org.slf4j.Logger;

@Plugin(id = "pixelmongym", name = "Pixelmon Gym", version= "0.1", description = "N/A")
public class PixelmonGym 
{
	
	@Inject
	private Logger logger;
	
	/*@Inject
	private Game game;*/
	
	@Inject
	@DefaultConfig(sharedRoot = false)
	private ConfigurationLoader<CommentedConfigurationNode> loader;

	@Inject
	@DefaultConfig(sharedRoot = false)
	private Path path;
	
	
	@Listener
	public void preInit(GamePreInitializationEvent event) {
		logger.info("Pixelmon Gym is a custom plugin for this server made by zellman01");
		logger.info("If you are seeing this message, DO NOT DISTRIBUTE the jar file of this plugin.");
	}
	
	@Listener
	public void onServerStart(GameInitializationEvent event) {
		logger.info("Pixelmon Gym starting up...");
		// Pull config options
		Path potentialFile = path;
		ConfigurationLoader<CommentedConfigurationNode> loader =
		        HoconConfigurationLoader.builder().setPath(potentialFile).build();
		loader.getDefaultOptions().withShouldCopyDefaults(true);
		ConfigurationNode rootNode;
		try {
		    rootNode = loader.load();
		    if (!rootNode.getNode("nochange", "noResetConfig").getBoolean(false)) {
		    	loader.save(rootNode);
		    }
		    // load configuration
		} catch(IOException e) {
		    // handle errors
		}
	}

	@Listener
	public void onServerStart(GameStartedServerEvent event) {
		logger.info("Pixelmon Gym started");
	}
}
