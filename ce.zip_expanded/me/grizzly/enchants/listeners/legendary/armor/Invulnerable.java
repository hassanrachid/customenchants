package me.grizzly.enchants.listeners.legendary.armor;

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

public class Invulnerable extends EnchantListener {

	public Invulnerable(EraEnchants pl) {
		super(pl);
	}

	@EventHandler (priority = EventPriority.HIGHEST)
	public void onEntityDamageEntity(EntityDamageByEntityEvent e) {
		if (e.isCancelled()) {
			return;
		}
		if (e.getDamager() instanceof Player) {
			if (e.getEntity() instanceof Player) {
				Player p = (Player) e.getEntity();
				Player target = (Player) e.getDamager();

				if (p.getInventory().getLeggings() == null
						|| p.getInventory().getLeggings().getType() != Material.DIAMOND_LEGGINGS) {
					return;
				}

				ItemStack item = p.getInventory().getLeggings();

				if (EnchantUtil.hasEnchant(item, CustomEnchant.INVULNERABLE)) {
					int chance = random.nextInt(99) + 1;
					if (EnchantUtil.isEnchantLevel(item, CustomEnchant.INVULNERABLE, "I")) {
						if (chance <= 2) {
							target.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, (20 * 4), 0));
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.INVULNERABLE));
						}
					} else if (EnchantUtil.isEnchantLevel(item, CustomEnchant.INVULNERABLE, "II")) {
						if (chance <= 4) {
							target.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, (20 * 4), 0));
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.INVULNERABLE));
						}
					} else if (EnchantUtil.isEnchantLevel(item, CustomEnchant.INVULNERABLE, "III")) {
						if (chance <= 5) {
							target.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, (20 * 4), 0));
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.INVULNERABLE));
						}
					}
				}
			}
		}
	}
}