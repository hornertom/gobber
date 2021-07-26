package com.kwpugh.gobber2.blocks.tileentities;

//import com.kwpugh.gobber2.config.GobberConfigBuilder;
//import com.kwpugh.gobber2.init.BlockEntityInit;
//
//import net.minecraft.world.level.block.*;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.block.Seagrass;
//import net.minecraft.world.level.block.TallSeagrass;
//import net.minecraft.world.level.block.entity.TickableBlockEntity;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.core.BlockPos;
//import net.minecraft.server.level.ServerLevel;
//
//public class BlockMaturatorTile extends BlockEntity implements TickableBlockEntity
//{
//	int radius = GobberConfigBuilder.MATURATOR_RADIUS.get();
//	int rediusVertical = GobberConfigBuilder.MATURATOR_VERTICAL_RANGE.get();
//	int minTickInterval = GobberConfigBuilder.MATURATOR_MIN_TICK.get();
//
//    public BlockMaturatorTile()
//	{
//		super(BlockEntityInit.BLOCK_MATURATOR.get());
//	}
//
//	@Override
//    public void tick()
//    {
//		if (level != null && !level.isClientSide)
//		{
//			if (level.getGameTime() % minTickInterval == 0)
//			{
//				for (BlockPos targetPos : BlockPos.betweenClosed(worldPosition.offset(-radius, -2, -radius), worldPosition.offset(radius, rediusVertical, radius)))
//				{
//					BlockState state1 = level.getBlockState(targetPos);
//
//			        if ((state1.getBlock() instanceof CropBlock) ||
//	                		(state1.getBlock() instanceof SaplingBlock) ||
//	                		(state1.getBlock() instanceof VineBlock) ||
//	                		(state1.getBlock() instanceof SugarCaneBlock) ||
//	                		(state1.getBlock() instanceof SweetBerryBushBlock) ||
//	                		(state1.getBlock() instanceof NetherWartBlock) ||
//	                		(state1.getBlock() instanceof CactusBlock) ||
//	                		(state1.getBlock() instanceof MelonBlock) ||
//	                		(state1.getBlock() instanceof StemBlock) ||
//	                		(state1.getBlock() instanceof PumpkinBlock) ||
//	                		(state1.getBlock() instanceof CoralBlock) ||
//	                		(state1.getBlock() instanceof BambooSaplingBlock) ||
//	                		(state1.getBlock() instanceof BambooBlock)  ||
//	                		(state1.getBlock() instanceof CocoaBlock) ||
//	                		(state1.getBlock() instanceof StemGrownBlock) ||
//	                		(state1.getBlock() instanceof CoralPlantBlock) ||
//	                		(state1.getBlock() instanceof CoralBlock) ||
//	                		(state1.getBlock() instanceof TallSeagrassBlock) ||
//	                		(state1.getBlock() instanceof SeagrassBlock) ||
//	                		(state1.getBlock() instanceof SeaPickleBlock) ||
//	                		(state1.getBlock() instanceof ChorusFlowerBlock)  )
//	                {
//			        	state1.randomTick((ServerLevel) level, targetPos, level.getServer().overworld().getRandom());
//	                }
//				}
//			}
//		}
//    }
//}