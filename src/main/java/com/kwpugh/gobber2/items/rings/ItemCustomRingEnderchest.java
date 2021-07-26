package com.kwpugh.gobber2.items.rings;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.SimpleMenuProvider;
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

public class ItemCustomRingEnderchest extends Item
{
	public static final TranslatableComponent CONTAINER_TITLE = new TranslatableComponent("item.gobber2.gobber2_ring_enderchest.title");
	
	public ItemCustomRingEnderchest(Properties properties)
	{
		super(properties);
	}

//	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
//	{		
//		if(entity instanceof PlayerEntity && !world.isRemote)
//		{
//			PlayerEntity player = (PlayerEntity)entity;
//
//			if(ClientEventSubscriber.playerHasKeyPressed)
//	        {
//	    		EnderChestInventory enderChest = player.getInventoryEnderChest();
//	    		
//	    		if (enderChest != null)
//	    		{
//	    			if (!world.isRemote)
//	    			{
//	    				 player.openContainer(new SimpleNamedContainerProvider((p_220114_1_, p_220114_2_, p_220114_3_) -> {
//	    		               return ChestContainer.createGeneric9X3(p_220114_1_, p_220114_2_, enderChest);
//	    		            }, ItemCustomRingEnderchest.CONTAINER_TITLE));
//	    			}
//	    		}
//	        }	
//		}
//	}
//	
//    @Override
//    public ICapabilityProvider initCapabilities(final ItemStack stack, CompoundNBT unused)
//    {
//        if (SupportMods.CURIOS.isLoaded())
//        {
//            return CuriosEnderchestUtil.initCapabilities();
//        }
//        
//        return super.initCapabilities(stack, unused);
//    }
    
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
	{
		
		PlayerEnderChestContainer enderChest = player.getEnderChestInventory();
		
		if (enderChest != null)
		{
			if (!world.isClientSide)
			{
				 player.openMenu(new SimpleMenuProvider((p_220114_1_, p_220114_2_, p_220114_3_) -> {
		               return ChestMenu.threeRows(p_220114_1_, p_220114_2_, enderChest);
		            }, CONTAINER_TITLE));
			}
		}
		return new InteractionResultHolder<ItemStack>(InteractionResult.PASS, player.getItemInHand(hand));
	}
	 
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_enderchest.line1").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_ring_enderchest.line2").withStyle(ChatFormatting.YELLOW)));
	} 
}
