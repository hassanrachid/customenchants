package me.grizzly.enchants.listeners.legendary.armor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import me.grizzly.enchants.EraEnchants;
import me.grizzly.enchants.listeners.AbilityActivateEvent;
import me.grizzly.enchants.listeners.EnchantListener;
import me.grizzly.enchants.utils.CustomEnchant;
import me.grizzly.enchants.utils.EnchantUtil;

public class Savior extends EnchantListener {

	public Savior(EraEnchants pl) {
		super(pl);
	}

	@EventHandler (priority = EventPriority.HIGHEST)
	public void onEntityDamageEvent(EntityDamageByEntityEvent e) {
		if (e.isCancelled()) {
			return;
		}
		if (e.getDamager() instanceof Player) {
			if (e.getEntity() instanceof Player) {
				Player p = (Player) e.getEntity();

				if (p.getInventory().getHelmet() == null
						|| p.getInventory().getHelmet().getType() != Material.DIAMOND_HELMET) {
					return;
				}

				ItemStack item = p.getInventory().getHelmet();

				if (EnchantUtil.hasEnchant(item, CustomEnchant.SAVIOR)) {
					int chance = random.nextInt(99);
					if (EnchantUtil.isEnchantLevel(item, CustomEnchant.SAVIOR, "I")) {
						if (chance <= 2) {
							p.setLastDamage(p.getLastDamage() / 2);
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.SAVIOR));
						}
					} else if (EnchantUtil.isEnchantLevel(item, CustomEnchant.SAVIOR, "II")) {
						if (chance <= 4) {
							p.setLastDamage(p.getLastDamage() / 2);
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.SAVIOR));
						}
					} else if (EnchantUtil.isEnchantLevel(item, CustomEnchant.SAVIOR, "III")) {
						if (chance <= 5) {
							p.setLastDamage(p.getLastDamage() / 2);
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.SAVIOR));
						}
					}
				}
			}
		}
	}
}