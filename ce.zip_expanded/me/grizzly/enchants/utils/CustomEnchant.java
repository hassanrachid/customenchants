package me.grizzly.enchants.utils;

public enum CustomEnchant {

	// TODO Swords
	POISON("Poison", 1, 2, "swords", "This enchant has a chance to apply poison on victim"), // Poisons enemy
	FREEZE("Freeze", 2, 3, "swords", "This enchant has a chance to freeze victim"), // Gives enemy slowness based on level
	BERSERKER("Berserker", 2, 5, "swords", "This enchant has a chance to deal double damage on victim"), // Double damage with sword
	GUILLOTINE("Guillotine", 1, 3, "swords", "This enchant has a chance to drop victim's head"), // Drop head on enemy death
	LEECH("Leech", 3, 3, "swords", "This enchant has a chance steal hearts from the victim"), // Take hearts away
	THOR("Thor", 4, 5, "swords", "This enchant has a chance to strike lightning on victim"), // Strikes lightning that damages enemy
	GRINDER("Grinder", 2, 4, "swords", "This enchant gives you more experience from killing mobs"), // More xp for mobs
	DIZZY("Dizzy", 3, 2, "swords", "This enchant has a chance to apply nausea to victim"), // Gives enemy naseua
	ELIXIR("Elixir", 2, 2, "swords", "This enchant has a chance to reduce potion effects of victim"), // Reduces enemies potion effect duration
	THEFT("Theft", 1, 3, "swords", "This enchant has a chance to steal experience from victim"), // Chance to take xp on hit
	KNOCKOUT("Knockout", 4, 10, "swords", "This enchant has a chance to remove the chestplate off the victim"), // Chance to remove armor piece
	EXECUTION("Execution", 3, 1, "swords", "This enchant has a chance to give you strength if victim is low"), // Gives strength when enemy is on 2 hearts or lower
	HUNGER("Hunger", 2, 7, "swords", "This enchant has a chance to steal hunger from victim"), // Takes hunger away from player
	CRIPPLE("Cripple", 1, 4, "swords", "This enchant has a chance to apply weakness on victim"), // chance to apply weakness on enemy
	COMMANDER("Commander", 4, 3, "swords", "This enchant has a chance to heal nearby faction members when attacking an enemy"),

	// TODO Bows
	EXPLOSION("Explosion", 4, 1, "bows", "This enchant has a chance to explode victim"), // Explosion when hitting enemy
	CANNON("Cannon", 3, 6, "bows", "This enchant has a chance to shoot victim into the air"), // Shoots enemy in air
	VENOM("Venom", 2, 2, "bows", "This enchant has a chance to poison victim"), // Poisons enemy
	FROSTBITE("Frostbite", 4, 3, "bows", "This enchant has a chance to freeze victim"), // Freezes enemy
	HEADSHOT("Headshot", 3, 3, "bows", "This enchant has a chance to deal double damage on victim"), // chance to apply double damage
	CORRUPTION("Corruption", 4, 10, "bows", "This enchant has a chance to remove potion effects from victim"), // Removes all potion effects

	// TODO Armor
	SPEED("Speed", 3, 3, "boots", "This enchant applies constant Speed effect based on level"), // Constant speed
	JUGGERNAUT("Juggernaut", 2, 6, "chestplates", "This enchant reduces the amount of damage you take"), // Less hearts damage
	SATURATION("Saturation", 1, 1, "helmets", "This enchant prevents you from losing hunger"), // No hunger loss
	REGENERATE("Regenerate", 4, 1, "chestplates", "This enchant applies permanent Regeneration effect"), // Gives permanent regen 1
	NOCTURNAL("Nocturnal", 1, 1, "helmets", "This enchant applies permanent Night Vision effect"), // Gives permanent night vision
	FATIGUE("Fatigue", 3, 3, "boots", "This enchant heals you but also takes experience away from you"),
	BUNNY("Bunny", 2, 2, "boots", "This enchant applies permanent Jump Boost effect"), // Permanent jump boost
	EXTINGUISHER("Extinguisher", 2, 1, "leggings", "This enchant applies permanant Fire Resistance effect"), // Permanent fire resistance
	CLUTCH("Clutch", 3, 2, "leggings", "This enchant applies Speed 3 or 4 effect when you are low"), // Gives speed 3/4 when 1 heart
	EXPLODE("Explode", 2, 2, "chestplates", "This enchant has a chance to make you explode when you die"), // Explode upon death
	TACTICALVEST("Tactical Vest", 1, 1, "chestplates", "This enchant does not allow you to take explosion damage"), // No tnt damage
	DIVER("Diver", 1, 1, "helmets", "This enchant applies permanent Water Breathing effect"), // Permanent water breathing
	JELLYLEGS("Jelly Legs", 4, 1, "leggings", "This enchant does not allow you to take fall damage"), // Jelly legs
	SAVIOR("Savior", 2, 3, "helmets", "This enchant reduces damage taken by half"), // chance to reduce damage by half
	REAPER("Reaper", 4, 4, "chestplates", "This enchant has a chance to apply wither effect on victim"), // chance to apply wither on enemy
	INVULNERABLE("Invulnerable", 2, 3, "leggings", "This enchant has a chance to apply Resistance effect"), // chance to apply resistance on player
	STRAPPED("Strapped", 4, 1, "chestplate", "This enchant protects your chestplate from being taken off"),

	// TODO Tools
	DISCOVER("Discover", 1, 2, "pickaxes", "This enchant gives you more experience while mining"), // More xp
	SCAVENGER("Scavenger", 3, 2, "pickaxes and shovels", "This enchant gives you extra ore drops from mining"), // 2x/3x drops
	OBSIDIAN("Obsidian", 4, 1, "pickaxes", "This enchant allows you to instant break obsidian"), // Instabreak obsidian
	VACUUM("Vacuum", 3, 1, "pickaxes and shovels", "This enchant automatically gives you broken blocks"); // Adds drops from blocks to inventory

	private String name;
	private int tier;
	private int maxLevel;
	private String item;
	private String desc;

	private CustomEnchant(String name, int tier, int maxLevel, String item, String desc) {
		this.name = name;
		this.tier = tier;
		this.maxLevel = maxLevel;
		this.item = item;
		this.desc = desc;
	}

	public String getName() {
		return this.name;
	}

	public int getTier() {
		return this.tier;
	}

	public int getMaxLevel() {
		return this.maxLevel;
	}

	public String getItemEnchantType() {
		return this.item;
	}
	
	public String getEnchantDesc() {
		return this.desc;
	}
	
	public int getApplicableItem() {
		if (this.getItemEnchantType() == "swords")
			return 1;
		if (this.getItemEnchantType() == "bows")
			return 2;
		if (this.getItemEnchantType() == "helmets")
			return 3;
		if (this.getItemEnchantType() == "chestplates")
			return 4;
		if (this.getItemEnchantType() == "leggings")
			return 5;
		if (this.getItemEnchantType() == "boots")
			return 6;
		if (this.getItemEnchantType() == "pickaxes")
			return 7;
		if (this.getItemEnchantType() == "shovels")
			return 8;
		if (this.getItemEnchantType() == "hoes")
			return 9;
		if (this.getItemEnchantType() == "pickaxes and shovels")
			return 78;
		return 0;
	}
}