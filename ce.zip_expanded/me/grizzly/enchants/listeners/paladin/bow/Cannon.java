package me.grizzly.enchants.listeners.paladin.bow;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import me.grizzly.enchants.EraEnchants;
import me.grizzly.enchants.listeners.AbilityActivateEvent;
import me.grizzly.enchants.listeners.EnchantListener;
import me.grizzly.enchants.utils.CustomEnchant;
import me.grizzly.enchants.utils.EnchantUtil;

public class Cannon extends EnchantListener {

	public Cannon(EraEnchants pl) {
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
						if (EnchantUtil.hasEnchant(p.getItemInHand(), CustomEnchant.CANNON)) {
							int chance = random.nextInt(99) + 1;
							Vector v = calculateVector(target);

							if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.CANNON, "I")) {
								if (chance <= 2) {
									target.setVelocity(v);
									Bukkit.getPluginManager()
											.callEvent(new AbilityActivateEvent(p, CustomEnchant.CANNON));
								}
							} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.CANNON, "II")) {
								if (chance <= 3) {
									target.setVelocity(v);
									Bukkit.getPluginManager()
											.callEvent(new AbilityActivateEvent(p, CustomEnchant.CANNON));
								}
							} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.CANNON, "III")) {
								if (chance <= 4) {
									target.setVelocity(v);
									Bukkit.getPluginManager()
											.callEvent(new AbilityActivateEvent(p, CustomEnchant.CANNON));
								}
							} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.CANNON, "IV")) {
								if (chance <= 5) {
									target.setVelocity(v);
									Bukkit.getPluginManager()
											.callEvent(new AbilityActivateEvent(p, CustomEnchant.CANNON));
								}
							} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.CANNON, "V")) {
								if (chance <= 6) {
									target.setVelocity(v);
									Bukkit.getPluginManager()
											.callEvent(new AbilityActivateEvent(p, CustomEnchant.CANNON));
								}
							} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.CANNON, "VI")) {
								// Was 8
								if (chance <= 8) {
									target.setVelocity(v);
									Bukkit.getPluginManager()
											.callEvent(new AbilityActivateEvent(p, CustomEnchant.CANNON));
								}
							}
						}
					}
				}
			}
		}
	}
}