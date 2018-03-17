package me.grizzly.enchants.listeners.paladin.armor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.grizzly.enchants.EraEnchants;
import me.grizzly.enchants.listeners.AbilityActivateEvent;
import me.grizzly.enchants.listeners.EnchantListener;
import me.grizzly.enchants.utils.CustomEnchant;
import me.grizzly.enchants.utils.EnchantUtil;

public class Reaper extends EnchantListener {

	public Reaper(EraEnchants pl) {
		super(pl);
	}

	@EventHandler (priority = EventPriority.HIGHEST)
	public void onEntityDamageEvent(EntityDamageByEntityEvent e) {
		if (e.isCancelled()) {
			return;
		}
		if (e.getDamager() instanceof Player) {
			if (e.getEntity() instanceof Player) {
				Player p = (Player) e.getEntity();
				Player target = (Player) e.getDamager();

				if (p.getInventory().getChestplate() == null
						|| p.getInventory().getChestplate().getType() != Material.DIAMOND_CHESTPLATE) {
					return;
				}

				ItemStack item = p.getInventory().getChestplate();

				if (EnchantUtil.hasEnchant(item, CustomEnchant.REAPER)) {
					int chance = random.nextInt(99) + 1;
					if (EnchantUtil.isEnchantLevel(item, CustomEnchant.REAPER, "I")) {
						if (chance <= 1) {
							target.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, (20 * 3), 0));
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.REAPER));
						}
					} else if (EnchantUtil.isEnchantLevel(item, CustomEnchant.REAPER, "II")) {
						if (chance <= 2) {
							target.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, (20 * 3), 0));
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.REAPER));
						}
					} else if (EnchantUtil.isEnchantLevel(item, CustomEnchant.REAPER, "III")) {
						if (chance <= 3) {
							target.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, (20 * 3), 0));
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.REAPER));
						}
					} else if (EnchantUtil.isEnchantLevel(item, CustomEnchant.REAPER, "IV")) {
						if (chance <= 4) {
							target.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, (20 * 3), 0));
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.REAPER));
						}
					}
				}
			}
		}
	}
}