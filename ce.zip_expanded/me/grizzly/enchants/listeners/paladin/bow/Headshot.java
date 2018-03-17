package me.grizzly.enchants.listeners.paladin.bow;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.grizzly.enchants.EraEnchants;
import me.grizzly.enchants.listeners.AbilityActivateEvent;
import me.grizzly.enchants.listeners.EnchantListener;
import me.grizzly.enchants.utils.CustomEnchant;
import me.grizzly.enchants.utils.EnchantUtil;

public class Headshot extends EnchantListener {

	public Headshot(EraEnchants pl) {
		super(pl);
	}

	@EventHandler (priority = EventPriority.HIGHEST)
	public void onEntityDamageEntity(EntityDamageByEntityEvent e) {
		if (e.isCancelled()) {
			return;
		}
		if (e.getEntity() instanceof Player) {
			if (e.getDamager() instanceof Arrow) {
				Player target = (Player) e.getEntity();
				Arrow arrow = (Arrow) e.getDamager();
				if (arrow.getShooter() instanceof Player) {
					Player p = (Player) arrow.getShooter();

					if (p.getItemInHand().getType() == Material.BOW) {
						if (EnchantUtil.hasEnchant(p.getItemInHand(), CustomEnchant.HEADSHOT)) {
							double chance = getRandom();
							if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.HEADSHOT, "I")) {
								if (chance <= 3) {
									if (target.getHealth() == 0.0) {
										e.setCancelled(true);
									} else {
										target.damage((p.getLastDamage()));
									}
									Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.HEADSHOT));
								}
							} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.HEADSHOT, "II")) {
								if (chance <= 5) {
									if (target.getHealth() == 0.0) {
										e.setCancelled(true);
									} else {
										target.damage((p.getLastDamage()));
									}
									Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.HEADSHOT));
								}
							} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.HEADSHOT, "III")) {
								if (chance <= 6) {
									if (target.getHealth() == 0.0) {
										e.setCancelled(true);
									} else {
										target.damage((p.getLastDamage()));
									}
									Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.HEADSHOT));
								}
							}
						}
					}
				}
			}
		}
	}
}
