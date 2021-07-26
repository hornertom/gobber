package com.kwpugh.gobber2.init;

import com.kwpugh.gobber2.Gobber2;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class BlockEntityInit
{
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Gobber2.modid);


//	public static final RegistryObject<BlockEntityType<BlockMaturatorTile>> BLOCK_MATURATOR = BLOCK_ENTITY_TYPES.register("block_maturator", () ->
//			BlockEntityType.Builder.of(BlockMaturatorTile::new, BlockInit.BLOCK_MATURATOR.get())
//					.build(null)
//	);
//
//	public static final RegistryObject<BlockEntityType<BlockHealerTile>> BLOCK_HEALER = BLOCK_ENTITY_TYPES.register("block_healer", () ->
//	BlockEntityType.Builder.of(BlockHealerTile::new, BlockInit.BLOCK_HEALER.get())
//			.build(null)
//	);
//
//	public static final RegistryObject<BlockEntityType<BlockProtectorTile>> BLOCK_PROTECTOR = BLOCK_ENTITY_TYPES.register("block_protector", () ->
//	BlockEntityType.Builder.of(BlockProtectorTile::new, BlockInit.BLOCK_PROTECTOR.get())
//			.build(null)
//	);


//	public static final RegistryObject<BlockEntityType<BlockDefenderEntity>> BLOCK_DEFENDER = BLOCK_ENTITY_TYPES.register("block_defender", () ->
//	BlockEntityType.Builder.of(BlockDefenderEntity::new, BlockInit.BLOCK_DEFENDER.get()).build(null)
//	);


//	public static final RegistryObject<BlockEntityType<BlockLooterTile>> BLOCK_LOOTER = BLOCK_ENTITY_TYPES.register("block_looter", () ->
//	BlockEntityType.Builder.of(BlockLooterTile::new, BlockInit.BLOCK_LOOTER.get())
//			.build(null)
//	);
}