package me.grizzly.enchants.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtil {

	public static void giveItem(Player p, int tier, String itemType) {

		Material type;
		String tool;

		if (itemType.equalsIgnoreCase("sword")) {
			type = Material.DIAMOND_SWORD;
			tool = "Sword";
		} else if (itemType.equalsIgnoreCase("axe")) {
			type = Material.DIAMOND_AXE;
			tool = "Axe";
		} else if (itemType.equalsIgnoreCase("chestplate")) {
			type = Material.DIAMOND_CHESTPLATE;
			tool = "Chestplate";
		} else if (itemType.equalsIgnoreCase("helmet")) {
			type = Material.DIAMOND_HELMET;
			tool = "Helmet";
		} else if (itemType.equalsIgnoreCase("leggings")) {
			type = Material.DIAMOND_LEGGINGS;
			tool = "Leggings";
		} else if (itemType.equalsIgnoreCase("boots")) {
			type = Material.DIAMOND_BOOTS;
			tool = "Boots";
		} else if (itemType.equalsIgnoreCase("bow")) {
			type = Material.BOW;
			tool = "Bow";
		} else if (itemType.equalsIgnoreCase("pickaxe")) {
			type = Material.DIAMOND_PICKAXE;
			tool = "Pickaxe";
		} else if (itemType.equalsIgnoreCase("shovel")) {
			type = Material.DIAMOND_SPADE;
			tool = "Shovel";
		} else {
			type = null;
			tool = "";
		}

		ItemStack item = new ItemStack(type);
		ItemMeta meta = item.getItemMeta();

		meta.setDisplayName(EnchantUtil.getTierColor(tier) + EnchantUtil.getName(tier) + " " + tool);
		item.setItemMeta(meta);

		p.getInventory().addItem(item);
	}
}