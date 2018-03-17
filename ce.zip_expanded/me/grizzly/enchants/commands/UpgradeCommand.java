package me.grizzly.enchants.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.grizzly.enchants.listeners.InventoryListener;
import me.grizzly.enchants.utils.CrystalUtil;
import me.grizzly.enchants.utils.CustomEnchant;
import me.grizzly.enchants.utils.EnchantUtil;
import me.grizzly.enchants.utils.ItemBuilder;

public class UpgradeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 0) {
				if (p.getItemInHand() != null) {
					p.openInventory(InventoryListener.upgradeInventory());
					if (p.getItemInHand().getType() == Material.EMERALD) {
						if (p.getItemInHand().hasItemMeta())
							if (p.getItemInHand().getItemMeta().hasDisplayName()) {
								CustomEnchant enchant = EnchantUtil.parseEnchant(
										CrystalUtil.getEnchantName(CrystalUtil.getEnchant(p.getItemInHand())));
								int enchantLevel = CrystalUtil
										.getEnchantLevel(p.getItemInHand().getItemMeta().getDisplayName());
								if (enchantLevel != enchant.getMaxLevel()) {
									if (p.getItemInHand().getAmount() == 1) {
										p.getOpenInventory().setItem(13, p.getItemInHand());
										p.getOpenInventory().setItem(16,
												new ItemBuilder(Material.ANVIL).setName(ChatColor.GREEN + "Upgrade Item"
														+ ChatColor.RED + " -> "
														+ CrystalUtil.getEnchantName(
																p.getOpenInventory().getItem(13).getItemMeta().getDisplayName())
														+ " " + EnchantUtil.integerToRomanNumeral(enchantLevel + 1))
												.setLore("" + ChatColor.RED + ChatColor.BOLD + "COST " + ChatColor.WHITE
														+ ((enchant.getTier() + (enchant.getMaxLevel() + enchantLevel))
																* 8000)
														+ " experience")
												.toItemStack());
									} else {
										p.sendMessage(ChatColor.RED + "You can only upgrade one item at a time!");
									}
								} else {
									p.sendMessage(ChatColor.RED + "This enchant is already at its highest level!");
								}
							}

					}
				}
			}
		} else {

		}
		return true;
	}
}