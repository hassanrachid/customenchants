package me.grizzly.enchants.listeners.hero.weapon;

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

public class Theft extends EnchantListener {

	public Theft(EraEnchants pl) {
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

				if (EnchantUtil.hasEnchant(p.getItemInHand(), CustomEnchant.THEFT)) {
					int chance = random.nextInt(99) + 1;
					if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.THEFT, "I")) {
						if (chance <= 3) {
							p.setExp(p.getExp() + (target.getExp() * 0.10F));
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.THEFT));
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.THEFT, "II")) {
						if (chance <= 5) {
							p.setExp(p.getExp() + (target.getExp() * 0.15F));
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.THEFT));
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.THEFT, "III")) {
						if (chance <= 7) {
							p.setExp(p.getExp() + (target.getExp() * 0.30F));
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.THEFT));
						}
					}
				}
			}
		}
	}
}