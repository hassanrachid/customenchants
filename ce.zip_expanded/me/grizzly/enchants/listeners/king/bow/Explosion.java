package me.grizzly.enchants.listeners.king.bow;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.grizzly.enchants.EraEnchants;
import me.grizzly.enchants.listeners.AbilityActivateEvent;
import me.grizzly.enchants.listeners.EnchantListener;
import me.grizzly.enchants.utils.CustomEnchant;
import me.grizzly.enchants.utils.EnchantUtil;

public class Explosion extends EnchantListener {

	public Explosion(EraEnchants pl) {
		super(pl);
	}

	@EventHandler (priority = EventPriority.HIGHEST)
	public void onEntityDamageEntity(EntityDamageByEntityEvent e) {
		if (e.isCancelled()) {
			return;
		}
		if (e.getEntity() instanceof Player) {
			if (e.getDamager() instanceof Arrow) {
				Player target = (Player) e.getEntity();
				Arrow arrow = (Arrow) e.getDamager();
				if (arrow.getShooter() instanceof Player) {
					Player p = (Player) arrow.getShooter();

					if (p.getItemInHand().getType() == Material.BOW) {
						if (EnchantUtil.hasEnchant(p.getItemInHand(), CustomEnchant.EXPLOSION)) {
							int chance = random.nextInt(99) + 1;
							// Was 8
							if (chance <= 8) {
								target.damage(4.0D);
							//	fakeExplosion(target);
								Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.EXPLOSION));
							}
						}
					}
				}
			}
		}
	}
}