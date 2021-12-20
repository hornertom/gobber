package com.kwpugh.gobber2.util;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;

public class GrowingUtil
{
	public static void growCrops(Level world, Player player, int baseTickDelay, int radius)
	{
		BlockPos playerPos = new BlockPos(player.position());
		
		for (BlockPos targetPos : BlockPos.betweenClosed(playerPos.offset(-radius, -2, -radius), playerPos.offset(radius, 3, radius)))
		{
			BlockState blockstate = world.getBlockState(targetPos);
			Block block = blockstate.getBlock();

			if ((blockstate.getBlock() instanceof CropBlock) ||
					(block instanceof BonemealableBlock) ||
					(block instanceof SaplingBlock) ||
					(block instanceof VineBlock) ||                     		               
					(block instanceof SugarCaneBlock) ||
					(block instanceof SweetBerryBushBlock) ||
					(block instanceof NetherWartBlock) ||
					(block instanceof CactusBlock) ||
					(block instanceof MelonBlock) ||
					(block instanceof StemBlock) ||
					(block instanceof PumpkinBlock)  )
			{
				if (player.tickCount % (baseTickDelay) == 0)
				{	
					blockstate.randomTick((ServerLevel) world, targetPos, world.getServer().overworld().getRandom());
				} 	
			}
     
			if ((block instanceof BambooSaplingBlock) ||
					(block instanceof BambooBlock)  ||
					(block instanceof CocoaBlock) || 
					(block instanceof StemGrownBlock) ||
					(block instanceof CoralPlantBlock) ||
					(block instanceof CoralBlock) ||
					(block instanceof TallSeagrassBlock) ||
					(block instanceof SeagrassBlock) ||
					(block instanceof SeaPickleBlock) ||
					(block instanceof ChorusFlowerBlock) )
			{        				
				if (player.tickCount % (baseTickDelay * 2) == 0)
				{
					blockstate.randomTick((ServerLevel) world, targetPos, world.getServer().overworld().getRandom());
				}                     		
			}

			if(block instanceof GrassBlock && player.isShiftKeyDown())
			{
				if (player.tickCount % (baseTickDelay * 6 ) == 0)
				{
					((GrassBlock) block).performBonemeal((ServerLevel) world, world.random, targetPos, blockstate);	
				}
			}
			
			if(block instanceof FungusBlock)
			{
				if (player.tickCount % (baseTickDelay * 6 ) == 0)
				{
					((FungusBlock) block).performBonemeal((ServerLevel) world, world.random, targetPos, blockstate);
				}	
			}
		}
	}
}