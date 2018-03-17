package me.grizzly.enchants.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class HelpListener implements Listener {

	@EventHandler
	public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
		String[] args = e.getMessage().toLowerCase().split(" ");
		if (args[0].equalsIgnoreCase("/help")) {
			if (args.length <= 1) {
				e.setCancelled(true);
				for (String s : firstPage())
					e.getPlayer().sendMessage(s);
			} else {
				if (args[1].equals("1")) {
					e.setCancelled(true);
					for (String s : firstPage())
						e.getPlayer().sendMessage(s);
				}
				if (args[1].equals("2")) {
					e.setCancelled(true);
					for (String s : secondPage())
						e.getPlayer().sendMessage(s);
				}
				if (args[1].equals("3")) {
					e.setCancelled(true);
					for (String s : thirdPage())
						e.getPlayer().sendMessage(s);
				}
			}
		}
	}

	public List<String> firstPage() {
		List<String> list = new ArrayList<String>();
		list.add(translate("&7&m-------------&e Help Desk &7[<] &a1 / 2 &7[>] &7&m-------------"));
		list.add(translate(
				"&aFor any outstanding support you may need, join our discord and ask for a staff member. &chttp://discord.gg/gFCbYe9"));
		list.add(translate(
				"&e1. &6Basic World Information: &a(Vanilla) 10,000 blocks in each direction &c(Nether) 2,000 blocks in each direction &d(End) 4,000 blocks in each direction"));
		list.add(translate(
				"&e2. &6Ore are increased in all biomes, &7and highly increased in custom biomes you will find around the world."));
		list.add(translate(
				"&e3. &6Money Making Strategies: &7Mining and fishing have chances to reward you with discovery cases which contain experience, money, and spawners."));
		list.add(translate(
				"&e4. &6Farming prices &7have been heavily increased to encourage players to farm and build farms."));
		list.add(translate(
				"&e5. &6How-To Custom Enchants: &7Type &6/ce &7to see all available enchants we have on the server."));
		list.add(translate(
				"&e6. &6How-To Custom Enchants: &7To get custom enchantments, you first must find empty magical crystals and place them in the empty slot inside &6/enchanter."));
		list.add(translate(
				"&e7. &6Cannon Limitations: &7Cannons have been limited to only be able to shoot 50-75 sand at once."));
		list.add(translate(
				"&e8. &6Chunk Buffer Rules: &7You may only claim 5 chunks out from the first wall of your base."));
		list.add(translate(
				"&e9. &6Faction Rules: &7200 power per faction, 20 power per player, and 20 members per faction."));
		list.add(translate("&6Type &a/help 2 &6to read the next page."));

		return list;
	}

	public List<String> secondPage() {
		List<String> list = new ArrayList<String>();
		list.add(translate("&7&m-------------&e Help Desk &7[<] &a2 / 2 &7[>] &7&m-------------"));
		list.add(translate(
				"&e10. &6Creating a Faction: &7After creating a faction, type &6/f crystal &7to place down your faction core and to enable all spawners in your claims."));
		list.add(translate(
				"&e11. &6Placing a crystal: &7Your core must be placed between y: 100 and y: 200 to ensure it can be raided by anyone."));
		list.add(translate(
				"&e12. &6To raid an enemy, &7you must target enemy faction core and blow it up. Once destroyed, enemy faction claims will be accessible to anyone."));
		list.add(translate(
				"&e13. &6If you are being raided, &7you can not mine any spawners and a TNT timer will be activated (See your scoreboard for time remaining)"));
		list.add(translate(
				"&e14. &6Custom Faction Commands: &7To see all our custom faction commands, type &6/f help &7to read about our custom factions plugin!"));
		list.add(translate(
				"&e15. &6Events: &7We run daily/hourly events of King of the Hill, Last Man Standing, and Dropzones. Participate to receive ultimate cases/rewards!"));
		list.add(translate(
				"&e16. &6Events: &7We run weekly raiding/conquest events. Stay tuned for each event on our forums at &6www.erapvp.com"));
		list.add(translate(
				"&e17. &6To craft TNT, &7all players have access to &6/gp &7command that turns all gunpowder in your inventory into TNT!"));
		list.add(translate(
				"&e18. &6Using /f stealth &7near enemy bases will not deactivate enemy player's fly. This can be really useful when trying to raid."));
		list.add(translate("&7Type &a/help 3 &7to know how to get points &6/f top..."));
		return list;
	}

	public List<String> thirdPage() {
		List<String> list = new ArrayList<String>();
		
		list.add(translate("&7&m-------------&e Help Desk &7[<] &a2 / 2 &7[>] &7&m-------------"));
		list.add(translate("&cYou can get faction points in several ways..."));
		list.add(translate("&e1. &6Placing down spawners and valuables &7in your faction claim will award you points that is &710% of your total weatlh."));
		list.add(translate("&e2. &6Raiding enemy factions &7will award you 50% of the raided faction's points."));
		list.add(translate("&e3. &6Participating in global faction events &7will award you faction points based on event."));
		list.add(translate("&e4. &6Upgrading your faction upgrades &7will award you faction points based on upgrade price."));
		list.add(translate("&e5. &7Your faction will be awarded 20% worth of all your faction members &6mcMMO levels."));
		
		return list;
	}
	private String translate(String translate) {
		return ChatColor.translateAlternateColorCodes('&', translate);
	}
}
