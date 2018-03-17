package me.grizzly.enchants.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.earth2me.essentials.craftbukkit.SetExpFix;

import me.grizzly.enchants.EraEnchants;
import me.grizzly.enchants.utils.CrystalUtil;
import me.grizzly.enchants.utils.CustomEnchant;
import me.grizzly.enchants.utils.EnchantUtil;
import me.grizzly.enchants.utils.ItemBuilder;

public class InventoryListener implements Listener {

	private EraEnchants plugin;

	public InventoryListener(EraEnchants pl) {
		this.plugin = pl;
	}

	public static Inventory mainInventory() {
		Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.AQUA + "Enchantment Menu");

		inventory.setItem(11,
				new ItemBuilder(Material.DIAMOND_SWORD).setName(ChatColor.RED + "Enchanter")
						.addLoreLine(ChatColor.GRAY + "Trade in experience to receive a")
						.addLoreLine(translate("&7random enchantment from the enchanter")).toItemStack());
		inventory.setItem(15,
				new ItemBuilder(Material.BOOK).setName(ChatColor.BLUE + "Enchantment Info")
						.addLoreLine(ChatColor.GRAY + "View all the available enchantments")
						.addLoreLine(translate("&7you can receive from the enchanter")).toItemStack());

		return inventory;
	}

	@EventHandler
	public void mainEnchanter(InventoryClickEvent e) {
		Inventory topInventory = e.getView().getTopInventory();
		if (!topInventory.getTitle().equals(ChatColor.AQUA + "Enchantment Menu")) {
			return;
		}
		if (e.getCurrentItem() == null) {
			return;
		}
		if (e.getView().getTopInventory() == e.getClickedInventory()) {
			if (e.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
				e.getWhoClicked().openInventory(newInventory());
			} else if (e.getCurrentItem().getType() == Material.BOOK) {
				e.getWhoClicked().openInventory(enchantInventory());
			}
		}

		e.setCancelled(true);

	}

	public static Inventory upgradeInventory() {
		Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.RED + "Upgrader");

		inventory.setItem(3, new ItemStack(Material.STAINED_GLASS_PANE));
		inventory.setItem(4, new ItemStack(Material.STAINED_GLASS_PANE));
		inventory.setItem(5, new ItemStack(Material.STAINED_GLASS_PANE));

		inventory.setItem(12, new ItemStack(Material.STAINED_GLASS_PANE));
		inventory.setItem(14, new ItemStack(Material.STAINED_GLASS_PANE));

		inventory.setItem(21, new ItemStack(Material.STAINED_GLASS_PANE));
		inventory.setItem(22, new ItemStack(Material.STAINED_GLASS_PANE));
		inventory.setItem(23, new ItemStack(Material.STAINED_GLASS_PANE));

		return inventory;
	}

	public static Inventory newInventory() {
		Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.RED + "Enchanter");

		inventory.setItem(10,
				new ItemBuilder(Material.BOOK).setName(ChatColor.BLUE + "Heroic Enchantment")
						.addLoreLine(ChatColor.GRAY + "Trade in experience to receive")
						.addLoreLine(translate("&7a random heroic enchantment")).addLoreLine("")
						.addLoreLine(translate("&3&lPRICE &72500 experience")).toItemStack());
		inventory.setItem(12,
				new ItemBuilder(Material.BOOK).setName(ChatColor.RED + "Legendary Enchantment")
						.addLoreLine(ChatColor.GRAY + "Trade in experience to receive")
						.addLoreLine(translate("&7a random legendary enchantment")).addLoreLine("")
						.addLoreLine(translate("&c&lPRICE &75000 experience")).toItemStack());
		inventory.setItem(14,
				new ItemBuilder(Material.BOOK).setName(ChatColor.DARK_GREEN + "Paladin Enchantment")
						.addLoreLine(ChatColor.GRAY + "Trade in experience to receive")
						.addLoreLine(translate("&7a random paladin enchantment")).addLoreLine("")
						.addLoreLine(translate("&2&lPRICE &710000 experience")).toItemStack());
		inventory.setItem(16,
				new ItemBuilder(Material.BOOK).setName(ChatColor.GOLD + "King Enchantment")
						.addLoreLine(ChatColor.GRAY + "Trade in experience to receive")
						.addLoreLine(translate("&7a random king enchantment")).addLoreLine("")
						.addLoreLine(translate("&6&lPRICE &720000 experience")).toItemStack());

		return inventory;
	}

	public static Inventory enchantInventory() {

		Inventory inventory = Bukkit.createInventory(null, 9, ChatColor.RED + "Enchants");

		inventory.setItem(1,
				new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 11)
						.setName(ChatColor.BLUE + "Heroic Enchantments")
						.setLore(ChatColor.GRAY + "View all known heroic enchantments").toItemStack());
		inventory.setItem(3,
				new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 14)
						.setName(ChatColor.RED + "Legendary Enchantments")
						.setLore(ChatColor.GRAY + "View all known legendary enchantments").toItemStack());
		inventory.setItem(5,
				new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 13)
						.setName(ChatColor.DARK_GREEN + "Paladin Enchantments")
						.setLore(ChatColor.GRAY + "View all known paladin enchantments").toItemStack());
		inventory.setItem(7,
				new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 1)
						.setName(ChatColor.GOLD + "King Enchantments")
						.setLore(ChatColor.GRAY + "View all known king enchantments").toItemStack());

		return inventory;
	}

	@EventHandler
	public void upgraderInventory(InventoryClickEvent e) {
		Inventory topInventory = e.getView().getTopInventory();
		if (!topInventory.getTitle().equals(ChatColor.RED + "Upgrader")) {
			return;
		}
		if (e.getCurrentItem() == null) {
			return;
		}

		Player p = (Player) e.getWhoClicked();

		if (e.getClickedInventory() == topInventory) {
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.ANVIL) {
				if (topInventory.getItem(16).hasItemMeta()) {
					String amount = getUpgradeCost(topInventory.getItem(16).getItemMeta().getLore().get(0));
					int cost = Integer.valueOf(amount);
					if (SetExpFix.getTotalExperience(p) >= cost) {
						int enchantLevel = CrystalUtil
								.getEnchantLevel(topInventory.getItem(13).getItemMeta().getDisplayName());
						p.getInventory().removeItem(topInventory.getItem(13));
						SetExpFix.setTotalExperience(p, SetExpFix.getTotalExperience(p) - cost);
						p.getInventory()
								.addItem(new ItemBuilder(Material.EMERALD)
										.setName(CrystalUtil.getEnchantName(
												topInventory.getItem(13).getItemMeta().getDisplayName()) + " " + EnchantUtil.integerToRomanNumeral(enchantLevel + 1))
										.setLore(topInventory.getItem(13).getItemMeta().getLore()).toItemStack());
						p.closeInventory();
					} else {
						p.sendMessage(ChatColor.RED + "You do not have enough experience to upgrade!");
						topInventory.setItem(13, new ItemStack(Material.AIR));
						topInventory.setItem(16, new ItemStack(Material.AIR));
					}
				}
			}
			e.setCancelled(true);
		}

		if (e.getClickedInventory() == e.getView().getBottomInventory()) {
			if (topInventory.getItem(13) == null || topInventory.getItem(13).getType() == Material.AIR) {
				if (e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.EMERALD) {
					if (e.getClick() == ClickType.LEFT) {
						if (e.getCurrentItem().hasItemMeta()) {
							if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
								if (topInventory.getItem(13) == null
										|| topInventory.getItem(13).getType() == Material.AIR) {
									CustomEnchant enchant = EnchantUtil.parseEnchant(
											CrystalUtil.getEnchantName(CrystalUtil.getEnchant(e.getCurrentItem())));
									int enchantLevel = CrystalUtil
											.getEnchantLevel(e.getCurrentItem().getItemMeta().getDisplayName());
									if (enchantLevel != enchant.getMaxLevel()) {
										if (e.getCurrentItem().getAmount() == 1) {
											topInventory.setItem(13, e.getCurrentItem());
											topInventory.setItem(16, new ItemBuilder(Material.ANVIL)
													.setName(ChatColor.GREEN + "Upgrade Item" + ChatColor.RED + " -> " + CrystalUtil.getEnchantName(
															topInventory.getItem(13).getItemMeta().getDisplayName()) + " " + EnchantUtil.integerToRomanNumeral(enchantLevel + 1)
													).setLore("" + ChatColor.RED + ChatColor.BOLD + "COST "
															+ ChatColor.WHITE
															+ ((enchant.getTier()
																	+ (enchant.getMaxLevel() + enchantLevel)) * 8000)
															+ " experience")
													.toItemStack());
										} else {
											p.sendMessage(ChatColor.RED + "You can only upgrade one item at a time!");
										}
									} else {
										p.sendMessage(ChatColor.RED + "This enchant is already at its highest level!");
									}
								}
							}
						}
					}
				}
			} else {
				topInventory.setItem(16, new ItemStack(Material.AIR));
				topInventory.setItem(13, new ItemStack(Material.AIR));
			}
		}
		e.setCancelled(true);
	}

	@EventHandler
	public void openInventory(InventoryOpenEvent e) {
		Inventory topInventory = e.getView().getTopInventory();
		if (!topInventory.getTitle().equals(ChatColor.RED + "Enchanter")) {
			return;
		}
		Player p = (Player) e.getPlayer();

		new BukkitRunnable() {
			@Override
			public void run() {
				if (SetExpFix.getTotalExperience(p) >= 2500) {
					topInventory.getItem(10).setType(Material.ENCHANTED_BOOK);
				} else {
					topInventory.getItem(10).setType(Material.BOOK);
				}
				if (SetExpFix.getTotalExperience(p) >= 5000) {
					topInventory.getItem(12).setType(Material.ENCHANTED_BOOK);
				} else {
					topInventory.getItem(12).setType(Material.BOOK);
				}
				if (SetExpFix.getTotalExperience(p) >= 10000) {
					topInventory.getItem(14).setType(Material.ENCHANTED_BOOK);
				} else {
					topInventory.getItem(14).setType(Material.BOOK);
				}
				if (SetExpFix.getTotalExperience(p) >= 20000) {
					topInventory.getItem(16).setType(Material.ENCHANTED_BOOK);
				} else {
					topInventory.getItem(16).setType(Material.BOOK);
				}
			}
		}.runTaskLater(plugin, 1L);
	}

	@EventHandler
	public void getEnchant(InventoryClickEvent e) {
		Inventory topInventory = e.getView().getTopInventory();
		if (!topInventory.getTitle().equals(ChatColor.RED + "Enchanter")) {
			return;
		}

		Player p = (Player) e.getWhoClicked();

		if (e.getCurrentItem() == null) {
			return;
		}

		new BukkitRunnable() {
			@Override
			public void run() {
				if (SetExpFix.getTotalExperience(p) >= 2500) {
					topInventory.getItem(10).setType(Material.ENCHANTED_BOOK);

				} else {
					topInventory.getItem(10).setType(Material.BOOK);
				}
				if (SetExpFix.getTotalExperience(p) >= 5000) {
					topInventory.getItem(12).setType(Material.ENCHANTED_BOOK);
				} else {
					topInventory.getItem(12).setType(Material.BOOK);
				}
				if (SetExpFix.getTotalExperience(p) >= 10000) {
					topInventory.getItem(14).setType(Material.ENCHANTED_BOOK);
				} else {
					topInventory.getItem(14).setType(Material.BOOK);
				}
				if (SetExpFix.getTotalExperience(p) >= 20000) {
					topInventory.getItem(16).setType(Material.ENCHANTED_BOOK);
				} else {
					topInventory.getItem(16).setType(Material.BOOK);
				}
			}
		}.runTaskLater(plugin, 1L);

		if (e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.ENCHANTED_BOOK
				&& e.getInventory() == topInventory) {
			if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Heroic")) {
				if (SetExpFix.getTotalExperience(p) >= 2500) {
					p.getInventory().addItem(CrystalUtil.getLevel30());
					p.getWorld().playSound(p.getLocation(), Sound.LEVEL_UP, 2F, 2f);
					SetExpFix.setTotalExperience(p, SetExpFix.getTotalExperience(p) - 2500);
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Legendary")) {
				if (SetExpFix.getTotalExperience(p) >= 5000) {
					p.getInventory().addItem(CrystalUtil.getLevel40());
					p.getWorld().playSound(p.getLocation(), Sound.LEVEL_UP, 2F, 2f);
					SetExpFix.setTotalExperience(p, SetExpFix.getTotalExperience(p) - 5000);
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Paladin")) {
				if (SetExpFix.getTotalExperience(p) >= 10000) {
					p.getInventory().addItem(CrystalUtil.getLevel50());
					p.getWorld().playSound(p.getLocation(), Sound.LEVEL_UP, 2F, 2f);
					SetExpFix.setTotalExperience(p, SetExpFix.getTotalExperience(p) - 10000);
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("King")) {
				if (SetExpFix.getTotalExperience(p) >= 20000) {
					p.getInventory().addItem(CrystalUtil.getLevel60());
					p.getWorld().playSound(p.getLocation(), Sound.LEVEL_UP, 2F, 2f);
					SetExpFix.setTotalExperience(p, SetExpFix.getTotalExperience(p) - 20000);
				}
			}
			e.setCancelled(true);
		}

		if (e.getCurrentItem().getType() != Material.ENCHANTED_BOOK || e.getCurrentItem().getType() == Material.AIR) {
			e.setCancelled(true);
		}
	}
	private static String translate(String translate) {
		return ChatColor.translateAlternateColorCodes('&', translate);
	}

	public static String getUpgradeCost(String s) {
		String[] parts = s.split(" ");
		return ChatColor.stripColor(parts[1]);
	}
}