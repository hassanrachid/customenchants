package me.grizzly.enchants.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import me.grizzly.enchants.listeners.InventoryListener;
import me.grizzly.enchants.utils.CustomEnchant;
import me.grizzly.enchants.utils.EnchantUtil;
import me.grizzly.enchants.utils.ItemBuilder;

public class HelpCommand implements CommandExecutor, Listener {
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Inventory topInventory = e.getView().getTopInventory();
		if (topInventory.getTitle().contains("Enchantments")) {
			e.setCancelled(true);
		}
		if (!topInventory.getTitle().equals(ChatColor.RED + "Enchants")) {
			return;
		}	
		if (e.getCurrentItem() == null) {
			return;
		}
		Player p = (Player) e.getWhoClicked();
		if (e.getCurrentItem().getDurability() == 11) {
			p.openInventory(getEnchantInventory(1));
		} else if (e.getCurrentItem().getDurability() == 14) {
			p.openInventory(getEnchantInventory(2));
		} else if (e.getCurrentItem().getDurability() == 13) {
			p.openInventory(getEnchantInventory(3));
		} else if (e.getCurrentItem().getDurability() == 1) {
			p.openInventory(getEnchantInventory(4));
		} else {
			e.setCancelled(true);
		}
		e.setCancelled(true);
		
	}
	
	public Inventory getEnchantInventory(int tier) {
		Inventory inventory = Bukkit.createInventory(null, 27, getTitle(tier));
		for (CustomEnchant enchant : CustomEnchant.values()) {
			if (enchant.getTier() == tier) {
				inventory.addItem(new ItemBuilder(Material.BOOK).setName(EnchantUtil.getTierColor(tier) + enchant.getName() + ChatColor.GRAY + " (Max " + enchant.getMaxLevel() + " Levels)").setLore(ChatColor.GRAY + enchant.getEnchantDesc(), ChatColor.RED + "(" + ChatColor.YELLOW + "!" + ChatColor.RED + ")" + ChatColor.YELLOW
						+ " Only applicable to " + enchant.getItemEnchantType()).toItemStack());
			}
		}	
		return inventory;
	}

	public String getTitle(int tier) {
		if (tier == 1) {
			return ChatColor.BLUE + "Heroic Enchantments";
		} else if (tier == 2) {
			return ChatColor.RED + "Legendary Enchantments";
		} else if (tier == 3) {
			return ChatColor.DARK_GREEN + "Paladin Enchantments";
		} else if (tier == 4) {
			return ChatColor.GOLD + "King Enchantments";
		}
		
		return null;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 0) {
				p.openInventory(InventoryListener.enchantInventory());
			}
		} else {

		}
		return true;
	}

}
