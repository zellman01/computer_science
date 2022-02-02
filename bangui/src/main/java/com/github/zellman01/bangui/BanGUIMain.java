package com.github.zellman01.bangui;

import org.apache.logging.log4j.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public class BanGUIMain extends JavaPlugin {
	private Logger logger;
	
	@Override
	public void onEnable() {
		logger.info("Plugin enabled");
	}
	
	@Override
	public void onDisable() {
	}
}
