package me.grizzly.enchants.listeners.legendary.weapon;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.grizzly.enchants.EraEnchants;
import me.grizzly.enchants.listeners.EnchantListener;
import me.grizzly.enchants.utils.CustomEnchant;
import me.grizzly.enchants.utils.EnchantUtil;

public class Hunger extends EnchantListener {

	public Hunger(EraEnchants pl) {
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

				if (EnchantUtil.hasEnchant(p.getItemInHand(), CustomEnchant.HUNGER)) {
					int chance = random.nextInt(99) + 1;
					if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.HUNGER, "I")) {
						if (chance <= 1) {
							target.setSaturation(target.getSaturation() - 0.5F);
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.HUNGER, "II")) {
						if (chance <= 2) {
							target.setSaturation(target.getSaturation() - 0.5F);
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.HUNGER, "III")) {
						if (chance <= 3) {
							target.setSaturation(target.getSaturation() - 0.5F);
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.HUNGER, "IV")) {
						if (chance <= 4) {
							target.setSaturation(target.getSaturation() - 0.5F);
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.HUNGER, "V")) {
						if (chance <= 5) {
							target.setSaturation(target.getSaturation() - 0.5F);
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.HUNGER, "VI")) {
						if (chance <= 6) {
							target.setSaturation(target.getSaturation() - 0.5F);
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.HUNGER, "VII")) {
						if (chance <= 7) {
							target.setSaturation(target.getSaturation() - 0.5F);
						}
					}
				}
			}
		}
	}
}