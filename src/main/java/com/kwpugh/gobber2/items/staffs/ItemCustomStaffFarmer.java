package com.kwpugh.gobber2.items.staffs;

import com.kwpugh.gobber2.config.GobberConfigBuilder;
import com.kwpugh.gobber2.util.GrowingUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class ItemCustomStaffFarmer extends Item
{
	int radius = GobberConfigBuilder.STAFF_FARMER_RADIUS.get();
	int baseTickDelay = GobberConfigBuilder.STAFF_FARMER_TICK_DELAY.get();
	boolean staffFarmerReplant = GobberConfigBuilder.STAFF_FARMER_REPLANT.get();
	
	public ItemCustomStaffFarmer(Properties properties)
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

	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);
		boolean maxAge;
		
		if (!world.isClientSide)
		{
			BlockPos playerPos = new BlockPos(player.position());
    		
			for (BlockPos targetPos : BlockPos.betweenClosed(playerPos.offset(-radius, -2, -radius), playerPos.offset(radius, 3, radius)))
    		{
				Block block = world.getBlockState(targetPos).getBlock();
				BlockState state = world.getBlockState(targetPos);
				BlockState defaultState = block.defaultBlockState();
				
				//These plants are simply broken with drops
				if(block instanceof CocoaBlock ||
						block instanceof MelonBlock ||
						block instanceof PumpkinBlock ||
						block instanceof CactusBlock ||
						block instanceof SugarCaneBlock ||
						block instanceof NetherWartBlock ||
						block instanceof BambooBlock)
				{
					world.destroyBlock(targetPos, true);
				}
				
				//Crops are harvested, if at max age, and re-planted
				if(block instanceof CropBlock)
				{
					maxAge = state.getValue(((CropBlock) block).getAgeProperty()) >= ((CropBlock) block).getMaxAge();
					
					if(maxAge)
					{
						world.destroyBlock(targetPos, true);
						
						if(staffFarmerReplant)
						{
							world.setBlockAndUpdate(targetPos, defaultState);	
						}
					}
				}
    		}    	
			
		}
		return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, stack);
	}

	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_staff_farmer.line1").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_staff_farmer.line3").withStyle(ChatFormatting.YELLOW)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_staff_farmer.line4").withStyle(ChatFormatting.YELLOW)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_staff_farmer.line2", radius).withStyle(ChatFormatting.LIGHT_PURPLE)));
	}
}
