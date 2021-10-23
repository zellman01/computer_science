package info.zellman01server.pixelmongym.commands.gym;

import java.sql.SQLException;
import java.util.Collection;

import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import info.zellman01server.pixelmongym.utilities.SQL;

public class CreateGym implements CommandExecutor {
	private SQL databaseInfo;
	private Logger logger;

	public CreateGym(SQL sql, Logger log) {
		databaseInfo = sql;
		logger = log;
	}

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if (src instanceof ConsoleSource) {
			src.sendMessage(Text.of("The console may not use this command."));
		}
		if (src instanceof Player) {
			Player player = (Player) src;
			String UUID = player.getUniqueId().toString();

			try {
				if (!databaseInfo.gymOwned(UUID)) {
					String name = args.<String>getOne("gym name").get();
					String username = player.getName();
					try {
						databaseInfo.addGym(name, UUID, username);
					} catch (SQLException e) {
						src.sendMessage(Text.of("Something went wrong - contact server owners"));
						e.printStackTrace();
						logger.error("Unable to create a gym");
					}
					src.sendMessage(Text.of("You created a gym."));
					Collection<Player> onlineList = Sponge.getServer().getOnlinePlayers();
					onlineList.forEach(x -> x.sendMessage(Text.of("[Gyms] " + username + " has created the gym " + name + ". It is currently not available for challenges yet.")));
					return CommandResult.success();
				} else {
					src.sendMessage(Text.of("You already own a gym."));
				}
			} catch (SQLException e) {
				src.sendMessage(Text.of("Something went wrong"));
				e.printStackTrace();
				logger.error("SQL Error: Look at above error");
			}
		} else {
			logger.warn("gymcreate was attempted by a command block");
		}

		return CommandResult.empty();
	}

}
