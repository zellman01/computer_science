package info.zellman01server.pixelmongym;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;

public class TestCommand implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		// TODO Auto-generated method stub
		src.sendMessage(Text.of("Pong!"));
		
		CommandResult result = CommandResult.builder()
				.affectedEntities(0)
				.successCount(1)
				.build();
		return result;
	}

}
