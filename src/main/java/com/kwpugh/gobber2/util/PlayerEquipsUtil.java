package com.kwpugh.gobber2.util;

import javax.annotation.Nonnull;

import com.kwpugh.gobber2.config.GobberConfigBuilder;
import com.kwpugh.gobber2.init.ItemInit;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public final class PlayerEquipsUtil
{
    @Nonnull
    @SuppressWarnings("ConstantConditions")
    public static <T> T _null() {
        return null;
    }

    //Full suit of End Armor gets perks
    public static boolean isPlayerGotVoidProtection(Player player)
    { 
    	ItemStack head = player.getItemBySlot(EquipmentSlot.HEAD);
    	ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
    	ItemStack legs = player.getItemBySlot(EquipmentSlot.LEGS);
    	ItemStack feet = player.getItemBySlot(EquipmentSlot.FEET);
    	
	    //Full suit
    	if(head.getItem() == ItemInit.GOBBER2_HELMET_DRAGON.get() &&
      			chest.getItem() == ItemInit.GOBBER2_CHESTPLATE_DRAGON.get() &&
      			legs.getItem() == ItemInit.GOBBER2_LEGGINGS_DRAGON.get() &&
      			feet.getItem() == ItemInit.GOBBER2_BOOTS_DRAGON.get())
      	{
      		return true;  		
      	}
      		
        return false;
    } 
    
    //All helmet tiers get water breathing
    public static boolean isPlayerGotWaterBreathing(Player player)
    { 
    	ItemStack head = player.getItemBySlot(EquipmentSlot.HEAD);

    	if(GobberConfigBuilder.ENABLE_ARMOR_WATER_BREATHING.get())
		{
			//Head piece
			if((head.getItem() == ItemInit.GOBBER2_HELMET.get() ||
					head.getItem() == ItemInit.GOBBER2_HELMET_NETHER.get() ||
					head.getItem() == ItemInit.GOBBER2_HELMET_END.get() ||
					head.getItem() == ItemInit.GOBBER2_HELMET_DRAGON.get())	)
			{
				return true;
			}
		}
      		
        return false;
    } 
    
    //All legging tiers get no fall damage
    public static boolean isPlayerGotFallProtection(Player player)
    {
    	ItemStack legs = player.getItemBySlot(EquipmentSlot.LEGS);
    	ItemStack mainHand = player.getMainHandItem();
    	
    	//Leggings
		if(GobberConfigBuilder.ENABLE_ARMOR_NO_FALL_DAMAGE.get())
		{
			if(legs.getItem() == ItemInit.GOBBER2_LEGGINGS.get() ||
					legs.getItem() == ItemInit.GOBBER2_LEGGINGS_NETHER.get() ||
					legs.getItem() == ItemInit.GOBBER2_LEGGINGS_END.get() ||
					legs.getItem() == ItemInit.GOBBER2_LEGGINGS_DRAGON.get())
			{
				return true;
			}
		}

		if(mainHand.getItem() == ItemInit.GOBBER2_RING_ASCENT.get())
		{
			return true;
		}

		return false;
    } 
    
		
    //Nether and End chestplates get fire protection
    public static boolean isPlayerGotFireProtection(Player player)
    {
    	ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
    	ItemStack mainHand = player.getMainHandItem();
    	
    	//Chestplate
    	if(GobberConfigBuilder.ENABLE_ARMOR_FIRE_PROTECTION.get())
		{
			if(chest.getItem() == ItemInit.GOBBER2_CHESTPLATE_NETHER.get() ||
					chest.getItem() == ItemInit.GOBBER2_CHESTPLATE_END.get()  ||
					chest.getItem() == ItemInit.GOBBER2_CHESTPLATE_DRAGON.get() ||

					mainHand.getItem() == ItemInit.GOBBER2_RING_BLAZE.get())
			{
				return true;
			}
		}

		if(mainHand.getItem() == ItemInit.GOBBER2_RING_BLAZE.get())
		{
			return true;
		}

	    Inventory inv1 = player.getInventory();
	    
		//Is the ring in the player inventory?
		for (int slot = 0; slot < inv1.getContainerSize(); slot++)
		{
			ItemStack stack = inv1.getItem(slot);
			if (stack.getItem() == ItemInit.GOBBER2_RING_PHOENIX.get())
			{	
				return true;
			}
		}

        return false;
    }
    
    //Holding the Ring of Stealth
    public static boolean isPlayerGotStealth(Player player)
    {
    	ItemStack mainHand = player.getMainHandItem();
    	ItemStack offHand = player.getOffhandItem();
    	
    	if(mainHand.getItem() == ItemInit.GOBBER2_RING_STEALTH.get() && offHand.isEmpty())
      	{
      		return true;  		
      	}
      		
        return false;
    } 
    
    public static boolean isPlayerGotHasteRing(Player player)
    { 	    
		Inventory inv2 = player.getInventory();
		
		//Is the ring in the player inventory?
		for (int slot = 0; slot < inv2.getContainerSize(); slot++)
		{
			ItemStack stack = inv2.getItem(slot);
			if (stack.getItem() == ItemInit.GOBBER2_RING_HASTE.get())
			{	
				return true;
			}
		}

		return false;
    } 
    
    public static boolean isPlayerGotExpToken(Player player)
    { 	    
		Inventory inv2 = player.getInventory();
		
		//Is the ring in the player inventory?
		for (int slot = 0; slot < inv2.getContainerSize(); slot++)
		{
			ItemStack stack = inv2.getItem(slot);
			if (stack.getItem() == ItemInit.GOBBER2_MEDALLION_EXP.get())
			{	
				return true;
			}
		}

		return false;
    } 
} 