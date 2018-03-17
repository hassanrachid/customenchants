package me.grizzly.enchants.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EnchantUtil {

	public static boolean isApplicableFor(ItemStack item, CustomEnchant enchant) {
		if (enchant.getMaxLevel() >= getTier(item))
			return true;

		return false;
	}

	public static boolean hasEnchant(ItemStack item, CustomEnchant enchant) {
		if (item.hasItemMeta() && item.getItemMeta().hasLore()) {
			List<String> lore = item.getItemMeta().getLore();
			for (String s : lore) {
				if (s.contains(enchant.getName()))
					return true;
			}
		}
		return false;
	}

	public static boolean isEnchantLevel(ItemStack item, CustomEnchant enchant, String level) {
		if (item.hasItemMeta() && item.getItemMeta().hasLore()) {
			List<String> lore = item.getItemMeta().getLore();
			if (lore.contains(getTierColor(enchant.getTier()) + enchant.getName() + " " + level)) {
				return true;
			}
		}
		return false;
	}

	public static void addEnchant(ItemStack item, CustomEnchant enchant, String level) {
		ItemMeta meta = item.getItemMeta();

		List<String> lore = new ArrayList<String>();

		lore.add(getTierColor(enchant.getTier()) + enchant.getName() + " " + level);
		meta.setLore(lore);
		item.setItemMeta(meta);
	}

	public static int getTier(ItemStack item) {
		String displayName = item.getItemMeta().getDisplayName();

		if (displayName.contains("Heroic"))
			return 1;
		if (displayName.contains("Legendary"))
			return 2;
		if (displayName.contains("Paladin"))
			return 3;
		if (displayName.contains("King"))
			return 4;
		return 0;
	}

	public static int getTierFromString(String s) {

		if (s.contains("Heroic"))
			return 1;
		if (s.contains("Legendary"))
			return 2;
		if (s.contains("Paladin"))
			return 3;
		if (s.contains("King"))
			return 4;
		return 0;
	}

	public static String getName(int tier) {
		if (tier == 1)
			return "Heroic";
		if (tier == 2)
			return "Legendary";
		if (tier == 3)
			return "Paladin";
		if (tier == 4)
			return "King";
		return "None";
	}

	public static ChatColor getTierColor(int tier) {
		if (tier == 1)
			return ChatColor.BLUE;
		if (tier == 2)
			return ChatColor.RED;
		if (tier == 3)
			return ChatColor.DARK_GREEN;
		if (tier == 4)
			return ChatColor.GOLD;
		return ChatColor.WHITE;
	}

	public static CustomEnchant parseEnchant(String s) {
		for (CustomEnchant enchant : CustomEnchant.values()) {
			if (enchant.getName().equalsIgnoreCase(s)) {
				return enchant;
			}
		}
		return null;
	}

	public static String integerToRomanNumeral(int input) {
		if (input < 1 || input > 3999)
			return "Invalid Roman Number Value";
		String s = "";
		while (input >= 1000) {
			s += "M";
			input -= 1000;
		}
		while (input >= 900) {
			s += "CM";
			input -= 900;
		}
		while (input >= 500) {
			s += "D";
			input -= 500;
		}
		while (input >= 400) {
			s += "CD";
			input -= 400;
		}
		while (input >= 100) {
			s += "C";
			input -= 100;
		}
		while (input >= 90) {
			s += "XC";
			input -= 90;
		}
		while (input >= 50) {
			s += "L";
			input -= 50;
		}
		while (input >= 40) {
			s += "XL";
			input -= 40;
		}
		while (input >= 10) {
			s += "X";
			input -= 10;
		}
		while (input >= 9) {
			s += "IX";
			input -= 9;
		}
		while (input >= 5) {
			s += "V";
			input -= 5;
		}
		while (input >= 4) {
			s += "IV";
			input -= 4;
		}
		while (input >= 1) {
			s += "I";
			input -= 1;
		}
		return s;
	}
}