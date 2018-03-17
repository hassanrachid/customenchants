package me.grizzly.enchants.listeners.king.armor;

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

public class JellyLegs extends EnchantListener {

	public JellyLegs(EraEnchants pl) {
		super(pl);
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			if (e.getCause() == DamageCause.FALL) {
				Player p = (Player) e.getEntity();

				if (p.getInventory().getLeggings() == null
						|| p.getInventory().getLeggings().getType() != Material.DIAMOND_LEGGINGS) {
					return;
				}

				ItemStack legs = p.getInventory().getLeggings();

				if (EnchantUtil.hasEnchant(legs, CustomEnchant.JELLYLEGS)) {
					if (EnchantUtil.isEnchantLevel(legs, CustomEnchant.JELLYLEGS, "I")) {
						e.setCancelled(true);
					}
				}
			}
		}
	}
}