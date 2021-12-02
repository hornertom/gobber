package com.kwpugh.gobber2.world;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.config.GobberConfigBuilder;
import com.kwpugh.gobber2.init.BlockInit;
import com.kwpugh.gobber2.world.feature.CustomOreFeature;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class GobberConfiguredFeature
{
	// Create target block lists
	public static List<OreConfiguration.TargetBlockState> GOBBER2_LUCKY_BLOCK_TARGET_LIST;
	public static List<OreConfiguration.TargetBlockState> GOBBER2_ORE_TARGET_LIST;
	public static List<OreConfiguration.TargetBlockState> GOBBER2_ORE_NETHER_TARGET_LIST;
	public static List<OreConfiguration.TargetBlockState> GOBBER2_ORE_END_TARGET_LIST;

	// Declare configured feature variables
	public static ConfiguredFeature<?,?> GOBBER2_LUCKY_BLOCK;
	public static ConfiguredFeature<?,?> GOBBER2_LUCKY_BLOCK_DEEPSLATE;
	public static ConfiguredFeature<?,?> GOBBER2_LUCKY_BLOCK_NETHER;
	public static ConfiguredFeature<?,?> GOBBER2_ORE;
	public static ConfiguredFeature<?,?> GOBBER2_ORE_DEEPSLATE;
	public static ConfiguredFeature<?,?> GOBBER2_ORE_NETHER;
	public static ConfiguredFeature<?,?> GOBBER2_ORE_END;


	public static void registerConfiguredFeatures()
	{
		// Assemble target block lists
		GOBBER2_LUCKY_BLOCK_TARGET_LIST = List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.GOBBER2_LUCKY_BLOCK.get().defaultBlockState()),
				OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.GOBBER2_LUCKY_BLOCK_DEEPSLATE.get().defaultBlockState()),
				OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, BlockInit.GOBBER2_LUCKY_BLOCK_NETHER.get().defaultBlockState()));
		GOBBER2_ORE_TARGET_LIST = List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.GOBBER2_ORE.get().defaultBlockState()),
				OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.GOBBER2_ORE_DEEPSLATE.get().defaultBlockState()));
		GOBBER2_ORE_NETHER_TARGET_LIST = List.of(OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, BlockInit.GOBBER2_ORE_NETHER.get().defaultBlockState()));
		GOBBER2_ORE_END_TARGET_LIST = List.of(OreConfiguration.target(CustomOreFeature.FillerBlockType.end_stone, BlockInit.GOBBER2_ORE_END.get().defaultBlockState()));

		// Add target blocks to configured features
		GOBBER2_LUCKY_BLOCK = Feature.ORE.configured(new OreConfiguration(GOBBER2_LUCKY_BLOCK_TARGET_LIST, GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_SIZE.get()));
		GOBBER2_LUCKY_BLOCK_DEEPSLATE = Feature.ORE.configured(new OreConfiguration(GOBBER2_LUCKY_BLOCK_TARGET_LIST, GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_DEEPSLATE_SIZE.get()));
		GOBBER2_LUCKY_BLOCK_NETHER = Feature.ORE.configured(new OreConfiguration(GOBBER2_LUCKY_BLOCK_TARGET_LIST, GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_NETHER_SIZE.get()));
		GOBBER2_ORE = Feature.ORE.configured(new OreConfiguration(GOBBER2_ORE_TARGET_LIST, GobberConfigBuilder.GOBBER2_ORE_SIZE.get()));
		GOBBER2_ORE_DEEPSLATE = Feature.ORE.configured(new OreConfiguration(GOBBER2_ORE_TARGET_LIST, GobberConfigBuilder.GOBBER2_ORE_DEEPSLATE_SIZE.get()));
		GOBBER2_ORE_NETHER = Feature.ORE.configured(new OreConfiguration(GOBBER2_ORE_NETHER_TARGET_LIST, GobberConfigBuilder.GOBBER2_ORE_NETHER_SIZE.get()));
		GOBBER2_ORE_END = Feature.ORE.configured(new OreConfiguration(GOBBER2_ORE_END_TARGET_LIST, GobberConfigBuilder.GOBBER2_ORE_END_SIZE.get()));

		// Register the configured features
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Gobber2.modid, "gobber2_lucky_block"), GOBBER2_LUCKY_BLOCK);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Gobber2.modid, "gobber2_lucky_block_deepslate"), GOBBER2_LUCKY_BLOCK_DEEPSLATE);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Gobber2.modid, "gobber2_lucky_block_nether"), GOBBER2_LUCKY_BLOCK_NETHER);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Gobber2.modid, "gobber2_ore"), GOBBER2_ORE);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Gobber2.modid, "gobber2_ore_deepslate"), GOBBER2_ORE_DEEPSLATE);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Gobber2.modid, "gobber2_ore_nether"), GOBBER2_ORE_NETHER);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Gobber2.modid, "gobber2_ore_end"), GOBBER2_ORE_END);
	}
}