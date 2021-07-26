package com.kwpugh.gobber2.items.rings;
import java.util.List;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.config.GobberConfigBuilder;
import com.kwpugh.gobber2.util.GrowingUtil;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.world.item.Item.Properties;

public class ItemCustomRingFarmer extends Item
{
	int radius = GobberConfigBuilder.RING_FARMER_RADIUS.get();
	int baseTickDelay = GobberConfigBuilder.RING_FARMER_TICK_DELAY.get();
	
	public ItemCustomRingFarmer(Properties properties)
	{
		super(properties);
	}
 
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int par4, boolean par5)
    {      
    	if(!(entity instanceof Player) || world.isClientSide)
        {
            return;
        }

    	Player player = (Player)entity;
        ItemStack equippedMain = player.getMainHandItem();
        
        if(stack == equippedMain)
        {
			if (!world.isClientSide)
			{  
				GrowingUtil.growCrops(world, player, baseTickDelay, radius);
			}
        }
    }
       
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_farmer.line1").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_farmer.line2").withStyle(ChatFormatting.YELLOW)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_farmer.line3", radius).withStyle(ChatFormatting.LIGHT_PURPLE)));
	}  
}