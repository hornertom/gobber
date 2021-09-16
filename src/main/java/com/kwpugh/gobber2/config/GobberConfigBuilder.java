package com.kwpugh.gobber2.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class GobberConfigBuilder
{
    public static ForgeConfigSpec.BooleanValue GOBBER2_ORE_GENERATION;
    public static ForgeConfigSpec.IntValue GOBBER2_ORE_CHANCE;
    public static ForgeConfigSpec.IntValue GOBBER2_ORE_SIZE;
    //public static ForgeConfigSpec.IntValue GOBBER2_ORE_MIN_HEIGHT;
    public static ForgeConfigSpec.IntValue GOBBER2_ORE_MAX_HEIGHT;

    public static ForgeConfigSpec.BooleanValue GOBBER2_LUCKY_BLOCK_GENERATION;
    public static ForgeConfigSpec.IntValue GOBBER2_LUCKY_BLOCK_CHANCE;
    public static ForgeConfigSpec.IntValue GOBBER2_LUCKY_BLOCK_SIZE;
    //public static ForgeConfigSpec.IntValue GOBBER2_LUCKY_BLOCK_MIN_HEIGHT;
    public static ForgeConfigSpec.IntValue GOBBER2_LUCKY_BLOCK_MAX_HEIGHT;

    public static ForgeConfigSpec.BooleanValue GOBBER2_ORE_NETHER_GENERATION;
    public static ForgeConfigSpec.IntValue GOBBER2_ORE_NETHER_CHANCE;
    public static ForgeConfigSpec.IntValue GOBBER2_ORE_NETHER_SIZE;
    //public static ForgeConfigSpec.IntValue GOBBER2_ORE_NETHER_MIN_HEIGHT;
    public static ForgeConfigSpec.IntValue GOBBER2_ORE_NETHER_MAX_HEIGHT;

    public static ForgeConfigSpec.BooleanValue GOBBER2_ORE_END_GENERATION;
    //public static ForgeConfigSpec.IntValue GOBBER2_ORE_END_COUNT;

    public static ForgeConfigSpec.IntValue RING_BLINK_COOLDOWN;
    public static ForgeConfigSpec.IntValue RING_MINER_COOLDOWN;
    public static ForgeConfigSpec.IntValue RING_ABOVE_COOLDOWN;
    public static ForgeConfigSpec.IntValue RING_EXPLORER_COOLDOWN;
    public static ForgeConfigSpec.IntValue SNIPER_SWORD_COOLDOWN;
    public static ForgeConfigSpec.IntValue SNIPER_STAFF_COOLDOWN;

    public static ForgeConfigSpec.IntValue RING_EXPLORER_MIN_RANGE;
    public static ForgeConfigSpec.IntValue RING_EXPLORER_MAX_RANGE;

    public static ForgeConfigSpec.BooleanValue REVERSE_RING_MINER;
    public static ForgeConfigSpec.BooleanValue DELAY_BREAK_MODE;
    public static ForgeConfigSpec.DoubleValue RING_ACCELERATION_VELOCITY;
    public static ForgeConfigSpec.IntValue RING_REPAIR_DELAY;
    public static ForgeConfigSpec.IntValue RING_ATTRACTION_BLOCK_DISTANCE;
    public static ForgeConfigSpec.BooleanValue RING_ATTRACTION_MODE;
    public static ForgeConfigSpec.DoubleValue RING_DISMISSAL_VELOCITY;
    public static ForgeConfigSpec.DoubleValue RING_DISMISSAL_LIFT;
    public static ForgeConfigSpec.DoubleValue RING_DISMISSAL_RANGE;
    public static ForgeConfigSpec.BooleanValue RING_TELEPORT_LIMITED_USE;

    public static ForgeConfigSpec.IntValue RING_FARMER_TICK_DELAY;
    public static ForgeConfigSpec.IntValue RING_FARMER_RADIUS;
    public static ForgeConfigSpec.IntValue STAFF_FARMER_TICK_DELAY;
    public static ForgeConfigSpec.IntValue STAFF_FARMER_RADIUS;

    public static ForgeConfigSpec.BooleanValue STAFF_HARVEST_REPLANT;
    public static ForgeConfigSpec.BooleanValue STAFF_FARMER_REPLANT;
    
    public static ForgeConfigSpec.IntValue MEDALLION_EXP_ORBS;
    public static ForgeConfigSpec.IntValue MEDALLION_EXP_LOOT;

    public static ForgeConfigSpec.IntValue GOO_HUNGER;
    public static ForgeConfigSpec.DoubleValue GOO_SATURATION;
    public static ForgeConfigSpec.IntValue GOOEY_APPLE_HUNGER;
    public static ForgeConfigSpec.DoubleValue GOOEY_APPLE_SATURATION;
    public static ForgeConfigSpec.IntValue GOOEY_BREAD_HUNGER;
    public static ForgeConfigSpec.DoubleValue GOOEY_BREAD_SATURATION;
    public static ForgeConfigSpec.IntValue GOOEY_BEEF_HUNGER;
    public static ForgeConfigSpec.DoubleValue GOOEY_BEEF_SATURATION;
    public static ForgeConfigSpec.IntValue GOOEY_BEEFSTEW_HUNGER;
    public static ForgeConfigSpec.DoubleValue GOOEY_BEEFSTEW_SATURATION;

    public static ForgeConfigSpec.IntValue NETHER_GOO_HUNGER;
    public static ForgeConfigSpec.DoubleValue NETHER_GOO_SATURATION;
    public static ForgeConfigSpec.IntValue NETHER_GOOEY_APPLE_HUNGER;
    public static ForgeConfigSpec.DoubleValue NETHER_GOOEY_APPLE_SATURATION;
    public static ForgeConfigSpec.IntValue NETHER_GOOEY_BREAD_HUNGER;
    public static ForgeConfigSpec.DoubleValue NETHER_GOOEY_BREAD_SATURATION;
    public static ForgeConfigSpec.IntValue NETHER_GOOEY_BEEF_HUNGER;
    public static ForgeConfigSpec.DoubleValue NETHER_GOOEY_BEEF_SATURATION;
    public static ForgeConfigSpec.IntValue NETHER_GOOEY_BEEFSTEW_HUNGER;
    public static ForgeConfigSpec.DoubleValue NETHER_GOOEY_BEEFSTEW_SATURATION;

    public static ForgeConfigSpec.IntValue GOBBER_ARMOR_HUNGER;
    public static ForgeConfigSpec.DoubleValue GOBBER_ARMOR_SATURATION;
    public static ForgeConfigSpec.IntValue GOBBER_NETHER_ARMOR_HUNGER;
    public static ForgeConfigSpec.DoubleValue GOBBER_NETHER_ARMOR_SATURATION;
    public static ForgeConfigSpec.IntValue GOBBER_END_ARMOR_HUNGER;
    public static ForgeConfigSpec.DoubleValue GOBBER_END_ARMOR_SATURATION;
    public static ForgeConfigSpec.IntValue GOBBER_DRAGON_ARMOR_HUNGER;
    public static ForgeConfigSpec.DoubleValue GOBBER_DRAGON_ARMOR_SATURATION;

    public static ForgeConfigSpec.IntValue GOBBER_TOOLS_DURABILITY;
    public static ForgeConfigSpec.DoubleValue GOBBER_TOOLS_MINING_SPEED;
    public static ForgeConfigSpec.DoubleValue GOBBER_TOOLS_ATTACK_DAMAGE;
    public static ForgeConfigSpec.IntValue GOBBER_TOOLS_MINING_LEVEL;
    public static ForgeConfigSpec.IntValue GOBBER_TOOLS_ENCHANTABILITY;

    public static ForgeConfigSpec.IntValue NETHER_GOBBER_TOOLS_DURABILITY;
    public static ForgeConfigSpec.DoubleValue NETHER_GOBBER_TOOLS_MINING_SPEED;
    public static ForgeConfigSpec.DoubleValue NETHER_GOBBER_TOOLS_ATTACK_DAMAGE;
    public static ForgeConfigSpec.IntValue NETHER_GOBBER_TOOLS_MINING_LEVEL;
    public static ForgeConfigSpec.IntValue NETHER_GOBBER_TOOLS_ENCHANTABILITY;

    public static ForgeConfigSpec.IntValue END_GOBBER_TOOLS_DURABILITY;
    public static ForgeConfigSpec.DoubleValue END_GOBBER_TOOLS_MINING_SPEED;
    public static ForgeConfigSpec.DoubleValue END_GOBBER_TOOLS_ATTACK_DAMAGE;
    public static ForgeConfigSpec.IntValue END_GOBBER_TOOLS_MINING_LEVEL;
    public static ForgeConfigSpec.IntValue END_GOBBER_TOOLS_ENCHANTABILITY;

    public static ForgeConfigSpec.IntValue GOBBER_ARMOR_DURABILITY_MULTIPLIER;
    public static ForgeConfigSpec.IntValue GOBBER_ARMOR_ENCHANTABILITY;
    public static ForgeConfigSpec.DoubleValue GOBBER_ARMOR_TOUGHNESS;
    public static ForgeConfigSpec.DoubleValue GOBBER_ARMOR_KNOCKBACK;

    public static ForgeConfigSpec.IntValue NETHER_GOBBER_ARMOR_DURABILITY_MULTIPLIER;
    public static ForgeConfigSpec.IntValue NETHER_GOBBER_ARMOR_ENCHANTABILITY;
    public static ForgeConfigSpec.DoubleValue NETHER_GOBBER_ARMOR_TOUGHNESS;
    public static ForgeConfigSpec.DoubleValue NETHER_GOBBER_ARMOR_KNOCKBACK;

    public static ForgeConfigSpec.IntValue END_GOBBER_ARMOR_DURABILITY_MULTIPLIER;
    public static ForgeConfigSpec.IntValue END_GOBBER_ARMOR_ENCHANTABILITY;
    public static ForgeConfigSpec.DoubleValue END_GOBBER_ARMOR_TOUGHNESS;
    public static ForgeConfigSpec.DoubleValue END_GOBBER_ARMOR_KNOCKBACK;

    public static ForgeConfigSpec.IntValue DRAGON_ARMOR_DURABILITY_MULTIPLIER;
    public static ForgeConfigSpec.IntValue DRAGON_ARMOR_ENCHANTABILITY;
    public static ForgeConfigSpec.DoubleValue DRAGON_ARMOR_TOUGHNESS;
    public static ForgeConfigSpec.DoubleValue DRAGON_ARMOR_KNOCKBACK;

    public static ForgeConfigSpec.BooleanValue ENABLE_GOBBER_ARMOR_HEALTH_PERKS;
    public static ForgeConfigSpec.BooleanValue ENABLE_GOBBER_NETHER_ARMOR_HEALTH_PERKS;
    public static ForgeConfigSpec.BooleanValue ENABLE_GOBBER_END_ARMOR_HEALTH_PERKS;
    public static ForgeConfigSpec.BooleanValue ENABLE_GOBBER_DRAGON_ARMOR_HEALTH_PERKS;
    
    public static ForgeConfigSpec.BooleanValue ENABLE_DRAGON_ARMOR_VOID_PROTECTION;
    public static ForgeConfigSpec.BooleanValue ENABLE_DRAGON_STAR_OFFHAND;
    public static ForgeConfigSpec.BooleanValue ENABLE_DRAGON_KILL_EVERY_KILL;

    public static void init(ForgeConfigSpec.Builder SERVER_BUILDER)
    {
        SERVER_BUILDER.comment("Dragon Armor Material Values").push("dragon_armor_material");

        DRAGON_ARMOR_DURABILITY_MULTIPLIER = SERVER_BUILDER.comment("Dragon armor durability multiplier [1-200, default: 100]").defineInRange("dragonArmorDurabilityMultiplier", 100, 1, 200);
        DRAGON_ARMOR_ENCHANTABILITY = SERVER_BUILDER.comment("Dragon armor enchantability [1-50, default: 30]").defineInRange("dragonArmorEnchantability", 30, 1, 50);
        DRAGON_ARMOR_TOUGHNESS = SERVER_BUILDER.comment("Dragon armor toughness [0-5, default: 3.0]").defineInRange("dragonArmorToughness", 3.0, 0, 5.0);
        DRAGON_ARMOR_KNOCKBACK = SERVER_BUILDER.comment("Dragon armor knockback [0-5, default: 0.3]").defineInRange("dragonArmorKnockback", 0.3, 0, 5.0);

        SERVER_BUILDER.pop();


        SERVER_BUILDER.comment("End Gobber Tool Material Values").push("end_gobber_tool_material");

        END_GOBBER_TOOLS_DURABILITY = SERVER_BUILDER.comment("End Gobber tool durability [1-9000, default: 8000]").defineInRange("endGobberToolsDurability", 8000, 0, 9000);
        END_GOBBER_TOOLS_MINING_SPEED = SERVER_BUILDER.comment("End Gobber tool mining speed [1-25, default: 14.0]").defineInRange("endGobberToolsSpeed", 14.0, 1, 25.0);
        END_GOBBER_TOOLS_ATTACK_DAMAGE = SERVER_BUILDER.comment("End Gobber tool attack damage [1-100, default: 1.0]").defineInRange("endGobberToolsDamage", 1.0, 1, 100.0);
        END_GOBBER_TOOLS_MINING_LEVEL = SERVER_BUILDER.comment("End Gobber tool mining level [1-6, default: 5]").defineInRange("endGobberToolsMiningLevel", 5, 1, 6);
        END_GOBBER_TOOLS_ENCHANTABILITY = SERVER_BUILDER.comment("End Gobber tool enchantability [0-50, default: 30]").defineInRange("endGobberToolsEnchantability", 30, 1, 50);

        SERVER_BUILDER.pop();


        SERVER_BUILDER.comment("End Gobber Armor Material Values").push("end_gobber_armor_material");

        END_GOBBER_ARMOR_DURABILITY_MULTIPLIER = SERVER_BUILDER.comment("End Gobber armor durability multiplier [1-200, default: 100]").defineInRange("endGobberArmorDurabilityMultiplier", 100, 1, 200);
        END_GOBBER_ARMOR_ENCHANTABILITY = SERVER_BUILDER.comment("End Gobber armor enchantability [1-50, default: 30]").defineInRange("endGobberArmorEnchantability", 30, 1, 50);
        END_GOBBER_ARMOR_TOUGHNESS = SERVER_BUILDER.comment("End Gobber armor toughness [0-5, default: 3.0]").defineInRange("endGobberArmorToughness", 3.0, 0, 5.0);
        END_GOBBER_ARMOR_KNOCKBACK = SERVER_BUILDER.comment("End Gobber armor knockback [0-5, default: 0.2]").defineInRange("endGobberArmorKnockback", 0.2, 0, 5.0);

        SERVER_BUILDER.pop();


        SERVER_BUILDER.comment("Nether Gobber Tool Material Values").push("nether_gobber_tool_material");

        NETHER_GOBBER_TOOLS_DURABILITY = SERVER_BUILDER.comment("Nether Gobber tool durability [1-9000, default: 5200]").defineInRange("netherGobberToolsDurability", 5200, 0, 9000);
        NETHER_GOBBER_TOOLS_MINING_SPEED = SERVER_BUILDER.comment("Nether Gobber tool mining speed [1-25, default: 12.0]").defineInRange("netherGobberToolsSpeed", 12.0, 1, 25.0);
        NETHER_GOBBER_TOOLS_ATTACK_DAMAGE = SERVER_BUILDER.comment("Nether Gobber tool attack damage [1-100, default: 1.0]").defineInRange("netherGobberToolsDamage", 1.0, 1, 100.0);
        NETHER_GOBBER_TOOLS_MINING_LEVEL = SERVER_BUILDER.comment("Nether Gobber tool mining level [1-6, default: 5]").defineInRange("netherGobberToolsMiningLevel", 5, 1, 6);
        NETHER_GOBBER_TOOLS_ENCHANTABILITY = SERVER_BUILDER.comment("Nether Gobber tool enchantability [0-50, default: 25]").defineInRange("netherGobberToolsEnchantability", 25, 1, 50);

        SERVER_BUILDER.pop();


        SERVER_BUILDER.comment("Nether Gobber Armor Material Values").push("nether_gobber_armor_material");

        NETHER_GOBBER_ARMOR_DURABILITY_MULTIPLIER = SERVER_BUILDER.comment("Nether Gobber armor durability multiplier [1-200, default: 83]").defineInRange("netherGobberArmorDurabilityMultiplier", 83, 1, 200);
        NETHER_GOBBER_ARMOR_ENCHANTABILITY = SERVER_BUILDER.comment("Nether Gobber armor enchantability [1-50, default: 30]").defineInRange("netherGobberArmorEnchantability", 30, 1, 50);
        NETHER_GOBBER_ARMOR_TOUGHNESS = SERVER_BUILDER.comment("Nether Gobber armor toughness [0-5, default: 2.75]").defineInRange("netherGobberArmorToughness", 2.75, 0, 5.0);
        NETHER_GOBBER_ARMOR_KNOCKBACK = SERVER_BUILDER.comment("Nether Gobber armor knockback [0-5, default: 0.1]").defineInRange("netherGobberArmorKnockback", 0.1, 0, 5.0);

        SERVER_BUILDER.pop();


        SERVER_BUILDER.comment("Gobber Tool Material Values").push("gobber_tool_material");

        GOBBER_TOOLS_DURABILITY = SERVER_BUILDER.comment("Gobber tool durability [1-9000, default: 3800]").defineInRange("gobberToolsDurability", 3800, 0, 9000);
        GOBBER_TOOLS_MINING_SPEED = SERVER_BUILDER.comment("Gobber tool mining speed [1-25, default: 9.0]").defineInRange("gobberToolsSpeed", 9.0, 1, 25.0);
        GOBBER_TOOLS_ATTACK_DAMAGE = SERVER_BUILDER.comment("Gobber tool attack damage [1-100, default: 1.0]").defineInRange("gobberToolsDamage", 1.0, 1, 100.0);
        GOBBER_TOOLS_MINING_LEVEL = SERVER_BUILDER.comment("Gobber tool mining level [1-6, default: 4]").defineInRange("gobberToolsMiningLevel", 4, 1, 6);
        GOBBER_TOOLS_ENCHANTABILITY = SERVER_BUILDER.comment("Gobber tool enchantability [0-50, default: 20]").defineInRange("gobberToolsEnchantability", 20, 1, 50);

        SERVER_BUILDER.pop();


        SERVER_BUILDER.comment("Gobber Armor Material Values").push("gobber_armor_material");

        GOBBER_ARMOR_DURABILITY_MULTIPLIER = SERVER_BUILDER.comment("Gobber armor durability multiplier [1-200, default: 71]").defineInRange("gobberArmorDurabilityMultiplier", 71, 1, 200);
        GOBBER_ARMOR_ENCHANTABILITY = SERVER_BUILDER.comment("Gobber armor enchantability [1-50, default: 25]").defineInRange("gobberArmorEnchantability", 25, 1, 50);
        GOBBER_ARMOR_TOUGHNESS = SERVER_BUILDER.comment("Gobber armor toughness [0-5, default: 2.5]").defineInRange("gobberArmorToughness", 2.5, 0, 5.0);
        GOBBER_ARMOR_KNOCKBACK = SERVER_BUILDER.comment("Gobber armor knockback [0-5, default: 0.1]").defineInRange("gobberArmorKnockback", 0.1, 0, 5.0);

        SERVER_BUILDER.pop();


        SERVER_BUILDER.comment("Gobber Food Values").push("gobber_foods");

        GOO_HUNGER = SERVER_BUILDER.comment("Goo hunger value [0-25, default: 7]").defineInRange("gooHunger", 7, 0, 25);
        GOO_SATURATION = SERVER_BUILDER.comment("Goo saturation value [0-5, default: 0.7]").defineInRange("gooSaturation", 0.7, 0, 5.0);
        GOOEY_APPLE_HUNGER = SERVER_BUILDER.comment("Gooey apple hunger value [0-25, default: 8]").defineInRange("gooeyAppleHunger", 8, 0, 25);
        GOOEY_APPLE_SATURATION = SERVER_BUILDER.comment("Gooey apple saturation value [0-5, default: 0.8]").defineInRange("gooeyAppleSaturation", 0.8, 0, 5.0);
        GOOEY_BREAD_HUNGER = SERVER_BUILDER.comment("Gooey bread hunger value [0-25, default: 8]").defineInRange("gooeyBreadHunger", 8, 0, 25);
        GOOEY_BREAD_SATURATION = SERVER_BUILDER.comment("Gooey bread saturation value [0-5, default: 0.8]").defineInRange("gooeyBreadSaturation", 0.8, 0, 5.0);
        GOOEY_BEEF_HUNGER = SERVER_BUILDER.comment("Gooey beef hunger value [0-25, default: 9]").defineInRange("gooeyBeefHunger", 9, 0, 25);
        GOOEY_BEEF_SATURATION = SERVER_BUILDER.comment("Gooey beef saturation value [0-5, default: 0.9]").defineInRange("gooeyBeefSaturation", 0.9, 0, 5.0);
        GOOEY_BEEFSTEW_HUNGER = SERVER_BUILDER.comment("Gooey beefstew hunger value [0-25, default: 10]").defineInRange("gooeyBeefstewHunger", 10, 0, 25);
        GOOEY_BEEFSTEW_SATURATION = SERVER_BUILDER.comment("Gooey beefstew saturation value [0-5, default: 1.0]").defineInRange("gooeyBeefstewSaturation", 1.0, 0, 5.0);

        SERVER_BUILDER.pop();


        SERVER_BUILDER.comment("Nether Gobber Food Values").push("nether_gobber_foods");

        NETHER_GOO_HUNGER = SERVER_BUILDER.comment("Nether Goo hunger value [0-25, default: 7]").defineInRange("netherGooHunger", 9, 0, 25);
        NETHER_GOO_SATURATION = SERVER_BUILDER.comment("Nether Goo saturation value [0-5, default: 0.7]").defineInRange("netherGooSaturation", 0.9, 0, 5.0);
        NETHER_GOOEY_APPLE_HUNGER = SERVER_BUILDER.comment("Nether Gooey apple hunger value [0-25, default: 8]").defineInRange("netherGooeyAppleHunger", 10, 0, 25);
        NETHER_GOOEY_APPLE_SATURATION = SERVER_BUILDER.comment("Nether Gooey apple saturation value [0-5, default: 0.8]").defineInRange("netherGooeyAppleSaturation", 1.0, 0, 5.0);
        NETHER_GOOEY_BREAD_HUNGER = SERVER_BUILDER.comment("Nether Gooey bread hunger value [0-25, default: 8]").defineInRange("netherGooeyBreadHunger", 10, 0, 25);
        NETHER_GOOEY_BREAD_SATURATION = SERVER_BUILDER.comment("Nether Gooey bread saturation value [0-5, default: 0.8]").defineInRange("netherGooeyBreadSaturation", 1.0, 0, 5.0);
        NETHER_GOOEY_BEEF_HUNGER = SERVER_BUILDER.comment("Nether Gooey beef hunger value [0-25, default: 9]").defineInRange("netherGooeyBeefHunger", 12, 0, 25);
        NETHER_GOOEY_BEEF_SATURATION = SERVER_BUILDER.comment("Nether Gooey beef saturation value [0-5, default: 0.9]").defineInRange("netherGooeyBeefSaturation", 1.2, 0, 5.0);
        NETHER_GOOEY_BEEFSTEW_HUNGER = SERVER_BUILDER.comment("Nether Gooey beefstew hunger value [0-25, default: 10]").defineInRange("netherGooeyBeefstewHunger", 14, 0, 25);
        NETHER_GOOEY_BEEFSTEW_SATURATION = SERVER_BUILDER.comment("Nether Gooey beefstew saturation value [0-5, default: 1.0]").defineInRange("netherGooeyBeefstewSaturation", 1.5, 0, 5.0);

        SERVER_BUILDER.pop();

        
        SERVER_BUILDER.comment("Gobber Lucky Block Generation").push("gobber2_lucky_block");

        GOBBER2_LUCKY_BLOCK_GENERATION = SERVER_BUILDER.comment("Generate Gobber Lucky Block in the world [true / false]").define("gobberLuckyBlockGeneration", true);
        GOBBER2_LUCKY_BLOCK_SIZE = SERVER_BUILDER.comment("Size of Gobber Lucky Block pockets [0-100, default: 3]").defineInRange("gobberLuckyBlockSize", 3, 0, 100);
        GOBBER2_LUCKY_BLOCK_CHANCE = SERVER_BUILDER.comment("Chances of Gobber Lucky Block pocket being generated [0-100, default: 20]").defineInRange("gobberLuckyBlockChance", 20, 0, 100);
        //GOBBER2_LUCKY_BLOCK_MIN_HEIGHT = SERVER_BUILDER.comment("Minimal height for Gobber Lucky Block pocket generation, [0-255, default: 1]").defineInRange("gobberLuckyBlockMinHeight", 1, 0, 255);
        GOBBER2_LUCKY_BLOCK_MAX_HEIGHT = SERVER_BUILDER.comment("Maximal height for Gobber Lucky Block pocket generation [0-255, default: 255]").defineInRange("gobberLuckyBlockMaxHeight", 255, 0, 255);

        SERVER_BUILDER.pop();
        
        

    	SERVER_BUILDER.comment("Gobber Ore Generation").push("gobber2_ore");

        GOBBER2_ORE_GENERATION = SERVER_BUILDER.comment("Generate Gobber Ore in the world [true / false]").define("gobberOreGeneration", true);
        GOBBER2_ORE_SIZE = SERVER_BUILDER.comment("Size of Gobber Ore pockets [0-100, default: 4]").defineInRange("gobberOreSize", 4, 0, 100);
        GOBBER2_ORE_CHANCE = SERVER_BUILDER.comment("Chances of Gobber Ore pocket being generated [0-100, default: 15]").defineInRange("gobberOreChance", 15, 0, 100);
        //GOBBER2_ORE_MIN_HEIGHT = SERVER_BUILDER.comment("Minimal height for Gobber Ore pocket generation, [0-255, default: 20]").defineInRange("gobberOreMinHeight", 20, 0, 255);
        GOBBER2_ORE_MAX_HEIGHT = SERVER_BUILDER.comment("Maximal height for Gobber Ore pocket generation [0-255, default: 30]").defineInRange("gobberOreMaxHeight", 30, 0, 255);

        SERVER_BUILDER.pop();

        
        SERVER_BUILDER.comment("Gobber Nether Ore Generation").push("gobber2_ore_nether");

        GOBBER2_ORE_NETHER_GENERATION = SERVER_BUILDER.comment("Generate Gobber Nether Ore in the world [true / false]").define("gobberOreNetherGeneration", true);
        GOBBER2_ORE_NETHER_SIZE = SERVER_BUILDER.comment("Size of Gobber Nether Ore pockets [0-100, default: 3]").defineInRange("gobberOreNetherSize", 3, 0, 100);
        GOBBER2_ORE_NETHER_CHANCE = SERVER_BUILDER.comment("Chances of Gobber Nether Ore pocket being generated [0-100, default: 80]").defineInRange("gobberOreNetherChance", 80, 0, 100);
        //GOBBER2_ORE_NETHER_MIN_HEIGHT = SERVER_BUILDER.comment("Minimal height for Gobber Nether Ore pocket generation, [0-255, default: 5]").defineInRange("gobberOreNetherMinHeight", 20, 0, 255);
        GOBBER2_ORE_NETHER_MAX_HEIGHT = SERVER_BUILDER.comment("Maximal height for Gobber Nether Ore pocket generation [0-255, default: 255]").defineInRange("gobberOreNetherMaxHeight", 255, 0, 255);

        SERVER_BUILDER.pop();


        SERVER_BUILDER.comment("Gobber End Ore Generation").push("gobber2_ore_end");

        GOBBER2_ORE_END_GENERATION = SERVER_BUILDER.comment("Generate Gobber End Ore in the world [true / false]").define("gobberOreEndGeneration", true);

        SERVER_BUILDER.pop();


        SERVER_BUILDER.comment("Item Cooldown Settings").push("item_cooldown_settings");

        RING_BLINK_COOLDOWN = SERVER_BUILDER.comment("Number of ticks duration for the Ring of Blink cooldown [0-3600, default: 60]").defineInRange("ringBlinkCooldown", 60, 0, 3600);
        RING_MINER_COOLDOWN = SERVER_BUILDER.comment("Number of ticks duration for the Ring of the Miner cooldown [0-3600, default: 80]").defineInRange("ringMinerCooldown", 80, 0, 3600);
        RING_ABOVE_COOLDOWN = SERVER_BUILDER.comment("Number of ticks duration for the Ring of Above cooldown [0-3600, default: 80]").defineInRange("ringAboveCooldown", 80, 0, 3600);
        RING_EXPLORER_COOLDOWN = SERVER_BUILDER.comment("Number of ticks duration for the Ring of the Explorer cooldown [0-3600, default: 480]").defineInRange("ringExplorerCooldown", 480, 0, 3600);
        SNIPER_SWORD_COOLDOWN = SERVER_BUILDER.comment("Number of ticks duration for the Sword of the Sniper cooldown [0-3600, default: 60]").defineInRange("swordSniperCooldown", 60, 0, 3600);
        SNIPER_STAFF_COOLDOWN = SERVER_BUILDER.comment("Number of ticks duration for the Staff of the Sniper cooldown [0-3600, default: 60]").defineInRange("staffSniperCooldown", 60, 0, 3600);

        SERVER_BUILDER.pop();


        SERVER_BUILDER.comment("Ring of the Exploer Settings").push("ring_explorer_settings");

        RING_EXPLORER_MIN_RANGE = SERVER_BUILDER.comment("Ring of Explorer - Min distance from world spawn to begin searching for a spot [default: 500]").defineInRange("ringExplorerMin", 500, 0, 100000);
        RING_EXPLORER_MAX_RANGE = SERVER_BUILDER.comment("Ring of Explorer - Max distance from world spawn to begin searching for a spot [default: 6000]").defineInRange("ringExplorerMax", 6000, 0, 250000);

        SERVER_BUILDER.pop();


        SERVER_BUILDER.comment("Misc Ring/staff Settings").push("misc_ring_staff_settings");

        REVERSE_RING_MINER = SERVER_BUILDER.comment("Reverse the drop/no drops feature on Ring of Miner [true / false]").define("reverseRingMiner", false);
        DELAY_BREAK_MODE = SERVER_BUILDER.comment("Uses a delayed break mode to reduce stutter and potential lag on Ring of Miner and Ring of Lumberjack [true / false]").define("delayedBreakMode", true);
        RING_ACCELERATION_VELOCITY = SERVER_BUILDER.comment("Ring of Acceleration - amount of velocity applied [default: .18]").defineInRange("ringAccelerationVelocity", .18, 0.0, .30);
        RING_REPAIR_DELAY = SERVER_BUILDER.comment("Ring of Repair - Delay time between repair ticks [default: 120]").defineInRange("ringRepairDelay", 120, 20, 600);
        RING_ATTRACTION_BLOCK_DISTANCE = SERVER_BUILDER.comment("Ring of Attraction - Distance coal blocks magnet effect [default: 5]").defineInRange("ringAttractionBlocking", 5, 0, 15);
        RING_ATTRACTION_MODE = SERVER_BUILDER.comment("Ring of Attraction - Items are instantly placed in player inventory  [true / false]").define("ringAttractionMode", false);
        RING_DISMISSAL_VELOCITY = SERVER_BUILDER.comment("Ring of Dismissal - Sets the horizontal velocity [default: 0.2]").defineInRange("ringDismissalVelocity", 0.2, 0.0, 15.0);
        RING_DISMISSAL_LIFT = SERVER_BUILDER.comment("Ring of Dismissal - Sets the vertical lift [default: 1.5]").defineInRange("ringDismissalLift", 1.5, 0.0, 15.0);
        RING_DISMISSAL_RANGE = SERVER_BUILDER.comment("Ring of Dismissal - Sets the horizontal range to detect mobs [default: 8.0]").defineInRange("ringDismissalLift", 8.0, 0.0, 16.0);
        RING_TELEPORT_LIMITED_USE = SERVER_BUILDER.comment("Enable a single use of the Ring of Teleport [true / false]").define("limitedUseRingTeleport", false);

        SERVER_BUILDER.pop();


        SERVER_BUILDER.comment("Ring/Staff of Farmer Settings").push("farmer_settings");

        RING_FARMER_TICK_DELAY = SERVER_BUILDER.comment("Ring of the Farmer base tick delay [default: 20]").defineInRange("ringFarmerTickDelay", 20, 10, 120);
        RING_FARMER_RADIUS = SERVER_BUILDER.comment("Ring of the Farmer radius from player [default: 10]").defineInRange("ringFarmerRadius", 10, 2, 20);
        STAFF_FARMER_TICK_DELAY = SERVER_BUILDER.comment("Staff of the Farmer base tick delay [default: 20]").defineInRange("staffFarmerTickDelay", 20, 10, 120);
        STAFF_FARMER_RADIUS = SERVER_BUILDER.comment("Staff of the Farmer radius from player [default: 10]").defineInRange("staffFarmerRadius", 10, 2, 20);

        SERVER_BUILDER.pop();

        
        SERVER_BUILDER.comment("Auto Replanting Settings").push("replanting_settings");
      
        STAFF_HARVEST_REPLANT = SERVER_BUILDER.comment("Should the Staff of Harvest auto-replant? [true / false]").define("StaffHarvestReplant", true);
        STAFF_FARMER_REPLANT = SERVER_BUILDER.comment("Should the Staff of the Farmer auto-replant? [true / false]").define("StaffFarmerReplant", true);

        SERVER_BUILDER.pop();

        
        SERVER_BUILDER.comment("Armor Hunger/Saturation Settings").push("armor_health_settings");

        GOBBER_ARMOR_HUNGER = SERVER_BUILDER.comment("Amount of hunger point to restore per interval").defineInRange("gobberArmorHunger", 4, 0, 10);
        GOBBER_ARMOR_SATURATION = SERVER_BUILDER.comment("Amount of saturation point to restore per interval").defineInRange("gobberArmorSaturation", .02, 0.0, 10.0);
        GOBBER_NETHER_ARMOR_HUNGER = SERVER_BUILDER.comment("Amount of hunger point to restore per interval").defineInRange("gobberNetherArmorHunger", 1, 0, 10);
        GOBBER_NETHER_ARMOR_SATURATION = SERVER_BUILDER.comment("Amount of saturation point to restore per interval").defineInRange("gobberNetherArmorSaturation", .05, 0.0, 10.0);
        GOBBER_END_ARMOR_HUNGER = SERVER_BUILDER.comment("Amount of hunger point to restore per interval").defineInRange("gobberEndArmorHunger", 1, 0, 10);
        GOBBER_END_ARMOR_SATURATION = SERVER_BUILDER.comment("Amount of saturation point to restore per interval").defineInRange("gobberEndArmorSaturation", .10, 0.0, 10.0);
        GOBBER_DRAGON_ARMOR_HUNGER = SERVER_BUILDER.comment("Amount of hunger point to restore per interval").defineInRange("gobberDragonArmorHunger", 1, 0, 10);
        GOBBER_DRAGON_ARMOR_SATURATION = SERVER_BUILDER.comment("Amount of saturation point to restore per interval").defineInRange("gobberDragonArmorSaturation", .15, 0.0, 10.0);
        
        SERVER_BUILDER.pop();
        
        
        SERVER_BUILDER.comment("Armor Health Perks Settings").push("armor_health_perk_settings");

        ENABLE_GOBBER_ARMOR_HEALTH_PERKS = SERVER_BUILDER.comment("Enable/disable Gobber Armor health perks").define("gobberArmorHealthPerks", true);
        ENABLE_GOBBER_NETHER_ARMOR_HEALTH_PERKS = SERVER_BUILDER.comment("Enable/disable Gobber Nether Armor health perks").define("gobberNetherArmorHealthPerks", true);
        ENABLE_GOBBER_END_ARMOR_HEALTH_PERKS = SERVER_BUILDER.comment("Enable/disable Gobber End Armor health perks").define("gobberEndArmorHealthPerks", true);
        ENABLE_GOBBER_DRAGON_ARMOR_HEALTH_PERKS = SERVER_BUILDER.comment("Enable/disable Gobber Dragon Armor health perks").define("gobberDragonArmorHealthPerks", true);

        SERVER_BUILDER.pop();
        

        SERVER_BUILDER.comment("Medallion of Experience Settings").push("medallion_exp_settings");

        MEDALLION_EXP_ORBS = SERVER_BUILDER.comment("Medallion of Experience multiplier for extra XP orbs [default: 5]").defineInRange("medallionExpOrbs", 5, 0, 20);
        MEDALLION_EXP_LOOT = SERVER_BUILDER.comment("Medallion of Experience multiplier for extra mob loot [default: 10]").defineInRange("medallionExpLoot", 20, 5, 100);

        SERVER_BUILDER.pop();


        SERVER_BUILDER.comment("Dragon Star Special").push("dragonStar_special");

        ENABLE_DRAGON_STAR_OFFHAND = SERVER_BUILDER.comment("Enable Dragon Star Offhannd Function [true / false]").define("enableDragonStarOffhand", true);

        SERVER_BUILDER.pop();
        
        
        SERVER_BUILDER.comment("Enable Dragon Armor Void Protect - immune to /kill command").push("dragon_armor_void");

        ENABLE_DRAGON_ARMOR_VOID_PROTECTION = SERVER_BUILDER.comment("Enable Dragon Armor Void Protection [true / false]").define("enableDragonArmorVoidProtection", false);

        SERVER_BUILDER.pop();


        SERVER_BUILDER.comment("Dragon Egg With Every Kill").push("dragon_egg_kill");

        ENABLE_DRAGON_KILL_EVERY_KILL = SERVER_BUILDER.comment("Enable Dragon Egg drops on every kill [true / false]").define("enableDragonEggEveryKill", false);

        SERVER_BUILDER.pop();
    }
}
