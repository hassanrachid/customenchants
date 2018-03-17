package me.grizzly.enchants.listeners.paladin.armor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import com.earth2me.essentials.craftbukkit.SetExpFix;

import me.grizzly.enchants.EraEnchants;
import me.grizzly.enchants.listeners.AbilityActivateEvent;
import me.grizzly.enchants.listeners.EnchantListener;
import me.grizzly.enchants.utils.CustomEnchant;
import me.grizzly.enchants.utils.EnchantUtil;

public class Fatigue extends EnchantListener {

	public Fatigue(EraEnchants pl) {
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

				if (p.getInventory().getChestplate() == null
						|| p.getInventory().getChestplate().getType() != Material.DIAMOND_BOOTS) {
					return;
				}

				ItemStack item = p.getInventory().getChestplate();

				if (EnchantUtil.hasEnchant(item, CustomEnchant.FATIGUE)) {
					int chance = random.nextInt(99) + 1;
					if (EnchantUtil.isEnchantLevel(item, CustomEnchant.FATIGUE, "I")) {
						if (chance <= 4) {
							if (SetExpFix.getTotalExperience(p) >= 100) {
								if (p.getHealth() + 3 > 20) {
									p.setHealth(20);
								} else {
									p.setHealth(p.getHealth() + 3);
								}
								Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.FATIGUE));
								SetExpFix.setTotalExperience(p, SetExpFix.getTotalExperience(p) - 100);
							}
						}
					} else if (EnchantUtil.isEnchantLevel(item, CustomEnchant.FATIGUE, "II")) {
						if (chance <= 5) {
							if (SetExpFix.getTotalExperience(p) >= 50) {
								if (p.getHealth() + 3 > 20) {
									p.setHealth(20);
								} else {
									p.setHealth(p.getHealth() + 3);
								}
								Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.FATIGUE));
								SetExpFix.setTotalExperience(p, SetExpFix.getTotalExperience(p) - 100);
							}
						}
					} else if (EnchantUtil.isEnchantLevel(item, CustomEnchant.FATIGUE, "III")) {
						if (chance <= 6) {
							if (SetExpFix.getTotalExperience(p) >= 25) {
								if (p.getHealth() + 3 > 20) {
									p.setHealth(20);
								} else {
									p.setHealth(p.getHealth() + 3);
								}
								Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.FATIGUE));
								SetExpFix.setTotalExperience(p, SetExpFix.getTotalExperience(p) - 100);
							}
						}
					}
				}
			}
		}
	}
}