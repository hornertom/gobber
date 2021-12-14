package com.kwpugh.gobber2.world;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.config.GobberConfigBuilder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class GobberPlacedFeature
{
    // Declare placed feature variables
    public static PlacedFeature ORE_LUCKY_BLOCK_OVERWORLD;
    public static PlacedFeature ORE_LUCKY_BLOCK_OVERWORLD_DEEPSLATE;
    public static PlacedFeature ORE_LUCKY_BLOCK_NETHER;
    public static PlacedFeature ORE_GOBBER_OVERWORLD;
    public static PlacedFeature ORE_GOBBER_OVERWORLD_DEEPSLATE;
    public static PlacedFeature ORE_GOBBER_NETHER;
    public static PlacedFeature ORE_GOBBER_THEEND;

    public static void registerPlacedFeatures()
    {
        // Add configured features and placement details to placed features
        ORE_LUCKY_BLOCK_OVERWORLD = GobberConfiguredFeature.GOBBER2_LUCKY_BLOCK
                .placed(commonOrePlacement(GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_CHANCE.get(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_MIN_HEIGHT.get()),
                VerticalAnchor.absolute(GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_MAX_HEIGHT.get()))));

        ORE_LUCKY_BLOCK_OVERWORLD_DEEPSLATE = GobberConfiguredFeature.GOBBER2_LUCKY_BLOCK_DEEPSLATE
                .placed(commonOrePlacement(GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_DEEPSLATE_CHANCE.get(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_DEEPSLATE_MIN_HEIGHT.get()),
                VerticalAnchor.absolute(GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_DEEPSLATE_MAX_HEIGHT.get()))));

        ORE_LUCKY_BLOCK_NETHER = GobberConfiguredFeature.GOBBER2_LUCKY_BLOCK_NETHER
                .placed(commonOrePlacement(GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_NETHER_CHANCE.get(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_NETHER_MIN_HEIGHT.get()),
                VerticalAnchor.absolute(GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_NETHER_MAX_HEIGHT.get()))));

        ORE_GOBBER_OVERWORLD = GobberConfiguredFeature.GOBBER2_ORE
                .placed(commonOrePlacement(GobberConfigBuilder.GOBBER2_ORE_CHANCE.get(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(GobberConfigBuilder.GOBBER2_ORE_MIN_HEIGHT.get()),
                VerticalAnchor.absolute(GobberConfigBuilder.GOBBER2_ORE_MAX_HEIGHT.get()))));

        ORE_GOBBER_OVERWORLD_DEEPSLATE = GobberConfiguredFeature.GOBBER2_ORE_DEEPSLATE
                .placed(commonOrePlacement(GobberConfigBuilder.GOBBER2_ORE_DEEPSLATE_CHANCE.get(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(GobberConfigBuilder.GOBBER2_ORE_DEEPSLATE_MIN_HEIGHT.get()),
                VerticalAnchor.absolute(GobberConfigBuilder.GOBBER2_ORE_DEEPSLATE_MAX_HEIGHT.get()))));

        ORE_GOBBER_NETHER = GobberConfiguredFeature.GOBBER2_ORE_NETHER
                .placed(commonOrePlacement(GobberConfigBuilder.GOBBER2_ORE_NETHER_CHANCE.get(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(GobberConfigBuilder.GOBBER2_ORE_NETHER_MIN_HEIGHT.get()),
                VerticalAnchor.absolute(GobberConfigBuilder.GOBBER2_ORE_NETHER_MAX_HEIGHT.get()))));

        ORE_GOBBER_THEEND = GobberConfiguredFeature.GOBBER2_ORE_END
                .placed(commonOrePlacement(GobberConfigBuilder.GOBBER2_ORE_END_CHANCE.get(),
                HeightRangePlacement.triangle(VerticalAnchor.absolute(GobberConfigBuilder.GOBBER2_ORE_END_MIN_HEIGHT.get()),
                VerticalAnchor.absolute(GobberConfigBuilder.GOBBER2_ORE_END_MAX_HEIGHT.get()))));

        // Register placed features
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(Gobber2.modid, "gobber2_lucky_block"), ORE_LUCKY_BLOCK_OVERWORLD);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(Gobber2.modid, "gobber2_lucky_block_deepslate"), ORE_LUCKY_BLOCK_OVERWORLD_DEEPSLATE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(Gobber2.modid, "gobber2_lucky_block_nether"), ORE_LUCKY_BLOCK_NETHER);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(Gobber2.modid, "gobber2_ore"), ORE_GOBBER_OVERWORLD);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(Gobber2.modid, "gobber2_ore_deepslate"), ORE_GOBBER_OVERWORLD_DEEPSLATE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(Gobber2.modid, "gobber2_ore_nether"), ORE_GOBBER_NETHER);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(Gobber2.modid, "gobber2_ore_end"), ORE_GOBBER_THEEND);
    }

    @SubscribeEvent
    public static void registerBiomeModification(final BiomeLoadingEvent event)
    {
        // Add features to be inserted into biome generation
		if(GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_GENERATION.get())
        {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(() -> ORE_LUCKY_BLOCK_OVERWORLD);
        }

		if(GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_DEEPSLATE_GENERATION.get())
        {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(() -> ORE_LUCKY_BLOCK_OVERWORLD_DEEPSLATE);
        }

		if(GobberConfigBuilder.GOBBER2_LUCKY_BLOCK_NETHER_GENERATION.get())
        {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(() -> ORE_LUCKY_BLOCK_NETHER);
        }

		if(GobberConfigBuilder.GOBBER2_ORE_GENERATION.get())
        {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(() -> ORE_GOBBER_OVERWORLD);
        }

		if(GobberConfigBuilder.GOBBER2_ORE_DEEPSLATE_GENERATION.get())
        {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(() -> ORE_GOBBER_OVERWORLD_DEEPSLATE);
        }

		if(GobberConfigBuilder.GOBBER2_ORE_NETHER_GENERATION.get())
        {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(() -> ORE_GOBBER_NETHER);
        }

		if(GobberConfigBuilder.GOBBER2_ORE_END_GENERATION.get())
        {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(() -> ORE_GOBBER_THEEND);
        }
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_)
    {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_)
    {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    private static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_)
    {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }
}
