package com.kwpugh.gobber2.items.medallions;

import java.util.List;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.util.EnableUtil;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.world.item.Item.Properties;

public class ItemCustomMedallionHero extends Item
{
	public ItemCustomMedallionHero(Properties properties)
	{
		super(properties);
	}
	
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected)
	{		
		if(entity instanceof Player && !world.isClientSide && EnableUtil.isEnabled(stack))
		{
			Player player = (Player)entity;

			if (player.tickCount % 180 == 0)
			{
				player.addEffect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 600, 2, false, false));
			} 
		}
	}	

	@Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
    {
		ItemStack stack = player.getItemInHand(hand);
		
        if(!world.isClientSide && player.isCrouching())
        {
            EnableUtil.changeEnabled(player, hand);
            player.displayClientMessage((new TranslatableComponent("item.gobber2.gobber2_medallion_hero.line1", EnableUtil.isEnabled(stack)).withStyle(ChatFormatting.GREEN)), true);
            
            return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, player.getItemInHand(hand));
        }
        return super.use(world, player, hand);
    }
	
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_medallion_hero.line2").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_medallion_hero.line1", EnableUtil.isEnabled(stack)).withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_medallion_hero.line3").withStyle(ChatFormatting.YELLOW)));
	} 
}
