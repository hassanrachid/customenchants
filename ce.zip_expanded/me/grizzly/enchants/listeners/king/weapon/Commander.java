package me.grizzly.enchants.listeners.king.weapon;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;

import me.grizzly.enchants.EraEnchants;
import me.grizzly.enchants.listeners.AbilityActivateEvent;
import me.grizzly.enchants.listeners.EnchantListener;
import me.grizzly.enchants.utils.CustomEnchant;
import me.grizzly.enchants.utils.EnchantUtil;
import net.md_5.bungee.api.ChatColor;
public class Commander extends EnchantListener {

	public Commander(EraEnchants pl) {
		super(pl);
	}

	@EventHandler (priority = EventPriority.HIGHEST)
	public void onEntityDamageEntity(EntityDamageByEntityEvent e) {
		if (e.isCancelled()) {
			return;
		}
		if (e.getDamager() instanceof Player) {
			if (e.getEntity() instanceof Player) {
				Player p = (Player) e.getDamager();

				if (EnchantUtil.hasEnchant(p.getItemInHand(), CustomEnchant.COMMANDER)) {
					int chance = random.nextInt(99) + 1;
					if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.COMMANDER, "I")) {
						if (chance <= 2) {
							getNearby(p);
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.COMMANDER));
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.COMMANDER, "II")) {
						if (chance <= 3) {
							getNearby(p);
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.COMMANDER));
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.COMMANDER, "III")) {
						if (chance <= 4) {
							getNearby(p);
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.COMMANDER));
						}
					}
				}
			}
		}
	}
	
	public void getNearby(Player player) {
		Faction playerFac = FPlayers.getInstance().getByPlayer(player).getFaction();
		for (Entity players : player.getNearbyEntities(10, 10, 10)) {
			if (players instanceof Player) {
				Faction fac = FPlayers.getInstance().getByPlayer((Player) players).getFaction();
				if (fac == playerFac) {
					if (((Player) players).getHealth() + 3 > 20) {
						((Player) players).setHealth(20);
					} else {
						((Player) players).setHealth(((Player) players).getHealth() + 3);
					}
					players.sendMessage(ChatColor.GREEN + "You have been healed by a nearby faction member!");
				}
			}
		}	
	}
}