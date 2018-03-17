package me.grizzly.enchants.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.grizzly.enchants.utils.CustomEnchant;
import me.grizzly.enchants.utils.EnchantUtil;

public class EnchantCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 2) {
				CustomEnchant enchant = EnchantUtil.parseEnchant(args[0]);
				int level = Integer.parseInt(args[1]);
				EnchantUtil.addEnchant(p.getItemInHand(), enchant, EnchantUtil.integerToRomanNumeral(level));
				p.sendMessage(ChatColor.GREEN + "Added to item");
			} else {
				p.sendMessage(ChatColor.RED + "Usage: /addenchant [enchant] [level]");
			}
		} else {

		}
		return true;
	}
}