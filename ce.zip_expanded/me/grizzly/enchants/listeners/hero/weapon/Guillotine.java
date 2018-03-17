package me.grizzly.enchants.listeners.hero.weapon;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import me.grizzly.enchants.EraEnchants;
import me.grizzly.enchants.listeners.EnchantListener;
import me.grizzly.enchants.utils.CustomEnchant;
import me.grizzly.enchants.utils.EnchantUtil;

public class Guillotine extends EnchantListener {

	public Guillotine(EraEnchants pl) {
		super(pl);
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		if (e.getEntity() instanceof Player) {
			if (e.getEntity().getKiller() instanceof Player) {
				Player p = e.getEntity().getKiller();
				Player target = (Player) e.getEntity();

				if (EnchantUtil.hasEnchant(p.getItemInHand(), CustomEnchant.GUILLOTINE)) {
					int chance = random.nextInt(99) + 1;
					if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.GUILLOTINE, "I")) {
						if (chance <= 30)
							e.getDrops().add(getSkull(target));
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.GUILLOTINE, "II")) {
						if (chance <= 65)
							e.getDrops().add(getSkull(target));
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.GUILLOTINE, "III")) {
							e.getDrops().add(getSkull(target));
					}
				}
			}
		}
	}

	public ItemStack getSkull(Player p) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);
		meta.setOwner(p.getName());
		skull.setItemMeta(meta);
		return skull;
	}
}