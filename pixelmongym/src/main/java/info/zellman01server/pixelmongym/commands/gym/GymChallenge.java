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

import info.zellman01server.pixelmongym.Permissions;
import info.zellman01server.pixelmongym.utilities.SQL;

public class GymChallenge implements CommandExecutor {

	private Logger logger;
	private SQL db;

	public GymChallenge(SQL database, Logger log) {
		logger = log;
		db = database;
	}
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if (!(src instanceof Player)) {
			src.sendMessage(Text.of("Only a player may use this command."));
			return CommandResult.empty();
		}
		
		if(src.hasPermission(Permissions.bannedPermission)) {
			src.sendMessage(Text.of("You are not allowed to preform this command."));
			return CommandResult.empty();
		}
		
		try {
			String gymName = args.<String>getOne("gym name").get();
			String gymLeaderName = db.getGymOwner(gymName);
			String gymLeaderUUID = db.getGymOwnerID(gymName);
			
			if (src.getName() == gymLeaderName) {
				src.sendMessage(Text.of("You cannot challenge your own gym."));
				return CommandResult.empty();
			}
			
			if (gymLeaderName == gymLeaderUUID) {
				src.sendMessage(Text.of("Either that gym name does not exist, or an error occured."));
				return CommandResult.empty();
			}
			
			if (!db.gymStatus(gymLeaderUUID)) {
				src.sendMessage(Text.of("The gym is closed."));
				return CommandResult.empty();
			}
			
			Collection<Player> onlineList = Sponge.getServer().getOnlinePlayers();
			onlineList.forEach(x -> {
				/*if (x.hasPermission("gym.staff") && (x.getName() != src.getName()) && (x.getName() != gymLeaderName)) {
					x.sendMessage(Text.of("[Gyms] " + src.getName() + " is trying to challenge " + gymName + ", operated by " + gymLeaderName + ". Please spectate this fight as an 'officiator' of the match. This is assuming that both parties agreed to it."));
				}*/
				
				if (x.getName() == gymLeaderName) {
					x.sendMessage(Text.of("[Gyms] " + src.getName() + " has challenged you to a battle. Use \"/gymchallenge<accept/deny> " + src.getName() + "\" to accept/deny the challenge."));
				}
			});
			
			src.sendMessage(Text.of("Your challenge has been sent. You will be notified if the gym leader accepts the challenge. Further instructions will be sent if they do accept it."));
		} catch (SQLException e) {
			src.sendMessage(Text.of("Something went wrong."));
			e.printStackTrace();
			logger.error("SQL error - read above");
			return CommandResult.empty();
		}

		return CommandResult.success();
	}

}
