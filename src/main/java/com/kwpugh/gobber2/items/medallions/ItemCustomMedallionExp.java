package com.kwpugh.gobber2.items.medallions;

import java.util.List;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.util.ExpUtils;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/*
 * This code is credited to bl4ckscor3
 * 
 * Taken from the mod XP Tome and adapted
 * 
 */


public class ItemCustomMedallionExp extends Item
{
	public static final int MAX_STORAGE = 200000; 
	private final Random random = new Random();

	public ItemCustomMedallionExp()
	{
		super(new Item.Properties().durability(MAX_STORAGE).tab(Gobber2.gobber2));
	}


	// Test code
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);
		
		// Store the players xp
		if(player.isShiftKeyDown() && getXPStored(stack) != MAX_STORAGE)
		{
			int playerXP = ExpUtils.getPlayerXP(player);

			if(playerXP == 0)
				return new InteractionResultHolder<>(InteractionResult.PASS, stack);

			int actuallyStored = addXP(stack, playerXP); //try to store all of the player's levels

			ExpUtils.addPlayerXP(player, -actuallyStored);

			if(!world.isClientSide)
				world.playSound(null, player.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 0.1F, (random.nextFloat() - random.nextFloat()) * 0.35F + 0.9F);

			return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
		}
		// Give stored xp to player
		else if(!player.isShiftKeyDown() && getXPStored(stack) != 0)
		{
			if(getXPStored(stack) >1401)
			{
				ExpUtils.addPlayerXP(player, 1401);
				setStoredXP(stack, getXPStored(stack)-1401);
			}
			else
			{
				ExpUtils.addPlayerXP(player, getXPStored(stack));
				setStoredXP(stack, 0);
			}
			

			if(!world.isClientSide)
			{
				float pitchMultiplier = player.experienceLevel > 30 ? 1.0F : player.experienceLevel / 30.0F;

				world.playSound(null, player.blockPosition(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, pitchMultiplier * 0.75F, 1.0F);
			}

			return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
		}

		return new InteractionResultHolder<>(InteractionResult.PASS, stack);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
// Original code	
//	@Override
//	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
//	{
//		ItemStack stack = player.getHeldItem(hand);
//
//		if(player.isSneaking() && getXPStored(stack) != MAX_STORAGE)
//		{
//			int playerXP = ExpUtils.getPlayerXP(player);
//
//			if(playerXP == 0)
//				return new ActionResult<>(ActionResultType.PASS, stack);
//
//			int actuallyStored = addXP(stack, playerXP); //try to store all of the player's levels
//
//			ExpUtils.addPlayerXP(player, -actuallyStored);
//
//			if(!world.isRemote)
//				world.playSound(null, player.getPosition(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.1F, (random.nextFloat() - random.nextFloat()) * 0.35F + 0.9F);
//
//			return new ActionResult<>(ActionResultType.SUCCESS, stack);
//		}
//		else if(!player.isSneaking() && getXPStored(stack) != 0)
//		{
//			ExpUtils.addPlayerXP(player, getXPStored(stack));
//			setStoredXP(stack, 0);
//
//			if(!world.isRemote)
//			{
//				float pitchMultiplier = player.experienceLevel > 30 ? 1.0F : player.experienceLevel / 30.0F;
//
//				world.playSound(null, player.getPosition(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, pitchMultiplier * 0.75F, 1.0F);
//			}
//
//			return new ActionResult<>(ActionResultType.SUCCESS, stack);
//		}
//
//		return new ActionResult<>(ActionResultType.PASS, stack);
//	}

	@Override
	public void onCraftedBy(ItemStack stack, Level worldIn, Player playerIn)
	{
		stack.setDamageValue(MAX_STORAGE);
	}
	
	@Override
	public boolean isFoil(ItemStack stack)
	{
		return getXPStored(stack) > 0;
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	{
		return false;
	}

	@Override
	public boolean isEnchantable(ItemStack stack)
	{
		return false;
	}

	@Override
	public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair)
	{
		return false;
	}

	@Override
	public boolean isRepairable(ItemStack stack)
	{
		return false;
	}

	/**
	 * Adds the given amount of XP to the given stack. If that action would exceed the storage capacity, as much XP as possible will be stored.
	 * @param stack The stack to add XP to
	 * @param amount The amount of XP to add
	 * @return The amount XP that was added
	 */
	public int addXP(ItemStack stack, int amount)
	{
		int stored = getXPStored(stack);

		if(stored + amount > MAX_STORAGE)
		{
			setStoredXP(stack, MAX_STORAGE);
			return MAX_STORAGE - stored;
		}
		else
		{
			setStoredXP(stack, stored + amount);
			return amount;
		}
	}

	/**
	 * Sets the amount of XP that is stored in the given stack
	 * @param stack The stack to set the amount of stored XP of
	 * @param amount The amount of XP to set the storage to
	 */
	public void setStoredXP(ItemStack stack, int amount)
	{
		stack.setDamageValue(MAX_STORAGE - amount);
	}

	/**
	 * Gets the amount of XP that the given stack has stored
	 * @param stack The stack to get the amount of stored XP from
	 * @return The amount of stored XP in the stack
	 */
	public int getXPStored(ItemStack stack)
	{
		return MAX_STORAGE - stack.getDamageValue(); //if the damage is 0, the book is full on xp
	}
	
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_medallion_exp.line1").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_medallion_exp.line3").withStyle(ChatFormatting.BLUE)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_medallion_exp.line2", getXPStored(stack)).withStyle(ChatFormatting.LIGHT_PURPLE)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_attraction.line3").withStyle(ChatFormatting.YELLOW)));
	} 
}