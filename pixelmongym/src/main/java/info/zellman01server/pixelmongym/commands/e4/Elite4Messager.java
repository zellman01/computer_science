package info.zellman01server.pixelmongym.commands.e4;

import java.util.Collection;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import info.zellman01server.pixelmongym.Permissions;

public class Elite4Messager implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if (!(src instanceof Player)) {
			src.sendMessage(Text.of("Only a player may execute this command."));
			return CommandResult.empty();
		}
		Collection<Player> onlineList = Sponge.getServer().getOnlinePlayers();
		onlineList.forEach(x -> {
			if (x.hasPermission(Permissions.elite4Permission)) {
				x.sendMessage(Text.of("[Gyms] The champion of the server has called you to the League building. Report there immediately."));
			}
		});
		src.sendMessage(Text.of("All online Elite 4 members have been called."));
		
		return CommandResult.success();
	}

}
