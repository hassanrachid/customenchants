package me.grizzly.enchants.listeners.paladin.armor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.grizzly.enchants.EraEnchants;
import me.grizzly.enchants.listeners.AbilityActivateEvent;
import me.grizzly.enchants.listeners.EnchantListener;
import me.grizzly.enchants.utils.CustomEnchant;
import me.grizzly.enchants.utils.EnchantUtil;

public class Clutch extends EnchantListener {

	public Clutch(EraEnchants pl) {
		super(pl);
	}

	@EventHandler (priority = EventPriority.HIGHEST)
	public void onEntityDamageEntity(EntityDamageByEntityEvent e) {
		if (e.isCancelled()) {
			return;
		}
		if (e.getDamager() instanceof Player) {
			if (e.getEntity() instanceof Player) {
				Player p = (Player) e.getEntity();

				if (p.getInventory().getChestplate() == null
						|| p.getInventory().getChestplate().getType() != Material.DIAMOND_CHESTPLATE) {
					return;
				}

				if (EnchantUtil.hasEnchant(p.getItemInHand(), CustomEnchant.CLUTCH)) {
					if (p.getHealth() <= 2.0D) {
						if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.CLUTCH, "I")) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, (5 * 20), 3));
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.CLUTCH));
						} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.CLUTCH, "II")) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, (5 * 20), 4));
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.CLUTCH));
						}
					}
				}
			}
		}
	}
}