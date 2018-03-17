package me.grizzly.enchants.listeners.king.tool;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockDamageEvent;

import me.grizzly.enchants.EraEnchants;
import me.grizzly.enchants.listeners.EnchantListener;
import me.grizzly.enchants.utils.CustomEnchant;
import me.grizzly.enchants.utils.EnchantUtil;

public class Obsidian extends EnchantListener {

	public Obsidian(EraEnchants pl) {
		super(pl);
	}

	@EventHandler (priority = EventPriority.HIGHEST)
	public void onBlockDamage(BlockDamageEvent e) {
		if (e.isCancelled()) {
			return;
		}
		Player p = e.getPlayer();

		if (e.getBlock().getType() == Material.OBSIDIAN) {

			if (p.getItemInHand() == null || p.getItemInHand().getType() != Material.DIAMOND_PICKAXE) {
				return;
			}

			if (EnchantUtil.hasEnchant(p.getItemInHand(), CustomEnchant.OBSIDIAN)) {
				if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.OBSIDIAN, "I")) {
					e.setInstaBreak(true);
				}
			}
		}
	}
}