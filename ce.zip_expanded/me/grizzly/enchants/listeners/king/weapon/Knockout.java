package me.grizzly.enchants.listeners.king.weapon;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import me.grizzly.enchants.EraEnchants;
import me.grizzly.enchants.listeners.AbilityActivateEvent;
import me.grizzly.enchants.listeners.EnchantListener;
import me.grizzly.enchants.utils.CustomEnchant;
import me.grizzly.enchants.utils.EnchantUtil;
import net.md_5.bungee.api.ChatColor;

public class Knockout extends EnchantListener {

	public Knockout(EraEnchants pl) {
		super(pl);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDamageEntity(EntityDamageByEntityEvent e) {
		if (e.isCancelled()) {
			return;
		}
		if (e.getDamager() instanceof Player) {
			if (e.getEntity() instanceof Player) {
				Player p = (Player) e.getDamager();
				Player target = (Player) e.getEntity();

				if (EnchantUtil.hasEnchant(p.getItemInHand(), CustomEnchant.KNOCKOUT)) {
					int chance = random.nextInt(99) + 1;
					if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.KNOCKOUT, "I")) {
						if (chance <= 0.2) {
							removeArmor(target);
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.KNOCKOUT, "II")) {
						if (chance <= 0.4) {
							removeArmor(target);
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.KNOCKOUT, "III")) {
						if (chance <= 0.6) {
							removeArmor(target);
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.KNOCKOUT, "IV")) {
						if (chance <= 0.8) {
							removeArmor(target);
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.KNOCKOUT, "V")) {
						if (chance <= 1.0) {
							removeArmor(target);
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.KNOCKOUT, "VI")) {
						if (chance <= 1.3) {
							removeArmor(target);
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.KNOCKOUT, "VII")) {
						if (chance <= 1.6) {
							removeArmor(target);
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.KNOCKOUT, "VIII")) {
						if (chance <= 1.9) {
							removeArmor(target);
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.KNOCKOUT, "IX")) {
						if (chance <= 2.2) {
							removeArmor(target);
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.KNOCKOUT, "X")) {
						if (chance <= 100) {
							removeArmor(target);
						}
					}

				}

			}
		}
	}

	public void removeArmor(Player p) {
		if (p.getInventory().getChestplate() != null) {
			if (p.getInventory().getChestplate().hasItemMeta() && p.getInventory().getChestplate().getItemMeta().hasLore()) {
				if (p.getInventory().getChestplate().getItemMeta().getLore().contains(ChatColor.GOLD + "Strapped I")) {
					p.sendMessage(ChatColor.RED + "Your chestplate was saved from the enemy's knock out!");
				} else {
					if (p.getInventory().firstEmpty() == -1) {
						p.getWorld().dropItemNaturally(p.getLocation(), p.getInventory().getChestplate());
					} else {
						p.getInventory().addItem(p.getInventory().getChestplate());
					}
					p.getInventory().setChestplate(new ItemStack(Material.AIR));
					Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.KNOCKOUT));
				}
			} else {
				if (p.getInventory().firstEmpty() == -1) {
					p.getWorld().dropItemNaturally(p.getLocation(), p.getInventory().getChestplate());
				} else {
					p.getInventory().addItem(p.getInventory().getChestplate());
				}
				p.getInventory().setChestplate(new ItemStack(Material.AIR));
				Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.KNOCKOUT));
			}
		}
	}

}