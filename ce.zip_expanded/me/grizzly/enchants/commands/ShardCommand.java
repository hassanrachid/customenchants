package me.grizzly.enchants.commands;

import java.util.Random;

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

import me.grizzly.enchants.utils.ItemBuilder;
import net.md_5.bungee.api.ChatColor;

public class ShardCommand implements CommandExecutor, Listener {

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
			if (e.getItem() == null || e.getItem().getType() == Material.AIR || !isTieredCrystal(e.getItem())) {
				return;
			}

			if (!e.getItem().hasItemMeta()) {
				return;
			}

			Player p = e.getPlayer();
			ItemStack item = e.getItem();
			ItemStack enchanted = null;
			int tier = getCrystalTier(item);
			Random r = new Random();
			if (tier == 1) {
				int rate = r.nextInt(4) + 1;
				enchanted = new ItemBuilder(new ItemStack(Material.INK_SACK, 1, (short) 6))
						.setName(ChatColor.BLUE + "Heroic Shard")
						.setLore(translate("&7Apply this shard to any magical crystal to"),
								translate("&7increase the rate of success"), "", "" + ChatColor.BLUE + ChatColor.BOLD + "SUCCESS INCREASE +" + rate + "%")
						.toItemStack();

			} else if (tier == 2) {
				int rate = r.nextInt(9) + 1;
				enchanted = new ItemBuilder(new ItemStack(Material.INK_SACK, 1, (short) 1))
						.setName(ChatColor.RED + "Legendary Shard")
						.setLore(translate("&7Apply this shard to any magical crystal to"),
								translate("&7increase the rate of success"), "", "" + ChatColor.RED + ChatColor.BOLD + "SUCCESS INCREASE +" + rate + "%")
						.toItemStack();
			} else if (tier == 3) {
				int rate = r.nextInt(14) + 1;
				enchanted = new ItemBuilder(new ItemStack(Material.INK_SACK, 1, (short) 10))
						.setName(ChatColor.DARK_GREEN + "Paladin Shard")
						.setLore(translate("&7Apply this shard to any magical crystal to"),
								translate("&7increase the rate of success"), "", "" + ChatColor.DARK_GREEN + ChatColor.BOLD + "SUCCESS INCREASE +" + rate + "%")
						.toItemStack();
			} else if (tier == 4) {
				int rate = r.nextInt(19) + 1;
				enchanted = new ItemBuilder(new ItemStack(Material.INK_SACK, 1, (short) 14))
						.setName(ChatColor.GOLD + "King Shard")
						.setLore(translate("&7Apply this shard to any magical crystal to"),
								translate("&7increase the rate of success"), "", "" + ChatColor.GOLD + ChatColor.BOLD + "SUCCESS INCREASE +" + rate + "%")
						.toItemStack();
			}

			e.setCancelled(true);
			p.getWorld().playSound(p.getLocation(), Sound.FIREWORK_TWINKLE, 3F, 3F);

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

	private boolean isTieredCrystal(ItemStack item) {
		if (item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().hasLore()) {
			String name = item.getItemMeta().getDisplayName();

			if (name.equalsIgnoreCase(ChatColor.GOLD + "King Shard" + ChatColor.GRAY + " (Right Click)")
					|| name.equalsIgnoreCase(ChatColor.RED + "Legendary Shard" + ChatColor.GRAY + " (Right Click)")
					|| name.equalsIgnoreCase(ChatColor.DARK_GREEN + "Paladin Shard" + ChatColor.GRAY + " (Right Click)")
					|| name.equalsIgnoreCase(ChatColor.BLUE + "Heroic Shard" + ChatColor.GRAY + " (Right Click)")) {
				return true;
			}
		}
		return false;
	}

	private ItemStack getTieredCrystal(String tier) {
		int tierInt = Integer.valueOf(tier);
		if (tierInt == 1) {
			ItemStack item = new ItemBuilder(new ItemStack(Material.INK_SACK, 1, (short) 6))
					.setName(ChatColor.BLUE + "Heroic Shard" + ChatColor.GRAY + " (Right Click)")
					.setLore(translate("&7Examine this shard to reveal its hidden success")).toItemStack();
			return item;
		} else if (tierInt == 2) {
			ItemStack item = new ItemBuilder(new ItemStack(Material.INK_SACK, 1, (short) 1))
					.setName(ChatColor.RED + "Legendary Shard" + ChatColor.GRAY + " (Right Click)")
					.setLore(translate("&7Examine this shard to reveal its hidden success")).toItemStack();
			return item;
		} else if (tierInt == 3) {
			ItemStack item = new ItemBuilder(new ItemStack(Material.INK_SACK, 1, (short) 10))
					.setName(ChatColor.DARK_GREEN + "Paladin Shard" + ChatColor.GRAY + " (Right Click)")
					.setLore(translate("&7Examine this shard to reveal its hidden success")).toItemStack();
			return item;
		} else if (tierInt == 4) {
			ItemStack item = new ItemBuilder(new ItemStack(Material.INK_SACK, 1, (short) 14))
					.setName(ChatColor.GOLD + "King Shard" + ChatColor.GRAY + " (Right Click)")
					.setLore(translate("&7Examine this shard to reveal its hidden success")).toItemStack();
			return item;
		} else {
			return null;
		}
	}

	private static String translate(String translate) {
		return ChatColor.translateAlternateColorCodes('&', translate);
	}
}
