package me.grizzly.enchants.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

import me.grizzly.enchants.utils.CrystalUtil;
import me.grizzly.enchants.utils.EnchantUtil;

public class CrystalListener implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getAction() != InventoryAction.SWAP_WITH_CURSOR) {
			return;
		}
		if (p.getItemOnCursor() == null || e.getCurrentItem() == null) {
			return;
		}

		if (p.getItemOnCursor().getType() != Material.EMERALD || !p.getItemOnCursor().hasItemMeta()
				|| !p.getItemOnCursor().getItemMeta().hasLore()) {
			return;
		}

		ItemStack crystal = p.getItemOnCursor();
		ItemStack item = e.getCurrentItem();
		
		if (item == null)
			return;

		if (!CrystalUtil.isApplicableFor(item.getType(),
				EnchantUtil.parseEnchant(CrystalUtil.getEnchantName(CrystalUtil.getEnchant(crystal))))) {
			return;
		}

		boolean applied = false;
		boolean success = false;

		if (!isTieredItem(item)) {
			p.sendMessage(translate("&cYou can only apply enchants on special tier of armor!"));
			return;
		}

		if (item.hasItemMeta() && item.getItemMeta().hasLore()) {
			if (item.getItemMeta().getLore().size() == (loreAmount(item))) {
				p.sendMessage(translate("&cThis item can not accept any more enchantments, try getting better armor."));
				return;
			}
		}

		if (item.hasItemMeta() && item.getItemMeta().hasLore()) {

			String crystalEnchantName = CrystalUtil.getEnchantName(crystal.getItemMeta().getDisplayName());
			int crystalLevel = CrystalUtil.getEnchantLevel(crystal.getItemMeta().getDisplayName());
			String itemEnchantName;
			int itemLevel;

			Map<String, Integer> enchantMap = new HashMap<String, Integer>();

			for (String line : item.getItemMeta().getLore()) {
				itemEnchantName = CrystalUtil.getEnchantName(line);
				itemLevel = CrystalUtil.getEnchantLevel(line);

				enchantMap.put(itemEnchantName, itemLevel);
			}

			if (enchantMap.containsKey(crystalEnchantName)) {
				if (enchantMap.get(crystalEnchantName) < crystalLevel) {
					enchantMap.put(crystalEnchantName, crystalLevel);
					applied = true;
				}
			} else {
				applied = true;
				enchantMap.put(crystalEnchantName, crystalLevel);
			}

			if (isSuccessful(crystal)) {
				List<String> lore = new ArrayList<String>();
				for (String name : enchantMap.keySet()) {
					lore.add(name + " " + EnchantUtil.integerToRomanNumeral(enchantMap.get(name)));
				}

				ItemMeta meta = item.getItemMeta();
				meta.setLore(lore);
				item.setItemMeta(meta);
				success = true;
			}
		} else {
			if (isSuccessful(crystal)) {
				ItemMeta meta = item.getItemMeta();
				List<String> lore = new ArrayList<String>();
				lore.add(crystal.getItemMeta().getDisplayName());
				meta.setLore(lore);
				item.setItemMeta(meta);
				success = true;
			}
			applied = true;
		}

		if (applied) {
			p.setItemOnCursor(null);
			e.setCancelled(true);

			if (success) {
				p.getWorld().playSound(p.getLocation(), Sound.ORB_PICKUP, 1F, 1F);
				p.sendMessage(translate("&cThe enchantment was successfully applied to your item!"));
			} else {
				p.getWorld().playSound(p.getLocation(), Sound.WITHER_DEATH, 1F, 1F);
				p.sendMessage(translate("&cThe enchantment failed to apply, only the crystal was destroyed!"));
			}
		}
	}

	public boolean isSuccessful(ItemStack crystal) {
		String line = ChatColor.stripColor(crystal.getItemMeta().getLore().get(3));

		line = line.replace("%", "");
		line = line.substring(line.length() - 2, line.length());
		line = line.trim();

		Random r = new Random();
		int rate = Integer.parseInt(line);
		int chance = r.nextInt(99) + 1;

		if (chance <= rate) {
			return true;
		}
		return false;
	}

	public boolean isTieredItem(ItemStack item) {
		if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
			String displayName = item.getItemMeta().getDisplayName();

			if (displayName.contains("King") || displayName.contains("Paladin") || displayName.contains("Legendary")
					|| displayName.contains("Heroic")) {
				return true;
			}
		}
		return false;
	}
	
	public int loreAmount(ItemStack item) {
		if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
			String tier = item.getItemMeta().getDisplayName();
		if (tier.contains("Heroic")) {
			return 2;
		} else if (tier.contains("Paladin")) {
			return 4;
		} else if (tier.contains("Legendary")) {
			return 6;
		} else if (tier.contains("King")) {
			return 8;
		}
	}
	return 0;
}
	private static String translate(String translate) {
		return ChatColor.translateAlternateColorCodes('&', translate);
	}
}