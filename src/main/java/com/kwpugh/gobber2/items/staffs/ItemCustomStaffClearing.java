package com.kwpugh.gobber2.items.staffs;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.MushroomBlock;
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

public class ItemCustomStaffClearing extends Item
{
	public ItemCustomStaffClearing(Properties properties)
	{
		super(properties);
	}
	
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);

		if (!world.isClientSide)
		{		 	
    		BlockPos playerPos = new BlockPos(player.position());
    		
    		for (BlockPos targetPos : BlockPos.betweenClosed(playerPos.offset(-11, -2, -11), playerPos.offset(11, 2, 11)))
    		{
				Block block = world.getBlockState(targetPos).getBlock();
				
				if (block == Blocks.GRASS || 
						block == Blocks.DEAD_BUSH || 
						block == Blocks.TALL_GRASS || 
						block == Blocks.FERN || 
						block == Blocks.LARGE_FERN || 
						block instanceof MushroomBlock || 
						block instanceof FlowerBlock)
				{
					world.destroyBlock(targetPos, true);
				}
				
				if (block instanceof LeavesBlock && player.isShiftKeyDown())
				{
					world.destroyBlock(targetPos, true);
				}
    		}    		
		}
		
		return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, stack);
	}

	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_staff_clearing.line1").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_staff_clearing.line2").withStyle(ChatFormatting.GREEN)));
	}
}
