package com.kwpugh.gobber2.items.rings;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.resources.ResourceKey;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.world.item.Item.Properties;

public class ItemCustomRingVoid extends Item
{
	public ItemCustomRingVoid(Properties properties)
	{
		super(properties);
	}
    
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand)
	{
		ItemStack stack = entity.getItemInHand(hand);
		
		//int currentDim = entity.dimension.getId();
		
		if(!world.isClientSide)
		{
			  if (world instanceof ServerLevel && !entity.isPassenger() && !entity.isVehicle())
			  {
			        ResourceKey<Level> registrykey = world.dimension() == Level.END ? Level.OVERWORLD : Level.END;
			        ServerLevel serverworld = ((ServerLevel)world).getServer().getLevel(registrykey);
			        
			        if (serverworld == null)
			        {
			        	return InteractionResultHolder.success(stack);
			        }

			        entity.changeDimension(serverworld);		        
			  }	
			  
			  return InteractionResultHolder.success(stack);
		}
		
		return InteractionResultHolder.fail(stack);
	}
			  	
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_void.line1").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_void.line2").withStyle(ChatFormatting.YELLOW)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_void.line3").withStyle(ChatFormatting.LIGHT_PURPLE)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_void.line4").withStyle(ChatFormatting.RED)));

	}  
}
