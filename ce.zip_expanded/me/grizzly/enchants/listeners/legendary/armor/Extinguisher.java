package me.grizzly.enchants.listeners.legendary.armor;

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

public class Extinguisher extends EnchantListener {
	public Extinguisher(EraEnchants pl) {
		super(pl);
	}

	@EventHandler
	public void onEquip(EquipmentSetEvent e) {
		Player p = (Player) e.getHumanEntity();
		if (e.getPreviousItem() != null && e.getPreviousItem().getType() == Material.DIAMOND_LEGGINGS) {
			if (EnchantUtil.hasEnchant(e.getPreviousItem(), CustomEnchant.EXTINGUISHER)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0));
			}
		} else if (e.getNewItem() != null && e.getNewItem().getType() == Material.DIAMOND_LEGGINGS) {
			if (EnchantUtil.hasEnchant(e.getNewItem(), CustomEnchant.EXTINGUISHER)) {
				p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
			}
		}
	}
}
