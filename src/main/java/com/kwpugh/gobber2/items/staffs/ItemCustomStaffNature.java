package com.kwpugh.gobber2.items.staffs;

import java.util.List;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.init.BlockInit;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.world.item.Item.Properties;

public class ItemCustomStaffNature extends Item
{
	public ItemCustomStaffNature(Properties properties)
	{
		super(properties);
	}

	Block block;
	
	@Override
	public InteractionResult useOn(UseOnContext context)
	{
		 Level world = context.getLevel();
		 Player player = context.getPlayer();
		 BlockPos pos = context.getClickedPos();
		 BlockState state = world.getBlockState(pos);
		 Block block = state.getBlock();
		 ItemStack stack = context.getItemInHand();

		 if(!player.isShiftKeyDown())
		 {
		     if(block == Blocks.ACACIA_SAPLING)
		     {
		    	 world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
		    	 world.setBlock(pos, Blocks.BIRCH_SAPLING.defaultBlockState(), 3);
		    	 stack.hurtAndBreak(1, player, (p_220038_0_) -> {
			         p_220038_0_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
			         });
		    	 
		    	 return InteractionResult.SUCCESS;
		     }
		     else if(block == Blocks.BIRCH_SAPLING)
		     {
		    	 world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
		    	 world.setBlock(pos, Blocks.DARK_OAK_SAPLING.defaultBlockState(), 3);
		    	 stack.hurtAndBreak(1, player, (p_220038_0_) -> {
			         p_220038_0_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
			         });
		    	 
		    	 return InteractionResult.SUCCESS;
		     }
		     else if(block == Blocks.DARK_OAK_SAPLING)
		     {
		    	 world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
		    	 world.setBlock(pos, Blocks.JUNGLE_SAPLING.defaultBlockState(), 3);
		    	 stack.hurtAndBreak(1, player, (p_220038_0_) -> {
			         p_220038_0_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
			         });
		    	 
		    	 return InteractionResult.SUCCESS;
		     }
		     else if(block == Blocks.JUNGLE_SAPLING)
		     {
		    	 world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
		    	 world.setBlock(pos, Blocks.OAK_SAPLING.defaultBlockState(), 3);
		    	 stack.hurtAndBreak(1, player, (p_220038_0_) -> {
			         p_220038_0_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
			         });
		    	 
		    	 return InteractionResult.SUCCESS;
		     }
		     else if(block == Blocks.OAK_SAPLING)
		     {
		    	 world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
		    	 world.setBlock(pos, Blocks.SPRUCE_SAPLING.defaultBlockState(), 3);
		    	 stack.hurtAndBreak(1, player, (p_220038_0_) -> {
			         p_220038_0_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
			         });
		    	 
		    	 return InteractionResult.SUCCESS;
		     }
		     else if(block == Blocks.SPRUCE_SAPLING)
		     {
		    	 world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
		    	 world.setBlock(pos, Blocks.ACACIA_SAPLING.defaultBlockState(), 3);
		    	 stack.hurtAndBreak(1, player, (p_220038_0_) -> {
			         p_220038_0_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
			         });
		    	 return InteractionResult.SUCCESS;
		     }
		     else if(block == Blocks.SUGAR_CANE)
		     {
		    	 world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
		    	 world.setBlock(pos, Blocks.BAMBOO_SAPLING.defaultBlockState(), 3);
		    	 stack.hurtAndBreak(1, player, (p_220038_0_) -> {
			         p_220038_0_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
			         });
		    	 
		    	 return InteractionResult.SUCCESS;
		     }
		     else if(block == Blocks.BAMBOO_SAPLING)
		     {
		    	 world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
		    	 world.setBlock(pos, Blocks.SUGAR_CANE.defaultBlockState(), 3);
		    	 stack.hurtAndBreak(1, player, (p_220038_0_) -> {
			         p_220038_0_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
			         });
		    	 
		    	 return InteractionResult.SUCCESS;
		     }			 
		 }
		 
		 if(player.isShiftKeyDown())
		 {
		     if(block == Blocks.GLASS)
		     {
		    	 world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
		    	 world.setBlock(pos, BlockInit.CLEAR_GLASS.get().defaultBlockState(), 3);
		    	 stack.hurtAndBreak(1, player, (p_220038_0_) -> {
			         p_220038_0_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
			         });
		    	 
		    	 return InteractionResult.SUCCESS;
		     }
		 }
		 	    	     
		 return InteractionResult.PASS;
	}
	   
	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	{
		return true;
	}
	
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_staff_nature.line1").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_staff_nature.line2").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_staff_nature.line3").withStyle(ChatFormatting.YELLOW)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_staff_nature.line4").withStyle(ChatFormatting.YELLOW)));
	}
}
