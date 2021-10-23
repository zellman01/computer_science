package info.zellman01server.pixelmongym.commands.staff;

import java.util.Collection;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import info.zellman01server.pixelmongym.Permissions;

public class NewChampion implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) {
		if (!(src instanceof Player)) {
			src.sendMessage(Text.of("This command can only be used by a player."));
			return CommandResult.empty();
		}
		Collection<Player> onlineList = Sponge.getServer().getOnlinePlayers();
		onlineList.forEach(x -> {
			x.sendMessage(Text.of("[Gyms] The champion has been defeated. All Elite 4 challenges are canceled until further notice."));
			if (x.hasPermission(Permissions.elite4Permission)) {
				x.sendMessage(Text.of("[Gyms] The Elite four internal structure is being changed. You will be mailed by the server your new Elite Four position, or if you were kicked out of the Elite Four."));
			}
			
			if (x.hasPermission(Permissions.championPermission)) {
				x.sendMessage(Text.of("[Gyms] You are no longer the champion. You will be the Elite 4 member right before the champion after the reorganization of the league has taken place."));
			}
		});
		return CommandResult.success();
	}

}
