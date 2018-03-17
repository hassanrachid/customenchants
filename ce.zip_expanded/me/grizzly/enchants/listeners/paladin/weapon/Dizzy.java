package me.grizzly.enchants.listeners.paladin.weapon;

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

public class Dizzy extends EnchantListener {

	public Dizzy(EraEnchants pl) {
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
				Player target = (Player) e.getEntity();

				if (EnchantUtil.hasEnchant(p.getItemInHand(), CustomEnchant.DIZZY)) {
					int chance = random.nextInt(99) + 1;
					if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.DIZZY, "I")) {
						if (chance <= 1) {
							target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (20 * 3), 0));
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.DIZZY));
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.DIZZY, "II")) {
						if (chance <= 2) {
							target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (20 * 3), 0));
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.DIZZY));
						}
					}
				}
			}
		}
	}
}