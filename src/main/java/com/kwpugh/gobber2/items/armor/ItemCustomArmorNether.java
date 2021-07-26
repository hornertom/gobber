package com.kwpugh.gobber2.items.armor;

import java.util.List;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.config.GobberConfigBuilder;
import com.kwpugh.gobber2.init.ItemInit;
import com.kwpugh.gobber2.util.PlayerSpecialAbilities;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.world.item.Item.Properties;

public class ItemCustomArmorNether extends ArmorItem
{
	public ItemCustomArmorNether(ArmorMaterial materialIn, EquipmentSlot slots, Properties builder)
	{
		super(materialIn, slots, builder);
	}
	
	boolean enablePerks = GobberConfigBuilder.ENABLE_GOBBER_NETHER_ARMOR_HEALTH_PERKS.get();
	int hunger = GobberConfigBuilder.GOBBER_NETHER_ARMOR_HUNGER.get();
	double saturation = GobberConfigBuilder.GOBBER_NETHER_ARMOR_SATURATION.get();
	@Override
	public void onArmorTick(final ItemStack stack, final Level world, final Player player)
	{
		if(!enablePerks) return;
		ItemStack head = player.getItemBySlot(EquipmentSlot.HEAD);
		ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
		ItemStack legs = player.getItemBySlot(EquipmentSlot.LEGS);
	    ItemStack feet = player.getItemBySlot(EquipmentSlot.FEET);	
	 
	    //Full Set
    	if(head.getItem() == ItemInit.GOBBER2_HELMET_NETHER.get() && 
    			chest.getItem() == ItemInit.GOBBER2_CHESTPLATE_NETHER.get() && 
    			legs.getItem() == ItemInit.GOBBER2_LEGGINGS_NETHER.get() && 
    			feet.getItem() == ItemInit.GOBBER2_BOOTS_NETHER.get())
    	{
			if(player.getEffect(MobEffects.POISON) != null)
			{
				player.removeEffect(MobEffects.POISON);
			}
			
			if(player.getEffect(MobEffects.WITHER) != null)
			{
				player.removeEffect(MobEffects.WITHER);
			}
    	}	

    	//Check ArmorUtil for additional perks applied to armor   	
	    //Helmet
	    if(head.getItem() == ItemInit.GOBBER2_HELMET_NETHER.get())
		{
			PlayerSpecialAbilities.giveYellowHearts(world, player, stack, 4, 0.33F);
			PlayerSpecialAbilities.giveRegenEffect(world, player, stack, hunger, (float) saturation);	
			//PlayerSpecialAbilities.giveRegenEffect(world, player, stack, 1, 0.05F);			
		}
	    
	    //Chestplate
	    if(chest.getItem() == ItemInit.GOBBER2_CHESTPLATE_NETHER.get())
		{
	 		// TBD
		}		
	    
	    //Leggings
	    if(legs.getItem() == ItemInit.GOBBER2_LEGGINGS_NETHER.get())
		{
			
		}
		else
		{
			
		}		
	    
	    //Boots
	    if(feet.getItem() == ItemInit.GOBBER2_BOOTS_NETHER.get())
		{
			
	    }
		else
	    {
			
	    }		
	}
		
	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	{
		return true;
	}

	@Override
	public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair)
	{
		return repair.getItem() == ItemInit.GOBBER2_INGOT_NETHER.get();
	}
	
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		
		if(enablePerks)
		{
			tooltip.add((new TranslatableComponent("item.gobber2.gobber2_armor_nether.line2").withStyle(ChatFormatting.LIGHT_PURPLE)));
			tooltip.add((new TranslatableComponent("item.gobber2.gobber2_armor_nether.line3").withStyle(ChatFormatting.LIGHT_PURPLE)));
			tooltip.add((new TranslatableComponent("item.gobber2.gobber2_armor_nether.line4").withStyle(ChatFormatting.LIGHT_PURPLE)));
			tooltip.add((new TranslatableComponent("item.gobber2.gobber2_armor_nether.line5").withStyle(ChatFormatting.LIGHT_PURPLE)));
			tooltip.add((new TranslatableComponent("item.gobber2.gobber2_armor_nether.line6").withStyle(ChatFormatting.GOLD)));	
		}
	}
}