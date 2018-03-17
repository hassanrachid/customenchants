package me.grizzly.enchants.listeners;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.util.Vector;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;

import me.grizzly.enchants.EraEnchants;
import me.grizzly.enchants.commands.HelpCommand;
import me.grizzly.enchants.commands.ShardCommand;
import me.grizzly.enchants.commands.UnknownCrystal;
import me.grizzly.enchants.listeners.hero.armor.Diver;
import me.grizzly.enchants.listeners.hero.armor.Nocturnal;
import me.grizzly.enchants.listeners.hero.armor.Saturation;
import me.grizzly.enchants.listeners.hero.armor.TacticalVest;
import me.grizzly.enchants.listeners.hero.tool.Discover;
import me.grizzly.enchants.listeners.hero.weapon.Cripple;
import me.grizzly.enchants.listeners.hero.weapon.Guillotine;
import me.grizzly.enchants.listeners.hero.weapon.Poison;
import me.grizzly.enchants.listeners.hero.weapon.Theft;
import me.grizzly.enchants.listeners.king.armor.JellyLegs;
import me.grizzly.enchants.listeners.king.armor.Regenerate;
import me.grizzly.enchants.listeners.king.armor.Strapped;
import me.grizzly.enchants.listeners.king.bow.Corruption;
import me.grizzly.enchants.listeners.king.bow.Explosion;
import me.grizzly.enchants.listeners.king.bow.Frostbite;
import me.grizzly.enchants.listeners.king.tool.Obsidian;
import me.grizzly.enchants.listeners.king.weapon.Commander;
import me.grizzly.enchants.listeners.king.weapon.Knockout;
import me.grizzly.enchants.listeners.king.weapon.Thor;
import me.grizzly.enchants.listeners.legendary.armor.Bunny;
import me.grizzly.enchants.listeners.legendary.armor.Explode;
import me.grizzly.enchants.listeners.legendary.armor.Extinguisher;
import me.grizzly.enchants.listeners.legendary.armor.Invulnerable;
import me.grizzly.enchants.listeners.legendary.armor.Savior;
import me.grizzly.enchants.listeners.legendary.bow.Venom;
import me.grizzly.enchants.listeners.legendary.weapon.Berserker;
import me.grizzly.enchants.listeners.legendary.weapon.Elixir;
import me.grizzly.enchants.listeners.legendary.weapon.Freeze;
import me.grizzly.enchants.listeners.legendary.weapon.Grinder;
import me.grizzly.enchants.listeners.legendary.weapon.Hunger;
import me.grizzly.enchants.listeners.paladin.armor.Clutch;
import me.grizzly.enchants.listeners.paladin.armor.Fatigue;
import me.grizzly.enchants.listeners.paladin.armor.Reaper;
import me.grizzly.enchants.listeners.paladin.armor.Speed;
import me.grizzly.enchants.listeners.paladin.bow.Cannon;
import me.grizzly.enchants.listeners.paladin.bow.Headshot;
import me.grizzly.enchants.listeners.paladin.tool.Scavenger;
import me.grizzly.enchants.listeners.paladin.tool.Vacuum;
import me.grizzly.enchants.listeners.paladin.weapon.Dizzy;
import me.grizzly.enchants.listeners.paladin.weapon.Execution;
import me.grizzly.enchants.listeners.paladin.weapon.Leech;

public class EnchantListener implements Listener {

	// TODO Swords 13/13
	// TODO Bows 5/6
	// TODO Armor 12/14
	// TODO Tools 7/7

	public EraEnchants plugin;
	public Random random = new Random();

	public EnchantListener(EraEnchants pl) {
		this.plugin = pl;
	}

	public void registerAll() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new Discover(plugin), plugin);
		pm.registerEvents(new Poison(plugin), plugin);
		pm.registerEvents(new Obsidian(plugin), plugin);
		pm.registerEvents(new Thor(plugin), plugin);
		pm.registerEvents(new Berserker(plugin), plugin);
		pm.registerEvents(new Freeze(plugin), plugin);
		pm.registerEvents(new Dizzy(plugin), plugin);
		pm.registerEvents(new Speed(plugin), plugin);
		pm.registerEvents(new Regenerate(plugin), plugin);
		pm.registerEvents(new Nocturnal(plugin), plugin);
		pm.registerEvents(new Diver(plugin), plugin);
		pm.registerEvents(new Venom(plugin), plugin);
		pm.registerEvents(new Explosion(plugin), plugin);
		pm.registerEvents(new Cannon(plugin), plugin);
		pm.registerEvents(new Frostbite(plugin), plugin);
		pm.registerEvents(new Guillotine(plugin), plugin);
		pm.registerEvents(new Leech(plugin), plugin);
		pm.registerEvents(new Theft(plugin), plugin);
		pm.registerEvents(new Grinder(plugin), plugin);
		pm.registerEvents(new Execution(plugin), plugin);
		pm.registerEvents(new Hunger(plugin), plugin);
		pm.registerEvents(new Explode(plugin), plugin);
		pm.registerEvents(new TacticalVest(plugin), plugin);
		pm.registerEvents(new JellyLegs(plugin), plugin);
		pm.registerEvents(new Clutch(plugin), plugin);
		pm.registerEvents(new Bunny(plugin), plugin);
		pm.registerEvents(new Scavenger(plugin), plugin);
		pm.registerEvents(new Saturation(plugin), plugin);
		pm.registerEvents(new Corruption(plugin), plugin);
		pm.registerEvents(new Elixir(plugin), plugin);
		pm.registerEvents(new Savior(plugin), plugin);
		pm.registerEvents(new Reaper(plugin), plugin);
		pm.registerEvents(new Invulnerable(plugin), plugin);
		pm.registerEvents(new Cripple(plugin), plugin);
		pm.registerEvents(new Headshot(plugin), plugin);
		pm.registerEvents(new Vacuum(plugin), plugin);
		pm.registerEvents(new Knockout(plugin), plugin);
		pm.registerEvents(new Strapped(plugin), plugin);
		pm.registerEvents(new Extinguisher(plugin), plugin);
		pm.registerEvents(new Commander(plugin), plugin);
		pm.registerEvents(new InventoryListener(plugin), plugin);
		pm.registerEvents(new CrystalListener(), plugin);
		pm.registerEvents(new TinkererListener(plugin), plugin);
		pm.registerEvents(new ShardListener(), plugin);
		pm.registerEvents(new AbilityListener(), plugin);
		pm.registerEvents(new UnknownCrystal(), plugin);
		pm.registerEvents(new HelpCommand(), plugin);
		pm.registerEvents(new HelpListener(), plugin);
		pm.registerEvents(new Fatigue(plugin), plugin);
		pm.registerEvents(new ShardCommand(), plugin);
	}

	private double randDouble(double bound1, double bound2) {
		double min = Math.min(bound1, bound2);
		double max = Math.max(bound1, bound2);
		return min + (Math.random() * (max - min));
	}

	public double getRandom() {
		return Math.round(randDouble(0, 100) * 100.0) / 100.0;
	}

	public List<Player> getNearbyPlayers(Player p, int x, int y, int z) {
		List<Player> players = new ArrayList<Player>();
		for (Entity ent : p.getNearbyEntities(x, y, z)) {
			if (ent.getType() == EntityType.PLAYER) {
				players.add((Player) ent);
			}
		}
		return players;
	}

	public void fakeExplosion(Player p) {
		PacketContainer fakeExplosion = new PacketContainer(PacketType.Play.Server.EXPLOSION);
		fakeExplosion.getDoubles().write(0, p.getLocation().getX()).write(1, p.getLocation().getY()).write(2,
				p.getLocation().getZ());
		fakeExplosion.getFloat().write(0, 3.0F);

		try {
			for (Player player : getNearbyPlayers(p, 25, 25, 25))
				plugin.getProtocolManager().sendServerPacket(player, fakeExplosion);
		} catch (InvocationTargetException e) {
			throw new RuntimeException("Cannot send packet " + fakeExplosion, e);
		}
	}

	public Vector calculateVector(Player player) {
		double radians = Math.toRadians(player.getLocation().getYaw() * 2);
		double x = -Math.sin(radians) * 6;
		double y = 6 / 6.0D;
		double z = Math.cos(radians) * 6;
		return new Vector(x, y, z);
	}
}