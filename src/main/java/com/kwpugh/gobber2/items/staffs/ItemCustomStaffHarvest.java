package com.kwpugh.gobber2.items.staffs;

import java.util.List;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.config.GobberConfigBuilder;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.world.item.Item.Properties;

public class ItemCustomStaffHarvest extends Item
{ 
	boolean staffHarvestReplant = GobberConfigBuilder.STAFF_HARVEST_REPLANT.get();
	
	public ItemCustomStaffHarvest(Properties properties)
	{
		super(properties);
	}
	
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);
	
		boolean maxAge;
		
		if (!world.isClientSide)
		{
			BlockPos playerPos = new BlockPos(player.position());
    		
    		for (BlockPos targetPos : BlockPos.betweenClosed(playerPos.offset(-11, -2, -11), playerPos.offset(11, 2, 11)))
    		{
				Block block = world.getBlockState(targetPos).getBlock();
				BlockState state = world.getBlockState(targetPos);
				BlockState defaultState = block.defaultBlockState();
				
				//These plants are simply broken with drops
				if(block instanceof BonemealableBlock ||
						block instanceof CocoaBlock ||
						block instanceof MelonBlock ||
						block instanceof PumpkinBlock ||
						block instanceof CactusBlock ||
						block instanceof SugarCaneBlock ||
						block instanceof NetherWartBlock ||
						block instanceof BushBlock ||
						block instanceof BambooBlock)
				{
					world.destroyBlock(targetPos, true);
				}
				
				//Crops are harvested, if at max age, and re-planted
				if(block instanceof CropBlock)
				{
					maxAge = state.getValue(((CropBlock) block).getAgeProperty()) >= ((CropBlock) block).getMaxAge();
					
					if(maxAge)
					{
						world.destroyBlock(targetPos, true);
						
						if(staffHarvestReplant)
						{
							world.setBlockAndUpdate(targetPos, defaultState);	
						}
					}
				}
    		}    	
		}
		
		return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, stack);
	}
	
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_staff_harvest.line1").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_staff_harvest.line2").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_staff_harvest.line3").withStyle(ChatFormatting.YELLOW)));
	}
}
