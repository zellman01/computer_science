package info.zellman01server.pixelmongym.commands.gym;

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

public class AcceptGymChallenge implements CommandExecutor {

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
		
		src.sendMessage(Text.of("Challenge accepted. Sending notification of acceptance."));
		challenger.sendMessage(Text.of("The challenge has been accepted by the gym leader."));
		
		Collection<Player> onlineList = Sponge.getServer().getOnlinePlayers();
		onlineList.forEach(x-> {
			if (x.hasPermission(Permissions.staffPermission) && !src.getName().equals(x.getName()) && !challenger.getName().equals(x.getName())) {
				x.sendMessage(Text.of("[Gyms] A gym battle between " + src.getName() + " and " + challenger.getName() + " is about to start. At least one staff member is required to adjudicate the match. If the match has already started, it must be forcefully ended and restarted."));
				
				if (src.getName().equals(x.getName()) || challenger.getName().equals(x.getName())) {
					x.sendMessage(Text.of("[Gyms] Challenge accepted against " + src.getName() + " and " + challenger.getName() + ". Please wait for a staff member before fighting. If the fight has started before one has come, the fight will be forcefully ended and the fight restarted."));
				}
			}
		});
		
		return CommandResult.success();
	}

}
