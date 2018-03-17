package me.grizzly.enchants.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.grizzly.enchants.utils.ItemUtil;

public class ItemCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 2) {
				int tier = Integer.parseInt(args[0]);
				String type = args[1];
				ItemUtil.giveItem(p, tier, type);
				p.sendMessage(ChatColor.GREEN + "Added to inventory");
			} else {
				p.sendMessage(ChatColor.RED + "Usage: /giveitem [tier] [type]");
			}
		} else {

		}
		return true;
	}
}