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

public class Scavenger extends EnchantListener {

	public Scavenger(EraEnchants pl) {
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

		if (EnchantUtil.hasEnchant(p.getItemInHand(), CustomEnchant.SCAVENGER)) {
			if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.SCAVENGER, "I")) {
				for (ItemStack item : e.getBlock().getDrops())
					e.getBlock().getDrops().add(item);
			} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.SCAVENGER, "II")) {
				for (ItemStack item : e.getBlock().getDrops()) {
					e.getBlock().getDrops().add(item);
					e.getBlock().getDrops().add(item);
				}
			}
		}
	}
}