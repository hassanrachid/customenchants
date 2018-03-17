package me.grizzly.enchants.listeners.king.bow;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;

import me.grizzly.enchants.EraEnchants;
import me.grizzly.enchants.listeners.AbilityActivateEvent;
import me.grizzly.enchants.listeners.EnchantListener;
import me.grizzly.enchants.utils.CustomEnchant;
import me.grizzly.enchants.utils.EnchantUtil;

public class Corruption extends EnchantListener {

	public Corruption(EraEnchants pl) {
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

						if (EnchantUtil.hasEnchant(p.getItemInHand(), CustomEnchant.CORRUPTION)) {
							double chance = getRandom();
							if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.CORRUPTION, "I")) {
								if (chance <= 0.25) {
									for (PotionEffect potion : target.getActivePotionEffects()) {
										target.removePotionEffect(potion.getType());
									}
									Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.CORRUPTION));
								}
							} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.CORRUPTION, "II")) {
								if (chance <= 0.75) {
									for (PotionEffect potion : target.getActivePotionEffects()) {
										target.removePotionEffect(potion.getType());
									}
									Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.CORRUPTION));
								}
							} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.CORRUPTION, "III")) {
								if (chance <= 1) {
									for (PotionEffect potion : target.getActivePotionEffects()) {
										target.removePotionEffect(potion.getType());
									}
									Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.CORRUPTION));
								}
							} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.CORRUPTION, "IV")) {
								if (chance <= 1.25) {
									for (PotionEffect potion : target.getActivePotionEffects()) {
										target.removePotionEffect(potion.getType());
									}
									Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.CORRUPTION));
								}
							} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.CORRUPTION, "V")) {
								if (chance <= 1.75) {
									for (PotionEffect potion : target.getActivePotionEffects()) {
										target.removePotionEffect(potion.getType());
									}
									Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.CORRUPTION));
								}
							} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.CORRUPTION, "VI")) {
								if (chance <= 2.15) {
									for (PotionEffect potion : target.getActivePotionEffects()) {
										target.removePotionEffect(potion.getType());
									}
									Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.CORRUPTION));
								}
							} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.CORRUPTION, "VII")) {
								if (chance <= 2.50) {
									for (PotionEffect potion : target.getActivePotionEffects()) {
										target.removePotionEffect(potion.getType());
									}
									Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.CORRUPTION));
								}
							} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.CORRUPTION,
									"VIII")) {
								if (chance <= 2.85) {
									for (PotionEffect potion : target.getActivePotionEffects()) {
										target.removePotionEffect(potion.getType());
									}
									Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.CORRUPTION));
								}
							} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.CORRUPTION, "IX")) {
								if (chance <= 3.00) {
									for (PotionEffect potion : target.getActivePotionEffects()) {
										target.removePotionEffect(potion.getType());
									}
									Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.CORRUPTION));
								}
							} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.CORRUPTION, "X")) {
								// Was 3.20
								if (chance <= 3.20) {
									for (PotionEffect potion : target.getActivePotionEffects()) {
										target.removePotionEffect(potion.getType());
									}
									Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.CORRUPTION));
								}
							}
						}
					}
				}
			}
		}
	}
}