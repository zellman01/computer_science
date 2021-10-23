package info.zellman01server.pixelmongym.commands.gym;

import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;

import info.zellman01server.pixelmongym.utilities.SQL;

public class GymList implements CommandExecutor {

	private SQL database;
	private Logger logger;

	public GymList (SQL databaseInfo, Logger log) {
		database = databaseInfo;
		logger = log;
	}

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		// TODO Auto-generated method stub
		ArrayList<String> result = new ArrayList<>();
		try {
			result = database.gymList();
			if (result.size() > 0) {
				src.sendMessage(Text.of("List of gyms on the server:"));
				for (int i = 0; i < result.size(); i++) {
					src.sendMessage(Text.of(result.get(i)));
				}
			} else {
				src.sendMessage(Text.of("There are no registered gyms on the server."));
			}
		} catch (SQLException e) {
			src.sendMessage(Text.of("An internal error occured."));
			e.printStackTrace();
			logger.error("Plugin error: look at above message.");
			return CommandResult.empty();
		}
		return CommandResult.success();
	}

}
