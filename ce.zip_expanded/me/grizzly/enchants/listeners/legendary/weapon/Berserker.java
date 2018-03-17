package me.grizzly.enchants.listeners.legendary.weapon;

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

public class Berserker extends EnchantListener {

	public Berserker(EraEnchants pl) {
		super(pl);
	}

	@EventHandler (priority = EventPriority.HIGHEST)
	public void onEntityDamageEvent(EntityDamageByEntityEvent e) {
		if (e.isCancelled()) {
			return;
		}
		if(!(e.getEntity() instanceof Player)) return;
		if (e.getDamager() instanceof Player) {
			if (e.getEntity() instanceof Player) {
				Player p = (Player) e.getDamager();
				Player target = (Player) e.getEntity();

				if (EnchantUtil.hasEnchant(p.getItemInHand(), CustomEnchant.BERSERKER)) {
					int chance = random.nextInt(99) + 1;
					if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.BERSERKER, "I")) {
						if (chance <= 2) {
							if (target.getHealth() == 0.0) {
								e.setCancelled(true);
							} else {
								target.damage((p.getLastDamage()));
							}
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.BERSERKER));
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.BERSERKER, "II")) {
						if (chance <= 3) {
							if (target.getHealth() == 0.0) {
								e.setCancelled(true);
							} else {
								target.damage((p.getLastDamage()));
							}
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.BERSERKER));
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.BERSERKER, "III")) {
						if (chance <= 4) {
							if (target.getHealth() == 0.0) {
								e.setCancelled(true);
							} else {
								target.damage((p.getLastDamage()));
							}
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.BERSERKER));
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.BERSERKER, "IV")) {
						if (chance <= 5) {
							if (target.getHealth() == 0.0) {
								e.setCancelled(true);
							} else {
								target.damage((p.getLastDamage()));
							}
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.BERSERKER));
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.BERSERKER, "V")) {
						if (chance <= 6) {
							if (target.getHealth() == 0.0) {
								e.setCancelled(true);
							} else {
								target.damage((p.getLastDamage()));
							}
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.BERSERKER));
						}
					}
				}
			}
		}
	}
}