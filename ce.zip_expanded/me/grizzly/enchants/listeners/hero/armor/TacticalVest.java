package me.grizzly.enchants.listeners.hero.armor;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

import me.grizzly.enchants.EraEnchants;
import me.grizzly.enchants.listeners.EnchantListener;
import me.grizzly.enchants.utils.CustomEnchant;
import me.grizzly.enchants.utils.EnchantUtil;

public class TacticalVest extends EnchantListener {

	public TacticalVest(EraEnchants pl) {
		super(pl);
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		if (e.isCancelled()) {
			return;
		}
		if (e.getEntity() instanceof Player) {
			if (e.getCause() == DamageCause.ENTITY_EXPLOSION || e.getCause() == DamageCause.BLOCK_EXPLOSION) {
				Player p = (Player) e.getEntity();

				if (p.getInventory().getChestplate() == null
						|| p.getInventory().getChestplate().getType() != Material.DIAMOND_CHESTPLATE) {
					return;
				}

				ItemStack chest = p.getInventory().getChestplate();

				if (EnchantUtil.hasEnchant(chest, CustomEnchant.TACTICALVEST)) {
					if (EnchantUtil.isEnchantLevel(chest, CustomEnchant.TACTICALVEST, "I")) {
						e.setCancelled(true);
					}
				}
			}
		}
	}
}