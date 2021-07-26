package com.kwpugh.gobber2.items.rings;

import java.util.List;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.config.GobberConfigBuilder;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.Vec3;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.world.item.Item.Properties;

public class ItemCustomRingAcceleration extends Item
{
	public ItemCustomRingAcceleration(Properties properties)
	{
		super(properties);
	}

	public static double velocityAcceleration = GobberConfigBuilder.RING_ACCELERATION_VELOCITY.get();
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
	{		
		InteractionResultHolder<ItemStack> result = super.use(world, player, hand);
		ItemStack itemstack = result.getObject();
		
		ItemStack equippedMain = player.getMainHandItem();
		
		 if(equippedMain == itemstack)  //Only works while in the main hand
		 {   			
			 // Right-click while in air gives acceleration in direction looking
			if(!player.isOnGround())
			{
				Vec3 look = player.getLookAngle().normalize();
				double lookX = look.x;
				double lookY = look.y;
				double lookZ = look.z;
				
				if(velocityAcceleration < .30)
				{
					player.push(lookX * velocityAcceleration, lookY * velocityAcceleration, lookZ * velocityAcceleration);
				}
				else
				{
					player.displayClientMessage(new TranslatableComponent("item.gobber2.gobber2_ring_acceleration.line3"), true);
				}				
			}
		 }	
		 return result; 
	}
	
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_acceleration.line1").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_acceleration.line2").withStyle(ChatFormatting.YELLOW)));
	}  
}
