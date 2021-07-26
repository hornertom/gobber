package com.kwpugh.gobber2.items.rings;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.world.item.Item.Properties;

public class ItemCustomRingAscent extends Item
{
	public ItemCustomRingAscent(Properties properties)
	{
		super(properties);
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
	{
		InteractionResultHolder<ItemStack> result = super.use(world, player, hand);
		
			if (!world.isClientSide)
			{
				if(player.isOnGround())     
				{
					player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 3600, 0, false, false));
				}
			}		
		return result; 	
	}
	
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected)
	{			
		if(entity instanceof Player && entity.isShiftKeyDown())
		{
			if(((LivingEntity) entity).getEffect(MobEffects.LEVITATION) != null)
			{
				((LivingEntity) entity).removeEffectNoUpdate(MobEffects.LEVITATION);
			}	
		}	
	}
	
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_ascent.line1").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_ascent.line2").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_ascent.line3").withStyle(ChatFormatting.YELLOW)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_ascent.line4").withStyle(ChatFormatting.RED)));
	}   
}
