package me.grizzly.enchants.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.grizzly.enchants.utils.CustomEnchant;


public class AbilityActivateEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	private Player player;
	private CustomEnchant enchant;

	public AbilityActivateEvent(Player player, CustomEnchant enchant) {
		this.player = player;
		this.enchant = enchant;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public Player getPlayer() {
		return player;
	}
	
	public CustomEnchant getEnchant() {
		return this.enchant;
	}
}