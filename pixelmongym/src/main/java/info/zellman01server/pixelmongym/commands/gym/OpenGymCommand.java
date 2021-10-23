package info.zellman01server.pixelmongym.commands.gym;

import java.sql.SQLException;
import java.util.Collection;

import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import info.zellman01server.pixelmongym.utilities.SQL;

public class OpenGymCommand implements CommandExecutor {
	
	private Logger logger;
	private SQL database;

	public OpenGymCommand(SQL pluginDatabase, Logger logger) {
		this.logger = logger;
		database = pluginDatabase;
	}

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if (!(src instanceof Player)) {
			src.sendMessage(Text.of("Only a player may run this command."));
			return CommandResult.empty();
		}
		//src.sendMessage(Text.of("This command is not finished yet"));
		Player player = (Player)src;
		String UUID = player.getUniqueId().toString();
		try {
			if (database.gymOwned(UUID)) {
				if (database.gymStatus(UUID)) {
					src.sendMessage(Text.of("Your gym is already opened."));
					return CommandResult.empty();
				} else {
					src.sendMessage(Text.of("Your gym is now opened."));
					// TODO: Send a message to the server that the gym is open
					database.switchGymStatus(UUID);
					Collection<Player> onlineList = Sponge.getServer().getOnlinePlayers();
					onlineList.forEach(x -> x.sendMessage(Text.of("[Gyms] " + player.getName() + " has opened their gym. Please speak with them if you are wanting to challenge them to a gym battle..")));
				}
			} else {
				src.sendMessage(Text.of("You have no gym to open."));
			}
		} catch (SQLException e) {
			src.sendMessage(Text.of("Something went wrong."));
			e.printStackTrace();
			logger.error("SQL error - read above");
			return CommandResult.empty();
		}
		return CommandResult.success();
	}

}
