package me.grizzly.enchants.listeners.legendary.weapon;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;

import me.grizzly.enchants.EraEnchants;
import me.grizzly.enchants.listeners.EnchantListener;
import me.grizzly.enchants.utils.CustomEnchant;
import me.grizzly.enchants.utils.EnchantUtil;

public class Elixir extends EnchantListener {

	public Elixir(EraEnchants pl) {
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

				if (EnchantUtil.hasEnchant(p.getItemInHand(), CustomEnchant.ELIXIR)) {
					int chance = random.nextInt(99) + 1;
					if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.ELIXIR, "I")) {
						if (chance <= 2) {
							for (PotionEffect potion : target.getActivePotionEffects()) {
								target.removePotionEffect(potion.getType());

								int duration = potion.getDuration() - 5;

								if (duration > 0) {
									target.addPotionEffect(new PotionEffect(potion.getType(), potion.getDuration(),
											potion.getAmplifier()));
								}
							}
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.ELIXIR, "II")) {
						if (chance <= 4) {
							for (PotionEffect potion : target.getActivePotionEffects()) {
								target.removePotionEffect(potion.getType());

								int duration = potion.getDuration() - 5;

								if (duration > 0) {
									target.addPotionEffect(new PotionEffect(potion.getType(), potion.getDuration(),
											potion.getAmplifier()));
								}
							}
						}
					}
				}
			}
		}
	}
}