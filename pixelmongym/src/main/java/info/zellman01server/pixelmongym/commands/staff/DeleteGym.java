package info.zellman01server.pixelmongym.commands.staff;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import info.zellman01server.pixelmongym.utilities.SQL;

public class DeleteGym implements CommandExecutor {
	
	private SQL database;
	private Logger log;
	
	public DeleteGym(SQL dataBase, Logger logger) {
		database = dataBase;
		log = logger;
	}

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		// TODO Auto-generated method stub
		src.sendMessage(Text.of("Command not finished"));
		Player player = args.<Player>getOne("player").get();
		String UUID = player.getUniqueId().toString();
		try {
			if (database.gymOwned(UUID)) {
				database.deleteGym(UUID);
			} else {
				src.sendMessage(Text.of("The user does not have a registered gym."));
				return CommandResult.empty();
			}
		} catch(SQLException e) {
			e.printStackTrace();
			log.error("SQL Error: see above");
			return CommandResult.empty();
		}
		return CommandResult.success();
	}

}
