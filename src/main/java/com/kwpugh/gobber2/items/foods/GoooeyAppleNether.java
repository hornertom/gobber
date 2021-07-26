package com.kwpugh.gobber2.items.foods;

import com.kwpugh.gobber2.util.PlayerSpecialAbilities;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import net.minecraft.world.item.Item.Properties;

public class GoooeyAppleNether extends Item
{

	public GoooeyAppleNether(Properties properties)
	{
		super(properties);
	}

	public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving)
	{
		Player player = (Player) (entityLiving);
		
		ItemStack itemstack = super.finishUsingItem(stack, worldIn, entityLiving);

		PlayerSpecialAbilities.giveNewMaxHealth(worldIn, player, stack, 60.0D);

		return itemstack;
	}	   

	public UseAnim getUseAnimation(ItemStack stack)
	{
		return UseAnim.EAT;
	}
	
}
