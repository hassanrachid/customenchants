package me.grizzly.enchants.listeners.hero.armor;

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

public class Diver extends EnchantListener {

	public Diver(EraEnchants pl) {
		super(pl);
	}

	@EventHandler
	public void onEquip(EquipmentSetEvent e) {
		Player p = (Player) e.getHumanEntity();
		if (e.getPreviousItem() != null && e.getPreviousItem().getType() == Material.DIAMOND_HELMET) {
			if (EnchantUtil.hasEnchant(e.getPreviousItem(), CustomEnchant.DIVER)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, 0));
			}
		} else if (e.getNewItem() != null && e.getNewItem().getType() == Material.DIAMOND_HELMET) {
			if (EnchantUtil.hasEnchant(e.getNewItem(), CustomEnchant.DIVER)) {
				p.removePotionEffect(PotionEffectType.WATER_BREATHING);
			}
		}
	}
}