package me.grizzly.enchants.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AbilityListener implements Listener {

	@EventHandler
	public void onAbilityActivate(AbilityActivateEvent e) {
		e.getPlayer().sendMessage(translate("&8[&a+&8] &b") + e.getEnchant().getName() + translate(" &7has been activated"));
	}
	private static String translate(String translate) {
		return ChatColor.translateAlternateColorCodes('&', translate);
	}
}