package me.grizzly.enchants.listeners.legendary.armor;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;

import me.grizzly.enchants.EraEnchants;
import me.grizzly.enchants.listeners.EnchantListener;
import me.grizzly.enchants.utils.CustomEnchant;
import me.grizzly.enchants.utils.EnchantUtil;

public class Explode extends EnchantListener {

	public Explode(EraEnchants pl) {
		super(pl);
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		Player p = (Player) e.getEntity();

		if (p.getInventory().getChestplate() == null
				|| p.getInventory().getChestplate().getType() != Material.DIAMOND_CHESTPLATE) {
			return;
		}

		ItemStack chest = p.getInventory().getChestplate();

		if (EnchantUtil.hasEnchant(chest, CustomEnchant.EXPLODE)) {
			
			if (!Board.getInstance().getFactionAt(new FLocation(p)).isWarZone()) {
				p.sendMessage(ChatColor.YELLOW + "Since you were not in a warzone, your explode enchant did not activate.");
				return;
			}
			
			int chance = random.nextInt(99) + 1;

			if (EnchantUtil.isEnchantLevel(chest, CustomEnchant.EXPLODE, "I")) {
				if (chance <= 25) {
					p.getWorld().spawnEntity(p.getLocation(), EntityType.PRIMED_TNT);
				}
			} else if (EnchantUtil.isEnchantLevel(chest, CustomEnchant.EXPLODE, "II")) {
				if (chance <= 55) {
					p.getWorld().spawnEntity(p.getLocation(), EntityType.PRIMED_TNT);
				}
			}
		}
	}
}