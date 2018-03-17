package me.grizzly.enchants.listeners.paladin.weapon;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.grizzly.enchants.EraEnchants;
import me.grizzly.enchants.listeners.AbilityActivateEvent;
import me.grizzly.enchants.listeners.EnchantListener;
import me.grizzly.enchants.utils.CustomEnchant;
import me.grizzly.enchants.utils.EnchantUtil;

public class Leech extends EnchantListener {

	public Leech(EraEnchants pl) {
		super(pl);
	}

	@EventHandler (priority = EventPriority.HIGHEST)
	public void onEntityDamageEntity(EntityDamageByEntityEvent e) {
		if (e.isCancelled()) {
			return;
		}
		if (e.getDamager() instanceof Player) {
			if (e.getEntity() instanceof Player) {
				Player p = (Player) e.getDamager();

				if (EnchantUtil.hasEnchant(p.getItemInHand(), CustomEnchant.LEECH)) {
					int chance = random.nextInt(99) + 1;
					if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.LEECH, "I")) {
						if (chance <= 5)
							if (p.getHealth() + 3.0D <= 20) {
								p.setHealth(p.getHealth() + 3.0D);
								Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.LEECH));
							} else {
								p.setHealth(p.getMaxHealth());
								Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.LEECH));
							}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.LEECH, "II")) {
						if (chance <= 10)
							if (p.getHealth() + 3.0D <= 20) {
								p.setHealth(p.getHealth() + 3.0D);
								Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.LEECH));
							} else {
								p.setHealth(p.getMaxHealth());
								Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.LEECH));
							}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.LEECH, "III")) {
						if (chance <= 15)
							if (p.getHealth() + 3.0D <= 20) {
								p.setHealth(p.getHealth() + 3.0D);
								Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.LEECH));
							} else {
								p.setHealth(p.getMaxHealth());
								Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.LEECH));
							}
					}
				}
			}
		}
	}
}