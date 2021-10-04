package com.kwpugh.gobber2.items.rings;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.world.item.Item.Properties;
import net.minecraftforge.client.event.EntityViewRenderEvent;

public class ItemCustomRingCuring extends Item
{

	public ItemCustomRingCuring(Properties properties)
	{
		super(properties);
	}

	public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected)
	{
		Player player = (Player)entity;
		
		if(entity instanceof Player)
		{		
			if(player.getEffect(MobEffects.BLINDNESS) != null)
			{
				player.removeEffect(MobEffects.BLINDNESS);
			}
	
			if(player.getEffect(MobEffects.MOVEMENT_SLOWDOWN) != null)
			{
				player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
			}
			
			if(player.getEffect(MobEffects.DIG_SLOWDOWN) != null)
			{
				player.removeEffect(MobEffects.DIG_SLOWDOWN);
			}
			
			if(player.getEffect(MobEffects.HARM) != null)
			{
				player.removeEffect(MobEffects.HARM);
			}
			
			if(player.getEffect(MobEffects.CONFUSION) != null)
			{
				player.removeEffect(MobEffects.CONFUSION);
			}
			
			if(player.getEffect(MobEffects.HUNGER) != null)
			{
				player.removeEffect(MobEffects.HUNGER);
			}
			
			if(player.getEffect(MobEffects.POISON) != null)
			{
				player.removeEffect(MobEffects.POISON);
			}
			
			if(player.getEffect(MobEffects.WITHER) != null)
			{
				player.removeEffect(MobEffects.WITHER);
			}
			
			if(player.getEffect(MobEffects.LEVITATION) != null)
			{
				player.removeEffect(MobEffects.LEVITATION);
			}
			
			if(player.getEffect(MobEffects.UNLUCK) != null)
			{
				player.removeEffect(MobEffects.UNLUCK);
			}
			
			if(player.getEffect(MobEffects.WEAKNESS) != null)
			{
				player.removeEffect(MobEffects.WEAKNESS);
			}	
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_curing.line1").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_curing.line2").withStyle(ChatFormatting.YELLOW)));
	}   
}
