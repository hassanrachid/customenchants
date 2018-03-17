package me.grizzly.enchants.listeners.paladin.tool;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import me.grizzly.enchants.EraEnchants;
import me.grizzly.enchants.listeners.EnchantListener;
import me.grizzly.enchants.utils.CustomEnchant;
import me.grizzly.enchants.utils.EnchantUtil;

public class Vacuum extends EnchantListener {

	public Vacuum(EraEnchants pl) {
		super(pl);
	}

	@EventHandler (priority = EventPriority.HIGHEST)
	public void onBlockMine(BlockBreakEvent e) {
		if (e.isCancelled()) {
			return;
		}
		Player p = e.getPlayer();

		if (p.getItemInHand() == null || (!(p.getItemInHand().getType() == Material.DIAMOND_PICKAXE
				|| p.getItemInHand().getType() == Material.DIAMOND_SPADE))) {
			return;
		}

		if (EnchantUtil.hasEnchant(p.getItemInHand(), CustomEnchant.VACUUM)) {
			if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.VACUUM, "I")) {
				for (ItemStack item : e.getBlock().getDrops())
					givePlayerItem(p, item);
			}
			e.getBlock().getDrops().clear();
		}
	}

	private void givePlayerItem(Player player, ItemStack item) {
		if (player.getInventory().firstEmpty() != -1) {
			player.getInventory().addItem(new ItemStack[] { item });
		} else if (getSlot(player, item.getType()) != -1) {
			player.getInventory().addItem(new ItemStack[] { item });
		} else {
			player.getWorld().dropItem(player.getLocation(), item);
		}
	}

	private int getSlot(Player p, Material m) {
		for (int i = 0; i < p.getInventory().getSize(); i++) {
			if ((p.getInventory().getItem(i).getType() == m)
					&& (p.getInventory().getItem(i).getAmount() < p.getInventory().getItem(i).getMaxStackSize())) {
				return i;
			}
		}
		return -1;
	}
}