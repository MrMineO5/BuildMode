package xyz.ultradev.buildmode;

import lombok.AllArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class BuildModeCommand implements CommandExecutor {
    private BuildMode parent;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cThis command may only be used by players!");
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            if (parent.toggle(player.getUniqueId())) {
                player.sendMessage("§aBuild mode enabled");
            } else {
                player.sendMessage("§cBuild mode disabled");
            }
            return true;
        }
        if (args[0].equalsIgnoreCase("on")) {
            if (parent.enable(player.getUniqueId())) {
                player.sendMessage("§aBuild mode enabled");
            } else {
                player.sendMessage("§7You already had build mode enabled.");
            }
        }
        if (args[0].equalsIgnoreCase("off")) {
            if (parent.disable(player.getUniqueId())) {
                player.sendMessage("§cBuild mode disabled");
            } else {
                player.sendMessage("§7You already had build mode disable.");
            }
        }
        return true;
    }
}
