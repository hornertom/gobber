package com.kwpugh.gobber2.init;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.blocks.*;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit
{
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Gobber2.modid);

	public static final RegistryObject<Block> GOBBER2_ORE = BLOCKS.register("gobber2_ore", () -> new BlockOreGobber(Block.Properties.of(Material.STONE, MaterialColor.METAL).strength(5.0F, 5.0F).requiresCorrectToolForDrops().sound(SoundType.STONE)));
	public static final RegistryObject<Block> GOBBER2_ORE_DEEPSLATE = BLOCKS.register("gobber2_ore_deepslate", () -> new BlockOreGobber(Block.Properties.of(Material.STONE, MaterialColor.METAL).strength(5.0F, 5.0F).requiresCorrectToolForDrops().sound(SoundType.STONE)));
	public static final RegistryObject<Block> GOBBER2_ORE_NETHER = BLOCKS.register("gobber2_ore_nether", () -> new BlockOreNether(Block.Properties.of(Material.STONE, MaterialColor.METAL).strength(5.0F, 5.0F).requiresCorrectToolForDrops().sound(SoundType.STONE)));
	public static final RegistryObject<Block> GOBBER2_ORE_END = BLOCKS.register("gobber2_ore_end", () -> new BlockOreEnd(Block.Properties.of(Material.STONE, MaterialColor.METAL).strength(5.0F, 5.0F).requiresCorrectToolForDrops().sound(SoundType.STONE)));

	public static final RegistryObject<Block> GOBBER2_BLOCK = BLOCKS.register("gobber2_block", () -> new BlockGobberBlock(Block.Properties.of(Material.METAL, MaterialColor.METAL).strength(3.0F, 3.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> GOBBER2_BLOCK_NETHER = BLOCKS.register("gobber2_block_nether", () -> new BlockNetherBlock(Block.Properties.of(Material.METAL, MaterialColor.METAL).strength(3.0F, 3.0F).sound(SoundType.METAL)));
	public static final RegistryObject<Block> GOBBER2_BLOCK_END = BLOCKS.register("gobber2_block_end", () -> new BlockEndBlock(Block.Properties.of(Material.METAL, MaterialColor.METAL).strength(3.0F, 3.0F).sound(SoundType.METAL)));
	
	public static final RegistryObject<Block> GOBBER2_LUCKY_BLOCK = BLOCKS.register("gobber2_lucky_block", () -> new Block(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(3.0F, 3.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> GOBBER2_LUCKY_BLOCK_DEEPSLATE = BLOCKS.register("gobber2_lucky_block_deepslate", () -> new Block(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(3.0F, 3.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> GOBBER2_LUCKY_BLOCK_NETHER = BLOCKS.register("gobber2_lucky_block_nether", () -> new Block(Block.Properties.of(Material.STONE, MaterialColor.STONE).strength(3.0F, 3.0F).sound(SoundType.STONE)));
	
	public static final RegistryObject<Block> GOBBER2_GLASS = BLOCKS.register("gobber2_glass", () -> new BlockGobberGlass(Block.Properties.of(Material.GLASS, MaterialColor.STONE).strength(2.0F, 2.0F).noOcclusion().lightLevel((state) -> { return 15; }).sound(SoundType.GLASS)));
	public static final RegistryObject<Block> GOBBER2_GLASS_NETHER = BLOCKS.register("gobber2_glass_nether", () -> new BlockGobberGlassNether(Block.Properties.of(Material.GLASS, MaterialColor.STONE).strength(2.0F, 2.0F).noOcclusion().lightLevel((state) -> { return 15; }).sound(SoundType.GLASS)));
	public static final RegistryObject<Block> GOBBER2_GLASS_END = BLOCKS.register("gobber2_glass_end", () -> new BlockGobberGlassEnd(Block.Properties.of(Material.GLASS, MaterialColor.STONE).strength(2.0F, 2.0F).noOcclusion().lightLevel((state) -> { return 15; }).sound(SoundType.GLASS)));
	public static final RegistryObject<Block> CLEAR_GLASS = BLOCKS.register("clear_glass", () -> new BlockClearGlass(Block.Properties.of(Material.GLASS, MaterialColor.STONE).strength(2.0F, 2.0F).noOcclusion().lightLevel((state) -> { return 15; }).sound(SoundType.GLASS)));
}
