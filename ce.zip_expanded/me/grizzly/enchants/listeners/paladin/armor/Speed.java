package me.grizzly.enchants.listeners.paladin.armor;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.EquipmentSetEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.grizzly.enchants.EraEnchants;
import me.grizzly.enchants.listeners.EnchantListener;
import me.grizzly.enchants.utils.CustomEnchant;
import me.grizzly.enchants.utils.EnchantUtil;

public class Speed extends EnchantListener {

	public Speed(EraEnchants pl) {
		super(pl);
	}

	@EventHandler
	public void onEquip(EquipmentSetEvent e) {
		Player p = (Player) e.getHumanEntity();
		if (e.getPreviousItem() != null && e.getPreviousItem().getType() == Material.DIAMOND_BOOTS) {
			if (EnchantUtil.hasEnchant(e.getPreviousItem(), CustomEnchant.SPEED)) {
				if (EnchantUtil.isEnchantLevel(e.getPreviousItem(), CustomEnchant.SPEED, "I")) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
				} else if (EnchantUtil.isEnchantLevel(e.getPreviousItem(), CustomEnchant.SPEED, "II")) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
				} else if (EnchantUtil.isEnchantLevel(e.getPreviousItem(), CustomEnchant.SPEED, "III")) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
				}
			}
		} else if (e.getNewItem() != null && e.getNewItem().getType() == Material.DIAMOND_BOOTS) {
			if (EnchantUtil.hasEnchant(e.getNewItem(), CustomEnchant.SPEED)) {
				p.removePotionEffect(PotionEffectType.SPEED);
			}
		}
	}
}