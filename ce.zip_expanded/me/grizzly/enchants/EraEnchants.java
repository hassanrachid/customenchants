package me.grizzly.enchants;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

import me.grizzly.enchants.commands.EnchantCommand;
import me.grizzly.enchants.commands.EnchanterCommand;
import me.grizzly.enchants.commands.HelpCommand;
import me.grizzly.enchants.commands.ItemCommand;
import me.grizzly.enchants.commands.RecyclerCommand;
import me.grizzly.enchants.commands.ShardCommand;
import me.grizzly.enchants.commands.UnknownCrystal;
import me.grizzly.enchants.commands.UpgradeCommand;
import me.grizzly.enchants.listeners.EnchantListener;

public class EraEnchants extends JavaPlugin {

	private EnchantListener listenerManager = new EnchantListener(this);
	private ProtocolManager protocolManager;
	private List<Material> blockTypes = new ArrayList<Material>();

	public void onEnable() {
		listenerManager.registerAll();
		registerCommands();

		protocolManager = ProtocolLibrary.getProtocolManager();
	}

	public void registerCommands() {
		getCommand("addenchant").setExecutor(new EnchantCommand());
		getCommand("giveitem").setExecutor(new ItemCommand());
		getCommand("enchanter").setExecutor(new EnchanterCommand());
		getCommand("giveunknown").setExecutor(new UnknownCrystal());
		getCommand("ce").setExecutor(new HelpCommand());
		getCommand("upgrader").setExecutor(new UpgradeCommand());
		getCommand("giveshard").setExecutor(new ShardCommand());
		getCommand("recycler").setExecutor(new RecyclerCommand());
	}

	public ProtocolManager getProtocolManager() {
		return this.protocolManager;
	}

	public void addBlocks() {
		for (String s : getConfig().getStringList("Blocks")) {
			this.blockTypes.add(Material.getMaterial(s));
		}
	}

	public List<Material> getBlockList() {
		return this.blockTypes;
	}
}