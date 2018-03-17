package me.grizzly.enchants.listeners.legendary.weapon;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

import me.grizzly.enchants.EraEnchants;
import me.grizzly.enchants.listeners.EnchantListener;
import me.grizzly.enchants.utils.CustomEnchant;
import me.grizzly.enchants.utils.EnchantUtil;

public class Grinder extends EnchantListener {

	public Grinder(EraEnchants pl) {
		super(pl);
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		if (e.getEntity().getKiller() instanceof Player) {
			Player p = (Player) e.getEntity().getKiller();

			if (EnchantUtil.hasEnchant(p.getItemInHand(), CustomEnchant.GRINDER)) {
				if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.GRINDER, "I")) {
					e.setDroppedExp((int) Math.ceil(e.getDroppedExp() * 1.35D));
				} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.GRINDER, "II")) {
					e.setDroppedExp((int) Math.ceil(e.getDroppedExp() * 1.75D));
				} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.GRINDER, "III")) {
					e.setDroppedExp((int) Math.ceil(e.getDroppedExp() * 2.0D));
				} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.GRINDER, "IV")) {
					e.setDroppedExp((int) Math.ceil(e.getDroppedExp() * 2.5D));
				}
			}
		}
	}
}