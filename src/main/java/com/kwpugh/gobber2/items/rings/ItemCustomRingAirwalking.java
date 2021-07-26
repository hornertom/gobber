package com.kwpugh.gobber2.items.rings;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.kwpugh.gobber2.init.BlockInit;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
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

public class ItemCustomRingAirwalking extends Item
{
	public ItemCustomRingAirwalking(Properties properties)
	{
		super(properties);
	}

	public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected)
	{
		LivingEntity player = (Player)entity;
		ItemStack equipped = player.getMainHandItem();
		
		if (!world.isClientSide)
		{	
			if(stack == equipped)
			{
				player.setNoGravity(true);
			}
			else
			{
				player.setNoGravity(false);
			}
		}
	}

	//Places an gobber glass block below where the player is looking, think Angel block
	@Nonnull
	public InteractionResultHolder<ItemStack> use(Level world, Player player,@Nonnull InteractionHand hand)
	{			
		if (!world.isClientSide)
		{
			double x = player.position().x + 3 * player.getLookAngle().x;
			double y = 1.5 + player.position().y + 3 * player.getLookAngle().y;
			double z = player.position().z  + 3 * player.getLookAngle().z;

			BlockPos pos = new BlockPos(x,y,z);
			Block glassBlock = BlockInit.GOBBER2_GLASS_END.get();
			BlockState glassDefaultState = glassBlock.defaultBlockState();	      
      
			if (world.isEmptyBlock(pos) || !world.getFluidState(pos).isEmpty())
			{
				world.setBlockAndUpdate(pos, glassDefaultState);
			}
    }
		return new InteractionResultHolder<>(InteractionResult.PASS, player.getItemInHand(hand));
	}
	 
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_airwalking.line1").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_airwalking.line2").withStyle(ChatFormatting.YELLOW)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_airwalking.line3").withStyle(ChatFormatting.YELLOW)));
	} 
}
