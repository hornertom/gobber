package com.kwpugh.gobber2.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

public class CustomOreFeature implements FeatureConfiguration
{
   public static final Codec<CustomOreFeature> CODEC = RecordCodecBuilder.create((p_236568_0_) -> {
      return p_236568_0_.group(RuleTest.CODEC.fieldOf("target").forGetter((p_236570_0_) -> {
         return p_236570_0_.target;
      }), BlockState.CODEC.fieldOf("state").forGetter((p_236569_0_) -> {
         return p_236569_0_.state;
      }), Codec.intRange(0, 64).fieldOf("size").forGetter((p_236567_0_) -> {
         return p_236567_0_.size;
      })).apply(p_236568_0_, CustomOreFeature::new);
   });
   public final RuleTest target;
   public final int size;
   public final BlockState state;

   public CustomOreFeature(RuleTest p_i241989_1_, BlockState p_i241989_2_, int p_i241989_3_) 
   {
      this.size = p_i241989_3_;
      this.state = p_i241989_2_;
      this.target = p_i241989_1_;
   }

   public static final class FillerBlockType 
   {
      public static final RuleTest end_stone = new BlockMatchTest(Blocks.END_STONE);
   }
}