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

public class Execution extends EnchantListener {

	public Execution(EraEnchants pl) {
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

				if (EnchantUtil.hasEnchant(p.getItemInHand(), CustomEnchant.EXECUTION)) {
					if (target.getHealth() <= 4.0D) {
						if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.EXECUTION, "I")) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, (4 * 20), 1));
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.EXECUTION));
						}
					}
				}
			}
		}
	}
}