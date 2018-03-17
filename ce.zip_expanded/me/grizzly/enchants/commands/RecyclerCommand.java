package me.grizzly.enchants.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.grizzly.enchants.listeners.TinkererListener;

public class RecyclerCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 0) {
				p.openInventory(TinkererListener.experienceInventory());
			}
		} else {

		}
		return true;
	}
}