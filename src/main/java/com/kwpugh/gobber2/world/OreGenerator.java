package com.kwpugh.gobber2.world;

import com.google.common.collect.ImmutableList;
import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.config.GobberConfigBuilder;
import com.kwpugh.gobber2.init.BlockInit;
import com.kwpugh.gobber2.world.feature.CustomOreFeature;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class OreGenerator
{
	public static ImmutableList<OreConfiguration.TargetBlockState> GOBBER2_LUCKY_BLOCK_TARGET_LIST;
	public static ImmutableList<OreConfiguration.TargetBlockState> GOBBER2_ORE_TARGET_LIST;
	public static ImmutableList<OreConfiguration.TargetBlockState> GOBBER2_ORE_NETHER_TARGET_LIST;
	public static ImmutableList<OreConfiguration.TargetBlockState> GOBBER2_ORE_END_TARGET_LIST;

	public static ConfiguredFeature<?,?> GOBBER2_LUCKY_BLOCK;	
	public static ConfiguredFeature<?,?> GOBBER2_ORE;
	public static ConfiguredFeature<?,?> GOBBER2_ORE_NETHER;
	public static ConfiguredFeature<?,?> GOBBER2_ORE_END;

	public static void registerConfiguredFeatures()
	{
		GOBBER2_LUCKY_BLOCK_TARGET_LIST = ImmutableList.of(OreConfiguration.target(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, BlockInit.GOBBER2_LUCKY_BLOCK.get().defaultBlockState()),
				OreConfiguration.target(OreConfiguration.Predicates.DEEPSLATE_ORE_REPLACEABLES, BlockInit.GOBBER2_LUCKY_BLOCK.get().defaultBlockState()));
		GOBBER2_ORE_TARGET_LIST = ImmutableList.of(OreConfiguration.target(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, BlockInit.GOBBER2_ORE.get().defaultBlockState()),
				OreConfiguration.target(OreConfiguration.Predicates.DEEPSLATE_ORE_REPLACEABLES, BlockInit.GOBBER2_ORE.get().defaultBlockState()));
		GOBBER2_ORE_NETHER_TARGET_LIST = ImmutableList.of(OreConfiguration.target(OreConfiguration.Predicates.NETHER_ORE_REPLACEABLES, BlockInit.GOBBER2_ORE_NETHER.get().defaultBlockState()));
		GOBBER2_ORE_END_TARGET_LIST = ImmutableList.of(OreConfiguration.target(CustomOreFeature.FillerBlockType.end_stone, BlockInit.GOBBER2_ORE_END.get().defaultBlockState()));


		GOBBER2_LUCKY_BLOCK = Feature.ORE.configured(new OreConfiguration(GOBBER2_LUCKY_BLOCK_TARGET_LIST,
				GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_SIZE.get()))
				.rangeUniform(VerticalAnchor.bottom(),
				VerticalAnchor.aboveBottom(GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_MAX_HEIGHT.get())).squared()
				.count(GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_CHANCE.get());
		GOBBER2_ORE = Feature.ORE.configured(new OreConfiguration(GOBBER2_ORE_TARGET_LIST,
				GobberConfigBuilder.GOBBER2_ORE_SIZE.get()))
				.rangeUniform(VerticalAnchor.bottom(),
				VerticalAnchor.aboveBottom(GobberConfigBuilder.GOBBER2_ORE_MAX_HEIGHT.get())).squared()
				.count(GobberConfigBuilder.GOBBER2_ORE_CHANCE.get());
		GOBBER2_ORE_NETHER = Feature.ORE.configured(new OreConfiguration(GOBBER2_ORE_NETHER_TARGET_LIST,
				GobberConfigBuilder.GOBBER2_ORE_NETHER_SIZE.get()))
				.rangeUniform(VerticalAnchor.bottom(),
				VerticalAnchor.aboveBottom(GobberConfigBuilder.GOBBER2_ORE_NETHER_MAX_HEIGHT.get())).squared()
				.count(GobberConfigBuilder.GOBBER2_ORE_NETHER_CHANCE.get());
		GOBBER2_ORE_END = Feature.ORE.configured(new OreConfiguration(GOBBER2_ORE_END_TARGET_LIST,
				4))
				.rangeUniform(VerticalAnchor.bottom(),
				VerticalAnchor.aboveBottom(90)).squared()
				.count(20);

		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Gobber2.modid, "gobber2_lucky_block"), GOBBER2_LUCKY_BLOCK);		
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Gobber2.modid, "gobber2_ore"), GOBBER2_ORE);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Gobber2.modid, "gobber2_ore_nether"), GOBBER2_ORE_NETHER);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Gobber2.modid, "gobber2_ore_end"), GOBBER2_ORE_END);
	}
	
	@SubscribeEvent
	public static void registerBiomeModification(final BiomeLoadingEvent event)
	{
		registerConfiguredFeatures();
		event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(() -> GOBBER2_LUCKY_BLOCK);
		event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(() -> GOBBER2_ORE);
		event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(() -> GOBBER2_ORE_NETHER);
		event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(() -> GOBBER2_ORE_END);
	}
}