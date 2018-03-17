package me.grizzly.enchants.listeners.king.weapon;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.grizzly.enchants.EraEnchants;
import me.grizzly.enchants.listeners.AbilityActivateEvent;
import me.grizzly.enchants.listeners.EnchantListener;
import me.grizzly.enchants.utils.CustomEnchant;
import me.grizzly.enchants.utils.EnchantUtil;
import net.minecraft.server.v1_8_R3.EntityLightning;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityWeather;
import net.minecraft.server.v1_8_R3.WorldServer;

public class Thor extends EnchantListener {

	public Thor(EraEnchants pl) {
		super(pl);
	}

	@EventHandler (priority = EventPriority.HIGHEST)
	public void onEntityDamageEntity(EntityDamageByEntityEvent e) {
		if (e.isCancelled()) {
			return;
		}
		
		if(!(e.getEntity() instanceof Player)) return;
		
		if (e.getDamager() instanceof Player) {
			if (e.getEntity() instanceof Player) {
				Player p = (Player) e.getDamager();
				Player target = (Player) e.getEntity();

				if (EnchantUtil.hasEnchant(p.getItemInHand(), CustomEnchant.THOR)) {
					int chance = random.nextInt(99) + 1;
					if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.THOR, "I")) {
						if (chance <= 2) {
							if (target.getHealth() - e.getDamage() <= 0.0) {
								return;
							} else {
								lightningPacket(target);
								target.damage(4.0D);
							}
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.THOR));
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.THOR, "II")) {
						if (chance <= 3) {
							if (target.getHealth() - e.getDamage() <= 0.0) {
								return;
							} else {
								lightningPacket(target);
								target.damage(4.0D);
							}
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.THOR));
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.THOR, "III")) {
						if (chance <= 4) {
							if (target.getHealth() - e.getDamage() <= 0.0) {
								return;
							} else {
								lightningPacket(target);
								target.damage(4.0D);
							}
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.THOR));
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.THOR, "IV")) {
						if (chance <= 5) {
							if (target.getHealth() - e.getDamage() <= 0.0) {
								return;
							} else {
								lightningPacket(target);
								target.damage(4.0D);
							}
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.THOR));
						}
					} else if (EnchantUtil.isEnchantLevel(p.getItemInHand(), CustomEnchant.THOR, "V")) {
						if (chance <= 6) {
							if (target.getHealth() - e.getDamage() <= 0.0) {
								return;
							} else {
								lightningPacket(target);
								target.damage(4.0D);
							}
							Bukkit.getPluginManager().callEvent(new AbilityActivateEvent(p, CustomEnchant.THOR));
						}
					}
				}
			}
		}
	}

	public void lightningPacket(Player p) {
		Location location = p.getLocation();
		WorldServer worldServer = ((CraftWorld) location.getWorld()).getHandle();

		EntityLightning entityLightning = new EntityLightning(worldServer, location.getX(), location.getY(),
				location.getZ(), false);
		PacketPlayOutSpawnEntityWeather packet = new PacketPlayOutSpawnEntityWeather(entityLightning);

		for (Player target : getNearbyPlayers(p, 25, 25, 25)) {
			((CraftPlayer) target).getHandle().playerConnection.sendPacket(packet);
			target.playSound(target.getLocation(), Sound.AMBIENCE_THUNDER, 1.0F, 1.0F);
		}
	}
}