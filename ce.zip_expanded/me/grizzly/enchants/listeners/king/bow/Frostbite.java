package me.grizzly.enchants.listeners.king.bow;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
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

public class Frostbite extends EnchantListener {

	public Frostbite(EraEnchants pl) {
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
						
						if (EnchantUtil.hasEnchant(p.getItemInHand(), CustomEnchant.FROSTBITE)) {
							int chance = random.nextInt(99) + 1;
							if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.FROSTBITE, "I")) {
								if (chance <= 4) {
									target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (20 * 6), 1));
									Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.FROSTBITE));
								}
							} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.FROSTBITE, "II")) {
								if (chance <= 5) {
									target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (20 * 6), 2));
									Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.FROSTBITE));
								}
							} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.FROSTBITE, "III")) {
								// Was 8
								if (chance <= 8) {
									target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (20 * 6), 3));
									Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.FROSTBITE));
								}
							}
						}
					}
				}
			}
		}
	}
}