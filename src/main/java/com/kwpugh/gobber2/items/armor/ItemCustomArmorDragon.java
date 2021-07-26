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

public class ItemCustomArmorDragon extends ArmorItem
{
	public ItemCustomArmorDragon(ArmorMaterial materialIn, EquipmentSlot slots, Properties builder)
	{
		super(materialIn, slots, builder);
	}	
	
	boolean enablePerks = GobberConfigBuilder.ENABLE_GOBBER_DRAGON_ARMOR_HEALTH_PERKS.get();
	int hunger = GobberConfigBuilder.GOBBER_DRAGON_ARMOR_HUNGER.get();
	double saturation = GobberConfigBuilder.GOBBER_DRAGON_ARMOR_SATURATION.get();
	
	@Override
	public void onArmorTick(final ItemStack stack, final Level world, final Player player)
	{
		//Full Set Bonus
		if(!player.getPersistentData().contains("wearingFullDragonArmor"))player.getPersistentData().putBoolean("wearingFullDragonArmor", false);
			
		ItemStack head = player.getItemBySlot(EquipmentSlot.HEAD);
		ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
		ItemStack legs = player.getItemBySlot(EquipmentSlot.LEGS);
		ItemStack feet = player.getItemBySlot(EquipmentSlot.FEET);	
	
		setDamage(head, 0);
		setDamage(chest, 0);
		setDamage(legs, 0);
		setDamage(feet, 0);
	
		boolean iswearingFullDragonArmor = head != null && head.getItem() == ItemInit.GOBBER2_HELMET_DRAGON.get() && 
				chest != null && chest.getItem() == ItemInit.GOBBER2_CHESTPLATE_DRAGON.get() &&
				legs != null && legs.getItem() == ItemInit.GOBBER2_LEGGINGS_DRAGON.get() && 
				feet != null && feet.getItem() == ItemInit.GOBBER2_BOOTS_DRAGON.get();
	
		boolean wasWearingDragonArmorLastTick = player.getPersistentData().getBoolean("wearingFullDragonArmor");
	  
		if(!iswearingFullDragonArmor && wasWearingDragonArmorLastTick && !player.isCreative())
		{
			player.getAbilities().mayfly = false;
			player.getAbilities().flying = false;
		}
		else if((iswearingFullDragonArmor) && (world.dimension().equals(Level.OVERWORLD) || world.dimension().equals(Level.NETHER) || world.dimension().equals(Level.END)))
		{
			player.getAbilities().mayfly = true;
		}
		player.getPersistentData().putBoolean("wearingFullDragonArmor", iswearingFullDragonArmor);
	

		if(enablePerks)
		{
			if(iswearingFullDragonArmor)
			{
				//Additional full set bonuses			
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
		
			//Check ArmorUtil for additional perks applied to armor
		
			//Helmet
			if(head.getItem() == ItemInit.GOBBER2_HELMET_DRAGON.get())
			{
				PlayerSpecialAbilities.giveYellowHearts(world, player, stack, 20, 0.66F);	
				PlayerSpecialAbilities.giveRegenEffect(world, player, stack, hunger, (float) saturation);
			}
			else
			{
				PlayerSpecialAbilities.giveNoExtraHearts(world, player, stack);
			}			
		}

		//Chestplate
		if(chest.getItem() == ItemInit.GOBBER2_CHESTPLATE_DRAGON.get())
		{				
			//something
		}		
		  
		//Leggings
		if(legs.getItem() == ItemInit.GOBBER2_LEGGINGS_DRAGON.get())
		{
			//TBD
		}
		else
		{
			//something
		}		
	  	  
		//Boots
		if(feet.getItem() == ItemInit.GOBBER2_BOOTS_DRAGON.get())
		{
			//something
		}
		else
		{
			//something
		}		
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	{
		return true;
	}

	@Override
	public void onCraftedBy(ItemStack stack, Level worldIn, Player playerIn)
	{
		stack.getOrCreateTag().putBoolean("Unbreakable", true);
	}
	
	@Override
	public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair)
	{
		return repair.getItem() == ItemInit.GOBBER2_INGOT_END.get();
	}
	
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_armor_dragon.line1").withStyle(ChatFormatting.LIGHT_PURPLE)));
		
		if(enablePerks)
		{
			tooltip.add((new TranslatableComponent("item.gobber2.gobber2_armor_dragon.line2").withStyle(ChatFormatting.GOLD)));	
		}
	}
}
