package me.grizzly.enchants.listeners;

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShardListener implements Listener {

	@EventHandler
	public void shardClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getAction() != InventoryAction.SWAP_WITH_CURSOR) {
			return;
		}
		if (p.getItemOnCursor() == null || e.getCurrentItem() == null) {
			return;
		}
		if (p.getItemOnCursor().getType() != Material.INK_SACK || !p.getItemOnCursor().hasItemMeta()
				|| !p.getItemOnCursor().getItemMeta().hasLore()) {
			return;
		}
		
		if (p.getItemOnCursor().getItemMeta().getDisplayName().contains("(Right Click)")) {
			return;
		}
		ItemStack shard = p.getItemOnCursor();
		ItemStack item = e.getCurrentItem();
		

		if (item == null)
			return;
		
		if (item.getType() != Material.EMERALD) {
			return;
		}
		
		if (!item.hasItemMeta()) {
			return;
		}
		int combined = getSuccessRateShard(shard) + getSuccessRate(item);
		if (combined <= 100) {
			ItemMeta meta = item.getItemMeta();
			List<String> lore = meta.getLore();
			lore.set(3, ChatColor.GREEN + "Success Rate: " + combined + "%");
			meta.setLore(lore);
			item.setItemMeta(meta);
		} else if (combined > 100) {
			ItemMeta meta = item.getItemMeta();
			List<String> lore = meta.getLore();
			lore.set(3, ChatColor.GREEN + "Success Rate: 100%");
			meta.setLore(lore);
			item.setItemMeta(meta);
		} else if (combined == 100) {
			return;
		}
		p.getWorld().playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 3F, 3F);
		e.setCancelled(true);
		p.setItemOnCursor(null);
	}
	
	
	public int getSuccessRateShard(ItemStack item) {
		String line = ChatColor.stripColor(item.getItemMeta().getLore().get(3));

		line = line.replace("%", "");
		line = line.replace("+", "");
		line = line.substring(line.length() - 2, line.length());
		line = line.trim();
		int rate = Integer.parseInt(line);
		return rate;
	}
	
	public int getSuccessRate(ItemStack item) {
		String line = ChatColor.stripColor(item.getItemMeta().getLore().get(3));

		line = line.replace("%", "");
		line = line.substring(line.length() - 2, line.length());
		line = line.trim();
		int rate = Integer.parseInt(line);
		return rate;
	}
}
