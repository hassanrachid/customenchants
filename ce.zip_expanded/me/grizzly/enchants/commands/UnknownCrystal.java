package me.grizzly.enchants.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.grizzly.enchants.utils.CrystalUtil;
import me.grizzly.enchants.utils.EnchantUtil;
import me.grizzly.enchants.utils.ItemBuilder;
import net.md_5.bungee.api.ChatColor;

public class UnknownCrystal implements CommandExecutor, Listener {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			return false;
		}

		if (args.length == 2) {
			Player p = Bukkit.getPlayer(args[0]);
			String tier = args[1];

			p.getInventory().addItem(getTieredCrystal(tier));
		}
		return true;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.getAction() != Action.RIGHT_CLICK_AIR || e.getAction() != Action.RIGHT_CLICK_BLOCK
				|| e.getAction() == Action.LEFT_CLICK_BLOCK) {
			if (e.getItem() == null || e.getItem().getType() == Material.AIR
					|| e.getItem().getType() != Material.EYE_OF_ENDER) {
				return;
			}

			if (!e.getItem().hasItemMeta()) {
				return;
			}

			Player p = e.getPlayer();
			ItemStack item = e.getItem();
			if (isTieredCrystal(item)) {
				ItemStack enchanted = null;
				int tier = getCrystalTier(item);
				if (tier == 4) {
					enchanted = CrystalUtil.getLevel60();
				} else if (tier == 3) {
					enchanted = CrystalUtil.getLevel50();
				} else if (tier == 2) {
					enchanted = CrystalUtil.getLevel40();
				} else if (tier == 1) {
					enchanted = CrystalUtil.getLevel30();
				}
				
				e.setCancelled(true);
				p.getWorld().playSound(p.getLocation(), Sound.WITHER_SHOOT, 3F, 3F);

				if (item.getAmount() > 1) {
					item.setAmount(item.getAmount() - 1);

					if (e.getPlayer().getInventory().firstEmpty() == -1) {
						e.getPlayer().getWorld().dropItem(e.getPlayer().getLocation(), enchanted);
					} else {
						e.getPlayer().getInventory().setItem(e.getPlayer().getInventory().firstEmpty(), enchanted);
					}
				} else {
					p.getInventory().remove(item);
					e.getPlayer().setItemInHand(enchanted);
				}
			}
		}
	}

	private ItemStack getTieredCrystal(String tier) {
		int tierInt = Integer.valueOf(tier);
		ItemStack item = new ItemBuilder(Material.EYE_OF_ENDER)
				.setName(EnchantUtil.getTierColor(tierInt) + EnchantUtil.getName(tierInt) + " Crystal"
						+ ChatColor.GRAY + " (Right Click)")
				.setLore(translate("&7This crystal contains hidden powerful magic that only a"),
						translate("&7warrior strong enough can reveal its enchantment"))
				.toItemStack();
		return item;
	}

	private boolean isTieredCrystal(ItemStack item) {
		if (item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().hasLore()) {
			String name = item.getItemMeta().getDisplayName();

			if (name.contains("King") || name.contains("Paladin") || name.contains("Legendary")
					|| name.contains("Heroic")) {
				return true;
			}
		}
		return false;
	}

	private int getCrystalTier(ItemStack item) {
		if (item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().hasLore()) {
			String name = item.getItemMeta().getDisplayName();

			if (name.contains("King")) {
				return 4;
			} else if (name.contains("Paladin")) {
				return 3;
			} else if (name.contains("Legendary")) {
				return 2;
			} else if (name.contains("Heroic")) {
				return 1;
			}
		}
		return 0;
	}

	private static String translate(String translate) {
		return ChatColor.translateAlternateColorCodes('&', translate);
	}
}