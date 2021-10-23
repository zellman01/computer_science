package info.zellman01server.pixelmongym.commands.gym;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

public class DenyGymChallenge implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if (!(src instanceof Player)) {
			src.sendMessage(Text.of("Only a player may run this command."));
			return CommandResult.empty();
		}
		
		Player challenger = args.<Player>getOne("player").get();
		
		if (!challenger.isOnline()) {
			src.sendMessage(Text.of("The challenger listed is not online, and therefore cannot fight."));
			return CommandResult.empty();
		}
		
		src.sendMessage(Text.of("Challenge denied. If the challenger keeps bugging you, use helpop and get a staff member to help you."));
		challenger.sendMessage(Text.of("Your challenge to " + src.getName() + " has been denied. Do not constantly keep bugging them about it, or you may face consequencces."));
		
		return CommandResult.success();
	}

}
