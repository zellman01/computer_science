package info.zellman01server.pixelmongym.commands.e4;

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

public class EliteFourList implements CommandExecutor {

	private SQL db;
	private Logger log;
	
	public EliteFourList(SQL pluginDatabase, Logger logger) {
		db = pluginDatabase;
		log = logger;
	}

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		ArrayList<String> res = new ArrayList<>();
		try {
			res = db.e4List();
			if (res.size() > 0) {
				src.sendMessage(Text.of("List of Elite 4 members and positions"));
				for (int i = 0; i < res.size(); i++) {
					src.sendMessage(Text.of(res.get(i)));
				}
			} else {
				src.sendMessage(Text.of("There are no current Elite 4 members registered."));
			}
		} catch (SQLException e) {
			src.sendMessage(Text.of("An internal error occured."));
			e.printStackTrace();
			log.error("Plugin error: look at above message.");
			return CommandResult.empty();
		}
		return CommandResult.success();
	}

}
