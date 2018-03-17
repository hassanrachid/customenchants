package me.grizzly.enchants.listeners.hero.weapon;

import org.bukkit.Bukkit;
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

public class Cripple extends EnchantListener {

	public Cripple(EraEnchants pl) {
		super(pl);
	}

	@EventHandler (priority = EventPriority.HIGHEST)
	public void onEntityDamageEvent(EntityDamageByEntityEvent e) {
		if (e.isCancelled()) {
			return;
		}
		if (e.getDamager() instanceof Player) {
			if (e.getEntity() instanceof Player) {
				Player p = (Player) e.getDamager();
				Player target = (Player) e.getEntity();

				if (EnchantUtil.hasEnchant(p.getItemInHand(), CustomEnchant.CRIPPLE)) {
					int chance = random.nextInt(99) + 1;
					if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.CRIPPLE, "I")) {
						if (chance <= 2) {
							target.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, (20 * 5), 0));
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.CRIPPLE));
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.CRIPPLE, "II")) {
						if (chance <= 4) {
							target.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, (20 * 5), 1));
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.CRIPPLE));
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.CRIPPLE, "III")) {
						if (chance <= 6) {
							target.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, (20 * 5), 2));
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.CRIPPLE));
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.CRIPPLE, "IV")) {
						if (chance <= 7) {
							target.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, (20 * 5), 3));
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.CRIPPLE));
						}
					}
				}
			}
		}
	}
}