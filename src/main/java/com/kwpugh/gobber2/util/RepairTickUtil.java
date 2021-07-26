package com.kwpugh.gobber2.util;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.config.GobberConfigBuilder;
import com.kwpugh.gobber2.init.ItemInit;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Gobber2.modid, bus = EventBusSubscriber.Bus.FORGE )
public class RepairTickUtil
{
	public static int repairTickRate = GobberConfigBuilder.RING_REPAIR_DELAY.get();
	
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event)
	{
		Player player = event.player;
		Inventory inv = event.player.getInventory();
		PlayerEnderChestContainer end_inv = player.getEnderChestInventory();

		for (int slot = 0; slot < inv.getContainerSize(); slot++)
		{
			ItemStack stack = inv.getItem(slot);
			if (stack.getItem() == ItemInit.GOBBER2_RING_REPAIR.get())
			{	
				if (player.tickCount % repairTickRate == 0)
        		{
					repair(player, inv);
       		 	} 
			}
		}
		
		for (int slot = 0; slot < end_inv.getContainerSize(); slot++)
		{
			ItemStack stack = end_inv.getItem(slot);
			if (stack.getItem() == ItemInit.GOBBER2_RING_REPAIR.get())
			{	
				if (player.tickCount % repairTickRate == 0)
        		{
					repair(player, inv);
       		 	} 
			}
		}
	}
	
	private static void repair(Player player, Inventory inv)
	{		
		for(int slot = 0; slot < inv.getContainerSize(); slot++)
		{
			ItemStack target = inv.getItem(slot);
			if (!target.isEmpty())
			{
				if (!(target == player.getMainHandItem()))
				{
					if (target.isDamaged() && target.getItem().isRepairable(target))
					{
						target.setDamageValue(target.getDamageValue() - 1);
						return; 
					}
				}
			}
		}
	}
}

