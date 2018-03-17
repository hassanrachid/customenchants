package me.grizzly.enchants.listeners.hero.tool;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;

import me.grizzly.enchants.EraEnchants;
import me.grizzly.enchants.listeners.EnchantListener;
import me.grizzly.enchants.utils.CustomEnchant;
import me.grizzly.enchants.utils.EnchantUtil;

public class Discover extends EnchantListener {

	public Discover(EraEnchants pl) {
		super(pl);
	}

	@EventHandler (priority = EventPriority.HIGHEST)
	public void onBlockMine(BlockBreakEvent e) {
		if (e.isCancelled()) {
			return;
		}
		Player p = e.getPlayer();

		if (p.getItemInHand() == null || p.getItemInHand().getType() != Material.DIAMOND_PICKAXE) {
			return;
		}

		if (EnchantUtil.hasEnchant(p.getItemInHand(), CustomEnchant.DISCOVER)) {
			if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.DISCOVER, "I")) {
				e.setExpToDrop(e.getExpToDrop() * 2);
			} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.DISCOVER, "II")) {
				e.setExpToDrop(e.getExpToDrop() * 3);
			}
		}
	}
}