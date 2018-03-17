package me.grizzly.enchants.listeners.legendary.weapon;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.grizzly.enchants.EraEnchants;
import me.grizzly.enchants.listeners.EnchantListener;
import me.grizzly.enchants.utils.CustomEnchant;
import me.grizzly.enchants.utils.EnchantUtil;

public class Freeze extends EnchantListener {

	public Freeze(EraEnchants pl) {
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

				if (EnchantUtil.hasEnchant(p.getItemInHand(), CustomEnchant.FREEZE)) {
					int chance = random.nextInt(99) + 1;
					if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.FREEZE, "I")) {
						if (chance <= 2) {
							target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (20 * 6), 1));
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.FREEZE, "II")) {
						if (chance <= 4) {
							target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (20 * 6), 2));
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.FREEZE, "III")) {
						if (chance <= 5) {
							target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (20 * 6), 2));
						}
					}
				}
			}
		}
	}
}