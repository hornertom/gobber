package com.kwpugh.gobber2.lists;

import com.kwpugh.gobber2.config.GobberConfigBuilder;

import net.minecraft.world.food.FoodProperties;

public class FoodList
{
	static int gooHunger = GobberConfigBuilder.GOO_HUNGER.get();
	static double gooSaturation = GobberConfigBuilder.GOO_SATURATION.get();
	static int gooeyAppleHunger = GobberConfigBuilder.GOOEY_APPLE_HUNGER.get();
	static double gooeyAppleSaturation = GobberConfigBuilder.GOOEY_APPLE_SATURATION.get();
	static int gooeyBreadHunger = GobberConfigBuilder.GOOEY_BREAD_HUNGER.get();
	static double gooeyBreadSaturation = GobberConfigBuilder.GOOEY_BREAD_SATURATION.get();
	static int gooeyBeefHunger = GobberConfigBuilder.GOOEY_BEEF_HUNGER.get();
	static double gooeyBeefSaturation = GobberConfigBuilder.GOOEY_BEEF_SATURATION.get();
	static int gooeyBeefstewHunger = GobberConfigBuilder.GOOEY_BEEFSTEW_HUNGER.get();
	static double gooeyBeefstewSaturation = GobberConfigBuilder.GOOEY_BEEFSTEW_SATURATION.get();
	
	static int netherGooHunger = GobberConfigBuilder.NETHER_GOO_HUNGER.get();
	static double netherGooSaturation = GobberConfigBuilder.NETHER_GOO_SATURATION.get();
	static int netherGooeyAppleHunger = GobberConfigBuilder.NETHER_GOOEY_APPLE_HUNGER.get();
	static double netherGooeyAppleSaturation = GobberConfigBuilder.NETHER_GOOEY_APPLE_SATURATION.get();
	static int netherGooeyBreadHunger = GobberConfigBuilder.NETHER_GOOEY_BREAD_HUNGER.get();
	static double netherGooeyBreadSaturation = GobberConfigBuilder.NETHER_GOOEY_BREAD_SATURATION.get();
	static int netherGooeyBeefHunger = GobberConfigBuilder.NETHER_GOOEY_BEEF_HUNGER.get();
	static double netherGooeyBeefSaturation = GobberConfigBuilder.NETHER_GOOEY_BEEF_SATURATION.get();
	static int netherGooeyBeefstewHunger = GobberConfigBuilder.NETHER_GOOEY_BEEFSTEW_HUNGER.get();
	static double netherGooeyBeefstewSaturation = GobberConfigBuilder.NETHER_GOOEY_BEEFSTEW_SATURATION.get();
	
	public static FoodProperties gooFood = (new FoodProperties.Builder()).nutrition(gooHunger).saturationMod((float) gooSaturation).alwaysEat().build();
	public static FoodProperties gooeyApple = (new FoodProperties.Builder()).nutrition(gooeyAppleHunger).saturationMod((float) gooeyAppleSaturation).alwaysEat().build();
	public static FoodProperties gooeyBread = (new FoodProperties.Builder()).nutrition(gooeyBreadHunger).saturationMod((float) gooeyBreadSaturation).alwaysEat().build();
	public static FoodProperties gooeyBeef = (new FoodProperties.Builder()).nutrition(gooeyBeefHunger).saturationMod((float) gooeyBeefSaturation).meat().alwaysEat().build();
	public static FoodProperties gooeyBeefstew = (new FoodProperties.Builder()).nutrition(gooeyBeefstewHunger).saturationMod((float) gooeyBeefstewSaturation).alwaysEat().build();
	
	public static FoodProperties gooFoodNether = (new FoodProperties.Builder()).nutrition(netherGooHunger).saturationMod((float) netherGooSaturation).alwaysEat().build();
	public static FoodProperties gooeyAppleNether = (new FoodProperties.Builder()).nutrition(netherGooeyAppleHunger).saturationMod((float) netherGooeyAppleSaturation).alwaysEat().build();
	public static FoodProperties gooeyBreadNether = (new FoodProperties.Builder()).nutrition(netherGooeyBreadHunger).saturationMod((float) netherGooeyBreadSaturation).alwaysEat().build();
	public static FoodProperties gooeyBeefNether = (new FoodProperties.Builder()).nutrition(netherGooeyBeefHunger).saturationMod((float) netherGooeyBeefSaturation).meat().alwaysEat().build();
	public static FoodProperties gooeyBeefstewNether = (new FoodProperties.Builder()).nutrition(netherGooeyBeefstewHunger).saturationMod((float) netherGooeyBeefstewSaturation).alwaysEat().build();
}
