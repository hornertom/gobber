package com.kwpugh.gobber2.items.medallions;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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

public class ItemCustomMedallionStepping extends Item
{
	public ItemCustomMedallionStepping(Properties properties)
	{
		super(properties);
	}

	float currentStepHeight;
	
	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected)
	{		
		if(entity instanceof Player)
		{
			Player player = (Player) entity;
			player.maxUpStep = currentStepHeight;
		}
	}
	
	@Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
    {
		if(!(player.isCrouching()))
		{
			player.displayClientMessage((new TranslatableComponent("item.gobber2.gobber2_medallion_stepping.line1", player.maxUpStep).withStyle(ChatFormatting.GREEN)), true);
		}
		
        if(player.isCrouching())
        {       	
        	if(player.maxUpStep < 1.0F)
		    {
		    	player.maxUpStep = 1.0F;
		    	player.displayClientMessage((new TranslatableComponent("item.gobber2.gobber2_medallion_stepping.line2", player.maxUpStep).withStyle(ChatFormatting.GREEN)), true);
		    }
		    else if(player.maxUpStep == 1.0F)
			{
		    	player.maxUpStep = 2.1F;
		    	player.displayClientMessage((new TranslatableComponent("item.gobber2.gobber2_medallion_stepping.line2", player.maxUpStep).withStyle(ChatFormatting.GREEN)), true);
			}
			else if(player.maxUpStep == 2.1F)
			{
				player.maxUpStep = 3.1F;
				player.displayClientMessage((new TranslatableComponent("item.gobber2.gobber2_medallion_stepping.line2", player.maxUpStep).withStyle(ChatFormatting.GREEN)), true);
			}
			else if(player.maxUpStep == 3.1F)
			{
				player.maxUpStep = 0.6F;
				player.displayClientMessage((new TranslatableComponent("item.gobber2.gobber2_medallion_stepping.line3").withStyle(ChatFormatting.GREEN)), true);
				player.displayClientMessage((new TranslatableComponent("item.gobber2.gobber2_medallion_stepping.line4").withStyle(ChatFormatting.GREEN)), true);
			}		    
		    
        	currentStepHeight = player.maxUpStep;

            return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, player.getItemInHand(hand));
        }
        return super.use(world, player, hand);
	}
	
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_medallion_stepping.line5").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_medallion_stepping.line6").withStyle(ChatFormatting.YELLOW)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_medallion_stepping.line7").withStyle(ChatFormatting.LIGHT_PURPLE)));
	} 
}
