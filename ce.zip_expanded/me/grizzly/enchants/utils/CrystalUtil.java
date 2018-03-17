package me.grizzly.enchants.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CrystalUtil {
	private static Random random = new Random();

	public static ItemStack getCrystal(CustomEnchant enchant, int level) {
		ItemStack item = new ItemStack(Material.EMERALD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(EnchantUtil.getTierColor(enchant.getTier()) + enchant.getName() + " "
				+ EnchantUtil.integerToRomanNumeral(level));
		int success = getRate();

		List<String> lore = new ArrayList<String>();
		lore.add(translate("&7") + enchant.getEnchantDesc());
		lore.add(ChatColor.RED + "(" + ChatColor.YELLOW + "!" + ChatColor.RED + ")" + ChatColor.YELLOW
				+ " Only applicable to " + enchant.getItemEnchantType());
		lore.add("");
		lore.add(translate("&aSuccess Rate: ") + translate("&a") + success + ChatColor.GREEN + "%");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public static int getLevel(CustomEnchant enchant) {
		return random.nextInt((enchant.getMaxLevel() - 1) + 1) + 1;
	}

	public static String getEnchant(ItemStack item) {
		return ChatColor.stripColor(item.getItemMeta().getDisplayName());
	}

	public static String getEnchantName(String s) {
		String[] parts = s.split(" ");

		if (parts.length == 3)
			return parts[0] + " " + parts[1];
		return parts[0];
	}

	public static int getEnchantLevel(String s) {
		if (s.endsWith(" I"))
			return 1;
		if (s.endsWith(" II"))
			return 2;
		if (s.endsWith(" III"))
			return 3;
		if (s.endsWith(" IV"))
			return 4;
		if (s.endsWith(" V"))
			return 5;
		if (s.endsWith(" VI"))
			return 6;
		if (s.endsWith(" VII"))
			return 7;
		if (s.endsWith(" VIII"))
			return 8;
		if (s.endsWith(" IX"))
			return 9;
		if (s.endsWith(" X"))
			return 10;
		return 0;
	}

	private static String translate(String translate) {
		return ChatColor.translateAlternateColorCodes('&', translate);
	}

	public static boolean isApplicableFor(Material mat, CustomEnchant enchant) {
		if (mat == null || enchant == null)
			return false;
		if (mat == Material.DIAMOND_SWORD && enchant.getApplicableItem() == 1)
			return true;
		if (mat == Material.BOW && enchant.getApplicableItem() == 2)
			return true;
		if (mat == Material.DIAMOND_HELMET && enchant.getApplicableItem() == 3)
			return true;
		if (mat == Material.DIAMOND_CHESTPLATE && enchant.getApplicableItem() == 4)
			return true;
		if (mat == Material.DIAMOND_LEGGINGS && enchant.getApplicableItem() == 5)
			return true;
		if (mat == Material.DIAMOND_BOOTS && enchant.getApplicableItem() == 6)
			return true;
		if (mat == Material.DIAMOND_PICKAXE && enchant.getApplicableItem() == 7)
			return true;
		if (mat == Material.DIAMOND_SPADE && enchant.getApplicableItem() == 8)
			return true;
		if (mat == Material.DIAMOND_HOE && enchant.getApplicableItem() == 9)
			return true;
		if ((mat == Material.DIAMOND_PICKAXE || mat == Material.DIAMOND_SPADE) && enchant.getApplicableItem() == 78)
			return true;
		return false;
	}

	public static int romanToInteger(String s) {
		if (s.equalsIgnoreCase("I"))
			return 1;
		if (s.equalsIgnoreCase("II"))
			return 2;
		if (s.equalsIgnoreCase("III"))
			return 3;
		if (s.equalsIgnoreCase("IV"))
			return 4;
		if (s.equalsIgnoreCase("V"))
			return 5;
		if (s.equalsIgnoreCase("VI"))
			return 6;
		if (s.equalsIgnoreCase("VII"))
			return 7;
		if (s.equalsIgnoreCase("VIII"))
			return 8;
		if (s.equalsIgnoreCase("IX"))
			return 9;
		if (s.equalsIgnoreCase("X"))
			return 10;
		return 0;
	}

	public static ItemStack getLevel30() { // tier 1
		int r = random.nextInt(35) + 1;
		if (r <= 3) {
			return getCrystal(CustomEnchant.POISON, getLevel(CustomEnchant.POISON));
		} else if (r > 3 && r <= 14) {
			return getCrystal(CustomEnchant.GUILLOTINE, getLevel(CustomEnchant.GUILLOTINE));
		} else if (r > 14 && r <= 18) {
			return getCrystal(CustomEnchant.THEFT, getLevel(CustomEnchant.THEFT));
		} else if (r > 18 && r <= 23) {
			return getCrystal(CustomEnchant.VENOM, getLevel(CustomEnchant.VENOM));
		} else if (r > 23 && r <= 28) {
			return getCrystal(CustomEnchant.SATURATION, 1);
		} else if (r > 28 && r <= 36) {
			return getCrystal(CustomEnchant.NOCTURNAL, 1);
		} else if (r > 36 && r <= 43) {
			return getCrystal(CustomEnchant.TACTICALVEST, 1);
		} else if (r > 43 && r <= 50) {
			return getCrystal(CustomEnchant.DIVER, 1);
		} else if (r > 14 && r <= 18) {
			return getCrystal(CustomEnchant.DISCOVER, getLevel(CustomEnchant.DISCOVER));
		} else if (r > 18 && r <= 24) {
			return getCrystal(CustomEnchant.DISCOVER, getLevel(CustomEnchant.DISCOVER));
		} else if (r > 24 && r <= 32) {
			return getCrystal(CustomEnchant.CRIPPLE, getLevel(CustomEnchant.CRIPPLE));
		} else if (r > 32 && r <= 35) {
			return getLevel40();
		}
		return null;
	}

	public static ItemStack getLevel40() { // tier 2
		int r = random.nextInt(42) + 1;
		if (r <= 3) {
			return getCrystal(CustomEnchant.FREEZE, getLevel(CustomEnchant.FREEZE));
		} else if (r > 3 && r <= 4) {
			return getCrystal(CustomEnchant.BERSERKER, getLevel(CustomEnchant.BERSERKER));
		} else if (r > 4 && r <= 5) {
			return getCrystal(CustomEnchant.GRINDER, getLevel(CustomEnchant.GRINDER));
		} else if (r > 5 && r <= 6) {
			return getCrystal(CustomEnchant.ELIXIR, getLevel(CustomEnchant.ELIXIR));
		} else if (r > 6 && r <= 15) {
			return getCrystal(CustomEnchant.HUNGER, getLevel(CustomEnchant.HUNGER));
		} else if (r > 15 && r <= 19) {
			return getCrystal(CustomEnchant.VENOM, getLevel(CustomEnchant.VENOM));
		} else if (r > 19 && r <= 22) {
			return getCrystal(CustomEnchant.BUNNY, getLevel(CustomEnchant.BUNNY));
		} else if (r > 22 && r <= 24) {
			return getCrystal(CustomEnchant.EXTINGUISHER, 1);
		} else if (r > 24 && r <= 29) {
			return getCrystal(CustomEnchant.EXPLODE, getLevel(CustomEnchant.EXPLODE));
		} else if (r > 29 && r <= 34) {
			return getCrystal(CustomEnchant.SAVIOR, getLevel(CustomEnchant.SAVIOR));
		} else if (r > 34 && r <= 37) {
			return getCrystal(CustomEnchant.INVULNERABLE, getLevel(CustomEnchant.INVULNERABLE));
		} else if (r > 37 && r <= 39) {
			return getLevel50();
		} else if (r > 39 && r <= 43) {
			return getLevel30();
		}
		return null;
	}

	public static ItemStack getLevel50() { // tier 3
		int r = random.nextInt(41) + 1;
		if (r <= 2) {
			return getCrystal(CustomEnchant.LEECH, getLevel(CustomEnchant.LEECH));
		} else if (r > 2 && r <= 8) {
			return getCrystal(CustomEnchant.DIZZY, getLevel(CustomEnchant.DIZZY));
		} else if (r > 8 && r <= 11) {
			return getCrystal(CustomEnchant.EXECUTION, getLevel(CustomEnchant.EXECUTION));
		} else if (r > 11 && r <= 15) {
			return getCrystal(CustomEnchant.EXPLODE, getLevel(CustomEnchant.EXPLODE));
		} else if (r > 15 && r <= 17) {
			return getCrystal(CustomEnchant.SPEED, getLevel(CustomEnchant.SPEED));
		} else if (r > 17 && r <= 19) {
			return getCrystal(CustomEnchant.CLUTCH, getLevel(CustomEnchant.CLUTCH));
		} else if (r > 19 && r <= 23) {
			return getCrystal(CustomEnchant.CANNON, 1);
		} else if (r > 23 && r <= 25) {
			return getCrystal(CustomEnchant.SCAVENGER, getLevel(CustomEnchant.SCAVENGER));
		} else if (r > 25 && r <= 27) {
			return getCrystal(CustomEnchant.VACUUM, getLevel(CustomEnchant.VACUUM));
		} else if (r > 27 && r <= 31) {
			return getCrystal(CustomEnchant.JUGGERNAUT, getLevel(CustomEnchant.JUGGERNAUT));
		} else if (r > 31 && r <= 34) {
			return getCrystal(CustomEnchant.HEADSHOT, getLevel(CustomEnchant.HEADSHOT));
		} else if (r > 34 && r <= 36) {
			return getCrystal(CustomEnchant.FROSTBITE, getLevel(CustomEnchant.FROSTBITE));
		} else if (r > 36 && r <= 39) {
			return getLevel40();
		} else if (r > 39 && r <= 40) {
			return getLevel60();
		} else if (r > 40 && r <= 42) {
			return getCrystal(CustomEnchant.FATIGUE, getLevel(CustomEnchant.FATIGUE));
		}
		return null;
	}

	public static ItemStack getLevel60() {
		int r = random.nextInt(32) + 1;
		if (r <= 3) {
			return getCrystal(CustomEnchant.THOR, getLevel(CustomEnchant.THOR));
		} else if (r > 3 && r <= 5) {
			return getCrystal(CustomEnchant.KNOCKOUT, getLevel(CustomEnchant.KNOCKOUT));
		} else if (r > 5 && r <= 8) {
			return getCrystal(CustomEnchant.FROSTBITE, getLevel(CustomEnchant.FROSTBITE));
		} else if (r > 8 && r <= 10) {
			return getCrystal(CustomEnchant.CORRUPTION, getLevel(CustomEnchant.CORRUPTION));
		} else if (r > 10 && r <= 11) {
			return getCrystal(CustomEnchant.REGENERATE, getLevel(CustomEnchant.REGENERATE));
		} else if (r > 11 && r <= 13) {
			return getCrystal(CustomEnchant.CANNON, getLevel(CustomEnchant.CANNON));
		} else if (r > 13 && r <= 15) {
			return getCrystal(CustomEnchant.JELLYLEGS, getLevel(CustomEnchant.JELLYLEGS));
		} else if (r > 15 && r <= 17) {
			return getCrystal(CustomEnchant.DIZZY, getLevel(CustomEnchant.DIZZY));
		} else if (r > 17 && r <= 23) {
			return getCrystal(CustomEnchant.OBSIDIAN, getLevel(CustomEnchant.OBSIDIAN));
		} else if (r > 23 && r <= 26) {
			return getCrystal(CustomEnchant.REAPER, getLevel(CustomEnchant.REAPER));
		} else if (r > 26 && r <= 29) {
			return getLevel40();
		} else if (r > 29 && r <= 31) {
			return getLevel50();
		} else if (r > 31 && r <= 33) {
			return getCrystal(CustomEnchant.COMMANDER, getLevel(CustomEnchant.COMMANDER));
		} else if (r > 33 && r <= 35) {
			return getCrystal(CustomEnchant.STRAPPED, getLevel(CustomEnchant.STRAPPED));
		}
		return null;
	}

	private static int getRate() {
		return random.nextInt(99) + 1;
	}
}