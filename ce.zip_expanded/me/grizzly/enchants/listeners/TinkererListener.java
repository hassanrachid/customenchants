package me.grizzly.enchants.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.earth2me.essentials.craftbukkit.SetExpFix;

import me.grizzly.enchants.EraEnchants;
import me.grizzly.enchants.utils.CrystalUtil;
import me.grizzly.enchants.utils.CustomEnchant;
import me.grizzly.enchants.utils.EnchantUtil;
import me.grizzly.enchants.utils.ItemBuilder;

public class TinkererListener implements Listener {

	private EraEnchants plugin;

	public TinkererListener(EraEnchants pl) {
		this.plugin = pl;
	}

	public static Inventory experienceInventory() {
		Inventory inventory = Bukkit.createInventory(null, 9, ChatColor.GOLD + "Recycler");

		inventory
				.setItem(8,
						new ItemBuilder(Material.EXP_BOTTLE).setName(ChatColor.AQUA + "Recycle Items")
								.setLore(ChatColor.GRAY + "You will receive experience when you",
										ChatColor.GRAY + "recycle custom enchanted gear or enchanted crystals")
								.toItemStack());

		return inventory;
	}

	@EventHandler
	public void recyclerListener(InventoryClickEvent e) {
		Inventory topInventory = e.getView().getTopInventory();
		if (!topInventory.getName().equals(ChatColor.GOLD + "Recycler")) {
			return;
		}
		if (e.getCurrentItem() == null || e.getCursor() == null) {
			return;
		}

		if (!isTieredItem(e.getCurrentItem()) && !isTieredItem(e.getCursor())) {
			e.setCancelled(true);
		}

		new BukkitRunnable() {

			@Override
			public void run() {
				int amount = (expForCrystal(topInventory.getItem(0)) + expForCrystal(topInventory.getItem(1))
						+ expForCrystal(topInventory.getItem(2)) + expForCrystal(topInventory.getItem(3))
						+ expForCrystal(topInventory.getItem(4)) + expForCrystal(topInventory.getItem(5))
						+ expForCrystal(topInventory.getItem(6)) + expForCrystal(topInventory.getItem(7)));
				if (amount == 0) {
					topInventory.setItem(8, new ItemBuilder(Material.EXP_BOTTLE)
							.setName(ChatColor.AQUA + "Recycle Items").setLore(ChatColor.GRAY + "You will receive experience when you",
									ChatColor.GRAY + "recycle custom enchanted gear or enchanted crystals").toItemStack());
				} else {
					topInventory.setItem(8,
							new ItemBuilder(Material.EXP_BOTTLE).setName(ChatColor.AQUA + "Recycle Items")
									.setLore(ChatColor.GRAY + "for " + ChatColor.GREEN + amount + " experience")
									.toItemStack());
				}
			}
		}.runTaskLater(plugin, 1L);
		Player p = (Player) e.getWhoClicked();

		int amount = 0;
		for (int i = 0; i < 8; ++i) {
			amount += expForCrystal(topInventory.getItem(i));
		}
		if (e.getClickedInventory() == topInventory && e.getCurrentItem().getType() == Material.EXP_BOTTLE) {
			if (amount != 0) {
				SetExpFix.setTotalExperience(p, SetExpFix.getTotalExperience(p) + amount);
				for (int i = 0; i < 8; ++i) {
					topInventory.setItem(i, null);
				}
			}
		}

	}

	public boolean isTieredItem(ItemStack item) {
		if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
			String displayName = item.getItemMeta().getDisplayName();

			if (item.getType() == Material.DIAMOND_BOOTS || item.getType() == Material.DIAMOND_LEGGINGS
					|| item.getType() == Material.DIAMOND_CHESTPLATE || item.getType() == Material.DIAMOND_HELMET
					|| item.getType() == Material.DIAMOND_SWORD || item.getType() == Material.DIAMOND_SPADE
					|| item.getType() == Material.DIAMOND_AXE || item.getType() == Material.DIAMOND_PICKAXE
					|| item.getType() == Material.BOW) {
				if (displayName.contains("King") || displayName.contains("Paladin") || displayName.contains("Legendary")
						|| displayName.contains("Heroic")) {
					return true;
				}
			} else if (item.getType() == Material.EMERALD) {
				if (item.getItemMeta().hasLore()) {
					return true;
				}
			}
			return false;
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

	public int expForCrystal(ItemStack item) {
		if (item != null) {
			if (item.getType() != Material.AIR) {
				if (item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().hasLore()) {
					if (item.getType() == Material.EMERALD) {
						CustomEnchant enchant = EnchantUtil
								.parseEnchant(CrystalUtil.getEnchantName(CrystalUtil.getEnchant(item)));
						String line = ChatColor.stripColor(item.getItemMeta().getLore().get(3));
						line = line.replace("%", "");
						line = line.substring(line.length() - 2, line.length());
						line = line.trim();
						int rate = Integer.parseInt(line) * 4;
						int tierAndLevel = enchant.getTier()
								+ CrystalUtil.getEnchantLevel(CrystalUtil.getEnchantName(CrystalUtil.getEnchant(item)));
						return (tierAndLevel * 700) + rate;
					} else {
						if (item.hasItemMeta() && isTieredItem(item)) {
							if (item.getItemMeta().hasLore()) {
								int amountofEnchants = item.getItemMeta().getLore().size() - 1;
								int tierAmount = loreAmount(item);
								return (amountofEnchants + tierAmount) * 600;
							}
						}
					}
				}
			}
		}
		return 0;
	}
}
