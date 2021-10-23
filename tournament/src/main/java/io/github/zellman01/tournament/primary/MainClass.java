package io.github.zellman01.tournament.primary;

import org.slf4j.Logger;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.plugin.Plugin;

import com.google.inject.Inject;

@Plugin(id ="pixelmontournament", name = "Pixelmon Tournament", version = "1.0", description = "Custom made tournament plugin for Pixelmon")
public class MainClass {
	@Inject
	private Logger logger;
	
	@Listener
	public void preInit(GamePreInitializationEvent event) {
		logger.info("Custom Tournaments plugin made specifically for this server.");
		logger.info("Plugin will disable itself as it is not finished.");
	}
}
